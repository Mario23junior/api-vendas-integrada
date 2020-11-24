package vendas.Service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import vendas.Entity.Cliente;
import vendas.Entity.ItemPedido;
import vendas.Entity.Pedido;
import vendas.Entity.Produto;
import vendas.Exception.PedidoNaoEncontradoException;
import vendas.Exception.RegrasNegocioException;
import vendas.PedidoDTO.ItemPedidoDTO;
import vendas.PedidoDTO.PedidoDTO;
import vendas.Repository.Clientes;
import vendas.Repository.ItemsPedido;
import vendas.Repository.Pedidos;
import vendas.Repository.Produtos;
import vendas.Service.PedidoService;
import vendas.enums.StatusPedido;

@Service
public class PedidoServicelmpl implements PedidoService {
    
	private Pedidos pedidoRepository;
	private Clientes clientesRepository;
	private Produtos produtosRepository;
	private ItemsPedido itemsPedidoRepository;
 	
	public PedidoServicelmpl(Pedidos pedidoRepository, Clientes clientesRepository, 
			Produtos produtosRepository , ItemsPedido itemsPedidoRepository ) {
	       this.pedidoRepository = pedidoRepository;
	       this.clientesRepository = clientesRepository;
	       this.produtosRepository = produtosRepository;
	       this.itemsPedidoRepository = itemsPedidoRepository;
	}
	
    @Override
	@Transactional
 	public Pedido salvar(PedidoDTO dto) {
 		Integer idcliente = dto.getCliente();
 		Cliente cliente = clientesRepository
	 		.findById(idcliente)
	 		.orElseThrow(() -> new RegrasNegocioException("Codigo de cliente Invalido"));
 		
 		Pedido pedido = new Pedido();
 		pedido.setTotal(dto.getTotal());
 		pedido.setDataPedido(LocalDate.now());
 		pedido.setCliente(cliente);
 		pedido.setStatus(StatusPedido.REALIZADO);
 		
 		List<ItemPedido> itemsPedido =  converterItems(pedido, dto.getItems());
 		pedidoRepository.save(pedido);
 		itemsPedidoRepository.saveAll(itemsPedido);
 		pedido.setItens(itemsPedido);
 		
  		return pedido;
	}
 	
 	
 	
 	

 	private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items) {
 		if(items.isEmpty()) {
 			throw new RegrasNegocioException("Não e possivel realizar um pedido sem items");
 		}
 		
 		return items
 				.stream()
 				.map(dto -> {
 					Integer idProduto = dto.getProduto();
 					Produto produto = produtosRepository
 					.findById(idProduto)
 					.orElseThrow(
 							() -> new RegrasNegocioException("Codigo de produto invalido : "+ idProduto
 						));
 					
 					ItemPedido itemPedido = new ItemPedido();
 					itemPedido.setQuantidade(dto.getQuantidade());
 					itemPedido.setPedido(pedido);
 					itemPedido.setProduto(produto);
 					return itemPedido;
 					
 				}).collect(Collectors.toList());
 	}

	@Override
	public Optional<Pedido> obterPedidoCompleto(Integer id) {
 		return pedidoRepository.findByIdFetchItens(id) ;
	}

	@Override
	@Transactional
	public void atualizaStatus(Integer id, StatusPedido statusPedido) {
		pedidoRepository
 		             .findById(id)
 		             .map(pedido -> {
 		            	 pedido.setStatus(statusPedido);
 		            	 return pedidoRepository.save(pedido);
 		             }).orElseThrow(() -> new PedidoNaoEncontradoException());
	}
	
}































