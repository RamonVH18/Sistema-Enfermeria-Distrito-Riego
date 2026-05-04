package dtos;

import java.util.Map;

/**
 * DTO para representar el reporte de empleados con datos estadísticos.
 *
 * @author isaac
 */
public class ReporteEmpleadosDTO {

    private Long totalEmpleados;
    private Double tasaCobertura;
    private Long productividadCitas;
    private Long totalCitas;
    private Long citasPendientes;
    private Long citasCanceladas;
    private Map<String, Long> distribucionPorDepartamento;
    private Map<String, Long> distribucionPorGenero;
    private Map<String, Long> distribucionPorUnidadTrabajo;
    private Map<String, Long> motivosMasFrecuentes;
    private Double promedioEdad;

    public ReporteEmpleadosDTO() {
    }

    public ReporteEmpleadosDTO(Long totalEmpleados, Double tasaCobertura, Long productividadCitas, Long totalCitas, Long citasPendientes, Long citasCanceladas, Map<String, Long> distribucionPorDepartamento, Map<String, Long> distribucionPorGenero, Map<String, Long> distribucionPorUnidadTrabajo, Map<String, Long> motivosMasFrecuentes, Double promedioEdad) {
        this.totalEmpleados = totalEmpleados;
        this.tasaCobertura = tasaCobertura;
        this.productividadCitas = productividadCitas;
        this.totalCitas = totalCitas;
        this.citasPendientes = citasPendientes;
        this.citasCanceladas = citasCanceladas;
        this.distribucionPorDepartamento = distribucionPorDepartamento;
        this.distribucionPorGenero = distribucionPorGenero;
        this.distribucionPorUnidadTrabajo = distribucionPorUnidadTrabajo;
        this.motivosMasFrecuentes = motivosMasFrecuentes;
        this.promedioEdad = promedioEdad;
    }

    public Long getTotalEmpleados() {
        return totalEmpleados;
    }

    public void setTotalEmpleados(Long totalEmpleados) {
        this.totalEmpleados = totalEmpleados;
    }

    public Double getTasaCobertura() {
        return tasaCobertura;
    }

    public void setTasaCobertura(Double tasaCobertura) {
        this.tasaCobertura = tasaCobertura;
    }

    public Long getProductividadCitas() {
        return productividadCitas;
    }

    public void setProductividadCitas(Long productividadCitas) {
        this.productividadCitas = productividadCitas;
    }

    public Long getTotalCitas() {
        return totalCitas;
    }

    public void setTotalCitas(Long totalCitas) {
        this.totalCitas = totalCitas;
    }

    public Long getCitasPendientes() {
        return citasPendientes;
    }

    public void setCitasPendientes(Long citasPendientes) {
        this.citasPendientes = citasPendientes;
    }

    public Long getCitasCanceladas() {
        return citasCanceladas;
    }

    public void setCitasCanceladas(Long citasCanceladas) {
        this.citasCanceladas = citasCanceladas;
    }

    public Map<String, Long> getDistribucionPorDepartamento() {
        return distribucionPorDepartamento;
    }

    public void setDistribucionPorDepartamento(Map<String, Long> distribucionPorDepartamento) {
        this.distribucionPorDepartamento = distribucionPorDepartamento;
    }

    public Map<String, Long> getDistribucionPorGenero() {
        return distribucionPorGenero;
    }

    public void setDistribucionPorGenero(Map<String, Long> distribucionPorGenero) {
        this.distribucionPorGenero = distribucionPorGenero;
    }

    public Map<String, Long> getDistribucionPorUnidadTrabajo() {
        return distribucionPorUnidadTrabajo;
    }

    public void setDistribucionPorUnidadTrabajo(Map<String, Long> distribucionPorUnidadTrabajo) {
        this.distribucionPorUnidadTrabajo = distribucionPorUnidadTrabajo;
    }

    public Map<String, Long> getMotivosMasFrecuentes() {
        return motivosMasFrecuentes;
    }

    public void setMotivosMasFrecuentes(Map<String, Long> motivosMasFrecuentes) {
        this.motivosMasFrecuentes = motivosMasFrecuentes;
    }

    public Double getPromedioEdad() {
        return promedioEdad;
    }

    public void setPromedioEdad(Double promedioEdad) {
        this.promedioEdad = promedioEdad;
    }
}
