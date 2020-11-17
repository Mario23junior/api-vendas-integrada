package vendas.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import vendas.Entity.Produto;
import vendas.Repository.Produtos;

@RestController
@RequestMapping("/api/produtos/")
public class ProdutoController {
    
	private Produtos produtoRepository;
	
	public ProdutoController(Produtos produtoRepository) {
		 this.produtoRepository = produtoRepository;
	}
	
	@PostMapping("{id}")
	@ResponseStatus(HttpStatus.CREATED)
 	public Produto save(@RequestBody Produto produto) {
		return produtoRepository.save(produto);
	}
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable Integer id , @RequestBody Produto produto) {
	      produtoRepository
		      .findById(id)
		      .map(p -> {
		    	  produto.setId(p.getId());
		    	  produtoRepository.save(produto);
		    	  return produto;
		      }).orElseThrow(() -> new 
		    		  ResponseStatusException(HttpStatus.NOT_FOUND,
		    		  "Produto não encontrado"));
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		 produtoRepository
		        .findById(id)
		        .map(d -> {
		            produtoRepository.delete(d);
		        	return Void.TYPE;
		        }).orElseThrow(() -> new 
		        		ResponseStatusException(HttpStatus.NOT_FOUND,
		        		"Produto não encontrado"));
	}
	
	@GetMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Produto getById(@PathVariable Integer id) {
		   return produtoRepository
		   .findById(id) 
		   .orElseThrow(() -> 
		      new ResponseStatusException(HttpStatus.NOT_FOUND,
		    	 "Produto não encontrado"));
		   
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public List<Produto> find(Produto buscar){
		ExampleMatcher matcher = ExampleMatcher
				.matching()
				.withIgnoreCase()
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
		Example<Produto> example = Example.of(buscar, matcher);
		return produtoRepository.findAll(example);
	}
	
}

























































