/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clienteApi;


import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import request.CrearCitaRequest;
import response.CrearCitaResponse;

/**
 *
 * @author Ramon Valencia
 */
public class ClienteApi {
    private final HttpClient client = HttpClient.newHttpClient();
    private final String BASE_URL = "http://localhost:8080/enfermeriaDR";
    private final ObjectMapper mapper = new ObjectMapper(); 

    public CompletableFuture<CrearCitaResponse> enviarCita(CrearCitaRequest requestData) {
        try {
            // 1. Convertimos el objeto Java a String JSON
            String jsonBody = mapper.writeValueAsString(requestData);

            // 2. Construimos la petición POST
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            // 3. Enviamos de forma asíncrona
            return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(response -> {
                        if (response.statusCode() == 202 || response.statusCode() == 201) {
                            try {
                                // Parseamos la respuesta del servidor a nuestro objeto Response
                                return mapper.readValue(response.body(), CrearCitaResponse.class);
                            } catch (Exception e) {
                                throw new RuntimeException("Error al leer respuesta: " + e.getMessage());
                            }
                        } else {
                            throw new RuntimeException("Error del servidor: " + response.statusCode());
                        }
                    });

        } catch (Exception e) {
            return CompletableFuture.failedFuture(e);
        }
    }
}
