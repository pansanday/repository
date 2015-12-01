/**
 * config参数列表:
 * 		text(必填) : 提示窗口中显示的提示信息(如:XXX成功)
 * 		level(最好提供): "INFO" -> 结果正常,background-color是68AF02->绿色;(如不提供,默认是绿色)
 *						"WARN" -> 警告,background-color是EF8F00->橘黄色
 * 		width(可选) : 弹出窗宽度(数字)
 * 		height(可选) : 弹出窗高度(数字)
 * 		callback(未用到) : 回调函数，当此方法被实现后调用，并将window对象下的result对象返回（注意，当需要窗口返回值时必须覆盖此方法）
 * 		示例: 
 		new PopupWindow({
			level: "WARN",
			text : "XXX不能为空"
		});
 */
PopupWindow = function(config){

	var me = this;
	
	/**
	 * 获取顶层窗口
	 */
	me.getTopWindow = function() {
		var top = window.top;
		var win = window;
		var count = 0;
		while(!win._isTop && count < 10) {
			count++;
			if (win == top) {
				break;
			} else {
				win = win.parent;
			}
		}
		return win;
	};
	
	var topwin = me.getTopWindow();
	
	// 初始化...
	me.initPopup = function(config) {
		config.text = config.text || "";
		
		me.isIE = window.ActiveXObject ? true : false;
		
		config.level = config.level || "INFO";
		if(me.isIE){
			// 创建弹窗
			me.oPopup = window.createPopup();
			me.popWindowHeight = config.height || 100; // 弹出窗高度
			me.popWindowWidth = config.width || 250; // 弹出窗宽度
			me.popWindowBackgroundColor = (config.level == "INFO" ? "68AF02":"EF8F00");
			me.scrollWidth = 18; // 滚动条宽度
			me.popWindowTop = topwin.document.body.clientHeight*0.6/2;
			
			// 弹出窗样式
			me.winstr = "<table style='background-color:"
				+ me.popWindowBackgroundColor + ";border: 1 solid blue;' height='" 
				+ me.popWindowHeight + "' width='" + me.popWindowWidth 
				+ "'><tr><td></td><td></td><td></td></tr><tr><td></td><td align='center' style='color:#FFF; font-size:20px;font-weight:bold;font-family: '微软雅黑',"
				+ " Arial, Helvetica, sans-serif;'><b>"
				+ config.text
				+ "</b></td><td></td></tr><tr><td></td><td></td><td></td></tr></table>";
			//me.oPopup.top = me.popWindowTop;
			me.oPopup.document.body.innerHTML = me.winstr;
		} else {
			me.intAlphaStep = 0.05; // 渐变步长,如果是IE浏览器,每次增加或减少5个透明度
			me.intTimeStep = 15; // 渐入渐出花费的时间,值越大越慢
			me.intCloseStep = config.time ? config.time : 400; // 窗口显示多久
			me.clearTimeOut();
			me.curObj = topwin.document.createElement("div"); // 创建弹窗
			me.setStyle(me.curObj); // 设置弹窗样式

			topwin.document.body.appendChild(me.curObj); // 将拼接好的窗口渲染到页面上
		}
	};
	
	// 弹窗样式
	me.setStyle = function(obj) {
		// 弹窗绝对位置
		obj.style.position = "absolute";
		
		obj.style.border = "1px solid #aaa"; // 边框样式
		obj.style.zIndex = 10000;
		obj.style.filter = "alpha(opacity = 100)";
		
		// 弹窗上字体样式
		obj.style.fontSize = "20px"; // 字体大小
		obj.style.fontWeight =  "bold"; // 加粗
		obj.style.fontFamily = "微软雅黑,宋体,Arial, Helvetica, sans-serif"; // 字体样式
		obj.style.color = "#555"; // 字体颜色
		obj.style.textAlign = "center"; // 字体位置
		obj.style.padding = "35px 0"; // 字体距离上 - 右 - 下 - 左距离
		
		obj.style.backgroundColor = (config.level == "WARN" ? "EF8F00" : "68AF02"); // 设置背景色
		obj.style.width = config.width ? config.width : "250px"; // 设置宽度
		
		// 弱提示居中
		obj.style.left = (topwin.document.body.clientWidth - parseInt(obj.style.width))/2;
		obj.style.top = topwin.document.body.clientHeight*0.6/2;
		obj.innerHTML = "<font color='#FFFFFF'>" + config.text + "</font>"; // 设置要提示的消息
	}
	
	// 兼容性相关
	me.compatibility = function() {
		// 待扩展
	};

	// 显示窗口
	me.showPopup = function() {
		if (me.isIE) {
			me.curObj.style.filter = "alpha(opacity=100)";
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

		me.curOpacity += me.intAlphaStep;
		me.curObj.style.opacity = me.curOpacity;
		if (me.curOpacity < 1) {
			me.timeOpen = setTimeout(me.timeOpenHandler, me.intTimeStep);
		} else {
			me.timeClose = setTimeout(me.timeCloseHandler, me.intCloseStep);
		}
	};
	
	// 淡出效果
	me.setObjClose = function() {
		me.timeCloseHandler = function() {
			me.setObjClose();
		};

		me.curOpacity -= me.intAlphaStep;
		me.curObj.style.opacity = me.curOpacity;
		if (me.curOpacity <= 0) {
			topwin.document.body.removeChild(me.curObj);
			me.curObj = null;
		} else {
			me.timeClose = setTimeout(me.timeCloseHandler, me.intTimeStep);
		}
	};
	
	// 清除时间
	me.clearTimeOut = function() {
		clearTimeout(me.timeClose);
		clearTimeout(me.timeOpen);
		if (me.curObj != null) {
			topwin.document.body.removeChild(me.curObj);
			me.curObj = null;
		}
	};
	
	me.showPopupIE = function() {
		/**
		 * 弹出弹窗
		 * show(x轴位置,y轴位置,弹出窗宽度,弹出窗高度)
		 */
		me.oPopup.show((document.body.offsetWidth - me.popWindowWidth)/2, me.popWindowTop, me.popWindowWidth, me.popWindowHeight, document.body);
	
		me.timeOutHandler = function(){
		    me.fadeOut(me.oPopup.document.body, 70);
		};
		
		// 2秒后淡出
		setTimeout(me.timeOutHandler, 500);
	};
	

	me.iBase = {
		// 设置元素透明度,透明度值按IE规则计,即0~100
		SetOpacity : function(ev, v) {
			ev.filters ? ev.style.filter = 'alpha(opacity=' + v + ')' : ev.style.opacity = v / 100;
		}
	};
	
	/*
	 * 参数说明
	 * elem==>需要淡入的元素
	 * speed==>淡入速度,正整数(可选)
	 * opacity==>淡入到指定的透明度,0~100(可选)
	 */
	//淡入效果(含淡入到指定透明度)
	me.fadeIn = function (elem, speed, opacity) {
	    speed = speed || 20;
	    opacity = opacity || 100;
	    //显示元素,并将元素值为0透明度(不可见)
	    elem.style.display = 'block';
	    me.iBase.SetOpacity(elem, 0);
	
	    var val = 0; //初始化透明度变化值为0
	    //循环将透明值以5递增,即淡入效果
	    (function(){
	        me.iBase.SetOpacity(elem, val);
	        val += 5;
	        if (val <= opacity) {
	            setTimeout(arguments.callee, speed)
	        }
	    })();
	};
	
	//淡出效果(含淡出到指定透明度)
	me.fadeOut = function (elem, speed, opacity){
	    speed = speed || 20;
	    opacity = opacity || 0; //初始化透明度变化值为0
	    
	    var val = 100;
		
	    //循环将透明值以5递减,即淡出效果
	    (function(){
	        me.iBase.SetOpacity(elem, val);
	        val -= 5;
	        if (val >= opacity) {
	            setTimeout(arguments.callee, speed);
	        }
	        else 
	            if (val <= 0) {
	                //元素透明度为0后隐藏元素
	                elem.style.display = 'none';
	                me.oPopup.hide();
	            }
	    })();
	};

	me.initPopup(config);
	if(me.isIE){
		me.showPopupIE();
	}else{
		me.showPopup();
	}
	if (config.callback) {
		config.callback();
	}
	return me;
};