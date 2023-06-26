package br.edu.femass.dao;

import jakarta.persistence.Query;
    import br.edu.femass.entities.Aluno;
    import java.util.List;

public class DaoAluno extends Dao<Aluno>{

    public DaoAluno(Class<Aluno> entity) {
        super(entity);
    }
    public DaoAluno() {
        super(Aluno.class);
    }
    public List<Aluno> findByName(String nome) {
        Query q = em.createQuery("select a from Aluno a where a.nome = '" + nome + "'");
        return q.getResultList();
    }
}


