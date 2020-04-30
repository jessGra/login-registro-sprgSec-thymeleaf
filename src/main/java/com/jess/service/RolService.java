package com.jess.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jess.enums.RolNombre;
import com.jess.model.Rol;
import com.jess.repository.RolRepository;

@Service
@Transactional
public class RolService {
	
	@Autowired
	RolRepository rolRepo;
	
	public Optional<Rol> optenerPorRolNombre(RolNombre rolNombre) {
		return rolRepo.findByRolNombre(rolNombre);
	}
	
	public boolean existePorRolNombre(RolNombre rolNombre) {
		return rolRepo.existsByRolNombre(rolNombre);
	}
	
	public void guardar(Rol rol) {
		rolRepo.save(rol);
	}
}
