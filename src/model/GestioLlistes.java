package model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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

    private void getData() throws SQLException {
        Statement searchdb = this.con.createStatement();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        this.RecordsList = new ArrayList();
        this.StudentsList = new ArrayList();
        this.SubjectsList = new ArrayList();
        ResultSet dataStudents = searchdb.executeQuery("SELECT DNI FROM Students");

        while(dataStudents.next()) {
            Student student = (Student)em.find(Student.class, dataStudents.getString(1));
            this.StudentsList.add(student);
        }

        ResultSet dataRecords = searchdb.executeQuery("SELECT DNI FROM Records");

        while(dataRecords.next()) {
            Record record = (Record)em.find(Record.class, dataRecords.getString(1));
            this.RecordsList.add(record);
        }

        ResultSet dataSubjects = searchdb.executeQuery("SELECT code FROM Subjects");

        while(dataSubjects.next()) {
            Subject subject = (Subject)em.find(Subject.class, dataSubjects.getInt(1));
            this.SubjectsList.add(subject);
        }

        em.close();
        emf.close();
    }

    public void createRecord(String dni, int subject_code, int ordinary_convocation_note, int extraordinary_convocation_note) throws SQLException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Record record = new Record(dni, subject_code, ordinary_convocation_note, extraordinary_convocation_note);
        em.persist(record);
        em.getTransaction().commit();
        em.close();
        emf.close();
        this.getData();
    }

    public void createStudent(String dni, String name, String address, String phone) throws SQLException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        Student student = new Student(dni, name, address, phone);
        em.persist(student);
        em.getTransaction().commit();
        em.close();
        emf.close();
        this.getData();
    }

    public void removeStudent(String dni) throws SQLException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        Student student = (Student)em.find(Student.class, dni);
        em.getTransaction().begin();
        em.remove(student);
        em.getTransaction().commit();
        em.close();
        emf.close();
        this.getData();
    }

    public void addSubject(int code, String name) throws SQLException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        Subject subject = new Subject(code, name);
        em.getTransaction().begin();
        em.persist(subject);
        em.getTransaction().commit();
        em.close();
        emf.close();
        this.getData();
    }

    public void removeSubject(int code) throws SQLException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        Subject subject = (Subject)em.find(Subject.class, code);
        em.getTransaction().begin();
        em.remove(subject);
        em.getTransaction().commit();
        em.close();
        emf.close();
        this.getData();
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
