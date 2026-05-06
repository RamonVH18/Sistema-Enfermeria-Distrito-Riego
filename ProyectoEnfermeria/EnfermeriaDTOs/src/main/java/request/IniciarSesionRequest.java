package request;

import jakarta.validation.constraints.NotNull;

/**
 *
 * @author isaac
 */
public class IniciarSesionRequest {

    @NotNull
    private String email;

    @NotNull
    private String contrasena;

    public IniciarSesionRequest() {}

    public IniciarSesionRequest(String email, String contrasena) {
        this.email = email;
        this.contrasena = contrasena;
    }

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getContrasena() {return contrasena;}

    public void setContrasena(String contrasena) {this.contrasena = contrasena;}

}