package vendas.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vendas.Entity.Produto;

public interface Produtos extends JpaRepository<Produto, Integer> {

}
