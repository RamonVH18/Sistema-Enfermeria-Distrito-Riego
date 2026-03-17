module com.mycompany.presentacionenfermeria {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires java.base;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310; // ESTA ES LA CLAVE

    opens com.mycompany.presentacionenfermeria to javafx.fxml;
    exports com.mycompany.presentacionenfermeria;
}
