package com.gage.store.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gage.store.product.entity.Categoria;
import com.gage.store.product.entity.Producto;
import com.gage.store.product.entity.UnidadMedida;

@Repository
public interface ProductoRepositorio extends JpaRepository<Producto, Integer> {

	public List<Producto> findByCategoria(Categoria categoria);

	public List<Producto> findByMedida(UnidadMedida medida);
}
