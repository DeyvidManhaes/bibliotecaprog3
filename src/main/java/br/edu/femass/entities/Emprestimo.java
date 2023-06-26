package br.edu.femass.entities;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Emprestimo{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    private LocalDate data = LocalDate.now();
    @Temporal (TemporalType.DATE)
    private LocalDate dataPrevistaEntrega;
    @Column(nullable=true)
    @Temporal (TemporalType.DATE)
    private LocalDate dataEntrega;
    private Boolean atrasado;
    @ManyToOne(cascade = { CascadeType.ALL })
    private Copia copia;
    @ManyToOne(cascade = { CascadeType.ALL })
    private Leitor leitor;

    public Boolean verificaAtraso() {
        return this.dataPrevistaEntrega.isBefore(LocalDate.now());
    }


   
   
}
