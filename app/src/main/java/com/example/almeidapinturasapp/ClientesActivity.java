package com.example.almeidapinturasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ClientesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);
    }

    public void abrir_tela_cadastro_cliente(View v){
        Intent it_telaCadastroCliente = new Intent(this, ConsultarClienteActivity.class);
        startActivity(it_telaCadastroCliente);
    }

    public void abrir_tela_inicial(View v){
        Intent it_telaInicial = new Intent(this, TelaInicialActivity.class);
        startActivity(it_telaInicial);
    }
}