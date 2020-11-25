package vendas.PedidoDTO;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;

import vendas.validation.NotEmptyList;

public class PedidoDTO {
	
   @NotNull(message = "Informe o codigo do cliente")	
   private Integer cliente;
   
   @NotNull(message = "Campo total de pedido e obrigatorio.")
   private BigDecimal total;
   
   @NotEmptyList(message ="Pedido não pode ser realizado sem itens.")
   private List<ItemPedidoDTO> items;
   
	public PedidoDTO() {
 	}
	
	public Integer getCliente() {
		return cliente;
	}
	
	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}
	
	public BigDecimal getTotal() {
		return total;
	}
	
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
	public List<ItemPedidoDTO> getItems() {
		return items;
	}
	
	public void setItems(List<ItemPedidoDTO> items) {
		this.items = items;
	}
   
}
