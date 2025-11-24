package com.mx.Empleados.Dao;

import org.springframework.data.repository.CrudRepository;

import com.mx.Empleados.Entity.Empleados;

public interface MetodosDao extends CrudRepository<Empleados, Integer> {

	
}
