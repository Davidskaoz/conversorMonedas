package src;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


public class HttpClientApp {

    public String invoke(String Url) throws URISyntaxException, ExecutionException, InterruptedException, TimeoutException {

        HttpClient client =  HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(2))
                .build();

        HttpRequest request =  HttpRequest.newBuilder()
                .uri(new URI(Url))
                .GET()
                .build();

        CompletableFuture<HttpResponse<String>> response =  client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        return  response.thenApply(HttpResponse::body).get(2, TimeUnit.SECONDS);
    }
}
