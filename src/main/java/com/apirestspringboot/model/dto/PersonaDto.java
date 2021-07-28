package com.apirestspringboot.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PersonaDto extends BaseDto {

	@NotBlank(message = "El nombre es obligatorio")
	@Size(min = 1, max = 100, message = "El nombre debe tener entre 1 y 100 carácteres")
	private String nombre;

	@Size(min = 1, max = 100, message = "La profesión debe tener entre 1 y 100 carácteres")
	private String profesion;

	@Size(min = 1, max = 1, message = "El sexo debe tener 1 carácter")
	private String sexo;

}
