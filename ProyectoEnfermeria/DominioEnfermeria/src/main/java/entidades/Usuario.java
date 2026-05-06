package entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 *
 * @author isaac
 */
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToOne
    @JoinColumn(name = "id_empleado", nullable = false, unique = true)
    private Empleado empleado;
    
    public Usuario () {}

    public Usuario(String email, String password, Empleado empleado) {
        this.email = email;
        this.password = password;
        this.empleado = empleado;
    }

    public Integer getIdUsuario() {return idUsuario;}

    public void setIdUsuario(Integer idUsuario) {this.idUsuario = idUsuario;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public Empleado getEmpleado() {return empleado;}

    public void setEmpleado(Empleado empleado) {this.empleado = empleado;}
}