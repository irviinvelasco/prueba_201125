package com.mx.Empleados.Service;

import java.util.List;

import com.mx.Empleados.Entity.Usuarios;

public interface MetodosUser {
	
	public void guardar(Usuarios user);
	public Usuarios buscar(Usuarios user);
	
	public List<Usuarios> lista();
	
}
