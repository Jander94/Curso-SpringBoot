package curso.springboot.Vendas.domain.entity;

import curso.springboot.Vendas.domain.enums.StatusPedido;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column(name = "data_pedido")
    private LocalDate dataPedido;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusPedido status;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens;

    @Column(name = "total", precision = 20, scale = 2)
    private BigDecimal total;

    @Override
    public String toString() {
        return "Pedido{" +
            "id=" + id +
            ", dataPedido=" + dataPedido +
            ", total=" + total +
            '}';
    }
}
