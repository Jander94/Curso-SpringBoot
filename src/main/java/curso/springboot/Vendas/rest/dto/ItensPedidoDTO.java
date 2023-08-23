package curso.springboot.Vendas.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItensPedidoDTO {

  private Integer produto;
  private Integer quantidade;


}
