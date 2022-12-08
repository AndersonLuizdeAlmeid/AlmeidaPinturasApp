package com.example.almeidapinturasapp.Utils;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseUtilFuncionario extends SQLiteOpenHelper {

    //NOME DA BASE DE DADOS
    private static final String NOME_BASE_DE_DADOS   = "ALMEIDA.db";

    //VERSÃO DO BANCO DE DADOS
    private static final int    VERSAO_BASE_DE_DADOS = 5;

    //CONSTRUTOR
    public DatabaseUtilFuncionario(Context context){

        super(context,NOME_BASE_DE_DADOS,null,VERSAO_BASE_DE_DADOS);
    }

    /*NA INICIALIZAÇÃO DA CLASSE VAMOS CRIAR A TABELA QUE VAMOS USAR*/
    @Override
    public void onCreate(SQLiteDatabase db) {

        String stringBuilderCreateTable = " CREATE TABLE tb_appAlmeida (" +
                "        id_pessoa      INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "        ds_nome             TEXT    ,             " +
                "        ds_rua              TEXT    ,             " +
                "        ds_numero           TEXT    ,             " +
                "        ds_bairro           TEXT    ,             " +
                "        ds_cidade           TEXT    ,             " +
                "        ds_pais             TEXT    ,             " +
                "        ds_cpf              TEXT    ,             " +
                "        ds_salario          TEXT    ,             " +
                "        ds_fone             TEXT    )             ";

        db.execSQL(stringBuilderCreateTable);

        stringBuilderCreateTable = " CREATE TABLE tb_appAlmeidaCliente (" +
                "        id_pessoaCliente      INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "        ds_nome             TEXT    ,             " +
                "        ds_rua              TEXT    ,             " +
                "        ds_numero           TEXT    ,             " +
                "        ds_bairro           TEXT    ,             " +
                "        ds_cidade           TEXT    ,             " +
                "        ds_pais             TEXT    ,             " +
                "        ds_cpf              TEXT    ,             " +
                "        ds_orcamento        TEXT    ,             " +
                "        ds_fone             TEXT    )             ";

        db.execSQL(stringBuilderCreateTable);
    }

    /*SE TROCAR A VERSÃO DO BANCO DE DADOS VOCÊ PODE EXECUTAR ALGUMA ROTINA
      COMO CRIAR COLUNAS, EXCLUIR ENTRE OUTRAS */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS tb_appAlmeida");
        onCreate(db);

    }

    /*MÉTODO QUE VAMOS USAR NA CLASSE QUE VAI EXECUTAR AS ROTINAS NO
    BANCO DE DADOS*/
    public SQLiteDatabase GetConexaoDataBase(){

        return this.getWritableDatabase();
    }
}
