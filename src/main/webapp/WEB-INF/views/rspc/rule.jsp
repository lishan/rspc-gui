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
<form action="${ctx}/rspc/rule/data/save" method="post" id="form">
</c:if>
  <c:if test="${!empty rule}">
  <form action="${ctx}/rspc/rule/data/update" method="post" id="form">
    </c:if>
  <!-- Unnamed (Rectangle) -->
  <div id="u216" class="ax_default heading_1">
    <div id="u216_div" class=""></div>
    <!-- Unnamed () -->
    <div id="u217" class="text">
      <p><span><spring:message code="rule.label"></spring:message></span></p>
    </div>
  </div>



  <!-- Unnamed (Text Area) -->
  <div id="u225" class="ax_default text_area">
    <textarea id="u225_input" datatype="*" name="body" onkeyup="contentChange(this.value)">${rule}</textarea>
    <textarea type="text" id="u225_input_default" class="hidden" >${rule}</textarea>
  </div>
  <!-- Unnamed (Text Field) -->
  <div id="u222" class="ax_default text_field">
    <input id="u222_input" type="text" value=""/>
  </div>
  <!-- Unnamed (Rectangle) -->
  <div id="u218" class="ax_default button">
    <button id="u218_div"><spring:message code="rule.save"></spring:message></button>
  </div>
  <!-- Unnamed (Rectangle) -->
  <div id="u220" class="ax_default label">
    <div id="u220_div" class=""></div>
    <!-- Unnamed () -->
    <div id="u221" class="text">
      <p><span><spring:message code="rule.uploadLabel"></spring:message></span></p>
    </div>
  </div>
</form>

<%--<script src="${ctx}/static/rspc/rule/data.js"></script>--%>
  <link rel="stylesheet" href="${ctx}/static/js/uploadify/uploadify.css" type="text/css">
  <script type="text/javascript" src="${ctx}/static/js/uploadify/jquery.uploadify.min.js"></script>
<script type="text/javascript">
  var defaultValue=$("#u225_input_default").text();
  function contentChange(value){
    if(value==defaultValue){
      $("#u218_div").css({"opacity":"0.65","cursor":"default"});
    }else{
      $("#u218_div").css({"opacity":"1","cursor":"pointer"});
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

        //返回数据data是json对象，{"info":"demo info","status":"y"}
        //info: 输出提示信息;
        //status: 返回提交数据的状态,是否提交成功。如可以用"y"表示提交成功，"n"表示提交失败，在ajax_post.php文件返回数据里自定字符，主要用在callback函数里根据该值执行相应的回调操作;
        //你也可以在ajax_post.php文件返回更多信息在这里获取，进行相应操作；
        //ajax遇到服务端错误时也会执行回调，这时的data是{ status:**, statusText:**, readyState:**, responseText:** }；

        //这里执行回调操作;
        //注意：如果不是ajax方式提交表单，传入callback，这时data参数是当前表单对象，回调函数会在表单验证全部通过后执行，然后判断是否提交表单，如果callback里明确return false，则表单不会提交，如果return true或没有return，则会提交表单。
      }
    })

    var url = '${ctx}/uploadify.do';
    $("#u222_input").uploadify({
      'swf'       	 : '${ctx}/static/js/uploadify/uploadify.swf',
      'uploader'       : url,//后台处理的请求
      'fileTypeDesc' : '文件' , //出现在上传对话框中的文件类型描述
      'fileTypeExts' : '*.*', //控制可上传文件的扩展名，启用本项时需同时声明filedesc

      'multi'          : false,
      'buttonText'     : '<spring:message code="rule.upload"/>',
      'height':32,
      onUploadSuccess:function(file, data, response){
        $.ajax({
          url:'${ctx}/rspc/rule/data/ajax/read?fileName='+file.name,
          success:function(d){
            $("#u225_input").html(d);
          }
        })

        <%--var imgId = "headImg_"+new Date().getTime();--%>
        <%--$("#filemanager").after("<input type='hidden' name='headImg' value='/upload/userHead/"+file.name+"'>")--%>
                <%--.after('<img id='+imgId+' src="${ctx}/upload/userHead/'+file.name+'"/>');--%>

        //同时启动裁剪操作，触发裁剪框显示，让用户选择图片区域
//        initJcrop($("#"+imgId));
      },
      onUploadError:function(file, errorCode, errorMsg) {
        alert("<spring:message code="rule.upload.failed"/>,file="+file.name);
      }
    });
  });
</script>
</body>
</html>
