import java.util.Scanner;

public class Main {
    public static final int[] ESCALAS_PERMITIDAS = {25, 50, 100, 200};

    /**
     * Verifica se a escala indicada pelo denominador é permitida.
     * Escalas válidas: 1:25, 1:50, 1:100 e 1:200.
     */
    public static boolean isEscalaValida(int escalaDenominador) {
        for (int escala : ESCALAS_PERMITIDAS) {
            if (escala == escalaDenominador) {
                return true;
            }
        }
        return false;
    }

    /**
     * Converte uma string de escala (como "1:50" ou "50") para o denominador inteiro.
     */
    public static int parseEscala(String escalaStr) {
        if (escalaStr == null || escalaStr.trim().isEmpty()) {
            throw new IllegalArgumentException("A escala não pode ser vazia.");
        }
        String s = escalaStr.trim();
        if (s.startsWith("1:")) {
            s = s.substring(2).trim();
        }
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Formato de escala inválido: '" + escalaStr + "'. Use 1:25, 1:50, 1:100 ou 1:200.");
        }
    }

    /**
     * Calcula a medida correspondente no desenho em centímetros.
     * 
     * @param medidaMetros Medida real em metros (deve ser positiva).
     * @param escalaDenominador Denominador da escala (25, 50, 100 ou 200).
     * @return Medida no desenho em centímetros.
     * @throws IllegalArgumentException se a medida for menor ou igual a zero ou a escala não for permitida.
     */
    public static double calcularMedidaDesenho(double medidaMetros, int escalaDenominador) {
        if (medidaMetros <= 0) {
            throw new IllegalArgumentException("A medida real deve ser positiva (maior que zero). Informado: " + medidaMetros);
        }
        if (!isEscalaValida(escalaDenominador)) {
            throw new IllegalArgumentException("Escala não permitida: 1:" + escalaDenominador + ". As escalas permitidas são: 1:25, 1:50, 1:100 e 1:200.");
        }
        // Medida real em cm = medidaMetros * 100
        // Medida no desenho = (medidaMetros * 100) / escalaDenominador
        return (medidaMetros * 100.0) / escalaDenominador;
    }

    /**
     * Sobrecarga que aceita a escala no formato String (ex: "1:50" ou "50").
     */
    public static double calcularMedidaDesenho(double medidaMetros, String escalaStr) {
        int denominador = parseEscala(escalaStr);
        return calcularMedidaDesenho(medidaMetros, denominador);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("========================================");
        System.out.println("         CALCULADORA DE ESCALA          ");
        System.out.println("========================================");

        double medidaMetros = 0.0;
        while (true) {
            System.out.print("\nInforme a medida real em metros: ");
            String entrada = scanner.nextLine().trim().replace(',', '.');
            try {
                medidaMetros = Double.parseDouble(entrada);
                if (medidaMetros <= 0) {
                    System.out.println("Erro: A medida deve ser positiva (maior que zero).");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Erro: Entrada inválida. Por favor, digite um número válido (ex: 5 ou 2.5).");
            }
        }

        int escalaDenominador = 0;
        while (true) {
            System.out.println("\nEscalas disponíveis: 1:25, 1:50, 1:100, 1:200");
            System.out.print("Escolha uma das escalas (ex: 1:50 ou 50): ");
            String entradaEscala = scanner.nextLine().trim();
            try {
                escalaDenominador = parseEscala(entradaEscala);
                if (!isEscalaValida(escalaDenominador)) {
                    System.out.println("Erro: Escala não permitida. Escolha exclusivamente entre 1:25, 1:50, 1:100 ou 1:200.");
                    continue;
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }

        try {
            double resultadoCm = calcularMedidaDesenho(medidaMetros, escalaDenominador);
            System.out.println("\n----------------------------------------");
            System.out.println("                RESULTADO               ");
            System.out.println("----------------------------------------");
            System.out.printf("Medida real: %.2f m%n", medidaMetros);
            System.out.printf("Escala escolhida: 1:%d%n", escalaDenominador);
            System.out.printf("Medida no desenho: %.2f cm%n", resultadoCm);
            System.out.println("----------------------------------------");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao calcular: " + e.getMessage());
        }

        scanner.close();
    }
}
