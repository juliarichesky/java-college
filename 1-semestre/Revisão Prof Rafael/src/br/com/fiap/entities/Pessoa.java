package br.com.fiap.entities;

public abstract class Pessoa {

    //visibilidade | tipo de dados | atributo
    private String nome;
    private String cpf;

    //metodo construtor
    public Pessoa(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    //metodos setters (entrada) e getters (retornar \ exibir)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "Pessoa" +
                "\nnome='" + nome + '\'' +
                "\ncpf='" + cpf + '\'' +
                '\n';
    }
}
