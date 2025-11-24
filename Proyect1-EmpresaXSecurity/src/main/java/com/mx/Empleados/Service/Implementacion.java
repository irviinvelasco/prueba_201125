package com.mx.Empleados.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mx.Empleados.Dao.MetodosDao;
import com.mx.Empleados.Entity.Empleados;

@Service
public class Implementacion implements Metodos {

	@Autowired
	MetodosDao dao;

	@Autowired
	RestTemplate rest;

	public Object listaPokemon() {
		String urlPokemon = "https://pokeapi.co/api/v2/pokemon/ditto";
		return rest.getForObject(urlPokemon, Object.class);
	}

	public Object listaEmpleados() {
		String urlEmpleados = "http://localhost:9003/Api/Modelo/listar";

		return rest.getForObject(urlEmpleados, Object.class);
	}

	@Override
	public void guardar(Empleados empleado) {
		dao.save(empleado);
		System.out.println("Guardado..");
	}

	@Override
	public void editar(Empleados empleado) {
		dao.save(empleado);
		System.out.println("Editado");
	}

	@Override
	public void eliminar(Empleados empleado) {
		dao.delete(empleado);
		System.out.println("Eliminado");
	}

	@Override
	public Empleados buscar(Empleados empleado) {
		return dao.findById(empleado.getId()).orElse(null);
	}

	@Override
	public List<Empleados> listar() {
		return (List<Empleados>) dao.findAll();
	}

	@Override
	public List<Empleados> listarDepto(Empleados empleado) {
		List<Empleados> lista = (List<Empleados>) dao.findAll();
		lista.stream().forEach(a -> a.getDepto().equalsIgnoreCase(empleado.getDepto()));
		return lista;
	}


}
