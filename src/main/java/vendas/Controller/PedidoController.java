package vendas.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vendas.Service.PedidoService;

@RestController
@RequestMapping("/api/pedidos/")
public class PedidoController {
    
	private PedidoService service;
	
	public PedidoController(PedidoService service) {
		this.service = service;
 	}
	
	
}
