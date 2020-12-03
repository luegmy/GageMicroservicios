package com.gage.store.product.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_medida")
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class UnidadMedida {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codMedida;
	private String descripcion;
	private String abreviatura;

}
