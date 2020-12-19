package com.gage.store.customer.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gage.store.customer.entity.Cliente;
import com.gage.store.customer.repository.ClienteRepositorio;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteServicioImpl implements ClienteServicio {

	// @Autowired
	final ClienteRepositorio repositorio;

	@Override
	public List<Cliente> listarCliente() {
		return repositorio.findAll();
	}

	@Override
	public Cliente guardarCliente(Cliente cliente) {
		Cliente clienteBD=obtenerCliente(cliente.getNroDocumento());
		if (clienteBD !=null) {
			return clienteBD;
		}
		
		clienteBD=repositorio.save(cliente);
		return clienteBD;
	}

	@Override
	public void eliminarCliente(Cliente cliente) {
		repositorio.delete(cliente);

	}

	@Override
	public Cliente obtenerCliente(String nroDocumento) {
		return repositorio.findByNroDocumento(nroDocumento);
	}

	@Override
	public Cliente actualizarCliente(Cliente cliente) {
		return repositorio.save(cliente);
		
	}

}
