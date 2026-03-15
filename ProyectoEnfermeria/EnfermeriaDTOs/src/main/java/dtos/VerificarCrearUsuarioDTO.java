package dtos;

/**
 *
 * @author Leonardo Flores Leyva - 252390
 */
public class VerificarCrearUsuarioDTO {
    
    private final String email;
    private final String password;

    public VerificarCrearUsuarioDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {return email;}

    public String getPassword() {return password;}
}