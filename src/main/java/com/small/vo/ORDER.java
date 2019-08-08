package com.small.vo;

import java.io.Serializable;

public class ORDER implements Serializable {



    private int ID;
    private int ORDER_NO;
    private int USER_ID;
    private int SHIPPING_ID;
    private int PAYMENT;
    private int PAYMENT_TYPE;
    private int POSTAGE;
    private int STATUS;
    private String PAYMENT_TIME;
    private String SEND_TIME;
    private String END_TIME;
    private String CLOSE_TIME;
    private String CREATE_TIME;
    private String UPDATE_TIME;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getORDER_NO() {
        return ORDER_NO;
    }

    public void setORDER_NO(int ORDER_NO) {
        this.ORDER_NO = ORDER_NO;
    }

    public int getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(int USER_ID) {
        this.USER_ID = USER_ID;
    }

    public int getSHIPPING_ID() {
        return SHIPPING_ID;
    }

    public void setSHIPPING_ID(int SHIPPING_ID) {
        this.SHIPPING_ID = SHIPPING_ID;
    }

    public int getPAYMENT() {
        return PAYMENT;
    }

    public void setPAYMENT(int PAYMENT) {
        this.PAYMENT = PAYMENT;
    }

    public int getPAYMENT_TYPE() {
        return PAYMENT_TYPE;
    }

    public void setPAYMENT_TYPE(int PAYMENT_TYPE) {
        this.PAYMENT_TYPE = PAYMENT_TYPE;
    }

    public int getPOSTAGE() {
        return POSTAGE;
    }

    public void setPOSTAGE(int POSTAGE) {
        this.POSTAGE = POSTAGE;
    }

    public int getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(int STATUS) {
        this.STATUS = STATUS;
    }

    public String getPAYMENT_TIME() {
        return PAYMENT_TIME;
    }

    public void setPAYMENT_TIME(String PAYMENT_TIME) {
        this.PAYMENT_TIME = PAYMENT_TIME;
    }

    public String getSEND_TIME() {
        return SEND_TIME;
    }

    public void setSEND_TIME(String SEND_TIME) {
        this.SEND_TIME = SEND_TIME;
    }

    public String getEND_TIME() {
        return END_TIME;
    }

    public void setEND_TIME(String END_TIME) {
        this.END_TIME = END_TIME;
    }

    public String getCLOSE_TIME() {
        return CLOSE_TIME;
    }

    public void setCLOSE_TIME(String CLOSE_TIME) {
        this.CLOSE_TIME = CLOSE_TIME;
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
        return "ORDER{" +
                "ID=" + ID +
                ", ORDER_NO=" + ORDER_NO +
                ", USER_ID=" + USER_ID +
                ", SHIPPING_ID=" + SHIPPING_ID +
                ", PAYMENT=" + PAYMENT +
                ", PAYMENT_TYPE=" + PAYMENT_TYPE +
                ", POSTAGE=" + POSTAGE +
                ", STATUS=" + STATUS +
                ", PAYMENT_TIME='" + PAYMENT_TIME + '\'' +
                ", SEND_TIME='" + SEND_TIME + '\'' +
                ", END_TIME='" + END_TIME + '\'' +
                ", CLOSE_TIME='" + CLOSE_TIME + '\'' +
                ", CREATE_TIME='" + CREATE_TIME + '\'' +
                ", UPDATE_TIME='" + UPDATE_TIME + '\'' +
                '}';
    }
}
