package entidades;

import java.util.Map;

/**
 * Entidad (POJO) para representar el reporte de empleados.
 * 
 * @author isaac
 */
public class ReporteEmpleados {
    
    private Long totalEmpleados;
    private Double tasaCobertura;
    private Long productividadCitas;
    private Long totalCitas;
    private Long citasPendientes;
    private Long citasCanceladas;
    private Map<String, Long> distribucionPorDepartamento;

    public ReporteEmpleados() {
    }

    public ReporteEmpleados(Long totalEmpleados, Double tasaCobertura, Long productividadCitas, Long totalCitas, Long citasPendientes, Long citasCanceladas, Map<String, Long> distribucionPorDepartamento) {
        this.totalEmpleados = totalEmpleados;
        this.tasaCobertura = tasaCobertura;
        this.productividadCitas = productividadCitas;
        this.totalCitas = totalCitas;
        this.citasPendientes = citasPendientes;
        this.citasCanceladas = citasCanceladas;
        this.distribucionPorDepartamento = distribucionPorDepartamento;
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
}
