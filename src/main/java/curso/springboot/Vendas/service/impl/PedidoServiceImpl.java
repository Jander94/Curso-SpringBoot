package curso.springboot.Vendas.service.impl;

import curso.springboot.Vendas.domain.entity.Pedido;
import curso.springboot.Vendas.domain.repository.PedidosRepository;
import curso.springboot.Vendas.rest.dto.PedidoDTO;
import curso.springboot.Vendas.service.PedidoService;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {

    private PedidosRepository pedidosRepository;

    public PedidoServiceImpl(PedidosRepository pedidosRepository) {
        this.pedidosRepository = pedidosRepository;
    }

    @Override
    public Pedido salvar(PedidoDTO pedidoDTO) {

        return null;
    }
}
