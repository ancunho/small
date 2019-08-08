package com.small.vo;

import java.io.Serializable;

public class PRODUCT implements Serializable {

    private int ID;
    private int CATEGORY_ID;
    private String NAME;
    private String SUBTITLE;
    private String MAIN_IMAGE;
    private String SUB_IMAGES;
    private String DETAIL;
    private int PRICE;
    private int STOCK;
    private int STATUS;
    private String CREATE_TIME;
    private String UPDATE_TIME;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getCATEGORY_ID() {
        return CATEGORY_ID;
    }

    public void setCATEGORY_ID(int CATEGORY_ID) {
        this.CATEGORY_ID = CATEGORY_ID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getSUBTITLE() {
        return SUBTITLE;
    }

    public void setSUBTITLE(String SUBTITLE) {
        this.SUBTITLE = SUBTITLE;
    }

    public String getMAIN_IMAGE() {
        return MAIN_IMAGE;
    }

    public void setMAIN_IMAGE(String MAIN_IMAGE) {
        this.MAIN_IMAGE = MAIN_IMAGE;
    }

    public String getSUB_IMAGES() {
        return SUB_IMAGES;
    }

    public void setSUB_IMAGES(String SUB_IMAGES) {
        this.SUB_IMAGES = SUB_IMAGES;
    }

    public String getDETAIL() {
        return DETAIL;
    }

    public void setDETAIL(String DETAIL) {
        this.DETAIL = DETAIL;
    }

    public int getPRICE() {
        return PRICE;
    }

    public void setPRICE(int PRICE) {
        this.PRICE = PRICE;
    }

    public int getSTOCK() {
        return STOCK;
    }

    public void setSTOCK(int STOCK) {
        this.STOCK = STOCK;
    }

    public int getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(int STATUS) {
        this.STATUS = STATUS;
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
        return "PRODUCT{" +
                "ID=" + ID +
                ", CATEGORY_ID=" + CATEGORY_ID +
                ", NAME='" + NAME + '\'' +
                ", SUBTITLE='" + SUBTITLE + '\'' +
                ", MAIN_IMAGE='" + MAIN_IMAGE + '\'' +
                ", SUB_IMAGES='" + SUB_IMAGES + '\'' +
                ", DETAIL='" + DETAIL + '\'' +
                ", PRICE=" + PRICE +
                ", STOCK=" + STOCK +
                ", STATUS=" + STATUS +
                ", CREATE_TIME='" + CREATE_TIME + '\'' +
                ", UPDATE_TIME='" + UPDATE_TIME + '\'' +
                '}';
    }
}
