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
						title:"选择人员",
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
									$("#recruiter").val(selectNames);
								}
								
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	}
//弹出组织树
function chooseParentCategory(tag){
	$.ajax({
		  url:'${vix}/hr/reSumAciton!goChooseCategory.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 560,
					height : 340,
					title:"选择组织",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var result = returnValue.split(",");
									$("#parentId").val(result[0]);
									
									if(tag==0){
									$("#hasTheJob").val(result[1]);
									}
									else if (tag==1){
										$("#responsibleForRecruitmentDepartment").val(result[1]);
									}
									else if (tag==2){
										$("#recommendedPositionName").val(result[1]);
									}
									else if (tag==4){
										$("#appraiserPosition").val(result[1]);
									}
									else{
									$("#employmentObjective").val(result[1]);
									}
							}else{
								$("#parentId").val("");
								$("#categoryName").html("");
								asyncbox.success("请选择分类信息!","提示信息");
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}

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
$("table tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("table tr:even").addClass("alt");

//页面首次加载
$(function(){
	
	$("#approvalStatus").val('${hrRecruitSummary.approvalStatus }');
});

/* 保存招聘总结 */
function saveOrUpdateOrder(){
	var orderItemStr = "";
	$.post('${vix}/hr/reSumAciton!saveOrUpdate.action',
		{
		'hrRecruitSummary.id':$("#id").val(),	
		'hrRecruitSummary.recruitmentPlanName':$("#recruitmentPlanName").val(),	
		'hrRecruitSummary.recruitmentActivityName':$("#recruitmentActivityName").val(),	
		'hrRecruitSummary.hasTheJob':$("#hasTheJob").val(),	
		'hrRecruitSummary.successRateOfRecruitment':$("#successRateOfRecruitment").val(),	
		'hrRecruitSummary.recruitmentPlanning':$("#recruitmentPlanning").val(),	
		'hrRecruitSummary.actualBigintOfRecruitment':$("#actualBigintOfRecruitment").val(),	
		'hrRecruitSummary.expensePocket':$("#expensePocket").val(),	
		'hrRecruitSummary.actualStartTime':$("#actualStartTime").val(),	
		'hrRecruitSummary.actualEndTime':$("#actualEndTime").val(),	
		'hrRecruitSummary.approvalStatus':$("#approvalStatus").val(),	
		'hrRecruitSummary.responsibleForRecruitmentDepartment':$("#responsibleForRecruitmentDepartment").val(),	
		'hrRecruitSummary.recruiter':$("#recruiter").val(),	
		'hrRecruitSummary.recruitmentResultEvaluation':$("#recruitmentResultEvaluation").val(),	
		'hrRecruitSummary.remark':$("#remark").val(),	
		'hrRecruitSummary.comment':$("#comment").val(),	
		'orderItemStr':orderItemStr
		},
		function(result){
			showMessage(result);
			setTimeout("",1000);
			loadContent('${vix}/hr/reSumAciton!goList.action?menuId=menuOrder');
		}
	 );
}
/* 保存并新增招聘总结 */
function saveOrAdd(){
	var orderItemStr = "";
	$.post('${vix}/hr/reSumAciton!saveOrUpdate.action',
		{
		'hrRecruitSummary.id':$("#id").val(),	
		'hrRecruitSummary.recruitmentPlanName':$("#recruitmentPlanName").val(),	
		'hrRecruitSummary.recruitmentActivityName':$("#recruitmentActivityName").val(),	
		'hrRecruitSummary.hasTheJob':$("#hasTheJob").val(),	
		'hrRecruitSummary.successRateOfRecruitment':$("#successRateOfRecruitment").val(),	
		'hrRecruitSummary.recruitmentPlanning':$("#recruitmentPlanning").val(),	
		'hrRecruitSummary.actualBigintOfRecruitment':$("#actualBigintOfRecruitment").val(),	
		'hrRecruitSummary.expensePocket':$("#expensePocket").val(),	
		'hrRecruitSummary.actualStartTime':$("#actualStartTime").val(),	
		'hrRecruitSummary.actualEndTime':$("#actualEndTime").val(),	
		'hrRecruitSummary.approvalStatus':$("#approvalStatus").val(),	
		'hrRecruitSummary.responsibleForRecruitmentDepartment':$("#responsibleForRecruitmentDepartment").val(),	
		'hrRecruitSummary.recruiter':$("#recruiter").val(),	
		'hrRecruitSummary.recruitmentResultEvaluation':$("#recruitmentResultEvaluation").val(),	
		'hrRecruitSummary.remark':$("#remark").val(),	
		'hrRecruitSummary.comment':$("#comment").val(),	
		'orderItemStr':orderItemStr
		},
		function(result){
			showMessage(result);
			setTimeout("",1000);
			loadContent('${vix}/hr/reSumAciton!goSaveOrUpdate.action');
		}
	 );
}

$("#order").validationEngine();
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
				<li><a href="#"><img src="img/hr_re.png" alt="" />
					<s:text name="hr_humanr_resources" /></a></li>
				<li><a href="#"><s:text name="hr_recruitment_mgs" /></a></li>
				<li><a href="#" onclick="loadContent('${vix}/hr/reSumAciton!goList.action');">招聘总结</a></li>
			</ul>
		</div>
	</h2>
</div>
<div class="content">
	<form id="order">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateOrder();"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="javascript:void(0)" onclick="saveOrAdd()"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/hr/reSumAciton!goList.action');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a>
					</span> <strong><b><s:text name="新增招聘总结" /> </b><i></i> </strong>
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
											<input type="hidden" id="id" name="id" value="${hrRecruitSummary.id}" />
											<td align="right">招聘计划名称：</td>
											<td><input name="" id="recruitmentPlanName" type="text" size="30" value="${hrRecruitSummary.recruitmentPlanName}" /></td>
											<td align="right">招聘活动名称：</td>
											<td><input name="" id="recruitmentActivityName" type="text" size="30" value="${hrRecruitSummary.recruitmentActivityName}" /></td>
										</tr>
										<tr>
											<td align="right">计划招聘人数：</td>
											<td><input name="" id="recruitmentPlanning" type="text" size="30" value="${hrRecruitSummary.recruitmentPlanning}" /></td>
											<td align="right">实际招聘人数：</td>
											<td><input name="" id="actualBigintOfRecruitment" type="text" size="30" value="${hrRecruitSummary.actualBigintOfRecruitment}" /></td>
										</tr>
										<tr>
											<td align="right">已招聘职位：</td>
											<td><input id="hasTheJob" name="parentId" value="${hrRecruitSummary.hasTheJob}" type="text" size="30" /> <input type="hidden" id="parentId" name="parentId" value="${hrCategory.parentCategory.id }" /> <input class="btn" type="button" value="选择" onclick="chooseParentCategory(0);" /></td>
											<td align="right">实际费用：</td>
											<td><input name="" id="expensePocket" type="text" size="30" value="${hrRecruitSummary.expensePocket}" /></td>
										</tr>
										<tr>
											<td align="right">招聘负责部门：</td>
											<td><input id="responsibleForRecruitmentDepartment" name="parentId" value="${hrRecruitSummary.responsibleForRecruitmentDepartment}" type="text" size="30" /> <input type="hidden" id="parentId" name="parentId" value="${hrCategory.parentCategory.id }" /> <input class="btn" type="button" value="选择" onclick="chooseParentCategory(1);" />
											</td>
											<td align="right">招聘负责人：</td>
											<td><input id="recruiter" name="parentId" value="${hrRecruitSummary.recruiter}" type="text" size="30" class="validate[required] text-input" /> <input type="hidden" id="parentId" name="parentId" value="${hrCategory.parentCategory.id }" /> <input class="btn" type="button" value="选择" onclick="chooseEmployees();" /> <span
												style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">实际开始时间：</td>
											<td><input id="actualStartTime" name="actualStartTime" value="<fmt:formatDate value='${hrRecruitSummary.actualStartTime }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('actualStartTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
											<td align="right">实际结束时间：</td>
											<td><input id="actualEndTime" name="actualEndTime" value="<fmt:formatDate value='${hrRecruitSummary.actualEndTime }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('actualEndTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<td align="right">招聘成功率：</td>
											<td><input name="" id="successRateOfRecruitment" type="text" size="30" value="${hrRecruitSummary.successRateOfRecruitment}" />%</td>
											<td align="right">审批状态：</td>
											<td><select id="approvalStatus" class="validate[required] text-input">
													<option value="">请选择</option>
													<option value="1">通过</option>
													<option value="2">未通过</option>
													<option value="3">待议</option>
											</select></td>
										</tr>
										<tr>
											<td align="right">招聘结果评价：</td>
											<td><textarea id="comment" name="comment" class="example" rows="2" style="width: 250px">${hrRecruitSummary.comment }</textarea></td>
											<td align="right">备注：</td>
											<td><textarea id="remark" name="remark" class="example" rows="2" style="width: 250px">${hrRecruitSummary.remark }</textarea></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
				</dd>
	</form>
</div>