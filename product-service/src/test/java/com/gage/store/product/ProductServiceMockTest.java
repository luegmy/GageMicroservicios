package com.gage.store.product;

import java.math.BigDecimal;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.gage.store.product.entity.Categoria;
import com.gage.store.product.entity.Producto;
import com.gage.store.product.repository.ProductoRepositorio;
import com.gage.store.product.service.ProductoServicio;
import com.gage.store.product.service.ProductoServicioImp;

@SpringBootTest
public class ProductServiceMockTest {

	ProductoRepositorio repositorioMock=Mockito.mock(ProductoRepositorio.class);

	ProductoServicio servicio;

	@BeforeEach
	public void setup() {

		servicio = new ProductoServicioImp(repositorioMock);

		Producto prod = Producto.builder().categoria(Categoria.builder().codTipo(1).build()).descripcion("linterna")
				.precioCompra(new BigDecimal(5.5)).precioVenta(new BigDecimal(10)).existencia(100).build();

		Mockito.when(repositorioMock.findById(1)).thenReturn(Optional.of(prod));
		Mockito.when(repositorioMock.save(prod)).thenReturn(prod);
	}

	@Test
	public void whenValidGetIdThenReturnProduct() {
		Producto prod = servicio.obtenerProducto(1);
		Assertions.assertThat(prod.getDescripcion()).isEqualTo("linterna");

	}

	@Test
	public void whenValidUpdateStockThenReturnNewStock() {
		Producto newStock = servicio.obtenerProducto(1);
		newStock.setExistencia(newStock.getExistencia() + 5);
		servicio.guardarProducto(newStock);
		Assertions.assertThat(newStock.getExistencia()).isEqualTo(105);
	}

}
