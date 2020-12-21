package com.gage.store.shopping.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gage.store.shopping.entity.Venta;
import com.gage.store.shopping.service.VentaServicio;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/ventas")
public class VentaControlador {
	
	@Autowired
	VentaServicio servicio;
	
	@PostMapping
	ResponseEntity<Venta>generarVenta(@Valid @RequestBody Venta venta,BindingResult result ){
		//log.info("Creating Invoice : {}", venta);
		if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatoMensaje(result));
        }
		return ResponseEntity.ok(servicio.guardarVenta(venta));
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
