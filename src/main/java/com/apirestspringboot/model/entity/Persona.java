package com.apirestspringboot.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "per")
@Getter
@Setter
@NoArgsConstructor
public class Persona extends BaseEntity {

	@Column(nullable = false, length = 100)
	private String nombre;

	@Column(length = 100)
	private String profesion;

	@Column(length = 1)
	private String sexo;

}
