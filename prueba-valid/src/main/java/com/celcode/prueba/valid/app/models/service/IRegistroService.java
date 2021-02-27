package com.celcode.prueba.valid.app.models.service;

import java.util.List;

import com.celcode.prueba.valid.app.models.entity.Registro;

public interface IRegistroService {
	
	public void saveRegistro(Registro reg);
	
	public List<Registro> findAll();
	
	public Registro findById(Long id);

}
