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
		$.post('${vix}/hr/demandApplyAction!saveOrUpdate.action',
			{
			'demandApply.id':$("#id").val(),
			'demandApply.applicationName':$("#applicationName").val(),
			'demandApply.applicationDate':$("#applicationDate").val(),
			'demandApply.departmentOrPersonnel':$("#departmentOrPersonnel").val(),
			'demandApply.pubIds' : $("#pubIds").val()+',',
			'demandApply.pubType':$('input:radio[name=pubType]:checked').val(),
			'demandApply.applyForDepartmentHeads':$("#applyForDepartmentHeads").val(),
			'demandApply.nameOfTrainingCourse':$("#nameOfTrainingCourse").val(),
			'demandApply.courseCategory':$("#courseCategory").val(),
			'demandApply.courseDescription':$("#courseDescription").val(),
			'demandApply.courseObjective':$("#courseObjective").val(),
			'demandApply.proposedTrainingInstitutions':$("#proposedTrainingInstitutions").val(),
			'demandApply.trainingInstructor':$("#trainingInstructor").val(),
			'demandApply.contactway':$("#contactway").val(),
			'demandApply.numberOfParticipants':$("#numberOfParticipants").val(),
			'demandApply.remarks':$("#remarks").val(),
			'demandApply.expectStartDate':$("#expectStartDate").val(),
			'demandApply.expectEndDate':$("#expectEndDate").val(),
			'orderItemStr':orderItemStr
			},
			function(result){
				showMessage(result);
				setTimeout("",1000);
				loadContent('${vix}/hr/demandApplyAction!goList.action?menuId=menuOrder');
			}
		 );
	}
}

