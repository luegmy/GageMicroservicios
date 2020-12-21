package com.gage.store.shopping.service;

import java.util.List;

import com.gage.store.shopping.entity.Venta;

public interface VentaServicio {

	List<Venta> listarVenta();

	Venta guardarVenta(Venta venta);

	Venta actualizarVenta(Venta venta);

	void eliminarVenta(int numVenta);

}
