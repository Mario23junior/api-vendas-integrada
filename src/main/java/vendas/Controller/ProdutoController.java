package vendas.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import vendas.Entity.Produto;
import vendas.Repository.Produtos;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
      
	private Produtos produtoRepository;
	
	public ProdutoController(Produtos produtosRepository) {
		 this.produtoRepository = produtosRepository;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
 	public Produto save(@RequestBody Produto produto) {
		return produtoRepository.save(produto);
	}

}
