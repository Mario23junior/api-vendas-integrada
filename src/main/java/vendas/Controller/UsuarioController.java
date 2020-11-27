package vendas.Controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import vendas.Entity.Usuario;
import vendas.Service.impl.UsuarioServicelmpl;

@RestController
@RequestMapping("/api/usuarios/")
public class UsuarioController {
    
	private UsuarioServicelmpl usuarioService ;
	
	private PasswordEncoder passwordEncoded;
 	
	public UsuarioController (UsuarioServicelmpl usuarioService, PasswordEncoder passwordEncoder) {
		this.usuarioService = usuarioService;
		this.passwordEncoded = passwordEncoder; 
 	}

	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario salvar(@RequestBody @Valid Usuario usuario) {
		String senhaCriptografada = passwordEncoded.encode(usuario.getSenha());
		usuario.setSenha(senhaCriptografada);
		return usuarioService.salvarUser(usuario);
	}
	
	
}
