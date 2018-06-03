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
//页面首次加载
$(function(){
	
	$("#trainingType").val('${trainingExpenses.trainingType }');
	$("#costTypes").val('${trainingExpenses.costTypes }');
	$("#hroughApproval").val('${trainingExpenses.hroughApproval }');
});

//提示
$("table tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("table tr:even").addClass("alt");
/** 保存并新增培训费用*/
function saveOrUpdateOrder(){
	var orderItemStr = "";
	$.post('${vix}/hr/trainCostAction!saveOrUpdate.action',
		{
			'trainingExpenses.id':$("#id").val(),	
			'trainingExpenses.projectTraining':$("#projectTraining").val(),	
			'trainingExpenses.trainingType':$("#trainingType").val(),	
			'trainingExpenses.costTypes':$("#costTypes").val(),	
			'trainingExpenses.costAccount':$("#costAccount").val(),	
			'trainingExpenses.costPeople':$("#costPeople").val(),	
			'trainingExpenses.costAudit':$("#costAudit").val(),	
			'trainingExpenses.hroughApproval':$("#hroughApproval").val(),	
			'trainingExpenses.costStartDate':$("#costStartDate").val(),	
			'trainingExpenses.costCheckDate':$("#costCheckDate").val(),	
			'trainingExpenses.remarks':$("#remarks").val(),	
			'orderItemStr':orderItemStr
		},
		function(result){
			showMessage(result);
			setTimeout("",1000);
			loadContent('${vix}/hr/trainCostAction!goList.action?menuId=menuOrder');
		}
	 );
}

/** 保存并新增培训费用*/
function saveOrAdd(){
	var orderItemStr = "";
	$.post('${vix}/hr/trainCostAction!saveOrUpdate.action',
		{
			'trainingExpenses.id':$("#id").val(),	
			'trainingExpenses.projectTraining':$("#projectTraining").val(),	
			'trainingExpenses.trainingType':$("#trainingType").val(),	
			'trainingExpenses.costTypes':$("#costTypes").val(),	
			'trainingExpenses.costAccount':$("#costAccount").val(),	
			'trainingExpenses.costPeople':$("#costPeople").val(),	
			'trainingExpenses.costAudit':$("#costAudit").val(),	
			'trainingExpenses.hroughApproval':$("#hroughApproval").val(),	
			'trainingExpenses.costStartDate':$("#costStartDate").val(),	
			'trainingExpenses.costCheckDate':$("#costCheckDate").val(),	
			'trainingExpenses.remarks':$("#remarks").val(),	
			'orderItemStr':orderItemStr
		},
		function(result){
			showMessage(result);
			setTimeout("",1000);
			loadContent('${vix}/hr/trainCostAction!goList.action');
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
				<li><a href="#"><img src="img/hr_training.png" alt="" />
					<s:text name="hr_humanr_resources" /></a></li>
				<li><a href="#" onclick="loadContent('${vix}/hr/trainCostAction!goList.action');"><s:text name="hr_train_admin" /></a></li>
				<li><a href="#" onclick="loadContent('${vix}/hr/trainCostAction!goList.action');"><s:text name='hr_train_cost' /></a></li>
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
						onclick="loadContent('${vix}/hr/trainCostAction!goList.action');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a>
					</span> <strong><b><s:text name="新增培训费用" /> </b><i></i> </strong>
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
											<input type="hidden" id="id" name="id" value="${trainingExpenses.id}" />
											<td align="right">培训项目名称：</td>
											<td><input name="projectTraining" id="projectTraining" type="text" size="30" value="${trainingExpenses.projectTraining}" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">培训类型：</td>
											<td><select id="trainingType" class="validate[required] text-input">
													<option value="">请选择</option>
													<option value="1">公司内部</option>
													<option value="2">公司集体</option>
													<option value="3">入职培训</option>
											</select></td>
										</tr>
										<tr>
											<td align="right">费用类型：</td>
											<td><select id="costTypes" class="validate[required] text-input/">
													<option value="">请选择</option>
													<option value="1">住宿费用</option>
													<option value="2">餐饮费用</option>
													<option value="3">交通费用</option>
													<option value="4">通讯费用</option>
											</select> <span style="color: red;">*</span></td>
											<td align="right">费用金额：</td>
											<td><input name="" id="costAccount" type="text" size="30" value="${trainingExpenses.costAccount}" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">费用提出人：</td>
											<td><input name="" id="costPeople" type="text" size="30" value="${trainingExpenses.costPeople}" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">费用审核人：</td>
											<td><input name="" id="costAudit" type="text" size="30" value="${trainingExpenses.costAudit}" /></td>
										</tr>
										<tr>
											<td align="right">是否通过审批：</td>
											<td><select id="hroughApproval" class="validate[required] text-input">
													<option value="">请选择</option>
													<option value="1">是</option>
													<option value="2">否</option>
											</select></td>
											<td align="right">备注：</td>
											<td><input name="" id="remarks" type="text" size="30" value="${trainingExpenses.remarks}" /></td>
										</tr>
										<tr>
											<td align="right">提出时间：</td>
											<td><input id="costStartDate" name="costStartDate" value="<fmt:formatDate value='${trainingExpenses.costStartDate }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('costStartDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
											<td align="right">审核时间：</td>
											<td><input id="costCheckDate" name="costCheckDate" value="<fmt:formatDate value='${trainingExpenses.costCheckDate }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('costCheckDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
				</dd>
				<!--oder-->
				<!--submenu-->
	</form>
</div>
<!-- content -->