package controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.GestioLlistes;

public class Controller_remove_student {

    private GestioLlistes model;

    @FXML
    private TextField textField_dni_remove;

    @FXML
    private Button btn_close;
    
    @FXML
    void btn_remove_student(ActionEvent event) {
        String dni = textField_dni_remove.getText();
        try {
            model.removeStudent(dni);
            textField_dni_remove.setText("");
            Stage stage = (Stage) btn_close.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void injecta(GestioLlistes model){
        this.model = model;
    }
}
