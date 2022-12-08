package com.example.almeidapinturasapp.Repository;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import com.example.almeidapinturasapp.Model.ClienteModel;
import com.example.almeidapinturasapp.Utils.DatabaseUtilCliente;
import com.example.almeidapinturasapp.Utils.DatabaseUtilFuncionario;

public class ClienteRepository {

    DatabaseUtilCliente databaseUtil;

    public ClienteRepository(Context context){

        /***
         * CONSTRUTOR
         * @param context
         */

        databaseUtil =  new DatabaseUtilCliente(context);

    }

    /***
     * SALVA UM NOVO REGISTRO NA BASE DE DADOS
     * @param clienteModel
     */

    public void Salvar(ClienteModel clienteModel){

        ContentValues contentValues =  new ContentValues();
        /*MONTANDO OS PARAMETROS PARA SEREM SALVOS*/
        contentValues.put("ds_nome",                  clienteModel.getNome());
        contentValues.put("ds_rua",                   clienteModel.getRua());
        contentValues.put("ds_numero",                clienteModel.getNumero());
        contentValues.put("ds_bairro",                clienteModel.getBairro());
        contentValues.put("ds_cidade",                clienteModel.getCidade());
        contentValues.put("ds_pais",                  clienteModel.getPais());
        contentValues.put("ds_cpf",                   clienteModel.getCpf());
        contentValues.put("ds_orcamento",             clienteModel.getOrcamento());
        contentValues.put("ds_fone",                  clienteModel.getFone());

        /*EXECUTANDO INSERT DE UM NOVO REGISTRO*/
        databaseUtil.GetConexaoDataBase().insert("tb_appAlmeidaCliente",null,contentValues);

    }

    /***
     * ATUALIZA UM REGISTRO JÁ EXISTENTE NA BASE
     * @param clienteModel
     */

    public void Atualizar(ClienteModel clienteModel){

        ContentValues contentValues =  new ContentValues();

        /*MONTA OS PARAMENTROS PARA REALIZAR UPDATE NOS CAMPOS*/
        contentValues.put("ds_nome",           clienteModel.getNome());
        contentValues.put("ds_rua",                   clienteModel.getRua());
        contentValues.put("ds_numero",                clienteModel.getNumero());
        contentValues.put("ds_bairro",                clienteModel.getBairro());
        contentValues.put("ds_cidade",                clienteModel.getCidade());
        contentValues.put("ds_pais",                  clienteModel.getPais());
        contentValues.put("ds_cpf",                   clienteModel.getCpf());
        contentValues.put("ds_orcamento",             clienteModel.getOrcamento());
        contentValues.put("ds_fone",                  clienteModel.getFone());

        /*REALIZANDO UPDATE PELA CHAVE DA TABELA*/
        databaseUtil.GetConexaoDataBase().update("tb_appAlmeidaCliente", contentValues, "id_pessoaCliente = ?", new String[]{Integer.toString(clienteModel.getCodigo())});
    }

    /***
     * EXCLUI UM REGISTRO PELO CÓDIGO
     * @param codigo
     * @return
     */

    public Integer Excluir(int codigo){

        //EXCLUINDO  REGISTRO E RETORNANDO O NÚMERO DE LINHAS AFETADAS
        return databaseUtil.GetConexaoDataBase().delete("tb_appAlmeidaCliente","id_pessoaCliente = ?", new String[]{Integer.toString(codigo)});
    }

    /***
     * CONSULTA UMA PESSOA CADASTRADA PELO CÓDIGO
     * @param codigo
     * @return
     */

