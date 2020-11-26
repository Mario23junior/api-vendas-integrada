package vendas.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {
    
	
	// criptografando  e descriptografando
	@Bean
	public PasswordEncoder passwordEncoder() {	
		return new BCryptPasswordEncoder();
    }
	
	
	// autenticando dados recebidos do cliente
	@Override
	protected void configure (AuthenticationManagerBuilder auth) throws Exception {
		 auth
		   .inMemoryAuthentication()
		   .passwordEncoder(passwordEncoder())
		   .withUser("fulano")
		   .password(passwordEncoder().encode("123"))
		   .roles("USER");
	}
    
	// Autorização dos dados para nevegação api por cliente / definir validação
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 super.configure(http);
	}
}
