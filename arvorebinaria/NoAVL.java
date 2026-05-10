package arvorebinaria;

public class NoAVL<T> extends No<T> {

    private int altura;

    public NoAVL(T valor) {
        super(valor);
        this.altura = 0; 
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }
}