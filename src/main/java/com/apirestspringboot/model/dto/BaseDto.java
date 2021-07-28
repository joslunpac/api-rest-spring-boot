package com.apirestspringboot.model.dto;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class BaseDto implements Serializable {

	private Long id;

}
