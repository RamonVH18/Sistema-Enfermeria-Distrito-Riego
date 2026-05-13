/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilerias;

import controladores.RegistroAntecedentesController;
import java.util.Map;
import request.AgregarExpedienteRequest;
import request.AtributosFisicosRequest;

/**
 *
 * @author Ramon Valencia
 */
public class SesionExpedientes {
    
    private static SesionExpedientes sesion;
    private RegistroAntecedentesController antecedentes;
    
    private SesionExpedientes() {
    }
    
    public static SesionExpedientes getInstance() {
        if (sesion == null) {
            sesion = new SesionExpedientes();
        }
        return sesion;
    }
    
    public void inyectarControllerAntecedentes(RegistroAntecedentesController antecedentes) {
        this.antecedentes = antecedentes;
    }
    
    public AgregarExpedienteRequest construirExpediente (Map<String, AtributosFisicosRequest> atributos) {
        AgregarExpedienteRequest agregarExpediente = antecedentes.obtenerDatos();
        agregarExpediente.setAtributos(atributos);
        return agregarExpediente;
    }
}
