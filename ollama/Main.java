import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite a medida em metros: ");
        double meters = scanner.nextDouble();
        System.out.print("Escolha a escala (25, 50, 100, 200): ");
        int scale = scanner.nextInt();

        if (meters < 0) {
            System.out.println("Medida inválida. Por favor, insira um valor positivo.");
        } else if (scale != 25 && scale != 50 && scale != 100 && scale != 200) {
            System.out.println("Escala inválida. Escolha entre 25, 50, 100 ou 200.");
        } else {
            double cm = (meters * 100) / scale;
            System.out.println("A medida em centímetros é: " + cm + " cm");
        }
    }
}