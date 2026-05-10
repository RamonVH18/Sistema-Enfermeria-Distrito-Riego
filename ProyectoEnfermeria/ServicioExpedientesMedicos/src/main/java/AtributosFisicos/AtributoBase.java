/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AtributosFisicos;

import enums.AtributoFisico;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ramon Valencia
 */
@XmlRootElement(name = "AtributoBase")
@XmlAccessorType(XmlAccessType.FIELD)
public class AtributoBase {
    
    @XmlAttribute(name = "tipo")
    private String tipo;
    
    @XmlElement(name = "Parte")
    private List<ParteCuerpo> partes;

    public AtributoBase() {
        this.partes = new ArrayList<>();
    }
    
    public AtributoBase(String tipo, List<ParteCuerpo> partes) {
        this.tipo = tipo;
        this.partes = partes;
    }
    
    public static AtributoBase fromMap(Map<String, Object> map, AtributoFisico atributo) {
        
        String tipo = (String) map.get("tipo");
        List<ParteCuerpo> partes = new ArrayList<>();
        List<String> campos = atributo.getCampos();
        for (String c: campos) {
            ParteCuerpo p = new ParteCuerpo();
            Map<String, String> m = (Map<String, String>) map.get(c);
            p.setNombre(c);
            p.setEstado(m.get("estado"));
            p.setEstado(m.get("nota"));
            partes.add(p);
        }
        return new AtributoBase(tipo, partes);
    }

    public List<ParteCuerpo> getPartes() {
        return partes;
    }

    public void setPartes(List<ParteCuerpo> partes) {
        this.partes = partes;
    }
    
    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        // Agregamos el tipo al mapa para que el Front-End sepa qué está dibujando
        map.put("tipo", tipo); 
        for (ParteCuerpo p : partes) {
            map.put(p.getNombre(), p.toMap());
        }
        return map;
    }
}

@XmlAccessorType(XmlAccessType.FIELD)
class ParteCuerpo {
    @XmlAttribute
    private String nombre; // FORMA, TAMAÑO, PELO, CARA

    private String estado; // "NORMAL" o "ANORMAL"

    private String nota;

    public ParteCuerpo() {
    }

    public ParteCuerpo(String nombre, String estado, String nota) {
        this.nombre = nombre;
        this.estado = estado;
        this.nota = nota;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }
    
    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        map.put("estado", estado);
        map.put("nota", nota);
        return map;
    }
}
