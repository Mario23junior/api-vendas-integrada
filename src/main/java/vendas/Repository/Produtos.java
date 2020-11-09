package vendas.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vendas.Entity.Pedido;

public interface Produtos extends JpaRepository<Pedido, Integer> {

}
