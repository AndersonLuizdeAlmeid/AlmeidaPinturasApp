package com.example.almeidapinturasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.almeidapinturasapp.Model.FuncionarioModel;
import com.example.almeidapinturasapp.Repository.FuncionarioRepository;
import com.example.almeidapinturasapp.Utils.Utils;

public class EditarFuncionarioActivity extends AppCompatActivity {

    /*COMPONENTES DA TELA*/
    EditText editTextCodigo;
    EditText editTextNome;
    EditText editTextRua;
    EditText editTextNumero;
    EditText editTextBairro;
    EditText editTextCidade;
    EditText editTextPais;
    EditText editTextCpf;
    EditText editTextSalario;
    EditText editTextFone;
    Button buttonAlterar;
    Button buttonVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_funcionario);
        //CHAMA O MÉTODO PARA CRIAR OS COMPONENTES DA TELA
        this.CriarComponentes();
        //CHAMA O MÉTODO QUE CRIA EVENTOS PARA OS COMPONENTES
        this.CriarEventos();
        //CARREGA OS VALORES NOS CAMPOS DA TELA.
        this.CarregaValoresCampos();
    }

    //VINCULA OS COMPONENTES DA TELA(VIEW) AOS OBJETOS DECLARADOS.
    protected  void CriarComponentes(){

        editTextCodigo            = (EditText) this.findViewById(R.id.editTextCodigo);
        editTextNome              = (EditText) this.findViewById(R.id.editTextNomeFuncionario);
        editTextRua               = (EditText) this.findViewById(R.id.editTextRua);
        editTextNumero            = (EditText)this.findViewById(R.id.editTextNumero);
        editTextBairro            = (EditText)this.findViewById(R.id.editTextBairro);
        editTextCidade            = (EditText)this.findViewById(R.id.editTextCidade);
        editTextPais              = (EditText)this.findViewById(R.id.editTextPais);
        editTextCpf               = (EditText)this.findViewById(R.id.editTextCPF);
        editTextSalario           = (EditText)this.findViewById(R.id.editTextSalario);
        editTextFone              = (EditText)this.findViewById(R.id.editTextFone);
        buttonAlterar             = (Button) this.findViewById(R.id.buttonAlterar);
        buttonVoltar              = (Button) this.findViewById(R.id.buttonVoltar);
    }

    //MÉTODO CRIA OS EVENTOS PARA OS COMPONENTES
    protected  void CriarEventos(){

        //CRIANDO EVENTO CLICK PARA O BOTÃO ALTERAR
        buttonAlterar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Alterar_onClick();
            }
        });

        //CRIANDO EVENTO CLICK PARA O BOTÃO VOLTAR
        buttonVoltar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intentTelaInicial = new Intent(getApplicationContext(), TelaInicialActivity.class);
                startActivity(intentTelaInicial);
                finish();
            }
        });
    }

    //ALTERA UM REGISTRO
    protected  void Alterar_onClick(){

        //VALIDA SE OS CAMPOS ESTÃO VAZIOS ANTES DE ALTERAR O REGISTRO
        if(editTextNome.getText().toString().trim().equals("")){

            Utils.Alert(this, "Nome Obrigatório");

            //FOCO NO CAMPO
            editTextNome.requestFocus();
        }
        else if(editTextCpf.getText().toString().trim().equals("")){
            Utils.Alert(this, "CPF Obrigatório");
            editTextCpf.requestFocus();

        }
        else{


            /*CRIANDO UM OBJETO PESSOA*/
            FuncionarioModel funcionarioModel = new FuncionarioModel();
            funcionarioModel.setCodigo(Integer.parseInt(editTextCodigo.getText().toString()));

            funcionarioModel.setNome(editTextNome.getText().toString().trim());
            funcionarioModel.setRua(editTextRua.getText().toString().trim());
            funcionarioModel.setNumero(editTextNumero.getText().toString().trim());
            funcionarioModel.setBairro(editTextBairro.getText().toString().trim());
            funcionarioModel.setCidade(editTextCidade.getText().toString().trim());
            funcionarioModel.setPais(editTextPais.getText().toString().trim());
            funcionarioModel.setCpf(editTextCpf.getText().toString().trim());
            funcionarioModel.setSalario(editTextSalario.getText().toString().trim());
            funcionarioModel.setFone(editTextFone.getText().toString().trim());

            /*ALTERANDO O REGISTRO*/
            new FuncionarioRepository(this).Atualizar(funcionarioModel);
            /*MENSAGEM DE SUCESSO!*/

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            //ADICIONANDO UM TITULO A NOSSA MENSAGEM DE ALERTA
            alertDialog.setTitle(R.string.app_name);
            //MENSAGEM A SER EXIBIDA
            alertDialog.setMessage("Registro alterado com sucesso! ");

            //CRIA UM BOTÃO COM O TEXTO OK SEM AÇÃO
            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    //RETORNA PARA A TELA DE CONSULTA
                    Intent intentRedirecionar = new Intent(getApplicationContext(), ConsultarFuncionarioActivity.class);
                    startActivity(intentRedirecionar);
                    finish();
                }
            });
            //MOSTRA A MENSAGEM NA TELA
            alertDialog.show();
        }
    }

    //CARREGA OS VALORES NOS CAMPOS APÓS RETORNAR DO SQLITE
    protected  void CarregaValoresCampos(){
        FuncionarioRepository funcionarioRepository = new FuncionarioRepository(this);

        //PEGA O ID PESSOA QUE FOI PASSADO COMO PARAMETRO ENTRE AS TELAS
        Bundle extra =  this.getIntent().getExtras();
        int id_pessoa = extra.getInt("id_pessoa");

        //CONSULTA UMA PESSOA POR ID
        FuncionarioModel funcionarioModel = funcionarioRepository.GetFuncionario(id_pessoa);
        //SETA O CÓDIGO NA VIEW
        editTextCodigo.setText(String.valueOf(funcionarioModel.getCodigo()));
        editTextNome.setText(funcionarioModel.getNome());
        editTextRua.setText(funcionarioModel.getRua());
        editTextNumero.setText(funcionarioModel.getNumero());
        editTextBairro.setText(funcionarioModel.getBairro());
        editTextCidade.setText(funcionarioModel.getCidade());
        editTextPais.setText(funcionarioModel.getPais());
        editTextCpf.setText(funcionarioModel.getCpf());
        editTextSalario.setText(funcionarioModel.getSalario());
        editTextFone.setText(funcionarioModel.getFone());
    }
}