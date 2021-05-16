package model;

public class Record {
    private String dni;
    private int subject_code, ordinary_note, extraordinary_note;


    public Record(String dni, int subject_code, int ordinary_note, int extraordinary_note) {
        this.dni = dni;
        this.subject_code = subject_code;
        this.ordinary_note = ordinary_note;
        this.extraordinary_note = extraordinary_note;
    }

    public String getDni() {
        return this.dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getSubject_code() {
        return this.subject_code;
    }

    public void setSubject_code(int subject_code) {
        this.subject_code = subject_code;
    }

    public int getOrdinary_note() {
        return this.ordinary_note;
    }

    public void setOrdinary_note(int ordinary_note) {
        this.ordinary_note = ordinary_note;
    }

    public int getExtraordinary_note() {
        return this.extraordinary_note;
    }

    public void setExtraordinary_note(int extraordinary_note) {
        this.extraordinary_note = extraordinary_note;
    }

    @Override
    public String toString() {
        return getDni();
    }

}
