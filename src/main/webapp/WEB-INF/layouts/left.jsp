<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="modules.adminUser.entity.PermissionType"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%--${pageContext.request.requestURI}--%>
<a href="${ctx}" class="list-group-item ">首页</a>

<div class="panel-group" id="accordion">
	<shiro:hasRole name="coding">
	  <div class="panel panel-info">
	    <div class="panel-heading">
	     <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne"> <h4 class="panel-title"> 开发人员 </h4> </a>
	    </div>
	    <div id="collapseOne" class="panel-collapse collapse">
			<a id="1-1" href="${ctx}/generate/tableList#1-1" class="list-group-item">代码生成</a>
			<a id="1-2" href="${ctx}/web/owner/form/1#1-2" class="list-group-item">业主（测试）</a>
	    </div>
	  </div>
  </shiro:hasRole>
  <shiro:hasAnyRoles name="admin,coding">
  	<div class="panel panel-info">
	    <div class="panel-heading">
	     <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo"> <h4 class="panel-title">管理员 </h4> </a>
	    </div>
	    <div id="collapseTwo" class="panel-collapse collapse">
			<a id="2-1" href="${ctx}/admin/user/list#2-1" class="list-group-item">用户管理</a>
	       	<a id="2-2" href="${ctx}/admin/role/list#2-2" class="list-group-item">角色管理</a>
			<a id="2-3" href="${ctx}/admin/permission/view#2-3" class="list-group-item">功能管理</a>
	    </div>
	  </div>
  </shiro:hasAnyRoles>
  <shiro:hasRole name="wxUser">
  	<div class="panel panel-info">
	    <div class="panel-heading">
	     <a data-toggle="collapse" data-parent="#accordion" href="#collapseThree"> <h4 class="panel-title">微信用户 </h4> </a>
	    </div>
	    <div id="#collapseThree" class="panel-collapse collapse">
	    </div>
	  </div>
  </shiro:hasRole>
 
	  <div class="panel panel-info">
	    <div class="panel-heading">
	       <a data-toggle="collapse" data-parent="#accordion" href="#collapseFour"> <h4 class="panel-title"> 功能菜单 </h4> </a>
	    </div>
	    <div id="collapseFour" class="panel-collapse collapse">
	    	<c:set var="menu" value="<%=PermissionType.MENU.toString() %>" />
	     	<shiro:user>
		       <c:forEach items="${menus}" var="bean">
					<a id="${bean.id}" href="${ctx}/${bean.url}#${bean.id}" class="list-group-item">${bean.name}</a>
				</c:forEach>
			</shiro:user>
	    </div>
	  </div>
  
</div>
<script type="text/javascript">
 	var hash=location.hash;
	$(hash).addClass('glyphicon glyphicon-hand-right');
	$(hash).parent().addClass('in');
</script>

