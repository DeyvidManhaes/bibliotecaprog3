package br.edu.femass.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.femass.dao.DaoAluno;
import br.edu.femass.dao.DaoEmprestimo;
import br.edu.femass.dao.DaoLivro;
import br.edu.femass.dao.DaoProfessor;
import br.edu.femass.entities.Aluno;
import br.edu.femass.entities.Copia;
import br.edu.femass.entities.Emprestimo;
import br.edu.femass.entities.Leitor;
import br.edu.femass.entities.Livro;
import br.edu.femass.entities.Professor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class EmprestimoController implements Initializable {

    @FXML
    private Button BotaoRealizarEmprestimo;
    @FXML
    private TableColumn<Emprestimo, LocalDate> ColunaDataDevolucao;
    @FXML
    private TableColumn<Emprestimo, LocalDate> ColunaDataEmprestimo;
    @FXML
    private TableColumn<Emprestimo, Long> ColunaID;
    @FXML
    private TableColumn<Leitor, String> ColunaLeitor;
    @FXML
    private TableColumn<Livro, String> ColunaLivro;
    @FXML
    private TableColumn<Emprestimo, LocalDate> ColunaDataPrevisao;
    @FXML
    private TableColumn<Copia, Boolean> ColunaAtrasado;
    @FXML
    private ListView<Leitor> ListaLeitores;
    @FXML
    private ListView<Livro> ListaLivros;
    @FXML
    private Pane PaneInfoSelecionada;
    @FXML
    private TextField TxtLeitor;
    @FXML
    private TextField TxtLivro;
    @FXML
    private Button BotaoDevolver;
    @FXML
    private TableView<Emprestimo> TabelaEmprestimos;

    private Leitor leitor;
    private Livro livro;
    private Emprestimo emprestimo;
    private Copia copia;
    private DaoAluno daoAluno = new DaoAluno();
    private DaoProfessor daoProfessor = new DaoProfessor();
    private DaoEmprestimo daoEmprestimo = new DaoEmprestimo();
    private DaoLivro daoLivro = new DaoLivro();
    private Boolean mostrarInfoSelecionada = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        preencherLista();

        List<Emprestimo> emprestimos = daoEmprestimo.findAll();

        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.verificaAtraso()) {
                emprestimo.setAtrasado(true);
            } else {
                emprestimo.setAtrasado(false);
            }
        }

        ColunaDataDevolucao.setCellValueFactory(new PropertyValueFactory<Emprestimo, LocalDate>("dataDevolucao"));
        ColunaDataEmprestimo.setCellValueFactory(new PropertyValueFactory<Emprestimo, LocalDate>("dataEmprestimo"));
        ColunaDataPrevisao
                .setCellValueFactory(new PropertyValueFactory<Emprestimo, LocalDate>("dataPrevistaDevolucao"));
        ColunaID.setCellValueFactory(new PropertyValueFactory<Emprestimo, Long>("codigo"));
        ColunaLeitor.setCellValueFactory(new PropertyValueFactory<Leitor, String>("leitor"));
        ColunaLivro.setCellValueFactory(new PropertyValueFactory<Livro, String>("copia"));
        ColunaAtrasado.setCellValueFactory(new PropertyValueFactory<Copia, Boolean>("atrasado"));
    }

    @FXML
    private void ListaLivros_MouseClicked(MouseEvent event) {

        livro = ListaLivros.getSelectionModel().getSelectedItem();

        exibirInfoSelecionada();

    }

    @FXML
    private void ListaLeitores_MouseClicked(MouseEvent event) {

        leitor = ListaLeitores.getSelectionModel().getSelectedItem();

        exibirInfoSelecionada();
    }

    @FXML
    private void RealizarEmprestimo_Click(ActionEvent event) {

        livro = ListaLivros.getSelectionModel().getSelectedItem();
        leitor = ListaLeitores.getSelectionModel().getSelectedItem();
        copia = livro.retornaCopiaDisponivel();

        copia.setDisponivel(false);
        emprestimo = new Emprestimo();


        emprestimo.setCopia(copia);
        emprestimo.setLeitor(leitor);
        

        daoEmprestimo.create(emprestimo);
        preencherLista();

    }

    @FXML
    void BotaoDevolver_Click(ActionEvent event) {

        emprestimo = TabelaEmprestimos.getSelectionModel().getSelectedItem();

        emprestimo.setDataEntrega(LocalDate.now());

        daoEmprestimo.update(emprestimo);
        preencherLista();

    }

    @FXML
    void TabelaEmprestimos_KeyPressed(KeyEvent event) {

        BotaoDevolver.setDisable(false);

    }

    @FXML
    void TabelaEmprestimos_MouseClicked(MouseEvent event) {

        BotaoDevolver.setDisable(false);

    }

    private void exibirInfoSelecionada() {

        PaneInfoSelecionada.setVisible(true);

        TxtLeitor.setText(leitor == null ? "" : leitor.getNome());
        TxtLivro.setText(livro == null ? "" : livro.getNome());
    }

    private void preencherLista() {

        List<Aluno> alunos = daoAluno.findAll();
        List<Professor> professores = daoProfessor.findAll();
        List<Leitor> leitores = new ArrayList<>();

        leitores.addAll(alunos);
        leitores.addAll(professores);

        ObservableList<Leitor> data1 = FXCollections.observableArrayList(leitores);
        ListaLeitores.setItems(data1);

        List<Livro> livros = daoLivro.findAll();

        List<Livro> livrosDisponiveis = new ArrayList<>();

        for (Livro livro : livros) {
            if (livro.getCopias().size() > 0) {
                List<Copia> copias = livro.getCopias();
                for (Copia copia : copias) {
                    if (copia.isDisponivel() == true) {
                        livrosDisponiveis.add(livro);
                        break;
                    }
                }
            }
        }

        ObservableList<Livro> data2 = FXCollections.observableArrayList(livrosDisponiveis);
        ListaLivros.setItems(data2);

        List<Emprestimo> emprestimos = daoEmprestimo.findAll();

        List<Emprestimo> emprestimosEmAberto = new ArrayList<>();

        for (Emprestimo emprestimo : emprestimos) {

            if (emprestimo.getDataPrevistaEntrega() == null) {

                emprestimosEmAberto.add(emprestimo);

            }
        }

        ObservableList<Emprestimo> data3 = FXCollections.observableArrayList(emprestimosEmAberto);
        TabelaEmprestimos.setItems(data3);
    }
}
