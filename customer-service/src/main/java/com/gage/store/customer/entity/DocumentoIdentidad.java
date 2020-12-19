package com.gage.store.customer.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_documento_identidad")
@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor @Builder
public class DocumentoIdentidad {

	@Id
	@NotNull
	private String codDocumento;
	private String descripcion;
}
