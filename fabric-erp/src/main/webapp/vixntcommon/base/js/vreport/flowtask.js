document.oncontextmenu = function() {
	return false;
}
function Rreport(config) {
	this.config = config || {};
	this.config.node = this.config.node || [];
	this.config.link = this.config.link || [];
	this.config.width = this.config.width || 1000;
	this.config.height = this.config.height || 350;
	this.config.viewType = this.config.viewType || 'w';
	if (typeof (this.config.id) == 'undefined') {
		this.config.id = "reportId" + (Rreport.reportId++);
		Rreport.reportArr[this.config.id] = this;
	}
	;
	this.nodeArr = {};
	this.linkArr = {};
	this.tempLink = null;
	this.selectItem = null;
	if (config.isEdit) {
		$("#" + this.config.contentDiv).click(function() {
			if (report.selectItem != null) {
				report.selectItem.unSelect();
			}
		});
		$("#" + this.config.contentDiv).keyup(function(event) {
			if (event.keyCode == 46) {
				if (report.selectItem != null) {
					report.selectItem.remove();
					report.selectItem = null;
				}
			}
		});
		this.configEditModel();
	}
	if (typeof (this.config.renderTo) == 'string') {
		this.paper = Raphael(config.renderTo, config.width, config.height);
		if (typeof (this.config.node) == 'object') {
			this.renderNode();
		}
	}
};
Rreport.conf = {
iconWidth : 48,
titleFontSize : 14,
bodyFontSize : 14,
iconArr : {
'start.png' : '开始',
'timer.png' : '时间',
'human.png' : '人员',
'email.png' : '邮件',
'money.png' : '优惠券'
}
}
Rreport.nodeId = 0;
Rreport.linkId = 0;
Rreport.reportId = 0;
Rreport.reportArr = {};
Rreport.template = {};
Rreport.prototype.addNode = function(config) {
	config.x = config.x || 10;
	config.y = config.y || 10;
	config.text = config.text || "标题";
	config.id = "node" + (++Rreport.nodeId);
	this.nodeArr[config.id] = new Rnode(config, this);
};
Rreport.prototype.addNodeTemplate = function(obj) {
	if ((typeof obj) == "object") {
		if ((typeof Rreport.template) == "object") {
			for ( var i in obj) {
				Rreport.template[i] = obj[i];
			}
		} else {
			Rreport.template = obj;
		}
	}
}
Rreport.prototype.configEditModel = function() {
	var this_ = this;
	$("#" + this.config.contentDiv)
			.append('<div id="vreporttoolbar" class="ui-widget-header ui-corner-all">' + '<button id="btn_add_start">开始节点</button>' + '<button id="btn_add_time">时间节点</button>' + '<button id="btn_add_process">客户筛选</button>' + '<button id="btn_add_email">邮件</button>' + '<button id="btn_add_message">短信</button>' + '<button id="btn_add_coupon">优惠券</button>' + '<button id="btn_add_summary">营销汇总</button>' + '<button id="btn_save">保存</button>' + '<button id="btn_modify">调整视图</button>' + '<button id="btn_delete_node">删除节点</button>' + '</div>' + '<div id="node-win" title="节点配置" style="display:none;">' + '<span>主键：</span><label id="nodeId"/><br>' + '<span>标题：</span><input type="text" id="titleId"/><br>' + '<span>类型：</span><select id="nodeType"><option value="start">开始节点</option><option value="process">处理节点</option><option value="delay">延时节点</option></select><br>' + '<span>图标：</span><select id="nodeIcon"></select><br>' + '<span>值：</span><input type="text" id="nodeValue"/><br>' + '<span>参数：</span><input type="text" id="nodeParam"/><br>' + '<span>扩展:</span><textarea id="nodeExt"></textarea><br>' + '</div>' + '<div id="link-win" title="出口配置" style="display:none;">' + '<span>并行：</span><select id="link_ismul"><option value="1">是</option><option value="0">否</option></select><br>' + '<span>条件：</span><input type="text" id="link_condition"/><br>' + '</div>' + '<div id="report-win" title="报表配置" style="display:none;">' + '<span>展现：</span><select id="report_view_type"><option value="w">横向</option><option value="h">纵向</option></select><br>' + '<span>宽度：</span><input type="text" id="report_view_width"/><br>' + '<span>高度：</span><input type="text" id="report_view_height"/>' + '</div>' + '<div id="reportEditDiv" style="overflow:auto;"></div>');
	for ( var i in Rreport.conf.iconArr) {
		$("#nodeIcon").append("<option value='" + i + "'>" + Rreport.conf.iconArr[i] + "</option>")
	}
	this.config.renderTo = 'reportEditDiv';
	$("#node-win").dialog({
	modal : true,
	autoOpen : false,
	resizable : false,
	buttons : {
	"确认" : function() {
		var nodeId = $("#node-win").attr("nodeId");
		var node = report.nodeArr[nodeId];
		node.setConfig({
		text : $('#titleId').val(),
		icon : $("#nodeIcon").val(),
		type : $("#nodeType").val(),
		value : $("#nodeValue").val(),
		param : $("#nodeParam").val(),
		ext : $("#nodeExt").val(),
		})
		$(this).dialog("close");
	},
	'关闭' : function() {
		$(this).dialog("close");
	}
	}
	});
	$("#link-win").dialog({
	modal : true,
	autoOpen : false,
	resizable : false,
	buttons : {
	"确认" : function() {
		var link = this_.linkArr[$("#link-win").attr("linkId")];
		link.config.isMul = $("#link_ismul").val();
		var targetId = $("#link-win").attr("targetId");
		for (var i = 0; i < link.config.to.length; i++) {
			if (link.config.to[i].targetId == targetId) {
				link.config.to[i].condition = $("#link_condition").val();
			}
		}
		$(this).dialog("close");
	},
	'关闭' : function() {
		$(this).dialog("close");
	}
	}
	});
	$("#report-win").dialog({
	modal : true,
	autoOpen : false,
	resizable : false,
	buttons : {
	"确认" : function() {
		$(this).dialog("close");
		this_.config.viewType = $("#report_view_type").val();
		for ( var i in this_.linkArr) {
			var link = this_.linkArr[i];
			link.reDraw();
		}
		this_.config.width = $("#report_view_width").val();
		this_.config.height = $("#report_view_height").val();
		this_.paper.setSize(this_.config.width, this_.config.height);
	},
	'关闭' : function() {
		$(this).dialog("close");
	}
	}
	});
	$("#btn_save").button({
	text : true,
	icons : {
		primary : "ui-icon-disk"
	}
	}).click(function() {
		if (typeof (this_.config.saveFun) == 'function') {
			var data = this_.getModelData();
			this_.config.saveFun(JSON.stringify(data));
		}
	});
	$("#btn_modify").button({
	text : true,
	icons : {
		primary : "ui-icon-gear"
	}
	}).click(function() {
		$("#report-win").dialog("open");
		$("#report_view_type").val(this_.config.viewType);
		$("#report_view_width").val(this_.config.width);
		$("#report_view_height").val(this_.config.height);
	})
	$("#btn_delete_node").button({
	text : true,
	icons : {
		primary : "ui-icon-closethick"
	}
	}).click(function() {
		if (this_.selectItem != null) {
			this_.selectItem.remove();
			this_.selectItem = null;
		}
	});
	$("#btn_add_start").button({
	text : true,
	icons : {
		primary : "ui-icon-circle-plus"
	}
	}).click(function() {
		this_.addNode({
		x : 10,
		icon : 'start.png',
		type : 'start',
		y : 10
		});
	});
	$("#btn_add_time").button({
	text : true,
	icons : {
		primary : "ui-icon-circle-plus"
	}
	}).click(function() {
		this_.addNode({
		x : 10,
		y : 10,
		type : 'delay',
		icon : 'timer.png'
		});
	});
	$("#btn_add_process").button({
	text : true,
	icons : {
		primary : "ui-icon-circle-plus"
	}
	}).click(function() {
		this_.addNode({
		x : 10,
		y : 10,
		type : 'process',
		icon : 'human.png'
		});
	});
	$("#btn_add_email").button({
	text : true,
	icons : {
		primary : "ui-icon-circle-plus"
	}
	}).click(function() {
		this_.addNode({
		x : 10,
		y : 10,
		type : 'process',
		icon : 'email.png'
		});
	});
	$("#btn_add_coupon").button({
	text : true,
	icons : {
		primary : "ui-icon-circle-plus"
	}
	}).click(function() {
		this_.addNode({
		x : 10,
		y : 10,
		type : 'process',
		icon : 'money.png'
		});
	});
	$("#btn_add_message").button({
	text : true,
	icons : {
		primary : "ui-icon-circle-plus"
	}
	}).click(function() {
		this_.addNode({
		x : 10,
		y : 10,
		type : 'process',
		icon : 'waiting.png'
		});
	});
	$("#btn_add_summary").button({
	text : true,
	icons : {
		primary : "ui-icon-circle-plus"
	}
	}).click(function() {
		this_.addNode({
		x : 10,
		y : 10,
		type : 'summary',
		icon : 'summary.png'
		});
	});
}

