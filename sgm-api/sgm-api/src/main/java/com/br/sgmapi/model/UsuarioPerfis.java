package com.br.sgmapi.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UsuarioPerfis {
	
	@Id
	private Long usuario_id;
	private Long perfis_id;
	
	public Long getUsuario_id() {
		return usuario_id;
	}
	public void setUsuario_id(Long usuario_id) {
		this.usuario_id = usuario_id;
	}
	public Long getPerfis_id() {
		return perfis_id;
	}
	public void setPerfis_id(Long perfis_id) {
		this.perfis_id = perfis_id;
	}
	
	
	
	
}
