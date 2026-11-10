package br.com.portfolio.imc;

import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public final class Main {

    private static final Locale PT_BR = Locale.forLanguageTag("pt-BR");

    private Main() {
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            executar(scanner);
        } catch (NoSuchElementException e) {
            System.out.println("\nEntrada encerrada.");
        }
    }

    private static void executar(Scanner scanner) {
        HistoricoAvaliacoes historico = new HistoricoAvaliacoes();

        while (true) {
            System.out.println("\n=== CALCULADORA DE IMC ===");
            System.out.println("1 - Registrar avaliacao");
            System.out.println("2 - Listar historico");
            System.out.println("3 - Ordenar por IMC");
            System.out.println("4 - Maior e menor IMC");
            System.out.println("0 - Sair");
            System.out.print("Opcao: ");

            String opcao = scanner.nextLine().trim();
            switch (opcao) {
                case "1" -> registrar(scanner, historico);
                case "2" -> mostrar(historico.listar());
                case "3" -> mostrar(historico.ordenarPorImc());
                case "4" -> mostrarExtremos(historico);
                case "0" -> {
                    System.out.println("Ate a proxima!");
                    return;
                }
                default -> System.out.println("Opcao invalida.");
            }
        }
    }

    private static void registrar(Scanner scanner, HistoricoAvaliacoes historico) {
        String nome = lerNome(scanner);
        double peso = lerNumeroPositivo(scanner, "Peso em kg: ");
        double altura = lerNumeroPositivo(scanner, "Altura em metros: ");

        try {
            Avaliacao avaliacao = new Avaliacao(nome, peso, altura);
            historico.adicionar(avaliacao);
            System.out.printf(PT_BR, "Registrado: %s | IMC: %.2f | %s%n",
                    avaliacao.getNome(), avaliacao.getImc(), avaliacao.getClassificacao());
        } catch (IllegalArgumentException e) {
            System.out.println("Avaliacao nao registrada: " + e.getMessage());
        }
    }

    private static String lerNome(Scanner scanner) {
        while (true) {
            System.out.print("Nome: ");
            String nome = scanner.nextLine().trim();
            if (!nome.isBlank()) return nome;
            System.out.println("O nome nao pode ficar vazio.");
        }
    }

    private static double lerNumeroPositivo(Scanner scanner, String rotulo) {
        while (true) {
            System.out.print(rotulo);
            String entrada = scanner.nextLine().trim().replace(',', '.');
            try {
                double numero = Double.parseDouble(entrada);
                if (Double.isFinite(numero) && numero > 0) return numero;
            } catch (NumberFormatException ignored) {
                // Exibe a mesma mensagem para entradas nao numericas.
            }
            System.out.println("Digite um numero positivo valido.");
        }
    }

    private static void mostrar(List<Avaliacao> avaliacoes) {
        if (avaliacoes.isEmpty()) {
            System.out.println("Nenhuma avaliacao registrada.");
            return;
        }

        System.out.println("\n=== HISTORICO ===");
        for (Avaliacao avaliacao : avaliacoes) {
            System.out.printf(PT_BR, "%s | %.2f kg | %.2f m | IMC %.2f | %s%n",
                    avaliacao.getNome(), avaliacao.getPesoKg(), avaliacao.getAlturaMetros(),
                    avaliacao.getImc(), avaliacao.getClassificacao());
        }
    }

    private static void mostrarExtremos(HistoricoAvaliacoes historico) {
        if (historico.listar().isEmpty()) {
            System.out.println("Nenhuma avaliacao registrada.");
            return;
        }

        historico.menorImc().ifPresent(a ->
                System.out.printf(PT_BR, "Menor IMC: %s (%.2f)%n", a.getNome(), a.getImc()));
        historico.maiorImc().ifPresent(a ->
                System.out.printf(PT_BR, "Maior IMC: %s (%.2f)%n", a.getNome(), a.getImc()));
    }
}
