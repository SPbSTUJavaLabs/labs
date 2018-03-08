package ru.spbstu.lab5;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Controller {
    @FXML
    private Button btnStart;

    @FXML
    private TextArea textArea;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private VBox box;
    // private SimpleStringProperty outputStr;

    private Executable executable;

    @FXML
    public void initialize() {
        for (int i = 1; i < 5; i++) {
            comboBox.getItems().add("Lab " + i);
        }

        btnStart.setOnMouseClicked(event -> {
            textArea.setText("Counting");
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                if (executable == null) {
                    return;
                }
                executable.exec();
            }
        });
    }

    @FXML
    private void choseLab() {
        String value = comboBox.getValue();
        System.out.println(value);
        box.getChildren().clear();
        switch (value) {
            case "Lab 1":
                executable = initializeLab1();
                break;
            case "Lab 2":
                executable = initializeLab2();
                break;
            case "Lab 3":
                executable = initializeLab3();
                break;
            case "Lab 4":
                executable = initializeLab4();
                break;
        }
    }

    private Executable initializeLab1() {
        Pair<Label, Spinner<Double>> fields[] = new Pair[3];
        for (int i = 0; i < 3; i++) {
            fields[i] = new Pair<>(new Label("Length " + i), new Spinner<Double>(0, 1000000, 0, 0.01));
            fields[i].getValue().setEditable(true);
            box.getChildren().add(fields[i].getKey());
            box.getChildren().add(fields[i].getValue());
        }
        return () -> {
            double lengths[] = new double[3];
            for (int i = 0; i < 3; i++) {
                lengths[i] = fields[i].getValue().getValue();
                System.out.println(lengths[i]);
                textArea.setText(LabsController.lab1(lengths));
            }
        };

    }

    private Executable initializeLab2() {
        return () -> {
            textArea.setText(LabsController.lab2());
        };
    }

    private Executable initializeLab3() {
        Label label = new Label("Amount of iterations");
        Spinner<Integer> spinner = new Spinner<>(0, 1000000000, 0);
        spinner.setEditable(true);
        box.getChildren().addAll(label, spinner);
        return () -> {
            textArea.setText(LabsController.lab3(spinner.getValue()));
        };
    }

    private Executable initializeLab4() {
        box.getChildren().add(new Label("Amount of students"));
        Spinner<Integer> students = new Spinner<>(0, 1000000000, 1);
        students.setEditable(true);
        box.getChildren().add(students);
        box.getChildren().add(new Label("Amount of teachers"));
        Spinner<Integer> teachers = new Spinner<>(0, 1000000000, 1);
        teachers.setEditable(true);
        box.getChildren().add(teachers);

        return () -> {
            textArea.setText(LabsController.lab4(students.getValue(), teachers.getValue()));
        };
    }

    interface Executable {
        void exec();
    }
}
