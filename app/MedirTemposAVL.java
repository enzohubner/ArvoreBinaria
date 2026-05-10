package app;

import arvorebinaria.ArvoreAVL;
import arvorebinaria.ArvoreBinaria;
import colecao.IColecao;
import dominio.Aluno;
import dominio.ComparadorAlunoMatricula;

import java.io.*;

public class MedirTemposAVL {

    public static void main(String[] args) throws Exception {

        String[] arquivos = {
            "arquivos/degenerado_100.txt",
            "arquivos/degenerado_500.txt",
            "arquivos/degenerado_1000.txt",
            "arquivos/degenerado_5000.txt",
            "arquivos/degenerado_10000.txt",
            "arquivos/degenerado_50000.txt",
            "arquivos/balanceado_100.txt",
            "arquivos/balanceado_500.txt",
            "arquivos/balanceado_1000.txt",
            "arquivos/balanceado_5000.txt",
            "arquivos/balanceado_10000.txt",
            "arquivos/balanceado_50000.txt"
        };

        for (String caminho : arquivos) {

            System.out.println("\n=============================");
            System.out.println("Arquivo: " + caminho);
            System.out.println("=============================");

            IColecao<Aluno> arvore =
                new ArvoreAVL<>(new ComparadorAlunoMatricula());

            long inicio = System.nanoTime();

            BufferedReader br =
                new BufferedReader(new FileReader(caminho));

            String linha;
            Aluno ultimoInserido = null;
            int maiorMatricula = 0;

            while ((linha = br.readLine()) != null) {
                String[] p = linha.split(";");
                int mat  = Integer.parseInt(p[0].trim());
                String nm = p[1].trim();
                int nota = Integer.parseInt(p[2].trim());
                Aluno a  = new Aluno(mat, nm, nota);
                arvore.adicionar(a);
                if (mat > maiorMatricula) {
                    maiorMatricula = mat;
                    ultimoInserido = a;
                }
            }

            br.close();

            long fim = System.nanoTime();
            System.out.println("1. Inserção total: "
                + (fim - inicio) + " ns");

            inicio = System.nanoTime();
            arvore.pesquisar(ultimoInserido);
            fim = System.nanoTime();
            System.out.println("2. Busca (pior caso): "
                + (fim - inicio) + " ns");

            Aluno inexistente =
                new Aluno(maiorMatricula + 1, "X", 0);
            inicio = System.nanoTime();
            arvore.pesquisar(inexistente);
            fim = System.nanoTime();
            System.out.println("3. Busca inexistente: "
                + (fim - inicio) + " ns");

            inicio = System.nanoTime();
            arvore.remover(ultimoInserido);
            fim = System.nanoTime();
            System.out.println("4. Remoção: "
                + (fim - inicio) + " ns");

            inicio = System.nanoTime();
            int qtd = arvore.quantidadeNos();
            fim = System.nanoTime();
            System.out.println("5. quantidadeNos(): "
                + qtd + " | Tempo: " + (fim - inicio) + " ns");
        }
    }
}