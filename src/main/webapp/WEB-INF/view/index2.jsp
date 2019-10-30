
<%-- Guest Index Page --%>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>系统</title>
    <%@include file="common/portal_meta.jsp"%>
</head>

<body class="">

<div id="wrapper">
    <!-- Left Menu Start -->
    <%@include file="common/portal_leftmenu.jsp"%>
    <!-- // Left Menu End -->

    <div id="page-wrapper" class="gray-bg">
        <div class="row border-bottom">
            <!-- Top Start -->
            <%@include file="common/portal_top.jsp"%>
            <!-- // Top End -->
        </div>
        <div class="row wrapper border-bottom white-bg page-heading">
            <div class="col-sm-4">
                <h2>空白页面</h2>
                <ol class="breadcrumb">
                    <li class="breadcrumb-item">
                        <a href="index.html">主页</a>
                    </li>
                    <li class="breadcrumb-item active">
                        <strong>面包屑</strong>
                    </li>
                </ol>
            </div>
            <div class="col-sm-8">
                <div class="title-action">
                    <a href="" class="btn btn-primary">这是行动区</a>
                </div>
            </div>
        </div>

        <div class="wrapper wrapper-content white-bg">
            <div class="middle-box text-center animated fadeInRightBig">
                <h3 class="font-bold">这是页面内容</h3>
                <div class="error-desc">
                    您可以在这里创建所需的任何网格布局。和任何你想象的变化布局:)<br>
                    看看主页等网站。它使用许多不同的布局。
                    <br/><a href="index.html" class="btn btn-primary m-t">返回主页</a>
                </div>
            </div>
        </div>
        <div class="footer">
            <div class="float-right">
                <strong>2.9.2 inspinia</strong>
            </div>
            <div>
                <strong>Copyright</strong> inspinia 2.9.2 &copy; 2014-2018
            </div>
        </div>

    </div>
</div>

<!-- Top Start -->
<%@include file="common/portal_footer.jsp"%>
<!-- // Top End -->


</body>

</html>
