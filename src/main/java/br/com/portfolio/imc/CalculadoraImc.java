package br.com.portfolio.imc;

public final class CalculadoraImc {

    private CalculadoraImc() {
    }

    public static double calcular(double pesoKg, double alturaMetros) {
        if (!Double.isFinite(pesoKg) || pesoKg <= 0
                || !Double.isFinite(alturaMetros) || alturaMetros <= 0) {
            throw new IllegalArgumentException("Peso e altura devem ser numeros positivos e finitos.");
        }

        double resultado = pesoKg / (alturaMetros * alturaMetros);
        if (!Double.isFinite(resultado) || resultado <= 0) {
            throw new IllegalArgumentException("O IMC calculado esta fora do intervalo numerico valido.");
        }

        return resultado;
    }

    public static ClassificacaoImc classificar(double imc) {
        if (!Double.isFinite(imc) || imc <= 0) {
            throw new IllegalArgumentException("IMC invalido.");
        }

        if (imc < 18.5) return ClassificacaoImc.ABAIXO_DO_PESO;
        if (imc < 25) return ClassificacaoImc.PESO_ADEQUADO;
        if (imc < 30) return ClassificacaoImc.SOBREPESO;
        if (imc < 35) return ClassificacaoImc.OBESIDADE_GRAU_I;
        if (imc < 40) return ClassificacaoImc.OBESIDADE_GRAU_II;
        return ClassificacaoImc.OBESIDADE_GRAU_III;
    }
}
