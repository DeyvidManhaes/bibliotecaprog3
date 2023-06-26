package br.edu.femass.dao;

    import jakarta.persistence.Query;
    import br.edu.femass.entities.Professor;
    import java.util.List;
 
public class DaoProfessor extends Dao<Professor>{

    public DaoProfessor(Class<Professor> entity) {
        super(entity);
    }
    public DaoProfessor() {
        super(Professor.class);
    }
    public List<Professor> findByName(String nome) {
        Query q = em.createQuery("select c from Professor c where c.nome = '" + nome + "'");
        return q.getResultList();
    }
}
