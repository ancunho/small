package com.small.common;

public class Const {

    public static final String CURRENT_USER = "currentUser";

    public static final String EMAIL = "email";
    public static final String USERNAME = "username";

    public static final String RedirectLoginPage = "login";

    public interface Role{
        String ROLE_CUSTOMER = "0"; //普通用户
        String ROLE_ADMIN = "1"; //管理员
    }

}
