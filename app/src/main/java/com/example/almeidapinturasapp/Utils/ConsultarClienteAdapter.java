package com.example.almeidapinturasapp.Utils;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.almeidapinturasapp.ConsultarClienteActivity;
import com.example.almeidapinturasapp.EditarClienteActivity;
import com.example.almeidapinturasapp.Model.ClienteModel;
import com.example.almeidapinturasapp.R;
import com.example.almeidapinturasapp.Repository.ClienteRepository;

import java.util.ArrayList;
import java.util.List;

public class ConsultarClienteAdapter extends BaseAdapter {

    //CRIANDO UM OBJETO LayoutInflater PARA FAZER LINK A NOSSA VIEW(activity_linha_consultar.xml)
    private static LayoutInflater layoutInflater = null;

    //CRIANDO UMA LISTA DE PESSOAS
    List<ClienteModel> clienteModels =  new ArrayList<ClienteModel>();

    //CIRANDO UM OBJETO DA NOSSA CLASSE QUE FAZ ACESSO AO BANCO DE DADOS
    ClienteRepository clienteRepository;

    //CRIANDO UM OBJETO DA NOSSA ATIVIDADE QUE CONTEM A LISTA
    private ConsultarClienteActivity consultarClienteActivity;

    //CONSTRUTOR QUE VAI RECEBER A NOSSA ATIVIDADE COMO PARAMETRO E A LISTA DE PESSOAS QUE VAI RETORNAR
    //DA NOSSA BASE DE DADOS
    public ConsultarClienteAdapter(ConsultarClienteActivity consultarClienteActivity, List<ClienteModel> clienteModels) {

        this.clienteModels             =  clienteModels;
        this.consultarClienteActivity  =  consultarClienteActivity;
        this.layoutInflater            = (LayoutInflater) this.consultarClienteActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.clienteRepository         = new ClienteRepository(consultarClienteActivity);
    }

    //RETORNA A QUANTIDADE DE REGISTROS DA LISTA
    @Override
    public int getCount(){

        return clienteModels.size();
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
        final View viewLinhaLista = layoutInflater.inflate(R.layout.activity_consultar_cliente_adapter,null);

        //VINCULANDO OS CAMPOS DO ARQUIVO DE LAYOUT(activity_linha_consultar.xml) AOS OBJETOS DECLARADOS.

        //CAMPO QUE VAI MOSTRAR O CÓDIGO DA PESSOA
        TextView textViewCodigo          = (TextView) viewLinhaLista.findViewById(R.id.textViewCodigoCliente);

        //CAMPO QUE VAI MOSTRAR O NOME DA PESSOA
        TextView textViewNome            = (TextView) viewLinhaLista.findViewById(R.id.textViewNomeCliente);

        //CAMPO QUE VAI MOSTRAR O ENDEREÇO DA PESSOA
        TextView textViewRua        = (TextView) viewLinhaLista.findViewById(R.id.textViewRua);

        TextView textViewNumero        = (TextView) viewLinhaLista.findViewById(R.id.textViewNumero);
        TextView textViewBairro        = (TextView) viewLinhaLista.findViewById(R.id.textViewBairro);
        TextView textViewCidade        = (TextView) viewLinhaLista.findViewById(R.id.textViewCidade);
        TextView textViewPais          = (TextView) viewLinhaLista.findViewById(R.id.textViewPais);
        TextView textViewCpf           = (TextView) viewLinhaLista.findViewById(R.id.textViewCPF);
        TextView textViewOrcamento     = (TextView) viewLinhaLista.findViewById(R.id.textViewOrcamento);
        TextView textViewFone          = (TextView) viewLinhaLista.findViewById(R.id.textViewFone);

        //CRIANDO O BOTÃO  EXCLUIR PARA DELETARMOS UM REGISTRO DO BANCO DE DADOS
        Button buttonExcluir             = (Button)   viewLinhaLista.findViewById(R.id.buttonExcluir);

        //CRIANDO O BOTÃO PARA EDITAR UM REGISTRO CADASTRADO
        Button buttonEditarCliente            = (Button)   viewLinhaLista.findViewById(R.id.buttonEditarCliente);

        //SETANDO DADOS PARA MOSTRAR NA TELA
        textViewCodigo.setText(String.valueOf(clienteModels.get(position).getCodigo()));
        textViewNome.setText(clienteModels.get(position).getNome());
        textViewRua.setText(clienteModels.get(position).getRua());
        textViewNumero.setText(clienteModels.get(position).getNumero());
        textViewBairro.setText(clienteModels.get(position).getBairro());
        textViewCidade.setText(clienteModels.get(position).getCidade());
        textViewPais.setText(clienteModels.get(position).getPais());
        textViewCpf.setText(clienteModels.get(position).getCpf());
        textViewOrcamento.setText(clienteModels.get(position).getOrcamento());
        textViewFone.setText(clienteModels.get(position).getFone());

        //CRIANDO EVENTO CLICK PARA O BOTÃO DE EXCLUIR REGISTRO
        buttonExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //EXCLUINDO UM REGISTRO
                clienteRepository.Excluir(clienteModels.get(position).getCodigo());

                //MOSTRA A MENSAGEM APÓS EXCLUIR UM REGISTRO
                Toast.makeText(consultarClienteActivity, "Registro excluido com sucesso!", Toast.LENGTH_LONG).show();

                //CHAMA O MÉTODO QUE ATUALIZA A LISTA COM OS REGISTROS QUE AINDA ESTÃO NA BASE
                AtualizarLista();

            }
        });

        //CRIANDO EVENTO CLICK PARA O BOTÃO QUE VAI REDIRECIONAR PARA A TELA DE EDIÇÃO
        // DO REGISTRO.
        buttonEditarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRedirecionar = new Intent(consultarClienteActivity, EditarClienteActivity.class);
                intentRedirecionar.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intentRedirecionar.putExtra("id_pessoaCliente",clienteModels.get(position).getCodigo());
                consultarClienteActivity.startActivity(intentRedirecionar);
                consultarClienteActivity.finish();
            }
        });
        return viewLinhaLista;
    }

    //ATUALIZA A LISTTA DEPOIS DE EXCLUIR UM REGISTRO
    public void AtualizarLista(){
        this.clienteModels.clear();
        this.clienteModels = clienteRepository.SelecionarTodosClientes();
        this.notifyDataSetChanged();
    }
}
