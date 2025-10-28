package br.com.fiap.entities;

public class Endereco {

    //visibilidade | tipo de dados | atributo
    private String logradouro;
    private int numero;
    private String cep;
    private String bairro;
    private String cidade;


    //metodo construtor vazio
    public Endereco() {
    }



    //metodos setters (entrada) e getters (retornar \ exibir)
    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @Override
    public String toString() {
        return "\n\nEndereço" +
                "\nlogradouro='" + logradouro + '\'' +
                "\nnumero=" + numero +
                "\ncep='" + cep + '\'' +
                "\nbairro='" + bairro + '\'' +
                "\ncidade='" + cidade + '\'' +
                '\n';
    }
}
