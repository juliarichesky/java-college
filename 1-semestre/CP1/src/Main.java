import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        var tarefas = new ArrayList<String>();
        Scanner leitor = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== TODO LIST =====");
            System.out.println("1. Adicionar tarefa");
            System.out.println("2. Ver tarefas");
            System.out.println("3. Sair");
            System.out.println("4. Remover tarefa");
            System.out.print("Escolha uma opção: ");

            int opcao = leitor.nextInt();
            leitor.nextLine();

            if (opcao == 1) {
                System.out.print("Digite a nova tarefa: ");
                String novaTarefa = leitor.nextLine();
                tarefas.add(novaTarefa);
                System.out.println("Tarefa adicionada!");
            } else if (opcao == 2) {
                System.out.println("\nSuas Tarefas:");
                if (tarefas.isEmpty()) {
                    System.out.println("Nenhuma tarefa na lista.");
                } else {
                    int contador = 1;
                    for (String tarefa : tarefas) {
                        System.out.println(contador + ". " + tarefa);
                        contador++;
                    }
                }
            } else if (opcao == 3) {
                System.out.println("Saindo do programa. Até mais!");
                leitor.close();
                break;
            } else if (opcao == 4) {
                if (tarefas.isEmpty()) {
                    System.out.println("Nenhuma tarefa para remover.");
                } else {
                    System.out.println("\nSuas Tarefas:");
                    int contador = 1;
                    for (String tarefa : tarefas) {
                        System.out.println(contador + ". " + tarefa);
                        contador++;
                    }
                    System.out.print("Digite o NOME da tarefa para remover: ");
                    String tarefaParaRemover = leitor.nextLine();

                    if (tarefas.remove(tarefaParaRemover)) {
                        System.out.println("Tarefa removida com sucesso!");
                    } else {
                        System.out.println("Tarefa não encontrada!");
                    }
                }
            } else {
                System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }
}