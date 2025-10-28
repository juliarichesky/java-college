import br.com.fiap.entities.Colaborador;
import br.com.fiap.entities.Endereco;

import javax.swing.*;

public class Main {

    //psvm abre automaticamente: (é tipo o sout)
    public static void main(String[] args) {

        //instanciar objetos (precisa dar o nome da classe)
        //String nome, String cpf, String cargo, int idade, double qtdHoras, double valorHora, double percentual
        //nome, cpf
        Colaborador objColaborador = new Colaborador(
                JOptionPane.showInputDialog("Nome"),
                JOptionPane.showInputDialog("CPF"),
                JOptionPane.showInputDialog("Cargo"),
                //parseInt é pra ver se tem um numero inteiro, ele detecta
                //JO so detecta string, pra detectar numero é assim:
                Integer.parseInt(JOptionPane.showInputDialog("Idade")),
                Double.parseDouble(JOptionPane.showInputDialog("Quantidade de Horas")),
                Double.parseDouble(JOptionPane.showInputDialog("Valor da Hora Trabalhada")),
                Double.parseDouble(JOptionPane.showInputDialog("Percentual"))
        );

        //outra forma de fazer a mesma coisa (construtor)
        Endereco objEndereco = new Endereco();

        objEndereco.setLogradouro(JOptionPane.showInputDialog("Digite o logradouro:"));
        objEndereco.setNumero(222);
        objEndereco.setCep("05142-020");
        objEndereco.setBairro("Cidade Tiradentes");
        objEndereco.setCidade("São Paulo");

        //
        objColaborador.setEndereco(objEndereco);

        System.out.println(objColaborador);
    }
}