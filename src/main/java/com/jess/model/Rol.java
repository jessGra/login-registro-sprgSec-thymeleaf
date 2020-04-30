package com.jess.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.jess.enums.RolNombre;

@Entity
public class Rol {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(unique = true)
	private RolNombre rolNombre;
	
	public Rol() {
	}

	public Rol(@NotNull RolNombre rolNombre) {
		this.rolNombre = rolNombre;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public RolNombre getRolNombre() {
		return rolNombre;
	}

	public void setRolNombre(RolNombre rolNombre) {
		this.rolNombre = rolNombre;
	}
}
