package com.gage.store.product.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gage.store.product.entity.Categoria;
import com.gage.store.product.entity.Producto;
import com.gage.store.product.service.ProductoServicio;

@RestController
@RequestMapping("/productos")
public class ProductoControlador {

	@Autowired
	ProductoServicio servicio;

	@GetMapping
	public ResponseEntity<List<Producto>> listarProductos(
			@RequestParam(name = "categoria", required = false) Integer categoria) {
		List<Producto> lista = new ArrayList<>();
		if (null == categoria) {
			lista = servicio.listarProducto();
			if (lista.isEmpty()) {
				return ResponseEntity.noContent().build();
			}

		} else {
			lista = servicio.listarProductoPorCategoria(Categoria.builder().codTipo(categoria).build());
			if (lista.isEmpty()) {
				return ResponseEntity.notFound().build();
			}
		}

		return ResponseEntity.ok(lista);
	}

	@GetMapping("/{codProducto}")
	public ResponseEntity<Producto> obtenerProducto(@PathVariable("codProducto") int codProducto) {
		Producto prod = servicio.obtenerProducto(codProducto);
		if (prod == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(prod);
	}

	@PostMapping
	public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto,@Valid BindingResult result) {
		if(result.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,this.formatoMensaje(result));
		}
		Producto prod = servicio.guardarProducto(producto);
		return ResponseEntity.status(HttpStatus.CREATED).body(prod);
	}

	@PutMapping
	public ResponseEntity<Producto> actualizarProducto(@RequestBody Producto producto) {
		Producto prod = servicio.guardarProducto(producto);
		if (prod == null) {
			ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(prod);
	}

	@DeleteMapping
	public ResponseEntity<Producto> eliminarProducto(@RequestBody Producto producto) {
		servicio.eliminarProducto(producto);
		if (producto == null) {
			ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(producto);
	}

	private String formatoMensaje(BindingResult result) {
		List<Map<String, String>> errores=result.getFieldErrors().stream()
				.map(err->{
					Map<String, String> error=new HashMap<>();
					error.put(err.getField(), err.getDefaultMessage());
					return error;
				}).collect(Collectors.toList());
		
		ErrorMensaje errorMensaje=ErrorMensaje.builder().code("01").mensajes(errores).build();
		
		ObjectMapper mapper = new ObjectMapper();
        String jsonString="";
        try {
            jsonString = mapper.writeValueAsString(errorMensaje);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
	}

}
