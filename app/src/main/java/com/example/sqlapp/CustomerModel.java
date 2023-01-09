package com.example.sqlapp;

public class CustomerModel {
    private int id;
    private String name;
    private int age;
    private boolean isActive;
    public CustomerModel(int _id, String _name, int _age, boolean _isActive){
        this.id=_id;
        this.name=_name;
        this.age=_age;
        this.isActive=_isActive;
    }
    public CustomerModel(){

    }

    @Override
    public String toString() {
        return "CustomerModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", isActive=" + isActive +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int _id) {
        this.id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        this.name = _name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int _age) {
        this.age = _age;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean _active) {
        isActive = _active;
    }

}