/** 保存并新增培训需求*/
function saveOrAdd(){
	var orderItemStr = "";
	if($('#replanForm').validationEngine('validate')){
		$.post('${vix}/hr/demandApplyAction!saveOrUpdate.action',
			{
			'demandApply.id':$("#id").val(),
			'demandApply.applicationName':$("#applicationName").val(),
			'demandApply.applicationDate':$("#applicationDate").val(),
			'demandApply.applyForDepartmentHeads':$("#applyForDepartmentHeads").val(),
			'demandApply.departmentOrPersonnel':$("#departmentOrPersonnel").val(),
			'demandApply.pubIds' : $("#pubIds").val()+',',
			'demandApply.pubType':$('input:radio[name=pubType]:checked').val(),
			'demandApply.nameOfTrainingCourse':$("#nameOfTrainingCourse").val(),
			'demandApply.courseCategory':$("#courseCategory").val(),
			'demandApply.courseDescription':$("#courseDescription").val(),
			'demandApply.courseObjective':$("#courseObjective").val(),
			'demandApply.proposedTrainingInstitutions':$("#proposedTrainingInstitutions").val(),
			'demandApply.trainingInstructor':$("#trainingInstructor").val(),
			'demandApply.contactway':$("#contactway").val(),
			'demandApply.numberOfParticipants':$("#numberOfParticipants").val(),
			'demandApply.remarks':$("#remarks").val(),
			'demandApply.expectStartDate':$("#expectStartDate").val(),
			'demandApply.expectEndDate':$("#expectEndDate").val(),
			'orderItemStr':orderItemStr
			},
			function(result){
				showMessage(result);
				setTimeout("",1000);
				loadContent('${vix}/hr/demandApplyAction!goSaveOrUpdate.action');
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
	$("#departmentOrPersonnel").val("");
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
 * 选择申请部门
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
						title:"选择申请部门",
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
										$("#departmentOrPersonnel").val(selectNames);
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
 * 选择申请人员
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
					title:"选择申请人员",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								//alert(returnValue);
								var selectIds = "";
								var selectNames = "";
							
								var pubIdTmp = $("#pubIds").val();
								
								pubIdTmp = pubIdTmp + ",";
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
								selectNames = $("#departmentOrPersonnel").val()+selectNames;
								
								$("#pubIds").val(selectIds);
								selectNames = selectNames.substring(1,selectNames.length);
								$("#departmentOrPersonnel").val(selectNames);
							}
							
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}

function chooseEmployees(){
	$.ajax({
		  url:'${vix}/common/select/commonSelectEmpAction!goList.action',
		  data:{chkStyle:"checkbox"},
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 800,
					height : 600,
					title:"选择培训教师",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var selectIds = "";
								var selectNames = "";
								var result = returnValue.split(",");
								for (var i=0; i<result.length; i++){
									if(result[i].length>1){
										var v = result[i].split(":");
										
										selectIds += "," + v[0];
										selectNames = v[1];
									}
								}
								$("#courseDescription").val(selectNames);
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
				<li><a href="#">教育培训</a></li>
				<li><a href="#" onclick="loadContent('${vix}/hr/demandApplyAction!goList.action');">培训需求</a></li>
				<li><a href="#" onclick="loadContent('${vix}/hr/demandApplyAction!goList.action');">培训需求申请</a></li>
				<li><a href="#" onclick="loadContent('${vix}/hr/demandApplyAction!goList.action');">新增培训需求申请</a></li>
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
						onclick="loadContent('${vix}/hr/demandApplyAction!goList.action');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a>
					</span> <strong><b><s:text name="新增培训需求申请" /> </b><i></i> </strong>
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
											<input type="hidden" id="id" name="id" value="${demandApply.id}" />
											<td align="right">申请名称：</td>
											<td><input id="applicationName" name="applicationName" value="${demandApply.applicationName }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">申请日期：</td>
											<td><input id="applicationDate" name="applicationDate" value="<fmt:formatDate value='${demandApply.applicationDate }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('applicationDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<td align="right">申请部门或人员：</td>
											<td><input type="hidden" id="id" name="id" value="${demandApply.id}" /> <s:radio id="pubType" list="#{'O':'申请部门','E':'申请人'}" name="pubType" value="%{demandApply.pubType}" onchange="changePubType(this.value);" theme="simple"></s:radio> <a href="javascript:void(0);" onclick="addBulletinPubobject()"><img src="img/icon_25.gif" />新增</a>&nbsp;&nbsp;
												<a href="#" onclick="clearPubType()"><img src="img/delete.gif" />清空</a> <br /> <textarea rows="6" cols="6" id="departmentOrPersonnel" name="departmentOrPersonnel" style="width: 250px; height: 103px;">${demandApply.departmentOrPersonnel}</textarea> <input type="hidden" id="pubIds" name="demandApply.pubIds" value="${demandApply.pubIds}" />
											</td>
											<td align="right">需求描述:</td>
											<td colspan="2"><textarea id="remarks" name="remarks" class="example" rows="6" style="width: 250px; height: 103px;">${demandApply.remarks }</textarea></td>
										</tr>
										<tr>
											<td align="right">培训方式：</td>
											<td><input name="courseCategory" id="courseCategory" type="text" size="30" value="${demandApply.courseCategory}" /></td>
											<td align="right">培训目标：</td>
											<td><input name="courseObjective" id="courseObjective" type="text" size="30" value="${demandApply.courseObjective} " /></td>
										</tr>
										<tr>
											<td align="right">建议培训机构：</td>
											<td><input name="proposedTrainingInstitutions" id="proposedTrainingInstitutions" type="text" size="30" value="${demandApply.proposedTrainingInstitutions}" /></td>
											<td align="right">建议培训讲师：</td>
											<td><input id="courseDescription" name="courseDescription" value="${demandApply.courseDescription }" type="text" size="30" class="validate[required] text-input" /> <input type="hidden" id="teacherid" name="teacherid" value="${demandApply.teacherid }" /> <input class="btn" type="button" value="选择" onclick="chooseEmployees();" /> <span
												style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">联系方式：</td>
											<td><input name="contactway" id="contactway" type="text" size="30" value="${demandApply.contactway}" /></td>
											<td align="right">参与人数：</td>
											<td><input name="numberOfParticipants" id="numberOfParticipants" type="text" size="30" value="${demandApply.numberOfParticipants} " /></td>
										</tr>
										<tr>
											<td align="right">期望开始日期：</td>
											<td><input id="expectStartDate" name="expectStartDate" value="<fmt:formatDate value='${demandApply.expectStartDate }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('expectStartDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
											<td align="right">期望结束日期：</td>
											<td><input id="expectEndDate" name="expectEndDate" value="<fmt:formatDate value='${demandApply.expectEndDate }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('expectEndDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
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
							<li class="current"><a onclick="javascript:tab(3,1,'a',event);"><img src="${vix}/common/img/mail.png" alt="" />培训课程</a></li>
							<li><a onclick="javascript:$('#a2').attr('style','');tab(3,2,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />培训设施</a></li>
							<li><a onclick="javascript:$('#a3').attr('style','');tab(3,3,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />培训资料</a></li>
						</ul>
					</div>
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<div style="padding: 8px;">
							<table id="dlLineItem" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="rownumbers:true,pagination: true,iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlLineItemTb',url: '${vix}/hr/demandApplyAction!getTrainingCourseJson.action?id=${demandApply.id}'">
								<thead>
									<tr>
										<th data-options="field:'courseCode',align:'center',width:120,editor:'text'">课程编码</th>
										<th data-options="field:'courseName',width:200,align:'center',editor:'text'">课程名称</th>
										<th data-options="field:'courseType',width:120,align:'center',editor:'text'">课程类别</th>
										<th data-options="field:'courseeducation',width:120,align:'center',editor:'text'">课程内容</th>
										<th data-options="field:'needs',width:120,align:'center',editor:'text'">需要课时</th>
										<th data-options="field:'courseFees',width:120,align:'center',editor:'text'">课程费用</th>
										<th data-options="field:'natureCourse',width:120,align:'center',editor:'text'">课程性质</th>
										<th data-options="field:'effectiveStartDate',width:200,align:'center',editor:'databox',formatter:formatDatebox">有效开始日期</th>
										<th data-options="field:'effectiveEndDate',width:200,align:'center',editor:'databox',formatter:formatDatebox">有效结束日期</th>
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
										  url:'${vix}/hr/demandApplyAction!goAddDemandCourse.action',
										  cache: false,
										  success: function(html){
											  $(".ab_outer .list td input[type='checkbox']").css("margin-left",10);
											  $(".ab_c .content").css("margin-bottom","0");
											  $('.ab_c .content').parent().css('position','relative');
											  asyncbox.open({
												 	modal:true,
													width : 1000,
													height : 500,
													title:"选择培训课程",
													html:html,
													callback : function(action,returnValue){
														if(action == 'ok'){
															if(returnValue != undefined){
																var rv = returnValue.split(",");
																$.ajax({
																	  url:'${vix}/hr/demandApplyAction!saveOrUpdateTrainingCourse.action?tcId='+rv[0]+"&id=${demandApply.id}",
																	  cache: false,
																	  success: function(html){
																		  showMessage(html);
																		  setTimeout("",1000);
																		  $('#dlLineItem').datagrid("reload");
																	  }
																});
															}else{
																asyncbox.success("请选择讲师!","提示信息");
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
										  url:'${vix}/hr/demandApplyAction!goAddDemandCourse.action?lineItemId='+rows.id,
										  cache: false,
										  success: function(html){
											  asyncbox.open({
												 	modal:true,
													width : 700,
													height : 400,
													title:"添加培训课程",
													html:html,
													callback : function(action){
														if(action == 'ok'){
															if($('#orderItemForm').validationEngine('validate')){
																$.post('${vix}/hr/demandApplyAction!saveOrUpdateTrainingCourse.action', {
																	'id' : $("#id").val(),
																	'trainingCourse.id' : $("#oiId").val(),
																	'trainingCourse.courseCode' : $("#courseCode").val(),
																	'trainingCourse.courseName' : $("#courseName").val(),
																	'trainingCourse.courseType' : $("#courseType").val(),
																	'trainingCourse.courseeducation' : $("#courseeducation").val(),
																	'trainingCourse.needs' : $("#needs").val(),
																	'trainingCourse.courseFees' : $("#courseFees").val(),
																	'trainingCourse.natureCourse' : $("#natureCourse").val(),
																	'trainingCourse.effectiveStartDate' : $("#effectiveStartDate").val(),
																	'trainingCourse.effectiveEndDate' : $("#effectiveEndDate").val()
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
					<div id="a2" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<div style="padding: 8px;">
							<table id="dlLineItems" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="rownumbers:true,pagination: true,iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlLineItemsTb',url: '${vix}/hr/demandApplyAction!getTrainingFacilitiesJson.action?id=${demandApply.id}'">
								<thead>
									<tr>
										<th data-options="field:'facilitiesCode',align:'center',width:120,editor:'text'">设施编码</th>
										<th data-options="field:'facilitiesType',width:200,align:'center',editor:'text'">设施类别</th>
										<th data-options="field:'facilitiesName',width:120,align:'center',editor:'text'">设施名称</th>
										<th data-options="field:'facilitiesNumber',width:120,align:'center',editor:'text'">数量</th>
										<th data-options="field:'remarsks',width:120,align:'center',editor:'text'">备注</th>
									</tr>
								</thead>
							</table>
							<div id="dlLineItemsTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="aaddItem()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
									data-options="iconCls:'icon-edit',plain:true" onclick="aeditItem()"><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left">修改</span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-remove',plain:true" onclick="aremoveDlLineItem()"><span
									class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a>
							</div>
							<script type="text/javascript">
							$('#dlLineItems').datagrid();  
							function aaddItem(){
								$.ajax({
									  url:'${vix}/hr/demandApplyAction!goAddDemandFacilities.action',
									  cache: false,
									  success: function(html){
										  $(".ab_outer .list td input[type='checkbox']").css("margin-left",10);
										  $(".ab_c .content").css("margin-bottom","0");
										  $('.ab_c .content').parent().css('position','relative');
										  asyncbox.open({
											 	modal:true,
												width : 1000,
												height : 500,
												title:"选择培训设施",
												html:html,
												callback : function(action,returnValue){
													if(action == 'ok'){
														if(returnValue != undefined){
															var rv = returnValue.split(",");
															$.ajax({
																  url:'${vix}/hr/demandApplyAction!saveOrUpdateTrainingFacilities.action?tdId='+rv[0]+"&id=${demandApply.id}",
																  cache: false,
																  success: function(html){
																	  showMessage(html);
																	  setTimeout("",1000);
																	  $('#dlLineItems').datagrid("reload");
																  }
															});
														}else{
															asyncbox.success("请选择渠道!","提示信息");
														}
													}
												},
												btnsbar : $.btn.OKCANCEL
											});
									  }
								});
							}
							function aeditItem(){
								var rows = $('#dlLineItems').datagrid('getSelected');
								if(null == rows){
									alert("请选择一条数据！");
									return;
								}
								$.ajax({
									  url:'${vix}/hr/demandApplyAction!goAddDemandFacilities.action?lineItemId='+rows.id,
									  cache: false,
									  success: function(html){
										  asyncbox.open({
											 	modal:true,
												width : 700,
												height : 400,
												title:"添加培训设施",
												html:html,
												callback : function(action){
													if(action == 'ok'){
														if($('#orderItemForm').validationEngine('validate')){
															$.post('${vix}/hr/demandApplyAction!saveOrUpdateTrainingFacilities.action', {
																	'id' : $("#id").val(),
																	'trainingFacilities.id' : $("#oiId").val(),
																	'trainingFacilities.facilitiesCode' : $("#facilitiesCode").val(),
																	'trainingFacilities.facilitiesType' : $("#facilitiesType").val(),
																	'trainingFacilities.facilitiesName' : $("#facilitiesName").val(),
																	'trainingFacilities.facilitiesNumber' : $("#facilitiesNumber").val(),
																	'trainingFacilities.remarsks' : $("#remarsks").val()
																	}, function(result){
																		showMessage(result);
																		setTimeout("",1000);
																		$('#dlLineItems').datagrid("reload");
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
							function aremoveDlLineItem(){
								var row = $('#dlLineItems').datagrid('getSelected');
								if(row){
									var index = $('#dlLineItems').datagrid('getRowIndex',row);
									$('#dlLineItems').datagrid('deleteRow', index);
								}
							}
					</script>
						</div>
					</div>
					<div id="a3" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<div style="padding: 8px;">
							<table id="dlLineItemas" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="rownumbers:true,pagination: true,iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlLineItemsaTb',url: '${vix}/hr/demandApplyAction!getTrainingDataJson.action?id=${demandApply.id}'">
								<thead>
									<tr>
										<th data-options="field:'datumCode',align:'center',width:120,editor:'text'">资料编码</th>
										<th data-options="field:'datumType',width:200,align:'center',editor:'text'">资料类别</th>
										<th data-options="field:'datumName',width:120,align:'center',editor:'text'">资料名称</th>
										<th data-options="field:'profile',width:120,align:'center',editor:'text'">资料简介</th>
										<th data-options="field:'datumauthor',width:120,align:'center',editor:'text'">资料作者</th>
										<th data-options="field:'datumCost',width:120,align:'center',editor:'text'">资料费用</th>
										<th data-options="field:'publishingUnit',width:120,align:'center',editor:'text'">出版单位</th>
										<th data-options="field:'storageLocation',width:120,align:'center',editor:'text'">存放位置</th>
										<th data-options="field:'remarksss',width:120,align:'center',editor:'text'">备注</th>
									</tr>
								</thead>
							</table>
							<div id="dlLineItemsaTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="addItemS()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
									data-options="iconCls:'icon-edit',plain:true" onclick="editSItem()"><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left">修改</span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-remove',plain:true" onclick="removeSItem()"><span class="l-btn-left"><span
										class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a>
							</div>
							<script type="text/javascript">
							$('#dlLineItemas').datagrid();  
							function addItemS(){
								$.ajax({
									  url:'${vix}/hr/demandApplyAction!goAddDemandData.action',
									  cache: false,
									  success: function(html){
										  $(".ab_outer .list td input[type='checkbox']").css("margin-left",10);
										  $(".ab_c .content").css("margin-bottom","0");
										  $('.ab_c .content').parent().css('position','relative');
										  asyncbox.open({
											 	modal:true,
												width : 1000,
												height : 500,
												title:"选择资料",
												html:html,
												callback : function(action,returnValue){
													if(action == 'ok'){
														if(returnValue != undefined){
															var rv = returnValue.split(",");
															$.ajax({
																  url:'${vix}/hr/demandApplyAction!saveOrUpdateTrainingData.action?tfId='+rv[0]+"&id=${demandApply.id}",
																  cache: false,
																  success: function(html){
																	  showMessage(html);
																	  setTimeout("",1000);
																	  $('#dlLineItemas').datagrid("reload");
																  }
															});
														}else{
															asyncbox.success("请选择资料!","提示信息");
														}
													}
												},
												btnsbar : $.btn.OKCANCEL
											});
									  }
								});
							}
							function editSItem(){
								var rows = $('#dlLineItemas').datagrid('getSelected');
								if(null == rows){
									alert("请选择一条数据！");
									return;
								}
								$.ajax({
									  url:'${vix}/hr/demandApplyAction!goAddDemandData.action?lineItemId='+rows.id,
									  cache: false,
									  success: function(html){
										  asyncbox.open({
											 	modal:true,
												width : 700,
												height : 400,
												title:"添加资料",
												html:html,
												callback : function(action){
													if(action == 'ok'){
														if($('#orderItemForm').validationEngine('validate')){
															$.post('${vix}/hr/demandApplyAction!saveOrUpdateTrainingData.action', {
																	'id' : $("#id").val(),
																	'trainingData.id' : $("#oiId").val(),
																	'trainingData.datumCode' : $("#datumCode").val(),
																	'trainingData.datumType' : $("#datumType").val(),
																	'trainingData.datumName' : $("#datumName").val(),
																	'trainingData.profile' : $("#profile").val(),
																	'trainingData.datumauthor' : $("#datumauthor").val(),
																	'trainingData.datumCost' : $("#datumCost").val(),
																	'trainingData.publishingUnit' : $("#publishingUnit").val(),
																	'trainingData.storageLocation' : $("#storageLocation").val(),
																	'trainingData.remarksss' : $("#remarksss").val()
																	}, function(result){
																		showMessage(result);
																		
																		setTimeout("",1000);
																		$('#dlLineItemas').datagrid("reload");
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
							function removeSItem(){
								var row = $('#dlLineItemas').datagrid('getSelected');
								if(row){
									var index = $('#dlLineItemas').datagrid('getRowIndex',row);
									$('#dlLineItemas').datagrid('deleteRow', index);
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