package com.gage.store.product;

import java.math.BigDecimal;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.gage.store.product.entity.Categoria;
import com.gage.store.product.entity.Producto;

import com.gage.store.product.repository.ProductoRepositorio;

@DataJpaTest
//@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ProductRepositoryTest {

	@Autowired
	private ProductoRepositorio repositorio;

	@Test
	public void whenFindByCategoryThenReturnListProducto() {

		Producto prod = Producto.builder().categoria(Categoria.builder().codTipo(1).build()).descripcion("linterna")
				.precioCompra(new BigDecimal(5.5)).precioVenta(new BigDecimal(10)).existencia(100).build();

		repositorio.save(prod);

		List<Producto> lista = repositorio.findByCategoria(prod.getCategoria());
		Assertions.assertThat(lista.size()).isEqualByComparingTo(2);

	}
}
