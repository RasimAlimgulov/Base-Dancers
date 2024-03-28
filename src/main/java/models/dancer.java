package models;


import javafx.beans.property.*;

public class dancer {
    private String nameF;
    private String secondname;
    private String thirdname;
    private int id;

    public dancer(int id, String name, String secondname, String thirdname) {
        this.id = id;
        this.nameF = name;
        this.secondname = secondname;
        this.thirdname = thirdname;
    }

    public String getName() {
        return nameF;
    }

    public void setName(String name) {
        this.nameF = name;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public String getThirdname() {
        return thirdname;
    }

    public void setThirdname(String thirthname) {
        this.thirdname = thirthname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}