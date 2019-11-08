<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="<%=request.getContextPath()%>" />

<!DOCTYPE html>
<html>
<head>
    <title>登录</title>
    <%@include file="common/metahtml.jsp"%>

    <!-- Ladda style -->
    <link href="${ctx}/static/css/plugins/ladda/ladda-themeless.min.css" rel="stylesheet">

    <!-- Sweet Alert -->
    <link href="${ctx}/static/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">

</head>

<body class="gray-bg">

<div class="middle-box text-center loginscreen animated fadeInDown">
    <div>
        <%--<div>--%>

            <%--<h1 class="logo-name">IN+</h1>--%>

        <%--</div>--%>
        <h3>欢迎登录</h3>
        <%--<p>一套高质量的后台管理html模板</p>--%>
        <form class="m-t" role="form">
            <div class="form-group">
                <input type="email" class="form-control" id="USERNAME" placeholder="用户名" required="">
            </div>
            <div class="form-group">
                <input type="password" class="form-control" id="PASSWORD" placeholder="密码" required="">
            </div>
            <button type="submit" class="btn btn-primary block full-width m-b" data-style="slide-up" id="btnLogin">登录</button>

            <a href="${ctx}/page/user/forget_password.do"><small>忘记密码?</small></a>
            <%--<p class="text-muted text-center"><small>还没有账号?</small></p>--%>
            <%--<a class="btn btn-sm btn-white btn-block" href="#this">创建一个帐户</a>--%>
        </form>
        <p class="m-t"> <small>Copyright</strong> </small> </p>
    </div>
</div>
<!-- include footer start -->
<%@include file="common/footer.jsp"%>
<!-- include footer end -->

<!-- Ladda -->
<script src="${ctx}/static/js/plugins/ladda/spin.min.js"></script>
<script src="${ctx}/static/js/plugins/ladda/ladda.min.js"></script>
<script src="${ctx}/static/js/plugins/ladda/ladda.jquery.min.js"></script>

<!-- Sweet alert -->
<script src="${ctx}/static/js/plugins/sweetalert/sweetalert.min.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
        var l = $("#btnLogin").ladda();
        $("#USERNAME").focus();

        $("#btnLogin").unbind('click').click(function(){

            l.ladda('start');
            var param = {
                username : $("#USERNAME").val()
                ,password : $("#PASSWORD").val()
            };

            if (param.username == '') {
                // alert("用户名不能为空");
                swal ( "Oops" ,  "用户名不能为空" ,  "error" );
                l.ladda('stop');
                return;
            }
            if (param.password == '') {
                // alert("密码不能为空");
                swal ( "Oops" ,  "密码不能为空" ,  "error" );
                l.ladda('stop');
                return;
            }

            $.ajax({
                type : 'POST'
                ,url : contextRootPath + '/user/login.do'
                ,data : param
                ,dataType : 'json'
                ,success : function(response){
                    console.log(response);
                    l.ladda('stop');
                    if (response.status == 1) {
                        // alert(response.msg);
                        swal ( "Oops" , response.msg ,  "error" );
                    } else if (response.status == 0) {
                        var $frmCommon = $("#frmSPCICommon");
                        if ($frmCommon.length < 1) {
                            $frmCommon = $("<form/>").attr({id:"frmCunhoCommon", method:'POST'});
                            $(document.body).append($frmCommon);
                        }
                        $frmCommon.empty();
                        $frmCommon.attr('target', '_self');
                        $frmCommon.attr('action', contextRootPath + '/page/index.do');

                        $frmCommon.submit();
                    }
                }
                ,error : function(req, status, e) {
                    // alert(req, status, e);
                    console.log(e);
                    l.ladda('stop');
                }
            });


        });

    });
</script>


</body>

</html>
