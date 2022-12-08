package com.example.almeidapinturasapp.Utils;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.almeidapinturasapp.ConsultarClienteActivity;
import com.example.almeidapinturasapp.ConsultarFuncionarioActivity;
import com.example.almeidapinturasapp.EditarFuncionarioActivity;
import com.example.almeidapinturasapp.Model.ClienteModel;
import com.example.almeidapinturasapp.Model.FuncionarioModel;
import com.example.almeidapinturasapp.R;
import com.example.almeidapinturasapp.Repository.ClienteRepository;
import com.example.almeidapinturasapp.Repository.FuncionarioRepository;

import java.util.ArrayList;
import java.util.List;

public class ConsultarFuncionarioAdapter extends BaseAdapter {

    //CRIANDO UM OBJETO LayoutInflater PARA FAZER LINK A NOSSA VIEW(activity_linha_consultar.xml)
    private static LayoutInflater layoutInflater = null;

    //CRIANDO UMA LISTA DE PESSOAS
    List<FuncionarioModel> funcionarioModels =  new ArrayList<FuncionarioModel>();

    //CIRANDO UM OBJETO DA NOSSA CLASSE QUE FAZ ACESSO AO BANCO DE DADOS
    FuncionarioRepository funcionarioRepository;

    //CRIANDO UM OBJETO DA NOSSA ATIVIDADE QUE CONTEM A LISTA
    private ConsultarFuncionarioActivity consultarFuncionarioActivity;

    //CONSTRUTOR QUE VAI RECEBER A NOSSA ATIVIDADE COMO PARAMETRO E A LISTA DE PESSOAS QUE VAI RETORNAR
    //DA NOSSA BASE DE DADOS
    public ConsultarFuncionarioAdapter(ConsultarFuncionarioActivity consultarFuncionarioActivity, List<FuncionarioModel> funcionarioModels) {

        this.funcionarioModels             =  funcionarioModels;
        this.consultarFuncionarioActivity  =  consultarFuncionarioActivity;
        this.layoutInflater                = (LayoutInflater) this.consultarFuncionarioActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.funcionarioRepository         = new FuncionarioRepository(consultarFuncionarioActivity);
    }

    //RETORNA A QUANTIDADE DE REGISTROS DA LISTA
    @Override
    public int getCount(){

        return funcionarioModels.size();
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
        final View viewLinhaLista = layoutInflater.inflate(R.layout.activity_consultar_funcionario_adapter,null);

        //VINCULANDO OS CAMPOS DO ARQUIVO DE LAYOUT(activity_linha_consultar.xml) AOS OBJETOS DECLARADOS.

        //CAMPO QUE VAI MOSTRAR O CÓDIGO DA PESSOA
        TextView textViewCodigo          = (TextView) viewLinhaLista.findViewById(R.id.textViewCodigo);

        //CAMPO QUE VAI MOSTRAR O NOME DA PESSOA
        TextView textViewNomeFuncionario = (TextView) viewLinhaLista.findViewById(R.id.textViewNomeFuncionario);

        //CAMPO QUE VAI MOSTRAR O ENDEREÇO DA PESSOA
        TextView textViewRua        = (TextView) viewLinhaLista.findViewById(R.id.textViewRua);

        TextView textViewNumero        = (TextView) viewLinhaLista.findViewById(R.id.textViewNumero);
        TextView textViewBairro        = (TextView) viewLinhaLista.findViewById(R.id.textViewBairro);
        TextView textViewCidade        = (TextView) viewLinhaLista.findViewById(R.id.textViewCidade);
        TextView textViewPais          = (TextView) viewLinhaLista.findViewById(R.id.textViewPais);
        TextView textViewCpf           = (TextView) viewLinhaLista.findViewById(R.id.textViewCPF);
        TextView textViewSalario       = (TextView) viewLinhaLista.findViewById(R.id.textViewSalario);
        TextView textViewFone          = (TextView) viewLinhaLista.findViewById(R.id.textViewFone);

        //CRIANDO O BOTÃO  EXCLUIR PARA DELETARMOS UM REGISTRO DO BANCO DE DADOS
        Button buttonExcluir             = (Button)   viewLinhaLista.findViewById(R.id.buttonExcluir);

        //CRIANDO O BOTÃO PARA EDITAR UM REGISTRO CADASTRADO
        Button buttonEditar            = (Button)   viewLinhaLista.findViewById(R.id.buttonEditar);

        //SETANDO DADOS PARA MOSTRAR NA TELA
        textViewCodigo.setText(String.valueOf(funcionarioModels.get(position).getCodigo()));
        textViewNomeFuncionario.setText(funcionarioModels.get(position).getNome());
        textViewRua.setText(funcionarioModels.get(position).getRua());
        textViewNumero.setText(funcionarioModels.get(position).getNumero());
        textViewBairro.setText(funcionarioModels.get(position).getBairro());
        textViewCidade.setText(funcionarioModels.get(position).getCidade());
        textViewPais.setText(funcionarioModels.get(position).getPais());
        textViewCpf.setText(funcionarioModels.get(position).getCpf());
        textViewSalario.setText(funcionarioModels.get(position).getSalario());
        textViewFone.setText(funcionarioModels.get(position).getFone());

        //CRIANDO EVENTO CLICK PARA O BOTÃO DE EXCLUIR REGISTRO
        buttonExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //EXCLUINDO UM REGISTRO
                funcionarioRepository.Excluir(funcionarioModels.get(position).getCodigo());

                //MOSTRA A MENSAGEM APÓS EXCLUIR UM REGISTRO
                Toast.makeText(consultarFuncionarioActivity, "Registro excluido com sucesso!", Toast.LENGTH_LONG).show();

                //CHAMA O MÉTODO QUE ATUALIZA A LISTA COM OS REGISTROS QUE AINDA ESTÃO NA BASE
                AtualizarLista();

            }
        });

        //CRIANDO EVENTO CLICK PARA O BOTÃO QUE VAI REDIRECIONAR PARA A TELA DE EDIÇÃO
        // DO REGISTRO.
        buttonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRedirecionar = new Intent(consultarFuncionarioActivity, EditarFuncionarioActivity.class);
                intentRedirecionar.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intentRedirecionar.putExtra("id_pessoa",funcionarioModels.get(position).getCodigo());
                consultarFuncionarioActivity.startActivity(intentRedirecionar);
                consultarFuncionarioActivity.finish();
            }
        });
        return viewLinhaLista;
    }

    //ATUALIZA A LISTTA DEPOIS DE EXCLUIR UM REGISTRO
    public void AtualizarLista(){
        this.funcionarioModels.clear();
        this.funcionarioModels = funcionarioRepository.SelecionarTodosFuncionarios();
        this.notifyDataSetChanged();
    }
}
