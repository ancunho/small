package com.small.vo;

import java.io.Serializable;
import java.util.Objects;

public class CATEGORY implements Serializable {


    private int ID;
    private int PARENT_ID;
    private String NAME;
    private int STATUS;
    private int SORT_ORDER;
    private String CREATE_TIME;
    private String UPDATE_TIME;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getPARENT_ID() {
        return PARENT_ID;
    }

    public void setPARENT_ID(int PARENT_ID) {
        this.PARENT_ID = PARENT_ID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public int getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(int STATUS) {
        this.STATUS = STATUS;
    }

    public int getSORT_ORDER() {
        return SORT_ORDER;
    }

    public void setSORT_ORDER(int SORT_ORDER) {
        this.SORT_ORDER = SORT_ORDER;
    }

    public String getCREATE_TIME() {
        return CREATE_TIME;
    }

    public void setCREATE_TIME(String CREATE_TIME) {
        this.CREATE_TIME = CREATE_TIME;
    }

    public String getUPDATE_TIME() {
        return UPDATE_TIME;
    }

    public void setUPDATE_TIME(String UPDATE_TIME) {
        this.UPDATE_TIME = UPDATE_TIME;
    }

    @Override
    public String toString() {
        return "CATEGORY{" +
                "ID=" + ID +
                ", PARENT_ID=" + PARENT_ID +
                ", NAME='" + NAME + '\'' +
                ", STATUS=" + STATUS +
                ", SORT_ORDER=" + SORT_ORDER +
                ", CREATE_TIME='" + CREATE_TIME + '\'' +
                ", UPDATE_TIME='" + UPDATE_TIME + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CATEGORY category = (CATEGORY) o;
        return ID == category.ID;
    }

    @Override
    public int hashCode() {

        return Objects.hash(ID);
    }
}
