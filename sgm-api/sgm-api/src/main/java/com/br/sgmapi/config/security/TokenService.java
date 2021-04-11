package com.br.sgmapi.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.br.sgmapi.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${forum.jwt.expiration}")
	private String expiration;
	
	@Value("${forum.jwt.secret}")
	private String secret;	
	
	public String gerarToken(Authentication authentication) {
		
		Usuario  logado = (Usuario) authentication.getPrincipal();
		
		Date hoje = new Date(); 
		Date dateExpiracao = new Date(hoje.getTime()+ Long.parseLong(expiration));
		
		return Jwts.builder()
				.setIssuer("SGM API")
				.setSubject(logado.getId().toString())
				.claim("role", logado.getAuthorities())
				.claim("nome", logado.getNome())
				.setIssuedAt(hoje)
				.setExpiration(dateExpiracao)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
				
				
	}

	public boolean isTokenValido(String token) {
		try{
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		}
		catch(Exception e ) {
			return false;
		}
	}

	public Long getIdUsuario(String token) {
		Claims c = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
				return Long.parseLong(c.getSubject());
				
				
	}
	
}
