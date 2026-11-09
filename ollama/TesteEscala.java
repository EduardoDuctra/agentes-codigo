public class TesteEscala {
    public static void testCalculo() {
        double meters = 5;
        int scale = 50;
        double expected = 10;
        double result = (meters * 100) / scale;
        if (Math.abs(result - expected) < 0.0001) {
            System.out.println("Teste 1: Aprovado");
        } else {
            System.out.println("Teste 1: Falha");
        }
    }

    public static void testEscalaInvalida() {
        int scale = 75;
        if (scale != 25 && scale != 50 && scale != 100 && scale != 200) {
            System.out.println("Teste 2: Aprovado");
        } else {
            System.out.println("Teste 2: Falha");
        }
    }

    public static void main(String[] args) {
        testCalculo();
        testEscalaInvalida();
    }
}