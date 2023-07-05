package curso.springboot.Vendas.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ProdutoRepository extends JpaRepository <curso.springboot.Vendas.domain.entity.Produto, Integer> {
}
