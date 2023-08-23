package curso.springboot.Vendas.rest.controller;

import curso.springboot.Vendas.domain.entity.Pedido;
import curso.springboot.Vendas.rest.dto.PedidoDTO;
import curso.springboot.Vendas.service.PedidoService;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("api/pedidos")
public class PedidoController {

    private PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save(@RequestBody PedidoDTO pedidoDTO){
        Pedido pedido = pedidoService.salvar(pedidoDTO) ;
        return pedido.getId();
    }
}