Rreport.prototype.addLink = function(config) {
	config.id = "link" + (++Rreport.linkId);
	this.linkArr[config.id] = new Rlink(config, this);
}

Rreport.prototype.setMovePath = function(nodeId, x, y) {
	for ( var nId in this.nodeArr) {
		var node = this.nodeArr[nId];
		if ((x > node.config.x) && (x < (node.config.x + Rreport.conf.iconWidth))) {
			if ((y > node.config.y + 40) && (y < (node.config.y + Rreport.conf.iconWidth + 70))) {
				if (nId != nodeId) {
					if (this.config.viewType == 'w') {
						x = node.config.x;
						y = node.config.y + Rreport.conf.iconWidth / 2;
						this.tempLink.targetId = nId;
						break;
					} else if (this.config.viewType == 'h') {
						x = node.config.x + (Rreport.conf.iconWidth / 2);
						y = node.config.y;
						this.tempLink.targetId = nId;
						break;
					} else {
						this.tempLink.targetId = null;
					}
				}
			}
		}
	}

	var length = 50;
	for ( var linkId in this.linkArr) {
		if (this.linkArr[linkId].config.from == nodeId) {
			length = this.linkArr[linkId].config.length;
			break;
		}
	}
	var startNode = this.nodeArr[this.tempLink.from];
	var startP, nextP;
	if (this.config.viewType == 'w') {
		var s_x = startNode.config.x + Rreport.conf.iconWidth;// 其实位置
		var s_y = startNode.config.y + Rreport.conf.iconWidth / 2;
		startP = "M " + s_x + " " + s_y;
		nextP = "L " + (s_x + length) + " " + s_y + " L " + (s_x + length) + " " + y;
	}
	if (this.config.viewType == 'h') {
		var s_x = startNode.config.x + Rreport.conf.iconWidth / 2;// 其实位置
		var s_y = startNode.config.y + Rreport.conf.iconWidth;
		startP = "M " + s_x + " " + s_y;
		nextP = "L " + s_x + " " + (s_y + length) + " L " + x + " " + (s_y + length);
	}
	this.tempLink.l.attr({
		path : startP + nextP + " L " + x + " " + y
	});

}
Rreport.prototype.getNodeById = function(id) {
	return this.nodeArr[id];
}
Rreport.prototype.renderNode = function() {
	with (this.config) {
		for ( var i in node) {
			var nodeConfig = node[i];
			if (typeof (nodeConfig.id) == 'undefined') {
				nodeConfig.id = "node" + (Rreport.nodeId++);
			} else {
				Rreport.nodeId++
			}
			this.nodeArr[nodeConfig.id] = new Rnode(nodeConfig, this);
		}
		for ( var i in link) {
			var linkConfig = link[i];
			if (typeof (linkConfig.id) == 'undefined') {
				linkConfig.id = "link" + (Rreport.linkId++);
			} else {
				Rreport.linkId++
			}
			this.linkArr[linkConfig.id] = new Rlink(linkConfig, this);
		}
	}

}

