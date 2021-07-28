package com.apirestspringboot.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.apirestspringboot.model.enumerate.RolEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "rol")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Rol extends BaseEntity {

    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true, length = 10)
    private RolEnum nombre;

}
