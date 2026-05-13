/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilerias;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;

/**
 *
 * @author Ramon Valencia
 */
public class TablaRegistro extends VBox {

    public TablaRegistro(String titulo, String[] filas) {
        Label lblTitulo = new Label(titulo);
        lblTitulo.setStyle("-fx-font-weight: bold;");

        TableView<RegistroAtributo> tableView = new TableView<>();
        tableView.setEditable(true);

        TableColumn<RegistroAtributo, String> colElemento = new TableColumn<>("Elemento");
        colElemento.setCellValueFactory(data -> data.getValue().componenteProperty());
        colElemento.setPrefWidth(150);

        // Columnas Normal/Anormal
        TableColumn<RegistroAtributo, Boolean> colNormal = new TableColumn<>("Normal");
        colNormal.setCellValueFactory(data -> data.getValue().normalProperty());
        colNormal.setCellFactory(CheckBoxTableCell.forTableColumn(colNormal));
        colNormal.setPrefWidth(70);

        TableColumn<RegistroAtributo, Boolean> colAnormal = new TableColumn<>("Anormal");
        colAnormal.setCellValueFactory(data -> data.getValue().anormalProperty());
        colAnormal.setCellFactory(CheckBoxTableCell.forTableColumn(colAnormal));
        colAnormal.setPrefWidth(70);

        // Columna Nota (Más grande)
        TableColumn<RegistroAtributo, String> colNota = new TableColumn<>("Nota");
        colNota.setCellValueFactory(data -> data.getValue().notaProperty());
        colNota.setEditable(true);
        colNota.setCellFactory(TextFieldTableCell.forTableColumn());
        colNota.setPrefWidth(300); // Tamaño mayor

        tableView.getColumns().addAll(colElemento, colNormal, colAnormal, colNota);

        ObservableList<RegistroAtributo> data = FXCollections.observableArrayList();
        for (String f : filas) {
            data.add(new RegistroAtributo(f));
        }
        tableView.setItems(data);
        // Quitar espacio extra: 
        // Usamos -1 para que la tabla no tenga altura mínima extra
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setPrefHeight((filas.length * 28) + 28);

        tableView.getColumns().forEach(col -> {
            col.setReorderable(false);
            col.setResizable(false); // Evita que el usuario arrastre el borde de la columna
        });

// 3. Evitar que las filas se puedan seleccionar (si es tu intención) 
// o simplemente bloquear el ordenamiento por cabecera
        for (TableColumn<RegistroAtributo, ?> col : tableView.getColumns()) {
            col.setSortable(false); // Evita que al hacer clic en la cabecera se ordenen las filas
        }

        this.setSpacing(5); // Espacio entre Label y Tabla
        this.getChildren().addAll(lblTitulo, tableView);
    }
//    
}
