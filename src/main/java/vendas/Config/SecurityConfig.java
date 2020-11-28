package vendas.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import vendas.Security.jwt.JwtAuthFilter;
import vendas.Security.jwt.JwtService;
import vendas.Service.impl.UsuarioServicelmpl;

@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {
    
	@Autowired
	private UsuarioServicelmpl usuarioService;
	
	@Autowired
	private JwtService jwtService;
	
	
	// criptografando  e descriptografando
	@Bean
	public PasswordEncoder passwordEncoder() {	
		return new BCryptPasswordEncoder();
    }
	
	
	@Bean
	public OncePerRequestFilter jwtFilter() {
		return new JwtAuthFilter(jwtService, usuarioService);
	}
	
	// autenticando dados recebidos do cliente
	@Override
	protected void configure (AuthenticationManagerBuilder auth) throws Exception {
		 auth
		     .userDetailsService(usuarioService)
		     .passwordEncoder(passwordEncoder());
	}
    
	// URLS Autorização dos dados para nevegação api por cliente / definir validação
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http
		   .csrf().disable()
		   .authorizeRequests()
		       .antMatchers("/api/clientes/**")
		          .hasAnyRole("USER","ADMIN")
		          
		       .antMatchers("/api/produtos/**")
		          .hasRole("ADMIN")
		         
		       .antMatchers("/api/pedidos/**")
		          .hasRole("ADMIN")
		          
		       .antMatchers(HttpMethod.POST, "/api/usuarios/**")  
		          .permitAll()
		          .anyRequest().authenticated()
		          
		   .and()
		   
		     .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		 .and()
		    .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);

		    
	}
}









