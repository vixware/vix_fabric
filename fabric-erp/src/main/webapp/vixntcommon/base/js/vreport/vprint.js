document.oncontextmenu=function(){return false;}
function VPrint(config){
	var this_ =this;
	this.config = config||{};
	this.config.expressJson = this.config.expressJson||{};
	this.config.node = this.config.node||[];
	this.config.link = this.config.link||[];
	this.config.width = this.config.width||1000;
	this.config.height = this.config.height||1000;
	this.config.viewType = this.config.viewType||'w';
	if(typeof(this.config.id)=='undefined'){
		this.config.id = "reportId"+(VPrint.reportId++);
		VPrint.reportArr[this.config.id] = this;
	};
	this.nodeArr = {};
	this.linkArr = {};
	this.tempLink = null;
	this.selectItem =null;
	if(config.isEdit){
		$(document).click(function(){
			if(this_.selectItem!=null){
				this_.selectItem.unSelect();
			}
		});
		$(document).keyup(function(event){
			if(event.keyCode==46){
				if(this_.selectItem!=null){
					this_.selectItem.remove();
					this_.selectItem = null;
				}
			}
		});
		this.configEditModel();
	}
	if(typeof(this.config.renderTo)=='string'){
		this.paper = Raphael(config.renderTo, config.width,
				config.height);
		if(typeof(this.config.node)=='object'){
			this.renderNode();
		}
	}
};
VPrint.conf={
	titleHeight:40,
	titleFontSize:16,
	bodyFontSize:14
}
VPrint.nodeId = 0;
VPrint.linkId = 0;
VPrint.reportId =0;
VPrint.reportArr={};
VPrint.prototype.addNode =function(config){
	config.x = config.x||10;
	config.y = config.y||10;
	config.title=config.title||"标题";
	config.titleRemark=config.titleRemark||"副标题";
	config.content = "内容";
	config.id = "node"+(++VPrint.nodeId);
	this.nodeArr[config.id]=new Rnode(config,this);
};
VPrint.prototype.configEditModel=function(){
	var this_ = this;
	$(document.body).append(
		'<div id="vreporttoolbar" class="ui-widget-header ui-corner-all">'+
			  '<button id="btn_save">保存</button>'+
			  '<button id="btn_modify">背景图配置</button>'+
			  '<button id="btn_add_node">新增节点</button>'+
			  '<button id="btn_delete_node">删除节点</button>'+
		 '</div>'+
		 '<div id="node-win" title="节点配置" style="display:none;">'+
		    '<span>主键：</span><label id="nodeId"/><br>'+
			'<span>标题：</span><input type="text" id="titleId"/><br>'+
			'<span>副标题：</span><input type="text" id="titleRemarkId"/><br>'+
			'<span>url：</span><input type="text" id="contentUrl" value=""/><br>'+
			'<span>内容:</span><textarea id="contentId"></textarea><br>'+
		 '</div>'+
		 '<div id="report-win" title="背景图配置" style="display:none;">'+
		    '<span>背景：</span><select id="report_view_type"></select><br>'+
			'<span>宽度：</span><input type="text" id="report_view_width"/><br>'+
			'<span>高度：</span><input type="text" id="report_view_height"/>'+
		 '</div>'+
		 '<div id="reportEditDiv"></div>'
	 );
	this.config.renderTo = 'reportEditDiv';
	 $("#node-win").dialog({
		modal: true,
		autoOpen: false ,
		resizable:false,
		buttons: {
			"确认": function() {
				var nodeId =  $("#node-win").attr("nodeId");
				var node = this_.nodeArr[nodeId];
				node.setConfig({
					title:$('#titleId').val(),
					titleRemark:$('#titleRemarkId').val(),
					url:$("#contentUrl").val(),
					content:$("#contentId").val()
				})
				$(this).dialog( "close" );
			},
			'关闭': function() {
				$(this).dialog( "close" );
			}
		}
	});
	 $("#report-win").dialog({
			modal: true,
			autoOpen: false ,
			resizable:false,
			buttons: {
				"确认": function() {
					$(this).dialog( "close" );
					this_.config.viewType = $("#report_view_type").val();
					for(var i in this_.linkArr){
						var link = this_.linkArr[i];
						link.reDraw();
					}
					this_.config.width = $("#report_view_width").val();
					this_.config.height = $("#report_view_height").val();
					this_.paper.setSize(this_.config.width,this_.config.height);
				},
				'关闭': function() {
					$(this).dialog( "close" );
				}
			}
		});
	 $( "#btn_save" ).button({
		text: true,
		icons: {
			primary: "ui-icon-disk"
		}
	}).click(function(){
		if(typeof(this_.config.saveFun)=='function'){
			var data = this_.getModelData();
			this_.config.saveFun(JSON.stringify(data));
		}
	});
 	$( "#btn_modify" ).button({
		text: true,
		icons: {
			primary: "ui-icon-gear"
		}
	}).click(function(){
		$( "#report-win" ).dialog( "open" );
		$("#report_view_type").html("");
		for(var i in this_.config.expressJson){
			$("#report_view_type").append("<option vlaue='"+this_.config.expressJson[i]+"'>"+i+"</option>")
		}
		//$("#report_view_type").val(this_.config.viewType);
		$("#report_view_width").val(this_.config.width);
		$("#report_view_height").val(this_.config.height);
	})
 	$( "#btn_delete_node" ).button({
		text: true,
		icons: {
			primary: "ui-icon-closethick"
		}
	}).click(function(){
		if(this_.selectItem!=null){
			this_.selectItem.remove();
			this_.selectItem = null;
		}
	});
 	$( "#btn_add_node" ).button({
		text: true,
		icons: {
			primary: "ui-icon-circle-plus"
		}
	}).click(function(){
		this_.addNode({
			x:10,
			y:10
		});
	})
}

