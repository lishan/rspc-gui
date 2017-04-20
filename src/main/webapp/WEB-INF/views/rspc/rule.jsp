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
    <title><spring:message code="rule.title"></spring:message></title>
  <link href="${ctx}/static/rspc/rule/styles.css" type="text/css" rel="stylesheet"/>
  <script type="text/javascript" src="${ctx}/static/js/uploadify/jquery.uploadify.min.js"></script>
  <link href="${ctx}/static/js/uploadify/uploadify.css" type="text/css" rel="stylesheet">
  <style>
    .uploadify-queue{
      margin-top: 30px;
    }

    #u218{
      top: 556px;
    }
    #u219{
      top: 9px;
    }
  </style>
</head>
<body>

<c:if test="${empty rule}">
<form action="${ctx}/rspc/rule/data/save" method="post" id="form" role="form">
</c:if>
  <c:if test="${!empty rule}">
  <form action="${ctx}/rspc/rule/data/update" method="post" id="form" role="form">
    </c:if>

    <div class="form-group">
      <label class="header-title"><spring:message code="rule.label"></spring:message></label>
      <textarea class="form-control" id="rult_body" rows="20" datatype="*" name="body" onkeyup="contentChange(this.value)">${rule}</textarea>
      <textarea class="form-control hide" id="rult_body_default">${rule}</textarea>
    </div>
    <div class="row">

      <div class="form-group col-md-5">
        <label for="uploadFile" class="col-sm-4 control-label text-right"><spring:message code="rule.uploadLabel"/></label>
        <div class="col-sm-8">
          <input type="text" class="form-control" name="uploadFile" id="uploadFile" >
        </div>
      </div>
      <div class="col-md-7">
        <button class="btn  pull-right background-color_golden" id="submitBtn"><spring:message code="rule.save"></spring:message></button>
      </div>
    </div>
</form>

<%--<script src="${ctx}/static/rspc/rule/data.js"></script>--%>
  <link rel="stylesheet" href="${ctx}/static/js/uploadify/uploadify.css" type="text/css">
  <script type="text/javascript" src="${ctx}/static/js/uploadify/jquery.uploadify.min.js"></script>
<script type="text/javascript">
  var defaultValue=$("#rult_body_default").text();
  function contentChange(value){
    if(value==defaultValue){
      $("#submitBtn").css({"opacity":"0.65","cursor":"default"});
    }else{
      $("#submitBtn").css({"opacity":"1","cursor":"pointer"});
    }
  }
  contentChange(defaultValue);
  $(function(){
    $("#form").Validform({
      btnSubmit:"#u218",
      ajaxPost:true,
      tiptype:4,
      beforeSubmit:function(curform){
        return document.forms[0]['body'].value!=defaultValue;
      },
      callback:function(data){
        if(data&&data.success){
          layer.alert("<spring:message code="rule.save.success"/>");
          document.forms[0]['body'].value=data.data;
          defaultValue=document.forms[0]['body'].value;
          contentChange(defaultValue);
        }else{
          layer.alert("<spring:message code="rule.save.failed"/>");
        }

      }
    })

    var url = '${ctx}/uploadify.do';
    $("#uploadFile").uploadify({
      'swf'       	 : '${ctx}/static/js/uploadify/uploadify.swf',
      'uploader'       : url,//后台处理的请求
      'fileTypeDesc' : '文件' , //出现在上传对话框中的文件类型描述
      'fileTypeExts' : '*.*', //控制可上传文件的扩展名，启用本项时需同时声明filedesc

      'multi'          : false,
      'buttonText'     : '<spring:message code="rule.upload"/>',
      'height':32,
      onUploadSuccess:function(file, data, response){
        $("#rult_body,#rult_body_default").html(data);
      },
      onUploadError:function(file, errorCode, errorMsg) {
        alert("<spring:message code="rule.upload.failed"/>,file="+file.name);
      }
    });
  });
</script>
</body>
</html>
