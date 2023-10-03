package curso.springboot.Vendas.rest.controller;

import curso.springboot.Vendas.domain.entity.ItemPedido;
import curso.springboot.Vendas.domain.entity.Pedido;
import curso.springboot.Vendas.domain.repository.PedidosRepository;
import curso.springboot.Vendas.exception.RegraNegocioException;
import curso.springboot.Vendas.rest.dto.InfoItensPedidoDTO;
import curso.springboot.Vendas.rest.dto.InfoPedidosDTO;
import curso.springboot.Vendas.rest.dto.PedidoDTO;
import curso.springboot.Vendas.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;
    private final PedidosRepository pedidosRepository;

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save(@RequestBody PedidoDTO pedidoDTO){
        Pedido pedido = pedidoService.salvar(pedidoDTO) ;
        return pedido.getId();
    }
    @GetMapping("{id}")
    public InfoPedidosDTO pedidoById (@PathVariable Integer id){
        return pedidoService.obterPedidoCompleto(id)
                .map(p -> converter(p))
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Pedido não encontrado."));
    }

    @GetMapping
    public List<Pedido> pedidos (){
        return pedidosRepository.findAll();
    }

    private InfoPedidosDTO converter(Pedido pedido){
        return InfoPedidosDTO.builder()
          .codigo(pedido.getId())
          .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
          .cpf(pedido.getCliente().getCpf())
          .nomeCliente(pedido.getCliente().getNome())
          .total(pedido.getTotal())
          .items(converter(pedido.getItens()))
          .build();
    }

    private List<InfoItensPedidoDTO> converter (List<ItemPedido> itens){
        if (CollectionUtils.isEmpty(itens)){
            return Collections.emptyList();
        }

        return itens.stream().map(
                    item -> InfoItensPedidoDTO
                        .builder()
                        .descricaoProduto(item.getProduto().getDescricao())
                        .quantidade(item.getQuantidade())
                        .precoUnitario(item.getProduto().getPreco())
                        .build()
                ).collect(Collectors.toList());
    }
}
