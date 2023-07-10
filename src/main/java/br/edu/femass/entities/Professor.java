package br.edu.femass.entities;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor

@Entity
public class Professor extends Leitor{
   
   private String formacao;

    public Professor() {
        setPrazoMaximoDevolucao(30);
    }
   
   public void setPrazoMaximoDevolucao(){
    this.prazoMaximoDevolucao = 30;
   }
   @Override
   public String toString() {
       return this.getNome();
   }

   
    }

