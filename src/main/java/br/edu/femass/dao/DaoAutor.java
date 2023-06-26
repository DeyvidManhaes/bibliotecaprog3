package br.edu.femass.dao;

    import jakarta.persistence.Query;
    import br.edu.femass.entities.Autor;
    import java.util.List;

public class DaoAutor extends Dao<Autor>{

    public DaoAutor(Class<Autor> entity) {
        super(entity);
    }
    public DaoAutor() {
        super(Autor.class);
    }
    public List<Autor> findByName(String nome) {
        Query q = em.createQuery("select au from Autor au where au.nome = '" + nome + "'");
        return q.getResultList();
    }
}
