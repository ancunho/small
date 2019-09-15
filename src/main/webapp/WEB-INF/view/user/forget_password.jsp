<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="<%=request.getContextPath()%>"/>

<!DOCTYPE html>
<html>
<head>
    <title>登录</title>
    <%@include file="../common/metahtml.jsp" %>

    <!-- Ladda style -->
    <link href="${ctx}/static/css/plugins/ladda/ladda-themeless.min.css" rel="stylesheet">

    <!-- Sweet Alert -->
    <link href="${ctx}/static/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">

</head>

<body class="gray-bg">

<div class="passwordBox animated fadeInDown">
    <div class="row">

        <div class="col-md-12">
            <div class="ibox-content">
                <h2 class="font-bold">找回密码</h2>
                <div class="forgetPasswordStep1">
                    <p class="mb0"><strong>1.验证用户名</strong> -- 2.验证找回密码答案 -- 3.修改密码</p>
                    <p class="mb5 questionDiv">验证密码的问题</p>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="m-t">
                                <div class="form-group">
                                    <input type="text" class="form-control" id="USERNAME" placeholder="请输入用户名">
                                </div>
                                <button type="submit" id="btnForgetGetQuestion"
                                        class="btn btn-primary block full-width m-b">验证用户名
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="forgetPasswordStep2" style="display:none;">
                    <p class="mb0">1.验证用户名 -- <strong>2.验证找回密码答案</strong> -- 3.修改密码</p>
                    <p class="mb5 questionDiv">验证密码的问题</p>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="m-t">
                                <div class="form-group">
                                    <input type="text" class="form-control" id="ANSWER" placeholder="请输入找回密码的答案">
                                </div>
                                <button type="submit" id="btnForgetCheckAnswer"
                                        class="btn btn-primary block full-width m-b">验证答案
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="forgetPasswordStep3" style="display:none;">
                    <p class="mb0">1.验证用户名 -- 2.验证找回密码答案 -- <strong>3.修改密码</strong></p>
                    <p class="mb5 questionDiv">验证密码的问题</p>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="m-t">
                                <div class="form-group">
                                    <input type="text" class="form-control" id="PASSWORDNEW" placeholder="请输入新密码">
                                </div>
                                <button type="submit" id="btnForgetResetPassword"
                                        class="btn btn-primary block full-width m-b">修改密码
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <hr/>
    <div class="row">
        <div class="col-md-6">Copyright Example Company</div>
        <div class="col-md-6 text-right">
            <small>© 2019-2020</small>
        </div>
    </div>
</div>
<!-- include footer start -->
<%@include file="../common/footer.jsp" %>
<!-- include footer end -->

<!-- Ladda -->
<script src="${ctx}/static/js/plugins/ladda/spin.min.js"></script>
<script src="${ctx}/static/js/plugins/ladda/ladda.min.js"></script>
<script src="${ctx}/static/js/plugins/ladda/ladda.jquery.min.js"></script>

<!-- Sweet alert -->
<script src="${ctx}/static/js/plugins/sweetalert/sweetalert.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        var l = $("#btnForgetGetQuestion").ladda();

        $("#btnForgetGetQuestion").unbind('click').click(function () {

            l.ladda('start');
            var param = {
                username: $("#USERNAME").val()
            };

            if (param.username == '') {
                swal("Oops", "用户名不能为空", "error");
                l.ladda('stop');
                return;
            }

            $.ajax({
                type: 'POST'
                , url: contextRootPath + '/user/forget_get_question.do'
                , data: param
                , dataType: 'json'
                , success: function (response) {
                    console.log(response);
                    l.ladda('stop');

                    if (response.status == 1) {
                        // alert(response.msg);
                        swal("Oops", response.msg, "error");
                    } else if (response.status == 0) {
                        $(".forgetPasswordStep1").hide();
                        $(".forgetPasswordStep2").show();
                        $(".forgetPasswordStep3").hide();
                        $(".questionDiv").text(response.data);

                        //验证答案开始
                        $("#btnForgetCheckAnswer").unbind('click').click(function () {
                            var param2 = {
                                username: param.username
                                , question: response.data
                                , answer: $("#ANSWER").val()
                            };
                            if (param2.answer == '') {
                                swal("Oops", "答案不能为空", "error");
                                return;
                            }

                            $.ajax({
                                type: 'POST'
                                , url: contextRootPath + '/user/forget_check_answer.do'
                                , data: param2
                                , dataType: 'json'
                                , success: function (response2) {
                                    if (response2.status == 1) {
                                        swal("Oops", response2.msg, "error");
                                    } else if (response2.status == 0) {
                                        $(".forgetPasswordStep1").hide();
                                        $(".forgetPasswordStep2").hide();
                                        $(".forgetPasswordStep3").show();

                                        //修改新密码开始
                                        $("#btnForgetResetPassword").unbind('click').click(function () {
                                            var param3 = {
                                                username: param.username
                                                , passwordnew: $("#PASSWORDNEW").val()
                                                , forgetToken: response2.data
                                            };
                                            if (param3.passwordnew == '') {
                                                swal("Oops", "新密码不能为空", "error");
                                                return;
                                            }

                                            $.ajax({
                                                type: 'POST'
                                                , url: contextRootPath + '/user/forget_reset_password.do'
                                                , data: param3
                                                , dataType: 'json'
                                                , success: function (response3) {
                                                    if (response3.status == 1) {
                                                        swal("Oops", response3.msg, "error");
                                                    } else if (response3.status == 0) {
                                                        swal({
                                                            title : response3.msg
                                                            ,text : '请通过新密码重新登入'
                                                            ,type : 'success'
                                                            ,showCancelButton : false
                                                            ,confirmButtonColor: "#1c84c6"
                                                            ,confirmButtonText: "确定"
                                                        },function(isConfirm){
                                                            var $frmCommon = $("#frmSPCICommon");
                                                            if ($frmCommon.length < 1) {
                                                                $frmCommon = $("<form/>").attr({id:"frmCunhoCommon", method:'POST'});
                                                                $(document.body).append($frmCommon);
                                                            }
                                                            $frmCommon.empty();
                                                            $frmCommon.attr('target', '_self');
                                                            $frmCommon.attr('action', contextRootPath + '/page/logout.do');

                                                            $frmCommon.submit();
                                                        });
                                                    }
                                                }
                                                , error: function (req, status, e) {
                                                    // alert(req, status, e);
                                                    console.log(e);
                                                }
                                            });

                                        });
                                        //修改新密码结束
                                    }
                                }
                                , error: function (req, status, e) {
                                    // alert(req, status, e);
                                    console.log(e);
                                }
                            });
                        });
                        //验证答案结束


                    }
                }
                , error: function (req, status, e) {
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
