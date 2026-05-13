package contratos;

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
public interface IClienteApi { 
    public CompletableFuture<CrearCitaResponse> enviarCita(CrearCitaRequest requestData);
    public CompletableFuture<ReagendarCitaResponse> reagendarCita(ReagendarCitaRequest requestData);
    public CompletableFuture<List<CitaPendienteResponse>> obtenerCitasPendientes();
    public CompletableFuture<List<CitaPendienteResponse>> obtenerCitasPorFecha(LocalDate filtro);
    public CompletableFuture<Void> cancelarCita(Integer idCita);
    public CompletableFuture<Void> completarCita(Integer idCita);
    public CompletableFuture<List<EmpleadoOptionResponse>> obtenerTodosLosEmpleados();
    public CompletableFuture<List<EmpleadoOptionResponse>> buscarEmpleadosPorFiltro(String filtro);
    public CompletableFuture<UsuarioResponse> inicioSesionRequest(IniciarSesionRequest requestData);
    public CompletableFuture<List<DatosEmpleadoResponse>> obtenerDatosHistorialEmpleado();
    public CompletableFuture<byte[]> obtenerReporte();
    public CompletableFuture<SignosVitalesResponse> obtenerSignosVitalesEmpleado(Integer id);
    public CompletableFuture<Map<String, List<DetalleResponse>>> obtenerAntecedentesEmpleado(Integer id);
    public CompletableFuture<Map<String, AtributoFisicoResponse>> obtenerAtributosFisicosEmpleado(Integer id);
    public CompletableFuture<ExpedienteConfigResponse> obtenerInfoConfiguracionExpediente();
}