Rreport.prototype.loadData = function(data) {
	this.config.node = data.node;
	this.config.link = data.link;
	this.config.viewType = data.viewType;
	this.config.height = data.height;
	this.config.width = data.width;
	this.paper.setSize(data.width, data.height);
	this.renderNode();
}

Rreport.prototype.loadDataFromUrl = function(url) {
	var this_ = this;
	$.ajax({
	url : url,
	dataType : 'json',
	success : function(data) {
		this_.config.node = data.node;
		this_.config.link = data.link;
		this_.config.viewType = data.viewType;
		this_.config.height = data.height;
		this_.config.width = data.width;
		this_.paper.setSize(data.width, data.height);
		this_.renderNode();
	}
	});
}

Rreport.prototype.monitorNode = function(obj) {
	for ( var i in obj) {
		this.getNodeById(obj[i].fromNodeId).text.attr({
			'fill' : "red"
		});
		this.getNodeById(obj[i].nodeId).text.attr({
			'fill' : "red"
		});
		var link = this.linkArr[i];
		var pathArr = link.getPath();
		for ( var j in pathArr) {
			link.l[j].attr({
				'stroke' : "red"
			});
		}
	}
}

Rreport.prototype.getModelData = function() {
	var dataJson = {
	viewType : this.config.viewType,
	width : this.config.width,
	height : this.config.height,
	link : {},
	node : {}
	};
	var linkArr = this.linkArr;
	for ( var linkId in linkArr) {
		var t = {};
		t.id = linkId;
		t.from = linkArr[linkId].config.from;
		t.to = linkArr[linkId].config.to;
		t.isMul = linkArr[linkId].config.isMul;
		t.length = linkArr[linkId].config.length;
		dataJson.link[linkId] = t;
	}
	var node = [];
	var nodeArr = this.nodeArr;
	for ( var nodeId in nodeArr) {
		var t = nodeArr[nodeId].config;
		t.id = nodeId;
		dataJson.node[nodeId] = t;
	}
	return dataJson;
}

