package com.jess.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jess.enums.RolNombre;
import com.jess.model.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long>{
	boolean existsByRolNombre(RolNombre rolNombre);
	Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
