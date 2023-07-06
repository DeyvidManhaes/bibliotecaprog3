package br.edu.femass.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.Action;
import javax.swing.JOptionPane;

import br.edu.femass.dao.DaoAluno;
import br.edu.femass.dao.DaoEmprestimo;
import br.edu.femass.dao.DaoLeitor;
import br.edu.femass.dao.DaoProfessor;
import br.edu.femass.dao.DaoUsuario;
import br.edu.femass.entities.Aluno;
import br.edu.femass.entities.Autor;
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
import javafx.application.Application;
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
    private Leitor leitor;
    private Aluno aluno;
    private Usuario usuario;
    private Professor professor;
    private boolean inserindo;
    private Telefone telefone;
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

        CBoxTipo.getSelectionModel().getSelectedIndex();
        Boolean bool;

        do {
            if (CBoxTipo.equals( "Aluno")) {

                telefone = new Telefone();
                aluno = new Aluno();
                usuario = new Usuario();

                aluno.setNome(TxtNome.getText());
                aluno.setEmail(TxtEmail.getText());
                telefone.setDdd(Txtddd.getText());
                telefone.setNumero(TxtTelefone.getText());
                aluno.addTelefone(telefone);
                aluno.setMatricula(TxtEspecifica.getText());
                aluno.setPrazoMaximoDevolucao();
                aluno.setUsuario(usuario);
                usuario.setLogin(aluno.getNome());
                usuario.setSenha(TxtSenha.getText());
                

                if (inserindo) {
                    daoAluno.create(aluno);
                    daoUsuario.create(usuario);

                    JOptionPane.showMessageDialog(null,  "Leitor"+" Id: "+leitor.getId()+" salvo!");
                } else {
                    daoAluno.update(aluno);
                    daoUsuario.update(usuario);

                    JOptionPane.showMessageDialog(null, "Leitor salvo!");
                }

                bool = true;
                break;

            } else if (CBoxTipo.equals( "Professor")) {

                telefone = new Telefone();
                professor = new Professor();
                usuario = new Usuario();

                professor.setNome(TxtNome.getText());
                professor.setEmail(TxtEmail.getText());
                telefone.setDdd(Txtddd.getText());
                telefone.setNumero(TxtTelefone.getText());
                professor.addTelefone(telefone);
                professor.setFormacao(TxtEspecifica.getText());
                professor.setPrazoMaximoDevolucao();
                professor.setUsuario(usuario);
                usuario.setLogin(professor.getNome());
                usuario.setSenha(TxtSenha.getText());

                if (inserindo) {
                    daoProfessor.create(professor);
                    daoUsuario.create(usuario);

                    JOptionPane.showMessageDialog(null, "Leitor"+" Id: "+leitor.getId()+" salvo!");
                } else {
                    daoProfessor.update(professor);
                    daoUsuario.update(usuario);
                        
                    

                    JOptionPane.showMessageDialog(null, "Leitor salvo!");
                }

                bool = true;
                break;
            }

            JOptionPane.showMessageDialog(null, "Selecione um tipo de leitor");

        } while (bool = false);

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
            

            daoAluno.delete(aluno);
            daoUsuario.delete(usuario.getLogin().equals(aluno.getNome()));

        } else if (TabelaLeitores.getSelectionModel().getSelectedItem().getClass() == Professor.class) {

            professor = (Professor) TabelaLeitores.getSelectionModel().getSelectedItem();

            daoProfessor.delete(professor);
            daoUsuario.delete(usuario.getLogin().equals(professor.getNome()));

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
    private void CBoxTipo_Clicked(ActionEvent event) {

        int indexCombo = CBoxTipo.getSelectionModel().getSelectedIndex();

        String textoLabel;

        if (indexCombo == 0)
            textoLabel = "Matrícula:";
        else
            textoLabel = "Formação:";

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

        TxtTelefone.setText(telefone.getNumero());
        Txtddd.setText(telefone.getDdd());
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
        Txtddd.setText("");
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
        Txtddd.setDisable(!habilitar);
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



    
    
    



    