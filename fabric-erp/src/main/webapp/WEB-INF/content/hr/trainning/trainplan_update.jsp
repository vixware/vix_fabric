<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<script src="${vix}/common/js/ajaxfileupload.js" type="text/javascript"></script>
<script src="${vix}/common/core/js/all.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function(){
		if(null == '${trainingPlanning.courseDescription}'){
			$("#courseDescription").val('${userAccount.account }');
		}
	});
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
		
		$(tooltipClass).css({ 'left': tooltipPosLeft, 'top': tooltipPosTop })
							.animate({'opacity':'1', 'marginTop':'0'}, 500);
		
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

/** 保存培训计划 */
function saveOrUpdateOrder(){
	var orderItemStr = "";
	if($('#replanForm').validationEngine('validate')){
		$.post('${vix}/hr/trainPlanAction!saveOrUpdate.action',
			{
			'trainingPlanning.id':$("#id").val(),
			'trainingPlanning.applicationName':$("#applicationName").val(),
			'trainingPlanning.orgUnitAndLeadingOfficial':$("#orgUnitAndLeadingOfficial").val(),
			'trainingPlanning.planStatus':$("#planStatus").val(),
			'trainingPlanning.pubTypes':$('input:radio[name=pubTypes]:checked').val(),
			'trainingPlanning.pubIds' : $("#pubIds").val()+',',
			'trainingPlanning.planType':$("#planType").val(),
			'trainingPlanning.planLevel':$("#planLevel").val(),
			'trainingPlanning.planningNature':$("#planningNature").val(),
			'trainingPlanning.courseDescription':$("#courseDescription").val(),
			'trainingPlanning.proposedTime':$("#proposedTime").val(),
			'trainingPlanning.remarks':$("#remarks").val(),
			'orderItemStr':orderItemStr
			},
			function(result){
				showMessage(result);
				setTimeout("",1000);
				loadContent('${vix}/hr/trainPlanAction!goList.action?menuId=menuOrder');
			}
		 );
	}
}

/** 保存并新增培训计划 */
function saveOrAdd(){
	var orderItemStr = "";
		$.post('${vix}/hr/trainPlanAction!saveOrUpdate.action',
		   {
			'trainingPlanning.id':$("#id").val(),
			'trainingPlanning.applicationName':$("#applicationName").val(),
			'trainingPlanning.orgUnitAndLeadingOfficial':$("#orgUnitAndLeadingOfficial").val(),
			'trainingPlanning.pubIds' : $("#pubIds").val()+',',
			'trainingPlanning.pubTypes':$('input:radio[name=pubTypes]:checked').val(),
			'trainingPlanning.planStatus':$("#planStatus").val(),
			'trainingPlanning.planType':$("#planType").val(),
			'trainingPlanning.planLevel':$("#planLevel").val(),
			'trainingPlanning.planningNature':$("#planningNature").val(),
			'trainingPlanning.courseDescription':$("#courseDescription").val(),
			'trainingPlanning.proposedTime':$("#proposedTime").val(),
			'trainingPlanning.remarks':$("#remarks").val(),
			'orderItemStr':orderItemStr
		   },
			function(result){
				showMessage(result);
				setTimeout("",1000);
				loadContent('${vix}/hr/trainPlanAction!goSaveOrUpdate.action');
			} 
		);
}

