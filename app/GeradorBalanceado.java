package app;

import java.io.*;
import java.util.*;

public class GeradorBalanceado {

    static List<Integer> ordem = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        int quantidade = 50000; // mude para cada tamanho

        String caminho = "arquivos/balanceado_" + quantidade + ".txt";

        // Gera a ordem de inserção que produz árvore balanceada
        // (divide o intervalo ao meio recursivamente)
        gerarOrdem(1, quantidade);

        BufferedWriter bw =
            new BufferedWriter(new FileWriter(caminho));

        for (int mat : ordem) {
            bw.write(mat + ";Aluno" + mat + ";" + (mat % 100));
            bw.newLine();
        }

        bw.close();

        System.out.println("Arquivo gerado: " + caminho);
    }

    static void gerarOrdem(int inicio, int fim) {
        if (inicio > fim) return;
        int meio = (inicio + fim) / 2;
        ordem.add(meio);
        gerarOrdem(inicio, meio - 1);
        gerarOrdem(meio + 1, fim);
    }
}