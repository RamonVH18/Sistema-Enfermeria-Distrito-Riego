/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;



/**
 *
 * @author Ramon Valencia
 */
public class CitasDAO {

    public CitasDAO() {
    }
    
//    public void crearSerieDeCitas(Cita citaBase) {
//
//        try (EntityManager em = ConexionDB.getEntityManager()) {
//
//            em.getTransaction().begin();
//            Serie serie = citaBase.getSerie();
//            // Guardamos la serie
//            em.persist(serie);
//
//            LocalDate fechaActual = serie.getFechaInicio();
//            LocalTime horaCita = serie.getHora();
//
//            while (!fechaActual.isAfter(serie.getFechaFin())) {
//
//                Cita cita = new Cita();
//
//                cita.setFecha_hora(LocalDateTime.of(fechaActual, horaCita));
//                cita.setEstado(EstadoCita.PENDIENTE);
//                cita.setMotivo("Cita automática");
//                cita.setDuracionMin(citaBase.getDuracionMin());
//                cita.setEmpleado(citaBase.getEmpleado());
//                cita.setEnfermero(citaBase.getEnfermero());
//                cita.setSerie(serie);
//
//                em.persist(cita);
//
//                // avanzar según el periodo
//                switch (serie.getPeriodo()) {
//
//                    case SEMANAL:
//                        fechaActual = fechaActual.plusWeeks(1);
//                        break;
//
//                    case MENSUAL:
//                        fechaActual = fechaActual.plusMonths(1);
//                        break;
//
//                    case BIMESTRAL:
//                        fechaActual = fechaActual.plusMonths(2);
//                        break;
//                    default:
//                        throw new IllegalArgumentException("Periodo no válido");
//                }
//            }
//
//            em.getTransaction().commit();
//
//        }
//    }
}
