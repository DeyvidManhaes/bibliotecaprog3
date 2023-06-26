package br.edu.femass.dao;

    import jakarta.persistence.Query;
    import br.edu.femass.entities.Telefone;
    import java.util.List;
 
public class DaoTelefone extends Dao<Telefone>{

    public DaoTelefone(Class<Telefone> entity) {
        super(entity);
    }
    public DaoTelefone() {
        super(Telefone.class);
    }
    public List<Telefone> findByName(String nome) {
        Query q = em.createQuery("select c from Autor c where c.nome = '" + nome + "'");
        return q.getResultList();
    }
}
