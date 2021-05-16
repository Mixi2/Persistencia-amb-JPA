package controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.GestioLlistes;

public class Controller_add_student {

    GestioLlistes model;

    @FXML
    private TextField textField_dni;

    @FXML
    private TextField textField_name;

    @FXML
    private TextField textField_address;

    @FXML
    private TextField textField_phone;

    @FXML
    private Button btn_close;

    @FXML
    void btn_add_student_win(ActionEvent event) {
        String dni = textField_dni.getText();
        String name = textField_name.getText();
        String address = textField_address.getText();
        String phone = textField_phone.getText();
        
        try {
            model.createStudent(dni, name, address, phone);
            textField_dni.setText("");
            textField_name.setText("");
            textField_address.setText("");
            textField_phone.setText("");
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
