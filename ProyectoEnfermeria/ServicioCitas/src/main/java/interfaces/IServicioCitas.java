package interfaces;

import dtos.CitaDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import request.ActualizarCitaRequest;
import request.CrearCitaRequest;
import response.ActualizarCitaResponse;
import response.CitaPendienteResponse;
import response.CrearCitaResponse;

/**
 *
 * @author Leonardo Flores Leyva - 252390
 */
public interface IServicioCitas {
    
    /**
     * Agenda una nueva cita.
     * @param cita Request con información de la cita a registrar en la base de datos.
     * @return Response con datos relevantes de la operación.
     */
    public CrearCitaResponse crear(@Valid CrearCitaRequest cita);
    
    /**
     * Reagenda una nueva cita.
     * @param cita Request con información de la cita a actualizar en la base de datos.
     * @return Response con datos relevantes de la operación.
     */
    public ActualizarCitaResponse actualizar(@Valid ActualizarCitaRequest cita);
    
    /**
     * Cancela una cita.
     */
    public void cancelar(@Valid Integer idCita);
    
    /**
     * Obtiene las citas pendientes.
     * @return Todas las citas de la base de datos.
     */
    public List<CitaPendienteResponse> obtenerCitasPendientes();
    
    /**
     * Obtiene una cita por su id.
     * @param id ID de la cita.
     * @return La cita solicitada o null.
     */
    public CitaDTO obtenerPorId(@NotNull Integer id);
    
    /**
     * Obtiene todas las citas pendientes en una fecha específica.
     * @param fecha Fecha a solicitar.
     * @return La lista de citas encontradas o null.
     */
    public List<CitaPendienteResponse> obtenerPorFechaPendiente(LocalDate fecha);
    
    /**
     * Busca la citas de un empleado por su nombre o curp.
     * @param nombreCurp Nombre o curp del empleado.
     * @return La lista de citas encontradas o null.
     */
    public List<CitaDTO> buscarPorNombreCurpPacientePendiente(String nombreCurp);
}