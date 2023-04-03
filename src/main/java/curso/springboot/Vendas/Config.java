package curso.springboot.Vendas;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean(name = "aplicationName")
    public String aplicationName(){
        return "Sistema de Vendas";
    }
}
