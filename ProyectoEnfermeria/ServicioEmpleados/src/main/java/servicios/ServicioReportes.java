package servicios;

import DAOs.CitaRepository;
import DAOs.EmpleadoRepository;
import DAOs.ExpedienteMedicoRepository;
import dtos.ReporteEmpleadosDTO;
import entidades.Cita;
import entidades.Empleado;
import enums.EstadoCita;
import interfaces.IServicioReportes;
import java.time.LocalDate;
import java.time.Period;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementación del servicio de reportes de empleados.
 * 
 * @author isaac
 */
@Service
public class ServicioReportes implements IServicioReportes {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private ExpedienteMedicoRepository expedienteMedicoRepository;

    @Override
    public ReporteEmpleadosDTO generarReporteEmpleados() {
        long totalEmpleados = empleadoRepository.count();
        
        if (totalEmpleados == 0) {
            return new ReporteEmpleadosDTO(0L, 0.0, 0L, 0L, 0L, 0L, Map.of(), Map.of(), Map.of(), Map.of(), 0.0);
        }

        long totalExpedientes = expedienteMedicoRepository.count();
        double tasaCobertura = (totalExpedientes * 100.0) / totalEmpleados;

        List<Cita> allCitas = citaRepository.findAll();
        long totalCitas = allCitas.size();
        long productividadCitas = allCitas.stream().filter(c -> c.getEstado() == EstadoCita.REALIZADA).count();
        long citasPendientes = allCitas.stream().filter(c -> c.getEstado() == EstadoCita.PENDIENTE).count();
        long citasCanceladas = allCitas.stream().filter(c -> c.getEstado() == EstadoCita.CANCELADA).count();

        // Motivos más frecuentes (Top 5)
        Map<String, Long> motivosMasFrecuentes = allCitas.stream()
                .filter(c -> c.getMotivo() != null)
                .collect(Collectors.groupingBy(Cita::getMotivo, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(5)
                .collect(Collectors.toMap(
                        Map.Entry::getKey, 
                        Map.Entry::getValue, 
                        (e1, e2) -> e1, 
                        LinkedHashMap::new));

        List<Empleado> empleados = empleadoRepository.findAll();

        Map<String, Long> distribucionDepto = empleados.stream()
                .filter(e -> e.getDepartamento() != null)
                .collect(Collectors.groupingBy(e -> e.getDepartamento().getNombre(), Collectors.counting()));

        Map<String, Long> distribucionGenero = empleados.stream()
                .filter(e -> e.getGenero() != null)
                .collect(Collectors.groupingBy(Empleado::getGenero, Collectors.counting()));

        Map<String, Long> distribucionUnidad = empleados.stream()
                .filter(e -> e.getUnidadTrabajo() != null)
                .collect(Collectors.groupingBy(Empleado::getUnidadTrabajo, Collectors.counting()));

        double promedioEdad = empleados.stream()
                .filter(e -> e.getFechaNacimiento() != null)
                .mapToInt(e -> Period.between(e.getFechaNacimiento(), LocalDate.now()).getYears())
                .average()
                .orElse(0.0);

        return new ReporteEmpleadosDTO(
                totalEmpleados,
                tasaCobertura,
                productividadCitas,
                totalCitas,
                citasPendientes,
                citasCanceladas,
                distribucionDepto,
                distribucionGenero,
                distribucionUnidad,
                motivosMasFrecuentes,
                promedioEdad
        );
    }
}
