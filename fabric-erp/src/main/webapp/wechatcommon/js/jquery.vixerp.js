"use strict";
// 开发
jQuery.vixerp = {
	ocid : '111',
	isDebug:false,
	rootPath : '${vixnt.erp.conf.rootPath}',
	mainDivId : 'mainContent',
	
	sDom: 
		"<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-12 hidden-xs'l>r>"+
		"t"+
		"<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>",
	bStateSave: false,// true
	searching : false,// 显示搜索框
	pageNo : 1,
	pageSize : 12,
	lengthChange : false,// 是否显示每页记录数
	lengthMenu : [[10, 25, 50, -1], [12, 25, 50, 100]],
	
};

/*
 * "sDom": "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-12
 * hidden-xs'l>r>"+ "t"+ "<'dt-toolbar-footer'<'col-sm-6 col-xs-12
 * hidden-xs'i><'col-xs-12 col-sm-6'p>>",
 */

jQuery.vixerp.msg = {
	title : '提示',
	titleConfirm : '确认',
	
	contentConfirmDelete : '确认删除吗？'
	
};


jQuery.vixerp.common = {
	getBootStrapTableLanguage:function(){
		 var obj =  {
				// "sSearch": "搜索:",
	 			"infoEmpty": "无记录",
	 			"zeroRecords": "暂无数据！",
	            "processing": "正在加载中......",
	            "sLengthMenu": "每页显示 _MENU_ 条记录",
	            "emptyTable": "暂无数据！",
	            "info": "当前 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
	            "infoFiltered": "数据表中共为 _MAX_ 条记录",
	            // "search": "查询条件:",
	            "paginate": {
	                "first": "首页",
	                "previous": "上一页",
	                "next": "下一页",
	                "last": "末页"
	            }
		};
		return obj;
	},
	toPage : function(targetPage,options){
		var t = $(targetPage);
		if(t){
			if(!options){
				options = {};
			}
			options.toPage = t;
			 $(".pt-page-current").sasPage("toPage",options);
			return t;
		}
		// TODO
	},
	
	loadContent : function(url,data){
		$("#mainContent").load(url, data);
	},
	loadMain : function( url, data , callback){
		this.load("mainContent",url, data,callback);
	},
	
	
	load : function(divId,  url, data , callback){
		$("#"+divId).load(url, data , callback);
	},
	post : function(url, data, callback, type){
		$.post(url, data, callback, type);
	},
	
	open : function(url,target){
		if(!target){
			target = "_blank";
		}
		if(url){
			window.open(url, target);
		}
	},
	
	ajaxPost : function(async,_url,data,callBack){
		if (_url.indexOf ('?') != - 1){
			_url += '&temp=' + Math.random ();
		}else{
			_url += '?temp=' + Math.random ();
		}
		try{
			$.ajax ({
				url : encodeURI (_url) ,
				type : 'POST' ,
				async : async,// false ,
				cache : false ,
				data : data ,
				dataType : 'json' ,
				success : function (result){
					if ( typeof (callBack) != 'undefined' && callBack.constructor == Function){
						callBack (result);
					}
				} ,
				error : function (result){
					// window.alert ('操作失败');
					$.vixoc.ui.smallBoxAlertCmn('操作失败');
				}
			});
		}catch (e){
			alert (e);
		}
	}

};


