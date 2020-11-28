package vendas.Controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import vendas.Entity.Usuario;
import vendas.Exception.SenhaInvalidaException;
import vendas.PedidoDTO.CredenciaisDTO;
import vendas.PedidoDTO.TokenDTO;
import vendas.Security.jwt.JwtService;
import vendas.Service.impl.UsuarioServicelmpl;

@RestController
@RequestMapping("/api/usuarios/")
public class UsuarioController {
    
	private UsuarioServicelmpl usuarioService ;
	
	private PasswordEncoder passwordEncoded;
	
	private JwtService jwtService;
 	
 
	public UsuarioController(UsuarioServicelmpl usuarioService, PasswordEncoder passwordEncoded,
			JwtService jwtService) {
 		this.usuarioService = usuarioService;
		this.passwordEncoded = passwordEncoded;
		this.jwtService = jwtService;
	}


	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario salvar(@RequestBody @Valid Usuario usuario) {
		String senhaCriptografada = passwordEncoded.encode(usuario.getSenha());
		usuario.setSenha(senhaCriptografada);
		return usuarioService.salvarUser(usuario);
	}
	
	@PostMapping("/auth")
	public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciais) {
		try {
		 UserDetails usuario = Usuario.builder()
				     .login(credenciais.getLogin())
				     .senha(credenciais.getSenha()).build();
		 UserDetails usuarioAutenticado = usuarioService.autenticar((Usuario) usuario);
		 String token = jwtService.gerarToken((Usuario) usuario);
		 return new TokenDTO(((Usuario) usuario).getLogin(), token);
		 
 		}catch (UsernameNotFoundException | SenhaInvalidaException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED , e.getMessage());
  			
 		}
	}
	 
	
}
