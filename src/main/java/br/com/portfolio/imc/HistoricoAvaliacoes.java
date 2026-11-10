package br.com.portfolio.imc;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public final class HistoricoAvaliacoes {

    private static final Comparator<Avaliacao> POR_IMC =
            Comparator.comparingDouble(Avaliacao::getImc);

    private final List<Avaliacao> avaliacoes = new ArrayList<>();

    public void adicionar(Avaliacao avaliacao) {
        avaliacoes.add(Objects.requireNonNull(avaliacao, "Avaliacao obrigatoria."));
    }

    public List<Avaliacao> listar() {
        return List.copyOf(avaliacoes);
    }

    public List<Avaliacao> ordenarPorImc() {
        return avaliacoes.stream().sorted(POR_IMC).toList();
    }

    public Optional<Avaliacao> menorImc() {
        return avaliacoes.stream().min(POR_IMC);
    }

    public Optional<Avaliacao> maiorImc() {
        return avaliacoes.stream().max(POR_IMC);
    }
}
