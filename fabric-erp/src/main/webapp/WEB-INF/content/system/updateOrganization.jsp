<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#tableUpdate tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("#tableUpdate tr:even").addClass("alt");
function chooseEmployee(){
	$.ajax({
		  url:'${vix}/system/employeeAction!goChooseEmployee.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 560,
					height : 340,
					title:"选择负责人",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							var result = returnValue.split(",");
							$("#employeeId").val(result[0]);
							$("#employeeName").html(result[1]);
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
function chooseOrganization(){
	$.ajax({
		  url:'${vix}/system/organizationAction!goChooseOrganization.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 560,
					height : 340,
					title:"选择父机构",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							var result = returnValue.split(",");
							$("#parentId").val(result[0]);
							$("#parentOrgName").html(result[1]);
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
</script>
<div style="padding: 5px;">
	<table>
		<s:hidden id="id" name="organization.id" value="%{organization.id}" theme="simple" />
		<s:hidden id="employeeId" name="organization.employeeId" value="%{organization.employeeId}" theme="simple" />
		<s:hidden id="parentId" name="organization.parentOrganization.id" value="%{organization.parentOrganization.id}" theme="simple" />
		<tr height="30">
			<td align="right">负责人:&nbsp;</td>
			<td colspan="2"><span id="employeeName"><s:property value="getEmployee(organization.employeeId)" /></span> <span class="btn"><a href="#" onclick="chooseEmployee();">选择</a></span></td>
			<td align="right">上级机构:&nbsp;</td>
			<td colspan="2"><span id="parentOrgName"><s:property value="%{organization.parentOrganization.orgName}" /></span> <span class="btn"><a href="#" onclick="chooseOrganization();">选择</a></span></td>
		</tr>
		<tr height="30">
			<td align="right">机构编码:&nbsp;</td>
			<td><s:textfield id="orgCode" name="organization.orgCode" value="%{organization.orgCode}" theme="simple" /></td>
			<td align="right">机构名称:&nbsp;</td>
			<td><s:textfield id="orgName" name="organization.orgName" value="%{organization.orgName}" theme="simple" /></td>
			<td align="right">税号:&nbsp;</td>
			<td><s:textfield id="taxNumber" name="organization.taxNumber" value="%{organization.taxNumber}" theme="simple" /></td>
		</tr>
		<tr height="30">
			<td align="right">本币:&nbsp;</td>
			<td><s:textfield id="homeCurrency" name="organization.homeCurrency" value="%{organization.homeCurrency}" theme="simple" /></td>
			<td align="right">本币编码:&nbsp;</td>
			<td><s:textfield id="homeCurrencyCode" name="organization.homeCurrencyCode" value="%{organization.homeCurrencyCode}" theme="simple" /></td>
			<td align="right">备注:&nbsp;</td>
			<td colspan="3"><s:textfield id="memo" name="organization.memo" value="%{organization.memo}" theme="simple" /></td>
		</tr>
	</table>
</div>
