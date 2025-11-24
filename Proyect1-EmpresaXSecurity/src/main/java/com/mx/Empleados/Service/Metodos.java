package com.mx.Empleados.Service;

import java.util.List;

import com.mx.Empleados.Entity.Empleados;

public interface Metodos {

	public void guardar(Empleados empleado);

	public void editar(Empleados empleado);

	public void eliminar(Empleados empleado);

	public Empleados buscar(Empleados empleado);

	public List<Empleados> listar();

	public List<Empleados>listarDepto(Empleados empleado);
}
