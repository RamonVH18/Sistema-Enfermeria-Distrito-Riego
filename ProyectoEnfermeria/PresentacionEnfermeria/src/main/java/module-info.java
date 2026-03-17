module com.mycompany.presentacionenfermeria {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires java.base;
    requires java.net.http;

    opens com.mycompany.presentacionenfermeria to javafx.fxml;
    exports com.mycompany.presentacionenfermeria;
}
