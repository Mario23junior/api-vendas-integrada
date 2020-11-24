package vendas.Controller;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import vendas.Entity.ItemPedido;
import vendas.Entity.Pedido;
import vendas.PedidoDTO.InformacaoesItemsPedidoDTO;
import vendas.PedidoDTO.InformacaoesPedidoDTO;
import vendas.PedidoDTO.PedidoDTO;
import vendas.Service.PedidoService;

@RestController
@RequestMapping("/api/pedidos/")
public class PedidoController {
    
	private PedidoService service;
	
	public PedidoController(PedidoService service) {
		this.service = service;
 	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Integer save (@RequestBody PedidoDTO dto) {
		Pedido pedido = service.salvar(dto);
		return pedido.getId();
	}
	
	@GetMapping("{id}")
	public InformacaoesPedidoDTO getById(@PathVariable Integer id) {
		return service
				.obterPedidoCompleto(id)
				.map( p -> converter(p) )
				.orElseThrow( () -> 
				           new ResponseStatusException(HttpStatus.NOT_FOUND,"pedido não encontrado"));
	}
	
	private InformacaoesPedidoDTO converter(Pedido pedido) {
	   return  InformacaoesPedidoDTO
	                 .builder()
	                 .codigo(pedido.getId())
	                 .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
	                 .cpf(pedido.getCliente().getNome())
	                 .nomeCliente(pedido.getCliente().getNome())
	                 .total(pedido.getTotal())
	                 .status(pedido.getStatus().name())
	                 .items(converter(pedido.getItens()))
	                 .build();               
     	}
	
	private List<InformacaoesItemsPedidoDTO> converter(List<ItemPedido> itens){
		if(CollectionUtils.isEmpty(itens)) {
			return Collections.emptyList();
		}
		
		return itens.stream().map(
				   item -> InformacaoesItemsPedidoDTO
				     .builder().descricaoProduto(item.getProduto().getDescricao())
				     .precoUnitario(item.getProduto().getPreco())
				     .quantidade(item.getQuantidade())
				     .build()
		).collect(Collectors.toList());
	}

}










