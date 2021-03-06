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
    <title><spring:message code="dashBoard.title"></spring:message></title>
    <link href="${ctx}/static/rspc/dashBoard/styles.css" type="text/css" rel="stylesheet"/>
    <style>
        .area{}
        .area:hover{
            border: outset ;
            /*border-color: #0088cc;*/
        }
    </style>
    <script type="text/javascript">
    var startFailure= '<spring:message code="dashBoard.task_start_failure"/>';
    var noData= '<spring:message code="dashBoard.noData"/>';
    var echartsTitle= '<spring:message code="dashBoard.echarts.title"/>';
    var echartsLegend= '<spring:message code="dashBoard.echarts.legend"/>';
    </script>
</head>
<body>
<div ng-controller="dashBoardContro">
    <div class="area" style="width: 100%;height: 300px;margin-top: 30px">
        <!-- Unnamed (Rectangle) -->
        <div id="runState" class="ax_default heading_1">
            <!-- Unnamed () -->
            <div id="runStatelabel" class="text">
                <p><span style="font-family:'Arial-BoldMT', 'Arial Bold', 'Arial';font-weight:700;">RSPC</span>
          <span style="font-family:'PingFangSC-Semibold', 'PingFang SC Semibold', 'PingFang SC';font-weight:650;">
            <spring:message code="dashBoard.label"/>
          </span></p>
            </div>
        </div>
        <!-- Unnamed (Image) -->
        <div id="runStateIcon" class="ax_default image" style="margin-top: 3px">
            <span ng-if="!isRunnion" style="color: red" class="glyphicon glyphicon-info-sign"></span>
            <span ng-if="isRunnion" style="color: green" class="glyphicon glyphicon-ok-sign"></span>
        </div>
        <!-- 启动 (Rectangle) -->
        <button id="runStateStart"  type="button" class="btn" ng-class="{disabled:isRunnion}"
                ng-click="taskStart(1)"><spring:message code="dashBoard.task_start"/></button>
        <!-- 停止 (Rectangle) -->
        <button id="runStateStop"  type="button" class="btn"  ng-class="{disabled:!isRunnion}"
                ng-click="taskStart(0)"><spring:message code="dashBoard.task_stop"/></button>

        <!-- Unnamed (Ellipse) -->
        <div id="runStateDataSource" class="ax_default marker">
            <img id="runStateDataSource_img" class="img " src="${ctx}/static/rspc/dashBoard/img/u46.png"/>
            <!-- Unnamed () -->
            <div id="runStateDataSourceLabel" class="text">
                <p><span><spring:message code="dashBoard.dataSource"/></span></p>
            </div>
        </div>

        <!-- Unnamed (Rectangle) -->
        <div id="runStateDataJoin" class="ax_default sticky_1">
            <div id="runStateDataJoin_div" class=""></div>
            <!-- Unnamed () -->
            <div id="runStateDataJoinLabel" class="text">
                <p><span>
              <spring:message code="dashBoard.dataJoin"/>
        </span></p><p><span><br></span></p><p>
                <span ng-bind="'<spring:message code="dashBoard.workerNum"/>'+configInfo.topo.spoutConfig.numberTasks">0</span></p>
            </div>
        </div>

        <!-- Unnamed (Rectangle) -->
        <div id="runStateRule" class="ax_default sticky_3">
            <div id="runStateRule_div" class=""></div>
            <!-- Unnamed () -->
            <div id="runStateRuleLabel" class="text">
                <p><span> <spring:message code="dashBoard.rule"/></span></p><p><span><br></span></p><p>
                <span ng-bind="'<spring:message code="dashBoard.workerNum"/>'+configInfo.topo.ruleBoltConfig.numberTasks">0</span></p>
            </div>
        </div>

        <!-- Unnamed (Horizontal Line) -->
        <div id="runStateGif1" class="ax_default arrow">

            <img id="runStateGif1_img" ng-if="!isRunnion" class="img" ng-src="${ctx}/static/rspc/dashBoard/img/u54.png"/>
            <img id="runStateGif1_img" style="z-index: -1;width: 108px" ng-if="isRunnion" class="img gifImg" ng-src="${ctx}/static/rspc/dashBoard/img/gif031.gif"/>
        </div>

        <!-- Unnamed (Horizontal Line) -->
        <div id="runStateGif2" class="ax_default arrow">
            <img id="runStateGif2_img" ng-if="!isRunnion" class="img" ng-src="${ctx}/static/rspc/dashBoard/img/u56.png"/>
            <img id="runStateGif2_img" style="z-index: -1;width: 108px" ng-if="isRunnion" class="img gifImg" ng-src="${ctx}/static/rspc/dashBoard/img/gif031.gif"/>
        </div>
        <!-- Unnamed (Horizontal Line) -->
        <div id="runStateGif3" class="ax_default arrow">
            <img id="runStateGif3_img" ng-if="!isRunnion" class="img" ng-src="${ctx}/static/rspc/dashBoard/img/u60.png"/>
            <img id="runStateGif3_img" style="z-index: -1;width: 108px" ng-if="isRunnion" class="img gifImg" ng-src="${ctx}/static/rspc/dashBoard/img/gif031.gif"/>
        </div>
        <!-- Unnamed (Ellipse) -->
        <div id="runStateDataOut" class="ax_default marker">
            <img id="runStateDataOut_img" class="img " src="${ctx}/static/rspc/dashBoard/img/u46.png"/>
            <div id="runStateDataOutLabel" class="text">
                <p><span><spring:message code="dashBoard.outData"/></span></p>
            </div>
        </div>

    </div>
    <!-- Unnamed (Rectangle) -->
    <div class="area" style="width: 100%;height: 475px;margin-top: 20px">
        <div id="rulestatistic" class="ax_default heading_1">
            <div id="rulestatisticLabel" class="text">
                <p><span><spring:message code="dashBoard.rulestatistic"/></span></p>
            </div>
        </div>
        <!-- Unnamed (Image) -->
        <div id="u42" class="ax_default image" style="height: 400px"></div>
    </div>

</div>
<script src="${ctx}/static/js/Echarts/echarts.min.js"></script>
<script src="${ctx}/static/rspc/dashBoard/active.js"></script>
</body>
</html>
