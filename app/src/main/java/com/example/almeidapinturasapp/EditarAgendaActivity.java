package com.example.almeidapinturasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.almeidapinturasapp.Model.AgendaModel;
import com.example.almeidapinturasapp.Repository.AgendaRepository;
import com.example.almeidapinturasapp.Utils.Utils;

public class EditarAgendaActivity extends AppCompatActivity {

    /*COMPONENTES DA TELA*/
    EditText editTextCodigo;
    EditText editTextData;
    EditText editTextHora;
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
        editTextData              = (EditText) this.findViewById(R.id.editTextData);
        editTextHora              = (EditText) this.findViewById(R.id.editTextHora);
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
        if(editTextData.getText().toString().trim().equals("")){

            Utils.Alert(this, "Data Obrigatório");

            //FOCO NO CAMPO
            editTextData.requestFocus();
        }
        else if(editTextHora.getText().toString().trim().equals("")){
            Utils.Alert(this, "Hora Obrigatório");
            editTextHora.requestFocus();

        }
        else{


            /*CRIANDO UM OBJETO PESSOA*/
            AgendaModel agendaModel = new AgendaModel();
            agendaModel.setCodigo(Integer.parseInt(editTextCodigo.getText().toString()));

            agendaModel.setData(editTextData.getText().toString().trim());
            agendaModel.setHora(editTextHora.getText().toString().trim());

            /*ALTERANDO O REGISTRO*/
            new AgendaRepository(this).Atualizar(agendaModel);
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
                    Intent intentRedirecionar = new Intent(getApplicationContext(), ConsultarAgendaActivity.class);
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
        AgendaRepository agendaRepository = new AgendaRepository(this);

        //PEGA O ID PESSOA QUE FOI PASSADO COMO PARAMETRO ENTRE AS TELAS
        Bundle extra =  this.getIntent().getExtras();
        int id_agenda = extra.getInt("id_agenda");

        //CONSULTA UMA PESSOA POR ID
        AgendaModel agendaModel = agendaRepository.GetAgenda(id_agenda);
        //SETA O CÓDIGO NA VIEW
        editTextCodigo.setText(String.valueOf(agendaModel.getCodigo()));
        editTextData.setText(agendaModel.getData());
        editTextHora.setText(agendaModel.getHora());

    }
}