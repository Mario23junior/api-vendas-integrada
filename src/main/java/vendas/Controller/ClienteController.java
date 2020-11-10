package vendas.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import vendas.Entity.Cliente;
import vendas.Repository.Clientes;

@RestController
 public class ClienteController {
	
	@Autowired
	private Clientes clienteRepository;
	
	public ClienteController(Clientes clienteRepository) {
		this.clienteRepository = clienteRepository;
 	}

	@GetMapping("/api/cliente/{id}")
 	public ResponseEntity<Cliente> getClientId(@PathVariable Integer id) {
		 Optional<Cliente> cliente = clienteRepository.findById(id);
		 
		 if(cliente.isPresent()) {
  			 return ResponseEntity.ok(cliente.get());
		 }else {
			 return ResponseEntity.notFound().build();
		 }	 
	}
	 
	@PostMapping("/api/cliente")
	public ResponseEntity<Cliente> save(@RequestBody Cliente cliente){
		  Cliente saveCliente = clienteRepository.save(cliente);
		  return ResponseEntity.ok(saveCliente);
	}
}

















