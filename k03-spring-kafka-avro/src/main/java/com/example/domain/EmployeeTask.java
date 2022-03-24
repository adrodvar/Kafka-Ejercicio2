package com.example.domain;

public class EmployeeTask {
    private Long id;
    private String name;
    private String lastname;
    private String rol;
    private CarTask carTask;

    public EmployeeTask() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public CarTask getCar() {
        return carTask;
    }

    public void setCar(CarTask carTask) {
        this.carTask = carTask;
    }
}
