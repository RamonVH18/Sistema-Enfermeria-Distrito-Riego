package mapper;

import entidades.Empleado;
import java.util.ArrayList;
import java.util.List;
import response.EmpleadoOptionResponse;

/**
 *
 * @author Leonardo Flores Leyva - 252390
 */
public class EmpleadoMapper {
    
    public static EmpleadoOptionResponse toOptionResponse(Empleado e) {
        return new EmpleadoOptionResponse(
                            e.getId(), 
                            e.getNombres() 
                                    + " " + e.getApellidoPaterno() 
                                    + " " + e.getApellidoMaterno()
                    );
    }
    
    public static List<EmpleadoOptionResponse> toOptionResponse(List<Empleado> empleados) {
        List<EmpleadoOptionResponse> emps = new ArrayList<>();
        for (Empleado e:  empleados) {
            emps.add(
                    new EmpleadoOptionResponse(
                            e.getId(), 
                            e.getNombres() 
                                    + " " + e.getApellidoPaterno() 
                                    + " " + e.getApellidoMaterno()
                    )
            );
        }
        
        return emps;
    }
}