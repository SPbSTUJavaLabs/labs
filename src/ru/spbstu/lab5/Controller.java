package ru.spbstu.lab5;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class Controller {
    @FXML
    private Button btnStart;
    @FXML
    private TextArea textArea;

    private SimpleStringProperty outputStr;

    @FXML
    public void initialize() {
    }

}
