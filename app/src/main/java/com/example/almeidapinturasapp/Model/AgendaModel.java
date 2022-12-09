package com.example.almeidapinturasapp.Model;

import java.sql.Time;
import java.util.Date;


public class AgendaModel extends FuncionarioModel {
    private Integer Codigo;
    private String Data;
    private String Hora;
    private FuncionarioModel funcionarioModel;


    public Integer getCodigo(){return Codigo;}
    public void setCodigo(Integer codigo) {this.Codigo = codigo;}

    public String getData(){return Data;}
    public void setData(String data) {this.Data = data;}

    public String getHora(){return Hora;}
    public void setHora(String hora) {this.Hora = hora;}

    public FuncionarioModel getFuncionarioModel() {
        return funcionarioModel;
    }

    public void setFuncionarioModel(FuncionarioModel funcionarioModel) {
        this.funcionarioModel = funcionarioModel;
    }
}
