package curso.springboot.Vendas.rest.controller;

import curso.springboot.Vendas.domain.entity.Cliente;
import curso.springboot.Vendas.domain.repository.ClienteRepository;
import curso.springboot.Vendas.exception.RegraNegocioException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/clientes")
public class ClienteController {

  private final ClienteRepository clienteRepository;

  public ClienteController(ClienteRepository clienteRepository) {
    this.clienteRepository = clienteRepository;
  }

  @GetMapping("{id}")
  public Cliente ClienteById(@PathVariable("id") Integer id){
    return clienteRepository.findById(id)
        .orElseThrow(() -> new RegraNegocioException("Cliente não encontrado - findById"));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Cliente save(@RequestBody Cliente cliente){
    return clienteRepository.save(cliente);
  }

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Integer id){
    clienteRepository.findById(id)
        .map( cliente -> {
          clienteRepository.delete(cliente);
              return Void.class;
        })
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encntrado - delete"));
  }

  @PutMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void update( @PathVariable Integer id, @RequestBody Cliente clienteRecebido){
    clienteRepository.findById(id)
        .map(clienteBuscado -> {
          clienteRecebido.setId(clienteBuscado.getId());
          clienteRepository.save(clienteRecebido);
          return clienteRecebido;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado - update")
        );
  }

  @GetMapping
  public List<Cliente> find ( Cliente filtro){
    // Example cria um filtro a partir da entidade Cliente. Os parâmetros são passados na url
    // ExampleMatcher cria o padrão da busca no banco:
    // withIgnoreCase() ignora lowerCase e upperCase
    // withStringMatcher define se o o resultado deve começar, ou conter algum caracter do filtro. entre outras opções.
    ExampleMatcher matcher = ExampleMatcher.matching()
        .withIgnoreCase()
        .withStringMatcher(
            ExampleMatcher.StringMatcher.CONTAINING);
    Example<Cliente> example = Example.of(filtro, matcher);

    return clienteRepository.findAll(example);


  }
}