Rreport.prototype.reDrawLink = function() {
	for ( var linkId in this.linkArr) {
		this.linkArr[linkId].reDraw();
	}
}

function getPxLength(text) {
	var l = 0;
	for (var t = 0; t < text.length; t++) {
		if (/^[\u4e00-\u9fa5]+$/.test(text.charAt(t))) {
			l = l + 18;
		} else {
			l = l + 4;
		}
	}
	return l;
}

function Rlink(config, report) {
	this.report = report;
	this.config = config;
	this.config.isMul = this.config.isMul || '0';
	var pathArr = this.getPath();
	this.l = {};
	for ( var i in pathArr) {
		var t = report.paper.path(pathArr[i])
		t.attr({
		"cursor" : 'pointer',
		"stroke-width" : 1.2,
		"arrow-end" : 'classic-wide-long'
		});
		if (report.config.isEdit) {
			t.drag(this.onMove, this.onStart, this.onEnd, this, this, this);
			t.dblclick(this.onDbClick, {
			link : this,
			targetId : i
			});
			t.click(this.onClick, this);
		}
		this.l[i] = t;
	}

}
Rlink.prototype.onDbClick = function(e, i) {
	var this_ = this.link;
	var targetId = this.targetId;
	$("#link-win").dialog("open");
	$("#link-win").attr("linkId", this_.config.id);
	$("#link-win").attr("targetId", targetId);
	$("#link_ismul").val(this_.config.isMul);
	for (var i = 0; i < this_.config.to.length; i++) {
		if (this_.config.to[i].targetId == targetId) {
			$("#link_condition").val(this_.config.to[i].condition);
		}
	}
}
Rlink.prototype.addTargetNode = function(tId) {
	var hasExit = false;
	for (var i = 0; i < this.config.to.length; i++) {
		if (this.config.to[i].targetId == tId) {
			hasExit = true;
			break;
		}
	}
	if (!hasExit) {
		this.config.to.push({
		targetId : tId,
		condition : ''
		});
		this.reDraw();
	}
}
Rlink.prototype.removeTargetNode = function(i) {
	var tId = this.config.to.splice(i, 1);
	this.l[tId[0].targetId].remove();
	delete this.l[tId[0].targetId];
	if (this.config.to.length == 0) {
		this.remove();
	} else {
		this.reDraw();
	}
}
Rlink.prototype.remove = function() {
	var pathArr = this.getPath();
	for ( var i in pathArr) {
		if (typeof (this.l[i]) != 'undefined') {
			this.l[i].remove();
		}
	}
	delete this.report.linkArr[this.config.id];
}
Rlink.prototype.reDraw = function() {
	var pathArr = this.getPath();
	for ( var i in pathArr) {
		if (typeof (this.l[i]) != 'undefined') {
			this.l[i].attr({
				path : pathArr[i]
			});
		} else {
			var t = this.report.paper.path(pathArr[i]);
			this.l[i] = t;
			t.attr({
			"cursor" : 'pointer',
			"stroke-width" : 1.2,
			"arrow-end" : 'classic-wide-long'
			});
			if (this.report.config.isEdit) {
				t.drag(this.onMove, this.onStart, this.onEnd, this, this, this);
				t.dblclick(this.onDbClick, {
				link : this,
				targetId : i
				});
				t.click(this.onClick, this);
			}
		}
	}
}
Rlink.prototype.onMove = function(dx, dy, x, y, event) {
	var report = this.report;
	if (report.config.viewType == 'w') {
		this.config.length = this.l.s.length + dx;
	} else {
		this.config.length = this.l.s.length + dy;
	}
	this.reDraw();
}
Rlink.prototype.onStart = function(x, y, event) {
	this.l.s = {
		length : this.config.length
	};
}
Rlink.prototype.onEnd = function(event) {
}
Rlink.prototype.getPath = function() {
	var pathArr = {};
	var lstr = "";
	var config = this.config;
	var report = this.report;
	this.config.length = this.config.length || 50;
	var length = config.length;
	var startNode = report.nodeArr[config.from];
	if (report.config.viewType == 'w') {
		var s_x = startNode.config.x + Rreport.conf.iconWidth;// 其实位置
		var s_y = startNode.config.y + Rreport.conf.iconWidth / 2;
		var startP = "M " + s_x + " " + s_y;
		var nextP = "L " + (s_x + length) + " " + s_y;
		for (var i = 0; i < config.to.length; i++) {
			var targetNode = report.nodeArr[config.to[i].targetId];
			var mP = "M " + (s_x + length) + " " + s_y;
			var threeP = "L " + (s_x + length) + " " + (targetNode.config.y + Rreport.conf.iconWidth / 2);
			var fourP = "L " + targetNode.config.x + " " + (targetNode.config.y + Rreport.conf.iconWidth / 2);
			if (targetNode.config.x < s_x) {
				fourP = "L " + (targetNode.config.x + Rreport.conf.iconWidth) + " " + (targetNode.config.y + Rreport.conf.iconWidth / 2);
			}
			lstr = startP + nextP;
			lstr = lstr + mP + threeP + fourP;
			pathArr[config.to[i].targetId] = lstr;
		}
	}
	if (report.config.viewType == 'h') {
		var s_x = startNode.config.x + Rreport.conf.iconWidth / 2;// 其实位置
		var s_y = startNode.config.y + Rreport.conf.iconWidth;
		var startP = "M " + s_x + " " + s_y;
		var nextP = "L " + s_x + " " + (s_y + length);
		for (var i = 0; i < config.to.length; i++) {
			var targetNode = report.nodeArr[config.to[i].targetId];
			var mP = "M " + s_x + " " + (s_y + length);
			var threeP = "L " + (targetNode.config.x + Rreport.conf.iconWidth / 2) + " " + (s_y + length);
			var fourP = "L " + (targetNode.config.x + Rreport.conf.iconWidth / 2) + " " + targetNode.config.y;
			lstr = startP + nextP;
			lstr = lstr + mP + threeP + fourP;
			pathArr[config.to[i].targetId] = lstr;
		}
	}
	return pathArr;
}
Rlink.prototype.onClick = function(e) {
	if (this.report.selectItem != null) {
		this.report.selectItem.unSelect();
	}
	this.select();
	if (e && e.stopPropagation)
		e.stopPropagation();
	else
		window.event.cancelBubble = true;
	return false;
}

