package br.com.portfolio.imc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HistoricoAvaliacoesTest {

    @Test
    void iniciaComHistoricoVazio() {
        var historico = new HistoricoAvaliacoes();
        assertTrue(historico.listar().isEmpty());
        assertTrue(historico.menorImc().isEmpty());
        assertTrue(historico.maiorImc().isEmpty());
    }

    @Test
    void preservaOrdemDeInclusao() {
        var historico = new HistoricoAvaliacoes();
        historico.adicionar(new Avaliacao("Ana", 80, 1.80));
        historico.adicionar(new Avaliacao("Bia", 60, 1.70));
        assertEquals("Ana", historico.listar().get(0).getNome());
        assertEquals("Bia", historico.listar().get(1).getNome());
    }

    @Test
    void protegeListaContraAlteracoesExternas() {
        var historico = new HistoricoAvaliacoes();
        historico.adicionar(new Avaliacao("Ana", 80, 1.80));
        assertThrows(UnsupportedOperationException.class,
                () -> historico.listar().clear());
    }

    @Test
    void ordenaPorImcSemModificarOrdemOriginal() {
        var historico = new HistoricoAvaliacoes();
        historico.adicionar(new Avaliacao("Maior", 100, 1.70));
        historico.adicionar(new Avaliacao("Menor", 55, 1.70));
        historico.adicionar(new Avaliacao("Meio", 75, 1.70));
        assertEquals("Menor", historico.ordenarPorImc().get(0).getNome());
        assertEquals("Meio", historico.ordenarPorImc().get(1).getNome());
        assertEquals("Maior", historico.ordenarPorImc().get(2).getNome());
        assertEquals("Maior", historico.listar().get(0).getNome());
    }

    @Test
    void identificaMaiorEMenorImc() {
        var historico = new HistoricoAvaliacoes();
        historico.adicionar(new Avaliacao("Ana", 90, 1.70));
        historico.adicionar(new Avaliacao("Bia", 50, 1.70));
        assertEquals("Ana", historico.maiorImc().orElseThrow().getNome());
        assertEquals("Bia", historico.menorImc().orElseThrow().getNome());
    }

    @Test
    void rejeitaAvaliacaoNula() {
        var historico = new HistoricoAvaliacoes();
        assertThrows(NullPointerException.class, () -> historico.adicionar(null));
    }
}
