package com.apirestspringboot.service.impl;

import java.util.List;
import java.util.Optional;

import com.apirestspringboot.model.entity.Persona;
import com.apirestspringboot.repository.PersonaRepository;
import com.apirestspringboot.service.PersonaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonaServiceImpl implements PersonaService {

	@Autowired
	PersonaRepository personaRepository;

	@Override
	public List<Persona> findAll(Sort sort) {
		return personaRepository.findAll(sort);
	}

	@Override
	public Optional<Persona> findById(Long id) {
		return personaRepository.findById(id);
	}

	@Override
	@Transactional
	public Persona save(Persona persona) {
		return personaRepository.save(persona);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		personaRepository.deleteById(id);
	}

	@Override
	public boolean existsById(Long id) {
		return personaRepository.existsById(id);
	}

}
