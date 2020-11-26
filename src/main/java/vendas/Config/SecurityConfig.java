package vendas.Config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {
    
	// autenticando dados recebidos do cliente
	@Override
	protected void configure (AuthenticationManagerBuilder auth) throws Exception {
		 
	}
    
	// Autorização dos dados para nevegação api por cliente / definir validação
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 super.configure(http);
	}
}
