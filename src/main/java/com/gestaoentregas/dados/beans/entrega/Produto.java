package com.gestaoentregas.dados.beans.entrega;


public class Produto {
    private String nome;
    private String codigo;
    private double valor;

    public Produto(String nome, String codigo, double valor) {
        this.nome = nome;
        this.codigo = codigo;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public  void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return String.format("Produto [Nome: %s, Código: %s, Valor: R$ %.2f]", nome, codigo, valor);
    }
}