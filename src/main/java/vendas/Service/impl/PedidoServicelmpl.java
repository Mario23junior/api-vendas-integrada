package vendas.Service.impl;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import vendas.Entity.Cliente;
import vendas.Entity.Pedido;
import vendas.Exception.RegrasNegocioException;
import vendas.PedidoDTO.PedidoDTO;
import vendas.Repository.Clientes;
import vendas.Repository.Pedidos;
import vendas.Repository.Produtos;
import vendas.Service.PedidoService;

@Service
public class PedidoServicelmpl implements PedidoService {
    
	private Pedidos pedidoRepository;
	private Clientes clientesRepository;
	private Produtos produtosRepository;
	
	public PedidoServicelmpl(Pedidos pedidoRepository, Clientes clientesRepository, 
			Produtos produtosRepository ) {
	       this.pedidoRepository = pedidoRepository;
	       this.clientesRepository = clientesRepository;
	       this.produtosRepository = produtosRepository;
	}

 	public Pedido salvar(PedidoDTO dto) {
 		Integer idcliente = dto.getCliente();
 		clientesRepository
	 		.findById(idcliente)
	 		.orElseThrow(() -> new RegrasNegocioException("Codigo de cliente Invalido"));
 		
 		Pedido pedido = new Pedido();
 		pedido.setTotal(dto.getTotal());
 		pedido.setDataPedido(LocalDate.now());
 		pedido.setcliente();
 		
  		return null;
	}
	
}
