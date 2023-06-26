package br.edu.femass.dao;

    import jakarta.persistence.Query;
    import br.edu.femass.entities.Usuario;
    import java.util.List;
 
public class DaoUsuario extends Dao<Usuario>{

    public DaoUsuario(Class<Usuario> entity) {
        super(entity);
    }
    public DaoUsuario() {
        super(Usuario.class);
    }
    public List<Usuario> findByName(String login ) {
        Query q = em.createQuery("select c from Usuario c where c.Login = '" + login + "'");
       
        return q.getResultList();
    }
}
