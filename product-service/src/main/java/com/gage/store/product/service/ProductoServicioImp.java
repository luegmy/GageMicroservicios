package com.gage.store.product.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gage.store.product.entity.Categoria;
import com.gage.store.product.entity.Producto;
import com.gage.store.product.repository.ProductoRepositorio;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductoServicioImp implements ProductoServicio {

	
	final ProductoRepositorio repositorio;

	@Override
	public List<Producto> listarProducto() {
		return repositorio.findAll();
	}

	@Override
	public Producto obtenerProducto(int id) {
		return repositorio.findById(id).orElse(null);
	}

	@Override
	public Producto guardarProducto(Producto producto) {
		producto.setFecha(new Date());
		return repositorio.save(producto);
	}

	@Override
	public void eliminarProducto(Producto producto) {
		 repositorio.delete(producto);
	}

	@Override
	public List<Producto> listarProductoPorCategoria(Categoria categoria) {
		return repositorio.findByCategoria(categoria);
	}



}
