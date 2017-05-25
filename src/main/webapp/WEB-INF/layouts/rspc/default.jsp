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

		<script type="text/javascript" src="${ctx}/static/js/angular-1.2.19/angular.js"></script>
		<script type="text/javascript" src="${ctx}/static/js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="${ctx}/static/js/angularPulgs/ngWdatePicker.js"></script>
		<script type="text/javascript" src="${ctx}/static/js/angularPulgs/ngPagination/ngPagination_beauty.js"></script>
		<script type="text/javascript" src="${ctx}/static/js/angularPulgs/overflow.js"></script>
		<script type="text/javascript" src="${ctx}/static/js/angularPulgs/httpHeader.js"></script>
		<script type="text/javascript" src="${ctx}/static/rspc/rspcApp.js"></script>
		<script type="text/javascript" src="${ctx}/static/rspc/dataService.js"></script>
		<script type="text/javascript">
			ctx = '${ctx}';
			layer.config({
				extend: 'extend/layer.ext.js',
				tiptype:4
			});
		</script>
		<sitemesh:head />
	</head>

	<body>
		<%@ include file="/WEB-INF/layouts/rspc/header.jsp"%>
		<div class="container" id="base" ng-app="rspcApp">
			<sitemesh:body />
		</div>
	</body>
</html>