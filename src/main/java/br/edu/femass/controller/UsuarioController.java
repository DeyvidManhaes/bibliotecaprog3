package br.edu.femass.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.femass.dao.DaoGenero;
import br.edu.femass.dao.DaoLivro;
import br.edu.femass.entities.Genero;
import br.edu.femass.entities.Livro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class UsuarioController implements Initializable {

    @FXML
    private TextField TxtAutor;

    @FXML
    private TextField TxtCopias;
    @FXML
    private ListView<Livro> listalivros;

    @FXML
    private ComboBox<Genero> CboGenero;
    private DaoLivro daolivro = new DaoLivro();
    private DaoGenero daogenero = new DaoGenero();

    @FXML 
    private void listalivros_keyPressed(KeyEvent event) {
        exibirDados();
    }

    @FXML 
    private void listalivros_mouseClicked(MouseEvent event) {
        exibirDados();
    }

    
    private void exibirDados() {
        Livro livro  = listalivros.getSelectionModel().getSelectedItem();
        if (livro==null) return;


        TxtAutor.setText(livro.getAutor().toString());
        
        CboGenero.getSelectionModel().select(livro.getGenero());
    }

    


    

    public void exibirlivros() {
        try {
        ObservableList<Livro> data = FXCollections.observableArrayList(
            
        );
        listalivros.setItems(data);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
    

 @Override
public void initialize(URL location, ResourceBundle resource){
    exibirlivros();
    
   }

}



