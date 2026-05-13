package response;

/**
 *
 * @author isaac
 */
public class UsuarioResponse {

    private String nombre;
    private String email;

    public UsuarioResponse() {}

    public UsuarioResponse(String nombre, String email) {
        this.email = email;
        this.nombre = nombre;
    }

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}
}