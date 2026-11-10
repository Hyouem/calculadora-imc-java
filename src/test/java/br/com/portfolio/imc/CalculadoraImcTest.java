package br.com.portfolio.imc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculadoraImcTest {

    @Test
    void calculaImcCorretamente() {
        assertEquals(24.691358, CalculadoraImc.calcular(80, 1.80), 0.000001);
    }

    @Test
    void rejeitaPesoZeroOuNegativo() {
        assertThrows(IllegalArgumentException.class, () -> CalculadoraImc.calcular(0, 1.70));
        assertThrows(IllegalArgumentException.class, () -> CalculadoraImc.calcular(-1, 1.70));
    }

    @Test
    void rejeitaAlturaZeroOuNegativa() {
        assertThrows(IllegalArgumentException.class, () -> CalculadoraImc.calcular(70, 0));
        assertThrows(IllegalArgumentException.class, () -> CalculadoraImc.calcular(70, -1.70));
    }

    @Test
    void rejeitaNumerosNaoFinitos() {
        assertThrows(IllegalArgumentException.class, () -> CalculadoraImc.calcular(Double.NaN, 1.70));
        assertThrows(IllegalArgumentException.class, () -> CalculadoraImc.calcular(70, Double.POSITIVE_INFINITY));
    }

    @Test
    void rejeitaImcComOverflow() {
        assertThrows(IllegalArgumentException.class,
                () -> CalculadoraImc.calcular(Double.MAX_VALUE, Double.MIN_NORMAL));
    }

    @Test
    void classificaAbaixoDoPeso() {
        assertEquals(ClassificacaoImc.ABAIXO_DO_PESO, CalculadoraImc.classificar(18.49));
    }

    @Test
    void classificaPesoAdequadoNosLimites() {
        assertEquals(ClassificacaoImc.PESO_ADEQUADO, CalculadoraImc.classificar(18.5));
        assertEquals(ClassificacaoImc.PESO_ADEQUADO, CalculadoraImc.classificar(24.99));
    }

    @Test
    void classificaSobrepesoNoLimite() {
        assertEquals(ClassificacaoImc.SOBREPESO, CalculadoraImc.classificar(25));
    }

    @Test
    void classificaObesidadeGrauUmNoLimite() {
        assertEquals(ClassificacaoImc.OBESIDADE_GRAU_I, CalculadoraImc.classificar(30));
    }

    @Test
    void classificaObesidadeGrauDoisNoLimite() {
        assertEquals(ClassificacaoImc.OBESIDADE_GRAU_II, CalculadoraImc.classificar(35));
    }

    @Test
    void classificaObesidadeGrauTresNoLimite() {
        assertEquals(ClassificacaoImc.OBESIDADE_GRAU_III, CalculadoraImc.classificar(40));
    }

    @Test
    void rejeitaImcInvalidoNaClassificacao() {
        assertThrows(IllegalArgumentException.class, () -> CalculadoraImc.classificar(0));
        assertThrows(IllegalArgumentException.class, () -> CalculadoraImc.classificar(Double.NaN));
    }
}
