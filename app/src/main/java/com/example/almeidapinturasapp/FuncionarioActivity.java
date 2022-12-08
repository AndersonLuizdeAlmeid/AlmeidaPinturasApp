package com.example.almeidapinturasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FuncionarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funcionario);
    }

    public void abrir_tela_cadastro_funcionario(View v){
        Intent it_telaCadastroFuncionario = new Intent(this, ConsultarFuncionarioActivity.class);
        startActivity(it_telaCadastroFuncionario);
    }

    public void abrir_tela_inicial(View v){
        Intent it_telaInicial = new Intent(this, TelaInicialActivity.class);
        startActivity(it_telaInicial);
    }
}