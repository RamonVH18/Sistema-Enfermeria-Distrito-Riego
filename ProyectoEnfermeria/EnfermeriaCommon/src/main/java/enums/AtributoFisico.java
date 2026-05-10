/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enums;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Ramon Valencia
 */
public enum AtributoFisico {
    AGUDEZA_VISUAL(Arrays.asList("ojo_derecho", "ojo_izquierdo")),
    CABEZA(Arrays.asList("FORMA", "TAMAÑO", "PELO", "CARA")),
    OJOS(Arrays.asList("REFLEJOS", "PARPADOS", "PUPILAS", "CONJUNTIVAS", "FONDO OJOS", "NARIZ", "MUCOSAS", "TABIQUE")),
    BOCA(Arrays.asList("MUCOSAS", "DENTADURA", "LENGUA","ENCIAS","FARINGE","AMIGDALAS")),
    OIDOS(Arrays.asList("PABELLON", "CONDUCTO", "TIMPANO")),
    CUELLO(Arrays.asList("GANGLIOS", "MOVILIDAD", "TIROIDES", "PULSOS")),
    AREA_PRECORDIAL(Arrays.asList("FORMA", "RUIDOS CARDIACOS", "CAMPOS PULMONARES")),
    ABDOMEN(Arrays.asList("FORMA", "VISCEROMEGALIAS", "HERNIAS")),
    COLUMNA_VERTEBRAL(Arrays.asList("INTEGRIDAD", "FORMA", "TONO MUSCULAR", "SENSIBILIDAD", "FUERZA")),
    LUMBAR(Arrays.asList("INTEGRIDAD", "FORMA", "TONO MUSCULAR", "SENSIBILIDAD", "FUERZA")),
    MIEMBROS_TORACICOS(Arrays.asList("INTEGRIDAD", "FORMA", "ARTICULACIONES", "TONO MUSCULAR", "REFLEJOS", "SENSIBILIDAD")),
    MIEMBROS_PELVICOS(Arrays.asList("INTEGRIDAD", "FORMA", "ARTICULACIONES", "TONO MUSCULAR", "REFLEJOS", "SENSIBILIDAD"));

    // El atributo donde se guardará la lista
    private final List<String> campos;

    // Constructor privado (obligatorio para Enums)
    AtributoFisico(List<String> campos) {
        this.campos = campos;
    }

    // Getter para acceder a la información
    public List<String> getCampos() {
        return campos;
    }

}
