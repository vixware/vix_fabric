<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<!-- New Content -->
<link rel=stylesheet href="${nvix}/vixntcommon/gtt/platform.css" type="text/css">
<link rel=stylesheet href="${nvix}/vixntcommon/libs/dateField/jquery.dateField.css" type="text/css">
<link rel=stylesheet href="${nvix}/vixntcommon/gtt/gantt.css" type="text/css">
<link rel=stylesheet href="${nvix}/vixntcommon/gtt/print.css" type="text/css" media="print">
<!-- New Content -->
<link rel=stylesheet href="${nvix}/vixntcommon/gtt/platform.css" type="text/css">
<link rel=stylesheet href="${nvix}/vixntcommon/libs/dateField/jquery.dateField.css" type="text/css">
<link rel=stylesheet href="${nvix}/vixntcommon/gtt/gantt.css" type="text/css">
<link rel=stylesheet href="${nvix}/vixntcommon/gtt/print.css" type="text/css" media="print">
<div id="content">
	<script src="${nvix}/vixntcommon/libs/jquery.min.js"></script>
	<script> var jq183 = jQuery.noConflict(true); </script>
	<script src="${nvix}/vixntcommon/libs/jquery-ui.min.js"></script>
	<!-- <script>
		jq183(function($){
		$('#');});
	</script> -->
	<style>
		.tab-content .tab-pane{
			height:700px;
		}
		.bootstrapWizard li .step:hover {
	    	border: 3px solid #55606E;
		}
	</style>
	<script src="${nvix}/vixntcommon/libs/jquery.livequery.min.js"></script>
	<script src="${nvix}/vixntcommon/libs/jquery.timers.js"></script>
	<script src="${nvix}/vixntcommon/libs/platform.js"></script>
	<script src="${nvix}/vixntcommon/libs/date.js"></script>
	<script src="${nvix}/vixntcommon/libs/i18nJs.js"></script>
	<script src="${nvix}/vixntcommon/libs/dateField/jquery.dateField.js"></script>
	<script src="${nvix}/vixntcommon/libs/JST/jquery.JST.js"></script>
	<link rel="stylesheet" type="text/css" href="${nvix}/vixntcommon/libs/jquery.svg.css">
	<script type="text/javascript" src="${nvix}/vixntcommon/libs/jquery.svg.min.js"></script>
	<!--In case of jquery 1.7-->
		<!--<script type="text/javascript" src="libs/jquery.svgdom.pack.js"></script>-->
	<!--In case of jquery 1.8-->
	<script type="text/javascript" src="${nvix}/vixntcommon/libs/jquery.svgdom.1.8.js"></script>
	
	<script src="${nvix}/vixntcommon/gtt/ganttUtilities.js"></script>
	<script src="${nvix}/vixntcommon/gtt/ganttTask.js"></script>
	<script src="${nvix}/vixntcommon/gtt/ganttDrawerSVG.js"></script>
	<script src="${nvix}/vixntcommon/gtt/ganttGridEditor.js"></script>
	<script src="${nvix}/vixntcommon/gtt/ganttMaster.js"></script> 
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-home"></i> 任务与计划 <span>&gt; 项目甘特图</span><span>&gt; ${project.projectName}</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/nvixProjectAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<section id="widget-grid" class="well">
		<div class="row">
			<article class="col-sm-12 col-md-12 col-lg-12">
				<div id="bootstrap-wizard-1" class="col-sm-12">
					<!-- <div class="form-bootstrapWizard">
						<ul class="bootstrapWizard form-wizard">
							<li class="active" data-target="#step1">
								<a href="#tab1" data-toggle="tab"> 
									<span class="step">1</span> 
									<span class="title">Basic information</span>
								</a>
							</li>
							<li data-target="#step2">
								<a href="#tab2" data-toggle="tab"> 
									<span class="step">2</span> 
									<span class="title">Billing information</span>
								</a>
							</li>
							<li data-target="#step3">
								<a href="#tab3" data-toggle="tab"> 
									<span class="step">3</span> 
									<span class="title">Domain Setup</span>
								</a>
							</li>
							<li data-target="#step4">
								<a href="#tab4" data-toggle="tab"> 
									<span class="step">4</span> 
									<span class="title">Save Form</span>
								</a>
							</li>
						</ul>
						<div class="clearfix"></div>
					</div> -->
					<div class="tab-content">
						<div class="tab-pane active" id="tab1">
							<div id="workSpace" style="padding:0px; overflow-y:auto; overflow-x:hidden;border:1px solid #e5e5e5;position:relative;margin:0 5px"></div>
							<div id="taZone" style="display:none;" class="noprint">
								<textarea rows="8" cols="150" id="ta">${gtt}</textarea>
							  	<button onclick="loadGanttFromServer();">load</button>
							</div>
							<style>
							  .resEdit {padding: 15px;}
							  .resLine {width: 95%;padding: 3px;margin: 5px;border: 1px solid #d0d0d0;}
							  /* body {overflow: hidden;} */
							  .ganttButtonBar h1{color: #000000;font-weight: bold;font-size: 28px;margin-left: 10px;}
							</style>
							<form id="gimmeBack" style="display:none;" action="../gimmeBack.jsp" method="post" target="_blank">
								<input type="hidden" name="prj" id="gimBaPrj">
							</form>
							<script type="text/javascript">
								var ge;  //this is the hugly but very friendly global var for the gantt editor
								jq183(function() {
								  	//加载模板
								  	$("#ganttemplates").loadTemplates();
								  	//甘特图初始化
								 	ge = new GanttMaster();
								  	var workSpace = $("#workSpace");
								  	workSpace.css({width:$("#tab1").width() - 20,height:$("#tab1").height()});
								  	ge.init(workSpace);
								  	//加载按钮
								  	/* $(".ganttButtonBar div").append("<button onclick='' class='button'>清除</button>")
								          .append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;")
								          .append("<button onclick='' class='button'>导出</button>"); */
								  	$(".ganttButtonBar h1").html("<a href='http://twproject.com' title='Twproject the friendly project and work management tool' target='_blank'><img width='80%' src='${nvix}/vixntcommon/res/twBanner.jpg'></a>");
								  	$(".ganttButtonBar div").addClass('buttons');
								  	loadI18n();//国际化
								  	loadGanttFromServer();//加载甘特图
								  	if (!ge.roles || ge.roles.length == 0) {
								    	setRoles();
								  	}
								  	if (!ge.resources || ge.resources.length == 0) {
								    	setResource();
								  	}
								  	$("#main").resize(function(){
								    	workSpace.css({width:$("#tab1").width() - 20,height:$("#tab1").height() - 120});
								    	workSpace.trigger("resize.gantt");
								  	})
								});
								function loadGanttFromServer(taskId, callback) {
							  		loadFromLocalStorage();
								}
								function saveGanttOnServer() {
							 		if(!ge.canWrite)
							    	return;
							  		saveInLocalStorage();
								}
								//-------------------------------------------  Create some demo data ------------------------------------------------------
								function setRoles() {
							  		ge.roles = [
								    	{
								      		id:"tmp_1",
								      		name:"Project Manager"
								    	},
								    	{
								      		id:"tmp_2",
								      		name:"Worker"
								    	},
								    	{
								      		id:"tmp_3",
								      		name:"Stakeholder/Customer"
								    	}
							  		];
								}
								function setResource() {
							 		var res = [];
							  		for (var i = 1; i <= 10; i++) {
							    		res.push({id:"tmp_" + i,name:"Resource " + i});
							  		}
							  		ge.resources = res;
								}
								function editResources(){
								}
								function clearGantt() {
								  ge.reset();
								}
								function loadI18n() {
							  		GanttMaster.messages = {
							    		"CANNOT_WRITE":"CANNOT_WRITE",
							
							    		"CHANGE_OUT_OF_SCOPE":"NO_RIGHTS_FOR_UPDATE_PARENTS_OUT_OF_EDITOR_SCOPE",
							
							    		"START_IS_MILESTONE":"START_IS_MILESTONE",
							
							    		"END_IS_MILESTONE":"END_IS_MILESTONE",
							
							    		"TASK_HAS_CONSTRAINTS":"TASK_HAS_CONSTRAINTS",
							
							    		"GANTT_ERROR_DEPENDS_ON_OPEN_TASK":"GANTT_ERROR_DEPENDS_ON_OPEN_TASK",
							
							    		"GANTT_ERROR_DESCENDANT_OF_CLOSED_TASK":"GANTT_ERROR_DESCENDANT_OF_CLOSED_TASK",
							
							    		"TASK_HAS_EXTERNAL_DEPS":"TASK_HAS_EXTERNAL_DEPS",
							
							    		"GANTT_ERROR_LOADING_DATA_TASK_REMOVED":"GANTT_ERROR_LOADING_DATA_TASK_REMOVED",
							
							    		"ERROR_SETTING_DATES":"ERROR_SETTING_DATES",
							
							    		"CIRCULAR_REFERENCE":"CIRCULAR_REFERENCE",
							
							    		"CANNOT_DEPENDS_ON_ANCESTORS":"CANNOT_DEPENDS_ON_ANCESTORS",
							
							    		"CANNOT_DEPENDS_ON_DESCENDANTS":"CANNOT_DEPENDS_ON_DESCENDANTS",
							
							    		"INVALID_DATE_FORMAT":"INVALID_DATE_FORMAT",
							
							    		"TASK_MOVE_INCONSISTENT_LEVEL":"TASK_MOVE_INCONSISTENT_LEVEL",
							
							    		"GANTT_QUARTER_SHORT":"trim.",
							
							    		"GANTT_SEMESTER_SHORT":"sem."
							  		};
								}
								//-------------------------------------------  Get project file as JSON (used for migrate project from gantt to Teamwork) ------------------------------------------------------
								function getFile() {
							  		$("#gimBaPrj").val(JSON.stringify(ge.saveProject()));
							  		$("#gimmeBack").submit();
							  		$("#gimBaPrj").val("");
								}
								//-------------------------------------------  LOCAL STORAGE MANAGEMENT (for this demo only) ------------------------------------------------------
								Storage.prototype.setObject = function(key, value) {
							  		this.setItem(key, JSON.stringify(value));
								};
								Storage.prototype.getObject = function(key) {
							  		return this.getItem(key) && JSON.parse(this.getItem(key));
								};
								function loadFromLocalStorage() {
							  		var ret;
							  		if (localStorage) {
								    	if (localStorage.getObject("teamworkGantDemo")) {
								      		ret = localStorage.getObject("teamworkGantDemo");
								    	}
							  		} else {
							    		$("#taZone").show();
							  		}
								  	if (!ret || !ret.tasks || ret.tasks.length == 0){
								    	ret = JSON.parse($("#ta").val());
								    	//actualiza data
								    	var offset=new Date().getTime()-ret.tasks[0].start;
								    	for (var i=0;i<ret.tasks.length;i++)
								      		ret.tasks[i].start=ret.tasks[i].start+offset;
								  	}
								  	ge.loadProject(ret);
								  	ge.checkpoint(); //empty the undo stack
								}
								function saveInLocalStorage() {
							  		var prj = ge.saveProject();
							  		if (localStorage) {
							    		localStorage.setObject("teamworkGantDemo", prj);
							  		} else {
							    		$("#ta").val(JSON.stringify(prj));
							 		}
								}
								//-------------------------------------------  Open a black popup for managing resources. This is only an axample of implementation (usually resources come from server) ------------------------------------------------------
								function editResources(){
							  		//make resource editor
							  		var resourceEditor = $.JST.createFromTemplate({}, "RESOURCE_EDITOR");
							  		var resTbl=resourceEditor.find("#resourcesTable");
							  		for (var i=0;i<ge.resources.length;i++){
							   			var res=ge.resources[i];
							    		resTbl.append($.JST.createFromTemplate(res, "RESOURCE_ROW"))
							  		}
							  		//bind add resource
							 	 	resourceEditor.find("#addResource").click(function(){
								    	resTbl.append($.JST.createFromTemplate({id:"new",name:"resource"}, "RESOURCE_ROW"))
								  	});
							  		//bind save event
							  		resourceEditor.find("#resSaveButton").click(function(){
							    		var newRes=[];
							    		//find for deleted res
							    		for (var i=0;i<ge.resources.length;i++){
							      			var res=ge.resources[i];
							      			var row = resourceEditor.find("[resId="+res.id+"]");
							      			if (row.size()>0){
							        		//if still there save it
							        		var name = row.find("input[name]").val();
							        		if (name && name!="")
							          			res.name=name;
							        			newRes.push(res);
							      			} else {
							        			//remove assignments
							        			for (var j=0;j<ge.tasks.length;j++){
							          				var task=ge.tasks[j];
							          				var newAss=[];
							         				for (var k=0;k<task.assigs.length;k++){
							            				var ass=task.assigs[k];
							            				if (ass.resourceId!=res.id)
							              				newAss.push(ass);
							          				}
							          				task.assigs=newAss;
							        			}
							      			}
							    		}
							    		//loop on new rows
							    		resourceEditor.find("[resId=new]").each(function(){
							      			var row = $(this);
							      			var name = row.find("input[name]").val();
							      			if (name && name!="")
											newRes.push (new Resource("tmp_"+new Date().getTime(),name));
							    		});
							    		ge.resources=newRes;
							    		closeBlackPopup();
							    		ge.redraw();
									});
							 		var ndo = createBlackPage(400, 500).append(resourceEditor);
								}
							</script>
							<div id="gantEditorTemplates" style="display:none;">
								<!-- 顶部按钮 -->
								<!-- <button class="button textual" title="undo"><i class="fa fa-warning"></i></button> -->
								<div class="__template__" type="GANTBUTTONS"><!--
							  		<div class="ganttButtonBar noprint">
							    		<div class="buttons">
							    			<button class="button textual" onclick="loadContent('','${nvix}/nvixnt/nvixProjectAction!goProjectTracing.action');"  title="新增"><i class="fa fa-plus"></i></button>
							    			<button onclick="$('#workSpace').trigger('undo.gantt');" class="button textual" title="undo"><span class="teamworkIcon">&#39;</span></button>
							    			<button onclick="$('#workSpace').trigger('redo.gantt');" class="button textual" title="redo"><span class="teamworkIcon">&middot;</span></button>
							    			<span class="ganttButtonSeparator"></span>
							    			<button onclick="$('#workSpace').trigger('addAboveCurrentTask.gantt');" class="button textual" title="insert above"><span class="teamworkIcon">l</span></button>
							    			<button onclick="$('#workSpace').trigger('addBelowCurrentTask.gantt');" class="button textual" title="insert below"><span class="teamworkIcon">X</span></button>
							    			<span class="ganttButtonSeparator"></span>
							    			<button onclick="$('#workSpace').trigger('indentCurrentTask.gantt');" class="button textual" title="indent task"><span class="teamworkIcon">.</span></button>
							    			<button onclick="$('#workSpace').trigger('outdentCurrentTask.gantt');" class="button textual" title="unindent task"><span class="teamworkIcon">:</span></button>
							    			<span class="ganttButtonSeparator"></span>
							    			<button onclick="$('#workSpace').trigger('moveUpCurrentTask.gantt');" class="button textual" title="move up"><span class="teamworkIcon">k</span></button>
							    			<button onclick="$('#workSpace').trigger('moveDownCurrentTask.gantt');" class="button textual" title="move down"><span class="teamworkIcon">j</span></button>
							    			<span class="ganttButtonSeparator"></span>
							    			<button onclick="$('#workSpace').trigger('zoomMinus.gantt');" class="button textual" title="zoom out"><span class="teamworkIcon">)</span></button>
							    			<button onclick="$('#workSpace').trigger('zoomPlus.gantt');" class="button textual" title="zoom in"><span class="teamworkIcon">(</span></button>
							    		</div>
									</div>
								--></div>
											<!-- <span class="ganttButtonSeparator"></span>
							    			<button onclick="$('#workSpace').trigger('deleteCurrentTask.gantt');" class="button textual" title="delete"><span class="teamworkIcon">&cent;</span></button>
											<span class="ganttButtonSeparator"></span>
							    			<button onclick="print();" class="button textual" title="print"><span class="teamworkIcon">p</span></button>
							    			<span class="ganttButtonSeparator"></span>
							    			<button onclick="ge.gantt.showCriticalPath=!ge.gantt.showCriticalPath; ge.redraw();" class="button textual" title="Critical Path"><span class="teamworkIcon">&pound;</span></button>
							    			<span class="ganttButtonSeparator"></span>
							    			<button onclick="editResources();" class="button textual" title="edit resources"><span class="teamworkIcon">M</span></button>
							      			&nbsp; &nbsp; &nbsp; &nbsp;
							      			<button onclick="" class="button first big" title="save">保存</button> -->
								<!-- 表格模板 -->
								<div class="__template__" type="TASKSEDITHEAD"><!--
									<table class="gdfTable" cellspacing="0" cellpadding="0">
							    		<thead>
							    			<tr style="height:40px">
								      			<th class="gdfColHeader" style="width:35px;"></th>
								      			<th class="gdfColHeader" style="width:25px;"></th>
								      			<th class="gdfColHeader gdfResizable" style="width:150px;">编号</th>
										      	<th class="gdfColHeader gdfResizable" style="width:300px;">名称</th>
										      	<th class="gdfColHeader gdfResizable" style="width:90px;">开始</th>
										      	<th class="gdfColHeader gdfResizable" style="width:90px;">结束</th>
										      	<th class="gdfColHeader gdfResizable" style="width:50px;">时间</th>
										      	<th class="gdfColHeader gdfResizable" style="width:100px;">执行人</th>
								    		</tr>
							    		</thead>
							  		</table>
							  	--></div>
							  	<!-- 图形模板 -->
								<div class="__template__" type="TASKROW"><!--
							  		<tr taskId="(#=obj.id#)" class="taskEditRow" level="(#=level#)">
							    		<th class="gdfCell edit" align="right" onclick="goSaveOrUpdate('(#=obj.id#)','修改任务')"; style="cursor:pointer;">
							    			<span class="taskRowIndex">(#=obj.getRow()+1#)</span> <span class="teamworkIcon" style="font-size:12px;" >e</span>
							    		</th>
							    		<td class="gdfCell noClip" align="center"><div class="taskStatus cvcColorSquare" status="(#=obj.status#)"></div></td>
							    		<td class="gdfCell"><input type="text" name="code" value="(#=obj.code?obj.code:''#)"></td>
							    		<td class="gdfCell indentCell" style="padding-left:(#=obj.level*10#)px;">
							      			<div class="(#=obj.isParent()?'exp-controller expcoll exp':'exp-controller'#)" align="center"></div>
							      			<input type="text" name="name" value="(#=obj.name#)">
							    		</td>
							    		<td class="gdfCell"><input type="text" name="start"  value="" class="date"></td>
							    		<td class="gdfCell"><input type="text" name="end" value="" class="date"></td>
							    		<td class="gdfCell"><input type="text" name="duration" value="(#=obj.duration#)"></td>
							    		<td class="gdfCell"><input type="text" name="description" value="(#=obj.description#)"></td>
							    		<td class="gdfCell taskAssigs">(#=obj.getAssigsString()#)</td>
							  		</tr>
							  	--></div>
							  	<div class="__template__" type="TASKEMPTYROW"><!--
									<tr class="taskEditRow emptyRow" >
							    		<th class="gdfCell" align="right"></th>
							    		<td class="gdfCell noClip" align="center"></td>
							    		<td class="gdfCell"></td>
							    		<td class="gdfCell"></td>
							    		<td class="gdfCell"></td>
							    		<td class="gdfCell"></td>
							    		<td class="gdfCell"></td>
							    		<td class="gdfCell"></td>
							    		<td class="gdfCell"></td>
							  		</tr>
								--></div>
							  	<div class="__template__" type="TASKBAR"><!--
									<div class="taskBox taskBoxDiv" taskId="(#=obj.id#)" >
							    		<div class="layout (#=obj.hasExternalDep?'extDep':''#)">
							      			<div class="taskStatus" status="(#=obj.status#)"></div>
							      			<div class="taskProgress" style="width:(#=obj.progress>100?100:obj.progress#)%; background-color:(#=obj.progress>100?'red':'rgb(153,255,51);'#);"></div>
							      			<div class="milestone (#=obj.startIsMilestone?'active':''#)" ></div>
							      			<div class="taskLabel"></div>
							      			<div class="milestone end (#=obj.endIsMilestone?'active':''#)" ></div>
							    		</div>
							  		</div>
							  	--></div>
							  	<!-- 状态 -->
							  	<div class="__template__" type="CHANGE_STATUS"><!--
							    	<div class="taskStatusBox">
							      		<div class="taskStatus cvcColorSquare" status="STATUS_ACTIVE" title="进行中"></div>
							      		<div class="taskStatus cvcColorSquare" status="STATUS_DONE" title="已完成"></div>
							      		<div class="taskStatus cvcColorSquare" status="STATUS_FAILED" title="已超时"></div>
							      		<div class="taskStatus cvcColorSquare" status="STATUS_SUSPENDED" title="未开始"></div>
							    	</div>
							  	--></div>
							  	<!-- <div class="taskStatus cvcColorSquare" status="STATUS_UNDEFINED" title="undefined"></div> -->
								<div class="__template__" type="TASK_EDITOR" style="display: none;"><!--
							  		<div class="ganttTaskEditor" style="display: none;">
							  			<table width="100%">
							    			<tr>
							      				<td>
							        				<table cellpadding="5">
							          					<tr>
							            					<td><label for="code">code/short name</label><br><input type="text" name="code" id="code" value="" class="formElements"></td>
							           					</tr>
							           					<tr>
							            					<td><label for="name">name</label><br><input type="text" name="name" id="name" value=""  size="35" class="formElements"></td>
							          					</tr>
							          					<tr>
								            				<td>
								              					<label for="description">description</label><br>
								              					<textarea rows="5" cols="30" id="description" name="description" class="formElements"></textarea>
								            				</td>
							          					</tr>
							        				</table>
							      				</td>
							      				<td valign="top">
							        				<table cellpadding="5">
							          					<tr>
							          						<td colspan="2"><label for="status">status</label><br><div id="status" class="taskStatus" status=""></div></td>
														</tr>
							          					<tr>
							          						<td colspan="2"><label for="progress">progress</label><br><input type="text" name="progress" id="progress" value="" size="3" class="formElements"></td>
							          					</tr>
							          					<tr>
							          						<td><label for="start">start</label><br><input type="text" name="start" id="start"  value="" class="date" size="10" class="formElements"><input type="checkbox" id="startIsMilestone"> </td>
							          						<td rowspan="2" class="graph" style="padding-left:50px"><label for="duration">dur.</label><br><input type="text" name="duration" id="duration" value=""  size="5" class="formElements"></td>
							        					</tr>
							        					<tr>
							          						<td><label for="end">end</label><br><input type="text" name="end" id="end" value="" class="date"  size="10" class="formElements"><input type="checkbox" id="endIsMilestone"></td>
														</tr>
							        				</table>
							      				</td>
							    			</tr>
							    		</table>
							  			<h2>assignments</h2>
							  			<table  cellspacing="1" cellpadding="0" width="100%" id="assigsTable">
							    			<tr>
							      				<th style="width:100px;">name</th>
							      				<th style="width:70px;">role</th>
							      				<th style="width:30px;">est.wklg.</th>
							      				<th style="width:30px;" id="addAssig"><span class="teamworkIcon" style="cursor: pointer">+</span></th>
							    			</tr>
							  			</table>
							  			<div style="text-align: right; padding-top: 20px"><button id="saveButton" class="button big">save</button></div>
									</div>
								--></div>
							  	<div class="__template__" type="ASSIGNMENT_ROW"><!--
							  		<tr taskId="(#=obj.task.id#)" assigId="(#=obj.assig.id#)" class="assigEditRow" >
							    		<td ><select name="resourceId"  class="formElements" (#=obj.assig.id.indexOf("tmp_")==0?"":"disabled"#) ></select></td>
							    		<td ><select type="select" name="roleId"  class="formElements"></select></td>
							    		<td ><input type="text" name="effort" value="(#=getMillisInHoursMinutes(obj.assig.effort)#)" size="5" class="formElements"></td>
							    		<td align="center"><span class="teamworkIcon delAssig" style="cursor: pointer">d</span></td>
							  		</tr>
							  	--></div>
							  	<div class="__template__" type="RESOURCE_EDITOR"><!--
							  		<div class="resourceEditor" style="padding: 5px;">
							    		<h2>Project team</h2>
							    		<table  cellspacing="1" cellpadding="0" width="100%" id="resourcesTable">
							      			<tr>
							        			<th style="width:100px;">name</th>
							        			<th style="width:30px;" id="addResource"><span class="teamworkIcon" style="cursor: pointer">+</span></th>
							      			</tr>
							    		</table>
							    		<div style="text-align: right; padding-top: 20px"><button id="resSaveButton" class="button big">save</button></div>
							  		</div>
							  	--></div>
							  	<div class="__template__" type="RESOURCE_ROW"><!--
							  		<tr resId="(#=obj.id#)" class="resRow" >
							    		<td ><input type="text" name="name" value="(#=obj.name#)" style="width:100%;" class="formElements"></td>
							    		<td align="center"><span class="teamworkIcon delRes" style="cursor: pointer">d</span></td>
							  		</tr>
							  	--></div>
							</div>
							<script type="text/javascript">
								$.JST.loadDecorator("ASSIGNMENT_ROW", function(assigTr, taskAssig) {
							    	var resEl = assigTr.find("[name=resourceId]");
							    	for (var i in taskAssig.task.master.resources) {
							      		var res = taskAssig.task.master.resources[i];
							      		var opt = $("<option>");
							      		opt.val(res.id).html(res.name);
							      		if (taskAssig.assig.resourceId == res.id)
							        	opt.attr("selected", "true");
							      		resEl.append(opt);
							    	}
							    	var roleEl = assigTr.find("[name=roleId]");
							    	for (var i in taskAssig.task.master.roles) {
							    		var role = taskAssig.task.master.roles[i];
							      		var optr = $("<option>");
							      		optr.val(role.id).html(role.name);
							      		if (taskAssig.assig.roleId == role.id)
							        	optr.attr("selected", "true");
							      		roleEl.append(optr);
							    	}
							    	if(taskAssig.task.master.canWrite && taskAssig.task.canWrite){
							      		assigTr.find(".delAssig").click(function() {
							        		var tr = $(this).closest("[assigId]").fadeOut(200, function() {
							          			$(this).remove();
							        		});
							      		});
							    	}
							  	});
							</script>
						</div>
					</div>
				</div>
			</article>
		</div>
	</section>
</div>
<script>
	$(".form-wizard").on("click","li a",function(){
		$(this).parents("li").addClass("active").siblings("li").removeClass("active");
		console.log("111")
	})
	// 修改任务
	function goSaveOrUpdate(id, title) {
		loadContent('', '${nvix}/nvixnt/nvixProjectAction!goSaveOrUpdateTask.action?id=' + id + '&source=gtt');
	};
</script>