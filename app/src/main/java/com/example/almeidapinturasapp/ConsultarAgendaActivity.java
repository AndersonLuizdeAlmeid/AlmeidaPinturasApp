package com.example.almeidapinturasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.almeidapinturasapp.Model.AgendaModel;
import com.example.almeidapinturasapp.Repository.AgendaRepository;
import com.example.almeidapinturasapp.Utils.ConsultarAgendaActivityAdapter;

import java.util.List;

public class ConsultarAgendaActivity extends AppCompatActivity {

    //CRIANDO UM OBJETO DO TIPO ListView PARA RECEBER OS REGISTROS DE UM ADAPTER
    ListView listViewAgenda;

    //CRIANDO O BOTÃO VOLTAR PARA RETORNAR PARA A TELA COM AS OPÇÕES
    Button buttonVoltar;

    Button buttonCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_cliente);

        //VINCULANDO O LISTVIEW DA TELA AO OBJETO CRIADO
        listViewAgenda = (ListView)this.findViewById(R.id.listViewAgenda);

        //VINCULANDO O BOTÃO VOLTAR DA TELA AO OBJETO CRIADO
        buttonVoltar       = (Button)this.findViewById(R.id.buttonVoltar);
        buttonCadastrar    = (Button)this.findViewById(R.id.buttonCadastrar);


        //CHAMA O MÉTODO QUE CARREGA AS PESSOAS CADASTRADAS NA BASE DE DADOS
        this.CarregarAgendaCadastradas();

        //CHAMA O MÉTODO QUE CRIA O EVENTO PARA O BOTÃO VOLTAR
        this.CriarEvento();
        this.CriarEventoCadastrar();
    }

    //MÉTODO QUE CRIA EVENTO PARA O BOTÃO VOLTAR
    protected  void CriarEvento(){

        buttonVoltar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                //REDIRECIONA PARA A TELA PRINCIPAL
                Intent intentTelaInicialActivity = new Intent(getApplicationContext(), TelaInicialActivity.class);
                startActivity(intentTelaInicialActivity);

                //FINALIZA A ATIVIDADE ATUAL
                finish();
            }
        });
    }

    protected  void CriarEventoCadastrar(){

        buttonCadastrar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                //REDIRECIONA PARA A TELA PRINCIPAL
                Intent intentCadastroAgendaActivity = new Intent(getApplicationContext(), CadastroAgendaActivity.class);
                startActivity(intentCadastroAgendaActivity);

                //FINALIZA A ATIVIDADE ATUAL
                finish();
            }
        });
    }

    //MÉTODO QUE CONSULTA AS PESSOAS CADASTRADAS
    protected  void CarregarAgendaCadastradas(){

        AgendaRepository agendaRepository =  new AgendaRepository(this);

        //BUSCA AS PESSOAS CADASTRADAS
        List<AgendaModel> agendas = agendaRepository.SelecionarTodosAgenda();

        //SETA O ADAPTER DA LISTA COM OS REGISTROS RETORNADOS DA BASE
        listViewAgenda.setAdapter(new ConsultarAgendaActivityAdapter(this, agendas));
    }

}