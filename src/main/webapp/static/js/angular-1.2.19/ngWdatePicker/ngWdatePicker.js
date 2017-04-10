angular.module("ui.WdatePicker",[])
.directive('ngWdatePicker', function () {
   return {
     require: 'ngModel',
     link: function (scope, element, attrs, ngModel) {
       if (!ngModel) {
         return;
       }
       element.bind("click", function () {
         var opts = angular.extend({
           onpicked: function (dp) {
             ngModel.$setViewValue(dp.cal.getDateStr());
             scope.$digest();
           }
         }, scope.$eval(attrs.ngWdatePicker));
         WdatePicker(opts);
       });
     }
   };
 })