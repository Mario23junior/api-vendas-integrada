package vendas.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import vendas.Entity.Pedido;
import vendas.PedidoDTO.PedidoDTO;
import vendas.enums.StatusPedido;

@Service
public interface PedidoService {
	Pedido salvar(PedidoDTO dto);
	
	Optional<Pedido> obterPedidoCompleto(Integer id);
	void atualizaStatus( Integer id, StatusPedido statusPedido);
	
}
