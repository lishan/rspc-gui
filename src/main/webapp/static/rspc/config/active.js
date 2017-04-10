angular.module('configureApp',[])
.controller('configureContro',['$scope','dataService',function($scope,dataService){
        $scope.pageData={};
        dataService.get().success(function(d){
            if(d.success){
                $scope.pageData= d.data;
            }
        });

        $scope.expand=function(domObj){
            $(domObj).collapse('toggle')
        }

}])
.service('dataService',['$http',function($http){

        this.get = function(){
            return ajaxRequest(ctx+'/rspc/config/data/get',{});
        };

        function ajaxRequest(url, params) {
            return $http.post(url, params, {
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},
                transformRequest: function (data) {
                    return $.param(data);
                }
            })
        }
    }]);