package br.edu.femass.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class CopiaController implements Initializable {

    @FXML
    private TextField TxtCodigo;
   
    @FXML
    private TextField TxtAutor;
   
    @FXML
    private ComboBox<Livro> ComboBoxLivros;
    @FXML
    private TableView<Copia> TabelaCopia;
    @FXML
    private Button BotaoAdicionar;
    @FXML
    private Button Deletar;
    @FXML
    private TableColumn<Autor, String> ColunaAutor;
    @FXML
    private TableColumn<Copia, Integer> ColunaCodigo;
    @FXML
    private TableColumn<Livro, String> ColunaTitulo;

    private DaoLivro daoLivro = new DaoLivro();
    private DaoCopia daoCopia = new DaoCopia();
    private Copia copia;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        preencherLista();

        ColunaCodigo.setCellValueFactory(new PropertyValueFactory<Copia, Integer>("codigo"));
        ColunaTitulo.setCellValueFactory(new PropertyValueFactory<Livro, String>("livro"));
        

    }

    @FXML
    private void Adicionar_Click(ActionEvent event) throws Exception {

        Copia copia = new Copia(ComboBoxLivros.getSelectionModel().getSelectedItem());

        daoCopia.create(copia);
        preencherLista();
    }

    @FXML
    private void ListarCopia_KeyPressed(KeyEvent event) {

        exibirDados();

    }

    @FXML
    private void ListarCopia_MouseClicked(MouseEvent event) {

        exibirDados();

    }

    @FXML
    void Deletar_Click(ActionEvent event) {

        /*
         * if (exemplar.getDisponivel() == false) {
         * 
         * JOptionPane.showMessageDialog(null,
         * "Esse exemplar está atrelado à um empréstimo.");
         * }else{
         * 
         * daoExemplar.apagar(exemplar);
         * preencherLista();
         * }
         */

        daoCopia.delete(copia);
        preencherLista();
    }

    private void exibirDados() {

        this.copia = TabelaCopia.getSelectionModel().getSelectedItem();

        if (copia == null)
            return;

        TxtCodigo.setText(copia.getId().toString());

        TxtAutor.setText(copia.getLivro().getAutor().toString());
        

    }

    private void preencherLista() {

        List<Copia> copias = daoCopia.findAll();

        ObservableList<Copia> data1 = FXCollections.observableArrayList(copias);
        TabelaCopia.setItems(data1);

        List<Livro> livros = daoLivro.findAll();

        ObservableList<Livro> data2 = FXCollections.observableArrayList(livros);
        ComboBoxLivros.setItems(data2);

    }
}