VPrint.prototype.addLink =function(config){
	config.id = "link"+(++VPrint.linkId);
	this.linkArr[config.id]=new Rlink(config,this);
}

VPrint.prototype.setMovePath = function(nodeId,x,y){
	for(var nId in this.nodeArr){
		var node = this.nodeArr[nId];
		if((x>node.config.x)&&(x<(node.config.x+node.config.w))){
			if((y>node.config.y)&&(y<(node.config.y+node.config.h))){
				if(nId!=nodeId){
					if(this.config.viewType=='w'){
						x = node.config.x;
						y = node.config.y+(node.config.h-VPrint.conf.titleHeight)/2+VPrint.conf.titleHeight;
						this.tempLink.targetId = nId;
						break;
					}else if(this.config.viewType=='h'){
						x = node.config.x+(node.config.w/2);
						y = node.config.y;
						this.tempLink.targetId = nId;
						break;
					}else{
						this.tempLink.targetId = null;
					}
				}
			}
		}
	}
	
	var length = 50;
	for(var linkId in this.linkArr){
		if(this.linkArr[linkId].config.from==nodeId){
			length = this.linkArr[linkId].config.length;
			break;
		}
	}
	var startNode = this.nodeArr[this.tempLink.from];
	var startP,nextP;
	if(this.config.viewType=='w'){
		var s_x = startNode.config.x + startNode.config.w;//其实位置
		var s_y = startNode.config.y + (startNode.config.h-VPrint.conf.titleHeight)/2+VPrint.conf.titleHeight;
		startP = "M "+s_x+" "+s_y;
		nextP= "L "+(s_x+length)+" "+s_y+" L "+(s_x+length)+" "+y;
	}
	if(this.config.viewType=='h'){
		var s_x = startNode.config.x + startNode.config.w/2;//其实位置
		var s_y = startNode.config.y + startNode.config.h;
		startP = "M "+s_x+" "+s_y;
		nextP= "L "+s_x+" "+(s_y+length)+" L "+x+" "+(s_y+length);
	}
	this.tempLink.l.attr({path:startP+nextP+" L "+x+" "+y});
}
VPrint.prototype.getNodeById = function(id){
	return this.nodeArr[id];
}
VPrint.prototype.renderNode=function(){
	with(this.config){
		for(var i=0;i<node.length;i++){
			var nodeConfig = node[i];
			if(typeof(nodeConfig.id)=='undefined'){
				nodeConfig.id = "node"+(VPrint.nodeId++);
			}else{
				VPrint.nodeId++
			}
			this.nodeArr[nodeConfig.id]=new Rnode(nodeConfig,this);
		}
		for(var i=0;i<link.length;i++){
			var linkConfig = link[i];
			if(typeof(linkConfig.id)=='undefined'){
				linkConfig.id = "link"+(VPrint.linkId++);
			}else{
				VPrint.linkId++
			}
			this.linkArr[linkConfig.id]=new Rlink(linkConfig,this);
		}
	}

}

