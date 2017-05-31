rspcApp.controller('dashBoardContro',['$scope','dataService',function($scope,dataService){
        $scope.configInfo={
            topo:{
                spoutConfig:{numberTasks:0},
                ruleBoltConfig:{numberTasks:0}
            }
        };

        $scope.taskStart=function(isRunnion){
            $scope.isRunnion=isRunnion;
            if(isRunnion){
                dataService.getConfig().success(function(d){
                    if(d.success){
                        $scope.configInfo= d.data;
                    }else{
                        $scope.isRunnion=false;
                        layer.alert(startFailure);
                    }
                });
            }else{
                $scope.configInfo.topo.spoutConfig.numberTasks=0;
                $scope.configInfo.topo.ruleBoltConfig.numberTasks=0;
            }
        }


        dataService.statistic().success(function(d){
            var xData=[],yData=[];
            if(d.success){
                for(var item in d.data.statistic){
                    xData.push(d.data.statistic[item].rulename);
                    yData.push(d.data.statistic[item].number);
                }
                initEcharts(xData,yData);
            }else{
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
                grid:{
                  bottom:140
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
                    data: xData,
                    axisLabel:{
                        interval:0,
                        rotate:30
                    }
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