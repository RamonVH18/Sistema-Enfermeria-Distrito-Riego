package clienteApi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import contratos.IClienteApi;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import request.CrearCitaRequest;
import request.IniciarSesionRequest;
import request.ReagendarCitaRequest;
import response.AtributoFisicoResponse;
import response.CitaPendienteResponse;
import response.CrearCitaResponse;
import response.DatosEmpleadoResponse;
import response.DetalleResponse;
import response.EmpleadoOptionResponse;
import response.ExpedienteConfigResponse;
import response.ReagendarCitaResponse;
import response.SignosVitalesResponse;
import response.UsuarioResponse;

/**
 *
 * @author Leonardo Flores Leyva
 */
public class ClienteApiTemporal implements IClienteApi {

    private HttpClient client = HttpClient.newHttpClient();
    private final String BASE_URL = "http://localhost:8080/enfermeriaDR";
    private final ObjectMapper mapper;

    public ClienteApiTemporal() {
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

    @Override
    public CompletableFuture<CrearCitaResponse> enviarCita(CrearCitaRequest requestData) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public CompletableFuture<ReagendarCitaResponse> reagendarCita(ReagendarCitaRequest requestData) {
        try {
            String json = mapper.writeValueAsString(requestData);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/citas/actualizar"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();
            return handleResponse(client.sendAsync(request, HttpResponse.BodyHandlers.ofString()), ReagendarCitaResponse.class);
        } catch (JsonProcessingException e) {
            return CompletableFuture.failedFuture(e);
        }
    }

    @Override
    public CompletableFuture<List<CitaPendienteResponse>> obtenerCitasPendientes() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public CompletableFuture<List<CitaPendienteResponse>> obtenerCitasPorFecha(LocalDate filtro) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public CompletableFuture<Void> cancelarCita(Integer idCita) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public CompletableFuture<Void> completarCita(Integer idCita) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public CompletableFuture<List<EmpleadoOptionResponse>> obtenerTodosLosEmpleados() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public CompletableFuture<List<EmpleadoOptionResponse>> buscarEmpleadosPorFiltro(String filtro) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public CompletableFuture<UsuarioResponse> inicioSesionRequest(IniciarSesionRequest requestData) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public CompletableFuture<List<DatosEmpleadoResponse>> obtenerDatosHistorialEmpleado() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public CompletableFuture<byte[]> obtenerReporte() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public CompletableFuture<SignosVitalesResponse> obtenerSignosVitalesEmpleado(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public CompletableFuture<Map<String, List<DetalleResponse>>> obtenerAntecedentesEmpleado(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public CompletableFuture<Map<String, AtributoFisicoResponse>> obtenerAtributosFisicosEmpleado(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public CompletableFuture<ExpedienteConfigResponse> obtenerInfoConfiguracionExpediente() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
