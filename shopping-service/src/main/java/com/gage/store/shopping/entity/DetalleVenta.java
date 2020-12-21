package com.gage.store.shopping.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
public class DetalleVenta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int numDetalle;

	@Positive(message = "La cantidad debe ser mayor a cero")
	private BigDecimal cantidad;

	private BigDecimal precio;

	private int codProducto;

	@Transient
	private BigDecimal subtotal;

	public BigDecimal getSubTotal() {
		if (precio.intValue() > 0 && cantidad.intValue() > 0) {
			return cantidad.multiply(precio);
		} else {
			return new BigDecimal(0);
		}
	}

}
