package com.apirestspringboot.model.dto.security;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.apirestspringboot.model.dto.BaseDto;
import com.apirestspringboot.model.enumerate.RolEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RolDto extends BaseDto {

	@NotBlank(message = "El nombre es obligatorio")
	@Size(min = 1, max = 10, message = "El nombre debe tener entre 1 y 10 carácteres")
	private RolEnum nombre;

}
