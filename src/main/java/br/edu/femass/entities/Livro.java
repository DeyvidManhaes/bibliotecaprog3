package br.edu.femass.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import br.edu.femass.dao.DaoCopia;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Livro{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer ano;
    private String edicao;
    @ManyToOne(cascade = { CascadeType.ALL })
    private Autor autor;
    @OneToMany(cascade = { CascadeType.DETACH }, fetch = FetchType.EAGER)
    private List<Copia> copias = new ArrayList<>();
    @ManyToOne (cascade = { CascadeType.ALL})
    private Genero genero;
    
    public void adicionarCopia(Copia copia) {
        this.copias.add(copia);
    }

    public Copia retornaCopiaDisponivel() {

        List<Copia> copiasDisponiveis = new ArrayList<>();

        DaoCopia daoCopia = new DaoCopia();

        copias = daoCopia.findAll();

        for (Copia copia : copias) {

            if (copia.isDisponivel() == true) {

                copiasDisponiveis.add(copia);

            }
        }

        int indexUltimo = copiasDisponiveis.size() - 1;

        return copiasDisponiveis.get(indexUltimo);
    }

    @Override
    public String toString() {
        return nome.toString();
    }

   
}