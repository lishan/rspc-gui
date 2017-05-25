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
    <title><spring:message code="event.title"/></title>
    <style type="text/css">
        table {
            table-layout: fixed;
        }
        td {
            overflow: hidden;
            white-space: normal;
            text-overflow: ellipsis;
        }
        .tooltip-inner{
            max-width: 100%;
        }
    </style>
</head>
<body>
<div ng-controller="eventContro" style="padding-top: 50px">
  <span class="header-title"><spring:message code="event.label"/></span>
  <div>
    <span class="pull-right "  data-toggle="modal" data-target="#myModal"><a href="javascript:void (0)"><spring:message code="event.filter"/></a></span>
  </div>
  <ng-table>
      <table class="table table-bordered table-hover">
        <thead>
        <tr style="background-color:#cc6600">
          <th width="100"><spring:message code="event.table_C1"/></th>
          <th width="200"><spring:message code="event.table_C2"/></th>
          <th width="200"><spring:message code="event.table_C3"/></th>
          <th><spring:message code="event.table_C4"/></th>
        </tr>
        </thead>
        <tbody>
        <tr ng-repeat="item in page.content">
          <td><p data-toggle="tooltip" ng-bind="item.id" title="{{'<h4>'+item.id+'</h4>'}}"></p></td>
          <td><p data-toggle="tooltip" ng-overflow="" ng-bind="item.rulename" title="{{'<h4>'+item.rulename+'</h4>'}}"></p></td>
          <td><p data-toggle="tooltip" ng-overflow="" ng-bind="item.creatdon|dateFormat" title="{{'<h4>'+(item.creatdon|dateFormat)+'</h4>'}}"></p></td>
          <td id="{{'c4_'+item.id}}">
              <p data-toggle="tooltip" ng-overflow="" verflow ng-bind="item.message" title="{{'<h4>'+(item.message|jsonFormat)+'</h4>'}}"></p>
          </td>
        </tr>
        <tr ng-if="page.content.length==0">
            <td colspan="4" style="text-align: center"><spring:message code="event.table.noData"></spring:message></td>
        </tr>
        </tbody>
      </table>
      <span ng-if="page.content.length>0">
          <ng-pagination
                  curr_page="page.number"
                  to_page="toPage"
                  sort='page.sort'
                  total_ele="page.totalElements"
                  />
      </span>

    </ng-table>

  <!-- 模态框（Modal） -->
  <div class="modal" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-body">
          <div class="form-group">
            <label for="rulename"><spring:message code="event.filter.ruleName"/></label>
            <input type="text" class="form-control"
                   ng-model="rulename"
                   id="rulename" placeholder="<spring:message code="event.filter.ruleName.placeholder"/>">
          </div>
          <div class="form-group">
            <label for="bDate"><spring:message code="event.filter.bDate"/></label>
            <input type="text" class="form-control"
                   ng-wdate-picker="{maxDate:'#F{$dp.$D(\'eDate\')}'}"
                   ng-model="bDate"
                   id="bDate" placeholder="<spring:message code="event.filter.bDate.placeholder"/>">
          </div>
          <div class="form-group">
            <input type="text" class="form-control"
                   ng-wdate-picker="{minDate:'#F{$dp.$D(\'bDate\')}'}"
                   ng-model="eDate"
                   id="eDate" placeholder="<spring:message code="event.filter.eDate.placeholder"/>">
          </div>


        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-primary" ng-click="search()"><spring:message code="event.filter.submit"/></button>
          <button type="button" id="cancelBtn" class="btn btn-default" data-dismiss="modal"><spring:message code="event.filter.cancel"/></button>
        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal -->
  </div>
</div>

  <script type="text/javascript" src="${ctx}/static/rspc/event/active.js"></script>
</body>
</html>
