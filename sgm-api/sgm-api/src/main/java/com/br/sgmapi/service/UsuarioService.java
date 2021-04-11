package com.br.sgmapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.br.sgmapi.dto.UsuarioDto;
import com.br.sgmapi.model.Perfil;
import com.br.sgmapi.model.Usuario;
import com.br.sgmapi.model.UsuarioPerfis;
import com.br.sgmapi.repository.UsuarioPerfisRepository;
import com.br.sgmapi.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioPerfisRepository perfisRepository;
	
	public List<Usuario> getAll(String nome){
		if(null != nome) {
			return usuarioRepository.findByNome(nome);
		}else {
			return usuarioRepository.findAll();
		}
		
	}
	
	public Usuario getUser(Long id){
		
		Optional<Usuario> opt = usuarioRepository.findById(id);
				
		return opt.isPresent() ? opt.get() : null ;
	}
	
	public Usuario salvarUsuario(UsuarioDto dto) {
		
		Usuario usuario = new Usuario();
		
		BeanUtils.copyProperties(dto, usuario);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		usuario.setSenha(encoder.encode(dto.getSenha()));
		List<Perfil> perfis = new ArrayList<>();
		Perfil perfil = new Perfil();
		
		if("ADMINISTRADOR".equals(dto.getPerfil())) {
			perfil.setId(1L);
		}else if("SERVIDOR".equals(dto.getPerfil())) {
			perfil.setId(2L);
		}else if("CIDADAO".equals(dto.getPerfil())) {
			perfil.setId(3L);
		}
		
		perfis.add(perfil);
		
		usuario = usuarioRepository.save(usuario);
		
		UsuarioPerfis up = new UsuarioPerfis();
		up.setPerfis_id(perfil.getId());
		up.setUsuario_id(usuario.getId());
		
		perfisRepository.save(up);
		return usuario;
		
	}
	
}
