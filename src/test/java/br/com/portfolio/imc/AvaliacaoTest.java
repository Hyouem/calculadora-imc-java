package br.com.portfolio.imc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AvaliacaoTest {

    @Test
    void removeEspacosDoNome() {
        Avaliacao avaliacao = new Avaliacao("  Ana  ", 80, 1.80);
        assertEquals("Ana", avaliacao.getNome());
    }

    @Test
    void naoAceitaNomeVazioOuNulo() {
        assertThrows(IllegalArgumentException.class, () -> new Avaliacao("  ", 80, 1.80));
        assertThrows(IllegalArgumentException.class, () -> new Avaliacao(null, 80, 1.80));
    }

    @Test
    void calculaImcEClassificacaoAoCriar() {
        Avaliacao avaliacao = new Avaliacao("Ana", 80, 1.80);
        assertEquals(24.691358, avaliacao.getImc(), 0.000001);
        assertEquals(ClassificacaoImc.PESO_ADEQUADO, avaliacao.getClassificacao());
    }
}
