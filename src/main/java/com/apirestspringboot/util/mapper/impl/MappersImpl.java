package com.apirestspringboot.util.mapper.impl;

import com.apirestspringboot.model.dto.PersonaDto;
import com.apirestspringboot.model.entity.Persona;
import com.apirestspringboot.util.mapper.IMappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MappersImpl implements IMappers {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PersonaDto convertToDto(Persona persona) {
        return modelMapper.map(persona, PersonaDto.class);
    }

    @Override
    public Persona convertToEntity(PersonaDto persona) {
        return modelMapper.map(persona, Persona.class);
    }

}
