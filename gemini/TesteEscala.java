public class TesteEscala {

    public static void main(String[] args) {
        int totalTestes = 0;
        int aprovados = 0;

        System.out.println("========================================");
        System.out.println("       TESTES AUTOMATIZADOS DE ESCALA   ");
        System.out.println("========================================");

        // Teste 1: 5 metros na escala 1:50 devem resultar em 10 cm
        totalTestes++;
        System.out.println("\n[Teste 1] Verificar se 5 metros na escala 1:50 resultam em 10 cm:");
        try {
            double medidaMetros = 5.0;
            String escala = "1:50";
            double esperado = 10.0;
            double resultado = Main.calcularMedidaDesenho(medidaMetros, escala);

            System.out.println(" - Entrada: " + medidaMetros + " m na escala " + escala);
            System.out.println(" - Resultado esperado: " + esperado + " cm");
            System.out.println(" - Resultado obtido: " + resultado + " cm");

            if (Math.abs(resultado - esperado) < 1e-6) {
                System.out.println(" -> Status: APROVAÇÃO");
                aprovados++;
            } else {
                System.out.println(" -> Status: FALHA");
            }
        } catch (Exception e) {
            System.out.println(" -> Status: FALHA (Exceção inesperada: " + e.getMessage() + ")");
        }

        // Teste 2: Escala não permitida, como 1:75, deve ser rejeitada
        totalTestes++;
        System.out.println("\n[Teste 2] Verificar se uma escala não permitida (1:75) é rejeitada:");
        try {
            double medidaMetros = 5.0;
            String escalaNaoPermitida = "1:75";
            System.out.println(" - Tentando calcular com escala não permitida: " + escalaNaoPermitida);
            Main.calcularMedidaDesenho(medidaMetros, escalaNaoPermitida);

            System.out.println(" -> Status: FALHA (A escala não permitida foi aceita sem lançar exceção)");
        } catch (IllegalArgumentException e) {
            System.out.println(" - Exceção capturada com sucesso: " + e.getMessage());
            System.out.println(" -> Status: APROVAÇÃO");
            aprovados++;
        } catch (Exception e) {
            System.out.println(" -> Status: FALHA (Tipo de exceção inesperado: " + e.getClass().getSimpleName() + ")");
        }

        // Resumo final
        System.out.println("\n========================================");
        System.out.printf("Resultado final: %d de %d testes aprovados.%n", aprovados, totalTestes);
        System.out.println("========================================");

        if (aprovados != totalTestes) {
            System.exit(1);
        }
    }
}
