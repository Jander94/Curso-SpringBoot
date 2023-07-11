package curso.springboot.Vendas.rest.controller;

import curso.springboot.Vendas.domain.entity.Cliente;
import curso.springboot.Vendas.domain.repository.ClienteRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/clientes")
public class ClienteController {

  private final ClienteRepository clienteRepository;

  public ClienteController(ClienteRepository clienteRepository) {
    this.clienteRepository = clienteRepository;
  }

  @GetMapping("{id}")
  public Cliente helloCliente(@PathVariable("id") Integer id){
    return clienteRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity save(@RequestBody Cliente cliente){
    clienteRepository.save(cliente);
    return ResponseEntity.ok(cliente);
  }

  @DeleteMapping("{id}")
  public ResponseEntity delete(@PathVariable Integer id){
    Optional<Cliente> cliente = clienteRepository.findById(id);

    if (cliente.isPresent()){
      clienteRepository.deleteById(id);
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
  }

  @PutMapping("{id}")
  public ResponseEntity update( @PathVariable Integer id, @RequestBody Cliente clienteRecebido){

    return clienteRepository.findById(id)
        .map(clienteBuscado -> {
          clienteRecebido.setId(clienteBuscado.getId());
          clienteRepository.save(clienteRecebido);
          return ResponseEntity.noContent().build();
        }).orElseGet(() -> ResponseEntity.notFound().build()
        );
  }

  @GetMapping
  public ResponseEntity find ( Cliente filtro){
    // Example cria um filtro a partir da entidade Cliente. Os parâmetros são passados na url
    // ExampleMatcher cria o padrão da busca no banco:
    // withIgnoreCase() ignora lowerCase e upperCase
    // withStringMatcher define se o o resultado deve começar, ou conter algum caracter do filtro. entre outras opções.
    ExampleMatcher matcher = ExampleMatcher.matching()
        .withIgnoreCase()
        .withStringMatcher(
            ExampleMatcher.StringMatcher.CONTAINING);
    Example example = Example.of(filtro, matcher);

    List<Cliente> lista = clienteRepository.findAll(example);

    return ResponseEntity.ok(lista);
  }
}