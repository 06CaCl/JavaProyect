package com.celcode.prueba.valid.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.celcode.prueba.valid.app.models.entity.Registro;

public interface IRegistroDao extends CrudRepository<Registro, Long> {

}
