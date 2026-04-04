/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clienteApi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import javafx.scene.control.Alert;
import request.CrearCitaRequest;
import request.IniciarSesionRequest;
import response.CitaPendienteResponse;
import response.CrearCitaResponse;
import response.EmpleadoOptionResponse;
import response.UsuarioResponse;

/**
 *
 * @author Ramon Valencia
 */
public class ClienteApi {

    private final HttpClient client = HttpClient.newHttpClient();
    private final String BASE_URL = "http://localhost:8080/enfermeriaDR";
    private final ObjectMapper mapper;

    public ClienteApi() {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public CompletableFuture<CrearCitaResponse> enviarCita(CrearCitaRequest requestData) {
        return CompletableFuture.supplyAsync(() -> {
            try {

                ObjectMapper mapper = new ObjectMapper();
                mapper.registerModule(new JavaTimeModule());
                String json = mapper.writeValueAsString(requestData);

                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(BASE_URL + "/citas"))
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

    public CompletableFuture<List<CitaPendienteResponse>> obtenerCitasPendientes() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(BASE_URL + "/citas?estado=PENDIENTE"))
                        .GET()
                        .build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                validarRespuesta(response);

                return mapper.readValue(response.body(),
                        new TypeReference<List<CitaPendienteResponse>>() {
                });
            } catch (Exception e) {
                throw new RuntimeException("Error al obtener empleados: " + e.getMessage(), e);
            }
        });
    }
    

    public CompletableFuture<List<EmpleadoOptionResponse>> obtenerTodosLosEmpleados() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("http://localhost:8080/enfermeriaDR/empleados"))
                        .GET()
                        .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                validarRespuesta(response);

                return mapper.readValue(response.body(), new TypeReference<List<EmpleadoOptionResponse>>() {
                });
            } catch (Exception e) {
                throw new RuntimeException("Error al obtener empleados: " + e.getMessage(), e);
            }
        });
    }

    // 2. Método para obtener por FILTRO (GET /empleados/options?filtroNombre=...)
    public CompletableFuture<List<EmpleadoOptionResponse>> buscarEmpleadosPorFiltro(String filtro) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                // Importante: Encodear el parámetro por si lleva espacios o caracteres especiales
                String urlConFiltro = BASE_URL + "/empleados" + "?filtroNombre=" + URLEncoder.encode(filtro, StandardCharsets.UTF_8);

                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(urlConFiltro))
                        .GET()
                        .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                validarRespuesta(response);

                return mapper.readValue(response.body(), new TypeReference<List<EmpleadoOptionResponse>>() {
                });
            } catch (Exception e) {
                throw new RuntimeException("Error al filtrar empleados: " + e.getMessage(), e);
            }
        });
    }

    public CompletableFuture<UsuarioResponse> inicioSesionRequest(IniciarSesionRequest requestData) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                ObjectMapper mapper = new ObjectMapper();
                mapper.registerModule(new JavaTimeModule());
                String json = mapper.writeValueAsString(requestData);

                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("http://localhost:8080/enfermeriaDR/auth/login"))
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(json))
                        .build();

                // Usamos un solo cliente (es mejor práctica que crear uno nuevo en cada petición)
                HttpResponse<String> response = HttpClient.newHttpClient()
                        .send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() >= 200 && response.statusCode() < 300) {
                    return mapper.readValue(response.body(), UsuarioResponse.class);
                } else {
                    // No mostramos alerta aquí, lanzamos la excepción para que el Controller la maneje
                    throw new RuntimeException("Credenciales incorrectas o error de servidor");
                }
            } catch (Exception e) {
                throw new RuntimeException("Error de conexión: " + e.getMessage());
            }
        });
    }

    private void validarRespuesta(HttpResponse<String> response) {
        if (response.statusCode() < 200 || response.statusCode() >= 300) {
            throw new RuntimeException("Error del servidor: " + response.statusCode() + " - " + response.body());
        }
    }

}