jQuery.vixerp.util = {
	getRequest:function() {
		var url = location.search; // 获取url中"?"符后的字串
		var theRequest = new Object();
		if (url.indexOf("?") != -1) {
			var str = url.substr(1);
			strs = str.split("&");
			for ( var i = 0; i < strs.length; i++) {
				var index = strs[i].indexOf("=");
 				theRequest[strs[i].substring(0,index)] = unescape(strs[i].substring(index+1,strs[i].length));
 			}
		}
		return theRequest;
	},
	getUrl : function(){
		var url = window.location.href;
		var index = url.indexOf("?");
		if (index != -1) {
			url = url.substr(0,index);
		}
		return url;
	},
	getUrlParam:function(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r != null)
			return decodeURI(r[2]);
		return null;
	},
	getParamByRequest:function(request,name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = request.match(reg);
		if (r != null)
			return decodeURI(r[2]);
		return null;
	},
	uuid : function(len, radix) {
		var chars = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ'.split('');
		var uuid = [], i;
		radix = radix || chars.length;

		if (len) {
			// Compact form
			for (i = 0; i < len; i++){
				uuid[i] = chars[0 | Math.random() * radix];
			}
		} else {
			// rfc4122, version 4 form
			var r;
			// rfc4122 requires these characters
			uuid[8] = uuid[13] = uuid[18] = uuid[23] = '-';
			uuid[14] = '4';
			// Fill in random data. At i==19 set the high bits of clock sequence
			// as
			// per rfc4122, sec. 4.1.5
			for (i = 0; i < 36; i++) {
				if (!uuid[i]) {
					r = 0 | Math.random() * 16;
					uuid[i] = chars[(i == 19) ? (r & 0x3) | 0x8 : r];
				}
			}
		}
		return uuid.join('').replace(/-/g, '');
	},
	isNull : function(obj) {
		if(obj == null || obj == "" || obj == "undefined" || obj == "null"){
			return true;
		}
		return false;
	},
	isNotNull : function(obj) {
		if(obj == null || obj == "" || obj == "undefined" || obj == "null"){
			return false;
		}
		return true;
	},
	formatDate : function(date, format) {
		var paddNum = function(num) {
			num += "";
			return num.replace(/^(\d)$/, "0$1");
		};
		var getWeekDay = function(d) {
			if (d == 1) {
				return "星期一";
			} else if (d == 2) {
				return "星期二";
			} else if (d == 3) {
				return "星期三";
			} else if (d == 4) {
				return "星期四";
			} else if (d == 5) {
				return "星期五";
			} else if (d == 6) {
				return "星期六";
			} else if (d == 0) {
				return "星期日";
			}
		};
		// 指定格式字符
		var cfg = {
			yyyy : date.getFullYear(), // 年 : 4位
			yy : date.getFullYear().toString().substring(2),// 年 : 2位
			M : date.getMonth() + 1, // 月 : 如果1位的时候不补0
			MM : paddNum(date.getMonth() + 1), // 月 : 如果1位的时候补0
			d : date.getDate(), // 日 : 如果1位的时候不补0
			dd : paddNum(date.getDate()),// 日 , 如果1位的时候补0
			h : date.getHours(), // 时
			hh : paddNum(date.getHours()), // 时,如果1位的时候补0
			m : date.getMinutes(), // 分
			mm : paddNum(date.getMinutes()), // 分,如果1位的时候补0
			s : date.getSeconds(), // 秒
			ss : paddNum(date.getSeconds()), // 秒 ,如果1位的时候补0
			w : getWeekDay(date.getDay())
		// 周几
		};
		format || (format = "yyyy-MM-dd hh:mm:ss");
		return format.replace(/([a-z])(\1)*/ig, function(m) {
			return cfg[m];
		});
	},
	formatDateLong : function(dateLong, format) {
		var dateTmp = (new Date(parseInt(dateLong)));
		return this.formatDate(dateTmp,format);
		
	},
	formatDateSimple : function(dateLong) {
		var dateTmp = (new Date(parseInt(dateLong)));
		return this.formatDate(dateTmp,"yyyy年MM月dd日");
		
	},
	formatDateAll : function(dateLong) {
		var dateTmp = (new Date(parseInt(dateLong)));
		return this.formatDate(dateTmp,"yyyy年MM月dd日 hh:mm:ss");
		
	},
	
	/**
	 * 参数:interval,字符串表达式，表示要添加的时间间隔 年 : y ; m : 月; d : 天 . *
	 * 参数:number,数值表达式，表示要添加的时间间隔的个数. * 参数:date,时间对象. * 返回:新的时间对象.
	 */
	addDate : function(interval,number,date){
		switch(interval)
		　　{
		　　　　case "y" : 
		　　　　　　date.setFullYear(date.getFullYear()+number);
		　　　　　　return date;
		　　　　　　break;
		　　　　case "q" : 
		　　　　　　date.setMonth(date.getMonth()+number*3);
		　　　　　　return date;
		　　　　　　break;
		　　　　case "m" : 
		　　　　　　date.setMonth(date.getMonth()+number);
		　　　　　　return date;
		　　　　　　break;
		　　　　case "w" : 
		　　　　　　date.setDate(date.getDate()+number*7);
		　　　　　　return date;
		　　　　　　break;
		　　　　case "d" : 
		　　　　　　date.setDate(date.getDate()+number);
		　　　　　　return date;
		　　　　　　break;
		　　　　case "h" : 
		　　　　　　date.setHours(date.getHours()+number);
		　　　　　　return date;
		　　　　　　break;
		　　　　case "m" : 
		　　　　　　date.setMinutes(date.getMinutes()+number);
		　　　　　　return date;
		　　　　　　break;
		　　　　case "s" :
		　　　　　　date.setSeconds(date.getSeconds()+number);
		　　　　　　return date;
		　　　　　　break;
		　　　　default : 
		　　　　　　date.setDate(d.getDate()+number);
		　　　　　　return date;
		　　　　　　break;
		　　}
	}
	
	/*
	 * setCookie : function(name,data,expires){ $.cookie(name, data, { expires :
	 * expires, path: '/' }); }, getCookie : function(name){ return
	 * $.cookie(name); }
	 */
};





