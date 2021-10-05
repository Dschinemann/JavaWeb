package servicos;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Base64;

public class WebService {

    /*public static void main(String[] args) {
        System.out.println("\nresult   "+logar("cliente.teste", "123456"));
    }*/

    public static String logar(String user, String password) {
        final String URL = "http://192.168.0.228:42510/authorization";
        String PASS = user + ":" + password;
        String authStringEncode = Base64.getEncoder().encodeToString(PASS.getBytes());
        String erroOuToken = null;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("Authorization", authStringEncode)
                .uri(URI.create(URL))
                .timeout(Duration.ofSeconds(120))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String respostaDoServidor = response.body();
            switch (response.statusCode()) {
                case 401:
                    int indexMensage = respostaDoServidor.indexOf("mensagem");
                    erroOuToken = respostaDoServidor.substring(indexMensage - 1).replaceAll("}]}", "").trim();
                break;
                case 200:
                    int indexToken = respostaDoServidor.indexOf("token");
                    erroOuToken = respostaDoServidor.substring(indexToken - 1).replaceAll("}]}", "").trim();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return erroOuToken;
    }

}
