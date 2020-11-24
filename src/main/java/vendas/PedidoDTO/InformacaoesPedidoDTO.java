package vendas.PedidoDTO;

import java.math.BigDecimal;
import java.util.List;

import lombok.Builder;

@Builder
public class InformacaoesPedidoDTO {
    
	private Integer codigo;
	private String cpf;
	private String nomeCliente;
	private String dataPedido;
	private BigDecimal total;
	private String status;
	private List<InformacaoesItemsPedidoDTO> items;
	
	public InformacaoesPedidoDTO() {
		
	}


	public InformacaoesPedidoDTO(Integer codigo, String cpf, String nomeCliente, String dataPedido, BigDecimal total,
			String status, List<InformacaoesItemsPedidoDTO> items) {
		super();
		this.codigo = codigo;
		this.cpf = cpf;
		this.nomeCliente = nomeCliente;
		this.dataPedido = dataPedido;
		this.total = total;
		this.status = status;
		this.items = items;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public List<InformacaoesItemsPedidoDTO> getItems() {
		return items;
	}

	public void setItems(List<InformacaoesItemsPedidoDTO> items) {
		this.items = items;
	}

	public String getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(String dataPedido) {
		this.dataPedido = dataPedido;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}

	

}
