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
			clientes.salvar(new Cliente("Jander"));
			clientes.salvar(new Cliente("Gustavo"));
			clientes.salvar(new Cliente("Yago"));
			List<Cliente> todosClientes = clientes.obterTodos();
			todosClientes.forEach(System.out::println);

//		Atualizando clientes
			todosClientes.forEach(c -> {
				c.setNome(c.getNome() + " atualizado.");
				clientes.atualizar(c);
			});
			todosClientes = clientes.obterTodos();
			todosClientes.forEach(System.out::println);

//			Buscando clientes
//			System.out.println("Busca: " + clientes.buscarPorNome("gu"));

//			Deletar clientes
			clientes.obterTodos().forEach(cliente -> {
				clientes.deletar(cliente.getId());
			});
			System.out.println("Deletessss...");
			todosClientes = clientes.obterTodos();
			todosClientes.forEach(System.out::println);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(VendasApplication.class, args);
	}
}
