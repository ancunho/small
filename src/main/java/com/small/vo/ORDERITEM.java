package com.small.vo;

import java.io.Serializable;

public class ORDERITEM implements Serializable {

    private int ID;
    private int USER_ID;
    private int ORDER_NO;
    private int PRODUCT_ID;
    private String PRODUCT_NAME;
    private String PRODUCT_IMAGE;
    private int CURRENT_UNIT_PRICE;
    private int QUANTITY;
    private int TOTAL_PRICE;
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

    public int getORDER_NO() {
        return ORDER_NO;
    }

    public void setORDER_NO(int ORDER_NO) {
        this.ORDER_NO = ORDER_NO;
    }

    public int getPRODUCT_ID() {
        return PRODUCT_ID;
    }

    public void setPRODUCT_ID(int PRODUCT_ID) {
        this.PRODUCT_ID = PRODUCT_ID;
    }

    public String getPRODUCT_NAME() {
        return PRODUCT_NAME;
    }

    public void setPRODUCT_NAME(String PRODUCT_NAME) {
        this.PRODUCT_NAME = PRODUCT_NAME;
    }

    public String getPRODUCT_IMAGE() {
        return PRODUCT_IMAGE;
    }

    public void setPRODUCT_IMAGE(String PRODUCT_IMAGE) {
        this.PRODUCT_IMAGE = PRODUCT_IMAGE;
    }

    public int getCURRENT_UNIT_PRICE() {
        return CURRENT_UNIT_PRICE;
    }

    public void setCURRENT_UNIT_PRICE(int CURRENT_UNIT_PRICE) {
        this.CURRENT_UNIT_PRICE = CURRENT_UNIT_PRICE;
    }

    public int getQUANTITY() {
        return QUANTITY;
    }

    public void setQUANTITY(int QUANTITY) {
        this.QUANTITY = QUANTITY;
    }

    public int getTOTAL_PRICE() {
        return TOTAL_PRICE;
    }

    public void setTOTAL_PRICE(int TOTAL_PRICE) {
        this.TOTAL_PRICE = TOTAL_PRICE;
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
        return "ORDERITEM{" +
                "ID=" + ID +
                ", USER_ID=" + USER_ID +
                ", ORDER_NO=" + ORDER_NO +
                ", PRODUCT_ID=" + PRODUCT_ID +
                ", PRODUCT_NAME='" + PRODUCT_NAME + '\'' +
                ", PRODUCT_IMAGE='" + PRODUCT_IMAGE + '\'' +
                ", CURRENT_UNIT_PRICE=" + CURRENT_UNIT_PRICE +
                ", QUANTITY=" + QUANTITY +
                ", TOTAL_PRICE=" + TOTAL_PRICE +
                ", CREATE_TIME='" + CREATE_TIME + '\'' +
                ", UPDATE_TIME='" + UPDATE_TIME + '\'' +
                '}';
    }
}
