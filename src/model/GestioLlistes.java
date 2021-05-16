package model;

import java.sql.*;
import java.util.ArrayList;

public class GestioLlistes {
    private boolean restart_all = false;
    private boolean tableStudents = false, tableRecords = false, tableSubjects = false;
    private ArrayList<Record> RecordsList;
    private ArrayList<Student> StudentsList;
    private ArrayList<Subject> SubjectsList;
    private Connection con;

    public GestioLlistes(boolean restart_all) {
        this.restart_all = restart_all;
        this.RecordsList = new ArrayList<>();
        this.StudentsList = new ArrayList<>();
        this.SubjectsList = new ArrayList<>();
        
        try {
            Class.forName("com.mysql.jdbc.Driver"); 
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/DB?characterEncoding=utf8", "root", "root");
            Statement stmt = con.createStatement();     

            tableStudents = checktableStudents();
            tableRecords = checktableRecords();
            tableSubjects = checktableSubjects();


            if (restart_all) {
                if (tableRecords){
                    stmt.execute("DROP TABLE Records;");
                    tableRecords = false;
                }
                if (tableStudents) {
                    stmt.execute("DROP TABLE Students;");
                    tableStudents = false;
                }
                if (tableSubjects) {
                    stmt.execute("DROP TABLE Subjects;");
                    tableSubjects = false;
                }
            }
            

            if (tableStudents && tableRecords && tableSubjects){
                getData();

            } else {
                if(!tableStudents){
                    String sql = "CREATE TABLE Students (DNI VARCHAR(10) PRIMARY KEY, name VARCHAR(30), address VARCHAR(50), phone VARCHAR(30));";
                    stmt.executeUpdate(sql);
                }

                if (!tableSubjects) {
                    String sql3 = "CREATE TABLE Subjects (code INT PRIMARY KEY, name VARCHAR(255));";
                    stmt.executeUpdate(sql3);
                }

                if (!tableRecords) {
                    String sql2 = "CREATE TABLE Records (DNI VARCHAR(10), subject_code INT, ordinary_note INT, extraordinary_note INT, FOREIGN KEY (DNI) REFERENCES Students(DNI) ON DELETE CASCADE, FOREIGN KEY (subject_code) REFERENCES Subjects(code) ON DELETE CASCADE);";
                    stmt.executeUpdate(sql2);
                }

            }

            
        } catch (Exception e) {
            System.out.println(e);
        }
         
    }

    private void getData() throws SQLException{
        Statement searchdb = con.createStatement();
        ResultSet dataStudents =  searchdb.executeQuery("SELECT * FROM Students");
        this.RecordsList = new ArrayList<>();
        this.StudentsList = new ArrayList<>();
        this.SubjectsList = new ArrayList<>();
        
        while (dataStudents.next()){
            StudentsList.add(
                new Student(
                    dataStudents.getString(1),
                    dataStudents.getString(2),
                    dataStudents.getString(3),
                    dataStudents.getString(4)
                )
            );
        }

        ResultSet dataRecords =  searchdb.executeQuery("SELECT * FROM Records");

        while (dataRecords.next()) {
            RecordsList.add(
                new Record(
                    dataRecords.getString(1), 
                    dataRecords.getInt(2),
                    dataRecords.getInt(3),
                    dataRecords.getInt(4)
                )
            ); 
        }

        ResultSet dataSubjects =  searchdb.executeQuery("SELECT * FROM Subjects");

        while (dataSubjects.next()) {
            SubjectsList.add(
                new Subject(
                    dataSubjects.getInt(1),
                    dataSubjects.getString(2)
                )
            ); 
        }
    }

    public void createRecord(String dni, int subject_code, int ordinary_convocation_note, int extraordinary_convocation_note) throws SQLException{
        String addRecord = "INSERT INTO Records VALUES (?,?,?,?);";

        PreparedStatement createRecord1 = con.prepareStatement(addRecord);
        createRecord1.setString(1, dni);
        createRecord1.setInt(2, subject_code);
        createRecord1.setInt(3, ordinary_convocation_note);
        createRecord1.setInt(4, extraordinary_convocation_note);
        createRecord1.executeUpdate();
        getData();
    }

    public void createStudent(String dni, String name, String address, String phone) throws SQLException{
        String addRegion = "INSERT INTO Students VALUES (?,?,?,?);";
        
        PreparedStatement createStudent1 = con.prepareStatement(addRegion);
        createStudent1.setString(1, dni);
        createStudent1.setString(2, name);
        createStudent1.setString(3, address);
        createStudent1.setString(4, phone);
        createStudent1.executeUpdate();
        getData();
    }

    public void removeStudent(String dni) throws SQLException{
        String removeStudent = "DELETE FROM Students WHERE (dni = ?);";

        PreparedStatement removeStudent1 = con.prepareStatement(removeStudent);
        removeStudent1.setString(1, dni);
        removeStudent1.executeUpdate();
        getData();
    }

    public void addSubject(int code, String name) throws SQLException{
        String addSubject = "INSERT INTO Subjects VALUES (?,?)";

        PreparedStatement addSubject1 = con.prepareStatement(addSubject);
        addSubject1.setInt(1, code);
        addSubject1.setString(2, name);
        addSubject1.executeUpdate();
        getData();
    }

    public void removeSubject(int code) throws SQLException{
        String removeSubject = "DELETE FROM Subjects WHERE (code = ?)";

        PreparedStatement removeSubject1 = con.prepareStatement(removeSubject);
        removeSubject1.setInt(1, code);
        removeSubject1.executeUpdate();
        getData();
    }

    public boolean checktableStudents(){
        try {
            DatabaseMetaData dbm = con.getMetaData();
            ResultSet checkStudents = dbm.getTables(null, null, "Students", null);
            if (checkStudents.next()) {
                tableStudents = true;
            }
            checkStudents.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return tableStudents;
    }

    public boolean checktableRecords(){
        try {
            DatabaseMetaData dbm = con.getMetaData();
            ResultSet checkRecords = dbm.getTables(null, null, "Records", null);
            if (checkRecords.next()) {
                tableRecords = true;
            }
            checkRecords.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return tableRecords;
    }

    public boolean checktableSubjects(){
        try {
            DatabaseMetaData dbm = con.getMetaData();
            ResultSet checkSubjects = dbm.getTables(null, null, "Subjects", null);
            if (checkSubjects.next()) {
                tableSubjects = true;
            }
            checkSubjects.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return tableSubjects;
    }

    public ArrayList<Subject> getSubjectsList() {
        return SubjectsList;
    }

    public ArrayList<Student> getStudentsList() {
        return StudentsList;
    }

    public ArrayList<Record> getRecordsList() {
        return RecordsList;
    }
}
