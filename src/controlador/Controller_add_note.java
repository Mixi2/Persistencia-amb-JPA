package controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.GestioLlistes;

public class Controller_add_note {

    private GestioLlistes model;

    @FXML
    private TextField textField_dni_note;

    @FXML
    private TextField textField_subject_code;

    @FXML
    private TextField textField_ordinary_note_note;

    @FXML
    private TextField textField_extraordinary_note_note;

    @FXML
    private Button btn_close;


    @FXML
    void btn_add_note_win(ActionEvent event) {
        String dni = textField_dni_note.getText();
        int subject_code = Integer.parseInt(textField_subject_code.getText()) ;
        int ordinary_note = Integer.parseInt(textField_ordinary_note_note.getText());
        int extraordinary_note = Integer.parseInt(textField_extraordinary_note_note.getText());

        try {
            model.createRecord(dni, subject_code, ordinary_note, extraordinary_note);
            textField_dni_note.setText("");
            textField_subject_code.setText("");
            textField_ordinary_note_note.setText("");
            textField_extraordinary_note_note.setText("");
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
