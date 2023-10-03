package curso.springboot.Vendas.service;

import curso.springboot.Vendas.domain.entity.Pedido;
import curso.springboot.Vendas.domain.enums.StatusPedido;
import curso.springboot.Vendas.rest.dto.ItensPedidoDTO;
import curso.springboot.Vendas.rest.dto.PedidoDTO;

import java.util.List;
import java.util.Optional;


public interface PedidoService {
  Pedido salvar (PedidoDTO pedidoDTO);
  Optional<Pedido> obterPedidoCompleto(Integer id);
  void atualizaStatus(Integer id, StatusPedido status);
}
