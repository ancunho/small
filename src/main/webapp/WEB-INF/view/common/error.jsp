<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>404 错误</title>

    <link href="${ctx}/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctx}/static/font-awesome/css/font-awesome.css" rel="stylesheet">

    <link href="${ctx}/static/css/animate.css" rel="stylesheet">
    <link href="${ctx}/static/css/style.css" rel="stylesheet">

</head>

<body class="gray-bg">


<div class="middle-box text-center animated fadeInDown">
    <h1>404</h1>
    <h3 class="font-bold">找不到网页</h3>

    <div class="error-desc">
        对不起，您正在寻找的页面已经找到。尝试检查URL的错误，然后按浏览器上的刷新按钮或尝试在我们的应用程序中找到其他内容。
        <form class="form-inline m-t" role="form">
            <div class="form-group">
                <input type="text" class="form-control" placeholder="请搜索你的内容">
            </div>
            <button type="submit" class="btn btn-primary">搜索</button>
        </form>
    </div>
</div>

<!-- Mainly scripts -->
<script src="${ctx}/static/js/jquery-3.1.1.min.js"></script>
<script src="${ctx}/static/js/popper.min.js"></script>
<script src="${ctx}/static/js/bootstrap.js"></script>

</body>

</html>
