/**
 * 在IE中模拟DOMContentLoaded事件
 * @param {Function} callback [description]
 */
function IEContentLoaded(callback) {
	(function() {
		try {
			document.documentElement.doScroll('left');
		} catch (error) {
			setTimeout(argument.callee, 0);
			return;
		}
		callback();
	}());
}
// 使用
IEContentLoaded(function() {
	alert('hello');
});
/**
 * 虚拟console对象
 * @param  {[type]} !window.console [description]
 * @return {[type]}                 [description]
 */
if (!window.console) {
	(function(win) {
		var names = ['assert', 'clear', 'count', 'debug', 'dir', 'dirxml', 'error', 'exception', 'group',
			'groupCollapsed', 'groupEnd', 'info', 'log', 'notifyFirebug', 'profile', 'profileEnd', 'table', 
			'time', 'timeEnd', 'trace', 'warn'];
		var consoleMock = {};
		for (var i = 0, len = names.length; i < len; i++) {
			consoleMock[names[i]] = function() {};
		}
		win.console = consoleMock;
	}(window));
}

/**
 * 基于用户代理的跨浏览器支持策略
 * 跨浏览器支持的事件侦听注册方法
 * @return {[type]} [description]
 */
var addEvent = function(target, name, fn) {
	// 判断正在访问该页面的浏览器是否是Internet Explorer
	var isIE = navigator.userAgent.indexOf('MSIE') > 0;
	if (isIE) {
		// IE中不存在addEventListener()方法,因此要换用attachEvent()方法
		addEvent = function(target, name, fn) {
			target.attachEvent('on' + name, fn);
		};
	} else {
		addEvent = function(target, name, fn) {
			target.addEventListener(name, fn, false);
		};
	}
	addEvent(target, name, fn);
};

/**
 * 基于功能测试的跨浏览器支持策略
 * @return {[type]} [description]
 */
var addEvent = function (target, name, fn){
	if (window.addEventListener) {
		addEvent = function (target, name, fn) {
			target.addEventListener(name, fn, false);
		};
	} else if (window.attachEvent) {
		addEvent = function(target, name, fn) {
			target.attachEvent('on'+name, fn);
		};
	}
	addEvent(target, name, fn);
};