VPrint.prototype.loadData =function(data){
	this.config.node = data.node;
	this.config.link = data.link;
	this.config.viewType = data.viewType;
	this.config.height = data.height;
	this.config.width = data.width;
	this.paper.setSize(data.width,data.height);
	this.renderNode();
}

VPrint.prototype.getModelData=function(){
	var dataJson = {
		viewType:this.config.viewType,
		width : this.config.width,
		height:this.config.height,
		link:[],
		node:[]
	};
	var linkArr = this.linkArr;
	for(var linkId in linkArr){
		var t = {};
		t.id=linkId;
		t.from=linkArr[linkId].config.from;
		t.to =linkArr[linkId].config.to;
		t.length = linkArr[linkId].config.length;
		dataJson.link.push(t);
	}
	var node = [];
	var nodeArr = this.nodeArr;
	for(var nodeId in nodeArr){
		var t = nodeArr[nodeId].config;
		t.id = nodeId;
		if(typeof(t.url)!='undefined'&&t.url!=''){
			t.content = '';
		}
		dataJson.node.push(t);
	}
	return dataJson;
}

VPrint.prototype.reDrawLink = function(){
	for(var linkId in this.linkArr){
		this.linkArr[linkId].reDraw();
	}
}

function getPxLength(text){
	var l =0;
	for(var t=0;t<text.length;t++){
		if(/^[\u4e00-\u9fa5]+$/.test(text.charAt(t))){
			l = l+18;
		}else{
			l = l + 4;
		}
	}
	return l;
}

function Rlink(config,report){
	this.report=report;
	this.config = config;
	this.l = report.paper.path(this.getPath());
	this.l.attr({"cursor":'pointer'})
	if(report.config.isEdit){
		this.l.drag(this.onMove,this.onStart,this.onEnd,this,this,this);
	}
}
Rlink.prototype.addTargetNode = function(tId){
	var hasExit = false;
	for(var i=0;i<this.config.to.length;i++){
		if(this.config.to[i]==tId){
			hasExit = true;
			break;
		}
	}
	if(!hasExit){
		this.config.to.push(tId);
		this.reDraw();
	}
}
Rlink.prototype.removeTargetNode = function(i){
	this.config.to.splice(i,1);
	if(this.config.to.length==0){
		this.remove();
	}else{
		this.reDraw();
	}
}
Rlink.prototype.remove = function(){
	this.l.remove();
	delete this.report.linkArr[this.config.id];
}
Rlink.prototype.reDraw=function(){
	this.l.attr({path:this.getPath()});
}
Rlink.prototype.onMove=function(dx,dy,x,y,event){
	var report = this.report;
	if(report.config.viewType=='w'){
		this.config.length=this.l.s.length+dx;
	}else{
		this.config.length=this.l.s.length+dy;
	}
	this.reDraw();
}
Rlink.prototype.onStart=function(x,y,event){
	this.l.s={length:this.config.length};
}
Rlink.prototype.onEnd=function(event){
}
Rlink.prototype.getPath = function(){
	var lstr = "";
	var config = this.config;
	var report = this.report;
	this.config.length=this.config.length||50;
	var length = config.length;
	var startNode = report.nodeArr[config.from];
	if(report.config.viewType=='w'){
		var s_x = startNode.config.x + startNode.config.w;//其实位置
		var s_y = startNode.config.y + (startNode.config.h-VPrint.conf.titleHeight)/2+VPrint.conf.titleHeight;
		var startP = "M "+s_x+" "+s_y;
		var nextP= "L "+(s_x+length)+" "+s_y;
		lstr = startP+nextP;
		for(var i=0;i<config.to.length;i++){
			var targetNode = report.nodeArr[config.to[i]];
			var mP = "M "+(s_x+length)+" "+s_y;
			var threeP = "L "+(s_x+length)+" "+(targetNode.config.y+(targetNode.config.h-VPrint.conf.titleHeight)/2+VPrint.conf.titleHeight);
			var fourP = "L "+targetNode.config.x+" "+(targetNode.config.y+(targetNode.config.h-VPrint.conf.titleHeight)/2+VPrint.conf.titleHeight);
			lstr = lstr +mP+ threeP+fourP;
		}
	}
	if(report.config.viewType=='h'){
		var s_x = startNode.config.x + startNode.config.w/2;//其实位置
		var s_y = startNode.config.y + startNode.config.h;
		var startP = "M "+s_x+" "+s_y;
		var nextP= "L "+s_x+" "+(s_y+length);
		lstr = startP+nextP;
		for(var i=0;i<config.to.length;i++){
			var targetNode = report.nodeArr[config.to[i]];
			var mP = "M "+s_x+" "+(s_y+length);
			var threeP = "L "+(targetNode.config.x+targetNode.config.w/2)+" "+(s_y+length);
			var fourP = "L "+(targetNode.config.x+targetNode.config.w/2)+" "+targetNode.config.y;
			lstr = lstr +mP+ threeP+fourP;
		}
	}
	return lstr;
}

