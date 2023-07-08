package br.edu.femass.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import br.edu.femass.dao.DaoAutor;
import br.edu.femass.entities.Autor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class AutorController implements Initializable {

    @FXML
    private TextField TxtNome;
    @FXML
    private TextField TxtSobrenome;
    
    @FXML
    private ListView<Autor> ListaAutores;
    @FXML
    private Button BotaoSalvar;
    @FXML
    private Button BotaoInserir;
    @FXML
    private Button BotaoAlterar;
    @FXML
    private Button BotaoExcluir;

    private DaoAutor daoAutor = new DaoAutor();
    private Autor autor;
    private boolean inserindo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        preencherLista();

    }

    @FXML
    private void Salvar_Click(ActionEvent event) {

        autor.setNome(TxtNome.getText());
        autor.setSobrenome(TxtSobrenome.getText());
        

        if (inserindo) {
            daoAutor.create(autor);
            JOptionPane.showMessageDialog(null, "Autor"+" Id: "+autor.getId()+" salvo!");
        } else {
            daoAutor.update(autor);
            JOptionPane.showMessageDialog(null, "Autor"+" Id: "+autor.getId()+" atualizado!");
        }

        preencherLista();
        dadosEmBranco();
        editar(false);
    }        

    @FXML
    private void Alterar_Click(ActionEvent event) {

        editar(true);
        inserindo = false;

    }

    @FXML
    private void Excluir_Click(ActionEvent event) {

        daoAutor.delete(autor.getId());
        dadosEmBranco();
        preencherLista();
        JOptionPane.showMessageDialog(null, "Autor Excluido!");
    }

    @FXML
    private void Inserir_Click(ActionEvent event) {

        editar(true);
        inserindo = true;
        autor = new Autor();

        // Deixa os campos em branco
        // CampoNome.setText("");
        // CampoSobrenome.setText("");
        // CampoNacionalidade.setText("");

        // Deixa o cursor nesse campo para digitar
        TxtNome.requestFocus();

        dadosEmBranco();
        preencherLista();

    }

    @FXML
    private void ListarAutores_KeyPressed(KeyEvent event) {

        exibirDados();

    }

    @FXML
    private void ListarAutores_MouseClicked(MouseEvent event) {

        exibirDados();

    }

    private void dadosEmBranco() {

        TxtNome.setText("");
        TxtSobrenome.setText("");
        

    }

    private void exibirDados() {

        this.autor = ListaAutores.getSelectionModel().getSelectedItem();

        if (autor == null)
            return;

        TxtNome.setText(autor.getNome());
        TxtSobrenome.setText(autor.getSobrenome());
    

    }

    private void editar(boolean habilitar) {

        ListaAutores.setDisable(habilitar); // Desabilita
        TxtNome.setDisable(!habilitar); // Habilita
        TxtSobrenome.setDisable(!habilitar);
        BotaoSalvar.setDisable(!habilitar);
    }

    private void preencherLista() {

        List<Autor> autores = daoAutor.findAll();   
        ObservableList<Autor> data = FXCollections.observableArrayList(autores);
        ListaAutores.setItems(data);

    }
}
