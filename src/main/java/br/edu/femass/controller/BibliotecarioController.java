package br.edu.femass.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BibliotecarioController implements Initializable{

 @FXML
    private void ClickButton_Usu(ActionEvent event){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Cadastrousuario.fxml"));

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setTitle("Cadastro Usuario");
            stage.setScene(scene);
            stage.show();
        } catch(Exception ex){
            ex.printStackTrace();
        }

    }

    @FXML
    private void ClickButton_Livros(ActionEvent event){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Livro.fxml"));

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setTitle("Controle Livros");
            stage.setScene(scene);
            stage.show();
        } catch(Exception ex){
            ex.printStackTrace();
        }

         }
         @FXML
    private void ClickButton_Bibl(ActionEvent event){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Emprestimo.fxml"));

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setTitle("Emprestimos e Devoluções");
            stage.setScene(scene);
            stage.show();
        } catch(Exception ex){
            ex.printStackTrace();
        }

    }
       @FXML
    private void ClickButton_Genero(ActionEvent event){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Genero.fxml"));

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setTitle("Cadastro Genero");
            stage.setScene(scene);
            stage.show();
        } catch(Exception ex){
            ex.printStackTrace();
        }

    }
     @FXML
    private void ClickButton_Autores(ActionEvent event){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Autor.fxml"));

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setTitle("Cadastro Autores");
            stage.setScene(scene);
            stage.show();
        } catch(Exception ex){
            ex.printStackTrace();
        }

    }
    @FXML
    private void ClickButton_Copias(ActionEvent event){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Copia.fxml"));

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setTitle("Cadastro Cópias");
            stage.setScene(scene);
            stage.show();
        } catch(Exception ex){
            ex.printStackTrace();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    

    

}
