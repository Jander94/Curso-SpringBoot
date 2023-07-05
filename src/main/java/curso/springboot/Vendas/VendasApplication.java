package curso.springboot.Vendas;

import curso.springboot.Vendas.domain.entity.Cliente;
import curso.springboot.Vendas.domain.entity.Pedido;
import curso.springboot.Vendas.domain.repository.ClienteRepository;
import curso.springboot.Vendas.domain.repository.PedidosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class VendasApplication {

	@Bean
	public CommandLineRunner init (@ Autowired ClienteRepository clientes, @Autowired PedidosRepository pedido ){
		return args -> {
//			Salvando Clientes
			Cliente jander = new Cliente("Jander");
			clientes.save(jander);


			Pedido pedidoJander = new Pedido();
			pedidoJander.setCliente(jander);
			pedidoJander.setDataPedido( LocalDate.now() );
			pedidoJander.setTotal( BigDecimal.valueOf( 100 ) );
			pedido.save(pedidoJander);

			Cliente clienteBuscado = clientes.findClienteFetchPedidos(jander.getId());
			System.out.println(clienteBuscado);
			System.out.println(clienteBuscado.getPedidos());

		};
	}

	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}
}
