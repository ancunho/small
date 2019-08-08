package com.small.vo;

import java.io.Serializable;

public class CART implements Serializable {

    private int ID;
    private int USER_ID;
    private int PRODUCT_ID;
    private int QUANTITY;
    private int CHECKED;
    private String CREATE_TIME;
    private String UPDATE_TIME;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(int USER_ID) {
        this.USER_ID = USER_ID;
    }

    public int getPRODUCT_ID() {
        return PRODUCT_ID;
    }

    public void setPRODUCT_ID(int PRODUCT_ID) {
        this.PRODUCT_ID = PRODUCT_ID;
    }

    public int getQUANTITY() {
        return QUANTITY;
    }

    public void setQUANTITY(int QUANTITY) {
        this.QUANTITY = QUANTITY;
    }

    public int getCHECKED() {
        return CHECKED;
    }

    public void setCHECKED(int CHECKED) {
        this.CHECKED = CHECKED;
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
        return "CART{" +
                "ID=" + ID +
                ", USER_ID=" + USER_ID +
                ", PRODUCT_ID=" + PRODUCT_ID +
                ", QUANTITY=" + QUANTITY +
                ", CHECKED=" + CHECKED +
                ", CREATE_TIME='" + CREATE_TIME + '\'' +
                ", UPDATE_TIME='" + UPDATE_TIME + '\'' +
                '}';
    }
}
