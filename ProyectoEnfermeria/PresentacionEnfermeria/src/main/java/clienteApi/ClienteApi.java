/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clienteApi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
        return CompletableFuture.supplyAsync(() -> {
            try {
                
                ObjectMapper mapper = new ObjectMapper();
                mapper.registerModule(new JavaTimeModule());
                String json = mapper.writeValueAsString(requestData);
                
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("http://localhost:8080/enfermeriaDR/citas"))
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(json))
                        .build();

                HttpResponse<String> response = HttpClient.newHttpClient()
                        .send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() >= 200 && response.statusCode() < 300) {
                    return mapper.readValue(response.body(), CrearCitaResponse.class);
                } else {
                    throw new RuntimeException("Error del servidor: " + response.statusCode());
                }
            } catch (Exception e) {
                throw new RuntimeException("Fallo en la conexión: " + e.getMessage());
            }
        });
    }
}
