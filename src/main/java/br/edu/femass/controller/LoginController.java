package br.edu.femass.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.femass.dao.Dao;
import br.edu.femass.entities.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController implements Initializable{

    @FXML
    private TextField TxtLogin; 

    @FXML
    private PasswordField Senha;

    @FXML
    private void Logar_button(ActionEvent event){

        String usuario = TxtLogin.getText();
        String Senha1 = Senha.getText();
      try{

        if(usuario.equals("adm") && Senha1.equals("adm12345") ){

            Parent root = FXMLLoader.load(getClass().getResource("/fxml/bibliotecario.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
    
            stage.setTitle("Bibliotecário");
            stage.setScene(scene);
            stage.show();


            


        }
        Dao<Usuario> daousuario = new Dao<>(Usuario.class);
        Usuario u = daousuario.findById(daousuario);
        
        if(usuario.equals(u.getLogin()) && Senha1.equals(u.getSenha())){
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Usuario.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
    
            stage.setTitle(u.getLeitor().getNome());
            stage.setScene(scene);
            stage.show();

        }
        
         
      }catch (Exception ex){
        ex.printStackTrace();
      }

    }

   @Override
    public void initialize(URL location, ResourceBundle rb) {
    
    }
}
