package com.small.vo;

import java.io.Serializable;

public class USER implements Serializable {



    private int ID;
    private String WEIXIN_OPEN_ID;
    private String USERNAME;
    private String PASSWORD;
    private String EMAIL;
    private String PHONE;
    private String QUESTION;
    private String ANSWER;
    private int ROLE;
    private String CREATE_TIME;
    private String UPDATE_TIME;


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getWEIXIN_OPEN_ID() {
        return WEIXIN_OPEN_ID;
    }

    public void setWEIXIN_OPEN_ID(String WEIXIN_OPEN_ID) {
        this.WEIXIN_OPEN_ID = WEIXIN_OPEN_ID;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getPHONE() {
        return PHONE;
    }

    public void setPHONE(String PHONE) {
        this.PHONE = PHONE;
    }

    public String getQUESTION() {
        return QUESTION;
    }

    public void setQUESTION(String QUESTION) {
        this.QUESTION = QUESTION;
    }

    public String getANSWER() {
        return ANSWER;
    }

    public void setANSWER(String ANSWER) {
        this.ANSWER = ANSWER;
    }

    public int getROLE() {
        return ROLE;
    }

    public void setROLE(int ROLE) {
        this.ROLE = ROLE;
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
        return "USER{" +
                "ID=" + ID +
                ", WEIXIN_OPEN_ID='" + WEIXIN_OPEN_ID + '\'' +
                ", USERNAME='" + USERNAME + '\'' +
                ", PASSWORD='" + PASSWORD + '\'' +
                ", EMAIL='" + EMAIL + '\'' +
                ", PHONE='" + PHONE + '\'' +
                ", QUESTION='" + QUESTION + '\'' +
                ", ANSWER='" + ANSWER + '\'' +
                ", ROLE=" + ROLE +
                ", CREATE_TIME='" + CREATE_TIME + '\'' +
                ", UPDATE_TIME='" + UPDATE_TIME + '\'' +
                '}';
    }
}
