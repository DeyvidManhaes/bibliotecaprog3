package br.edu.femass.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.hibernate.mapping.List;

import br.edu.femass.dao.DaoAutor;
import br.edu.femass.dao.DaoCopia;
import br.edu.femass.dao.DaoLivro;
import br.edu.femass.entities.Autor;
import br.edu.femass.entities.Copia;
import br.edu.femass.entities.Livro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class LivrosController implements Initializable {

    @FXML
    private TextField TxtId;
    @FXML
    private TextField TxtNome;
    @FXML
    private ComboBox<Autor> ComboBoxAutores;
    @FXML
    private ListView<Livro> ListaLivros;
    @FXML
    private TableView<Copia> TabelaEmprestimos;
    @FXML
    private Button BotaoSalvar;
    @FXML
    private Button BotaoInserir;
    @FXML
    private Button BotaoAlterar;
    @FXML
    private Button BotaoExcluir;

    private DaoLivro daoLivro = new DaoLivro();
    private DaoAutor daoAutor = new DaoAutor();
    private DaoCopia daoCopia = new DaoCopia();
    private Livro livro;
    private boolean inserindo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        preencherLista();

    }

    @FXML
    private void Salvar_Click(ActionEvent event) {

        Autor autor = ComboBoxAutores.getSelectionModel().getSelectedItem();

        livro.setNome(TxtNome.getText());
        livro.setAutor(autor);
        autor.adicionarLstLivro(livro);

        if (inserindo) {
            daoLivro.create(livro);
        } else {
            daoLivro.update(livro);
        }

        preencherLista();
        editar(false);
    
    }

    @FXML
    private void Alterar_Click(ActionEvent event) {

        editar(true);
        inserindo = false;

    }

    @FXML
    private void Excluir_Click(ActionEvent event) {

        daoLivro.delete(livro);
        preencherLista();
    }

    @FXML
    private void Inserir_Click(ActionEvent event) {

        editar(true);
        inserindo = true;
        livro = new Livro();

        // Deixa os campos em branco
        TxtId.setText("");
        TxtNome.setText("");
        ComboBoxAutores.setValue(null);

        // Deixa o cursor nesse campo para digitar
        TxtNome.requestFocus();

        preencherLista();

    }

    @FXML
    private void ListarLeitores_KeyPressed(KeyEvent event) {

        exibirDados();

    }

    @FXML
    private void ListarLeitores_MouseClicked(MouseEvent event) {

        exibirDados();

    }

    private void exibirDados() {

        this.livro = ListaLivros.getSelectionModel().getSelectedItem();

        if (livro == null)
            return;

        TxtId.setText(livro.getId().toString());
        TxtNome.setText(livro.getNome());
        ComboBoxAutores.setValue(livro.getAutor());

    }

    private void editar(boolean habilitar) {

        ListaLivros.setDisable(habilitar); // Desabilita
        TabelaEmprestimos.setDisable(habilitar); // Desabilita
        TxtNome.setDisable(!habilitar); // Habilita
        ComboBoxAutores.setDisable(!habilitar);
        BotaoExcluir.setDisable(habilitar);
        BotaoInserir.setDisable(habilitar);
        BotaoAlterar.setDisable(habilitar);
        BotaoSalvar.setDisable(!habilitar);

    }

    private void preencherLista() {

        List<Livro> livros = daoLivro.findAll();

        ObservableList<Livro> data1 = FXCollections.observableArrayList(livros);
        ListaLivros.setItems(data1);

        List<Copia> copias = daoCopia.findAll();

        ObservableList<Copia> data2 = FXCollections.observableArrayList(copias);
        TabelaEmprestimos.setItems(data2);

        List<Autor> autores = daoAutor.findAll();

        ObservableList<Autor> data3 = FXCollections.observableArrayList(autores);
        ComboBoxAutores.setItems(data3);

    }
}