function Rnode(config,report){
	this.report=report;
	config = config||{};
	config.w = config.w||200;//宽度
	config.h = config.h||200;//高度
	config.th = config.th||VPrint.conf.titleHeight;//标题高度
	config.title=config.title||'';
	config.titleRemark = config.titleRemark||'';
	this.config = config;
	if(typeof(this.config.url)!='undefined'&&this.config.url!=''){
		$.ajax({
			url:this.config.url,
			node:this,
			success:function(data){
				this.node.config.content = data;
				this.node.afterAjaxCrete();
			}
		})
	}else{
		this.afterAjaxCrete();
	}
	return this;
}
Rnode.prototype.resetSize=function(){
	with(this.config){
		var k=1;
		var ma = content.match(new RegExp(/\n/ig));
		if(ma!=null){
			k = content.match(new RegExp(/\n/ig)).length+1;
		}
		this.config.h = VPrint.conf.titleHeight+k*20;
		this.config.w = getPxLength(title)+100+getPxLength(titleRemark);
		this.c1.attr({width:w,height:h});
		this.c2.attr({width:w,height:th});
		this.title.attr({x:x+getPxLength(title)/2+20,y:y+20});
		this.titleRemark.attr({x:x+w-VPrint.conf.titleHeight,y:y+20});
		this.content.attr({x:x+10,y:y+th+h/2-20});
	}
}

