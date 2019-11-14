package com.small.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class CartProductVO implements Serializable {

    //结合了产品和购物车的一个抽象对象

    private Integer ID;
    private Integer USER_ID;
    private Integer PRODUCT_ID;
    private Integer QUANTITY;//购物车中此商品的数量
    private String PRODUCT_NAME;
    private String PRODUCT_SUB_TITLE;
    private String PRODUCT_MAIN_IMAGE;
    private BigDecimal PRODUCT_PRICE;
    private Integer PRODUCT_STATUS;
    private BigDecimal PRODUCT_TOTAL_PRICE;
    private Integer PRODUCT_STOCK;
    private Integer PRODUCT_CHECKED;//此商品是否勾选

    private String LIMIT_QUANTITY;//限制数量的一个返回结果


    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(Integer USER_ID) {
        this.USER_ID = USER_ID;
    }

    public Integer getPRODUCT_ID() {
        return PRODUCT_ID;
    }

    public void setPRODUCT_ID(Integer PRODUCT_ID) {
        this.PRODUCT_ID = PRODUCT_ID;
    }

    public Integer getQUANTITY() {
        return QUANTITY;
    }

    public void setQUANTITY(Integer QUANTITY) {
        this.QUANTITY = QUANTITY;
    }

    public String getPRODUCT_NAME() {
        return PRODUCT_NAME;
    }

    public void setPRODUCT_NAME(String PRODUCT_NAME) {
        this.PRODUCT_NAME = PRODUCT_NAME;
    }

    public String getPRODUCT_SUB_TITLE() {
        return PRODUCT_SUB_TITLE;
    }

    public void setPRODUCT_SUB_TITLE(String PRODUCT_SUB_TITLE) {
        this.PRODUCT_SUB_TITLE = PRODUCT_SUB_TITLE;
    }

    public String getPRODUCT_MAIN_IMAGE() {
        return PRODUCT_MAIN_IMAGE;
    }

    public void setPRODUCT_MAIN_IMAGE(String PRODUCT_MAIN_IMAGE) {
        this.PRODUCT_MAIN_IMAGE = PRODUCT_MAIN_IMAGE;
    }

    public BigDecimal getPRODUCT_PRICE() {
        return PRODUCT_PRICE;
    }

    public void setPRODUCT_PRICE(BigDecimal PRODUCT_PRICE) {
        this.PRODUCT_PRICE = PRODUCT_PRICE;
    }

    public Integer getPRODUCT_STATUS() {
        return PRODUCT_STATUS;
    }

    public void setPRODUCT_STATUS(Integer PRODUCT_STATUS) {
        this.PRODUCT_STATUS = PRODUCT_STATUS;
    }

    public BigDecimal getPRODUCT_TOTAL_PRICE() {
        return PRODUCT_TOTAL_PRICE;
    }

    public void setPRODUCT_TOTAL_PRICE(BigDecimal PRODUCT_TOTAL_PRICE) {
        this.PRODUCT_TOTAL_PRICE = PRODUCT_TOTAL_PRICE;
    }

    public Integer getPRODUCT_STOCK() {
        return PRODUCT_STOCK;
    }

    public void setPRODUCT_STOCK(Integer PRODUCT_STOCK) {
        this.PRODUCT_STOCK = PRODUCT_STOCK;
    }

    public Integer getPRODUCT_CHECKED() {
        return PRODUCT_CHECKED;
    }

    public void setPRODUCT_CHECKED(Integer PRODUCT_CHECKED) {
        this.PRODUCT_CHECKED = PRODUCT_CHECKED;
    }

    public String getLIMIT_QUANTITY() {
        return LIMIT_QUANTITY;
    }

    public void setLIMIT_QUANTITY(String LIMIT_QUANTITY) {
        this.LIMIT_QUANTITY = LIMIT_QUANTITY;
    }
}
