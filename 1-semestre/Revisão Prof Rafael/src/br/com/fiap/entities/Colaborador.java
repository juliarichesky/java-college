package br.com.fiap.entities;

public class Colaborador extends Pessoa {

    //visibilidade | tipo de dados | atributo
    private String cargo;
    private int idade;
    private double qtdHoras;
    private double valorHora;
    private double percentual;
    private Endereco endereco; //atributo de referencia

    //metodo construtor - vem predefinido algumas coisas
    public Colaborador(String nome, String cpf, String cargo, int idade, double qtdHoras, double valorHora, double percentual) {
        super(nome, cpf);
        this.cargo = cargo;
        this.idade = idade;
        this.qtdHoras = qtdHoras;
        this.valorHora = valorHora;
        this.percentual = percentual;
    }

    //metodos setters (entrada) e getters (retornar \ exibir)
    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public double getQtdHoras() {
        return qtdHoras;
    }

    public void setQtdHoras(double qtdHoras) {
        this.qtdHoras = qtdHoras;
    }

    public double getValorHora() {
        return valorHora;
    }

    public void setValorHora(double valorHora) {
        this.valorHora = valorHora;
    }

    public double getPercentual() {
        return percentual;
    }

    public void setPercentual(double percentual) {
        this.percentual = percentual;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Colaborador" +
                "\ncargo='" + cargo + '\'' +
                "\nidade=" + idade +
                "\nqtdHoras=" + qtdHoras +
                "\nvalorHora=" + valorHora +
                "\npercentual=" + percentual +
                 endereco +
                "\n" + super.toString();
    }
}