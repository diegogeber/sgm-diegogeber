package com.br.sgmapi.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.br.sgmapi.model.Usuario;
import com.br.sgmapi.repository.UsuarioRepository;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {
	
	private TokenService tokenService;
	private UsuarioRepository repository;
	
	@Autowired
	public AutenticacaoViaTokenFilter(TokenService tokenService, UsuarioRepository repository) {
		this.tokenService = tokenService;
		this.repository = repository;
}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = this.recuperarToken(request);
		
		boolean tokenValido = tokenService.isTokenValido(token);
		if(tokenValido) {
			this.autenticacaoCliente(token);
		}
		filterChain.doFilter(request, response);
	
	}
	
	private void autenticacaoCliente(String token) {
		Long idUsuario = tokenService.getIdUsuario(token);
		Usuario usuario= repository.findById(idUsuario).get();
		
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String recuperarToken(HttpServletRequest request) {
		
		String header = request.getHeader("Authorization");
		
		if(header == null || header.isEmpty() || !header.startsWith("Bearer ")){
			return null;
		}
		
		return header.substring(7, header.length());
	
		
	}

}
