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
    <title><spring:message  code="config.title"/></title>
    <link href="${ctx}/static/rspc/config/configure.css" rel="stylesheet" type="text/css">
    <script>
        var saveSuccess='<spring:message  code="config.save.success"/>';
        var saveFailed='<spring:message  code="config.save.failed"/>';
    </script>
</head>
<body>
<div ng-app="configApp" ng-controller="configControl" style="padding-top: 50px">
    <form id="configForm">
        <span class="header-title"><spring:message  code="config.label"/></span>
        <div style="height: auto">
            <div class="row-title" ng-click="expand('comFig')">
                <img ng-show="comFigFlag" src="${ctx}/static/rspc/config/img/topo___u104.png">
                <img ng-show="!comFigFlag" src="${ctx}/static/rspc/config/img/u171.png">
                <span><spring:message  code="config.commConfig"/></span>
            </div>
            <div id="comFig">
                <div class="config-input" style="">
                    <span style="">stormServer：</span>

                    <div class="" style="">
                        <input type="text" id="stormServer" datatype="*" ng-change="isChange(pageData)"
                               ng-model="pageData.stormServer" name="stormServer" style="width: 911px">
                    </div>
                </div>
                <div class="config-input">
                    <span>stormUiServer：</span>

                    <div class="" style="">
                        <input type="text" datatype="*" ng-model="pageData.stormUiServer" ng-change="isChange(pageData)"
                               name="stormUiServer" style="width: 911px">
                    </div>
                </div>
                <div class="config-input">
                    <span class="">stormDRPCServer：</span>

                    <div class="" style="">
                        <input type="text" datatype="*" ng-change="isChange(pageData)"
                               name="stormDRPCServer"
                               ng-model="pageData.stormDRPCServer" style="width: 911px">
                    </div>
                </div>
            </div>
        </div>
        <div style="height: auto">
            <div class="row-title" ng-click="expand('ZookeeperFig')">
                <img ng-show="ZookeeperFigFlag" src="${ctx}/static/rspc/config/img/topo___u104.png">
                <img ng-show="!ZookeeperFigFlag" src="${ctx}/static/rspc/config/img/u171.png">
                <span><spring:message  code="config.zkConfig"/></span>
            </div>
            <div id="ZookeeperFig">
                <div class="config-input">
                    <span>servers：</span>
                    <div class="" style="">
                        <input type="text" datatype="*" ng-change="isChange(pageData)"
                               name="zkConfig.servers"
                               ng-model="pageData.zkConfig.servers" style="width: 911px">
                    </div>
                </div>
                <div class="config-input">
                    <span>zookeeperServers：</span>

                    <div class="" style="">
                        <input type="text" datatype="*" ng-change="isChange(pageData)"
                               name="zkConfig.zookeeperServers"
                               ng-model="pageData.zkConfig.zookeeperServers" style="width: 911px">
                    </div>
                </div>
                <div class="config-input">
                    <span class="">brokerpath：</span>

                    <div class="" style="">
                        <input type="text" datatype="*" ng-change="isChange(pageData)"
                               name="zkConfig.brokerPath"
                               ng-model="pageData.zkConfig.brokerPath" style=";width: 911px">
                    </div>
                </div>
                <div class="config-input">
                    <span class="">stomzkpath：</span>

                    <div class="" style="">
                        <input type="text" datatype="*" ng-change="isChange(pageData)"
                               name="zkConfig.stormzkpath"
                               ng-model="pageData.zkConfig.stormzkpath" style=";width: 911px">
                    </div>
                </div>
                <div class="config-input">
                    <span class="">port：</span>

                    <div class="" style="">
                        <input type="text" datatype="*" ng-change="isChange(pageData)"
                               name="zkConfig.port"
                               ng-model="pageData.zkConfig.port"
                               style=";width: 911px">
                    </div>
                </div>
            </div>
        </div>
        <div style="height: auto">
            <div class="row-title" ng-click="expand('TopoFig')">
                <img ng-show="TopoFigFlag" src="${ctx}/static/rspc/config/img/topo___u104.png">
                <img ng-show="!TopoFigFlag" src="${ctx}/static/rspc/config/img/u171.png">
                <span><spring:message  code="config.topoConfig"/></span>
            </div>
            <div id="TopoFig">
                <div class="config-input">
                    <span>topoName：</span>

                    <div class="" style="">
                        <input type="text" datatype="*" ng-change="isChange(pageData)"
                               name="topo.topoName"
                               ng-model="pageData.topo.topoName"
                               style="width: 911px">
                    </div>
                </div>
                <div class="config-input">
                    <span>numberOfWorkers：</span>

                    <div class="" style="">
                        <input type="text" datatype="*" ng-change="isChange(pageData)"
                               name="topo.numberOfWorkers"
                               ng-model="pageData.topo.numberOfWorkers" style="width: 911px">
                    </div>
                </div>
                <div class="config-input">
                    <span class="">maxSpoutPending：</span>

                    <div class="" style="">
                        <input type="text" datatype="*"
                               name="topo.maxSpoutPending"
                               ng-change="isChange(pageData)"
                               ng-model="pageData.topo.maxSpoutPending" style=";width: 911px">
                    </div>
                </div>
                <div style="margin-left: 15px">
                    <div style="height: auto">
                        <div class="row-title" style="width: 1086px" ng-click="expand('spoutConfig')">
                            <img ng-show="spoutConfigFlag" src="${ctx}/static/rspc/config/img/topo___u104.png">
                            <img ng-show="!spoutConfigFlag" src="${ctx}/static/rspc/config/img/u171.png">
                            <span><spring:message  code="config.spoutConfig"/></span>
                        </div>
                        <div id="spoutConfig">
                            <div class="config-child">
                                <span>topic：</span>

                                <div class="" style="">
                                    <input type="text" datatype="*" ng-change="isChange(pageData)"
                                           name="topo.spoutConfig.topic"
                                           ng-model="pageData.topo.spoutConfig.topic" style="width: 911px">
                                </div>
                            </div>
                            <div class="config-child">
                                <span>server：</span>

                                <div class="" style="">
                                    <input type="text" datatype="*" ng-change="isChange(pageData)"
                                           name="topo.spoutConfig.server"
                                           ng-model="pageData.topo.spoutConfig.server" style="width: 911px">
                                </div>
                            </div>
                            <div class="config-child">
                                <span class="">groupid：</span>

                                <div class="" style="">
                                    <input type="text" datatype="*" ng-change="isChange(pageData)"
                                           name="topo.spoutConfig.groupid"
                                           ng-model="pageData.topo.spoutConfig.groupid" style=";width: 911px">
                                </div>
                            </div>
                            <div class="config-child">
                                <span class="">serializer：</span>

                                <div class="" style="">
                                    <input type="text" datatype="*" ng-change="isChange(pageData)"
                                           name="topo.spoutConfig.serializer"
                                           ng-model="pageData.topo.spoutConfig.serializer" style=";width: 911px">
                                </div>
                            </div>
                            <div class="config-child">
                                <span class="">id：</span>

                                <div class="" style="">
                                    <input type="text" datatype="*" ng-change="isChange(pageData)"
                                           name="topo.spoutConfig.id"
                                           ng-model="pageData.topo.spoutConfig.id" style=";width: 911px">
                                </div>
                            </div>
                            <div class="config-child">
                                <span class="">maxTaskParallelism：</span>

                                <div class="" style="">
                                    <input type="text" datatype="*" ng-change="isChange(pageData)"
                                           name="topo.spoutConfig.maxTaskParallelism"
                                           ng-model="pageData.topo.spoutConfig.maxTaskParallelism"
                                           style=";width: 911px">
                                </div>
                            </div>
                            <div class="config-child">
                                <span class="">numberTasks：</span>

                                <div class="" style="">
                                    <input type="text" datatype="*" ng-change="isChange(pageData)"
                                           name="topo.spoutConfig.numberTasks"
                                           ng-model="pageData.topo.spoutConfig.numberTasks" style=";width: 911px">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div style="height: auto">
                        <div class="row-title" style="width: 1086px" ng-click="expand('ruleboltConfig')">
                            <img ng-show="ruleboltConfigFlag" src="${ctx}/static/rspc/config/img/topo___u104.png">
                            <img ng-show="!ruleboltConfigFlag" src="${ctx}/static/rspc/config/img/u171.png">
                            <span><spring:message  code="config.rulebolt"/></span>
                        </div>
                        <div id="ruleboltConfig">
                            <div class="config-child">
                                <span>id：</span>

                                <div class="" style="">
                                    <input type="text" datatype="*" ng-change="isChange(pageData)"
                                           name="topo.ruleBoltConfig.id"
                                           ng-model="pageData.topo.ruleBoltConfig.id" style="width: 911px">
                                </div>
                            </div>
                            <div class="config-child">
                                <span>maxTaskParallelism：</span>

                                <div class="" style="">
                                    <input type="text" datatype="*" ng-change="isChange(pageData)"
                                           name="topo.ruleBoltConfig.maxTaskParallelism"
                                           ng-model="pageData.topo.ruleBoltConfig.maxTaskParallelism"
                                           style="width: 911px">
                                </div>
                            </div>
                            <div class="config-child">
                                <span class="">numberTasks：</span>

                                <div class="" style="">
                                    <input type="text" datatype="*" ng-change="isChange(pageData)"
                                           name="topo.ruleBoltConfig.numberTasks"
                                           ng-model="pageData.topo.ruleBoltConfig.numberTasks" style=";width: 911px">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <p></p>
        <div class="btn" id="btn" ng-click="submitForm(pageData)" ng-disabled="submitFlag">
            <span><spring:message  code="config.save"/></span>
        </div>
    </form>
</div>

<script type="text/javascript" src="${ctx}/static/js/angular-1.2.19/angular.js"></script>
<script type="text/javascript" src="${ctx}/static/js/angularPulgs/httpHeader.js"></script>
<script src="${ctx}/static/rspc/config/configure.js"></script>

</body>
</html>
