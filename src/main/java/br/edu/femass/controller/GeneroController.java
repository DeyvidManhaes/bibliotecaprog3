package br.edu.femass.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import br.edu.femass.dao.DaoGenero;
import br.edu.femass.entities.Genero;
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

public class GeneroController implements Initializable {

    @FXML
    private TextField TxtNome;
       
    @FXML
    private ListView<Genero> ListaGeneros;
    @FXML
    private Button BotaoSalvar;
    @FXML
    private Button BotaoInserir;
    @FXML
    private Button BotaoAlterar;
    @FXML
    private Button BotaoExcluir;

    private DaoGenero daoGenero = new DaoGenero();
    private Genero genero;
    private boolean inserindo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        preencherLista();

    }

    @FXML
    private void Salvar_Click(ActionEvent event) {

        genero.setNome(TxtNome.getText());
        

        if (inserindo) {
            daoGenero.create(genero);
            JOptionPane.showMessageDialog(null, "Genero"+" Id: "+genero.getId()+" salvo!");
        } else {
            daoGenero.update(genero);
            JOptionPane.showMessageDialog(null, "Genero"+" Id: "+genero.getId()+" atualizado!");
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

        daoGenero.delete(genero.getId());
        dadosEmBranco();
        preencherLista();
        JOptionPane.showMessageDialog(null, "Genero Excluido!");
    }

    @FXML
    private void Inserir_Click(ActionEvent event) {

        editar(true);
        inserindo = true;
        genero = new Genero();

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
    private void ListarGeneros_KeyPressed(KeyEvent event) {

        exibirDados();

    }

    @FXML
    private void ListarGeneros_MouseClicked(MouseEvent event) {

        exibirDados();

    }

    private void dadosEmBranco() {

        TxtNome.setText("");
               

    }

    private void exibirDados() {

        this.genero = ListaGeneros.getSelectionModel().getSelectedItem();

        if (genero == null)
            return;

        TxtNome.setText(genero.getNome());
    

    }

    private void editar(boolean habilitar) {

        ListaGeneros.setDisable(habilitar); // Desabilita
        TxtNome.setDisable(!habilitar); // Habilita
        BotaoSalvar.setDisable(!habilitar);
    }

    private void preencherLista() {

        List<Genero> generos = daoGenero.findAll();

        ObservableList<Genero> data = FXCollections.observableArrayList(generos);
        ListaGeneros.setItems(data);

    }
}

