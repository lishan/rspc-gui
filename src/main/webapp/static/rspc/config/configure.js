/**
 * Created by Administrator on 2017/3/17.
 */
var configModule = angular.module('configApp', ["httpHeader"]);
configModule.controller('configControl', ['$scope', 'dataService', function ($scope, dataService) {
    $scope.submitFlag = true;
    $scope.comFigFlag = true;
    $scope.ZookeeperFigFlag = true;
    $scope.TopoFigFlag = true;
    $scope.spoutConfigFlag = true;
    $scope.ruleboltConfigFlag = true;

    $scope.expand = function (domObj) {
        if (domObj == 'comFig') {
            $scope.comFigFlag = !$scope.comFigFlag;
            $scope.flag = $scope.comFigFlag;
        };
        if (domObj == 'ZookeeperFig') {
            $scope.ZookeeperFigFlag = !$scope.ZookeeperFigFlag;
            $scope.flag = $scope.ZookeeperFigFlag;
        };
        if (domObj == 'TopoFig') {
            $scope.TopoFigFlag = !$scope.TopoFigFlag;
            $scope.flag = $scope.TopoFigFlag;
        };
        if (domObj == 'spoutConfig') {
            $scope.spoutConfigFlag = !$scope.spoutConfigFlag;
            $scope.flag = $scope.spoutConfigFlag;
        };
        if (domObj == 'ruleboltConfig') {
            $scope.ruleboltConfigFlag = !$scope.ruleboltConfigFlag;
            $scope.flag = $scope.ruleboltConfigFlag;
        };

        if ($scope.flag) {
            $("#" + domObj).show(100);
        } else {
            $("#" + domObj).hide(100);
        };
    };

    $scope.pageData = {};
    var data = {};
    dataService.get().success(function (d) {
        if (d.success) {
            $scope.pageData = d.data;
            data = angular.copy(d.data);
        };
    });

    $scope.isChange = function (d) {
        if (JSON.stringify(d) != JSON.stringify(data)) {
            $scope.submitFlag = false;
        } else {
            $scope.submitFlag = true;
        }
    }

    $("#configForm").Validform({
        btnSubmit: "#btn",
        ajaxPost:true,
        tiptype: 2,
        postonce:true,
        beforeSubmit: function (curform) {

            if (JSON.stringify($scope.pageData) == JSON.stringify(data)) {
                $scope.submitFlag = true;
            } else {
                $scope.submitFlag = false;
                dataService.update($scope.pageData).success(function(d){
                    if(d&& d.success){
                        layer.alert(saveSuccess);
                        data = angular.copy(d.data);
                        $scope.isChange(data);
                    }else{
                        layer.alert(saveFailed);
                    }

                })
            };
            return false;
        }
    });

}]).service('dataService', ['$http', function ($http) {

    this.get = function () {
        return $http.post(ctx + '/rspc/config/data/get', {});
    };

    this.update = function (param) {
        return $http.post(ctx + '/rspc/config/data/update', {body: JSON.stringify(param)});
    };
}]);