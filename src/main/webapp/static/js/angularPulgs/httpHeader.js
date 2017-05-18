(function(angular){
    //增加一个自定义的http请求拦截器
    angular.module("httpHeader",[]).factory('HeaderInterceptor', [function(){
        function param(obj) {
            if (obj == undefined || obj == null) {
                throw "undefine and null is not be allow";
            }
            if (!obj instanceof Object) {
                throw "param must be a object";
            }
            if (obj instanceof Array) {
                throw "array is  not be allow";
            }
            var ar = [];
            for (var k in obj) {
                var v = (obj[k]==undefined || obj[k]==null) ? "" : obj[k];
                ar.push(k + "=" + v);
            }
            return ar.join("&").replace(/%20/g, "+");
        }
        return {
            request:function(config){
                if(config.method=='POST'){
                    config.headers['Content-Type']='application/x-www-form-urlencoded; charset=UTF-8';
                    config.transformRequest= function (data) {
                        data = data?data:{};
                        return param(data);
                    }
                }
                return config;
            },
            responseError: function (response) {
                //var data = response.data;
                // 判断错误码，如果是未登录
                if(response&&response.status==999){
                }

            }
        };
    }]).config(function ($httpProvider) {
        $httpProvider.interceptors.push('HeaderInterceptor');
    });
})(angular);
