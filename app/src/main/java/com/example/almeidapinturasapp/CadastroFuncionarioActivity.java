package com.example.almeidapinturasapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.example.almeidapinturasapp.Model.FuncionarioModel;
import com.example.almeidapinturasapp.Repository.FuncionarioRepository;
import com.example.almeidapinturasapp.Utils.Utils;

import java.util.Locale;

public class CadastroFuncionarioActivity extends AppCompatActivity {

    EditText editTextNome;
    EditText editTextCpf;
    EditText editTextSalario;
    EditText editTextRua;
    EditText editTextNumero;
    EditText editTextBairro;
    EditText editTextCidade;
    EditText editTextPais;
    EditText editTextFone;

    boolean ValidarDados;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_funcionario);

        Localizacao();

        editTextNome    = (EditText) this.findViewById(R.id.editTextNomeFuncionario);
        editTextCpf     = (EditText) this.findViewById(R.id.editTextCpf);
        editTextSalario = (EditText) this.findViewById(R.id.editTextSalario);
        editTextRua     = (EditText) this.findViewById(R.id.editTextRua);
        editTextNumero  = (EditText) this.findViewById(R.id.editTextNumero);
        editTextBairro  = (EditText) this.findViewById(R.id.editTextBairro);
        editTextCidade  = (EditText) this.findViewById(R.id.editTextCidade);
        editTextPais    = (EditText) this.findViewById(R.id.editTextPais);
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

    private void validarSalario(){
        if (TextUtils.isEmpty(editTextSalario.getText().toString())){
            ValidarDados = false;
            AlertDialog.Builder cxMsg = new AlertDialog.Builder(this);
            cxMsg.setMessage("Email invalido");
            cxMsg.show();
            validarSalario();
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
        validarCpf();
        validarSalario();
        validarRua();
        validarNumero();
        validarBairro();
        validarCidade();
        validarPais();
        validarFone();

    }

    protected  void SalvarCadastroBanco() {

        /*CRIANDO UM OBJETO FUNCIONARIO*/
        FuncionarioModel funcionarioModel = new FuncionarioModel();

        funcionarioModel.setNome(editTextNome.getText().toString().trim());
        funcionarioModel.setCpf(editTextCpf.getText().toString().trim());
        funcionarioModel.setSalario(editTextSalario.getText().toString().trim());
        funcionarioModel.setRua(editTextRua.getText().toString().trim());
        funcionarioModel.setNumero(editTextNumero.getText().toString().trim());
        funcionarioModel.setBairro(editTextBairro.getText().toString().trim());
        funcionarioModel.setCidade(editTextCidade.getText().toString().trim());
        funcionarioModel.setPais(editTextPais.getText().toString().trim());
        funcionarioModel.setFone(editTextFone.getText().toString().trim());

        /*SALVANDO UM NOVO REGISTRO*/
        new FuncionarioRepository(this).Salvar(funcionarioModel);
        /*MENSAGEM DE SUCESSO!*/
        Utils.Alert(this, "Registro Salvo");
    }

    public void abrir_tela_funcionario(View v){
        validarFormulario();
        SalvarCadastroBanco();
        if (ValidarDados == true){
            Intent it_telaConsultarFuncionario = new Intent(this, ConsultarFuncionarioActivity.class);
            startActivity(it_telaConsultarFuncionario);
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
