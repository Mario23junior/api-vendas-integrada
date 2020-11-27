package vendas.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import vendas.Entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
       
	Optional<Usuario> findByLogin(String login);
	
}
