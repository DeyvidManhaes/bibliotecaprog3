package br.edu.femass.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.application.Application;

public class CadastroUsuarioController implements Initializable{

    @FXML
    private ChoiceBox<String> CBoxTipo = new ChoiceBox<>();
    @FXML
    private TextField TxtNome;
    @FXML
    private TextField TxtTelefone;
    @FXML
    private TextField TxtEmail;
    @FXML
    private TextField TxtLogin;
    @FXML
    private TextField TxtSenha;
    @FXML
    private TextField TxtMatricula;
    @FXML
    private TextField TxtFormação;

    @FXML
    
    



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        CBoxTipo.getItems().addAll("Professor","Aluno");

     


        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'initialize'");
    }


    
}
