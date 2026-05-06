package entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author Ramon Valencia
 * @author Leonardo Flores Leyva - 252390
 */
@Entity
@Table(name = "direcciones_empleado")
public class DireccionEmpleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dir_empl")
    private Integer idDireccion;

    @Column(name = "numero", nullable = false)
    private int numero;

    @Column(name = "calle", nullable = false)
    private String calle;

    @Column(name = "municipio", nullable = false)
    private String municipio;

    @Column(name = "codigo_postal", nullable = false)
    private int cp;

    @Column(name = "ciudad", nullable = false)
    private String ciudad;

    public DireccionEmpleado() {}

    public DireccionEmpleado(
            int numero, 
            String calle, 
            String municipio, 
            int cp, 
            String ciudad
    ) {
        this.numero = numero;
        this.calle = calle;
        this.municipio = municipio;
        this.cp = cp;
        this.ciudad = ciudad;
    }

    public DireccionEmpleado(
            Integer id, 
            int numero, 
            String calle, 
            String municipio, 
            int cp, 
            String ciudad
    ) {
        this.idDireccion = id;
        this.numero = numero;
        this.calle = calle;
        this.municipio = municipio;
        this.cp = cp;
        this.ciudad = ciudad;
    }

    public Integer getId() {return idDireccion;}

    public void setId(Integer id) {this.idDireccion = id;}

    public int getNumero() {return numero;}

    public void setNumero(int numero) {this.numero = numero;}

    public String getCalle() {return calle;}

    public void setCalle(String calle) {this.calle = calle;}

    public String getMunicipio() {return municipio;}

    public void setMunicipio(String municipio) {this.municipio = municipio;}

    public int getCp() {return cp;}

    public void setCp(int cp) {this.cp = cp;}

    public String getCiudad() {return ciudad;}

    public void setCiudad(String ciudad) {this.ciudad = ciudad;}
}