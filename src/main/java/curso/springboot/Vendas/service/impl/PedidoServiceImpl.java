package curso.springboot.Vendas.service.impl;

import curso.springboot.Vendas.domain.entity.Cliente;
import curso.springboot.Vendas.domain.entity.ItemPedido;
import curso.springboot.Vendas.domain.entity.Pedido;
import curso.springboot.Vendas.domain.entity.Produto;
import curso.springboot.Vendas.domain.enums.StatusPedido;
import curso.springboot.Vendas.domain.repository.ClienteRepository;
import curso.springboot.Vendas.domain.repository.ItensPedidoRepository;
import curso.springboot.Vendas.domain.repository.PedidosRepository;
import curso.springboot.Vendas.domain.repository.ProdutoRepository;
import curso.springboot.Vendas.exception.PedidoNaoEncontradoException;
import curso.springboot.Vendas.exception.RegraNegocioException;
import curso.springboot.Vendas.rest.dto.ItensPedidoDTO;
import curso.springboot.Vendas.rest.dto.PedidoDTO;
import curso.springboot.Vendas.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidosRepository pedidosRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final ItensPedidoRepository itensPedidoRepository;

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO pedidoDTO) {
        Cliente cliente = clienteRepository.findById(pedidoDTO.getCliente())
                .orElseThrow(() -> new RegraNegocioException(("Código de cliente inválido")));

        Pedido pedido = new Pedido();
        pedido.setTotal(pedidoDTO.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.REALIZADO);
        List<ItemPedido> itensPedidos = converterItens(pedido, pedidoDTO.getItens());
        pedidosRepository.save(pedido);
        itensPedidoRepository.saveAll(itensPedidos);
        pedido.setItens(itensPedidos);
        return pedido;
    }

  @Override
  public Optional<Pedido> obterPedidoCompleto(Integer id) {
    return pedidosRepository.findByIdFetchItens(id);
  }

    @Override
    @Transactional
    public void atualizaStatus(Integer id, StatusPedido status) {
        pedidosRepository.findById(id)
                .map(p -> {
                    p.setStatus(status);
                    return pedidosRepository.save(p);
                })
                .orElseThrow(PedidoNaoEncontradoException::new);
    }

    public List<ItemPedido> converterItens (Pedido pedido,List<ItensPedidoDTO> itens) {
        if (itens.isEmpty()) {
            throw new RegraNegocioException("Não é possível realizar um pedido sem itens.");
        }

        return itens
                .stream()
                .map(dto -> {
                    Produto produto = produtoRepository
                            .findById(dto.getProduto())
                            .orElseThrow(() -> new RegraNegocioException("Código de produto inválido: " + dto.getProduto()));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());
    }
}
