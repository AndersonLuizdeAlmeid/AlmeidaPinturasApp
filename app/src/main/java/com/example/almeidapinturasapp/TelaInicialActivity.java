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

    public void abrir_tela_funcionario(View v){
        Intent it_telaInicial = new Intent(this, FuncionarioActivity.class);
        startActivity(it_telaInicial);
    }

    public void abrir_tela_cliente(View v){
        Intent it_telaInicial = new Intent(this, ClientesActivity.class);
        startActivity(it_telaInicial);
    }

    public void abrir_tela_agenda(View v){
        Intent it_telaInicial = new Intent(this, AgendaActivity.class);
        startActivity(it_telaInicial);
    }
}