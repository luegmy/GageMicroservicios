package com.gage.store.product.service;

import java.util.List;

import com.gage.store.product.entity.Categoria;
import com.gage.store.product.entity.Producto;

public interface ProductoServicio {

	public List<Producto> listarProducto();

	public Producto obtenerProducto(int id);

	public Producto guardarProducto(Producto producto);

	public void eliminarProducto(Producto producto);

	public List<Producto> listarProductoPorCategoria(Categoria categoria);

}
