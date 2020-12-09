package com.gage.store.product;

import java.math.BigDecimal;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.gage.store.product.entity.Categoria;
import com.gage.store.product.entity.Producto;
import com.gage.store.product.entity.UnidadMedida;
import com.gage.store.product.repository.ProductoRepositorio;

@DataJpaTest
public class ProductRepositoryTest {

	@Autowired
	private ProductoRepositorio repositorio;

	@Test
	public void whenCreateProductThenReturnNotNull() {

		Producto prod = Producto.builder().categoria(Categoria.builder().codTipo(1).build())
				.medida(UnidadMedida.builder().codMedida(58).build()).descripcion("linterna")
				.precioCompra(new BigDecimal(5.5)).precioVenta(new BigDecimal(10)).existencia(100).build();

		Producto prodSaved = repositorio.save(prod);

		Assertions.assertThat(prodSaved).isNotNull();
	}
	
	@Test
	public void whenFindByIdThenReturnProduct() {

		Producto prod = repositorio.findById(1).orElse(null);

		Assertions.assertThat(prod.getDescripcion()).isEqualTo("martillo");
	}


	@Test
	public void whenUpdateProductoThenReturnProductDescription() {

		Producto prod = Producto.builder().codProducto(1).categoria(Categoria.builder().codTipo(1).build())
				.medida(UnidadMedida.builder().codMedida(58).build()).descripcion("linterna minera")
				.precioCompra(new BigDecimal(5.5)).precioVenta(new BigDecimal(10)).existencia(100).build();

		repositorio.save(prod);

		Producto updateProd = repositorio.findById(1).orElse(null);
		Assertions.assertThat(updateProd.getDescripcion()).isEqualTo("linterna minera");
	}

	@Test
	public void whenDeleteProductoThenNoReturnProductDelete() {

		boolean isExistBeforeDelete = repositorio.findById(1).isPresent();

		repositorio.deleteById(1);

		boolean notExistAfterDelete = repositorio.findById(1).isPresent();

		Assertions.assertThat(isExistBeforeDelete).isTrue();
		Assertions.assertThat(notExistAfterDelete).isFalse();

	}
	
	@Test
	public void whenFindByCategryThenReturnListProduct() {

		List<Producto> lista = repositorio.findByCategoria(Categoria.builder().codTipo(1).build());
		Assertions.assertThat(lista.size()).isEqualByComparingTo(1);
	}

}
