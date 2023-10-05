package curso.springboot.Vendas.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome", length = 100)
    @NotEmpty(message = "Campo nome é obrigatório.")
    private String nome;

    @Column(name = "cpf", length = 11)
    @NotEmpty(message = "Campo CPF é obrigatório.")
    @CPF(message = "CPF inválido.")
    private String cpf;

    // LAZY -> Buscar os clientes sem os pedidos
    //EAGER -> Buscar os clientes com os pedidos
    @JsonIgnore
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private List<Pedido> pedidos;



    public Cliente(String nome) {
        this.nome = nome;
    }

    public Cliente(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }


}
