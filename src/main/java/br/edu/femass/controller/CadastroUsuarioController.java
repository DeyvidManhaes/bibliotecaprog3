package br.edu.femass.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.Action;
import javax.swing.JOptionPane;

import br.edu.femass.dao.DaoAluno;
import br.edu.femass.dao.DaoEmprestimo;
import br.edu.femass.dao.DaoLeitor;
import br.edu.femass.dao.DaoProfessor;
import br.edu.femass.entities.Aluno;
import br.edu.femass.entities.Autor;
import br.edu.femass.entities.Emprestimo;
import br.edu.femass.entities.Leitor;
import br.edu.femass.entities.Professor;
import br.edu.femass.entities.Telefone;
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
    private Leitor leitor;
    private Aluno aluno;
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

                aluno.setNome(TxtNome.getText());
                aluno.setEmail(TxtEmail.getText());
                telefone.setDdd(Txtddd.getText());
                telefone.setNumero(TxtTelefone.getText());
                aluno.addTelefone(telefone);
                aluno.setMatricula(TxtEspecifica.getText());
                

                if (inserindo) {
                    daoAluno.create(aluno);

                    JOptionPane.showMessageDialog(null,  "Leitor"+" Id: "+leitor.getId()+" salvo!");
                } else {
                    daoAluno.update(aluno);

                    JOptionPane.showMessageDialog(null, "Leitor salvo!");
                }

                bool = true;
                break;

            } else if (CBoxTipo.equals( "Professor")) {

                telefone = new Telefone();
                professor = new Professor();

                professor.setNome(TxtNome.getText());
                professor.setEmail(TxtEmail.getText());
                telefone.setDdd(Txtddd.getText());
                telefone.setNumero(TxtTelefone.getText());
                professor.addTelefone(telefone);
                professor.setFormacao(TxtEspecifica.getText());
                
                

                if (inserindo) {
                    daoProfessor.create(professor);

                    JOptionPane.showMessageDialog(null, "Leitor"+" Id: "+leitor.getId()+" salvo!");
                } else {
                    daoProfessor.update(professor);

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

        } else if (TabelaLeitores.getSelectionModel().getSelectedItem().getClass() == Professor.class) {

            professor = (Professor) TabelaLeitores.getSelectionModel().getSelectedItem();

            daoProfessor.delete(professor);

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

        CampoCodigo.setText(leitor.getCodigo().toString());
        CampoNome.setText(leitor.getNome());
        CampoEndereco.setText(leitor.getEndereco());
        CampoTelefone.setText(leitor.getTelefone());
        CampoPrazo.setText(leitor.getPrazoMaximoDevolucao().toString());
        ComboBoxTipoLeitor.setPromptText(leitor.getClass().getSimpleName());
        LabelInfoEspecifica.setText(leitor.getClass() == Aluno.class ? "Matrícula" : "Disciplina");
        CampoInfoEspecifica
                .setText(leitor.getClass() == Aluno.class ? aluno.getMatricula() : professor.getDisciplina());

    }

    private void dadosEmBranco() {

        CampoCodigo.setText("");
        CampoNome.setText("");
        CampoEndereco.setText("");
        CampoTelefone.setText("");
        CampoInfoEspecifica.setText("");
        CampoPrazo.setText("");
        ComboBoxTipoLeitor.setValue("Selecione um tipo de leitor");
        ;
        LabelInfoEspecifica.setText("");
        CampoInfoEspecifica.setText("");

    }

    private void editar(boolean habilitar) {

        TabelaLeitores.setDisable(habilitar); // Desabilita
        TabelaEmprestimos.setDisable(habilitar); // Desabilita
        CampoNome.setDisable(!habilitar); // Habilita
        CampoEndereco.setDisable(!habilitar);
        CampoTelefone.setDisable(!habilitar);
        BotaoExcluir.setDisable(habilitar);
        BotaoInserir.setDisable(habilitar);
        BotaoAlterar.setDisable(habilitar);
        BotaoSalvar.setDisable(!habilitar);
        ComboBoxTipoLeitor.setDisable(!habilitar);
        CampoInfoEspecifica.setDisable(!habilitar);

    }

    private void preencherLista() {

        List<String> tiposDeLeitor = new ArrayList<>();
        tiposDeLeitor.add("Aluno");
        tiposDeLeitor.add("Professor");
        ObservableList<String> itensCombo = FXCollections.observableArrayList(tiposDeLeitor);
        ComboBoxTipoLeitor.setItems(itensCombo);

        List<Aluno> alunos = daoAluno.buscarTodos();
        List<Professor> professores = daoProfessor.buscarTodos();
        List<Leitor> leitores = new ArrayList<>();

        leitores.addAll(alunos);
        leitores.addAll(professores);

        ObservableList<Leitor> data1 = FXCollections.observableArrayList(leitores);
        TabelaLeitores.setItems(data1);

        List<Emprestimo> emprestimos = daoEmprestimo.buscarTodos();

        List<Emprestimo> emprestimosDoLeitor = new ArrayList<>();

        for (Emprestimo emprestimo : emprestimos) {

            if (emprestimo.getLeitor() == leitor) {

                emprestimosDoLeitor.add(emprestimo);

            }
        }

        ObservableList<Emprestimo> data2 = FXCollections.observableArrayList(emprestimos);
        TabelaEmprestimos.setItems(data2);

    }
}



    
    
    



    