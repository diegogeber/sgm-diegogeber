package com.br.sgmapi.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.br.sgmapi.model.Usuario;
import com.br.sgmapi.repository.UsuarioRepository;

@Service
public class autenticacaoService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository; 
	
	@Override
	public UserDetails loadUserByUsername(String aut) throws UsernameNotFoundException {
		Optional<Usuario>  usuario = usuarioRepository.findByEmail(aut);
		
		if(usuario.isPresent()) {
			return usuario.get();
		}
		
		
		throw new UsernameNotFoundException("Não foi possível logar!");
	}

}
