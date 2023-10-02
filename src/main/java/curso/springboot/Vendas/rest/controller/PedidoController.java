package curso.springboot.Vendas.rest.controller;

import curso.springboot.Vendas.domain.entity.Pedido;
import curso.springboot.Vendas.domain.repository.PedidosRepository;
import curso.springboot.Vendas.rest.dto.PedidoDTO;
import curso.springboot.Vendas.service.PedidoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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
    public Pedido pedidoById (@PathVariable Integer id){
        return pedidosRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Pedido [" + id + "] não encontrado."));
    }

    @GetMapping
    public List<Pedido> pedidos (){
        return pedidosRepository.findAll();
    }
}
