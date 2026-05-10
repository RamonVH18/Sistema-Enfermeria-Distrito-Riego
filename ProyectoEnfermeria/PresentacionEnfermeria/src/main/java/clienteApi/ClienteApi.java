package clienteApi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import request.CrearCitaRequest;
import request.IniciarSesionRequest;
import response.AtributoFisicoResponse;
import response.CitaPendienteResponse;
import response.CrearCitaResponse;
import response.DatosEmpleadoResponse;
import response.DetalleResponse;
import response.EmpleadoOptionResponse;
import response.UsuarioResponse;
import response.SignosVitalesResponse;

/**
 *
 * @author Ramon Valencia
 */
public class ClienteApi {

    private HttpClient client = HttpClient.newHttpClient();
    private final String BASE_URL = "http://localhost:8080/enfermeriaDR";
    private ObjectMapper mapper;

    public ClienteApi() {
        this.client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        this.mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    // Método genérico para procesar la respuesta y deserializar
    private <T> CompletableFuture<T> handleResponse(CompletableFuture<HttpResponse<String>> responseFuture, Class<T> responseClass) {
        return responseFuture.thenApply(response -> {
            validarRespuesta(response);

            if (responseClass == Void.class) {
                return null;
            }
            try {
                return mapper.readValue(response.body(), responseClass);
            } catch (Exception e) {
                throw new RuntimeException("Error al deserializar: " + e.getMessage());
            }
        });
    }

    // Sobrecarga para Listas (TypeReference)
    private <T> CompletableFuture<T> handleResponse(CompletableFuture<HttpResponse<String>> responseFuture, TypeReference<T> typeRef) {
        return responseFuture.thenApply(response -> {
            validarRespuesta(response);
            try {
                return mapper.readValue(response.body(), typeRef);
            } catch (Exception e) {
                throw new RuntimeException("Error al deserializar lista: " + e.getMessage());
            }
        });
    }

    public CompletableFuture<CrearCitaResponse> enviarCita(CrearCitaRequest requestData) {
        try {

            String json = mapper.writeValueAsString(requestData);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/citas"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();
            return handleResponse(client.sendAsync(request, HttpResponse.BodyHandlers.ofString()), CrearCitaResponse.class);
        } catch (IOException e) {
            return CompletableFuture.failedFuture(e);
        }
    }

    public CompletableFuture<List<CitaPendienteResponse>> obtenerCitasPendientes() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/citas?estado=PENDIENTE"))
                .GET()
                .build();

        return handleResponse(client.sendAsync(request, HttpResponse.BodyHandlers.ofString()),
                new TypeReference<List<CitaPendienteResponse>>() {
        });
    }

    public CompletableFuture<List<CitaPendienteResponse>> obtenerCitasPorFecha(LocalDate filtro) {

        String urlConFiltro = BASE_URL + "/citas" + "?estado=PENDIENTE&filtroFecha="
                + URLEncoder.encode(filtro.format(DateTimeFormatter.ISO_DATE), StandardCharsets.UTF_8);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlConFiltro))
                .GET()
                .build();
        return handleResponse(client.sendAsync(request, HttpResponse.BodyHandlers.ofString()),
                new TypeReference<List<CitaPendienteResponse>>() {
        });
    }

    public CompletableFuture<Void> cancelarCita(Integer idCita) {
        String url = BASE_URL + "/citas/" + idCita + "/cancelar";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .method("PATCH",
                        HttpRequest.BodyPublishers.ofString("{\"status\":\"cancelada\"}"
                        ))
                .header("Content-Type", "application/json")
                .build();
        return handleResponse(client.sendAsync(request, HttpResponse.BodyHandlers.ofString()),
                Void.class);

    }

    public CompletableFuture<Void> completarCita(Integer idCita) {
        String url = BASE_URL + "/citas/" + idCita + "/completar";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .method("PATCH",
                        HttpRequest.BodyPublishers.ofString("{\"status\":\"completada\"}"
                        ))
                .header("Content-Type", "application/json")
                .build();
        return handleResponse(client.sendAsync(request, HttpResponse.BodyHandlers.ofString()),
                Void.class);
    }

    public CompletableFuture<List<EmpleadoOptionResponse>> obtenerTodosLosEmpleados() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/enfermeriaDR/empleados"))
                .GET()
                .build();

        return handleResponse(client.sendAsync(request, HttpResponse.BodyHandlers.ofString()),
                new TypeReference<List<EmpleadoOptionResponse>>() {
        });
    }

    // 2. Método para obtener por FILTRO (GET /empleados/options?filtroNombre=...)
    public CompletableFuture<List<EmpleadoOptionResponse>> buscarEmpleadosPorFiltro(String filtro) {
        String urlConFiltro = BASE_URL + "/empleados" + "?filtroNombre=" + URLEncoder.encode(filtro, StandardCharsets.UTF_8);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlConFiltro))
                .GET()
                .build();
        return handleResponse(client.sendAsync(request, HttpResponse.BodyHandlers.ofString()),
                new TypeReference<List<EmpleadoOptionResponse>>() {
        });
    }

    public CompletableFuture<UsuarioResponse> inicioSesionRequest(IniciarSesionRequest requestData) {
        return CompletableFuture.supplyAsync(() -> {
            try {
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

    public CompletableFuture<List<DatosEmpleadoResponse>> obtenerDatosHistorialEmpleado() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/expedientes"))
                .GET()
                .build();
        return handleResponse(client.sendAsync(request, HttpResponse.BodyHandlers.ofString()),
                new TypeReference<List<DatosEmpleadoResponse>>() {
        });
    }

    public CompletableFuture<byte[]> obtenerReporte() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/reportes/salud"))
                .GET()
                .build();
        CompletableFuture<HttpResponse<byte[]>> response = client.sendAsync(request, HttpResponse.BodyHandlers.ofByteArray());
        return response.thenApply(HttpResponse::body);
    }

    public CompletableFuture<SignosVitalesResponse> obtenerSignosVitalesEmpleado(Integer id) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/expedientes/signos/" + id))
                .GET()
                .build();
        return handleResponse(client.sendAsync(request, HttpResponse.BodyHandlers.ofString()),
                SignosVitalesResponse.class);
    }
    
    public CompletableFuture<Map<String, List<DetalleResponse>>> obtenerAntecedentesEmpleado(Integer id) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/expedientes/antecedentes/" + id))
                .GET()
                .build();
        return handleResponse(client.sendAsync(request, HttpResponse.BodyHandlers.ofString()),
                new TypeReference<Map<String, List<DetalleResponse>>>(){});
    }
    
    public CompletableFuture<Map<String, AtributoFisicoResponse>> obtenerAtributosFisicosEmpleado(Integer id) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/expedientes/atributos/" + id))
                .GET()
                .build();
        return handleResponse(client.sendAsync(request, HttpResponse.BodyHandlers.ofString()),
                new TypeReference<Map<String, AtributoFisicoResponse>>(){});
    }

    private void validarRespuesta(HttpResponse<String> response) {
        if (response.statusCode() >= 400) {
            String mensajeParaUsuario;
            try {
                // Leemos el JSON que mandó el servidor
                JsonNode nodo = mapper.readTree(response.body());
                // Sacamos solo el valor del campo "message"
                mensajeParaUsuario = nodo.get("message").asText();
            } catch (Exception e) {
                // Si no es un JSON o no tiene 'message', mensaje genérico
                mensajeParaUsuario = "Error inesperado en el servidor (Código: " + response.statusCode() + ")";
            }
            throw new RuntimeException(mensajeParaUsuario);
        }

    }

}
