angular.module('configureApp',["dataService"])
.controller('configureContro',['$scope','dataService',function($scope,dataService){
        $scope.pageData={};
        dataService.getConfig().success(function(d){
            if(d.success){
                $scope.pageData= d.data;
            }
        });

        $scope.expand=function(domObj){
            $(domObj).collapse('toggle')
        }

}])