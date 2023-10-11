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

    @NotNull(message = "{campo.codigo-cliente.obrigatorio}")
    private Integer cliente;

    @NotNull(message = "{campo.total.obrigatorio}")
    private BigDecimal total;

    @NotEmptyList(message = "{campo.itens.obrigatorio}")
    private List<ItensPedidoDTO> itens;

}
