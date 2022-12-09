package com.example.almeidapinturasapp.Utils;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.almeidapinturasapp.ConsultarAgendaActivity;
import com.example.almeidapinturasapp.EditarAgendaActivity;
import com.example.almeidapinturasapp.Model.AgendaModel;
import com.example.almeidapinturasapp.R;
import com.example.almeidapinturasapp.Repository.AgendaRepository;

import java.util.ArrayList;
import java.util.List;

public class ConsultarAgendaActivityAdapter extends BaseAdapter {

    //CRIANDO UM OBJETO LayoutInflater PARA FAZER LINK A NOSSA VIEW(activity_linha_consultar.xml)
    private static LayoutInflater layoutInflater = null;

    //CRIANDO UMA LISTA DE PESSOAS
    List<AgendaModel> agendaModel =  new ArrayList<AgendaModel>();

    //CIRANDO UM OBJETO DA NOSSA CLASSE QUE FAZ ACESSO AO BANCO DE DADOS
    AgendaRepository agendaRepository;

    //CRIANDO UM OBJETO DA NOSSA ATIVIDADE QUE CONTEM A LISTA
    private ConsultarAgendaActivity consultarAgendaActivity;

    //CONSTRUTOR QUE VAI RECEBER A NOSSA ATIVIDADE COMO PARAMETRO E A LISTA DE PESSOAS QUE VAI RETORNAR
    //DA NOSSA BASE DE DADOS
    public ConsultarAgendaActivityAdapter(ConsultarAgendaActivity consultarAgendaActivity, List<AgendaModel> agendaModel) {

        this.agendaModel               =  agendaModel;
        this.consultarAgendaActivity   =  consultarAgendaActivity;
        this.layoutInflater            = (LayoutInflater) this.consultarAgendaActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.agendaRepository          = new AgendaRepository(consultarAgendaActivity);
    }

    //RETORNA A QUANTIDADE DE REGISTROS DA LISTA
    @Override
    public int getCount(){

        return agendaModel.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    //ESSE MÉTODO SETA OS VALORES DE UM ITEM DA NOSSA LISTA DE PESSOAS PARA UMA LINHA DO NOSSO LISVIEW
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        //CRIANDO UM OBJETO DO TIPO View PARA ACESSAR O NOSSO ARQUIVO DE LAYOUT activity_linha_consultar.xml
        final View viewLinhaLista = layoutInflater.inflate(R.layout.activity_consultar_agenda,null);

        //VINCULANDO OS CAMPOS DO ARQUIVO DE LAYOUT(activity_linha_consultar.xml) AOS OBJETOS DECLARADOS.

        //CAMPO QUE VAI MOSTRAR O CÓDIGO DA PESSOA
        TextView textViewCodigo          = (TextView) viewLinhaLista.findViewById(R.id.textViewCodigoAgenda);
        TextView textViewData            = (TextView) viewLinhaLista.findViewById(R.id.textViewData);
        TextView textViewHora        = (TextView) viewLinhaLista.findViewById(R.id.textViewHora);

        //CRIANDO O BOTÃO  EXCLUIR PARA DELETARMOS UM REGISTRO DO BANCO DE DADOS
        Button buttonExcluir             = (Button)   viewLinhaLista.findViewById(R.id.buttonExcluir);

        //CRIANDO O BOTÃO PARA EDITAR UM REGISTRO CADASTRADO
        Button buttonEditarCliente            = (Button)   viewLinhaLista.findViewById(R.id.buttonEditarAgenda);

        //SETANDO DADOS PARA MOSTRAR NA TELA
        textViewCodigo.setText(String.valueOf(agendaModel.get(position).getCodigo()));
        textViewData.setText(agendaModel.get(position).getNome());
        textViewHora.setText(agendaModel.get(position).getRua());

        //CRIANDO EVENTO CLICK PARA O BOTÃO DE EXCLUIR REGISTRO
        buttonExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //EXCLUINDO UM REGISTRO
                agendaRepository.Excluir(agendaModel.get(position).getCodigo());

                //MOSTRA A MENSAGEM APÓS EXCLUIR UM REGISTRO
                Toast.makeText(consultarAgendaActivity, "Registro excluido com sucesso!", Toast.LENGTH_LONG).show();

                //CHAMA O MÉTODO QUE ATUALIZA A LISTA COM OS REGISTROS QUE AINDA ESTÃO NA BASE
                AtualizarLista();

            }
        });

        //CRIANDO EVENTO CLICK PARA O BOTÃO QUE VAI REDIRECIONAR PARA A TELA DE EDIÇÃO
        // DO REGISTRO.
        buttonEditarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRedirecionar = new Intent(consultarAgendaActivity, EditarAgendaActivity.class);
                intentRedirecionar.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intentRedirecionar.putExtra("id_agenda",agendaModel.get(position).getCodigo());
                consultarAgendaActivity.startActivity(intentRedirecionar);
                consultarAgendaActivity.finish();
            }
        });
        return viewLinhaLista;
    }

    //ATUALIZA A LISTTA DEPOIS DE EXCLUIR UM REGISTRO
    public void AtualizarLista(){
        this.agendaModel.clear();
        this.agendaModel = agendaRepository.SelecionarTodosAgenda();
        this.notifyDataSetChanged();
    }
}