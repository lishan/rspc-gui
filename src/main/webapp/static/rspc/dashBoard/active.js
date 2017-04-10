angular.module('dashBoardApp',[])
.controller('dashBoardContro',['$scope','dataService',function($scope,dataService){
        $scope.configInfo={
            topo:{
                spoutConfig:{numberTasks:0},
                ruleBoltConfig:{numberTasks:0}
            }
        };

        $scope.taskStart=function(isRunnion){
            $scope.isRunnion=isRunnion;
            if(isRunnion){
                dataService.get().success(function(d){
                    if(d.success){
                        $scope.configInfo= d.data;
                    }
                });
            }else{
                $scope.configInfo.topo.spoutConfig.numberTasks=0;
                $scope.configInfo.topo.ruleBoltConfig.numberTasks=0;
            }
        }


        dataService.statistic().success(function(d){
            if(d.success){
                var xData=[],yData=[];
                for(var item in d.data.statistic){
                    xData.push(d.data.statistic[item].rulename);
                    yData.push(d.data.statistic[item].number);
                }
                initEcharts(xData,yData);
            }
        });

        function initEcharts(xData,yData){
            var myChart = echarts.init(document.getElementById('u42'));
            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: '24小时告警触发Top10'
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                        type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                    }
                },
                legend: {
                    data:['告警']
                },
                xAxis: {
                    data: xData
                },
                yAxis: {},
                series: [{
                    name: '告警',
                    type: 'bar',
                    data: yData
                }]
            };
            myChart.setOption(option);

        }


}])
.service('dataService',['$http',function($http){

        this.get = function(){
            return ajaxRequest(ctx+'/rspc/config/data/get',{});
        };
        this.statistic = function(){
            return ajaxRequest(ctx+'/rspc/event/data/statistic',{});
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