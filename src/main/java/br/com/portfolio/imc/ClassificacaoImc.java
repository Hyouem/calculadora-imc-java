package br.com.portfolio.imc;

public enum ClassificacaoImc {
    ABAIXO_DO_PESO("Abaixo do peso"),
    PESO_ADEQUADO("Peso adequado"),
    SOBREPESO("Sobrepeso"),
    OBESIDADE_GRAU_I("Obesidade grau I"),
    OBESIDADE_GRAU_II("Obesidade grau II"),
    OBESIDADE_GRAU_III("Obesidade grau III");

    private final String descricao;

    ClassificacaoImc(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
