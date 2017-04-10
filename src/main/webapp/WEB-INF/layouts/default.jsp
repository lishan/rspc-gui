<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp" %>
	

<!DOCTYPE html>
<html>
	<head>
		<title><sitemesh:title />-- bvc_wechat</title>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
		<meta http-equiv="Cache-Control" content="no-store" />
		<meta http-equiv="Pragma" content="no-cache" />
		<meta http-equiv="Expires" content="0" />
		<script type="text/javascript">
		ctx = '${ctx}';
		</script>
		<style type="text/css">
		 table {
        table-layout: fixed;
        }
	    td {
	    overflow: hidden;
	    white-space: nowrap;
	    text-overflow: ellipsis;
	    }
	    .min-height{
	    	min-height: 450px!important;
	    }
	    .bg{
  		background: url("${ctx}/static/imgs/meinv_1.png") no-repeat scroll;
  		background-size:cover;  
  	}
		</style>
		<sitemesh:head />
	</head>

	<body>
		<div class="col-md-12 ">
			<div class="panel panel-success bg">
			   <div class="panel-heading">
			      <%@ include file="/WEB-INF/layouts/header.jsp"%>
			   </div>
			   <div class="panel-body">
			   	<div class="col-md-2">
					 <%@ include file="/WEB-INF/layouts/left.jsp"%>
			   	</div>
			   	<div  class="col-md-10 min-height">
			   	  <sitemesh:body />
			   	</div>
			   </div>
				<div class="panel-footer text-center">
				 <%@ include file="/WEB-INF/layouts/footer.jsp" %>
				</div>
			</div>
		</div>
	</body>
</html>