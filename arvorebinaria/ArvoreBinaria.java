package arvorebinaria;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class ArvoreBinaria<T> extends ArvoreBinariaBase<T> {

    private No<T> raiz;
    private int quantidade;

    public ArvoreBinaria(Comparator<T> comparador) {
        super(comparador);
        this.raiz = null;
        this.quantidade = 0;
    }

    @Override
    public void adicionar(T novoValor) {
        raiz = adicionarRec(raiz, novoValor);
    }

    private No<T> adicionarRec(No<T> atual, T valor) {

        if (atual == null) {
            quantidade++;
            return new No<>(valor);
        }

        int comp = comparador.compare(valor, atual.getValor());

        if (comp < 0) {
            atual.setEsquerda(
                adicionarRec(atual.getEsquerda(), valor)
            );
        }
        else if (comp > 0) {
            atual.setDireita(
                adicionarRec(atual.getDireita(), valor)
            );
        }
        else {
            System.out.println("Esse aluno já existe.");
        }


        return atual;
    }

    @Override
    public T pesquisar(T valor) {

        No<T> resultado = pesquisarRec(raiz, valor);

        if (resultado == null) {
            return null;
        }

        return resultado.getValor();
    }

    private No<T> pesquisarRec(No<T> atual, T valor) {

        if (atual == null) {
            return null;
        }

        int comp = comparador.compare(valor, atual.getValor());

        if (comp == 0) {
            return atual;
        }

        if (comp < 0) {
            return pesquisarRec(atual.getEsquerda(), valor);
        }

        return pesquisarRec(atual.getDireita(), valor);
    }

    @Override
    public boolean remover(T valor) {

        if (pesquisar(valor) == null) {
            return false;
        }

        raiz = removerRec(raiz, valor);

        quantidade--;

        return true;
    }

    private No<T> removerRec(No<T> atual, T valor) {

        if (atual == null) {
            return null;
        }

        int comp = comparador.compare(valor, atual.getValor());

        if (comp < 0) {

            atual.setEsquerda(
                removerRec(atual.getEsquerda(), valor)
            );
        }
        else if (comp > 0) {

            atual.setDireita(
                removerRec(atual.getDireita(), valor)
            );
        }
        else {

            if (atual.getEsquerda() == null &&
                atual.getDireita() == null) {

                return null;
            }

            if (atual.getEsquerda() == null) {
                return atual.getDireita();
            }

            if (atual.getDireita() == null) {
                return atual.getEsquerda();
            }

            No<T> menorDireita = menorValor(
                atual.getDireita()
            );

            atual.setValor(menorDireita.getValor());

            atual.setDireita(
                removerRec(
                    atual.getDireita(),
                    menorDireita.getValor()
                )
            );
        }

        return atual;
    }

    private No<T> menorValor(No<T> no) {

        No<T> atual = no;

        while (atual.getEsquerda() != null) {
            atual = atual.getEsquerda();
        }

        return atual;
    }

    @Override
    public int quantidadeNos() {
        return quantidade;
    }

    @Override
    public int altura() {
        return alturaRec(raiz);
    }

    private int alturaRec(No<T> no) {

        if (no == null) {
            return -1;
        }

        int alturaEsquerda =
            alturaRec(no.getEsquerda());

        int alturaDireita =
            alturaRec(no.getDireita());

        return 1 + Math.max(
            alturaEsquerda,
            alturaDireita
        );
    }

    @Override
    public String caminharEmNivel() {

        if (raiz == null) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();

        Queue<No<T>> fila = new LinkedList<>();

        fila.add(raiz);

        sb.append("[\n");

        while (!fila.isEmpty()) {

            int tamanhoNivel = fila.size();

            for (int i = 0; i < tamanhoNivel; i++) {

                No<T> atual = fila.poll();

                sb.append(atual.getValor())
                  .append(" ");

                if (atual.getEsquerda() != null) {
                    fila.add(atual.getEsquerda());
                }

                if (atual.getDireita() != null) {
                    fila.add(atual.getDireita());
                }
            }

            sb.append("\n");
        }

        sb.append("]");

        return sb.toString();
    }

    @Override
    public String caminharEmOrdem() {

        StringBuilder sb = new StringBuilder();

        sb.append("[");

        caminharEmOrdemRec(raiz, sb);

        if (sb.length() > 1) {
            sb.setLength(sb.length() - 2);
        }

        sb.append("]");

        return sb.toString();
    }

    private void caminharEmOrdemRec(
        No<T> no,
        StringBuilder sb
    ) {

        if (no != null) {

            caminharEmOrdemRec(
                no.getEsquerda(),
                sb
            );

            sb.append(no.getValor())
              .append(", ");

            caminharEmOrdemRec(
                no.getDireita(),
                sb
            );
        }
    }
}