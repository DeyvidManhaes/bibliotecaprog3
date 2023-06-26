package br.edu.femass.dao;

    import jakarta.persistence.Query;
import javafx.scene.chart.PieChart.Data;
import br.edu.femass.entities.Emprestimo;
    import java.util.List;

public class DaoEmprestimo extends Dao<Emprestimo>{

    public DaoEmprestimo(Class<Emprestimo> entity) {
        super(entity);
    }
    public DaoEmprestimo() {
        super(Emprestimo.class);
    }
    public List<Emprestimo> findByName(Data LocaDate) {
        Query q = em.createQuery("select e from Emprestimo e where e.Data = '" + LocaDate + "'");
        return q.getResultList();
    }
}
