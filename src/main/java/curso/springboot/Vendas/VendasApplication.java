package curso.springboot.Vendas;

import curso.springboot.Vendas.domain.entity.Cliente;
import curso.springboot.Vendas.domain.repository.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class VendasApplication {

	@Bean
	public CommandLineRunner init (@Autowired Clientes clientes){
		return args -> {
//			Salvando Clientes
			clientes.save(new Cliente("Jander"));
			clientes.save(new Cliente("Gustavo"));
			clientes.save(new Cliente("Yago"));
			List<Cliente> todosClientes = clientes.findAll();
			todosClientes.forEach(System.out::println);

//			Checa se existe
			System.out.println("Existe o nome: " + clientes.existsByNome("Jander"));
//			System.out.println("Existe o nome: " + clientes.findByNomeLike("%o%"));

//			Deletar cliente
			clientes.deleteByNome("Yago");
			System.out.println("Lista depois do delete: ");
			todosClientes = clientes.findAll();
			todosClientes.forEach(System.out::println);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}
}
