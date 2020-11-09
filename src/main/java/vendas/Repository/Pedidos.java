package vendas.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import vendas.Entity.Cliente;
import vendas.Entity.Pedido;

public interface Pedidos  extends JpaRepository<Pedido, Integer>{
    
   List<Pedido> findByCliente(Cliente cliente);
}
