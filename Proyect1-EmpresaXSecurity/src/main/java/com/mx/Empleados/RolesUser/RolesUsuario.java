package com.mx.Empleados.RolesUser;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mx.Empleados.Dao.MetodosUserDao;
import com.mx.Empleados.Entity.Usuarios;

@Service
public class RolesUsuario implements UserDetailsService {

    @Autowired
    MetodosUserDao dao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // buscar el usuario a autenticar
        Usuarios userBuscar = dao.buscarUser(username);
        System.out.println("Se encontro el userBuscar " + userBuscar);

        // crear una lista de autorizaciones
        List<GrantedAuthority> rol = new ArrayList<GrantedAuthority>();
        rol.add(new SimpleGrantedAuthority("ADMIN"));

        UserDetails userDetails = new User(userBuscar.getEmail(), userBuscar.getPassword(), rol);

        return userDetails;
    }
}
