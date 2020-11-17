package vendas.Service.impl;

import org.springframework.stereotype.Service;

import vendas.Repository.Pedidos;
import vendas.Service.PedidoService;

@Service
public class PedidoServicelmpl implements PedidoService {
    
	private Pedidos pedidoRepository;
	
	public PedidoServicelmpl(Pedidos pedidoRepository) {
	       this.pedidoRepository = pedidoRepository;
	}
	
}
