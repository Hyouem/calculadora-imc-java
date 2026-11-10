package br.com.portfolio.imc;

public final class Avaliacao {

    private final String nome;
    private final double pesoKg;
    private final double alturaMetros;
    private final double imc;
    private final ClassificacaoImc classificacao;

    public Avaliacao(String nome, double pesoKg, double alturaMetros) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome obrigatorio.");
        }

        this.nome = nome.trim();
        this.pesoKg = pesoKg;
        this.alturaMetros = alturaMetros;
        this.imc = CalculadoraImc.calcular(pesoKg, alturaMetros);
        this.classificacao = CalculadoraImc.classificar(imc);
    }

    public String getNome() { return nome; }
    public double getPesoKg() { return pesoKg; }
    public double getAlturaMetros() { return alturaMetros; }
    public double getImc() { return imc; }
    public ClassificacaoImc getClassificacao() { return classificacao; }
}
