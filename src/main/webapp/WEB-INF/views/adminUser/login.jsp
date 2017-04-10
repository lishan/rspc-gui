<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp" %>
<html>
  <head>
  	<title>登录</title>
	  <%--<link href="${ctx}/static/rspc/login/styles.css" type="text/css" rel="stylesheet"/>--%>
	  <style type="text/css">
		  .bg{
			  background: url("${ctx}/static/rspc/login/img/u0.png") no-repeat;
			  background-size: 100%;
			  height: 100%;
			  width: 100%;
		  }
		  .login-bg{
			  background-color: #CCCCCC;
			  position: absolute;
			  padding: 20px 10px 10px 10px;
			  width: 500px;
			  left:0;
			  right: 0;
			  margin: auto;
			  top: 50%;
			  /*margin-top:-150px;*/
		  }
	  </style>
  </head>
  <body >
  	<div class="bg">
		<div class="row background-color_golden" style="height: 38px">
			<div class="col-md-5">
				<div>
					<span><img src="${ctx}/static/rspc/login/img/u7.png" style="width: 38px;height: 38px"></span>
					<span class="banner_desc"><spring:message code="rspc.desc"></spring:message>：RSPC</span>
				</div>
			</div>
		</div>
		<div>
			<form class="form-horizontal login-bg" role="form" action="${ctx}/admin/user/login"　method="post" id="form">
			<c:if test="${! empty param.msg}">
				<div class="form-group  form-inline">
					<label class="col-md-8 text-danger text-center">
						<spring:message code="login.loginErr"></spring:message>
					</label>
				</div>
			</c:if>
				<div class="form-group  form-inline">
					<label class="col-md-4 control-label"><spring:message code="login.userName.label"></spring:message>：</label>
					<div class="col-md-4">
						<input type="text" class="form-control" name="userName" datatype="*" placeholder="<spring:message code="login.userName.placeholder"/>"/>
					</div>
				</div>
				<div class="form-group  form-inline">
					<label class="col-md-4 control-label"><spring:message code="login.userPwd.label"></spring:message>：</label>
					<div class="col-md-4">
						<input type="password" class="form-control" name="password"
							   datatype="*" placeholder="<spring:message code="login.userPwd.placeholder"/>"/>
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-10 text-right">
						<input type="submit" class="btn background-color_golden" value="<spring:message code="login.loginBtn"></spring:message>"/>
					</div>
				</div>
			</form>
		</div>
  	</div>
	<script type="text/javascript">
	$(function(){
	$("#form").Validform({
		btnSubmit:"#u17",
		tiptype:2
	})
	});
	</script>
  </body>
</html>
