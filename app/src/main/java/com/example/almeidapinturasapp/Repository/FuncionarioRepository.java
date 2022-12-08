package com.example.almeidapinturasapp.Repository;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import com.example.almeidapinturasapp.Utils.DatabaseUtilFuncionario;
import com.example.almeidapinturasapp.Model.FuncionarioModel;

public class FuncionarioRepository {

    DatabaseUtilFuncionario databaseUtil;

    public FuncionarioRepository(Context context){

        /***
         * CONSTRUTOR
         * @param context
         */

        databaseUtil =  new DatabaseUtilFuncionario(context);

    }

    /***
     * SALVA UM NOVO REGISTRO NA BASE DE DADOS
     * @param funcionarioModel
     */

    public void Salvar(FuncionarioModel funcionarioModel){

        ContentValues contentValues =  new ContentValues();
        /*MONTANDO OS PARAMETROS PARA SEREM SALVOS*/
        contentValues.put("ds_nome",                  funcionarioModel.getNome());
        contentValues.put("ds_cpf",                   funcionarioModel.getCpf());
        contentValues.put("ds_salario",               funcionarioModel.getSalario());
        contentValues.put("ds_rua",                   funcionarioModel.getRua());
        contentValues.put("ds_numero",                funcionarioModel.getNumero());
        contentValues.put("ds_bairro",                funcionarioModel.getBairro());
        contentValues.put("ds_cidade",                funcionarioModel.getCidade());
        contentValues.put("ds_pais",                  funcionarioModel.getPais());
        contentValues.put("ds_fone",                  funcionarioModel.getFone());

        /*EXECUTANDO INSERT DE UM NOVO REGISTRO*/
        databaseUtil.GetConexaoDataBase().insert("tb_appAlmeida",null,contentValues);

    }

    /***
     * ATUALIZA UM REGISTRO JÁ EXISTENTE NA BASE
     * @param funcionarioModel
     */

    public void Atualizar(FuncionarioModel funcionarioModel){

        ContentValues contentValues =  new ContentValues();

        /*MONTA OS PARAMENTROS PARA REALIZAR UPDATE NOS CAMPOS*/
        contentValues.put("ds_nome",                  funcionarioModel.getNome());
        contentValues.put("ds_cpf",                   funcionarioModel.getCpf());
        contentValues.put("ds_salario",               funcionarioModel.getSalario());
        contentValues.put("ds_rua",                   funcionarioModel.getRua());
        contentValues.put("ds_numero",                funcionarioModel.getNumero());
        contentValues.put("ds_bairro",                funcionarioModel.getBairro());
        contentValues.put("ds_cidade",                funcionarioModel.getCidade());
        contentValues.put("ds_pais",                  funcionarioModel.getPais());
        contentValues.put("ds_fone",                  funcionarioModel.getFone());


        /*REALIZANDO UPDATE PELA CHAVE DA TABELA*/
        databaseUtil.GetConexaoDataBase().update("tb_appAlmeida", contentValues, "id_pessoa = ?", new String[]{Integer.toString(funcionarioModel.getCodigo())});
    }

    /***
     * EXCLUI UM REGISTRO PELO CÓDIGO
     * @param codigo
     * @return
     */

    public Integer Excluir(int codigo){

        //EXCLUINDO  REGISTRO E RETORNANDO O NÚMERO DE LINHAS AFETADAS
        return databaseUtil.GetConexaoDataBase().delete("tb_appAlmeida","id_pessoa = ?", new String[]{Integer.toString(codigo)});
    }

    /***
     * CONSULTA UMA PESSOA CADASTRADA PELO CÓDIGO
     * @param codigo
     * @return
     */