Rlink.prototype.select = function() {
	var pathArr = this.getPath();
	for ( var i in pathArr) {
		if (typeof (this.l[i]) != 'undefined') {
			this.l[i].attr({
				'stroke' : "#ff0C01"
			});
		}
	}
	this.report.selectItem = this;
}
Rlink.prototype.unSelect = function() {
	var pathArr = this.getPath();
	for ( var i in pathArr) {
		if (typeof (this.l[i]) != 'undefined') {
			this.l[i].attr({
				'stroke' : "#000000"
			});
		}
	}
	this.report.selectItem = null;
};

function Rnode(config, report) {
	this.report = report;
	config = config || {};
	config.text = config.text || '';
	config.type = config.type || 'process';
	config.icon = config.icon || 'human.png';
	this.config = config;
	this.createNode();
	return this;
}

Rnode.prototype.setText = function(text) {
	this.text.attr({
		"text" : text
	});
	this.config.text = text;
}

Rnode.prototype.setIcon = function(icon) {
	this.c1.attr({
		"src" : "css/img/icon/" + icon
	});
	this.config.icon = icon;
}

Rnode.prototype.setType = function(type) {
	this.config.type = type;
}

Rnode.prototype.createNode = function() {
	var report = this.report;
	with (this.config) {
		this.c1 = report.paper.image("css/img/icon/" + icon, x, y, Rreport.conf.iconWidth, Rreport.conf.iconWidth).attr({
			"cursor" : 'pointer'
		});
		;
		this.text = report.paper.text(x + Rreport.conf.iconWidth / 2, y + Rreport.conf.iconWidth + 10, text).attr({
		"font-size" : Rreport.conf.titleFontSize + 'px',
		"cursor" : 'pointer',
		"fill" : '#000000'
		});
		if (report.config.isEdit) {
			this.text.drag(this.onMove, this.onStart, this.onEnd, this, this, this);
			this.text.click(this.onClick, this);
			this.text.dblclick(this.onDbClick, this);
			this.c1.drag(this.onMove, this.onStart, this.onEnd, this, this, this);
			this.c1.click(this.onClick, this);
			this.c1.dblclick(this.onDbClick, this);
		}
	}
}
Rnode.prototype.setConfig = function(config) {
	if (typeof (config.text) != 'undefined') {
		this.setText(config.text);
	}
	if (typeof (config.icon) != 'undefined') {
		this.setIcon(config.icon);
	}
	if (typeof (config.type) != 'undefined') {
		this.setType(config.type);
	}
	this.config.value = config.value;
	this.config.param = config.param;
	this.config.ext = config.ext;
};
Rnode.prototype.onClick = function(e) {
	if (this.report.selectItem != null) {
		this.report.selectItem.unSelect();
	}
	this.select();
	if (e && e.stopPropagation)
		e.stopPropagation();
	else
		window.event.cancelBubble = true;
	return false;
}
Rnode.prototype.onDbClick = function(e) {
	var key = this.config.type + "-" + this.config.icon;
	if (typeof (Rreport.template[key]) == 'function') {
		Rreport.template[key](this);
	} else {
		$("#node-win").attr("nodeId", this.config.id);
		$("#node-win").dialog("open");
		$('#nodeId').html(this.config.id);
		$('#titleId').val(this.config.text);
		$("#nodeIcon").val(this.config.icon);
		$("#nodeType").val(this.config.type);
		$("#nodeValue").val(this.config.value);
		$("#nodeParam").val(this.config.param);
		$("#nodeExt").val(this.config.ext);
	}
}
Rnode.prototype.select = function() {
	this.text.attr({
		'fill' : "#ff0C01"
	});
	this.report.selectItem = this;
}
Rnode.prototype.unSelect = function() {
	this.text.attr({
		'fill' : "#000000"
	});
	this.report.selectItem = null;
};
Rnode.prototype.remove = function() {
	this.c1.remove();
	this.text.remove();
	var tempId = this.config.id;
	var linkArr = this.report.linkArr;
	for ( var i in linkArr) {
		var link = linkArr[i];
		if (this.config.id == link.config.from) {
			link.remove();
		}
		for (var j = 0; j < link.config.to.length; j++) {
			if (this.config.id == link.config.to[j].targetId) {
				link.removeTargetNode(j);
			}
		}
	}
	delete this.report.nodeArr[tempId];
}
Rnode.prototype.onMove = function(dx, dy, x, y, event) {
	// 位置调整 strart
	var contentDiv = document.getElementById(this.report.config.renderTo);
	var o = getElemPos(contentDiv);
	x = x - o.x;
	y = y - o.y;
	// 位置调整 end
	if (this.eb == 2) {
		var div = $("#" + this.report.config.contentDiv);
		this.report.setMovePath(this.config.id, x, y - div.get(0).offsetTop);
	} else {
		this.config.x = dx + this.c1.s.x;
		this.config.y = dy + this.c1.s.y;
		this.c1.attr({
		x : dx + this.c1.s.x,
		y : dy + this.c1.s.y
		});
		this.text.attr({
		x : dx + this.text.s.x,
		y : dy + this.text.s.y
		});
		var linkArr = this.report.linkArr;
		for ( var i in linkArr) {
			var link = linkArr[i];
			if (this.config.id == link.config.from) {
				link.reDraw();
			}
			for (var j = 0; j < link.config.to.length; j++) {
				if (this.config.id == link.config.to[j].targetId) {
					link.reDraw();
				}
			}
		}
	}
}

