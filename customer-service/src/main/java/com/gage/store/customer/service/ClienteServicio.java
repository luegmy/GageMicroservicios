package com.gage.store.customer.service;

import java.util.List;

import com.gage.store.customer.entity.Cliente;

public interface ClienteServicio {

	public List<Cliente> listarCliente();

	public Cliente guardarCliente(Cliente cliente);

	public Cliente obtenerCliente(String nroDocumento);

	public Cliente actualizarCliente(Cliente cliente);

	public void eliminarCliente(Cliente cliente);

}
