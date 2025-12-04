package com.gestaoentregas.dados.beans;

public abstract class Usuario {
    // Atributos comuns para Login/Identificação
    private String email;
    private String senha;
    private TipoUsuario tipo;
    private int id;

    // Construtor
    public Usuario(String email, String senha, TipoUsuario tipo, int id) {
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
        this.id = id;
    }

    // Getters e setters obrigatórios para o Login/Serviço
    public String getEmail() { return email; }
    public String getSenha() { return senha; }
    public TipoUsuario getTipo() { return tipo; }
    public int getId() { return id; }
    public void setEmail(String email) { this.email = email; }
    public void setSenha(String senha) { this.senha = senha; }
    public void setTipo(TipoUsuario tipo) { this.tipo = tipo;}
    public void setId(int id){ this.id = id;}

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Usuario usuario = (Usuario) obj;
        return this.id == usuario.id;
    }
}
