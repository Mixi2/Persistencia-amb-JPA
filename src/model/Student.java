package model;

public class Student {
    private String dni,name,address,phone;

    public Student(String dni, String name, String address, String phone) {
        this.dni = dni;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }


    public String getDni() {
        return this.dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    @Override
    public String toString() {
        return getName();
    }
}
