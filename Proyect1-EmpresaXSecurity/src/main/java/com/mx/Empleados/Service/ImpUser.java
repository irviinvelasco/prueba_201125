package com.mx.Empleados.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mx.Empleados.Dao.MetodosUserDao;
import com.mx.Empleados.Entity.Usuarios;

@Service
public class ImpUser implements MetodosUser {

	@Autowired

	MetodosUserDao dao;

	@Override
	public void guardar(Usuarios user) {

		List<Usuarios> lista = lista();

		if (lista.isEmpty()) {
			dao.save(user);
		} else {
			boolean userExiste = false;

			for (Usuarios u : lista) {
				if (u.getUsuario().equalsIgnoreCase(user.getUsuario()) && u.getEmail().equals(user.getEmail())) {
					userExiste = true;
					return;
				}

			}
			if (userExiste) {
				System.out.println("El usuario ya está registrado");
			} else {
				dao.save(user);
			}
		}
	}

	@Override
	public Usuarios buscar(Usuarios user) {
		// TODO Auto-generated method stub
		return dao.buscarUser(user.getEmail());
	}

	@Override
	public List<Usuarios> lista() {
		// TODO Auto-generated method stub
		return dao.findAll(Sort.by(Sort.Direction.ASC, "id"));
	}

}
