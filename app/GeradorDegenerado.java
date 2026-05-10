package app;

import java.io.*;

public class GeradorDegenerado {

    public static void main(String[] args) throws Exception {

        // Mude esse valor para gerar arquivos de tamanhos diferentes
        int quantidade = 50000;

        String caminho = "arquivos/degenerado_" + quantidade + ".txt";

        BufferedWriter bw =
            new BufferedWriter(new FileWriter(caminho));

        // Matrículas em ordem crescente = árvore degenerada
        for (int i = 1; i <= quantidade; i++) {
            bw.write(i + ";Aluno" + i + ";" + (i % 100));
            bw.newLine();
        }

        bw.close();

        System.out.println("Arquivo gerado: " + caminho);
    }
}