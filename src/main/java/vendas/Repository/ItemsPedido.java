package vendas.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vendas.Entity.ItemPedido;

public interface ItemsPedido extends JpaRepository<ItemPedido, Integer>{

}
