package com.gage.store.product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.gage.store.product.entity.Categoria;
import com.gage.store.product.entity.Producto;
import com.gage.store.product.repository.ProductoRepositorio;
import com.gage.store.product.service.ProductoServicio;
import com.gage.store.product.service.ProductoServicioImp;

@SpringBootTest
public class ProductServiceMockTest {

	ProductoRepositorio repositorioMock = Mockito.mock(ProductoRepositorio.class);

	ProductoServicio servicio;

	@BeforeEach
	public void setup() {

		servicio = new ProductoServicioImp(repositorioMock);

		Producto prod = Producto.builder().categoria(Categoria.builder().codTipo(1).build()).descripcion("linterna")
				.precioCompra(new BigDecimal(5.5)).precioVenta(new BigDecimal(10)).existencia(100).build();
		Producto prod2 = Producto.builder().categoria(Categoria.builder().codTipo(2).build()).descripcion("martillo")
				.precioCompra(new BigDecimal(7.5)).precioVenta(new BigDecimal(14)).existencia(10).build();

		List<Producto> listaProductos = Arrays.asList(prod, prod2);

		Mockito.when(repositorioMock.findById(1)).thenReturn(Optional.of(prod));
		Mockito.when(repositorioMock.findById(2)).thenReturn(Optional.of(prod2));
		Mockito.when(repositorioMock.save(prod)).thenReturn(prod);
		Mockito.when(repositorioMock.save(prod2)).thenReturn(prod2);
		Mockito.when(repositorioMock.findAll()).thenReturn(listaProductos);
		Mockito.when(repositorioMock.findByCategoria(prod.getCategoria())).thenReturn(Arrays.asList(prod));
		Mockito.when(repositorioMock.findByCategoria(prod2.getCategoria())).thenReturn(Arrays.asList(prod2));
	}

	@Test
	public void whenValidGetIdThenReturnProduct() {
		Producto prod = servicio.obtenerProducto(2);
		Assertions.assertThat(prod.getDescripcion()).isEqualTo("martillo");
	}

	@Test
	public void whenValidUpdateStockThenReturnNewStock() {
		Producto newStock = servicio.obtenerProducto(1);
		newStock.setExistencia(newStock.getExistencia() + 5);
		servicio.guardarProducto(newStock);
		Assertions.assertThat(newStock.getExistencia()).isEqualTo(105);
	}

	@Test
	public void whenFindAllProductThenRetunListProducto() {
		List<Producto> listaProducto = servicio.listarProducto();
		Assertions.assertThat(listaProducto.size()).isEqualTo(2);
	}

	@Test
	public void whenFindByCategoryThenRetunListProducto() {
		List<Producto> listaProducto = servicio.listarProductoPorCategoria(Categoria.builder().codTipo(2).build());
		Assertions.assertThat(listaProducto.size()).isEqualTo(1);
	}

}
