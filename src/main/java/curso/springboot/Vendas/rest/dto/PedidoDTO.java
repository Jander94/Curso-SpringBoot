package curso.springboot.Vendas.rest.dto;

import curso.springboot.Vendas.Validation.NotEmptyList;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {

    @NotNull(message = "Código do cliente é obrigatório")
    private Integer cliente;

    @NotNull(message = "Campo total é obrigatório")
    private BigDecimal total;

    @NotEmptyList(message = "Pedido não pode ser realizado sem itens.")
    private List<ItensPedidoDTO> itens;

}
