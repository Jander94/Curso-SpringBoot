package curso.springboot.Vendas.service;

import curso.springboot.Vendas.domain.entity.Pedido;
import curso.springboot.Vendas.rest.dto.PedidoDTO;
import org.springframework.stereotype.Service;


public interface PedidoService {
  Pedido salvar (PedidoDTO pedidoDTO);
}