Rnode.prototype.setTitle=function(title){
	this.title.attr({"text":title});
	this.config.title = title;
}
Rnode.prototype.setTitleRemark=function(titleRemark){
	this.titleRemark.attr({"text":titleRemark});
	this.config.titleRemark = titleRemark;
}
Rnode.prototype.setTitleRemarkClick=function(funName){
	this.config.titleRemarkClic = window[funName];
}
Rnode.prototype.setContent=function(content){
	this.content.attr({"text":content});
	this.config.content = content;
}
Rnode.prototype.afterAjaxCrete = function(){
	var report = this.report;
	with(this.config){
		var k =1;
		var ma = content.match(new RegExp(/\n/ig));
		if(ma!=null){
			k=ma.length+1;
		}
		this.config.h = VPrint.conf.titleHeight+k*20;
		this.config.w = getPxLength(title)+100+getPxLength(titleRemark);
		this.c1 = report.paper.rect(x,y,w,h,3).attr({stroke: "#400CA1"});;
		this.c2 = report.paper.rect(x,y,w,th,3).attr({fill: "#0C14A1"});
		this.title = report.paper.text(x+getPxLength(title)/2+20,y+20,title).attr({
			"font-size":VPrint.conf.titleFontSize+'px',
			"font-weight":'bold',
			"cursor":'pointer',
			"fill":'#ffffff'
		});
		if(report.config.isEdit){
			this.title.drag(this.onMove,this.onStart,this.onEnd,this,this,this);
			this.title.click(function(e){
				if(this.report.selectItem!=null){
					this.report.selectItem.unSelect();
				}
				this.select();
				if ( e && e.stopPropagation ) 
				    e.stopPropagation(); 
				else
				    window.event.cancelBubble = true; 
				return false;
			},this);
			this.title.dblclick(function(){
				$( "#node-win" ).attr("nodeId",this.config.id);
				$( "#node-win" ).dialog( "open" );
				$('#nodeId').html(this.config.id);
				$('#titleId').val(this.config.title);
				$('#titleRemarkId').val(this.config.titleRemark);
				$("#contentUrl").val(this.config.url);
				if(typeof(this.config.url)=='undefined'||this.config.url==''){
					$("#contentId").val(this.config.content);
				}else{
					$("#contentId").val("");
				}
			},this);
		}
		this.titleRemark = report.paper.text(x+w-VPrint.conf.titleHeight,y+20,titleRemark).attr({
			"font-size":VPrint.conf.titleFontSize+'px',
			"font-weight":'bold',
			"cursor":'pointer',
			"fill":'#ffffff'
		});
		this.content = report.paper.text(x+10,y+th+h/2-20,content).attr({
			"font-size":VPrint.conf.bodyFontSize+'px',
			"font-weight":'normal',
			"fill":'#000000',
			"text-anchor":'start'
		});
	}
}
Rnode.prototype.afterAjax=function(config){
	if(typeof(config.title)!='undefined'){
		this.setTitle(config.title);
	}
	if(typeof(config.titleRemark)!='undefined'){
		this.setTitleRemark(config.titleRemark);
	}
	if(typeof(config.content)!='undefined'){
		this.setContent(config.content);
	}
	this.resetSize();
	this.report.reDrawLink();
}
Rnode.prototype.setConfig=function(config){
	config = config||{};
	if(typeof(config.url)!='undefined'&&config.url!=''){
		this.config.url = config.url;
		$.ajax({
			url:config.url,
			node:this,
			config:config,
			success:function(data){
				this.config.content = data;
				this.node.afterAjax(this.config);
			}
		})
	}else{
		this.afterAjax(config);
	}
};
Rnode.prototype.select=function(){
	this.c1.attr({stroke: "#ff0C01"});
	this.c2.attr({fill: "#ff0C01"});
	this.report.selectItem = this;
}
Rnode.prototype.unSelect=function(){
	this.c1.attr({stroke: "#400CA1"});
	this.c2.attr({fill: "#0C14A1"});
	this.report.selectItem = null;
};
Rnode.prototype.remove=function(){
	this.c1.remove();
	this.c2.remove();
	this.title.remove();
	this.titleRemark.remove();
	this.content.remove();
	var tempId = this.config.id;
	var linkArr = this.report.linkArr;
	for(var i in linkArr){
		var link = linkArr[i];
		if(this.config.id==link.config.from){
			link.remove();
		}
		for(var j=0;j<link.config.to.length;j++){
			if(this.config.id==link.config.to[j]){
				link.removeTargetNode(j);
			}
		}
	}
	delete this.report.nodeArr[tempId];
}
Rnode.prototype.onMove=function(dx,dy,x,y,event){
	if (event.button == 2) {
		this.report.setMovePath(this.config.id,x,y);
	}else{
		this.config.x = dx+this.c1.s.x;
		this.config.y = dy+this.c1.s.y;
		this.c1.attr({x:dx+this.c1.s.x,y:dy+this.c1.s.y});
		this.c2.attr({x:dx+this.c2.s.x,y:dy+this.c2.s.y});
		this.title.attr({x:dx+this.title.s.x,y:dy+this.title.s.y});
		this.titleRemark.attr({x:dx+this.titleRemark.s.x,y:dy+this.titleRemark.s.y});
		this.content.attr({x:dx+this.content.s.x,y:dy+this.content.s.y});
		var linkArr = this.report.linkArr;
		for(var i in linkArr){
			var link = linkArr[i];
			if(this.config.id==link.config.from){
				link.reDraw();
			}
			for(var j=0;j<link.config.to.length;j++){
				if(this.config.id==link.config.to[j]){
					link.reDraw();
				}
			}
		}
	}
}

Rnode.prototype.onStart=function(x,y,event){
	this.c1.s={x:this.c1.attr("x"),y:this.c1.attr("y")};
	this.c2.s={x:this.c2.attr("x"),y:this.c2.attr("y")};
	this.title.s={x:this.title.attr("x"),y:this.title.attr("y")};
	this.titleRemark.s={x:this.titleRemark.attr("x"),y:this.titleRemark.attr("y")};
	this.content.s={x:this.content.attr("x"),y:this.content.attr("y")};
	if (event.button == 2) {
		var l = this.report.paper.path("M "+x+" "+y);
		this.report.tempLink = {from:this.config.id,l:l}
	}
}
Rnode.prototype.onEnd=function(event){
	if (event.button == 2) {
		if(this.report.tempLink.targetId!=null){
			var targetLinkId = null;
			for(var linkId in this.report.linkArr){
				if(this.report.linkArr[linkId].config.from==this.report.tempLink.from){
					targetLinkId = linkId;
					break;
				}
			}
			if(targetLinkId!=null){
				this.report.linkArr[targetLinkId].addTargetNode(this.report.tempLink.targetId);
			}else{
				this.report.addLink({
					from:this.report.tempLink.from,
					to:[this.report.tempLink.targetId]
				});
			}
		}
		this.report.tempLink.l.remove();
		this.report.tempLink = {};
	}
}
