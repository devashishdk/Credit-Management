package com.devashish.creditmanagementapp;

public class DataBean {
    int ID;
    String NAME,EMAIL,CREDIT;

    DataBean()
    {

    }
    public DataBean(int ID, String NAME, String EMAIL, String CREDIT) {
        this.ID = ID;
        this.NAME = NAME;
        this.EMAIL = EMAIL;
        this.CREDIT = CREDIT;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getCREDIT() {
        return CREDIT;
    }

    public void setCREDIT(String CREDIT) {
        this.CREDIT = CREDIT;
    }
}
