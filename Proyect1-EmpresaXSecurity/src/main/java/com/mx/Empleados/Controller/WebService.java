package com.mx.Empleados.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mx.Empleados.Entity.Empleados;

import com.mx.Empleados.Service.Implementacion;

@RestController
@RequestMapping(path = "Api")
@CrossOrigin("*")
public class WebService {

	@Autowired
	private Implementacion imp;

	// http://localhost:9000/Api/pokemon
	@GetMapping("pokemon")
	public ResponseEntity<?> pokemon() {
		return ResponseEntity.status(HttpStatus.CREATED).body(imp.listaPokemon());
	}

	// http://localhost:9000/Api/listaEmpleados
	@GetMapping("listaEmpleados")
	public ResponseEntity<?> listaEmpleados() {
		return ResponseEntity.status(HttpStatus.CREATED).body(imp.listaEmpleados());
	}

	// LISTAR http://localhost:9000/Api/lista
	@GetMapping("lista")
	public List<Empleados> lista() {
		return imp.listar();
	}

	// GUARDAR
	@PostMapping("guardar")
	public String guardar(@RequestBody Empleados empleado) {
		imp.guardar(empleado);
		return "Empleado guardado correctamente";
	}

	// EDITAR
	@PostMapping("editar")
	public String editar(@RequestBody Empleados empleado) {
		imp.editar(empleado);
		return "Empleado editado correctamente";
	}

	// ELIMINAR
	@PostMapping("eliminar")
	public String eliminar(@RequestBody Empleados empleado) {
		imp.eliminar(empleado);
		return "Empleado eliminado correctamente";
	}

	@PostMapping("buscar")
	public Empleados buscar(@RequestBody Empleados empleado) {
		return imp.buscar(empleado);
	}

}
