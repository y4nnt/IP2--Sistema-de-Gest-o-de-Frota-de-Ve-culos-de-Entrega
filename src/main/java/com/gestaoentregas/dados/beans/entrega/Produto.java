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

    public String getCodigo() {
        return codigo;
    }

    public double getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return String.format("Produto [Nome: %s, Código: %s, Valor: R$ %.2f]", nome, codigo, valor);
    }
}