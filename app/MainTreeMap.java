package app;

import dominio.Aluno;
import dominio.ComparadorAlunoNome;

import java.util.Scanner;
import java.util.TreeMap;

public class MainTreeMap {

    public static void main(String[] args) {

        TreeMap<Aluno, Aluno> mapa =
            new TreeMap<>(new ComparadorAlunoNome());

        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        do {
            try {
                System.out.println("\n1 - Adicionar aluno");
                System.out.println("2 - Pesquisar aluno");
                System.out.println("3 - Remover aluno");
                System.out.println("4 - Imprimir todos");
                System.out.println("5 - Quantidade de alunos");
                System.out.println("0 - Sair");
                System.out.print("Opção: ");

                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {

                    case 1:
                        System.out.print("Matrícula: ");
                        int mat = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();

                        if (nome.trim().isEmpty()) {
                            System.out.println(
                                "ERRO: nome não pode ser vazio!"
                            );
                            break;
                        }

                        System.out.print("Nota (0-100): ");
                        int nota = scanner.nextInt();

                        if (nota < 0 || nota > 100) {
                            System.out.println(
                                "ERRO: nota deve ser entre 0 e 100!"
                            );
                            break;
                        }

                        Aluno novo = new Aluno(mat, nome, nota);

                        if (mapa.containsKey(novo)) {
                            System.out.println(
                                "ERRO: já existe aluno com esse nome!"
                            );
                        } else {
                            mapa.put(novo, novo);
                            System.out.println("Aluno adicionado!");
                        }
                        break;

                    case 2:
                        if (mapa.isEmpty()) {
                            System.out.println(
                                "ERRO: árvore vazia, nada a pesquisar!"
                            );
                            break;
                        }

                        System.out.print("Nome a buscar: ");
                        String nomeBusca = scanner.nextLine();

                        if (nomeBusca.trim().isEmpty()) {
                            System.out.println(
                                "ERRO: digite um nome para buscar!"
                            );
                            break;
                        }

                        Aluno resultado = mapa.get(
                            new Aluno(0, nomeBusca, 0)
                        );

                        if (resultado == null) {
                            System.out.println(
                                "Aluno \"" + nomeBusca
                                + "\" não encontrado."
                            );
                        } else {
                            System.out.println(
                                "Encontrado: " + resultado
                            );
                        }
                        break;

                    case 3:
                        if (mapa.isEmpty()) {
                            System.out.println(
                                "ERRO: árvore vazia, nada a remover!"
                            );
                            break;
                        }

                        System.out.print("Nome a remover: ");
                        String nomeRem = scanner.nextLine();

                        if (nomeRem.trim().isEmpty()) {
                            System.out.println(
                                "ERRO: digite um nome para remover!"
                            );
                            break;
                        }

                        Aluno removido = mapa.remove(
                            new Aluno(0, nomeRem, 0)
                        );

                        if (removido == null) {
                            System.out.println(
                                "Aluno \"" + nomeRem
                                + "\" não encontrado."
                            );
                        } else {
                            System.out.println(
                                "Removido: " + removido
                            );
                        }
                        break;

                    case 4:
                        if (mapa.isEmpty()) {
                            System.out.println(
                                "Árvore vazia!"
                            );
                        } else {
                            System.out.println(
                                "\nAlunos em ordem alfabética:"
                            );
                            mapa.values().forEach(
                                System.out::println
                            );
                        }
                        break;

                    case 5:
                        System.out.println(
                            "Total de alunos: " + mapa.size()
                        );
                        break;

                    case 0:
                        System.out.println("Encerrando.");
                        break;

                    default:
                        System.out.println(
                            "ERRO: opção inválida! Digite de 0 a 5."
                        );
                }

            } catch (java.util.InputMismatchException e) {
                System.out.println(
                    "ERRO: entrada inválida! "
                    + "Digite apenas números onde pedido."
                );
                scanner.nextLine(); // limpa o buffer
                opcao = -1; // evita sair do loop
            } catch (Exception e) {
                System.out.println(
                    "ERRO inesperado: " + e.getMessage()
                );
                scanner.nextLine();
                opcao = -1;
            }

        } while (opcao != 0);

        scanner.close();
    }
}