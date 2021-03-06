package vendas.Service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import vendas.Entity.Usuario;
import vendas.Exception.SenhaInvalidaException;
import vendas.Repository.UsuarioRepository;

@Service
public class UsuarioServicelmpl implements UserDetailsService  {


	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Transactional
	public Usuario salvarUser(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	
	public UserDetails autenticar(Usuario usuario) {
		UserDetails user = loadUserByUsername(usuario.getLogin());
		boolean senhasBatem = encoder.matches(usuario.getSenha(), user.getPassword());
		  
		if(senhasBatem) {
			return user;
		}
		
		  throw new SenhaInvalidaException();
		
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
      Usuario usuario = usuarioRepository.findByLogin(username)
                           .orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado na base de dados"));
      
      String[] roles = usuario.isAdmin() ? 
    		  new String[] {""} : new String[] {"USER"};
    		  
      return User
    		  .builder()
    		  .username(usuario.getLogin())
    		  .password(usuario.getSenha())
    		  .roles(roles)
    		  .build();
	}
}
 