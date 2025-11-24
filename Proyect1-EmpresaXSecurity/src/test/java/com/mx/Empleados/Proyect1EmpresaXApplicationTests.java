package com.mx.Empleados;


import static org.junit.jupiter.api.Assertions.assertFalse;

import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mx.Empleados.Entity.Empleados;
import com.mx.Empleados.Service.Implementacion;

@SpringBootTest
@RunWith(SpringRunner.class)
class Proyect1EmpresaXApplicationTests {

	@Autowired
	Implementacion imp;
	@Test
	@Order(1)
	public void listar() {
		List<Empleados> lista = imp.listar();
		System.out.println("Lista de empleados:" +lista);
		assertFalse(lista.isEmpty(),"No existen empleados.");
	}
	@Test
	@Order(2)
	public void guardar() {
		Empleados empleado = new Empleados();
		empleado.setId(20);
		empleado.setNombre("Test");
		empleado.setApellido("Prueba");
		empleado.setFechaNacimiento(Date.valueOf("2000-10-18"));
		empleado.setEdad(30);
		empleado.setTipoContrato("Test");
		empleado.setDepto("Ventas");
		empleado.setSueldo(10000);
	//	imp.guardar(empleado);
	}
	@Test
	@Order(3)
	public void buscar() {
		Empleados empleado = new Empleados();
		empleado.setId(1);
		Empleados empBuscar = imp.buscar(empleado);
		System.out.println("Se encontró el empleado: "+empBuscar);
		
	}
	@Test
	@Order(4)
	public void editar() {
	    Empleados empleado = new Empleados();
	    empleado.setId(20); // ID del empleado a editar
	    empleado.setNombre("Test");
	    empleado.setApellido("Prueba");
	    empleado.setFechaNacimiento(Date.valueOf("2000-10-18"));
	    empleado.setEdad(30);
	    empleado.setTipoContrato("Test");
	    empleado.setDepto("Ventas");
	    empleado.setSueldo(10000);

	    imp.editar(empleado);
	    System.out.println("Empleado editado correctamente: " + empleado);

	    // Verificamos que se haya actualizado
	    Empleados empActualizado = imp.buscar(empleado);
	    System.out.println("Datos actuales del empleado: " + empActualizado);
	}



	@Test
	@Order(6)
	public void eliminar() {
	    Empleados empleado = new Empleados();
	    empleado.setId(1); // ID del empleado que deseas eliminar

	    imp.eliminar(empleado);
	    System.out.println("Empleado eliminado correctamente");
	}

}
