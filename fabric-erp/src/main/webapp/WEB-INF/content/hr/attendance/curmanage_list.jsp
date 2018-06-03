<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script src="${vix}/common/js/jquery.listmenu.min-1.1.js" type="text/javascript"></script>
<link rel='stylesheet' type='text/css' href='${vix}/common/css/fullcalendar.css' />
<link rel='stylesheet' type='text/css' href='${vix}/common/css/fullcalendar.print.css' media='print' />
<script src="${vix}/common/js/jquery.tokeninput.js" type="text/javascript"></script>
<script src="${vix}/common/js/jquery.jnotify.js" type="text/javascript"></script>
<script type='text/javascript' src='${vix}/common/js/jquery-ui-1.8.17.custom.min.js'></script>
<script type='text/javascript' src='${vix}/common/js/fullcalendar.min.js'></script>

<script type="text/javascript">
loadMenuContent('${vix}/hr/hrMainAction!goMenuContent.action');
$(function(){
	if($('#numBox').length) $('#numBox').listmenu({menuWidth: '100%', cols:{ count:6, gutter:0 }});
});
var name = "";
var purchaseName = "";
var purchasePerson = "";
function loadName(){
	name = $('#nameS').val(); 
	name=Url.encode(name);
	name=Url.encode(name);
}
function loadPurchaseName(){
	purchaseName = $('#purchaseName').val(); 
	purchaseName=Url.encode(purchaseName);
	purchaseName=Url.encode(purchaseName);
}
function loadPurchasePerson(){
	purchasePerson = $('#purchasePerson').val(); 
	purchasePerson=Url.encode(purchasePerson);
	purchasePerson=Url.encode(purchasePerson);
}
$('#numBtn').click(function(){
	$('#numBtn').parent("li").toggleClass("current");
	$('#number').stop().animate({height: 'toggle', opacity: 'toggle'},500,function(){
		$('#number').css('overflow','visible');
	});
	return false;
});
$('input.btn[type="button"],input.btn[type="submit"]').hover(function(){
	$(this).addClass("btnhover");
},function(){
	$(this).removeClass("btnhover");
});
$(".drop>ul>li").bind('mouseover',function(){
	$(this).children('ul').delay(400).slideDown('fast');
}).bind('mouseleave',function(){
	$(this).children('ul').slideDown('fast').stop(true, true);
	$(this).children('ul').slideUp('fast');
});
//面包屑
if($('.sub_menu').length)
{
	$("#breadCrumb").jBreadCrumb();
}
/*搜索*/
function searchForContent(i){
	loadName();
	loadPurchaseName();
	loadPurchasePerson();
	if(i == 0){
		pager("start","${vix}/purchase/purchasePlanAction!goSearchList.action?i="+i+"&searchContent="+name,'category');
	}
	else{
		pager("start","${vix}/purchase/purchasePlanAction!goSearchList.action?i="+i+"&purchasePerson="+purchasePerson+"&purchasePlanName="+purchaseName,'category');
	}
}
/*重置搜索*/
function resetForContent(i){
	if(i == 0){
		$("#nameS").val("");
	}
	else{
		$("#purchasePerson").val("");
		$("#purchaseName").val("");
	}
}
/*改变搜索按钮的显示*/
function changeDisplay(){
	var divText = $("#lb_search_advanced").text();
	if(divText == "高级搜索"){
		$("#nameS").attr({disabled:'true'});
		$("#simpleSearch").hide();
		$("#simpleReset").hide();
	}
	else{
		$("#nameS").removeAttr("disabled");
		$("#simpleSearch").show();
		$("#simpleReset").show();
	}
}
//状态
function purStatus(status){
	pager("start","${vix}/purchase/purchasePlanAction!goSingleList.action?status="+status,'category');
}
//最近使用
function purRecent(rencentDate){
	pager("start","${vix}/purchase/purchasePlanAction!goSingleList.action?updateTime="+rencentDate,'category');
}
</script>
<!-- sub menu -->
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="cmn_print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="cmn_help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/hr_kaoqin.png" alt="" />
					<s:text name="hr_humanr_resources" /></a></li>
				<li><a href="#"><s:text name="hr_attendance_manage" /></a></li>
				<li><a href="#" onclick="loadContent('${vix}/hr/attendanceRecordAction!goList.action');">日常管理</a></li>
			</ul>
		</div>
	</h2>
	<div class="drop">
		<p>
			<a href="#" onclick="saveOrUpdate(0,$('#selectId').val());"><span><s:text name="cmn_add" /></span></a> <a href="#" onclick="deleteByIds(0,$('#selectId').val());"><span><s:text name='cmn_delete' /></span></a>
		</p>
	</div>
