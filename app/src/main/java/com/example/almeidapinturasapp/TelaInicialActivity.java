package com.example.almeidapinturasapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TelaInicialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        AlertDialog.Builder cxMsg = new AlertDialog.Builder(this);
        cxMsg.setMessage("Bem vindo");
        cxMsg.show();
    }

    public void abrir_tela_consulta_funcionario(View v){
        Intent it_telaCadastroFuncionario = new Intent(this, ConsultarFuncionarioActivity.class);
        startActivity(it_telaCadastroFuncionario);
    }

    public void abrir_tela_consulta_cliente(View v){
        Intent it_telaCadastroCliente = new Intent(this, ConsultarClienteActivity.class);
        startActivity(it_telaCadastroCliente);
    }

    public void abrir_tela_agenda(View v){
        Intent it_telaAgenda = new Intent(this, ConsultarAgendaActivity.class);
        startActivity(it_telaAgenda);
    }
}