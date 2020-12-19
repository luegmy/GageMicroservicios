package com.gage.store.customer;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.gage.store.customer.entity.Cliente;
import com.gage.store.customer.entity.DocumentoIdentidad;
import com.gage.store.customer.repository.ClienteRepositorio;

@DataJpaTest
public class CustomerRepositoryTest {
	
	@Autowired
	private ClienteRepositorio repositorio;
	
	@Test
	void whenSaveCustomerReturnListCustomer() {
		Cliente cliente=Cliente.builder().documento(DocumentoIdentidad.builder().codDocumento("1").build()).nombre("xyz sac")
				.direccion("los alipios").nroDocumento("20102020153").correo("a@bb.com").build();
		repositorio.save(cliente);
		
		List<Cliente> lista=repositorio.findAll();
		Assertions.assertThat(lista.size()).isEqualByComparingTo(2);
	}
	
	@Test
	void whenFindByNroDocumentoThenReturnCustomer() {
		Cliente cliente=repositorio.findByNroDocumento("20202020201");
		Assertions.assertThat(cliente.getNombre()).isEqualTo("abc sac");
	}

}
