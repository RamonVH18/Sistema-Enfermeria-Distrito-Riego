/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AtributosFisicos;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ramon Valencia
 */
@XmlRootElement(name = "AgudezaVisual") // Nodo raíz
@XmlAccessorType(XmlAccessType.FIELD)
public class AgudezaVisual {

    private List<OjoData> ojos;

    private String nota;

    public AgudezaVisual() {
    }

    public AgudezaVisual(List<OjoData> ojos, String nota) {
        this.ojos = ojos;
        this.nota = nota;
    }

    public List<OjoData> getOjos() {
        return ojos;
    }

    public void setOjos(List<OjoData> ojos) {
        this.ojos = ojos;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> mapaPrincipal = new HashMap<>();

        // Protegemos el dominio: Validamos que existan los ojos para evitar NullPointerException
        if (ojos != null && !ojos.isEmpty()) {
            for (OjoData ojo : ojos) {
                // Creamos un sub-mapa por cada ojo usando su 'tipo' (DERECHO/IZQUIERDO) como llave
                String llaveOjo = "ojo_" + ojo.getTipo().toLowerCase();
                mapaPrincipal.put(llaveOjo, ojo.toMap());
            }
        }

        mapaPrincipal.put("nota", this.nota);
        return mapaPrincipal;
    }

}

@XmlAccessorType(XmlAccessType.FIELD)
class OjoData {

    @XmlAttribute // Aparecerá como <Ojo tipo="DERECHO">
    private String tipo;

    private String lentes;

    private String campoVisual;

    private String visionCromatica;

    public OjoData() {
    }

    public OjoData(String tipo, String lentes, String campoVisual, String visionCromatica) {
        this.tipo = tipo;
        this.lentes = lentes;
        this.campoVisual = campoVisual;
        this.visionCromatica = visionCromatica;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getLentes() {
        return lentes;
    }

    public void setLentes(String lentes) {
        this.lentes = lentes;
    }

    public String getCampoVisual() {
        return campoVisual;
    }

    public void setCampoVisual(String campoVisual) {
        this.campoVisual = campoVisual;
    }

    public String getVisionCromatica() {
        return visionCromatica;
    }

    public void setVisionCromatica(String visionCromatica) {
        this.visionCromatica = visionCromatica;
    }

    public Map<String, String> toMap() {
        Map<String, String> m = new HashMap<>();
        m.put("lentes", this.lentes);
        m.put("campo_visual", this.campoVisual);
        m.put("vision_cromatica", this.visionCromatica);
        return m;
    }
}
