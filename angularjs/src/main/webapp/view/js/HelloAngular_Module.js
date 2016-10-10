var myModule = angular.module('HelloAngular', []);

// 依赖注入
myModule.controller('helloAngular', ['$scope', 
	function HelloAngular($scope) {
		$scope.greeting = {
			text: 'Hello'
		};
	}
]);