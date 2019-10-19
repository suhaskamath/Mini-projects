package com.example.myapplication;

import android.widget.EditText;

import java.io.Serializable;

public class Company implements Serializable {
    private String companyname;
    private float salary;

    public Company(String description,String companyname, float salary, float tenmarks, float twelevemarks, float bemarks, String job) {
        this.companyname = companyname;
        this.salary = salary;
        this.tenmarks = tenmarks;
        this.twelevemarks = twelevemarks;
        this.bemarks = bemarks;
        this.job = job;
        this.description=description;
    }
    public Company() {
        this.companyname = null;
        this.salary = 0.0f;
        this.tenmarks = 0.0f;
        this.twelevemarks = 0.0f;
        this.bemarks = 0.0f;
        this.job = null;
        this.description=null;
    }
    private float tenmarks;

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public float getTenmarks() {
        return tenmarks;
    }

    public void setTenmarks(float tenmarks) {
        this.tenmarks = tenmarks;
    }

    public float getTwelevemarks() {
        return twelevemarks;
    }

    public void setTwelevemarks(float twelevemarks) {
        this.twelevemarks = twelevemarks;
    }

    public float getBemarks() {
        return bemarks;
    }

    public void setBemarks(float bemarks) {
        this.bemarks = bemarks;
    }

    public String getJob() {
        return job;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setJob(String job) {
        this.job = job;
    }

    private float twelevemarks;
    private float bemarks;
    private String job;
    private String description;
}
