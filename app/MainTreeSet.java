package app;

import dominio.Aluno;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.InputMismatchException; // Import necessário
import java.util.List;

public class MainTreeSet {

    public static void main(String[] args) {

        TreeSet<Aluno> arvore = new TreeSet<>(
            (a, b) -> Integer.compare(a.getMatricula(), b.getMatricula())
        );

        Scanner scanner = new Scanner(System.in);
        boolean rodando = true;

        while (rodando) {            
            System.out.println("\n1 - Adicionar aluno");
            System.out.println("2 - Pesquisar aluno");
            System.out.println("3 - Remover aluno");
            System.out.println("4 - Imprimir árvore");
            System.out.println("5 - Mostrar quantidade de alunos");
            System.out.println("0 - Sair");

            try {
                int opcao = scanner.nextInt();
                scanner.nextLine(); 

                switch (opcao) {
                    case 1:
                        System.out.println("Digite a matrícula do aluno");
                        int mat = scanner.nextInt();
                        
                        scanner.nextLine();
                        
                        System.out.println("Digite o nome do aluno");
                        String nome = scanner.nextLine();

                        System.out.println("Digite a nota do aluno");
                        int nota = scanner.nextInt();
                        
                        if (!arvore.add(new Aluno(mat, nome, nota))) {
                            System.out.println("Esse aluno já existe.");
                        } else {
                            System.out.println("Aluno adicionado!");
                        }
                        break;

                    case 2:
                        System.out.println("Digite a matrícula do aluno a ser procurado");
                        mat = scanner.nextInt();
                        scanner.nextLine();
                        
                        // Melhoria: usar floor ou apenas comparar com o objeto na árvore
                        Aluno chave = new Aluno(mat, "", 0);
                        Aluno resultado = arvore.contains(chave) ? arvore.floor(chave) : null;
                        
                        if (resultado != null && resultado.getMatricula() == mat) {
                            System.out.println("Aluno encontrado: " + resultado);
                        } else {
                            System.out.println("Aluno não encontrado");
                        }
                        break;

                    case 3:
                        System.out.println("Digite a matrícula do aluno a ser removido");
                        mat = scanner.nextInt();
                        scanner.nextLine();

                        if (arvore.remove(new Aluno(mat, "", 0))) {
                            System.out.println("Aluno removido");
                        } else {
                            System.out.println("Aluno não encontrado");
                        }
                        break;

                    case 4:
                        if (arvore.isEmpty()) {
                            System.out.println("Árvore vazia!");
                        } else {
                            List<Aluno> lista = new ArrayList<>(arvore);
                            // int meio = lista.size() / 2;
                            // int meioMatricula = lista.get(meio).getMatricula();

                            // System.out.println(lista.get(meio));

                            // int nivel = 0;
                            // for (Aluno aluno : lista) {
                            //     recursao(aluno, meioMatricula);
                            //     nivel++;
                            //     if (nivel == 2) {
                            //         meioMatricula = aluno.getMatricula();
                            //         recursao(aluno, meioMatricula);
                            //         nivel = 0;
                            //     }
                            // }

                            // nível 1: 1 nó  (o meio)
                            // nível 2: 2 nós (meios da esquerda e direita)
                            // nível 3: 4 nós ...
                            List<List<Aluno>> nivelAtual = new ArrayList<>();
                            nivelAtual.add(lista);

                            while (!nivelAtual.isEmpty()) {
                                List<List<Aluno>> proximoNivel = new ArrayList<>();
                                
                                for (List<Aluno> subNivel : nivelAtual) {
                                    int meio = subNivel.size() / 2;
                                    System.out.print(subNivel.get(meio) + "   ");  
                                    
                                    // fatiando a lista em esquerda e direita
                                    List<Aluno> esq = subNivel.subList(0, meio); 
                                    List<Aluno> dir = subNivel.subList(meio + 1, subNivel.size()); 

                                    if (!esq.isEmpty()) proximoNivel.add(esq);
                                    if (!dir.isEmpty()) proximoNivel.add(dir);
                                }
                                System.out.println();
                                nivelAtual = proximoNivel;
                            }

                        }

                        break;

                    case 5:
                        System.out.println("Quantidade de alunos: " + arvore.size());

                        break;

                    case 0:
                        System.out.println("Programa encerrado.");
                        rodando = false;
                        break;

                    default:
                        System.out.println("Opção inválida!");
                }

            } catch (InputMismatchException e) {
                System.err.println("ERRO: Entrada inválida. Por favor, digite um número.");
                scanner.nextLine();
            } catch (Exception e) {
                System.err.println("Ocorreu um erro inesperado: " + e.getMessage());
            }
        }
        scanner.close();
    }

    // private static void recursao(Aluno aluno, int meio) {
    //     if (aluno.getMatricula() < meio){
    //         System.out.print(aluno + " --- ");
    //     } else if (aluno.getMatricula() > meio) {   
    //         System.out.print(aluno + "     ");
    //     }
    // }
}