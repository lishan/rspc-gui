/**
 * Created by Administrator on 2017/5/18.
 */
angular.module('dataService',["httpHeader"])
    .service('dataService',['$http',function($http){

        /**
         * 获取config
         * @returns {HttpPromise}
         */
        this.getConfig = function(){
            return $http.post(ctx+'/rspc/config/data/get',{});
        };

        this.updateConfig = function (param) {
            return $http.post(ctx + '/rspc/config/data/update', {body: JSON.stringify(param)});
        };
        /**
         *  获取统计
         * @returns {HttpPromise}
         */
        this.statistic = function(){
            return $http.post(ctx+'/rspc/event/data/statistic',{});
        };

        /**
         * 获取事件数据
         * @param page
         * @param pageSize
         * @param rulename
         * @param bDate
         * @param eDate
         * @returns {HttpPromise}
         */
        this.getEvent = function(page,pageSize,rulename,bDate,eDate){
            return $http.post(ctx+'/rspc/event/data/get',
                {
                    page:page,
                    pageSize:pageSize,
                    rulename:rulename,
                    bDate:bDate,
                    eDate:eDate
                });
        };

    }]);