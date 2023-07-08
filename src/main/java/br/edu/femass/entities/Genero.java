package br.edu.femass.entities;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Genero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Livro> livros;

     public void adicionarLstLivro(Livro livro) {

        livros.add(livro);
    }
    @Override
    public String toString() {
        return this.nome;
    }

    
}