    @SuppressLint("Range")
    public ClienteModel GetCliente(int codigo){


        Cursor cursor =  databaseUtil.GetConexaoDataBase().rawQuery("SELECT * FROM tb_appAlmeidaCliente WHERE id_pessoaCliente = "+ codigo,null);

        cursor.moveToFirst();

        ///CRIANDO UMA NOVA PESSOAS
        ClienteModel clienteModel =  new ClienteModel();

        //ADICIONANDO OS DADOS DA PESSOA
        clienteModel.setCodigo(cursor.getInt(cursor.getColumnIndex("id_pessoaCliente")));
        clienteModel.setNome(cursor.getString(cursor.getColumnIndex("ds_nome")));
        clienteModel.setRua(cursor.getString(cursor.getColumnIndex("ds_rua")));
        clienteModel.setNumero(cursor.getString(cursor.getColumnIndex("ds_numero")));
        clienteModel.setBairro(cursor.getString(cursor.getColumnIndex("ds_bairro")));
        clienteModel.setCidade(cursor.getString(cursor.getColumnIndex("ds_cidade")));
        clienteModel.setPais(cursor.getString(cursor.getColumnIndex("ds_pais")));
        clienteModel.setCpf(cursor.getString(cursor.getColumnIndex("ds_cpf")));
        clienteModel.setOrcamento(cursor.getString(cursor.getColumnIndex("ds_orcamento")));
        clienteModel.setFone(cursor.getString(cursor.getColumnIndex("ds_fone")));

        //RETORNANDO A PESSOA
        return clienteModel;

    }

    /***
     * CONSULTA TODAS AS PESSOAS CADASTRADAS NA BASE
     * @return
     */
    public List<ClienteModel> SelecionarTodosClientes(){

        List<ClienteModel> clientes = new ArrayList<ClienteModel>();


        //MONTA A QUERY A SER EXECUTADA
        StringBuilder stringBuilderQuery = new StringBuilder();
        stringBuilderQuery.append(" SELECT id_pessoaCliente,           ");
        stringBuilderQuery.append("        ds_nome,             ");
        stringBuilderQuery.append("        ds_rua,              ");
        stringBuilderQuery.append("        ds_numero,           ");
        stringBuilderQuery.append("        ds_bairro,           ");
        stringBuilderQuery.append("        ds_cidade,           ");
        stringBuilderQuery.append("        ds_pais,             ");
        stringBuilderQuery.append("        ds_cpf,              ");
        stringBuilderQuery.append("        ds_orcamento,        ");
        stringBuilderQuery.append("        ds_fone              ");
        stringBuilderQuery.append("  FROM  tb_appAlmeidaCliente ");
        stringBuilderQuery.append(" ORDER BY id_pessoaCliente   ");


        //CONSULTANDO OS REGISTROS CADASTRADOS
        Cursor cursor = databaseUtil.GetConexaoDataBase().rawQuery(stringBuilderQuery.toString(), null);

        /*POSICIONA O CURSOR NO PRIMEIRO REGISTRO*/
        cursor.moveToFirst();


        ClienteModel clienteModel;

        //REALIZA A LEITURA DOS REGISTROS ENQUANTO NÃO FOR O FIM DO CURSOR
        while (!cursor.isAfterLast()){

            /* CRIANDO UMA NOVA PESSOAS */
            clienteModel =  new ClienteModel();

            //ADICIONANDO OS DADOS DA PESSOA

            clienteModel.setCodigo(cursor.getInt(cursor.getColumnIndex("id_pessoaCliente")));
            clienteModel.setNome(cursor.getString(cursor.getColumnIndex("ds_nome")));
            clienteModel.setRua(cursor.getString(cursor.getColumnIndex("ds_rua")));
            clienteModel.setNumero(cursor.getString(cursor.getColumnIndex("ds_numero")));
            clienteModel.setBairro(cursor.getString(cursor.getColumnIndex("ds_bairro")));
            clienteModel.setCidade(cursor.getString(cursor.getColumnIndex("ds_cidade")));
            clienteModel.setPais(cursor.getString(cursor.getColumnIndex("ds_pais")));
            clienteModel.setCpf(cursor.getString(cursor.getColumnIndex("ds_cpf")));
            clienteModel.setOrcamento(cursor.getString(cursor.getColumnIndex("ds_orcamento")));
            clienteModel.setFone(cursor.getString(cursor.getColumnIndex("ds_fone")));

            //ADICIONANDO UMA PESSOA NA LISTA
            clientes.add(clienteModel);

            //VAI PARA O PRÓXIMO REGISTRO
            cursor.moveToNext();
        }

        //RETORNANDO A LISTA DE PESSOAS
        return clientes;

    }
}
