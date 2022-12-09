package com.example.almeidapinturasapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.example.almeidapinturasapp.Model.AgendaModel;
import com.example.almeidapinturasapp.Repository.AgendaRepository;
import com.example.almeidapinturasapp.Utils.Utils;

import java.util.Locale;

public class CadastroAgendaActivity extends AppCompatActivity {


    EditText editTextData;
    EditText editTextHora;

    boolean ValidarDados;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);

        Localizacao();

        editTextData = (EditText) this.findViewById(R.id.editTextData);
        editTextHora = (EditText) this.findViewById(R.id.editTextHora);

        ValidarDados = true;
    }

    private void validarData() {
        if (TextUtils.isEmpty(editTextData.getText().toString())) {
            ValidarDados = false;
            AlertDialog.Builder cxMsg = new AlertDialog.Builder(this);
            cxMsg.setMessage("Nome invalido");
            cxMsg.show();
            validarHora();
        } else {
            ValidarDados = true;
        }
    }

    private void validarHora() {
        if (TextUtils.isEmpty(editTextHora.getText().toString())) {
            ValidarDados = false;
            AlertDialog.Builder cxMsg = new AlertDialog.Builder(this);
            cxMsg.setMessage("Email invalido");
            cxMsg.show();
        } else {
            ValidarDados = true;
        }
    }


    private void validarFormulario() {
        validarData();
        validarHora();
    }

    protected void SalvarCadastroBanco() {

        /*CRIANDO UM OBJETO FUNCIONARIO*/
        AgendaModel agendaModel = new AgendaModel();

        agendaModel.setData(editTextData.getText().toString().trim());
        agendaModel.setHora(editTextHora.getText().toString().trim());

        /*SALVANDO UM NOVO REGISTRO*/
        new AgendaRepository(this).Salvar(agendaModel);
        /*MENSAGEM DE SUCESSO!*/
        Utils.Alert(this, "Registro Salvo");
    }

    public void abrir_tela_cliente(View v) {
        validarFormulario();
        SalvarCadastroBanco();
        if (ValidarDados == true) {
            Intent it_telaConsultarAgenda = new Intent(this, ConsultarAgendaActivity.class);
            startActivity(it_telaConsultarAgenda);
        } else {
            AlertDialog.Builder cxMsg = new AlertDialog.Builder(this);
            cxMsg.setMessage("Erro no cadastro.");
            cxMsg.show();
        }
    }

    protected void Localizacao() {
        Locale locale = new Locale("pt", "BR");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getApplicationContext().getResources().updateConfiguration(config, null);
    }
}

