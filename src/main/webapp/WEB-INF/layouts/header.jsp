<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp"%>
<div class="row" style="height: 20%;overflow: hidden">
	 <div id="myCarousel" class="carousel slide col-md-10" data-ride="carousel">
	   <div class="carousel-inner text-center">
	      <div class="item active">
	         <img src="${ctx}/static/imgs/banner1.jpg" alt="First slide">
	      </div>
	      <div class="item">
	         <img src="${ctx}/static/imgs/banner2.jpg" alt="Second slide">
	      </div>
	   </div>
	   <a class="carousel-control left" href="#myCarousel" 
	      data-slide="prev">&lsaquo;</a>
	   <a class="carousel-control right" href="#myCarousel" 
	      data-slide="next">&rsaquo;</a>
	</div> 
	<div class="col-md-2" style="padding-bottom: 0px;">
		<shiro:user>  
			欢迎[<shiro:principal property="userName"/>]登录
			<p class="pull-right" style="bottom: 0px; position: relative;" id="activeNum"></p>
		</shiro:user>
		<shiro:guest>
			<a class="btn btn-link" href="${ctx}/admin/user/logint"><h3>注册</h3></a>
			<a class="btn btn-link" href="${ctx}/admin/user/login.action"><h3>登录</h3></a>
		</shiro:guest>
		<shiro:user>
			<a class="text-right" href="${ctx}/admin/user/logout"><h4>退出</h4></a>
		</shiro:user>
	</div>
</div>