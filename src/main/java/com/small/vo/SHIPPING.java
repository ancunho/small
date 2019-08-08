package com.small.vo;

import java.io.Serializable;

public class SHIPPING implements Serializable {

    private int ID;
    private int USER_ID;
    private String RECEIVER_NAME;
    private String RECEIVER_PHONE;
    private String RECEIVER_MOBILE;
    private String RECEIVER_PROVICE;
    private String RECEIVER_CITY;
    private String RECEIVER_DISTRICT;
    private String RECEIVER_ADDRESS;
    private String RECEIVER_ZIP;
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

    public String getRECEIVER_NAME() {
        return RECEIVER_NAME;
    }

    public void setRECEIVER_NAME(String RECEIVER_NAME) {
        this.RECEIVER_NAME = RECEIVER_NAME;
    }

    public String getRECEIVER_PHONE() {
        return RECEIVER_PHONE;
    }

    public void setRECEIVER_PHONE(String RECEIVER_PHONE) {
        this.RECEIVER_PHONE = RECEIVER_PHONE;
    }

    public String getRECEIVER_MOBILE() {
        return RECEIVER_MOBILE;
    }

    public void setRECEIVER_MOBILE(String RECEIVER_MOBILE) {
        this.RECEIVER_MOBILE = RECEIVER_MOBILE;
    }

    public String getRECEIVER_PROVICE() {
        return RECEIVER_PROVICE;
    }

    public void setRECEIVER_PROVICE(String RECEIVER_PROVICE) {
        this.RECEIVER_PROVICE = RECEIVER_PROVICE;
    }

    public String getRECEIVER_CITY() {
        return RECEIVER_CITY;
    }

    public void setRECEIVER_CITY(String RECEIVER_CITY) {
        this.RECEIVER_CITY = RECEIVER_CITY;
    }

    public String getRECEIVER_DISTRICT() {
        return RECEIVER_DISTRICT;
    }

    public void setRECEIVER_DISTRICT(String RECEIVER_DISTRICT) {
        this.RECEIVER_DISTRICT = RECEIVER_DISTRICT;
    }

    public String getRECEIVER_ADDRESS() {
        return RECEIVER_ADDRESS;
    }

    public void setRECEIVER_ADDRESS(String RECEIVER_ADDRESS) {
        this.RECEIVER_ADDRESS = RECEIVER_ADDRESS;
    }

    public String getRECEIVER_ZIP() {
        return RECEIVER_ZIP;
    }

    public void setRECEIVER_ZIP(String RECEIVER_ZIP) {
        this.RECEIVER_ZIP = RECEIVER_ZIP;
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
        return "SHIPPING{" +
                "ID=" + ID +
                ", USER_ID=" + USER_ID +
                ", RECEIVER_NAME='" + RECEIVER_NAME + '\'' +
                ", RECEIVER_PHONE='" + RECEIVER_PHONE + '\'' +
                ", RECEIVER_MOBILE='" + RECEIVER_MOBILE + '\'' +
                ", RECEIVER_PROVICE='" + RECEIVER_PROVICE + '\'' +
                ", RECEIVER_CITY='" + RECEIVER_CITY + '\'' +
                ", RECEIVER_DISTRICT='" + RECEIVER_DISTRICT + '\'' +
                ", RECEIVER_ADDRESS='" + RECEIVER_ADDRESS + '\'' +
                ", RECEIVER_ZIP='" + RECEIVER_ZIP + '\'' +
                ", CREATE_TIME='" + CREATE_TIME + '\'' +
                ", UPDATE_TIME='" + UPDATE_TIME + '\'' +
                '}';
    }
}
