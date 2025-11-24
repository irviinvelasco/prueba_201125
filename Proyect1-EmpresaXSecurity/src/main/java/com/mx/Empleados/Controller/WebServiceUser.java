package com.mx.Empleados.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.Empleados.Dao.MetodosUserDao;
import com.mx.Empleados.Entity.Usuarios;
import com.mx.Empleados.Service.ImpUser;

@RestController
@RequestMapping(path = "Api/User")
@CrossOrigin("*")
public class WebServiceUser {

	private final AuthenticationManager authenticationManager;

	@Autowired
	ImpUser imp;

	WebServiceUser(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	// http://localhost:9000/Api/User/lista
	@GetMapping(value = "lista")
	public ResponseEntity<?> lista() {
		return ResponseEntity.status(HttpStatus.CREATED).body(imp.lista());
	}

	// http://localhost:9000/Api/User/guardar
	@PostMapping(value = "guardar")
	public ResponseEntity<?> guardar(@RequestBody Usuarios user) {
		imp.guardar(user);
		return new ResponseEntity<String>("Guardado", HttpStatus.OK);
	}

	// http://localhost:9000/Api/User/buscarEmail
	@PostMapping(value = "buscarEmail")
	public ResponseEntity<?> buscarEmail(@RequestBody Usuarios user) {
		return ResponseEntity.status(HttpStatus.CREATED).body(imp.buscar(user));
	}

	// http://localhost:9000/Api/User/guardarBcryp
	@Autowired
	BCryptPasswordEncoder encoder;

	@PostMapping(value = "guardarBcryp")
	public ResponseEntity<?> guardarBcryp(@RequestBody Usuarios user) {

		user.setPassword(encoder.encode(user.getPassword()));

		imp.guardar(user);
		return new ResponseEntity<String>("Guardado", HttpStatus.OK);
	}

	@Autowired
	MetodosUserDao dao;

	// http://localhost:9000/Api/User/login
	// LOGIN --> ANGULAR
	@PostMapping(value = "login")
	public ResponseEntity<?> login(@RequestBody Usuarios user) {
		Usuarios userBuscar = dao.buscarUser(user.getEmail());
		System.out.println("Usuario auth --> " + userBuscar);
		if (userBuscar != null) {
			System.out.println("Entrando al login");
			if (encoder.matches(user.getPassword(), userBuscar.getPassword())) {
				System.out.println("Coinciden las contraseñas");
				return ResponseEntity.status(HttpStatus.ACCEPTED).body(userBuscar);
			}
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{Usuario no autorizado}");
	}

}
