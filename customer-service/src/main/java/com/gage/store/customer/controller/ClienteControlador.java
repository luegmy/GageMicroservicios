package com.gage.store.customer.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gage.store.customer.entity.Cliente;
import com.gage.store.customer.service.ClienteServicio;

@RestController
@RequestMapping("/clientes")
public class ClienteControlador {
	
	@Autowired
	ClienteServicio servicio;
	
	@GetMapping
	public ResponseEntity<List<Cliente>> listarClientes(){
		
		return ResponseEntity.ok(servicio.listarCliente());
	}
	
	@PostMapping
	public ResponseEntity<Cliente> crearClientes(@RequestBody Cliente cliente,@Valid BindingResult result){
		if(result.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,this.formatoMensaje(result));
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(servicio.guardarCliente(cliente));
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
