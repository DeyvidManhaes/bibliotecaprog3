package br.edu.femass.dao;

    import jakarta.persistence.Query;
    import br.edu.femass.entities.Leitor;
    import java.util.List;
 
public class DaoLeitor extends Dao<Leitor>{

    public DaoLeitor(Class<Leitor> entity) {
        super(entity);
    }
    public DaoLeitor() {
        super(Leitor.class);
    }
    public List<Leitor> findByName(String nome) {
        Query q = em.createQuery("select c from Leitor c where c.nome = '" + nome + "'");
        return q.getResultList();
    }
}