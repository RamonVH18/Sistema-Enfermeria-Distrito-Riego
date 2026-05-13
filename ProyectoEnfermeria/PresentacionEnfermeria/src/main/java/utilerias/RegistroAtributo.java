/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilerias;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Ramon Valencia
 */
public class RegistroAtributo {
    private final SimpleStringProperty componente = new SimpleStringProperty();
    private final SimpleBooleanProperty normal = new SimpleBooleanProperty();
    private final SimpleBooleanProperty anormal = new SimpleBooleanProperty();
    private final SimpleStringProperty nota = new SimpleStringProperty();

    public RegistroAtributo(String comp) {
        this.componente.set(comp);
        
        // Listener: Si Normal se vuelve TRUE, Anormal se vuelve FALSE
        normal.addListener((obs, oldVal, newVal) -> {
            if (newVal) anormal.set(false);
        });
        
        // Listener: Si Anormal se vuelve TRUE, Normal se vuelve FALSE
        anormal.addListener((obs, oldVal, newVal) -> {
            if (newVal) normal.set(false);
        });
    }

    // Getters
    public SimpleBooleanProperty normalProperty() { return normal; }
    public SimpleBooleanProperty anormalProperty() { return anormal; }
    public SimpleStringProperty componenteProperty() { return componente; }
    public SimpleStringProperty notaProperty() { return nota; }
}