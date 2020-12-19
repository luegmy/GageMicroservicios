package com.gage.store.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gage.store.customer.entity.Cliente;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Integer>{
	
	public Cliente findByNroDocumento(String nroDocumento);

}
