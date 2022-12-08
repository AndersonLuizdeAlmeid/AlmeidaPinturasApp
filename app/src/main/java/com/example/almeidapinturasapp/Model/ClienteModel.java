package com.example.almeidapinturasapp.Model;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ClienteModel extends AppCompatActivity {

    private Integer codigo;
    private String Nome;
    private String Rua;
    private String Numero;
    private String Bairro;
    private String Cidade;
    private String Pais;
    private String Cpf;
    private String Orcamento;
    private String Fone;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getRua() {
        return Rua;
    }

    public void setRua(String rua) {
        Rua = rua;
    }

    public String getNumero() {
        return Numero;
    }

    public void setNumero(String numero) {
        Numero = numero;
    }

    public String getBairro() {
        return Bairro;
    }

    public void setBairro(String bairro) {
        Bairro = bairro;
    }

    public String getCidade() {
        return Cidade;
    }

    public void setCidade(String cidade) {
        Cidade = cidade;
    }

    public String getPais() {
        return Pais;
    }

    public void setPais(String pais) {
        Pais = pais;
    }

    public String getCpf() {
        return Cpf;
    }

    public void setCpf(String cpf) {
        Cpf = cpf;
    }

    public String getOrcamento() {
        return Orcamento;
    }

    public void setOrcamento(String orcamento) {
        Orcamento = orcamento;
    }

    public String getFone() {
        return Fone;
    }

    public void setFone(String fone) {
        Fone = fone;
    }

}