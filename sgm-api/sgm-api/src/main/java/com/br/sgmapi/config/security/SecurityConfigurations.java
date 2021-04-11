package com.br.sgmapi.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.br.sgmapi.repository.UsuarioRepository;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

	@Autowired
	private autenticacaoService autenticacaoService;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UsuarioRepository repository;
	
		@Override
		@Bean
		protected AuthenticationManager authenticationManager() throws Exception {
			return super.authenticationManager();
		}
		
	   //autenticação 
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {

			auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
		}
		
		//autorização
		@Override
		protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
		.antMatchers(HttpMethod.POST, "/auth").permitAll()
		.antMatchers(HttpMethod.GET, "/tributo").permitAll()
		.antMatchers(HttpMethod.POST, "/usuario").hasAuthority("ADMINISTRADOR")
		.antMatchers(HttpMethod.GET, "/usuario/*").hasAnyAuthority("ADMINISTRADOR", "SERVIDOR")
		.antMatchers(HttpMethod.POST, "/projeto").hasAnyAuthority("ADMINISTRADOR", "SERVIDOR")
		.antMatchers(HttpMethod.GET, "/projeto").hasAnyAuthority("ADMINISTRADOR", "SERVIDOR")
		.and().csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().addFilterAfter(new AutenticacaoViaTokenFilter(tokenService,repository), UsernamePasswordAuthenticationFilter.class);
				
		}
		
		
		//recursos estáticos(js.css, imagem)
		@Override
		public void configure(WebSecurity web) throws Exception {
			 web.ignoring()
		        .antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
		}
		

	
	
}
