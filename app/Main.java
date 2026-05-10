package app;

import java.util.Scanner;

import colecao.IColecao;
import arvorebinaria.ArvoreBinaria;
import dominio.ComparadorAlunoNome;
import dominio.Aluno;

public class Main {

    public static void main(String[] args) {

        IColecao<Aluno> arvore;

        arvore = new ArvoreBinaria<Aluno>(
            new ComparadorAlunoNome()
        );

        Aluno a;

        int mat;
        int nota;
        int opcao;

        String nome;

        Scanner scanner = new Scanner(System.in);

        try {

            do {

                System.out.println("\n1 - Adicionar aluno");
                System.out.println("2 - Pesquisar aluno");
                System.out.println("3 - Remover aluno");
                System.out.println("4 - Imprimir árvore");
                System.out.println("5 - Caminhar em nível");
                System.out.println("6 - Mostrar altura");
                System.out.println("7 - Mostrar quantidade de nós");
                System.out.println("0 - Sair");

                opcao = scanner.nextInt();
                scanner.nextLine();

                switch(opcao){

                    case 1:

                        System.out.println(
                            "Digite a matrícula do aluno"
                        );

                        mat = scanner.nextInt();
                        scanner.nextLine();

                        System.out.println(
                            "Digite o nome do aluno"
                        );

                        nome = scanner.nextLine();

                        System.out.println(
                            "Digite a nota do aluno"
                        );

                        nota = scanner.nextInt();

                        a = new Aluno(mat, nome, nota);

                        arvore.adicionar(a);

                        System.out.println(
                            "Aluno adicionado!"
                        );

                        break;

                    case 2:

                        System.out.println(
                            "Digite o nome do aluno a ser procurado"
                        );

                        nome = scanner.nextLine();

                        a = arvore.pesquisar(
                            new Aluno(0, nome, 0)
                        );

                        if (a == null) {

                            System.out.println(
                                "Aluno não encontrado"
                            );
                        }
                        else {

                            System.out.println(
                                "Aluno encontrado:"
                            );

                            System.out.println(a);
                        }

                        break;

                    case 3:

                        System.out.println(
                            "Digite o nome do aluno a ser removido"
                        );

                        nome = scanner.nextLine();

                        boolean removido =
                            arvore.remover(
                                new Aluno(0, nome, 0)
                            );

                        if (removido) {

                            System.out.println(
                                "Aluno removido"
                            );
                        }
                        else {

                            System.out.println(
                                "Aluno não encontrado"
                            );
                        }

                        break;

                    case 4:

                        System.out.println(
                            "Árvore em ordem:"
                        );

                        System.out.println(arvore);

                        break;

                    case 5:

                        System.out.println(
                            "Caminhamento em nível:"
                        );

                        System.out.println(
                            ((ArvoreBinaria<Aluno>) arvore)
                            .caminharEmNivel()
                        );

                        break;

                    case 6:

                        System.out.println(
                            "Altura da árvore:"
                        );

                        System.out.println(
                            ((ArvoreBinaria<Aluno>) arvore)
                            .altura()
                        );

                        break;

                    case 7:

                        System.out.println(
                            "Quantidade de nós:"
                        );

                        System.out.println(
                            arvore.quantidadeNos()
                        );

                        break;

                    case 0:

                        System.out.println(
                            "Programa encerrado."
                        );

                        break;

                    default:

                        System.out.println(
                            "Opção inválida!"
                        );
                }

            } while(opcao != 0);

            scanner.close();

        } catch (Exception e) {

            scanner.close();

            System.out.println(
                "ERRO! " + e.getMessage()
            );
        }
    }
}