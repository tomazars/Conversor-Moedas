

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.Gson;


public class TesteHttpCliente {

    public static void main(String[] args) {
        try {
            String json = buscarDadosDaAPI();
            ExchangeRates exchangeRates = converterJsonParaObjeto(json);
            exibirTaxasDeCambio(exchangeRates);
            converterValor(exchangeRates);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para buscar os dados da API
    public static String buscarDadosDaAPI() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.exchangerate-api.com/v4/latest/BRL"))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Status: " + response.statusCode());
        return response.body();
    }

    // Método para converter JSON em objeto Java
    public static ExchangeRates converterJsonParaObjeto(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, ExchangeRates.class);
    }

    // Método para exibir as taxas de câmbio de 6 moedas
    public static void exibirTaxasDeCambio(ExchangeRates exchangeRates) {
        System.out.println("Base: " + exchangeRates.getBase());

        String[] moedasInteresse = {"USD", "EUR", "ARS", "JPY", "GBP", "CAD"};
        System.out.println("\n--- Taxas de câmbio filtradas ---");
        for (String moeda : moedasInteresse) {
            Double taxa = exchangeRates.getRates().get(moeda);
            System.out.println("1 BRL = " + taxa + " " + moeda);
        }
    }

    // Método para converter valor em BRL para outra moeda
    public static void converterValor(ExchangeRates exchangeRates) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nDigite o valor em BRL que deseja converter: ");
        double valorBRL = scanner.nextDouble();

        System.out.print("Digite a moeda para conversão (ex: USD, EUR, JPY): ");
        String moeda = scanner.next().toUpperCase();

        Double taxa = exchangeRates.getRates().get(moeda);

        if (taxa != null) {
            double valorConvertido = valorBRL * taxa;
            System.out.printf("%.2f BRL equivalem a %.2f %s\n", valorBRL, valorConvertido, moeda);
        } else {
            System.out.println("Moeda não encontrada: " + moeda);
        }

        scanner.close();

    }

}
