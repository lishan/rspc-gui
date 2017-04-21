<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/3/7
  Time: 下午 01:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/commons/taglibs.jsp" %>
<html>
<head>
    <title><spring:message code="schema.title"/></title>
  <link href="${ctx}/static/rspc/schema/styles.css" type="text/css" rel="stylesheet"/>
</head>
<body>
<c:if test="${empty data}">
<form action="${ctx}/rspc/schema/data/save" method="post" id="form">
  </c:if>
  <c:if test="${!empty data}">
  <form action="${ctx}/rspc/schema/data/update" method="post" id="form">
    </c:if>
      <div class="form-group"style="padding-bottom: 15px">
        <label class="header-title"><spring:message code="schema.label"></spring:message></label>
      </div>
      <div class="form-group" style="height: 430px">
        <textarea class="form-control" id="schema_body" rows="20" datatype="*" name="body" onkeyup="contentChange(this.value)">${data.schema}</textarea>
        <textarea class="form-control hide" id="schema_body_default">${data.schema}</textarea>
      </div>
      <button class="btn  pull-right background-color_golden" id="submitBtn"><spring:message code="schema.save"></spring:message></button>
  </form>
    <script type="text/javascript">
      var defaultValue='${data.schema}';
      $(function(){
        $("#form").Validform({
          btnSubmit:"#submitBtn",
          ajaxPost:true,
          tiptype:4,
          postonce:true,
          beforeSubmit:function(curform){
            return document.forms[0]['body'].value!=defaultValue;
          },
          callback:function(data){
            if(data&&data.success){
              layer.alert("<spring:message code="schema.save.success"/>");
              defaultValue=document.forms[0]['body'].value;
              contentChange(defaultValue);
            }else{
              layer.alert("<spring:message code="schema.save.failed"/>");
            }
          }
        })
      });
      function contentChange(value){
        if(value==defaultValue){
          $("#submitBtn").css({"opacity":"0.65","cursor":"default"});
        }else{
          $("#submitBtn").css({"opacity":"1","cursor":"pointer"});
        }
      }
      contentChange(defaultValue);
    </script>
</body>
</html>
