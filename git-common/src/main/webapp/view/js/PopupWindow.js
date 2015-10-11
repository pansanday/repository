/**
 * message(mandatory)	窗口字体内容
 * level(mandatory)		"INFO" -> 结果正常,background-color是68AF02->绿色;(如不提供,默认是绿色)
 * 						"WARN" -> 警告,background-color是EF8F00->橘黄色
 * time(option)			窗口显示多久,单位:毫秒
 * width(option)		窗口宽度;默认是250px
 * 
 * 调用示例:
 * new PopupWindow({
 * 		message : "i am message",
 * 		level : "INFO"
 * });
 */

PopupWindow = function(config) {

	var me = this;
	
	// 初始化...
	me.initPopup = function(config) {
		me.isIE = window.ActiveXObject ? true : false;
		me.intAlphaStep = (window.ActiveXObject) ? 5 : 0.05; // 渐变步长,如果是IE浏览器,每次增加或减少5个透明度
		
		me.intTimeStep = 15; // 渐入渐出花费的时间,值越大越慢
		me.intCloseStep = config.time ? config.time : 2000; // 窗口显示多久

		me.clearTimeOut();

		config.message = config.message || "";
		
		me.curObj = document.createElement("div"); // 创建弹窗
		me.setStyle(me.curObj); // 设置弹窗样式
		document.body.appendChild(me.curObj); // 将拼接好的窗口渲染到页面上
	};
	
	// 弹窗样式
	me.setStyle = function(obj) {
		// 弹窗绝对位置
		obj.style.position = "absolute";
		obj.style.top = document.body.clientHeight*0.7/2;
		obj.style.left = document.body.clientWidth*0.7/2;
		
		obj.style.border = "1px solid #aaa"; // 边框样式
		obj.style.zIndex = 110;
		obj.style.filter = "alpha(opacity = 0)";
		
		// 弹窗上字体样式
		obj.style.fontSize = "20px"; // 字体大小
		obj.style.fontWeight =  "bold"; // 加粗
		obj.style.fontFamily = "微软雅黑,宋体,Arial, Helvetica, sans-serif"; // 字体样式
		obj.style.color = "#555"; // 字体颜色
		obj.style.textAlign = "center"; // 字体位置
		obj.style.padding = "35px 0"; // 字体距离上 - 右 - 下 - 左距离
		
		obj.style.backgroundColor = (config.level == "WARN" ? "EF8F00" : "68AF02"); // 设置背景色
		obj.style.width = config.width ? config.width : "250px"; // 设置宽度
		obj.innerHTML = config.message; // 设置要提示的消息
	}
	
	// 兼容性相关
	me.compatibility = function() {
		// 待扩展
	};

	// 显示窗口
	me.showPopup = function() {
		if (me.isIE) {
			//me.curObj.filters.alpha.opacity = 0;
			//me.curObj.filters.item("DXImageTransform.Microsoft.Alpha").Opacity = 0;
			//me.curObj.style.filter = "progid:DXImageTransform.Microsoft.Alpha(opacity=00)"; 
			me.curObj.style.filter = "alpha(opacity=0)";
		} else {
			me.curObj.style.opacity = 0;
		}
		me.curOpacity = 0;
		me.setObjOpen();
	};

	// 淡入效果
	me.setObjOpen = function() {
		me.timeCloseHandler = function() {
			me.setObjClose();
		};
		me.timeOpenHandler = function() {
			me.setObjOpen();
		}

		if (me.isIE) {
			me.curObj.filters.alpha.opacity += me.intAlphaStep;
			if (me.curObj.filters.alpha.opacity < 100) {
				me.timeOpen = setTimeout(me.timeOpenHandler, me.intTimeStep);
			} else {
				// 一秒后自动关闭
				me.timeClose = setTimeout(me.timeCloseHandler, me.intCloseStep);
			}
		} else {
			me.curOpacity += me.intAlphaStep;
			me.curObj.style.opacity = me.curOpacity;
			if (me.curOpacity < 1) {
				me.timeOpen = setTimeout(me.timeOpenHandler, me.intTimeStep);
			} else {
				me.timeClose = setTimeout(me.timeCloseHandler, me.intCloseStep);
			}
		}
	};
	
	// 淡出效果
	me.setObjClose = function() {
		me.timeCloseHandler = function() {
			me.setObjClose();
		};

		if (me.isIE) {
			me.curObj.filters.alpha.opacity -= me.intAlphaStep;
			if (me.curObj.filters.alpha.opacity > 0) {
				me.timeClose = setTimeout(me.timeCloseHandler, me.intTimeStep);
			} else {
				document.body.removeChild(me.curObj);
				me.curObj = null;
			}
		} else {
			me.curOpacity -= me.intAlphaStep;
			me.curObj.style.opacity = me.curOpacity;
			if (me.curOpacity <= 0) {
				document.body.removeChild(me.curObj);
				me.curObj = null;
			} else {
				me.timeClose = setTimeout(me.timeCloseHandler, me.intTimeStep);
			}
		}
	};
	
	// 清除时间
	me.clearTimeOut = function() {
		clearTimeout(me.timeClose);
		clearTimeout(me.timeOpen);
		if (me.curObj != null) {
			document.body.removeChild(me.curObj);
			me.curObj = null;
		}
	};

	me.initPopup(config);
	me.showPopup();
	if (config.callback) {
		config.callback();
	}

	return me;
};