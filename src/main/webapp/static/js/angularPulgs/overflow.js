/**
 * Created by Administrator on 2015/11/4.
 */

/**

 */
angular.module("ngOverflow",[])
    .directive('ngOverflow', function () {
    return {
        restrict:'A',
        priority:99999999,
        link: function (scope, element, attrs, ngModel) {
            setTimeout(function(){
                var offsetWidth =element[0].offsetWidth;
                if(element[0].scrollWidth>offsetWidth){
                    var fontSize=parseInt($(element[0]).css("fontSize"),10);
                    var fontNum= Math.floor(offsetWidth/fontSize);
                    var hasNum=0;
                    var hasStr='';
                    var hasE=0;
                    for(var k in element[0].innerText){
                        if(isChinese(element[0].innerText[k])){
                            hasNum++
                            hasStr+=element[0].innerText[k];
                        }else{
                            hasStr+=element[0].innerText[k];
                            if(hasE!=0&&hasE%2==0){
                                hasNum++;
                                hasE=0;
                            }
                            hasE++;
                        }
                        if(hasNum==fontNum){
                            break;
                        }
                    }
                    element[0].innerText=hasStr+"...";
                }
            },10)
            	function isChinese(temp){
                    var re=/[^\u4e00-\u9fa5A-Z]/;
                    return !re.test(temp);
                }
        }
    };
})