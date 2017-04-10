<%@ page  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="dateSimplePattern" value="yyyy-MM-dd" />
<c:set var="dateFullPattern" value="yyyy-MM-dd HH:mm:ss" />


<link href="${ctx}/static/bootstrap-3.3.0-dist/dist/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/js/Validform/style.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/js/layer-v1.9.3/layer/skin/layer.css" type="text/css" rel="stylesheet" />

<link href="${ctx}/static/data/styles.css" type="text/css" rel="stylesheet"/>
<link href="${ctx}/static/rspc/comm.css" type="text/css" rel="stylesheet"/>

<script src="${ctx}/static/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx }/static/bootstrap-3.3.0-dist/dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/layer-v1.9.3/layer/layer.js"></script>
<script type="text/javascript" src="${ctx}/static/js/layer-v1.9.3/layer/extend/layer.ext.js"></script>
<script type="text/javascript" src="${ctx}/static/js/Validform/Validform_v5.3.2.js"></script>

<script>
layer.config({
    extend: 'extend/layer.ext.js',
    tiptype:4
});
</script>