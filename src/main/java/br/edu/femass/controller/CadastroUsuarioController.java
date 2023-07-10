package br.edu.femass.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import br.edu.femass.dao.DaoAluno;
import br.edu.femass.dao.DaoEmprestimo;
import br.edu.femass.dao.DaoLeitor;
import br.edu.femass.dao.DaoProfessor;
import br.edu.femass.dao.DaoTelefone;
import br.edu.femass.dao.DaoUsuario;
import br.edu.femass.entities.Aluno;
import br.edu.femass.entities.Emprestimo;
import br.edu.femass.entities.Leitor;
import br.edu.femass.entities.Professor;
import br.edu.femass.entities.Telefone;
import br.edu.femass.entities.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class CadastroUsuarioController implements Initializable{

    @FXML
    private ChoiceBox<String> CBoxTipo = new ChoiceBox<>();
    @FXML
    private TextField TxtNome;
    @FXML
    private TextField TxtId;
    @FXML
    private TextField TxtTelefone;
    @FXML
    private TextField Txtddd;
    @FXML
    private TextField TxtEmail;
    @FXML
    private TextField TxtLogin;
    @FXML
    private TextField TxtSenha;
    @FXML
    private TextField TxtEspecifica;
    @FXML
    private TextField TxtPrazo;
   
     @FXML
    private TableView<Leitor> TabelaLeitores;
    @FXML
    private TableColumn<Leitor, String> ColunaEmail;
    @FXML
    private TableColumn<Leitor, String> ColunaNome;
    @FXML
    private TableColumn<Leitor, String> ColunaTelefone;
    @FXML
    private ListView<Emprestimo> Lista_emprestimos;
    
    @FXML
    private Button BotaoExcluir;
    
    @FXML
    private Button BotaoEditar;
    @FXML
    private Button BotaoCadastrar;
    @FXML
    private Button BotaoSalvar;
    
    private DaoAluno daoAluno = new DaoAluno();
    private DaoProfessor daoProfessor = new DaoProfessor();
    private DaoEmprestimo daoEmprestimo = new DaoEmprestimo();
    private DaoUsuario daoUsuario = new DaoUsuario();
    private DaoTelefone daoTelefone = new DaoTelefone();
    private Leitor leitor;
    private Aluno aluno;
    private Usuario usuario;
    private Professor professor;
    private boolean inserindo;
    private Telefone telefone;
    Boolean bool;
    @FXML
    private Label LabelInfo;
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        CBoxTipo.getItems().addAll("Professor","Aluno");
        preencherLista();
        ColunaNome.setCellValueFactory(new PropertyValueFactory<Leitor, String>("nome"));
        ColunaEmail.setCellValueFactory(new PropertyValueFactory<Leitor, String>("email"));
        ColunaTelefone.setCellValueFactory(new PropertyValueFactory<Leitor, String>("telefone"));

    }

    @FXML
    private void Salvar_Click(ActionEvent event) {

       String indexCombo = CBoxTipo.getSelectionModel().getSelectedItem();
        

        do {
            if (indexCombo == "Aluno"){
               
               
                telefone = new Telefone();
                aluno = new Aluno();
                usuario = new Usuario();

                

                String teloriginal = TxtTelefone.getText();
                int cont = teloriginal.length();
                String dddpart = teloriginal.substring(0,3);
                String telpart = teloriginal.substring(3,cont);

                aluno.setNome(TxtNome.getText());
                aluno.setEmail(TxtEmail.getText());
                telefone.setDdd(dddpart);
                telefone.setNumero(telpart);
                aluno.addTelefone(telefone);
                aluno.setMatricula(TxtEspecifica.getText());
                aluno.setPrazoMaximoDevolucao();
                usuario.setLogin(aluno.getNome());
                usuario.setSenha(TxtSenha.getText());
                aluno.setUsuario(usuario);
                
                

                if (inserindo) {
                    daoAluno.create(aluno);
                    daoUsuario.create(usuario);
                    daoTelefone.create(telefone);

                    JOptionPane.showMessageDialog(null,  "Leitor"+" Id: "+leitor.getId()+" salvo!");
                } else {
                    
                    daoAluno.update(aluno);
                    daoUsuario.update(usuario);
                    daoTelefone.update(telefone);

                    JOptionPane.showMessageDialog(null, "Leitor salvo!");
                }

                 dadosEmBranco();
                preencherLista();
                bool = true;
               
                

            } else if (indexCombo == "Professor") {
                

               
                telefone = new Telefone();
                professor = new Professor();
                usuario = new Usuario();
                
                String teloriginal = TxtTelefone.getText();
                int cont = teloriginal.length();
                String dddpart = teloriginal.substring(0,3);
                String telpart = teloriginal.substring(3,cont);
                professor.setNome(TxtNome.getText());
                professor.setEmail(TxtEmail.getText());
                telefone.setDdd(dddpart);
                telefone.setNumero(telpart);
                professor.addTelefone(telefone);
                professor.setFormacao(TxtEspecifica.getText());
                professor.setPrazoMaximoDevolucao();
                usuario.setLogin(professor.getNome());
                usuario.setSenha(TxtSenha.getText());
                professor.setUsuario(usuario);
                

                if (inserindo) {
                    
                    daoProfessor.create(professor);
                    daoUsuario.create(usuario);
                    daoTelefone.create(telefone);

                    JOptionPane.showMessageDialog(null, "Leitor"+" Id: "+leitor.getId()+" salvo!");
                } else {
                    
                    daoProfessor.update(professor);
                    daoUsuario.update(usuario);
                    daoTelefone.update(telefone);
                    JOptionPane.showMessageDialog(null, "Leitor"+" Id: "+leitor.getId()+" atualizado!");
                        
                    

                    
                }
                dadosEmBranco();
                preencherLista();
                bool = true;
                
                
            }

            JOptionPane.showMessageDialog(null, "Selecione um tipo de leitor");

        } while (!bool);

        preencherLista();
        dadosEmBranco();
        editar(false);
        
    }

    @FXML
    private void Button_Click_Editar(ActionEvent event) {

        editar(true);
        inserindo = false;

    }

    @FXML
    private void Button_Click_Excluir(ActionEvent event) {

        if (TabelaLeitores.getSelectionModel().getSelectedItem().getClass() == Aluno.class) {
             
            aluno = (Aluno) TabelaLeitores.getSelectionModel().getSelectedItem();
            

            daoAluno.delete(aluno.getId());
            
            Usuario usuario1 = aluno.getUsuario();
            daoUsuario.delete(usuario1.getId());
            List<Telefone> telefones = aluno.getTelefones();
            for (Telefone telefone : telefones){
                daoTelefone.delete(telefone.getId());
            }

        } else if (TabelaLeitores.getSelectionModel().getSelectedItem().getClass() == Professor.class) {

            professor = (Professor) TabelaLeitores.getSelectionModel().getSelectedItem();

            daoProfessor.delete(professor.getId());
            Usuario usuario1 = professor.getUsuario();
            daoUsuario.delete(usuario1.getId());
             List<Telefone> telefones = professor.getTelefones();
            for (Telefone telefone : telefones){
                daoTelefone.delete(telefone.getId());
            }

        }

        JOptionPane.showMessageDialog(null, "Leitor deletado!");

        dadosEmBranco();
        preencherLista();
    }

    @FXML
    private void Button_Click_Cadastrar(ActionEvent event) {

        editar(true);
        inserindo = true;

        // Deixa os campos em branco
        
        dadosEmBranco();

        // Deixa o cursor nesse campo para digitar
        TxtNome.requestFocus();

        preencherLista();

    }

    @FXML
    private void ListarLeitores_KeyPressed(KeyEvent event) {

        exibirDados();

        preencherLista();

    }

    @FXML
    private void ListarLeitores_MouseClicked(MouseEvent event) {

        exibirDados();

        preencherLista();

    }

    @FXML
    private void CBoxTipo_Clicked(MouseEvent event) {

        String indexCombo2 = CBoxTipo.getSelectionModel().getSelectedItem();

        String textoLabel;

        if (indexCombo2 == "Aluno"){
            textoLabel = "Matrícula:";}
        else{
            textoLabel = "Formação:";}

            LabelInfo.setText(textoLabel);

    }

    private void exibirDados() {

        this.leitor = TabelaLeitores.getSelectionModel().getSelectedItem();
        

        if (leitor.getClass() == Aluno.class)
            
            aluno = (Aluno) leitor;
            
           
            

        else
            professor = (Professor) leitor;

        if (leitor == null)
            return;

        TxtId.setText(leitor.getId().toString());
        TxtNome.setText(leitor.getNome());
        TxtEmail.setText(leitor.getEmail());
        TxtLogin.setText(usuario.getLogin());
        TxtSenha.setText(usuario.getSenha());

        TxtTelefone.setText(telefone.getDdd()+telefone.getNumero());
        TxtPrazo.setText(leitor.getPrazoMaximoDevolucao().toString());
        CBoxTipo.setValue(leitor.getClass().getSimpleName());
        LabelInfo.setText(leitor.getClass() == Aluno.class ? "Matrícula" : "Disciplina");
        TxtEspecifica.setText(leitor.getClass() == Aluno.class ? aluno.getMatricula() : professor.getFormacao());

    }

    private void dadosEmBranco() {

        TxtId.setText("");
        TxtNome.setText("");
        TxtEmail.setText("");
        TxtTelefone.setText("");
        TxtEspecifica.setText("");
        TxtPrazo.setText("");
        CBoxTipo.setValue("Selecione um tipo de leitor");
        ;
        LabelInfo.setText("");
        TxtEspecifica.setText("");
        TxtLogin.setText("");
        TxtSenha.setText("");

    }

    private void editar(boolean habilitar) {

        TabelaLeitores.setDisable(habilitar); // Desabilita
        Lista_emprestimos.setDisable(habilitar); // Desabilita
        TxtNome.setDisable(!habilitar); // Habilita
        TxtEmail.setDisable(!habilitar);
        TxtTelefone.setDisable(!habilitar);
        BotaoExcluir.setDisable(habilitar);
        BotaoCadastrar.setDisable(habilitar);
        BotaoEditar.setDisable(habilitar);
        BotaoSalvar.setDisable(!habilitar);
        CBoxTipo.setDisable(!habilitar);
        TxtEspecifica.setDisable(!habilitar);
        TxtSenha.setDisable(!habilitar);

    }

    private void preencherLista() {

        List<String> tiposDeLeitor = new ArrayList<>();
        tiposDeLeitor.add("Aluno");
        tiposDeLeitor.add("Professor");
        ObservableList<String> itensCombo = FXCollections.observableArrayList(tiposDeLeitor);
        CBoxTipo.setItems(itensCombo);

        List<Aluno> alunos = daoAluno.findAll();
        List<Professor> professores = daoProfessor.findAll();
        List<Leitor> leitores = new ArrayList<>();

        leitores.addAll(alunos);
        leitores.addAll(professores);

        ObservableList<Leitor> data1 = FXCollections.observableArrayList(leitores);
        TabelaLeitores.setItems(data1);

        List<Emprestimo> emprestimos = daoEmprestimo.findAll();

        List<Emprestimo> emprestimosDoLeitor = new ArrayList<>();

        for (Emprestimo emprestimo : emprestimos) {

            if (emprestimo.getLeitor() == leitor) {

                emprestimosDoLeitor.add(emprestimo);

            }
        }

        ObservableList<Emprestimo> data2 = FXCollections.observableArrayList(emprestimos);
        Lista_emprestimos.setItems(data2);

    }
}



    
    
    



    