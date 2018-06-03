<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<script src="${vix}/common/js/ajaxfileupload.js" type="text/javascript"></script>
<script src="${vix}/common/core/js/all.js" type="text/javascript"></script>
<script type="text/javascript">
	$(".addtable .addtable_t").click(function(){
		$(this).toggleClass("addtable_td");
		$(this).next(".addtable_c").toggle();
	});
	$(".newvoucher dt b").click(function(){
		$(this).toggleClass("downup");
		$(this).parent("dt").next("dd").toggle();
	});
	$(".order_table input[type='text']").focusin( function() {
	   $(this).css({'border':'1px solid #f00','background':'#f5f5f5'});
	});
	$(".order_table  input[type='text']").focusout( function() {
	   $(this).css({'border':'1px solid #ccc','background':'#fff'});
	});
function resize(){
	$('#test').datagrid('resize', {
		width:700,
		height:400
	});
}
function getSelected(){
	var selected = $('#test').datagrid('getSelected');
	if (selected){
		alert(selected.code+":"+selected.name+":"+selected.addr+":"+selected.col4);
	}
}
function getSelections(){
	var ids = [];
	var rows = $('#test').datagrid('getSelections');
	for(var i=0;i<rows.length;i++){
		ids.push(rows[i].code);
	}
	alert(ids.join(':'));
}
function clearSelections(){
	$('#test').datagrid('clearSelections');
}
function selectRow(){
	$('#test').datagrid('selectRow',2);
}
function selectRecord(){
	$('#test').datagrid('selectRecord','002');
}
function unselectRow(){
	$('#test').datagrid('unselectRow',2);
}
function mergeCells(){
	$('#test').datagrid('mergeCells',{
		index:2,
		field:'addr',
		rowspan:2,
		colspan:2
	});
}

