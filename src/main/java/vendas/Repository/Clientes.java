package vendas.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import vendas.Entity.Cliente;

 public interface Clientes extends JpaRepository<Cliente, Integer>{
    
	@Query(value = "select * from Cliente nome where nome like '%nome%' ", nativeQuery = true)
	List<Cliente> encontrarPorNome(@Param("nome") String nome);
	
	@Modifying
	@Query("delete from Cliente c where c.nome = :nome")
	void deleteByName(String nome);
	
	 boolean existsByNome(String nome);
	 
	 @Query("select c from Cliente c left join fetch c.pedidos where c.id = :id ")
	 Cliente findClienteFetchPedidos(@Param("id")Integer id);
	 
	 
	
 		
}




















