angular.module('eventApp',['ngPagination','ngWdatePicker','ngOverflow'])
.controller('eventContro',['$scope','dataService',function($scope,dataService){

        $scope.page={
            content:[],
            totalElements:0,
            size:15,
            number:1,
            sort:{property:'',direction:''}
        }
        $scope.area={
            area_1:false,
            area_2:false
        }

        $scope.toPage=function(page,pageSize,sort){
            pageSize=15;
            dataService.get(page,pageSize,$scope.rulename,$scope.bDate,$scope.eDate).success(function(d){
                if(d.success){
                    if(d.data){
                        $scope.page.totalElements= d.data.totalPageNumber;
                        $scope.page.content= d.data.events;
                        $scope.page.size= d.data.pageSize;
                        $scope.page.number= d.data.currentPage-1;
                        setTimeout(function(){
                            $("[data-toggle='tooltip']").tooltip({html : true ,placement:'auto'});
                        },500)
                    }else{
                        $scope.page.totalElements= 0;
                        $scope.page.content= [];
                        $scope.page.size=15;
                        $scope.page.number= 0;
                    }

                }
            });

        }
        $scope.toPage();
        //模态框操作方法
        //$('#myModal').on('hidden.bs.modal', function () {
        //    $scope.rulename="",$scope.bDate="",$scope.eDate="";
        //    $scope.$apply();
        //})
        $scope.search=function(){
            //if($scope.rulename||$scope.bDate||$scope.eDate){
                $scope.toPage(0,15);
                $('#myModal').modal('hide');
            //}
        }

        $scope.slctArea=function(area){
            console.info(area);
            if(area=="area_1"){
                $scope.area={
                    area_1:true,
                    area_2:false
                }
            }else{
                $scope.area={
                    area_1:false,
                    area_2:true
                }
            }
        }
}])
.service('dataService',['$http',function($http){

        this.get = function(page,pageSize,rulename,bDate,eDate){
            return ajaxRequest(ctx+'/rspc/event/data/get',
                {
                    page:page,
                    pageSize:pageSize,
                    rulename:rulename,
                    bDate:bDate,
                    eDate:eDate
                });
        };

        function ajaxRequest(url, params) {
            return $http.post(url, params, {
                headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},
                transformRequest: function (data) {
                    return $.param(data);
                }
            })
        }
    }])
    .filter("dateFormat",function(){
        return function(date){
            if(date){
                return new Date(date).toLocaleString();
            }
        }
    }).filter("jsonFormat",function(){
        return function(json){
            if(json){
                return json2list(JSON.parse(json));
            }
        }
    });

/*
 json2list 可以很方便的把json数据转换为html列表
 可接受的参数类型为typeof: 'object'
 包括：[...], {...}, null
 [...] 表示数组形式的对象 typeOf: 'array'
 {...} 表示散列形式的对象 typeOf: 'object'
 null 表示什么都没有的对象 typeOf: 'null'
 在这里，null, [], {} 的输出结果都是: 'null'
 by rugby, wlxku123@gmail.com 2011.5.14
 */

var json2list = function _parse(o) {
    if (isFalse(o))  // 这个实际上只检查 [...], {...}, null
        return 'null'; // 如果是[],{}和null其中一个，就返回'null'字符
    else {
        var i, s = '', t = typeOf(o);
        for (i in o) {
            var s1 = '', t1 = typeof o[i];
            if (t1 === 'object')
                s1 = _parse(o[i]);
            else // 对于[...]及{...}以外的，直接字符化即可
                s1 = '<span>' + String(o[i]) + '</span>';

            if (t === 'array')
                s += '<li>' + s1 + '</li>';
            else //'object'需要加上键值
                s += '<li><span>' + i + '</span> : ' + s1 + '</li>';
        }
    }
    return '<ul>' + s + '</ul>';
}

// Default (typeof):
// typeof Object() === 'object'
// typeof Array() === 'object'
// typeof Function() === 'function'
// typeof String() === 'string'
// typeof Number() === 'number'
// typeof Boolean() === 'boolean'
// typeof null === 'object'
// typeof undefined === 'undefined'
// Costum (typeOf):
// typeOf(Object()) === 'object'
// typeOf(Array()) === 'array'
// typeOf(Function()) === 'function'
// typeOf(String()) === 'string'
// typeOf(Number()) === 'number'
// typeOf(Boolean()) === 'boolean'
// typeOf(null) === 'null'
// typeOf(undefined) === 'undefined'

function typeOf(value) {
    var t = typeof value;
    if (value === null)
        t = 'null'; //null表示什么都没有,只有null===null才返回true
    if (t === 'object' && value.constructor === Array)
        t = 'array'; //如果type确实为'object'，后面才会执行
    return t;
}

// when checked by isFalse(),
// the following values return true.
// Object: {}
// Array: []
// Function: depends on its returned value
// String: '' or ""
// Number: 0, NaN
// Boolean: false
// null: null
// undefined: undefined
// and others return false.

function isFalse(value) {
    var i, s = true, t = typeof value;
    if (value !== null && t === 'object')
        for(i in value) return false; //如果对象是可枚举的
    else if (t === 'function')
        return isFalse(value()); //依据函数的返回值判定
    else
        s = value ? false : true;
    return s;
}
