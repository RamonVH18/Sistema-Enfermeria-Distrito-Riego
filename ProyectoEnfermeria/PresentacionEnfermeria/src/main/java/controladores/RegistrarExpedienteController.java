/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 *
 * @author Ramon Valencia
 */
public class RegistrarExpedienteController implements Initializable{
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void handleAgregarAntecedente() {
//        String tipo = cbTipoAntecedente.getValue();
//        String desc = txtDescripcion.getText().trim();
//
//        // 1. Validar campos obligatorios
//        if (tipo == null || desc.isEmpty()) {
//            mostrarAlerta("Campos incompletos", "Por favor seleccione un tipo y escriba una descripción.");
//            return;
//        }
//
//        // 2. Validar duplicados (UX)
//        boolean duplicado = listaObservable.stream()
//                .anyMatch(a -> a.getTipo().equals(tipo) && a.getDescripcion().equalsIgnoreCase(desc));
//
//        if (duplicado) {
//            mostrarAlerta("Dato duplicado", "Este antecedente ya ha sido agregado a la lista inferior.");
//            return;
//        }
//
//        // 3. Agregar a la tabla
//        listaObservable.add(new AntecedenteDTO(tipo, desc));
//
//        // 4. Limpiar campos para nueva entrada rápida
//        txtDescripcion.clear();
//        txtDescripcion.requestFocus();
    }
}
