package br.edu.femass.dao;


import jakarta.persistence.Query;
import br.edu.femass.entities.Copia;
import java.util.List;

public class DaoCopia extends Dao<Copia>{

    public DaoCopia(Class<Copia> entity) {
        super(entity);
    }
    public DaoCopia() {
        super(Copia.class);
    }
    public List<Copia> findByName(Long id ) {
        Query q = em.createQuery("select c from Copia c where c.id = '" + id + "'");
        return q.getResultList();
    }
}