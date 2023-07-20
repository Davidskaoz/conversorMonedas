package src;

import com.sun.net.httpserver.Request;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;


public class HttpClientApp {

    public void invoke() throws URISyntaxException {

        HttpClient client =  HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(2))
                .build();

        HttpRequest request =  HttpRequest.newBuilder()
                .uri(new URI("https://api.currencyapi.com/v3/" +
                        "latest?apikey=cur_live_rO3DwvWoqfYc5oCeuGWh9gTTnMYE0EEcNrOdkOsQ&" +
                        "currencies=EUR%2CUSD%2CCAD&base_currency=MXN"))
                .GET()
                .build();

        client.sendAsync(request, BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join();
    }
}
