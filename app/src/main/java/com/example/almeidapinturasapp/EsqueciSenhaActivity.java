package com.example.almeidapinturasapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import kotlinx.coroutines.AwaitKt;

public class EsqueciSenhaActivity extends AppCompatActivity {

    private EditText esqueciSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esqueci_senha);

        esqueciSenha = findViewById(R.id.esqueciSenha);
    }

    public void enviar_senha_celular(View v){

        if(!TextUtils.equals(esqueciSenha.getText().toString(), "5499972516")){
            AlertDialog.Builder cxMsg = new AlertDialog.Builder(this);
            cxMsg.setMessage("Celular invalido");
            cxMsg.show();
        } else {
            AlertDialog.Builder cxMsg = new AlertDialog.Builder(this);
            cxMsg.setMessage("Senha enviada");
            cxMsg.show();
            Intent it_main = new Intent(this, MainActivity.class);
            startActivity(it_main);
        }
    }

}