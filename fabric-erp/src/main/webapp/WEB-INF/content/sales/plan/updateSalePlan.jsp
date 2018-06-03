<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
function chooseParentSalePlan(){
	$.ajax({
		  url:'${vix}/sales/plan/salePlanAction!goChooseSalePlan.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 640,
					height : 340,
					title:"选择上级计划",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(typeof(returnValue) == undefined){
								$("#parentSalePlanId").val("");
								$("#parentSalePlanName").val("");
								asyncbox.success("<s:text name='pleaseChooseSalePlan'/>","<s:text name='vix_message'/>");
							}else{
								var result = returnValue.split(",");
								$("#parentSalePlanId").val(result[0]);
								$("#parentSalePlanName").val(result[1]);
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
function chooseItem(){
	$.ajax({
		  url:'${vix}/mdm/item/itemAction!goChooseItem.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 960,
					height : 580,
					title:"选择${vv:varView('vix_mdm_item')}",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != ''){
								var r = returnValue.split(",");
								$("#itemId").val(r[0]);
								$("#itemCode").val(r[1]);
								$("#itemName").val(r[2]);
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
function chooseEmployee(){
	$.ajax({
		  url:'${vix}/common/select/commonSelectEmpAction!goList.action?tag=choose',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 960,
					height : 500,
					title:"选择销售员",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var result = returnValue.split(":");
								$("#salesManId").val(result[0].replace(',',''));
								$("#salesManName").val(result[1].replace(',',''));
							}else{
								$("#salesManId").val("");
								$("#salesManName").val("");
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
function chooseDepartment(tag){
	$.ajax({
		  url:'${vix}/common/select/commonSelectOrgUnitAction!goChooseOrgUnit.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 560,
					height : 340,
					title:"选择部门",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var resObj = $.parseJSON(returnValue);
								var uId = resObj[0].realId;
								$("#"+tag+"Id").val(uId);
								$("#"+tag+"Name").val(resObj[0].name);
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
$("#salePlanForm").validationEngine();
function loadMeasureUnit(){
	var mugId = $("#measureUnitGroupId").val();
	if(mugId != '-1'){	
		$.ajax({
			  url:"${vix}/sales/plan/salePlanAction!loadMeasureUnit.action?type=salePlanMeasureUnit&id="+$("#id").val()+"&measureUnitGroupId="+mugId,
			  cache: false,
			  success: function(html){
				  $("#measureUnitId").html(html);
			  }
		});
		$.ajax({
			  url:"${vix}/sales/plan/salePlanAction!loadMeasureUnit.action?type=salePlanAssitMeasureUnit&id="+$("#id").val()+"&measureUnitGroupId="+mugId,
			  cache: false,
			  success: function(html){
				  $("#assitMeasureUnitId").html(html);
			  }
		});
	}
}
loadMeasureUnit();
</script>

<div class="content" style="margin-top: 25px; overflow: hidden">
	<form id="salePlanForm">
		<div class="order">
			<dl>
				<dt>
					<span class="no_line"> <s:if test="isAllowAudit == 1">
							<a onclick="approvalStockRecords(1);" href="###"><img width="22" height="22" alt="保存并提交审批" src="${vix}/common/img/dt_submit.png" /></a>
						</s:if>
					</span>
				</dt>
			</dl>
		</div>
		<div class="box order_table" style="height: 275px;">
			<table>
				<s:hidden id="id" name="salePlan.id" value="%{salePlan.id}" theme="simple" />
				<tr height="30">
					<td></td>
					<td></td>
				</tr>
				<tr height="30">
					<td align="right">计划名称:&nbsp;</td>
					<td><input id="name" name="salePlan.name" value="${salePlan.name}" class="validate[required] text-input" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="30">
					<td align="right">上级计划:&nbsp;</td>
					<td><input id="parentSalePlanName" name="salePlan.parentSalePlan.name" value="${salePlan.parentSalePlan.name}" /> <s:hidden id="parentSalePlanId" name="parentSalePlanId" value="%{salePlan.parentSalePlan.id}" theme="simple" /> <a class="abtn"><span onclick="chooseParentSalePlan();">选择</span></a></td>
					<td align="right">销售人员:&nbsp;</td>
					<td><input id="salesManName" name="salePlan.salesMan.name" value="${salePlan.salesMan.name}" class="validate[required] text-input" /><span style="color: red;">*</span> <s:hidden id="salesManId" name="salesManId" value="%{salePlan.salesMan.id}" theme="simple" /> <a class="abtn"><span onclick="chooseEmployee();">选择</span></a></td>
				</tr>
				<tr height="30">
					<td align="right">销售组织:&nbsp;</td>
					<td><input id="saleOrgName" value="${salePlan.saleOrg.fs}" type="text" readonly="readonly" class="validate[required] text-input" /><span style="color: red;">*</span> <s:hidden id="saleOrgId" name="saleOrgId" value="%{salePlan.saleOrg.id}" theme="simple" /> <span class="abtn"><span style="cursor: pointer;"
							onclick="chooseDepartment('saleOrg');">选择</span></span></td>
					<td align="right">销售部门:</td>
					<td><input id="departmentName" value="${salePlan.departmet.fs}" type="text" readonly="readonly" class="validate[required] text-input" /><span style="color: red;">*</span> <s:hidden id="departmentId" name="departmetId" value="%{salePlan.departmet.id}" theme="simple" /> <span class="abtn"><span style="cursor: pointer;"
							onclick="chooseDepartment('department');">选择</span></span></td>
				</tr>
				<tr height="30">
					<td align="right">${vv:varView('vix_mdm_item')}名称:&nbsp;</td>
					<td><input id="itemName" value="${salePlan.item.name}" type="text" readonly="readonly" class="validate[required] text-input" /><span style="color: red;">*</span> <s:hidden id="itemId" name="itemId" value="%{salePlan.item.id}" theme="simple" /> <span class="abtn"><span style="cursor: pointer;" onclick="chooseItem();">选择</span></span></td>
					<td align="right">${vv:varView('vix_mdm_item')}规格:</td>
					<td><input id="specifications" name="salePlan.specifications" value="${salePlan.specifications}" type="text" /></td>
				</tr>
				<tr height="30">
					<td align="right">数量:&nbsp;</td>
					<td><input id="amount" name="salePlan.amount" value="${salePlan.amount}" /></td>
					<td align="right">计量单位组：</td>
					<td><s:select id="measureUnitGroupId" headerKey="" headerValue="--请选择--" list="%{measureUnitGroupList}" listValue="name" listKey="id" value="%{salePlan.measureUnitGroup.id}" multiple="false" theme="simple" onchange="loadMeasureUnit();"></s:select></td>
				</tr>
				<tr>
					<td align="right">计量单位:&nbsp;</td>
					<td><select id="measureUnitId"><option value="-1">--------请选择------</option></select></td>
					<td align="right">辅助计量单位:&nbsp;</td>
					<td><select id="assitMeasureUnitId"><option value="-1">--------请选择------</option></select></td>
				</tr>
				<tr height="30">
					<td align="right">生产组织:&nbsp;</td>
					<td><input id="produceOrg" name="salePlan.produceOrg" value="${salePlan.produceOrg}" /></td>
				</tr>
				<tr height="30">
					<td align="right">时间:&nbsp;</td>
					<td><input id="time" name="salePlan.time" value="${salePlan.time}" onfocus="showTime('billDate','yyyy-MM-dd')" class="validate[required] text-input" /><span style="color: red;">*</span> <img onclick="showTime('time','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
					<td align="right">周期:&nbsp;</td>
					<td><input id="cycle" name="salePlan.cycle" value="${salePlan.cycle}" class="validate[required] text-input" /><span style="color: red;">*</span></td>
				</tr>
			</table>
		</div>
	</form>
</div>