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

//页面首次加载
$(function(){
	$("#releaseState").val('${hrRecruitmentPlansummary.releaseState }');
	$("#releaseMode").val('${hrRecruitmentPlansummary.releaseMode }');
});
/** 保存培训需求*/
function saveOrUpdateOrder(){
	var orderItemStr = "";
	if($('#replanForm').validationEngine('validate')){
		$.post('${vix}/hr/recruitmentPlanIssuedAction!saveOrUpdate.action',
			{
			'hrRecruitmentPlansummary.id':$("#id").val(),
			'hrRecruitmentPlansummary.recruitment':$("#recruitment").val(),
			'hrRecruitmentPlansummary.releaseTime':$("#releaseTime").val(),
			'hrRecruitmentPlansummary.recruitmentDepartment':$("#recruitmentDepartment").val(),
			'hrRecruitmentPlansummary.auditPerson':$("#auditPerson").val(),
			'hrRecruitmentPlansummary.releaseState':$("#releaseState").val(),
			'hrRecruitmentPlansummary.releaseMode':$("#releaseMode").val(),
			'hrRecruitmentPlansummary.remarks':$("#remarks").val(),
			'orderItemStr':orderItemStr
			},
			function(result){
				showMessage(result);
				setTimeout("",1000);
				loadContent('${vix}/hr/recruitmentPlanIssuedAction!goList.action?menuId=menuOrder');
			}
		 );
	}
}

/** 保存并新增培训需求*/
function saveOrAdd(){
	var orderItemStr = "";
	if($('#replanForm').validationEngine('validate')){
		$.post('${vix}/hr/recruitmentPlanIssuedAction!saveOrUpdate.action',
			{
			'hrRecruitmentPlansummary.id':$("#id").val(),
			'hrRecruitmentPlansummary.recruitment':$("#recruitment").val(),
			'hrRecruitmentPlansummary.releaseTime':$("#releaseTime").val(),
			'hrRecruitmentPlansummary.recruitmentDepartment':$("#recruitmentDepartment").val(),
			'hrRecruitmentPlansummary.auditPerson':$("#auditPerson").val(),
			'hrRecruitmentPlansummary.releaseState':$("#releaseState").val(),
			'hrRecruitmentPlansummary.releaseMode':$("#releaseMode").val(),
			'hrRecruitmentPlansummary.remarks':$("#remarks").val(),
			'orderItemStr':orderItemStr
			},
			function(result){
				showMessage(result);
				setTimeout("",1000);
				loadContent('${vix}/hr/recruitmentPlanIssuedAction!goList.action');
			}
		 );
	}
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
				<li><a href="#"><img src="img/hr_training.png" alt="" />人力资源</a></li>
				<li><a href="#" onclick="loadContent('${vix}/hr/recruitmentPlanIssuedAction!goList.action');">招聘管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/hr/recruitmentPlanIssuedAction!goList.action');">招聘计划</a></li>
				<li><a href="#" onclick="loadContent('${vix}/hr/recruitmentPlanIssuedAction!goList.action');">下达</a></li>
			</ul>
		</div>
	</h2>
</div>

<div class="content">
	<form id="replanForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a href="#" onclick="loadContent('${vix}/hr/recruitmentPlanIssuedAction!goList.action');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a>
					</span> <strong><b><s:text name="查看招聘计划汇总" /></b><i></i> </strong>
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
											<input type="hidden" id="id" name="id" value="${hrRecruitmentPlansummary.id}" />
											<td align="right">招聘职务：</td>
											<td><input disabled="disabled" id="recruitment" name="auditDepartment" value="${hrRecruitmentPlansummary.recruitment}" type="text" size="30" /></td>
											<td align="right">发布时间：</td>
											<td><input id="releaseTime" name="releaseTime" value="<fmt:formatDate value='${hrRecruitmentPlansummary.releaseTime }' type='both' pattern='yyyy-MM-dd' />" disabled="disabled" type="text" /></td>
										</tr>
										<tr>
											<td align="right">招聘部门：</td>
											<td><input disabled="disabled" id="recruitmentDepartment" name="recruitmentDepartment" value="${hrRecruitmentPlansummary.recruitmentDepartment}" type="text" size="30" /></td>
											<td align="right">审核人：</td>
											<td><input disabled="disabled" id="auditPerson" name="parentId" value="${hrRecruitmentPlansummary.auditPerson}" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">发布状态：</td>
											<td><select disabled="disabled" id="releaseState">
													<option value="">请选择</option>
													<option value="1">发布</option>
													<option value="2">不发布</option>
											</select></td>
											<td align="right">发布方式：</td>
											<td><select disabled="disabled" id="releaseMode">
													<option value="">请选择</option>
													<option value="1">内部</option>
													<option value="2">外部</option>
													<option value="3">内部和外部</option>
											</select></td>
										</tr>
										<tr>
											<td align="right">备注</td>
											<td colspan="2"><textarea disabled="disabled" id="remarks" name="remarks" class="example" rows="2" style="width: 250px">${hrRecruitmentPlansummary.remarks }</textarea></td>
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
							<li class="current"><a onclick="javascript:tab(1,1,'a',event);"><img src="${vix}/common/img/mail.png" alt="" />培训计划</a></li>
						</ul>
					</div>
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<div style="padding: 8px;">
							<table id="dlLineItem" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="rownumbers:true,pagination: true,iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlLineItemTb',url: '${vix}/hr/recruitmentPlanIssuedAction!getHrRecruitplanJson.action?id=${hrRecruitmentPlansummary.id}'">
								<thead>
									<tr>
										<th data-options="field:'programName',align:'center',width:120,editor:'text'">计划主题</th>
										<th data-options="field:'org',width:200,align:'center',editor:'text'">提出计划部门</th>
										<th data-options="field:'responsibleForOrgUnit',width:120,align:'center',editor:'text'">组织部门</th>
										<th data-options="field:'schemer',width:120,align:'center',editor:'text'">计划人</th>
										<th data-options="field:'proposedTime',width:200,align:'center',editor:'databox',formatter:formatDatebox">计划提出时间</th>
										<th data-options="field:'effectTime',width:200,align:'center',editor:'databox',formatter:formatDatebox">计划生效时间</th>
									</tr>
								</thead>
							</table>
							<%-- <div id="dlLineItemTb" style="height: auto">
							<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="baddItem()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add"/></span></span></a> 
							<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-edit',plain:true" onclick="beditItem()"><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left">修改</span></span></a> 
							<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-remove',plain:true" onclick="bremoveDlLineItem()"><span class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete"/></span></span></a>
						</div> --%>
							<script type="text/javascript">
								$('#dlLineItem').datagrid();  
								function baddItem(){
									$.ajax({
									   url:'${vix}/hr/recruitmentPlanIssuedAction!goAddHrRecruitplan.action',
									});
								}
								
								
						</script>
						</div>
					</div>

				</div>
			</dl>
		</div>
	</form>
</div>