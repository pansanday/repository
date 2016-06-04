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