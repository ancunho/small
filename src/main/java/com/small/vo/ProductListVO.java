package com.small.vo;

import java.io.Serializable;

public class ProductListVO implements Serializable {

    private Integer ID;
    private Integer CATEGORY_ID;
    private String NAME;
    private String SUBTITLE;
    private String MAIN_IMAGE;
    private Integer PRICE;
    private Integer STATUS;
    private String imageHost;

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

    public Integer getPRICE() {
        return PRICE;
    }

    public void setPRICE(Integer PRICE) {
        this.PRICE = PRICE;
    }

    public Integer getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(Integer STATUS) {
        this.STATUS = STATUS;
    }

    public String getImageHost() {
        return imageHost;
    }

    public void setImageHost(String imageHost) {
        this.imageHost = imageHost;
    }

    @Override
    public String toString() {
        return "ProductListVO{" +
                "ID=" + ID +
                ", CATEGORY_ID=" + CATEGORY_ID +
                ", NAME='" + NAME + '\'' +
                ", SUBTITLE='" + SUBTITLE + '\'' +
                ", MAIN_IMAGE='" + MAIN_IMAGE + '\'' +
                ", PRICE=" + PRICE +
                ", STATUS=" + STATUS +
                ", imageHost='" + imageHost + '\'' +
                '}';
    }
}
