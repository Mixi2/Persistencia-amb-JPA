package model;

import java.util.Locale;
import java.util.ResourceBundle;

import controlador.Controller;
import controlador.Controller_add_note;
import controlador.Controller_add_student;
import controlador.Controller_add_subject;
import controlador.Controller_remove_student;
import controlador.Controller_remove_subject;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Init_windows {

    private Stage addNoteStage;
    private Stage addStudentStage;
    private Stage rmStudentStage;
    private Stage addSubjectStage;
    private Stage rmSubjectStage;


    public Init_windows(GestioLlistes model, Controller controlador) {

        FXMLLoader addNoteLoader = new FXMLLoader(getClass().getResource("../vista/add_note.fxml"), ResourceBundle.getBundle("strings", Locale.getDefault()));
        FXMLLoader addStudentLoader = new FXMLLoader(getClass().getResource("../vista/add_student.fxml"), ResourceBundle.getBundle("strings", Locale.getDefault()));
        FXMLLoader rmStudentLoader = new FXMLLoader(getClass().getResource("../vista/remove_student.fxml"), ResourceBundle.getBundle("strings", Locale.getDefault()));
        FXMLLoader addSubjectLoader = new FXMLLoader(getClass().getResource("../vista/add_subject.fxml"), ResourceBundle.getBundle("strings", Locale.getDefault()));
        FXMLLoader rmSubjectLoader = new FXMLLoader(getClass().getResource("../vista/remove_subject.fxml"), ResourceBundle.getBundle("strings", Locale.getDefault()));

        Controller_add_note controlador1 = new Controller_add_note();
        controlador1.injecta(model);
        addNoteLoader.setControllerFactory(c -> controlador1);
        try {
            Parent root2 = (Parent) addNoteLoader.load();
            addNoteStage = new Stage();
            addNoteStage.setTitle("Add note");
            addNoteStage.setScene(new Scene(root2));

        } catch (Exception e){
            System.out.println(e);
        }

        Controller_add_student controlador2 = new Controller_add_student();
        controlador2.injecta(model);
        addStudentLoader.setControllerFactory(c -> controlador2);
        try {
            Parent root3 = (Parent) addStudentLoader.load();
            addStudentStage= new Stage();
            addStudentStage.setTitle("Add Student");
            addStudentStage.setScene(new Scene(root3));

        } catch (Exception e){
            System.out.println(e);
        }
        
        Controller_remove_student controlador3 = new Controller_remove_student();
        controlador3.injecta(model);
        rmStudentLoader.setControllerFactory(c -> controlador3);
        try {
            Parent root4 = (Parent) rmStudentLoader.load();
            rmStudentStage = new Stage();
            rmStudentStage.setTitle("Remove Student");
            rmStudentStage.setScene(new Scene(root4));

        } catch (Exception e){
            System.out.println(e);
        }

        Controller_add_subject controlador4 = new Controller_add_subject();
        controlador4.injecta(model);
        addSubjectLoader.setControllerFactory(c -> controlador4);
        try {
            Parent root5 = (Parent) addSubjectLoader.load();
            addSubjectStage = new Stage();
            addSubjectStage.setTitle("Add Subject");
            addSubjectStage.setScene(new Scene(root5));

        } catch (Exception e){
            System.out.println(e);
        }

        Controller_remove_subject controlador5 = new Controller_remove_subject();
        controlador5.injecta(model);
        rmSubjectLoader.setControllerFactory(c -> controlador5);
        try {
            Parent root6 = (Parent) rmSubjectLoader.load();
            rmSubjectStage = new Stage();
            rmSubjectStage.setTitle("Remove Subject");
            rmSubjectStage.setScene(new Scene(root6));

        } catch (Exception e){
            System.out.println(e);
        }        
        
    }


    public void showAddNote() {
        addNoteStage.show();
    }

    public void showAddStudent() {
        addStudentStage.show();
    }

    public void showRmStudent() {
        rmStudentStage.show();
    }

    public void showAddSubject() {
        addSubjectStage.show();
    }

    public void showRmSubject() {
        rmSubjectStage.show();
    }

    
}
