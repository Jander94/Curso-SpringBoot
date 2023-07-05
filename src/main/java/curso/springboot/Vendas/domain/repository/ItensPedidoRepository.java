package curso.springboot.Vendas.domain.repository;

import curso.springboot.Vendas.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ItensPedidoRepository extends JpaRepository<ItemPedido, Integer> {
}
