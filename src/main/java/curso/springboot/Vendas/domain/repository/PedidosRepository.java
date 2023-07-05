package curso.springboot.Vendas.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;


public interface PedidosRepository extends JpaRepository<curso.springboot.Vendas.domain.entity.Pedido, Integer> {
}
