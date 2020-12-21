package com.gage.store.shopping.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gage.store.shopping.entity.Venta;
import com.gage.store.shopping.repository.VentaRepositorio;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VentaServicioImpl implements VentaServicio {

	final VentaRepositorio repositorio;

	@Override
	public List<Venta> listarVenta() {
		return null;
	}

	@Override
	public Venta guardarVenta(Venta venta) {
		return repositorio.save(venta);
	}

	@Override
	public Venta actualizarVenta(Venta venta) {
		return repositorio.save(venta);
	}

	@Override
	public void eliminarVenta(int numVenta) {
		repositorio.deleteById(numVenta);

	}

}
