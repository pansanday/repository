var myModule = angular.module('MVC3', []);

myModule.controller('CommonController', ['$scope', 
	function ($scope){
		$scope.commonFn = function(){
			alert("这里是通用功能!");
		};
	}
]);

myModule.controller('Controller1', ['$scope', 
	function ($scope) {
		$scope.greeting = {
			text: 'Hello1'
		};
		$scope.test1=function(){
			alert("test1");
		};
	}
]);

myModule.controller('Controller2', ['$scope', 
	function ($scope) {
		$scope.greeting = {
			text: 'Hello2'
		};
		$scope.test2 = function(){
			alert("test2");
		};
	}
]);