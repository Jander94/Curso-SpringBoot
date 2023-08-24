package curso.springboot.Vendas.service;

import curso.springboot.Vendas.domain.entity.Pedido;
import curso.springboot.Vendas.rest.dto.ItensPedidoDTO;
import curso.springboot.Vendas.rest.dto.PedidoDTO;

import java.util.List;


public interface PedidoService {
  Pedido salvar (PedidoDTO pedidoDTO);
}
