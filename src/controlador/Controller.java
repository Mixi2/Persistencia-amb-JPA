package controlador;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.GestioLlistes;
import model.Init_windows;
import model.Student;

public class Controller implements Initializable{

    /* Controller variables */

    private Init_windows init_windows;
    private GestioLlistes model;
    ArrayList<Integer> subjects_list;
    ArrayList<Student> students_list;


    /* Main window */
    @FXML
    private ComboBox<Integer> comboBox_subject;

    @FXML
    private Label lbl_dni;

    @FXML
    private Label lbl_name;

    @FXML
    private Label lbl_address;

    @FXML
    private Label lbl_phone;

    @FXML
    private ListView<Student> listView_students;

    @FXML
    private Label lbl_ordinary_note;

    @FXML
    private Label lbl_extraordinary_note;

    @FXML
    void btn_add_note(ActionEvent event) throws Exception{
        init_windows.showAddNote();
    }

    @FXML
    void btn_add_student(ActionEvent event) throws Exception{
        init_windows.showAddStudent();
    }

    @FXML
    void btn_remove_student(ActionEvent event) throws Exception{
        init_windows.showRmStudent();
    }

    
    @FXML
    void btn_add_subject(ActionEvent event) {
        init_windows.showAddSubject();
    }


    @FXML
    void btn_remove_subject(ActionEvent event) {
        init_windows.showRmSubject();
    }
    
    @FXML
    void btn_search(ActionEvent event) {
        students_list = new ArrayList<Student>();

        if(comboBox_subject.getSelectionModel().getSelectedItem() != null){
            for (int i = 0; i < model.getRecordsList().size(); i++) {
                if(model.getRecordsList().get(i).getSubject_code() == comboBox_subject.getSelectionModel().getSelectedItem()){
                    for (int j = 0; j < model.getStudentsList().size(); j++) {
                        if (model.getStudentsList().get(j).getDni().equals(model.getRecordsList().get(i).getDni())) {
                            students_list.add(model.getStudentsList().get(j));
                        }
                    }
                }
            }
        }

        listView_students.setItems(FXCollections.observableList(students_list));
    }

    @FXML
    void btn_refresh(ActionEvent event) {
        updateSubjectList();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateSubjectList();

        listView_students.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showStudent(newValue);
            }
        }); 
    }

    public void updateSubjectList(){
        subjects_list = new ArrayList<Integer>();
        int modelSubject_list_size = model.getSubjectsList().size();

        for (int i = 0; i < modelSubject_list_size; i++) {
            subjects_list.add(model.getSubjectsList().get(i).getCode());
        }

        comboBox_subject.setItems(FXCollections.observableArrayList(subjects_list));
        
    }

    private void showStudent(Student student) {
        lbl_dni.setText(student.getDni());
        lbl_name.setText(student.getName());
        lbl_address.setText(student.getAddress());
        lbl_phone.setText(student.getPhone());

        int ordinary_note = 0;
        int extra_note = 0;
        boolean test = true;
        for (int i = 0; i < model.getRecordsList().size() && test; i++) {
            String dni = model.getRecordsList().get(i).getDni();

            if (dni.equals(student.getDni())) {
                ordinary_note = model.getRecordsList().get(i).getOrdinary_note();
                try {
                    extra_note = model.getRecordsList().get(i).getExtraordinary_note();
                } catch (Exception e) {
                    System.out.println(e);
                }
                test = false;
            }
        }
        
        lbl_ordinary_note.setText(Integer.toString(ordinary_note));

        if (ordinary_note >=5) {
            lbl_extraordinary_note.setText("-");
        } else {
            lbl_extraordinary_note.setText(Integer.toString(extra_note));
        }
    }

    public void injecta(Init_windows init_windows, GestioLlistes model){
        this.init_windows = init_windows;
        this.model = model;
    }
}
