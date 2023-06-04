package com.ajsv.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ajsv.model.Cliente;
import com.ajsv.repo.IGenericRepo;
import com.ajsv.repo.IClienteRepo;
import com.ajsv.service.IClienteService;

@Service
public class ClienteServiceImpl extends CRUDImpl<Cliente, Integer> implements IClienteService {

	@Autowired
	private IClienteRepo repo;

	@Override
	protected IGenericRepo<Cliente, Integer> getRepo() {
		return repo;
	}


}
