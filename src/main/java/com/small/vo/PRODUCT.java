package com.small.vo;

import java.io.Serializable;

public class PRODUCT implements Serializable {

    private Integer ID;
    private Integer CATEGORY_ID;
    private String NAME;
    private String SUBTITLE;
    private String MAIN_IMAGE;
    private String SUB_IMAGES;
    private String DETAIL;
    private Integer PRICE;
    private Integer STOCK;
    private Integer STATUS;
    private String CREATE_TIME;
    private String UPDATE_TIME;

    public PRODUCT(Integer ID, Integer CATEGORY_ID, String NAME, String SUBTITLE, String MAIN_IMAGE, String SUB_IMAGES, String DETAIL, Integer PRICE, Integer STOCK, Integer STATUS, String CREATE_TIME, String UPDATE_TIME) {
        this.ID = ID;
        this.CATEGORY_ID = CATEGORY_ID;
        this.NAME = NAME;
        this.SUBTITLE = SUBTITLE;
        this.MAIN_IMAGE = MAIN_IMAGE;
        this.SUB_IMAGES = SUB_IMAGES;
        this.DETAIL = DETAIL;
        this.PRICE = PRICE;
        this.STOCK = STOCK;
        this.STATUS = STATUS;
        this.CREATE_TIME = CREATE_TIME;
        this.UPDATE_TIME = UPDATE_TIME;
    }

    public PRODUCT() {
        super();
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getCATEGORY_ID() {
        return CATEGORY_ID;
    }

    public void setCATEGORY_ID(Integer CATEGORY_ID) {
        this.CATEGORY_ID = CATEGORY_ID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME == null ? null : NAME.trim();
    }

    public String getSUBTITLE() {
        return SUBTITLE;
    }

    public void setSUBTITLE(String SUBTITLE) {
        this.SUBTITLE = SUBTITLE == null ? null : SUBTITLE.trim();
    }

    public String getMAIN_IMAGE() {
        return MAIN_IMAGE;
    }

    public void setMAIN_IMAGE(String MAIN_IMAGE) {
        this.MAIN_IMAGE = MAIN_IMAGE == null ? null : MAIN_IMAGE.trim();
    }

    public String getSUB_IMAGES() {
        return SUB_IMAGES;
    }

    public void setSUB_IMAGES(String SUB_IMAGES) {
        this.SUB_IMAGES = SUB_IMAGES == null ? null : SUB_IMAGES.trim();
    }

    public String getDETAIL() {
        return DETAIL;
    }

    public void setDETAIL(String DETAIL) {
        this.DETAIL = DETAIL == null ? null : DETAIL.trim();
    }

    public Integer getPRICE() {
        return PRICE;
    }

    public void setPRICE(Integer PRICE) {
        this.PRICE = PRICE;
    }

    public Integer getSTOCK() {
        return STOCK;
    }

    public void setSTOCK(Integer STOCK) {
        this.STOCK = STOCK;
    }

    public Integer getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(Integer STATUS) {
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
