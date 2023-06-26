package br.edu.femass.entities;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Copia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean disponivel;
    @ManyToOne(cascade = { CascadeType.ALL })
    private Livro livro;
public Copia(Livro livro) throws Exception {
        this.disponivel = true;
        this.livro = livro;
        livro.adicionarCopia(this);
    }

    @Override
    public String toString() {
        return this.getLivro().getNome() + " " + this.getId().toString();
    }
    
}
