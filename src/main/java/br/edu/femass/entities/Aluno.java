package br.edu.femass.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor


@Entity
public class Aluno extends Leitor{
   
    private String matricula;
    
   public Aluno() {
        setPrazoMaximoDevolucao(5);
    }

    @Override
    public String toString() {
        return this.getNome();
    }
    public void setPrazoMaximoDevolucao(){
        this.prazoMaximoDevolucao = 5;
    }

}