Rnode.prototype.onStart = function(x, y, event) {

	this.c1.s = {
	x : this.c1.attr("x"),
	y : this.c1.attr("y")
	};
	this.text.s = {
	x : this.text.attr("x"),
	y : this.text.attr("y")
	};
	this.eb = event.button;
	if (event.button == 2) {
		var l = this.report.paper.path("M " + x + " " + y + " L " + x + " " + y);
		this.report.tempLink = {
		from : this.config.id,
		l : l
		}
		l.attr({
		"stroke-dasharray" : '-',
		"cursor" : 'pointer',
		"stroke-width" : 1.2,
		"arrow-end" : 'classic-wide-long'
		});
	}
}
Rnode.prototype.onEnd = function(event) {
	if (event.button == 2) {
		if (this.report.tempLink.targetId != null) {
			var targetLinkId = null;
			for ( var linkId in this.report.linkArr) {
				if (this.report.linkArr[linkId].config.from == this.report.tempLink.from) {
					targetLinkId = linkId;
					break;
				}
			}
			if (targetLinkId != null) {
				this.report.linkArr[targetLinkId].addTargetNode(this.report.tempLink.targetId);
			} else {
				this.report.addLink({
				from : this.report.tempLink.from,
				isMul : '0',
				to : [ {
				targetId : this.report.tempLink.targetId,
				condition : ''
				} ]
				});
			}
		}
		this.report.tempLink.l.remove();
		this.report.tempLink = {};
	}
}

function getElemPos(obj) {
	var pos = {
	"top" : 0,
	"left" : 0
	};
	if (obj.offsetParent) {
		while (obj.offsetParent) {
			pos.top += obj.offsetTop;
			pos.left += obj.offsetLeft;
			obj = obj.offsetParent;
		}
	} else if (obj.x) {
		pos.left += obj.x;
	} else if (obj.x) {
		pos.top += obj.y;
	}
	return {
	x : pos.left,
	y : pos.top
	};
}
