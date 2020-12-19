package com.gage.store.customer;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.gage.store.customer.entity.Cliente;
import com.gage.store.customer.entity.DocumentoIdentidad;
import com.gage.store.customer.repository.ClienteRepositorio;
import com.gage.store.customer.service.ClienteServicio;
import com.gage.store.customer.service.ClienteServicioImpl;

@SpringBootTest
public class CustomerServiceMockTest {

	ClienteRepositorio repositorioMock = Mockito.mock(ClienteRepositorio.class);

	ClienteServicio servicio;

	@BeforeEach
	public void setup() {
		servicio = new ClienteServicioImpl(repositorioMock);

		Cliente cliente = Cliente.builder().documento(DocumentoIdentidad.builder().codDocumento("1").build())
				.nombre("xyz sac").direccion("los alipios").nroDocumento("20102020153").correo("a@bb.com").build();

		List<Cliente>listaCliente=Arrays.asList(cliente);
		Mockito.when(repositorioMock.findByNroDocumento(cliente.getNroDocumento())).thenReturn(cliente);
		Mockito.when(repositorioMock.findAll()).thenReturn(listaCliente);
	}
	
	@Test
	void whenValidGetNroDocumentThenReturnCustomer() {
		Cliente cliente=servicio.obtenerCliente("20102020153");
		Assertions.assertThat(cliente.getNombre()).isEqualTo("xyz sac");
	}
	
	@Test
	void whenUpdateNameCustomerThenReturnCustomerUpdate() {
		Cliente cliente = servicio.obtenerCliente("20102020153");
		cliente.setNombre("mnl sac");
		servicio.guardarCliente(cliente);
		Assertions.assertThat(cliente.getNombre()).isEqualTo("mnl sac");

	}
	
	@Test
	void whenFindAllCustomerThenReturnListCustomer() {
		List<Cliente>listaCliente=servicio.listarCliente();
		Assertions.assertThat(listaCliente.size()).isEqualTo(1);
	}

}
