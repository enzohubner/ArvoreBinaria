package dominio;

import java.util.Comparator;

public class ComparadorAlunoMatricula
        implements Comparator<Aluno> {

    @Override
    public int compare(Aluno a1, Aluno a2) {

        return Integer.compare(
            a1.getMatricula(),
            a2.getMatricula()
        );
    }
}