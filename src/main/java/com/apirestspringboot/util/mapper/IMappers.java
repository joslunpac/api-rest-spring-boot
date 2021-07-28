package com.apirestspringboot.util.mapper;

import com.apirestspringboot.model.dto.PersonaDto;
import com.apirestspringboot.model.entity.Persona;

public interface IMappers {

    public PersonaDto convertToDto(Persona persona);

    public Persona convertToEntity(PersonaDto persona);

}
