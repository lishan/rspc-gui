/*
 * 陕西识代运筹技术有限公司
 */

/**
 * Created by Administrator on 2015/10/15.
 */
/**使用示例
$scope.page={
    content:[],
    totalPages:0,
    totalElements:0,
    size:10,
    number:0,
    sort:{property:'',direction:''}
}
 */
    // <ng-pagination page="{{init.page}}" to-page="Function(当前页数)" show-num="2"></ng-pagination>
angular.module("ngPagination",[])
    .factory('ngPagiationFactory',function(){
        return{
            getPageSize:function(){
                return  10;
            },
            createPageNums:function(pageNum,currentPage,showNum){
            var pageArr=[];
            var bPage= 0,ePage=pageNum;
            if(pageNum&&showNum){
                var pc=Math.round(showNum/1);

                if(currentPage-pc>=0){
                    bPage=currentPage-(currentPage%showNum);
                }

                ePage=bPage+parseInt(showNum,10);
                if(ePage>pageNum){
                    ePage=pageNum;
                }
            }
            for(var bPage;bPage<ePage;bPage++){
                pageArr.push(bPage+1);
            }
            return pageArr;
        }
        }
    })
    .directive("ngTable",function(){
        return {
            restrict:"E",
            scope:{}
        }
    })
    .directive("ngOrder",function($document){
        var directiveDefinitionObject={
            replace:false,
            //transclude:true,
            restrict:'A',
            require : '^?toPage',
            scope:{
                order:'@ngOrder'
            },
            controller:function($scope,$element,$attrs,$transclude){
                $scope.$watch('$parent.pagination.sort',function(n,o){
                    if(!n) return;
                    var order=n[$scope.order];
                    var a=null;
                    if("A"===$element[0].nodeName){
                        a= $element;
                    }else{
                        a= $element.find("a");
                    }

                    switch (order){
                        case "desc":
                        case "DESC":
                            a.attr('class','px2');
                            break;
                        case "ASC":
                        case "asc":
                            a.attr('class','px3');
                            break;
                        default :
                            a.attr('class','px1');
                            break;
                    }

                });
                $element.on('click',function(){
                    var order =$scope.order;
                    var sort={};
                    if(!$scope.$parent.pagination.sort){
                        $scope.$parent.pagination.sort={};
                    }
                    var orderby= $scope.$parent.pagination.sort[order];
                    switch (orderby){
                        case "desc":
                        case "DESC":
                            sort[order]='ASC';
                            break;
                        case "ASC":
                        case "asc":
                            sort[order]='DESC';
                            break;
                        default :
                            sort[order]='DESC';
                            break;
                    }
                    $scope.$parent.pagination.sort=sort;
                    if($scope.$parent.toPage){
                        $scope.$parent.toPage($scope.$parent.pagination.currPage,$scope.$parent.pagination.slicerNum,$scope.$parent.pagination.sort);
                    }
                    $scope.$apply();
                });
            }

        }
        return directiveDefinitionObject;
    })
    .directive("ngPagination", function ($document,ngPagiationFactory) {

        var showNum =5;

        var directiveDefinitionObject={
            priority:0,
            template: '<div ng-include="contentUrl"></div>' ,
            replace:true,
            //transclude:true,
            restrict:'E',
            scope:{
                totalEle:"=",
                currPage:"=",
                showNum:"@",
                slicer:"@",
                toPage:"=",
                sort:'='
            },
            controller:function($scope,$element,$attrs,$transclude){

                if(!$scope.showNum){
                    $scope.showNum=showNum;
                }

                //分页器
                if(!$scope.slicer){
                    $scope.slicer=[10,15,20];
                }
                $scope.slicerNum=$scope.slicer[0];

                $scope.$watch(function(){
                    return $scope.totalEle+":"+$scope.currPage+":"+$scope.showNum;
                },function(n){
                    $scope.totalPage=Math.ceil($scope.totalEle/$scope.slicerNum);
                    if(!$scope.showNum){
                        $scope.showNum=showNum;
                    }
                    $scope.pageNums=ngPagiationFactory.createPageNums($scope.totalPage,$scope.currPage,$scope.showNum);
                })
                //排序
                if(!$scope.sort||null==$scope.sort){
                    $scope.sort={};
                }
                //将当前scpoe放到父级scpoe中
                $scope.$parent.pagination=$scope;
                //点击钮
                $scope.clickBitchBtn=function(bitchFlag){
                    var bitch= $scope.currPage%$scope.showNum;
                    var page;
                    if("next"==bitchFlag){
                        page=$scope.currPage+(5-bitch);
                        if(page>$scope.totalPage-1){
                            page=$scope.totalPage-1;
                        }
                    }else{
                        page=$scope.currPage-bitch-5;
                        if(page<0){
                            page=0;
                        }
                    }

                    $scope.clickPageNum(page);

                }


                //点击页数
                $scope.clickPageNum=function(page){
                    var exeFun=true;
                    if(page<0){
                        page=0;
                        exeFun=false;
                    }
                    if(page>$scope.totalPage-1){
                        page=$scope.totalPage-1;
                        exeFun=false;
                    }
                    $scope.currPage=page;

                    if( exeFun&&$attrs.toPage){
                        $scope.toPage(page,$scope.slicerNum,$scope.sort)
                        //$scope.$parent.toPage();
                    }
                }
                //刷新
                $scope.refresh=function(){
                    $scope.clickPageNum($scope.currPage);
                }
                //点击分割器
                $scope.slctSlicer=function(slicerNum){
                    $scope.slicerNum=slicerNum;
                    $scope.clickPageNum(0);
                }
                //初始化
                $scope.toPage(0,$scope.slicerNum,$scope.sort)
            },
            link:function(scope, element, attrs){
                if(attrs.template){
                    scope.contentUrl =  attrs.template;
                }else{
                    scope.contentUrl="/static/js/angularPulgs/ngPagination/template/paginationTemplate.html";
                }
            }
        }
        return directiveDefinitionObject;

    });