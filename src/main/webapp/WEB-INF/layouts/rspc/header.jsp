<%@ page language="java" pageEncoding="UTF-8" %>
<style>
	.navbar .active a{background-color: #ffffff!important;}
</style>
<nav class="navbar navbar-default background-color_golden" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header col-md-5">
			<a class="navbar-brand" style="padding-top: 10px">
				<span class="banner_desc">
				<img src="${ctx}/static/rspc/login/img/u7.png" style="width: 33px;height: 33px;">
					<spring:message code="rspc.desc"></spring:message>
				</span>
			</a>
			<%--<a class="navbar-brand" href="#">菜鸟教程</a>--%>
		</div>
		<div class="col-md-5">
			<ul class="nav navbar-nav">
				<li nav="/rspc/dashBoard"><a  style="color: #0000FF" href="${ctx}/rspc/dashBoard"><div ><spring:message code="dashBoard.title"/></div></a></li>
				<li nav="/rspc/config"><a  style="color: #0000FF" href="${ctx}/rspc/config"><div ><spring:message code="config.title"/></div></a></li>
				<li nav="/rspc/rule"><a  style="color: #0000FF" href="${ctx}/rspc/rule"><div ><spring:message code="rule.title"/></div></a></li>
				<li nav="/rspc/schema"><a  style="color: #0000FF" href="${ctx}/rspc/schema"><div ><spring:message code="schema.title"/></div></a></li>
				<li nav="/rspc/event"><a  style="color: #0000FF" href="${ctx}/rspc/event"><div ><spring:message code="event.title"/></div></a></li>
			</ul>
		</div>
		<div class="navbar-header col-md-2" style="vertical-align: middle">
			<a class="navbar-brand" style="color: #ffffff"><spring:message code="rspc.currUser"></spring:message><shiro:principal property="userName"/></a>
			<a class="navbar-brand" style="color: #0000FF" href="${ctx}/admin/user/logout"><spring:message code="rspc.logout"></spring:message></a>
		</div>
	</div>
</nav>
<script>
//	$("li[nav]").removeClass("active");
	$("li[nav='"+location.pathname+"']").addClass("active");
</script>
<%--<div class="row background-color_golden" style="height: 38px">--%>
	<%--<div class="col-md-5">--%>
		<%--<div>--%>
			<%--<span><img src="${ctx}/static/rspc/login/img/u7.png" style="width: 38px;height: 38px"></span>--%>
			<%--<span class="banner_desc"><spring:message code="rspc.desc"></spring:message></span>--%>
		<%--</div>--%>
	<%--</div>--%>
	<%--<shiro:user>--%>
	<%--<div class="col-md-5 banner_item">--%>
		<%--<a href="${ctx}/rspc/dashBoard"><div class="col-md-2 left_border"><spring:message code="dashBoard.title"/></div></a>--%>
		<%--<a href="${ctx}/rspc/config"><div class="col-md-2"><spring:message code="config.title"/></div></a>--%>
		<%--<a href="${ctx}/rspc/rule"><div class="col-md-2"><spring:message code="rule.title"/></div></a>--%>
		<%--<a href="${ctx}/rspc/schema"><div class="col-md-2"><spring:message code="schema.title"/></div></a>--%>
		<%--<a href="${ctx}/rspc/event"><div class="col-md-2"><spring:message code="event.title"/></div></a>--%>
	<%--</div>--%>
	<%--<div class="col-md-2" style="padding-top: 7px">--%>
			<%--<span class="banner_user"><spring:message code="rspc.currUser"></spring:message><shiro:principal property="userName"/></span>--%>
			<%--<span><a style="color: #0000FF" href="${ctx}/admin/user/logout"><spring:message code="rspc.logout"></spring:message></a></span>--%>
	<%--</div>--%>
	<%--</shiro:user>--%>
<%--</div>--%>
