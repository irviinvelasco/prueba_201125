package com.mx.Empleados.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mx.Empleados.Entity.Usuarios;

@Repository
public interface MetodosUserDao extends JpaRepository<Usuarios, Integer> {

	@Query(nativeQuery = true, value = "SELECT * FROM USER_EMPLEADOS WHERE EMAIL=:EMAIL")
	public Usuarios buscarUser(@Param("EMAIL") String email);

}