jQuery.vixerp.ui = {
	smallBoxBase : function(title, content, color,iconSmall,timeout){
		$.smallBox({
			title : title,
			content : content,
			color : color,
			iconSmall : iconSmall,
			timeout : timeout
		});
		
		return false;
	},
	
	smallBoxAlert : function(title,content){
		this.smallBoxBase(title,content,"#296191","fa fa-thumbs-up bounce animated",4000);
	},
	smallBoxYes : function(title,content){
		this.smallBoxBase(title,"<i class='fa fa-clock-o'></i><i>" + content +"</i>","#659265","fa fa-check fa-2x fadeInRight animated",4000);
	},
	smallBoxNo : function(title,content){
		this.smallBoxBase(title,content,"#C46A69","fa fa-times fa-2x fadeInRight animated",4000);
	},
	
	// 常用消息提示
	smallBoxCmn : function(content){
		this.smallBoxBase($.vixoc.msg.title,content,"#5384AF","fa fa-bell",4000);
	},
	smallBoxCmnError : function(content){
		this.smallBoxBase($.vixoc.msg.title,content,"#C46A69","fa fa-bell",4000);
	},
	
	msgBoxConfirmBase:function(msgTitle,msgContent,   yesTitle,yesContent,   noTitle,noContent) {
		$.SmartMessageBox({
			title : msgTitle,
			content : "<i>" + msgContent + "</i>",
			buttons : '[否][是]'
		}, function(ButtonPressed) {
			if (ButtonPressed === "是") {
				smallBoxYes(yesTitle,yesContent);// "提示"
			}else if (ButtonPressed === "否") {
				smallBoxNo(noTitle,noContent);
			}
		
		});
		return false;
	},
	msgBoxConfirmTip:function(msgTitle,msgContent,   yesTitle,yesContent,   noTitle,noContent) {
		if(msgTitle==null || msgTitle=='' ){
			msgTitle = "提示";
		}
		if(yesTitle==null || yesTitle=='' ){
			yesTitle = "提示";
		}
		if(noTitle==null || noTitle=='' ){
			noTitle = "提示";
		}
		this.msgBoxConfirmBase(msgTitle,msgContent,   yesTitle,yesContent,   noTitle,noContent);
	},
	
	
	msgBoxConfirm:function(title, msgContent,  yesCallBack,   noCallBack) {
		$.SmartMessageBox({
			title : title,
			content :"<i>" + msgContent + "</i>",
			buttons : '[否][是]'
		}, function(ButtonPressed) {
			if (ButtonPressed === "是") {
				if ( typeof (yesCallBack) != 'undefined' && yesCallBack.constructor == Function){
					yesCallBack();
				}
			}else if (ButtonPressed === "否") {
				if ( typeof (noCallBack) != 'undefined' && noCallBack.constructor == Function){
					noCallBack();
				}
			}
		
		});
		return false;
	},
	
	// 删除确认
	msgBoxConfirmDelete:function( yesCallBack,   noCallBack) {
		this.msgBoxConfirm(
				$.vixerp.msg.title,
				$.vixerp.msg.contentConfirmDelete,
				yesCallBack,   noCallBack);
	}
	
	
};

