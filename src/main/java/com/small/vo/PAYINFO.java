package com.small.vo;

import java.io.Serializable;

public class PAYINFO implements Serializable {

    private int ID;
    private int USER_ID;
    private int ORDER_NO;
    private int PAY_PLATFORM;
    private String PLATFORM_NUMBER;
    private String PLATFORM_STATUS;
    private String CREATE_TIME;
    private String UPDATE_TIME;

    public PAYINFO(int ID, int USER_ID, int ORDER_NO, int PAY_PLATFORM, String PLATFORM_NUMBER, String PLATFORM_STATUS, String CREATE_TIME, String UPDATE_TIME) {
        this.ID = ID;
        this.USER_ID = USER_ID;
        this.ORDER_NO = ORDER_NO;
        this.PAY_PLATFORM = PAY_PLATFORM;
        this.PLATFORM_NUMBER = PLATFORM_NUMBER;
        this.PLATFORM_STATUS = PLATFORM_STATUS;
        this.CREATE_TIME = CREATE_TIME;
        this.UPDATE_TIME = UPDATE_TIME;
    }

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

    public int getPAY_PLATFORM() {
        return PAY_PLATFORM;
    }

    public void setPAY_PLATFORM(int PAY_PLATFORM) {
        this.PAY_PLATFORM = PAY_PLATFORM;
    }

    public String getPLATFORM_NUMBER() {
        return PLATFORM_NUMBER;
    }

    public void setPLATFORM_NUMBER(String PLATFORM_NUMBER) {
        this.PLATFORM_NUMBER = PLATFORM_NUMBER;
    }

    public String getPLATFORM_STATUS() {
        return PLATFORM_STATUS;
    }

    public void setPLATFORM_STATUS(String PLATFORM_STATUS) {
        this.PLATFORM_STATUS = PLATFORM_STATUS;
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
        return "PAYINFO{" +
                "ID=" + ID +
                ", USER_ID=" + USER_ID +
                ", ORDER_NO=" + ORDER_NO +
                ", PAY_PLATFORM=" + PAY_PLATFORM +
                ", PLATFORM_NUMBER='" + PLATFORM_NUMBER + '\'' +
                ", PLATFORM_STATUS='" + PLATFORM_STATUS + '\'' +
                ", CREATE_TIME='" + CREATE_TIME + '\'' +
                ", UPDATE_TIME='" + UPDATE_TIME + '\'' +
                '}';
    }
}
