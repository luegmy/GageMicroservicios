package com.gage.store.customer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_cliente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codCliente;
	
	@NotEmpty(message = "Ingrese un nombre")
	private String nombre;
	
	@NotEmpty(message = "Ingrese una direccion")
	private String direccion;
	
	@NotNull(message = "Seleccione el docuemnto")
	private String nroDocumento;
	
	private String telefono;
	
	@Email(message = "No es un dirección de correo bien formada")
	@Column(unique = true)
	private String correo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codDocumento")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@Valid
	private DocumentoIdentidad documento;

}