    @SuppressLint("Range")
    public FuncionarioModel GetFuncionario(int codigo){


        Cursor cursor =  databaseUtil.GetConexaoDataBase().rawQuery("SELECT * FROM tb_appAlmeida WHERE id_pessoa = "+ codigo,null);

        cursor.moveToFirst();

        ///CRIANDO UMA NOVA PESSOAS
        FuncionarioModel funcionarioModel =  new FuncionarioModel();

        //ADICIONANDO OS DADOS DA PESSOA
        funcionarioModel.setCodigo(cursor.getInt(cursor.getColumnIndex("id_pessoa")));
        funcionarioModel.setNome(cursor.getString(cursor.getColumnIndex("ds_nome")));
        funcionarioModel.setCpf(cursor.getString(cursor.getColumnIndex("ds_cpf")));
        funcionarioModel.setSalario(cursor.getString(cursor.getColumnIndex("ds_salario")));
        funcionarioModel.setRua(cursor.getString(cursor.getColumnIndex("ds_rua")));
        funcionarioModel.setNumero(cursor.getString(cursor.getColumnIndex("ds_numero")));
        funcionarioModel.setBairro(cursor.getString(cursor.getColumnIndex("ds_bairro")));
        funcionarioModel.setCidade(cursor.getString(cursor.getColumnIndex("ds_cidade")));
        funcionarioModel.setPais(cursor.getString(cursor.getColumnIndex("ds_pais")));
        funcionarioModel.setFone(cursor.getString(cursor.getColumnIndex("ds_fone")));

        //RETORNANDO A PESSOA
        return funcionarioModel;

    }

    /***
     * CONSULTA TODAS AS PESSOAS CADASTRADAS NA BASE
     * @return
     */


    public List<FuncionarioModel> SelecionarTodosFuncionarios(){

        List<FuncionarioModel> funcionarios = new ArrayList<FuncionarioModel>();


        //MONTA A QUERY A SER EXECUTADA
        StringBuilder stringBuilderQuery = new StringBuilder();
        stringBuilderQuery.append(" SELECT id_pessoa,           ");
        stringBuilderQuery.append("        ds_nome,             ");
        stringBuilderQuery.append("        ds_rua,              ");
        stringBuilderQuery.append("        ds_numero,           ");
        stringBuilderQuery.append("        ds_bairro,           ");
        stringBuilderQuery.append("        ds_cidade,           ");
        stringBuilderQuery.append("        ds_pais,             ");
        stringBuilderQuery.append("        ds_cpf,              ");
        stringBuilderQuery.append("        ds_salario,          ");
        stringBuilderQuery.append("        ds_fone              ");
        stringBuilderQuery.append("  FROM  tb_appAlmeida        ");
        stringBuilderQuery.append(" ORDER BY id_pessoa          ");


        //CONSULTANDO OS REGISTROS CADASTRADOS
        Cursor cursor = databaseUtil.GetConexaoDataBase().rawQuery(stringBuilderQuery.toString(), null);

        /*POSICIONA O CURSOR NO PRIMEIRO REGISTRO*/
        cursor.moveToFirst();


        FuncionarioModel funcionarioModel;

        //REALIZA A LEITURA DOS REGISTROS ENQUANTO NÃO FOR O FIM DO CURSOR
        while (!cursor.isAfterLast()){

            /* CRIANDO UMA NOVA PESSOAS */
            funcionarioModel =  new FuncionarioModel();

            //ADICIONANDO OS DADOS DA PESSOA

            funcionarioModel.setCodigo(cursor.getInt(cursor.getColumnIndex("id_pessoa")));
            funcionarioModel.setNome(cursor.getString(cursor.getColumnIndex("ds_nome")));
            funcionarioModel.setRua(cursor.getString(cursor.getColumnIndex("ds_rua")));
            funcionarioModel.setNumero(cursor.getString(cursor.getColumnIndex("ds_numero")));
            funcionarioModel.setBairro(cursor.getString(cursor.getColumnIndex("ds_bairro")));
            funcionarioModel.setCidade(cursor.getString(cursor.getColumnIndex("ds_cidade")));
            funcionarioModel.setPais(cursor.getString(cursor.getColumnIndex("ds_pais")));
            funcionarioModel.setCpf(cursor.getString(cursor.getColumnIndex("ds_cpf")));
            funcionarioModel.setSalario(cursor.getString(cursor.getColumnIndex("ds_salario")));
            funcionarioModel.setFone(cursor.getString(cursor.getColumnIndex("ds_fone")));

            //ADICIONANDO UMA PESSOA NA LISTA
            funcionarios.add(funcionarioModel);

            //VAI PARA O PRÓXIMO REGISTRO
            cursor.moveToNext();
        }

        //RETORNANDO A LISTA DE PESSOAS
        return funcionarios;

    }
}
