package com.example.almeidapinturasapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.example.almeidapinturasapp.Model.ClienteModel;
import com.example.almeidapinturasapp.Model.FuncionarioModel;
import com.example.almeidapinturasapp.Repository.ClienteRepository;
import com.example.almeidapinturasapp.Repository.FuncionarioRepository;
import com.example.almeidapinturasapp.Utils.Utils;

import java.util.Locale;

public class CadastroClienteActivity extends AppCompatActivity {

    EditText editTextNome;
    EditText editTextRua;
    EditText editTextNumero;
    EditText editTextBairro;
    EditText editTextCidade;
    EditText editTextPais;
    EditText editTextCpf;
    EditText editTextOrcamento;
    EditText editTextFone;

    boolean ValidarDados;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);

        Localizacao();

        editTextNome    = (EditText) this.findViewById(R.id.editTextNome);
        editTextRua     = (EditText) this.findViewById(R.id.editTextRua);
        editTextNumero  = (EditText) this.findViewById(R.id.editTextNumero);
        editTextBairro  = (EditText) this.findViewById(R.id.editTextBairro);
        editTextCidade  = (EditText) this.findViewById(R.id.editTextCidade);
        editTextPais    = (EditText) this.findViewById(R.id.editTextPais);
        editTextCpf     = (EditText) this.findViewById(R.id.editTextCpf);
        editTextOrcamento = (EditText) this.findViewById(R.id.editTextOrcamento);
        editTextFone    = (EditText) this.findViewById(R.id.editTextFone);

        ValidarDados = true;
    }

    private void validarNome(){
        if (TextUtils.isEmpty(editTextNome.getText().toString())){
            ValidarDados = false;
            AlertDialog.Builder cxMsg = new AlertDialog.Builder(this);
            cxMsg.setMessage("Nome invalido");
            cxMsg.show();
            validarNome();
        } else {
            ValidarDados = true;
        }
    }

    private void validarRua(){
        if (TextUtils.isEmpty(editTextRua.getText().toString())){
            ValidarDados = false;
            AlertDialog.Builder cxMsg = new AlertDialog.Builder(this);
            cxMsg.setMessage("Email invalido");
            cxMsg.show();
            validarRua();
        } else {
            ValidarDados = true;
        }
    }

    private void validarNumero(){
        if (TextUtils.isEmpty(editTextNumero.getText().toString())){
            ValidarDados = false;
            AlertDialog.Builder cxMsg = new AlertDialog.Builder(this);
            cxMsg.setMessage("Email invalido");
            cxMsg.show();
            validarNumero();
        } else {
            ValidarDados = true;
        }
    }

    private void validarBairro(){
        if (TextUtils.isEmpty(editTextBairro.getText().toString())){
            ValidarDados = false;
            AlertDialog.Builder cxMsg = new AlertDialog.Builder(this);
            cxMsg.setMessage("Email invalido");
            cxMsg.show();
            validarBairro();
        } else {
            ValidarDados = true;
        }
    }

    private void validarCidade(){
        if (TextUtils.isEmpty(editTextCidade.getText().toString())){
            ValidarDados = false;
            AlertDialog.Builder cxMsg = new AlertDialog.Builder(this);
            cxMsg.setMessage("Email invalido");
            cxMsg.show();
            validarCidade();
        } else {
            ValidarDados = true;
        }
    }

    private void validarPais(){
        if (TextUtils.isEmpty(editTextPais.getText().toString())){
            ValidarDados = false;
            AlertDialog.Builder cxMsg = new AlertDialog.Builder(this);
            cxMsg.setMessage("Email invalido");
            cxMsg.show();
            validarPais();
        } else {
            ValidarDados = true;
        }
    }

    private void validarCpf(){
        if (TextUtils.isEmpty(editTextCpf.getText().toString())){
            ValidarDados = false;
            AlertDialog.Builder cxMsg = new AlertDialog.Builder(this);
            cxMsg.setMessage("Email invalido");
            cxMsg.show();
            validarCpf();
        } else {
            ValidarDados = true;
        }
    }

    private void validarOrcamento(){
        if (TextUtils.isEmpty(editTextOrcamento.getText().toString())){
            ValidarDados = false;
            AlertDialog.Builder cxMsg = new AlertDialog.Builder(this);
            cxMsg.setMessage("Email invalido");
            cxMsg.show();
            validarOrcamento();
        } else {
            ValidarDados = true;
        }
    }

    private void validarFone(){
        if (TextUtils.isEmpty(editTextFone.getText().toString())){
            ValidarDados = false;
            AlertDialog.Builder cxMsg = new AlertDialog.Builder(this);
            cxMsg.setMessage("Email invalido");
            cxMsg.show();
            validarFone();
        } else {
            ValidarDados = true;
        }
    }

    private void validarFormulario(){
        validarNome();
        validarRua();
        validarNumero();
        validarBairro();
        validarCidade();
        validarPais();
        validarCpf();
        validarFone();
        validarOrcamento();
    }

    protected  void SalvarCadastroBanco() {

        /*CRIANDO UM OBJETO FUNCIONARIO*/
        ClienteModel clienteModel = new ClienteModel();

        clienteModel.setNome(editTextNome.getText().toString().trim());
        clienteModel.setCpf(editTextCpf.getText().toString().trim());
        clienteModel.setOrcamento(editTextOrcamento.getText().toString().trim());
        clienteModel.setRua(editTextRua.getText().toString().trim());
        clienteModel.setNumero(editTextNumero.getText().toString().trim());
        clienteModel.setBairro(editTextBairro.getText().toString().trim());
        clienteModel.setCidade(editTextCidade.getText().toString().trim());
        clienteModel.setPais(editTextPais.getText().toString().trim());
        clienteModel.setFone(editTextFone.getText().toString().trim());

        /*SALVANDO UM NOVO REGISTRO*/
        new ClienteRepository(this).Salvar(clienteModel);
        /*MENSAGEM DE SUCESSO!*/
        Utils.Alert(this, "Registro Salvo");
    }

    public void abrir_tela_cliente(View v){
        validarFormulario();
        SalvarCadastroBanco();
        if (ValidarDados == true){
            Intent it_telaConsultarCliente = new Intent(this, ConsultarFuncionarioActivity.class);
            startActivity(it_telaConsultarCliente);
        } else {
            AlertDialog.Builder cxMsg = new AlertDialog.Builder(this);
            cxMsg.setMessage("Erro no cadastro.");
            cxMsg.show();
        }
    }

    protected  void Localizacao(){
        Locale locale = new Locale("pt", "BR");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getApplicationContext().getResources().updateConfiguration(config, null);
    }

}