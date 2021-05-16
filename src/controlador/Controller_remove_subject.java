package controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.GestioLlistes;

public class Controller_remove_subject {

    private GestioLlistes model;

    @FXML
    private TextField textField_code_subject_win;

    @FXML
    private Button btn_close;

    @FXML
    void btn_add_remove_win(ActionEvent event) {
        int code = Integer.parseInt(textField_code_subject_win.getText());
        try {
            model.removeSubject(code);
            textField_code_subject_win.setText("");
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