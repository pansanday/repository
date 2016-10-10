var myModule = angular.module('MyModule', []);

// 定义了<hello></hello>标签
myModule.directive('hello', [function () {
	return {
		restrict: 'E',
		template: '<div>Hi everyone!</div>',
		replace: true
		/*link: function (scope, iElement, iAttrs) {
			
		}*/
	};
}]);