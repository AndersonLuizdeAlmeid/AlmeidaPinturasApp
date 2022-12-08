package com.example.almeidapinturasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.almeidapinturasapp.Model.ClienteModel;
import com.example.almeidapinturasapp.Repository.ClienteRepository;
import com.example.almeidapinturasapp.Utils.Utils;

public class EditarClienteActivity extends AppCompatActivity {

    /*COMPONENTES DA TELA*/
    EditText editTextCodigo;
    EditText editTextNomeCliente;
    EditText editTextRua;
    EditText editTextNumero;
    EditText editTextBairro;
    EditText editTextCidade;
    EditText editTextPais;
    EditText editTextCpf;
    EditText editTextOrcamento;
    EditText editTextFone;
    Button buttonAlterar;
    Button buttonVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_cliente);
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
        editTextNomeCliente       = (EditText) this.findViewById(R.id.editTextNomeCliente);
        editTextRua               = (EditText) this.findViewById(R.id.editTextRua);
        editTextNumero            = (EditText)this.findViewById(R.id.editTextNumero);
        editTextBairro            = (EditText)this.findViewById(R.id.editTextBairro);
        editTextCidade            = (EditText)this.findViewById(R.id.editTextCidade);
        editTextPais              = (EditText)this.findViewById(R.id.editTextPais);
        editTextCpf               = (EditText)this.findViewById(R.id.editTextCPF);
        editTextOrcamento         = (EditText)this.findViewById(R.id.editTextOrcamento);
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
        if(editTextNomeCliente.getText().toString().trim().equals("")){

            Utils.Alert(this, "Nome Obrigatório");

            //FOCO NO CAMPO
            editTextNomeCliente.requestFocus();
        }
        else if(editTextCpf.getText().toString().trim().equals("")){
            Utils.Alert(this, "CPF Obrigatório");
            editTextCpf.requestFocus();

        }
        else{


            /*CRIANDO UM OBJETO PESSOA*/
            ClienteModel clienteModel = new ClienteModel();
            clienteModel.setCodigo(Integer.parseInt(editTextCodigo.getText().toString()));

            clienteModel.setNome(editTextNomeCliente.getText().toString().trim());
            clienteModel.setRua(editTextRua.getText().toString().trim());
            clienteModel.setNumero(editTextNumero.getText().toString().trim());
            clienteModel.setBairro(editTextBairro.getText().toString().trim());
            clienteModel.setCidade(editTextCidade.getText().toString().trim());
            clienteModel.setPais(editTextPais.getText().toString().trim());
            clienteModel.setCpf(editTextCpf.getText().toString().trim());
            clienteModel.setOrcamento(editTextOrcamento.getText().toString().trim());
            clienteModel.setFone(editTextFone.getText().toString().trim());

            /*ALTERANDO O REGISTRO*/
            new ClienteRepository(this).Atualizar(clienteModel);
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
                    Intent intentRedirecionar = new Intent(getApplicationContext(), ConsultarClienteActivity.class);
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
        ClienteRepository clienteRepository = new ClienteRepository(this);

        //PEGA O ID PESSOA QUE FOI PASSADO COMO PARAMETRO ENTRE AS TELAS
        Bundle extra =  this.getIntent().getExtras();
        int id_pessoa = extra.getInt("id_pessoaCliente");

        //CONSULTA UMA PESSOA POR ID
        ClienteModel clienteModel = clienteRepository.GetCliente(id_pessoa);
        //SETA O CÓDIGO NA VIEW
        editTextCodigo.setText(String.valueOf(clienteModel.getCodigo()));
        editTextNomeCliente.setText(clienteModel.getNome());
        editTextRua.setText(clienteModel.getRua());
        editTextNumero.setText(clienteModel.getNumero());
        editTextBairro.setText(clienteModel.getBairro());
        editTextCidade.setText(clienteModel.getCidade());
        editTextPais.setText(clienteModel.getPais());
        editTextCpf.setText(clienteModel.getCpf());
        editTextOrcamento.setText(clienteModel.getOrcamento().toString());
        editTextFone.setText(clienteModel.getFone());
    }
}