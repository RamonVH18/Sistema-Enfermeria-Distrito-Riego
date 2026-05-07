package enums;

/**
 *
 * @author Ramon Valencia
 */
public enum TipoSangre {
    O_POS("O+"),
    O_NEG("O-"),
    A_POS("A+"),
    A_NEG("A-"),
    B_POS("B+"),
    B_NEG("B-"),
    AB_POS("AB+"),
    AB_NEG("AB-");

    private final String valor;

    TipoSangre(String valor) {this.valor = valor;}

    public String getValor() {return valor;}
    
    public static TipoSangre desdeString(String valorBuscado) {
        if (valorBuscado == null) return null;
        
        for (TipoSangre tipo : TipoSangre.values()) {
            // Comparamos el valor interno (descripcion) con lo que recibimos
            if (tipo.valor.equalsIgnoreCase(valorBuscado.trim())) {
                return tipo;
            }
        }
        // Si no lo encuentra, puedes lanzar error o devolver null
        throw new IllegalArgumentException("No se encontró el tipo: " + valorBuscado);
    }
}