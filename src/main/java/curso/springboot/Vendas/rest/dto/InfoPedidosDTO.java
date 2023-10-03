package curso.springboot.Vendas.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InfoPedidosDTO {
  private Integer codigo;
  private String nomeCliente;
  private String cpf;
  private BigDecimal total;
  private String dataPedido;
  private List<InfoItensPedidoDTO> items;
}
