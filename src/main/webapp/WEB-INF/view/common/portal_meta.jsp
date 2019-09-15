<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="<%=request.getContextPath()%>" />
<script type="text/javascript">
    var contextRootPath = "${ctx}";
</script>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="${ctx}/static/css/bootstrap.min.css" rel="stylesheet">
<link href="${ctx}/static/font-awesome/css/font-awesome.css" rel="stylesheet">

<link href="${ctx}/static/css/animate.css" rel="stylesheet">
<link href="${ctx}/static/css/style.css" rel="stylesheet">