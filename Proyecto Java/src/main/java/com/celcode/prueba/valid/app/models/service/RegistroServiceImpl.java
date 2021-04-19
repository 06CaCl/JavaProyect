package com.celcode.prueba.valid.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.celcode.prueba.valid.app.models.dao.IRegistroDao;
import com.celcode.prueba.valid.app.models.entity.Registro;

@Service
public class RegistroServiceImpl implements IRegistroService{
	
	@Autowired
	private IRegistroDao registroDao;

	@Override
	@Transactional
	public void saveRegistro(Registro reg) {
		registroDao.save(reg);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Registro> findAll() {
		return (List<Registro>) registroDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Registro findById(Long id) {
		return registroDao.findById(id).orElse(null);
	}

}
