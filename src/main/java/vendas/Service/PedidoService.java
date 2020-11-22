package vendas.Service;

import org.springframework.stereotype.Service;

import vendas.Entity.Pedido;
import vendas.PedidoDTO.PedidoDTO;

@Service
public interface PedidoService {
	Pedido salvar(PedidoDTO dto);
}
