package com.apirestspringboot.controller.v1;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.apirestspringboot.exception.ApiNotFoundException;
import com.apirestspringboot.model.dto.PersonaDto;
import com.apirestspringboot.model.entity.Persona;
import com.apirestspringboot.service.PersonaService;
import com.apirestspringboot.util.Constantes;
import com.apirestspringboot.util.Utilidades;
import com.apirestspringboot.util.mapper.IMappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = PersonaController.ENTIDADES, description = Constantes.OA_ENDPOINT + PersonaController.ENTIDADES)
public class PersonaController {

	public static final String ENTIDAD = Constantes.PER;
	public static final String ENTIDADES = Constantes.PER_PLURAL;

	private static final String OA_OBTENER_TODOS = Constantes.OA_OBTENER_TODOS + ENTIDADES;
	private static final String OA_OBTENER = Constantes.OA_OBTENER + ENTIDAD;
	private static final String OA_CREAR = Constantes.OA_CREAR + ENTIDAD;
	private static final String OA_ACTUALIZAR = Constantes.OA_ACTUALIZAR + ENTIDAD;
	private static final String OA_ELIMINAR = Constantes.OA_ELIMINAR + ENTIDAD;

	@Autowired
	private Utilidades utilidades;

	@Autowired
	private IMappers mappers;

	@Autowired
	private PersonaService personaService;

	@Operation(summary = OA_OBTENER_TODOS)
	@GetMapping(path = Constantes.V1 + "/personas")
	public ResponseEntity<List<PersonaDto>> findAll(@RequestParam(defaultValue = "nombre,asc") String[] sort) {
		List<Persona> personas = personaService.findAll(Sort.by(utilidades.construirOrders(sort)));

		if (personas.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		List<PersonaDto> personaDtos = personas.stream().map(mappers::convertToDto).collect(Collectors.toList());
		return new ResponseEntity<>(personaDtos, HttpStatus.OK);
	}

	@Operation(summary = OA_OBTENER)
	@GetMapping(path = Constantes.V1 + "/personas/{id}")
	public ResponseEntity<PersonaDto> find(@PathVariable("id") Long id) {
		Persona persona = personaService.findById(id).orElseThrow(
				() -> new ApiNotFoundException(ENTIDAD + Constantes.CON_ID + id + Constantes.NO_ENCONTRADA));

		PersonaDto personaDto = mappers.convertToDto(persona);
		return new ResponseEntity<>(personaDto, HttpStatus.OK);
	}

	@Operation(summary = OA_CREAR)
	@PostMapping(path = Constantes.V1 + "/personas")
	public ResponseEntity<PersonaDto> create(@Valid @RequestBody PersonaDto personaDto) {
		personaDto.setId(null);
		Persona persona = mappers.convertToEntity(personaDto);
		personaDto = mappers.convertToDto(personaService.save(persona));
		return new ResponseEntity<>(personaDto, HttpStatus.CREATED);
	}

	@Operation(summary = OA_ACTUALIZAR)
	@PutMapping(path = Constantes.V1 + "/personas/{id}")
	public ResponseEntity<PersonaDto> update(@PathVariable("id") Long id, @Valid @RequestBody PersonaDto personaDto) {
		if (!personaService.existsById(id))
			throw new ApiNotFoundException(ENTIDAD + Constantes.CON_ID + id + Constantes.NO_ENCONTRADA);

		personaDto.setId(id);
		Persona persona = personaService.save(mappers.convertToEntity(personaDto));
		personaDto = mappers.convertToDto(persona);
		return new ResponseEntity<>(personaDto, HttpStatus.OK);
	}

	@Operation(summary = OA_ELIMINAR)
	@DeleteMapping(path = Constantes.V1 + "/personas/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
		if (!personaService.existsById(id))
			throw new ApiNotFoundException(ENTIDAD + Constantes.CON_ID + id + Constantes.NO_ENCONTRADA);

		personaService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
