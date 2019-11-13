<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="<%=request.getContextPath()%>" />
<script type="text/javascript">
    var contextRootPath = "${ctx}";
</script>
<html>
<head>
    <meta charset="utf-8">
    <link href="${ctx}/static/js/plugins/wangEditor/wangEditor.min.css" rel="stylesheet">
    <script src="${ctx}/static/js/plugins/wangEditor/wangEditor.min.js"></script>
</head>
<body>

<div id="editor"></div>
<button class="btn btn-default" id="submit">提交</button>

<h2>springmvc Single 上传文件</h2>
<form name="form1" action="/managed/product/singleUpload.do" method="post" enctype="multipart/form-data">
    <input type="file" name="singleFileUpload" />
    <input type="submit" value="springmvc Single 上传文件" />
</form>

<h2>springmvc Multie 上传文件</h2>
<form name="form1" action="/managed/product/multipleUpload.do" method="post" enctype="multipart/form-data">
    <input type="file" name="multiFileUpload" multiple="multiple" />
    <input type="submit" value="springmvc Multie 上传文件" />
</form>
<script type="text/javascript">
    var E = window.wangEditor
    var editor = new E('#editor')
    // 或者 var editor = new E( document.getElementById('editor') )
    // editor.customConfig.uploadImgShowBase64 = true;
    editor.customConfig.uploadImgServer = "/managed/product/multipleUpload.do";
    editor.customConfig.uploadFileName = "multiFileUpload";

    editor.create();

    // $("#submit").unbind('click').click(function(e){
    //     var content =  editor.txt.html();
    //
    // });
</script>
</body>
</html>
