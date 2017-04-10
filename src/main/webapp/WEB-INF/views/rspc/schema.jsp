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
    <!-- Unnamed (Rectangle) -->
    <div id="u247" class="ax_default heading_1">
      <div id="u247_div" class=""></div>
      <!-- Unnamed () -->
      <div id="u248" class="text">
        <p><span><spring:message code="schema.label"/></span></p>
      </div>
    </div>

    <!-- Unnamed (Rectangle) -->
    <div id="u249" class="ax_default button">
      <button id="u249_div"><spring:message code="schema.save"></spring:message></button>
    </div>

    <!-- Unnamed (Text Area) -->
    <div id="u251" class="ax_default text_area">
      <textarea id="u251_input" name="body" datatype="*" onkeyup="contentChange(this.value)">${data.schema}</textarea>
    </div>
      </form>
    <script type="text/javascript">
      var defaultValue='${data.schema}';
      $(function(){
        $("#form").Validform({
          btnSubmit:"#u249",
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
          $("#u249_div").css({"opacity":"0.65","cursor":"default"});
        }else{
          $("#u249_div").css({"opacity":"1","cursor":"pointer"});
        }
      }
      contentChange(defaultValue);
    </script>
</body>
</html>
