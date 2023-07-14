package curso.springboot.Vendas.rest.controller;

import curso.springboot.Vendas.domain.entity.Produto;
import curso.springboot.Vendas.domain.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/produto")
@AllArgsConstructor
public class ProdutoController {

  private final ProdutoRepository produtoRepository;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void save ( @RequestBody Produto produto ) {
    produtoRepository.save(produto);
  }

  @GetMapping("{id}")
  public Produto produtoById (@PathVariable Integer id) {
    return produtoRepository.findById(id)
        .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado - findById"));
  }
  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete (@PathVariable Integer id) {
    produtoRepository.findById(id)
        .map(p -> {
          produtoRepository.deleteById(p.getId());
          return Void.class;
        })
        .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado - delete"));
  }


}