//页面首次加载
$(function(){
	$("#planStatus").val('${trainingPlanning.planStatus}');
	$("#planType").val('${trainingPlanning.planType}');
	$("#planLevel").val('${trainingPlanning.planLevel}');
	$("#planningNature").val('${trainingPlanning.planningNature}');
	
	//默认选择部门
    if($('input:radio[name=pubTypes]:checked').length==0){
    	$('input:radio[name=pubTypes]:first').trigger('click');
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
	$("#orgUnitAndLeadingOfficial").val("");
}

/**
 * 添加发布对象
 */
function addBulletinPubobject(){
	
	var pubTypeVal = $("input[name='pubTypes']:checked").val();
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
										$("#orgUnitAndLeadingOfficial").val(selectNames);
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
								selectNames = $("#orgUnitAndLeadingOfficial").val()+selectNames;
								
								$("#pubIds").val(selectIds);
								selectNames = selectNames.substring(1,selectNames.length);
								$("#orgUnitAndLeadingOfficial").val(selectNames);
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
				<li><a href="#" onclick="loadContent('${vix}/hr/trainPlanAction!goList.action');">培训计划</a></li>
				<li><a href="#" onclick="loadContent('${vix}/hr/trainPlanAction!goList.action');">创建培训计划</a></li>
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
						onclick="loadContent('${vix}/hr/trainPlanAction!goList.action');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a>
					</span> <strong><b><s:text name="新增培训计划" /> </b><i></i> </strong>
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
										<%--检查ID，判断修改--%>
										<input type="hidden" id="id" name="id" value="${trainingPlanning.id}" />
										<tr>
											<td align="right">计划主题：</td>
											<td><input id="applicationName" name="applicationName" value="${trainingPlanning.applicationName }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">计划状态：</td>
											<td><select id="planStatus" class="validate[required] text-input">
													<option value="">请选择</option>
													<option value="1">执行</option>
													<option value="2">未执行</option>
											</select> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">计划级别：</td>
											<td><select id="planLevel" class="validate[required] text-input">
													<option value="">请选择</option>
													<option value="1">新</option>
													<option value="2">低</option>
													<option value="3">中</option>
													<option value="4">高</option>
											</select> <span style="color: red;">*</span></td>
											<td align="right">计划性质：</td>
											<td><select id="planningNature" class="validate[required] text-input">
													<option value="">请选择</option>
													<option value="1">非定向</option>
													<option value="2">定向</option>
											</select> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">计划类型：</td>
											<td><select id="planType" class="validate[required] text-input">
													<option value="">请选择</option>
													<option value="1">短期计划</option>
													<option value="2">中期计划</option>
													<option value="3">长期计划</option>
											</select> <span style="color: red;">*</span></td>
											<td align="right">提出计划时间：</td>
											<td><input id="proposedTime" name="proposedTime" value="<fmt:formatDate value='${trainingPlanning.proposedTime }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('proposedTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<td align="right">负责部门或负责人：</td>
											<td><input type="hidden" id="id" name="id" value="${trainingPlanning.id}" /> <s:radio id="pubTypes" list="#{'O':'负责部门','E':'负责人'}" name="pubTypes" value="%{trainingPlanning.pubTypes}" onchange="changePubType(this.value);" theme="simple"></s:radio> <a href="javascript:void(0);" onclick="addBulletinPubobject()"><img
													src="img/icon_25.gif" />新增</a>&nbsp;&nbsp; <a href="#" onclick="clearPubType()"><img src="img/delete.gif" />清空</a> <br /> <textarea rows="6" cols="6" id="orgUnitAndLeadingOfficial" name="orgUnitAndLeadingOfficial" style="width: 250px; height: 103px;">${trainingPlanning.orgUnitAndLeadingOfficial}</textarea> <input type="hidden"
												id="pubIds" name="trainingPlanning.pubIds" value="${trainingPlanning.pubIds}" /></td>
											<td align="right">备注：</td>
											<td colspan="2"><textarea id="remarks" name="remarks" class="example" rows="6" style="width: 250px; height: 103px;">${trainingPlanning.remarks }</textarea></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
				</dd>
				<div class="clearfix" style="background: #FFF; position: relative;">
					<div class="right_menu">
						<ul>
							<li class="current"><a onclick="javascript:tab(2,1,'a',event);"><img src="${vix}/common/img/mail.png" alt="" />培训计划</a></li>
							<li><a onclick="javascript:$('#a2').attr('style','');tab(2,2,'a',event)"><img src="${vix}/common/img/attachment.png" alt="" />附件</a></li>
						</ul>
					</div>

					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<script type="text/javascript">
						function saveDeliveryAddress(url){
							$.ajax({
								  url:url,
								  cache: false,
								  success: function(html){
									  asyncbox.open({
										  	title:'培训计划明细 ',
										 	modal:true,
											width : 724,
											height :340,
											html:html,
											callback : function(action,returnValue){
												if(action == 'ok'){
													if($('#daForm').validationEngine('validate')){
														$.post('${vix}/hr/trainPlanAction!saveOrUpdateTrainingPlanningDetail.action',
																{'id':$("#id").val(),
																  'detail.id':$("#daId").val(),
																  'detail.trainingWay':$("#trainingWay").val(),
																  'detail.goalOfTraining':$("#goalOfTraining").val(),
																  'detail.trainingContent':$("#trainingContent").val(),
																  'detail.trainingCourse':$("#trainingCourse").val(),
																  'detail.trainingSite':$("#trainingSite").val(),
																  'detail.trainingInstitutions':$("#trainingInstitutions").val(),
																  'detail.sourcesOfTeachers':$("#sourcesOfTeachers").val(),
																  'detail.curriculumClassHours':$("#curriculumClassHours").val(),
																  'detail.planStartDate':$("#planStartDate").val(),
																  'detail.planEndDate':$("#planEndDate").val(),
																},
																function(result){
																	showMessage(result);
																	setTimeout("",1000);
																	$('#dlAddress2').datagrid("reload");
																}
														);
													}
												}
											},
											btnsbar : $.btn.OKCANCEL
										});
								  }
							});
						}
						$('#dlAddress2').datagrid({
							url: '${vix}/hr/trainPlanAction!getTrainingPlanningDetailJson.action?id=${trainingPlanning.id}',
							title: '培训计划明细',
							width: 'auto',
							height: '450',
							rownumbers : true,
							idField : 'id',
							fitColumns: true,
							columns:[[
								{field:'trainingWay',title:'培训方式',width:200,align:'center'},
								{field:'goalOfTraining',title:'培训目标',width:200,align:'center'},
								{field:'trainingContent',title:'培训内容',width:200,align:'center'},
								{field:'trainingCourse',title:'培训课程',width:200,align:'center'},
								{field:'trainingSite',title:'培训地点',width:200,align:'center'},
								{field:'trainingInstitutions',title:'培训机构',width:200,align:'center'},
								{field:'sourcesOfTeachers',title:'师资来源',width:200,align:'center'},
								{field:'curriculumClassHours',title:'课程学时',width:200,align:'center'},
								{field:'planStartDate',title:'计划开始时间',width:200,align:'center',formatter:formatDatebox},
								{field:'planEndDate',title:'计划结束时间',width:200,align:'center',formatter:formatDatebox},
							]],
							toolbar:[{
								id:'da2Btnadd',
								text:'添加',
								iconCls:'icon-add',
								handler:function(){
									$('#btnsave').linkbutton('enable');
									saveDeliveryAddress('${vix}/hr/trainPlanAction!goAddTrainingPlanningDetail.action?id=0');
								}
							},'-',{
								id:'btnedit',
								text:'修改',
								iconCls: 'icon-edit',
								handler: function(){
									var row = $('#dlAddress2').datagrid("getSelected");	//获取你选择的所有行
									if(row){
										saveDeliveryAddress('${vix}/hr/trainPlanAction!goAddTrainingPlanningDetail.action?id='+row.id)
									}
								}
							},'-',{
								text:'删除',
								iconCls:'icon-remove',
								handler:function(){
									var rows = $('#dlAddress2').datagrid("getSelections");	//获取你选择的所有行	
									//循环所选的行
									for(var i =0;i<rows.length;i++){
										var index = $('#dlAddress2').datagrid('getRowIndex',rows[i]);//获取某行的行号
										$('#dlAddress2').datagrid('deleteRow',index);	//通过行号移除该行
										$.ajax({url:'${vix}/hr/trainPlanAction!deleteTrainingPlanningDetailById.action?id='+rows[i].id,cache: false});
									}
								}
							}]
						});
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
						<div style="padding: 8px;">
							<table id="dlAddress2"></table>
						</div>
					</div>

					<div id="a2" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<script type="text/javascript">
						$('#soAttach').datagrid({
							url: '${vix}/hr/trainPlanAction!getHrAttachmentsJson.action?id=${trainingPlanning.id}',
							title: '附件',
							width: 'auto',
							height: '450',
							fitColumns: true,
							columns:[[
								{field:'id',title:'编号',width:80},
								{field:'name',title:'名称',width:180},
							]],
							toolbar:[{
								id:'saBtnadd',
								text:'添加',
								iconCls:'icon-add',
								handler:function(){
									$('#btnsave').linkbutton('enable');
									$.ajax({
										  url:'${vix}/hr/trainPlanAction!addHrAttachments.action',
										  cache: false,
										  success: function(html){
											  asyncbox.open({
												 	modal:true,
													width : 364,
													height : 160,
													title:"上传附件",
													html:html,
													callback : function(action,returnValue){
														if(action == 'ok'){
															uploadFile('${vix}/hr/trainPlanAction!uploadHrAttachments.action?id=${trainingPlanning.id}','fileToUpload');
															$('#soAttach').datagrid("reload");
														}
													},
													btnsbar : $.btn.OKCANCEL
												});
										  }
									});
								}
							},'-',{
								text:'删除',
								iconCls:'icon-remove',
								handler:function(){
									var rows = $('#soAttach').datagrid("getSelections");	//获取你选择的所有行	
									//循环所选的行
									for(var i =0;i<rows.length;i++){
										var index = $('#soAttach').datagrid('getRowIndex',rows[i]);//获取某行的行号
										$('#soAttach').datagrid('deleteRow',index);	//通过行号移除该行
										$.ajax({url:'${vix}/hr/trainPlanAction!deleteHrAttachments.action?afId='+rows[i].id,cache: false});
									}
								}
							}]
						});
					</script>
						<div style="padding: 8px;">
							<table id="soAttach"></table>
						</div>
					</div>
				</div>
			</dl>
		</div>
	</form>
</div>