//提示
if($('input.sweet-tooltip').length){
	$('input.sweet-tooltip').focus(function() {
		tooltip				= $(this);
		tooltipText 		= tooltip.attr('data-text-tooltip');
		tooltipClassName	= 'tooltip';
		tooltipClass		= '.' + tooltipClassName;
		if(tooltip.hasClass('showed-tooltip')) return false;
		tooltip.addClass('showed-tooltip')
				 .after('<div class="'+tooltipClassName+'"><div class="tooltip_l"></div><div class="tooltip_r"></div><div class="tooltip_x">'+tooltipText+'</div><div class="tooltip_b"></div></div>');
		tooltipPosTop 	= tooltip.position().top - $(tooltipClass).outerHeight() - 10;
		tooltipPosLeft = tooltip.position().left;
		tooltipPosLeft = tooltipPosLeft - (($(tooltipClass).outerWidth()/2) - tooltip.outerWidth()/2);
		$(tooltipClass).css({ 'left': tooltipPosLeft, 'top': tooltipPosTop }).animate({'opacity':'1', 'marginTop':'0'}, 500);
		
	}).focusout(function() {
		$(tooltipClass).animate({'opacity':'0', 'marginTop':'-10px'}, 500, function() {
			$(this).remove();
			tooltip.removeClass('showed-tooltip');
				
		});
	});
	JT_init();
}
$("table tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("table tr:even").addClass("alt");
/** 保存培训需求*/
function saveOrUpdateOrder(){
	var orderItemStr = "";
	if($('#replanForm').validationEngine('validate')){
		$.post('${vix}/hr/trainingActivityAction!saveOrUpdate.action',
			{
			'trainingActivity.id':$("#id").val(),
			'trainingActivity.activityCode':$("#activityCode").val(),
			'trainingActivity.activityName':$("#activityName").val(),
			'trainingActivity.examinationApproval':$("#examinationApproval").val(),
			'trainingActivity.activityDepartmentOrLeadings':$("#activityDepartmentOrLeadings").val(),
			'trainingActivity.pubIds' : $("#pubIds").val()+',',
			'trainingActivity.pubType':$('input:radio[name=pubType]:checked').val(),
			'trainingActivity.trainingTime':$("#trainingTime").val(),
			'trainingActivity.budgetExpense':$("#budgetExpense").val(),
			'trainingActivity.activityStartDate':$("#activityStartDate").val(),
			'trainingActivity.activityEndDate':$("#activityEndDate").val(),
			'trainingActivity.trainingContent':$("#trainingContent").val(),
			'trainingActivity.trainingMethod':$('input:radio[name=trainingMethod]:checked').val(),
			'trainingActivity.examinationMethod':$('input:radio[name=examinationMethod]:checked').val(),
			'orderItemStr':orderItemStr
			},
			function(result){
				showMessage(result);
				setTimeout("",1000);
				loadContent('${vix}/hr/trainingActivityAction!goList.action?menuId=menuOrder');
			}
		 );
	}
}

/** 保存并新增培训需求*/
function saveOrAdd(){
	var orderItemStr = "";
	if($('#replanForm').validationEngine('validate')){
		$.post('${vix}/hr/trainingActivityAction!saveOrUpdate.action',
			{
			'trainingActivity.id':$("#id").val(),
			'trainingActivity.activityCode':$("#activityCode").val(),
			'trainingActivity.activityName':$("#activityName").val(),
			'trainingActivity.examinationApproval':$("#examinationApproval").val(),
			'trainingActivity.activityDepartmentOrLeadings':$("#activityDepartmentOrLeadings").val(),
			'trainingActivity.pubIds' : $("#pubIds").val()+',',
			'trainingActivity.pubType':$('input:radio[name=pubType]:checked').val(),
			'trainingActivity.trainingTime':$("#trainingTime").val(),
			'trainingActivity.budgetExpense':$("#budgetExpense").val(),
			'trainingActivity.activityStartDate':$("#activityStartDate").val(),
			'trainingActivity.activityEndDate':$("#activityEndDate").val(),
			'trainingActivity.trainingContent':$("#trainingContent").val(),
			'trainingActivity.trainingMethod':$('input:radio[name=trainingMethod]:checked').val(),
			'trainingActivity.examinationMethod':$('input:radio[name=examinationMethod]:checked').val(),
			'orderItemStr':orderItemStr
			},
			function(result){
				showMessage(result);
				setTimeout("",1000);
				loadContent('${vix}/hr/trainingActivityAction!goSaveOrUpdate.action');
			}
		 );
	}
}

//页面首次加载
$(function(){
	//默认选择部门
    if($('input:radio[name=pubType]:checked').length==0){
    	$('input:radio[name=pubType]:first').trigger('click');
    }
});

/**
 * 变更选择发布类型
 */
function changePubType(pubTypeValue){
	clearPubType();
}

/**
 * 清空选择对象
 */
function clearPubType(){
	$("#pubIds").val("");
	$("#activityDepartmentOrLeadings").val("");
}

/**
 * 添加发布对象
 */
function addBulletinPubobject(){
	
	var pubTypeVal = $("input[name='pubType']:checked").val();
	//debugger;
	if(pubTypeVal=="O"){
		chooseBulletinOrgUnit($("#pubIds").val());
	}else if(pubTypeVal=="E"){
		chooseBulletinEmp($("#pubIds").val());
	}
}

/**
 * 选择负责部门
 */
 function chooseBulletinOrgUnit(){
		$.ajax({
			  url:'${vix}/common/select/commonSelectOrgUnitAction!goChooseOrgUnit.action',
			  data:{chkStyle:"checkbox",canCheckComp:0},
			  cache: false,
			  success: function(html){
				  asyncbox.open({
					 	modal:true,
						width : 560,
						height : 340,
						title:"选择负责部门",
						html:html,
						callback : function(action,returnValue){
							if(action == 'ok'){
								if(returnValue != undefined){
									//alert(returnValue);
									var selectIds = "";
									var selectNames = "";
								
									var resObj = $.parseJSON(returnValue);
									
									for(var i=0;i<resObj.length;i++){
										selectIds += "," + resObj[i].treeId;
										selectNames += "," + resObj[i].name;
									}

									if(selectIds!=''){
										selectIds = selectIds.substring(1);
										selectNames = selectNames.substring(1);
										//alert(selectIds)
										$("#pubIds").val(selectIds);
										$("#activityDepartmentOrLeadings").val(selectNames);
									}
								}
								
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	}
 

/**
 * 选择负责人员
 */
function chooseBulletinEmp(checkObjIds){
	$.ajax({
		  url:'${vix}/common/select/commonSelectEmpAction!goList.action',
		  data:{chkStyle:"checkbox"},
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 660,
					height : 340,
					title:"选择负责人员",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								//alert(returnValue);
								var selectIds = "";
								var selectNames = "";
							
								var pubIdTmp = $("#pubIds").val();
								
								pubIdTmp = pubIdTmp + ",";
								
								/* if(resObj.length == 0 ){
									return;
								} */
								//debugger;
								var result = returnValue.split(",");
								for (var i=0; i<result.length; i++){
									if(result[i].length>1){
										var v = result[i].split(":");
										if(!pubIdTmp.contains(v[0]+",")){
											selectIds += "," + v[0];
											selectNames += "," + v[1];
										}
									}
								}
								
								selectIds = $("#pubIds").val()+selectIds;
								selectNames = $("#activityDepartmentOrLeadings").val()+selectNames;
								
								$("#pubIds").val(selectIds);
								selectNames = selectNames.substring(1,selectNames.length);
								$("#activityDepartmentOrLeadings").val(selectNames);
							}
							
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}

$("#replanForm").validationEngine();
if($(".ms").length>0){
	$(".ms").hover(function(){
		$(">ul",this).stop().slideDown(100);
	},function(){
		$(">ul",this).stop().slideUp(100);
	});
	$(".ms ul li").hover(function(){
		$(">a",this).addClass("hover");
		$(">ul",this).stop().slideDown(100);
	},function(){
		$(">a",this).removeClass("hover");
		$(">ul",this).stop().slideUp(100);
	});
}
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/hr_training.png" alt="" />
					<s:text name="hr_humanr_resources" /></a></li>
				<li><a href="#" onclick="loadContent('${vix}/hr/trainingActivityAction!goList.action');">教育培训</a></li>
				<li><a href="#" onclick="loadContent('${vix}/hr/trainingActivityAction!goList.action');">培训活动</a></li>
				<li><a href="#" onclick="loadContent('${vix}/hr/trainingActivityAction!goList.action');">新增培训活动</a></li>
			</ul>
		</div>
	</h2>
</div>

<div class="content">
	<form id="replanForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateOrder();"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="javascript:void(0)" onclick="saveOrAdd()"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/hr/trainingActivityAction!goList.action');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a>
					</span> <strong><b><s:text name="新增计划活动" /> </b><i></i> </strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b> <span style="display: none;"> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />
										<s:text name="calculator" /></a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />
										<s:text name="calculator" /></a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />
										<s:text name="calculator" /></a>
									</span> <strong>基本信息</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<%--检查ID，判断修改--%>
											<input type="hidden" id="id" name="id" value="${trainingActivity.id}" />
											<td align="right">活动编码：</td>
											<td><input name="activityCode" id="activityCode" type="text" size="30" value="${trainingActivity.activityCode }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">活动名称：</td>
											<td><input name="activityName" id="activityName" type="text" size="30" value="${trainingActivity.activityName }" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">活动部门或负责人：</td>
											<td><input type="hidden" id="id" name="id" value="${trainingActivity.id}" /> <s:radio id="pubType" list="#{'O':'负责部门','E':'负责人'}" name="pubType" value="%{trainingActivity.pubType}" onchange="changePubType(this.value);" theme="simple"></s:radio> <a href="javascript:void(0);" onclick="addBulletinPubobject()"><img
													src="img/icon_25.gif" />新增</a>&nbsp;&nbsp; <a href="#" onclick="clearPubType()"><img src="img/delete.gif" />清空</a> <br /> <textarea rows="6" cols="6" id="activityDepartmentOrLeadings" name="activityDepartmentOrLeadings" style="width: 250px; height: 103px;">${trainingActivity.activityDepartmentOrLeadings}</textarea> <input type="hidden"
												id="pubIds" name="trainingActivity.pubIds" value="${trainingActivity.pubIds}" /></td>
											<td align="right">培训内容：</td>
											<td colspan="2"><textarea id="trainingContent" name="trainingContent" class="example" rows="6" style="width: 250px; height: 103px;">${trainingActivity.trainingContent }</textarea></td>
										</tr>
										<tr>
											<td align="right">拟培训日期：</td>
											<td><input id="trainingTime" name="trainingTime" value="<fmt:formatDate value='${trainingActivity.trainingTime }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('trainingTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
											<td align="right">预算费用：</td>
											<td><input name="budgetExpense" id="budgetExpense" type="text" size="30" value="${trainingActivity.budgetExpense }" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">活动开始时间：</td>
											<td><input id="activityStartDate" name="trainingActivity.activityStartDate" value="${trainingActivity.activityStartDate}" onchange="salesOrderFieldChanged(this);" type="text" class="validate[required] text-input" /> <img onclick="showTime('activityStartDate','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16"
												height="22" align="absmiddle"></td>
											<td align="right">活动结束时间：</td>
											<td><input id="activityEndDate" name="activityEndDate" value="${trainingActivity.activityEndDate}" onchange="salesOrderFieldChanged(this);" type="text" class="validate[required] text-input" /> <img onclick="showTime('activityEndDate','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22"
												align="absmiddle"></td>
										</tr>
										<tr>
											<th align="right">培训方式：</th>
											<td><s:radio list="#{'0':'学习','1':'授课'}" name="trainingMethod" value="%{trainingActivity.trainingMethod}" theme="simple"></s:radio></td>
											<th align="right">考核方式：</th>
											<td><s:radio list="#{'0':'笔记','1':'评价'}" name="examinationMethod" value="%{trainingActivity.examinationMethod}" theme="simple"></s:radio></td>
										</tr>
									</table>
									<script type="text/javascript">
									//为原始Date类型拓展format一个方法，用于日期显示的格式化
										Date.prototype.format = function (format) 
										 {
										     var o = {
										         "M+": this.getMonth() + 1, //month 
										         "d+": this.getDate(),    //day 
										         "h+": this.getHours(),   //hour 
										         "m+": this.getMinutes(), //minute 
										         "s+": this.getSeconds(), //second 
										         "q+": Math.floor((this.getMonth() + 3) / 3),  //quarter 
										         "S": this.getMilliseconds() //millisecond 
										     }
										     if (/(y+)/.test(format)) format = format.replace(RegExp.$1,
										     (this.getFullYear() + "").substr(4 - RegExp.$1.length));
										     for (var k in o) if (new RegExp("(" + k + ")").test(format))
										         format = format.replace(RegExp.$1,
										       RegExp.$1.length == 1 ? o[k] :
										         ("00" + o[k]).substr(("" + o[k]).length));
										     return format;
										 }
										
										//格式化日期
										function formatDatebox(value) {
									         if (value == null || value == '') {
									             return '';
									         }
									     var dt;
									     if (value instanceof Date) {
									         dt = value;
									     }
									     else {
									         dt = new Date(value);
									         if (isNaN(dt)) {
									             value = value.replace(/\/Date\((-?\d+)\)\//, '$1'); //标红的这段是关键代码，将那个长字符串的日期值转换成正常的JS日期格式
									             dt = new Date();
									             dt.setTime(value);
									         }
									     }
									 
									    return dt.format("yyyy-MM-dd");  //这里用到一个javascript的Date类型的拓展方法，这个是自己添加的拓展方法，在后面的步骤3定义
									 }
							 </script>
								</dd>
						</div>
					</div>
				</dd>
				<div class="clearfix" style="background: #FFF; position: relative;">
					<div class="right_menu">
						<ul>
							<li class="current"><a onclick="javascript:tab(1,1,'a',event);"><img src="${vix}/common/img/mail.png" alt="" />培训计划明细</a></li>
						</ul>
					</div>
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<div style="padding: 8px;">
							<table id="dlLineItem" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="rownumbers:true,pagination: true,iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlLineItemTb',url: '${vix}/hr/trainingActivityAction!getTrainingPlanningDetailJson.action?id=${trainingActivity.id}'">
								<thead>
									<tr>
										<th data-options="field:'trainingWay',align:'center',width:120,editor:'text'">培训方式</th>
										<th data-options="field:'goalOfTraining',align:'center',width:120,editor:'text'">培训目标</th>
										<th data-options="field:'trainingContent',width:200,align:'center',editor:'text'">培训内容</th>
										<th data-options="field:'trainingCourse',width:120,align:'center',editor:'text'">培训课程</th>
										<th data-options="field:'trainingSite',width:120,align:'center',editor:'text'">培训地点</th>
										<th data-options="field:'trainingInstitutions',width:120,align:'center',editor:'text'">培训机构</th>
										<th data-options="field:'sourcesOfTeachers',width:120,align:'center',editor:'text'">师资来源</th>
										<th data-options="field:'curriculumClassHours',width:120,align:'center',editor:'text'">课程学时</th>
										<th data-options="field:'planStartDate',width:200,align:'center',editor:'databox',formatter:formatDatebox">计划开始日期</th>
										<th data-options="field:'planEndDate',width:200,align:'center',editor:'databox',formatter:formatDatebox">计划结束日期</th>
									</tr>
								</thead>
							</table>
							<div id="dlLineItemTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="baddItem()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
									data-options="iconCls:'icon-edit',plain:true" onclick="beditItem()"><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left">修改</span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-remove',plain:true" onclick="bremoveDlLineItem()"><span
									class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a>
							</div>
							<script type="text/javascript">
								$('#dlLineItem').datagrid();  
								function baddItem(){
									$.ajax({
										  url:'${vix}/hr/trainingActivityAction!goChooseTrainingPlanningDetail.action',
										  cache: false,
										  success: function(html){
											  $(".ab_outer .list td input[type='checkbox']").css("margin-left",10);
											  $(".ab_c .content").css("margin-bottom","0");
											  $('.ab_c .content').parent().css('position','relative');
											  asyncbox.open({
												 	modal:true,
													width : 1000,
													height : 500,
													title:"选择培训活动",
													html:html,
													callback : function(action,returnValue){
														if(action == 'ok'){
															if(returnValue != undefined){
																var rv = returnValue.split(",");
																$.ajax({
																	  url:'${vix}/hr/trainingActivityAction!saveOrUpdateTrainingPlanningDetail.action?tcId='+rv[0]+"&id=${trainingActivity.id}",
																	  cache: false,
																	  success: function(html){
																		  showMessage(html);
																		  setTimeout("",1000);
																		  $('#dlLineItem').datagrid("reload");
																	  }
																});
															}else{
																asyncbox.success("请选择培训活动!","提示信息");
															}
														}
													},
													btnsbar : $.btn.OKCANCEL
												});
										  }
									});
								}
								function beditItem(){
									var rows = $('#dlLineItem').datagrid('getSelected');
									if(null == rows){
										alert("请选择一条数据！");
										return;
									}
									$.ajax({
										  url:'${vix}/hr/trainingActivityAction!goChooseTrainingPlanningDetail.action?lineItemId='+rows.id,
										  cache: false,
										  success: function(html){
											  asyncbox.open({
												 	modal:true,
													width : 700,
													height : 400,
													title:"添加培训活动",
													html:html,
													callback : function(action){
														if(action == 'ok'){
															if($('#orderItemForm').validationEngine('validate')){
																$.post('${vix}/hr/trainingActivityAction!saveOrUpdateTrainingPlanningDetail.action', {
																  'id' : $("#id").val(),
																  'trainingPlanningDetail.id':$("#daId").val(),
																  'trainingPlanningDetail.trainingWay':$("#trainingWay").val(),
																  'trainingPlanningDetail.goalOfTraining':$("#goalOfTraining").val(),
																  'trainingPlanningDetail.trainingContent':$("#trainingContent").val(),
																  'trainingPlanningDetail.trainingCourse':$("#trainingCourse").val(),
																  'trainingPlanningDetail.trainingSite':$("#trainingSite").val(),
																  'trainingPlanningDetail.trainingInstitutions':$("#trainingInstitutions").val(),
																  'trainingPlanningDetail.sourcesOfTeachers':$("#sourcesOfTeachers").val(),
																  'trainingPlanningDetail.curriculumClassHours':$("#curriculumClassHours").val(),
																  'trainingPlanningDetail.planStartDate':$("#planStartDate").val(),
																  'trainingPlanningDetail.planEndDate':$("#planEndDate").val()
																}, function(result){
																	showMessage(result);
																	setTimeout("",1000);
																	$('#dlLineItem').datagrid("reload");
																});
															}else{
											  					return false;
											  				}
														}
													},
													btnsbar : $.btn.OKCANCEL
												});
										  }
									});
								}
								function bremoveDlLineItem(){
									var row = $('#dlLineItem').datagrid('getSelected');
									if(row){
										var index = $('#dlLineItem').datagrid('getRowIndex',row);
										$('#dlLineItem').datagrid('deleteRow', index);
									}
								}
						</script>
						</div>
					</div>

				</div>
			</dl>
		</div>
	</form>
</div>