package br.edu.femass.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String sobrenome;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Livro> livros= new ArrayList<>();

     public void adicionarLstLivro(Livro livro) {

        livros.add(livro);
    }
    @Override
    public String toString() {
        return this.nome + " " + this.sobrenome;
    }
}
