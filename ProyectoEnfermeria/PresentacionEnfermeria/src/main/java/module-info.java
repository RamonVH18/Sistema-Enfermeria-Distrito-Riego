module com.mycompany.presentacionenfermeria {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.presentacionenfermeria to javafx.fxml;
    exports com.mycompany.presentacionenfermeria;
}
