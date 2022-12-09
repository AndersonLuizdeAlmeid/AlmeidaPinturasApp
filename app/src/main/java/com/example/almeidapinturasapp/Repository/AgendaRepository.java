package com.example.almeidapinturasapp.Repository;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.almeidapinturasapp.Model.AgendaModel;
import com.example.almeidapinturasapp.Utils.DatabaseUtilCliente;

import java.util.ArrayList;
import java.util.List;

public class AgendaRepository {

    DatabaseUtilCliente databaseUtil;

    public AgendaRepository(Context context){

        /***
         * CONSTRUTOR
         * @param context
         */

        databaseUtil =  new DatabaseUtilCliente(context);

    }

    /***
     * SALVA UM NOVO REGISTRO NA BASE DE DADOS
     * @param agendaModel
     */

    public void Salvar(AgendaModel agendaModel){

        ContentValues contentValues =  new ContentValues();
        /*MONTANDO OS PARAMETROS PARA SEREM SALVOS*/
        contentValues.put("ds_data", String.valueOf(agendaModel.getData()));
        contentValues.put("ds_hora", String.valueOf(agendaModel.getHora()));

        /*EXECUTANDO INSERT DE UM NOVO REGISTRO*/
        databaseUtil.GetConexaoDataBase().insert("tb_appAlmeidaAgenda",null,contentValues);

    }

    /***
     * ATUALIZA UM REGISTRO JÁ EXISTENTE NA BASE
     * @param agendaModel
     */

    public void Atualizar(AgendaModel agendaModel){

        ContentValues contentValues =  new ContentValues();

        /*MONTA OS PARAMENTROS PARA REALIZAR UPDATE NOS CAMPOS*/
        contentValues.put("ds_data", String.valueOf(agendaModel.getData()));
        contentValues.put("ds_hora", String.valueOf(agendaModel.getHora()));

        /*REALIZANDO UPDATE PELA CHAVE DA TABELA*/
        databaseUtil.GetConexaoDataBase().update("tb_appAlmeidaAgenda", contentValues, "id_agenda = ?", new String[]{Integer.toString(agendaModel.getCodigo())});
    }

    /***
     * EXCLUI UM REGISTRO PELO CÓDIGO
     * @param codigo
     * @return
     */

    public Integer Excluir(int codigo){

        //EXCLUINDO  REGISTRO E RETORNANDO O NÚMERO DE LINHAS AFETADAS
        return databaseUtil.GetConexaoDataBase().delete("tb_appAlmeidaAgenda","id_agenda= ?", new String[]{Integer.toString(codigo)});
    }

    /***
     * CONSULTA UMA PESSOA CADASTRADA PELO CÓDIGO
     * @param codigo
     * @return
     */

    @SuppressLint("Range")
    public AgendaModel GetAgenda(int codigo){


        Cursor cursor =  databaseUtil.GetConexaoDataBase().rawQuery("SELECT * FROM tb_appAlmeidaAgenda WHERE id_agenda = "+ codigo,null);

        cursor.moveToFirst();

        ///CRIANDO UMA NOVA PESSOAS
        AgendaModel agendaModel =  new AgendaModel();

        //ADICIONANDO OS DADOS DA PESSOA
        agendaModel.setCodigo(cursor.getInt(cursor.getColumnIndex("id_agenda")));
        agendaModel.setData(cursor.getString(cursor.getColumnIndex("ds_data")));
        agendaModel.setHora(cursor.getString(cursor.getColumnIndex("ds_hora")));

        //RETORNANDO A PESSOA
        return agendaModel;

    }

    /***
     * CONSULTA TODAS AS PESSOAS CADASTRADAS NA BASE
     * @return
     */
    @SuppressLint("Range")
    public List<AgendaModel> SelecionarTodosAgenda(){

        List<AgendaModel> agenda = new ArrayList<AgendaModel>();


        //MONTA A QUERY A SER EXECUTADA
        StringBuilder stringBuilderQuery = new StringBuilder();
        stringBuilderQuery.append(" SELECT id_agenda,           ");
        stringBuilderQuery.append("        ds_data,             ");
        stringBuilderQuery.append("        ds_hora              ");
        stringBuilderQuery.append("  FROM  tb_appAlmeidaAgenda  ");
        stringBuilderQuery.append(" ORDER BY id_agenda          ");


        //CONSULTANDO OS REGISTROS CADASTRADOS
        Cursor cursor = databaseUtil.GetConexaoDataBase().rawQuery(stringBuilderQuery.toString(), null);

        /*POSICIONA O CURSOR NO PRIMEIRO REGISTRO*/
        cursor.moveToFirst();


        AgendaModel agendaModel;

        //REALIZA A LEITURA DOS REGISTROS ENQUANTO NÃO FOR O FIM DO CURSOR
        while (!cursor.isAfterLast()){

            /* CRIANDO UMA NOVA PESSOAS */
            agendaModel =  new AgendaModel();

            //ADICIONANDO OS DADOS DA PESSOA

            agendaModel.setCodigo(cursor.getInt(cursor.getColumnIndex("id_agenda")));
            agendaModel.setNome(cursor.getString(cursor.getColumnIndex("ds_data")));
            agendaModel.setRua(cursor.getString(cursor.getColumnIndex("ds_hora")));

            //ADICIONANDO UMA PESSOA NA LISTA
            agenda.add(agendaModel);

            //VAI PARA O PRÓXIMO REGISTRO
            cursor.moveToNext();
        }

        //RETORNANDO A LISTA DE PESSOAS
        return agenda;

    }
}