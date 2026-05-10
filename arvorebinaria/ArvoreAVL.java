package arvorebinaria;

import java.util.Comparator;

public class ArvoreAVL<T> extends ArvoreBinaria<T> {

    private NoAVL<T> raiz;
    private int quantidade;

    public ArvoreAVL(Comparator<T> comparador) {
        super(comparador);
        this.raiz = null;
        this.quantidade = 0;
    }


    private int alturaNo(NoAVL<T> no) {
        return (no == null) ? -1 : no.getAltura();
    }

    private void atualizarAltura(NoAVL<T> no) {
        no.setAltura(
            1 + Math.max(
                alturaNo((NoAVL<T>) no.getEsquerda()),
                alturaNo((NoAVL<T>) no.getDireita())
            )
        );
    }

    private int fatorBalanceamento(NoAVL<T> no) {
        return alturaNo((NoAVL<T>) no.getEsquerda())
             - alturaNo((NoAVL<T>) no.getDireita());
    }


    private NoAVL<T> rotacionarDireita(NoAVL<T> y) {
        NoAVL<T> x  = (NoAVL<T>) y.getEsquerda();
        NoAVL<T> t2 = (NoAVL<T>) x.getDireita();

        x.setDireita(y);
        y.setEsquerda(t2);

        atualizarAltura(y);
        atualizarAltura(x);

        return x; 
    }

    private NoAVL<T> rotacionarEsquerda(NoAVL<T> x) {
        NoAVL<T> y  = (NoAVL<T>) x.getDireita();
        NoAVL<T> t2 = (NoAVL<T>) y.getEsquerda();

        y.setEsquerda(x);
        x.setDireita(t2);

        atualizarAltura(x);
        atualizarAltura(y);

        return y; 
    }

    private NoAVL<T> balancear(NoAVL<T> no, T valor) {

        atualizarAltura(no);
        int fb = fatorBalanceamento(no);

        if (fb > 1 &&
            comparador.compare(valor,
                no.getEsquerda().getValor()) < 0) {
            return rotacionarDireita(no);
        }

        if (fb < -1 &&
            comparador.compare(valor,
                no.getDireita().getValor()) > 0) {
            return rotacionarEsquerda(no);
        }

        if (fb > 1 &&
            comparador.compare(valor,
                no.getEsquerda().getValor()) > 0) {
            no.setEsquerda(
                rotacionarEsquerda(
                    (NoAVL<T>) no.getEsquerda()
                )
            );
            return rotacionarDireita(no);
        }

        if (fb < -1 &&
            comparador.compare(valor,
                no.getDireita().getValor()) < 0) {
            no.setDireita(
                rotacionarDireita(
                    (NoAVL<T>) no.getDireita()
                )
            );
            return rotacionarEsquerda(no);
        }

        return no; 
    }

    @Override
    public void adicionar(T novoValor) {
        raiz = adicionarAVL(raiz, novoValor);
    }

    private NoAVL<T> adicionarAVL(NoAVL<T> atual, T valor) {

        if (atual == null) {
            quantidade++;
            return new NoAVL<>(valor);
        }

        int comp = comparador.compare(valor, atual.getValor());

        if (comp < 0) {
            atual.setEsquerda(
                adicionarAVL((NoAVL<T>) atual.getEsquerda(), valor)
            );
        } else if (comp > 0) {
            atual.setDireita(
                adicionarAVL((NoAVL<T>) atual.getDireita(), valor)
            );
        } else {
            return atual; 
        }

        return balancear(atual, valor);
    }

    @Override
    public T pesquisar(T valor) {
        No<T> resultado = pesquisarRec(raiz, valor);
        return (resultado == null) ? null : resultado.getValor();
    }

    private No<T> pesquisarRec(No<T> atual, T valor) {
        if (atual == null) return null;
        int comp = comparador.compare(valor, atual.getValor());
        if (comp == 0) return atual;
        if (comp < 0)  return pesquisarRec(atual.getEsquerda(), valor);
        return pesquisarRec(atual.getDireita(), valor);
    }

    @Override
    public int quantidadeNos() {
        return quantidade;
    }

    @Override
    public int altura() {
        return alturaNo(raiz);
    }
}