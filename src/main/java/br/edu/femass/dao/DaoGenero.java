package br.edu.femass.dao;
    import jakarta.persistence.Query;
    import br.edu.femass.entities.Genero;
    import java.util.List;
 
public class DaoGenero extends Dao<Genero>{

    public DaoGenero(Class<Genero> entity) {
        super(entity);
    }
    public DaoGenero() {
        super(Genero.class);
    }
    public List<Genero> findByName(String nome) {
        Query q = em.createQuery("select g from Genero g where g.nome = '" + nome + "'");
        return q.getResultList();
    }
}
