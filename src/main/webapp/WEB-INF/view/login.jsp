<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="<%=request.getContextPath()%>" />


<!DOCTYPE html>
<html>

<head>
    <script type="text/javascript">
        var contextRootPath = "${ctx}";
    </script>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>登录</title>

    <link href="${ctx}/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctx}/static/font-awesome/css/font-awesome.css" rel="stylesheet">

    <link href="${ctx}/static/css/animate.css" rel="stylesheet">
    <link href="${ctx}/static/css/style.css" rel="stylesheet">

</head>

<body class="gray-bg">

<div class="middle-box text-center loginscreen animated fadeInDown">
    <div>
        <%--<div>--%>

            <%--<h1 class="logo-name">IN+</h1>--%>

        <%--</div>--%>
        <h3>欢迎登录</h3>
        <%--<p>一套高质量的后台管理html模板</p>--%>
        <form class="m-t" role="form" action="index.html">
            <div class="form-group">
                <input type="email" class="form-control" placeholder="用户名" required="">
            </div>
            <div class="form-group">
                <input type="password" class="form-control" placeholder="密码" required="">
            </div>
            <button type="submit" class="btn btn-primary block full-width m-b">登录</button>

            <a href="#"><small>忘记密码?</small></a>
            <p class="text-muted text-center"><small>还没有账号?</small></p>
            <a class="btn btn-sm btn-white btn-block" href="register.html">创建一个帐户</a>
        </form>
        <p class="m-t"> <small>Copyright</strong> </small> </p>
    </div>
</div>

<!-- Mainly scripts -->
<script src="${ctx}/static/js/jquery-3.1.1.min.js"></script>
<script src="${ctx}/static/js/popper.min.js"></script>
<script src="${ctx}/static/js/bootstrap.js"></script>

</body>

</html>
