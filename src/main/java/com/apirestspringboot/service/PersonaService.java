package com.apirestspringboot.service;

import java.util.List;
import java.util.Optional;

import com.apirestspringboot.model.entity.Persona;

import org.springframework.data.domain.Sort;

public interface PersonaService {

	List<Persona> findAll(Sort sort);

	Optional<Persona> findById(Long id);

	Persona save(Persona persona);

	void deleteById(Long id);

	boolean existsById(Long id);

}
