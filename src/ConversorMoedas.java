import java.util.Scanner;

class ConversorMoedas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o valor em reais (R$): ");
        double valorReais = scanner.nextDouble();




        //double valorReais = scanner.nextDouble();

        System.out.println("Você digitou: R$ " + valorReais);

        // Exibir opções de moeda
        System.out.println("Escolha a moeda para conversão:");
        System.out.println("1 - Dólar Americano (USD)");
        System.out.println("2 - Euro (EUR)");
        System.out.println("3 - Peso Argentino (ARS)");
        System.out.print("Opção: ");
        int opcao = scanner.nextInt();

        double cotacao = 0;
        String simboloMoeda = "";

        if (opcao == 1) {
            cotacao = 5.25;
            simboloMoeda = "US$";
        } else if (opcao == 2) {
            cotacao = 6.10;
            simboloMoeda = "€";
        } else if (opcao == 3) {
            cotacao = 0.02;
            simboloMoeda = "ARS$";
        } else {
            System.out.println("Opção inválida.");
            scanner.close();
            return;
        }

        double valorConvertido = valorReais / cotacao;
        System.out.printf("R$ %.2f equivalem a %s %.2f%n", valorReais, simboloMoeda, valorConvertido);

        scanner.close();
    }
}























