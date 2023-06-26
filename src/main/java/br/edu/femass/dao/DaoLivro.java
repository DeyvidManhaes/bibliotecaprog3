package br.edu.femass.dao;

    import jakarta.persistence.Query;
    import br.edu.femass.entities.Livro;
    import java.util.List;

public class DaoLivro extends Dao<Livro>{

    public DaoLivro(Class<Livro> entity) {
        super(entity);
    }
    public DaoLivro() {
        super(Livro.class);
    }
    public List<Livro> findByName(String nome) {
        Query q = em.createQuery("select c from Livro c where c.nome = '" + nome + "'");
        return q.getResultList();
    }
}
