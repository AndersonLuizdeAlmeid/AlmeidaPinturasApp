package com.example.almeidapinturasapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText email;
    private EditText senha;
    private boolean dadosValidados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email);
        senha = findViewById(R.id.senha);
        dadosValidados = false;
    }

    public void abrir_tela_inicial(View v){

        validarFormulario();

        if (dadosValidados == false && !TextUtils.equals(email.getText().toString(), "almeida.pinturas@gmail.com")) {
            AlertDialog.Builder cxMsg = new AlertDialog.Builder(this);
            cxMsg.setMessage("Email invalido");
            cxMsg.show();
        } else if (dadosValidados == false && !TextUtils.equals(senha.getText().toString(), "almeida1234")){
            AlertDialog.Builder cxMsg = new AlertDialog.Builder(this);
            cxMsg.setMessage("Senha invalida");
            cxMsg.show();
        } else {
            Intent it_telaInicial = new Intent(this, TelaInicialActivity.class);
            startActivity(it_telaInicial);
        }
    }

    private void validarFormulario(){

        if (TextUtils.equals(email.getText().toString(), "almeida.pinturas@gmail.com") &&
                TextUtils.equals(senha.getText().toString(), "almeida1234")){
            dadosValidados = true;
        } else {
            dadosValidados = false;
        }
    }

    public void abrir_esqueci_senha(View v){
        Intent it_esquecisenha = new Intent(this, EsqueciSenhaActivity.class);
        startActivity(it_esquecisenha);
    }



}