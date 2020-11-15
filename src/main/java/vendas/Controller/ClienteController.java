package vendas.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
 	public ResponseEntity<Cliente> getClientId(@PathVariable Integer id) {
		 Optional<Cliente> cliente = clienteRepository.findById(id);
		 
		 if(cliente.isPresent()) {
  			 return ResponseEntity.ok(cliente.get());
		 }else {
			 return ResponseEntity.notFound().build();
		 }	 
	}
	 
	@PostMapping
	public ResponseEntity<Cliente> save(@RequestBody Cliente cliente){
		  Cliente saveCliente = clienteRepository.save(cliente);
		  return ResponseEntity.ok(saveCliente);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Cliente> delete(@PathVariable Integer id){
		Optional<Cliente> cliente = clienteRepository.findById(id);
		
		if(cliente.isPresent()) {
		    clienteRepository.delete(cliente.get());
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.notFound().build();
		}
 	}
	
 	@PutMapping("/update/{id}")
	public ResponseEntity<Object> update(@PathVariable Integer id , @RequestBody Cliente cliente) {
	    return clienteRepository
	    		.findById(id)
	    		.map(clienteExistente -> {
	    			cliente.setId(clienteExistente.getId());
	    			clienteRepository.save(cliente);
	    			return ResponseEntity.noContent().build();
	    		}).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
 	@SuppressWarnings("rawtypes")
	@GetMapping
	public ResponseEntity find(Cliente filtro){
		ExampleMatcher matcher = ExampleMatcher
				                .matching()
				                .withIgnoreCase()
				                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
		
		Example exemple = Example.of(filtro, matcher);
		List<Cliente> lista = clienteRepository.findAll(exemple);
		return ResponseEntity.ok(lista);
	}
	
}


















































