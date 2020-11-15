package vendas.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import vendas.Entity.Cliente;
import vendas.Repository.Clientes;

@RestController
@RequestMapping("/api/clientes/")
 public class ClienteController {
	
	@Autowired
	private Clientes clienteRepository;
	
	public ClienteController(Clientes clienteRepository) {
		this.clienteRepository = clienteRepository;
 	}

	@GetMapping("{id}")
 	public Cliente getClientId(@PathVariable Integer id) {		 
  		  return clienteRepository
  					.findById(id)
  					.orElseThrow(() -> 
  					new ResponseStatusException(HttpStatus.NOT_FOUND,
  							"Cliente não encontrado"));	  
	}
	 
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente save(@RequestBody Cliente cliente){
		  return clienteRepository.save(cliente);
 	}
	
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("{id}")
	public void delete(@PathVariable Integer id) {
		clienteRepository.findById(id)
		                 .map(clienteDelete -> {
		                	 clienteRepository.deleteById(id);
		                	 return ResponseEntity.ok().build();
		                 }).orElse(ResponseEntity.notFound().build());
		                 
  		                 
 	}
	
 	@PutMapping("/update/{id}")
	public void update(@PathVariable Integer id , @RequestBody Cliente cliente) {
	    clienteRepository
	    		.findById(id)
	    		.map(clienteExistente -> {
	    			cliente.setId(clienteExistente.getId());
	    			clienteRepository.save(cliente);
	    			return clienteExistente;
	    		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
	    				"Cliente não encontrada"));
	}
	
 	@GetMapping
	public List<Cliente> find(Cliente filtro){
		ExampleMatcher matcher = ExampleMatcher
				                .matching()
				                .withIgnoreCase()
				                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
		
		Example<Cliente> exemple = Example.of(filtro, matcher);
		 return clienteRepository.findAll(exemple);
 	}
	
}


















































