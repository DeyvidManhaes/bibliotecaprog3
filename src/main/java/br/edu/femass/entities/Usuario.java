package br.edu.femass.entities;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column( unique = true)
    private String login;
    @Column(length = 8, unique = false)
    private String senha;
    @OneToOne(cascade = { CascadeType.ALL })
    private Leitor leitor;
    

}