</div>
<div class="content">
	<div id="c_head" class=" drop">
		<span class="left_bg"></span> <span class="right_bg"></span>
		<div class="untitled">
			<b><img src="img/icon_11.png" alt="" /></b>
			<div class="popup">
				<strong> <i class="close"><a href="#"></a></i> <i><a href="#"></a></i> <i><a href="#"></a></i> <b>帮助</b>
				</strong>
				<p>帮助</p>
			</div>
		</div>
		<ul>
			<li><a href="#" id="numBtn"><img src="img/find.png" alt="" />
				<s:text name="cmn_index" /></a></li>
			<li class="fly"><a href="#"><img alt="" src="img/icon_10.png">
				<s:text name="cmn_mode" /></a>
				<ul>
					<li><a href="#" onclick="purStatus('S1')"><img alt="" src="img/uncommitted.png">待确认</a></li>
					<li><a href="#" onclick="purStatus('S2')"><img alt="" src="img/unaudited.png">审批中</a></li>
					<li><a href="#" onclick="purStatus('S3')"><img alt="" src="img/verifying.png">已发货</a></li>
					<li><a href="#" onclick="purStatus('S4')"><img alt="" src="img/approved.png">已完成</a></li>
				</ul></li>
			<li class="fly"><a href="#"><img alt="" src="img/leastRecentlyUsed.png">
				<s:text name="cmn_recently_used" /></a>
				<ul>
					<li><a href="#" onclick="purRecent('T1')"><img alt="" src="img/time_go.png">
						<s:text name="cmn_three_days" /></a></li>
					<li><a href="#" onclick="purRecent('T2')"><img alt="" src="img/time_go.png">
						<s:text name="cmn_seven_days" /></a></li>
					<li><a href="#" onclick="purRecent('T3')"><img alt="" src="img/time_go.png">
						<s:text name="cmn_month" /></a></li>
					<li><a href="#" onclick="purRecent('T4')"><img alt="" src="img/time_go.png">
						<s:text name="cmn_three_months" /></a></li>
				</ul></li>
		</ul>
		<div>
			<label>搜索内容:<input id="nameS" name="" type="text" class="int" /></label> <label> <input id="simpleSearch" name="" type="button" class="btn" value="<s:text name='cmn_search'/>" onclick="searchForContent(0);" /> <input id="simpleReset" name="" type="button" class="btn" value="<s:text name='cmn_reset'/>" onclick="resetForContent(0);" />
			</label> <strong id="lb_search_advanced" onclick="changeDisplay();"><s:text name='cmn_advance_search' /></strong>
		</div>
		<div class="search_advanced">
			<label>主题:<input id="purchaseName" name="" type="text" class="int" /></label> <label>业务员:<input id="purchasePerson" name="" type="text" class="int" /></label> <label> <input name="" type="button" class="btn" value="<s:text name='cmn_search'/>" onclick="searchForContent(1);" /> <input name="" type="button" class="btn"
				value="<s:text name='cmn_reset'/>" onclick="resetForContent(1);" />
			</label>
		</div>
	</div>
	<div id="number">
		<span class="num_left_bg"></span> <span class="num_right_bg"></span>
		<ul id="numBox" class="numBox">
			<!-- 后台获取的数据以下列格式迭代，索引的效果就有了。 -->
			<li>111</li>
			<s:iterator value="purchasePlanList" var="c">
				<li><a href="#" onclick="saveOrUpdate(${c.id},$('#selectId').val());">aa</a></li>
			</s:iterator>
		</ul>
	</div>
	<!-- c_head -->
	<div class="box">
		<div id="left" class="ui-resizable switch" style="height: 500px; width: 7px;">
			<div id="switch_box" class="switch_btn off"></div>
			<div class="left_content current" style="height: 500px;">
				<div style="padding-left: 4px;">
					<img src="${vix}/common/img/file.png" style="padding-right: 5px;" /><a href="#" onclick="loadRoot();"><s:text name='cmn_rootDirectory' /></a>
				</div>
				<div id="tree_root" class="ztree" style="padding: 0;"></div>
			</div>
			<script type="text/javascript">
				<!--
				var zTree;			
				var setting = {
					async: {
						enable: true,
						url:"${vix}/purchase/purchasePlanAction!findTreeToJson.action",
						autoParam:["id", "name=n", "level=lv"]
					},
					callback: {
						onClick: onClick
					}
				};
				function onClick(event, treeId, treeNode, clickFlag) {
					$("#selectId").val(treeNode.id);
					$("#selectIdTreeId").val(treeNode.tId);
					pager("start","${vix}/purchase/purchasePlanAction!goSingleList.action?parentId="+treeNode.id,"category");
				}
				zTree = $.fn.zTree.init($("#tree_root"), setting);
				//-->
			</script>
			<div class="ui-resizable-handle ui-resizable-e"></div>
		</div>
		<input type="hidden" id="selectId" name="selectId" value="${parentId}" /> <input type="hidden" id="selectIdTreeId" name="selectIdTreeId" />
		<!-- left -->
		<div id="right">
			<div class="right_menu">
				<ul>
					<li class="current"><a onclick="javascript:tab(2,1,'a',event)"><img src="img/common_listx16.png" alt="" />明细</a></li>
					<li><a onclick="javascript:$('#a2').attr('style','');tab(2,2,'a',event)"><img src="img/common_listx16.png" alt="" />视图</a></li>
				</ul>
			</div>
			<div id="a1" class="right_content" style="position: relative; z-index: 1; background: #FFF;">
				<div style="padding: 8px;">
					<table id="dlLineItem" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlLineItemTb',url: '${vix}/purchase/purchasePlanAction!getPurchasePlanItemsJson.action?id=${purchasePlan.id}',onClickRow: onDlClickRow1">
						<thead>
							<tr>
								<th data-options="field:'id',align:'center',width:120">编号</th>
								<th data-options="field:'itemCode',align:'center',width:120,editor:'text'">编码</th>
								<th data-options="field:'itemName',width:200,align:'center',editor:'text'">考勤时间</th>
								<th data-options="field:'itemType',width:200,align:'center',editor:'text'">类型</th>
								<th data-options="field:'amount',width:200,align:'center',editor:'numberbox'">审核人</th>
								<th data-options="field:'price',width:200,align:'center',editor:'numberbox'">审核时间</th>
							</tr>
						</thead>
					</table>
					<div id="dlLineItemTb" style="height: auto">
						<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="appendDlLineItem()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
							data-options="iconCls:'icon-remove',plain:true" onclick="removeDlLineItem()"><span class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-save',plain:true"
							onclick="saveDlLineItem()"><span class="l-btn-left"><span class="l-btn-text icon-save l-btn-icon-left"><s:text name="cmn_save" /></span></span></a>
					</div>
					<script type="text/javascript">
							$('#dlLineItem').datagrid();
							var editIndexDlLineItem = undefined;
							function endDlEditing1(){
								if (editIndexDlLineItem == undefined){return true;}
								if ($('#dlLineItem').datagrid('validateRow', editIndexDlLineItem)){
									$('#dlLineItem').datagrid('endEdit', editIndexDlLineItem);
									editIndexDlLineItem = undefined;
									return true;
								} else {
									return false;
								}
							}
							function onDlClickRow1(index){
								if (editIndexDlLineItem != index){
									if (endDlEditing1()){
										$('#dlLineItem').datagrid('selectRow', index).datagrid('beginEdit', index);
										editIndexDlLineItem = index;
									} else {
										$('#dlLineItem').datagrid('selectRow', editIndexDlLineItem);
									}
								}
							}
							function appendDlLineItem(){
								if (endDlEditing1()){
									$('#dlLineItem').datagrid('appendRow',{status:'P'});
									editIndexDlLineItem = $('#dlLineItem').datagrid('getRows').length-1;
									$('#dlLineItem').datagrid('selectRow', editIndexDlLineItem).datagrid('beginEdit', editIndexDlLineItem);
								}
							}
							function removeDlLineItem(){
								if (editIndexDlLineItem == undefined){return;}
								$('#dlLineItem').datagrid('cancelEdit', editIndexDlLineItem)
										.datagrid('deleteRow', editIndexDlLineItem);
								editIndexDlLineItem = undefined;
							}
							function saveDlLineItem(){
								if (endDlEditing1()){
									$('#dlLineItem').datagrid('acceptChanges');
								}
							}
					</script>
				</div>
			</div>
			<div id="a2" class="right_content" style="display: none; overflow: hidden; height: 0;">
				<div id='calendar'></div>

			</div>
		</div>
		<!-- right -->
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<!-- c_foot -->
</div>
<script type="text/javascript">
<!--
	//面包屑
	if($('.sub_menu').length)
	{
		$("#breadCrumb").jBreadCrumb();
	}
	$("#switch_box").toggle(
		  function () {
			$("#switch_box").addClass("off");
			$("#left").addClass("switch");
			$("#right").addClass("switch");
			$(".left_head").addClass("wid7px");
			$(".left_content").addClass("current");
			$("#left").css({ "width": "7px"});
		  },
		  function () {
			$("#switch_box").removeClass("off");
			$("#left").removeClass("switch");
			$("#right").removeClass("switch");
			$(".left_head").removeClass("wid7px");
			$(".left_content").removeClass("current");
			$("#left").css({ "width": "252px"});
		  }
		)
	var date = new Date();
	var d = date.getDate();
	var m = date.getMonth();
	var y = date.getFullYear();
	
	var calendar = $('#calendar').fullCalendar({
		header: {
			left: 'prev,next today',
			center: 'title',
			right: 'month,agendaWeek,agendaDay'
		},
		selectable: true,
		selectHelper: true,
		select: function(start, end, allDay) {
			var title = 'test';
			$.ajax({
				  url:'${vix}/template/simpleGridAction!goSaveOrUpdate.action?id='+1,
				  cache: false,
				  success: function(html){
					  asyncbox.open({
						 	modal:true,
							width : 880,
							height : 360,
							title:"品牌",
							html:html,
							callback : function(action){
								if(action == 'ok'){
									if (title) {
										calendar.fullCalendar('renderEvent',
											{
												title: title,
												start: start,
												end: end,
												allDay: allDay
											},
											true // make the event "stick"
										);
									}
									calendar.fullCalendar('unselect');
								}
							},
							btnsbar : $.btn.OKCANCEL
						});
				  }
			});
		},
		editable: true,
		events: [
			{
				title: 'All Day Event',
				start: new Date(y, m, 1)
			},{
				title: 'Long Event',
				start: new Date(y, m, d-5),
				end: new Date(y, m, d-2)
			},{
				id: 999,
				title: 'Repeating Event',
				start: new Date(y, m, d-3, 16, 0),
				allDay: false
			},{
				id: 999,
				title: 'Repeating Event',
				start: new Date(y, m, d+4, 16, 0),
				allDay: false
			},{
				title: 'Meeting',
				start: new Date(y, m, d, 10, 30),
				allDay: false
			},{
				title: 'Lunch',
				start: new Date(y, m, d, 12, 0),
				end: new Date(y, m, d, 14, 0),
				allDay: false
			},{
				title: 'Birthday Party',
				start: new Date(y, m, d+1, 19, 0),
				end: new Date(y, m, d+1, 22, 30),
				allDay: false
			},{
				title: 'Click for Google',
				start: new Date(y, m, 28),
				end: new Date(y, m, 29),
				url: 'http://google.com/'
			}
		]
	});
//-->
</script>