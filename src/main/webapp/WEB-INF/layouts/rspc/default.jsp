<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp" %>
	

<!DOCTYPE html>
<html>
	<head>
		<title><sitemesh:title />-- rspc</title>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		<meta http-equiv="Cache-Control" content="no-store" />
		<meta http-equiv="Pragma" content="no-cache" />
		<meta http-equiv="Expires" content="0" />
		<script type="text/javascript">
		ctx = '${ctx}';
		</script>
		<sitemesh:head />

	</head>

	<body>
		<%@ include file="/WEB-INF/layouts/rspc/header.jsp"%>
		<div class="container" id="base">
			<sitemesh:body />
		</div>
	</body>
</html>