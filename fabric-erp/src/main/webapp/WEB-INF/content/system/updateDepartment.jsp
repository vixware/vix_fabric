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
							if(returnValue != undefined){
								var result = returnValue.split(",");
								$("#employeeId").val(result[0]);
								$("#employeeName").html(result[1]);
							}
						}else{
							$("#employeeId").val("");
							$("#employeeName").html("");
							asyncbox.success("请选择负责人!","提示信息");
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
function chooseDepartment(){
	$.ajax({
		  url:'${vix}/system/departmentAction!goChooseDepartment.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 560,
					height : 340,
					title:"选择机构",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var result = returnValue.split(",");
								if(result[0] == $("#id").val()){
									asyncbox.success("不允许引用自身为父分类!","提示信息");
								}else{
									$("#deptId").val(result[0]);
									$("#parentDeptName").html(result[1]);
								}
							}
						}else{
							$("#deptId").val("");
							$("#parentDeptName").html("");
							asyncbox.success("请选择部门信息!","提示信息");
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
					title:"选择机构",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var result = returnValue.split(",");
								$("#orgId").val(result[0]);
								$("#parentOrgName").html(result[1]);
							}
						}else{
							$("#orgId").val("");
							$("#parentOrgName").html("");
							asyncbox.success("请选择机构信息!","提示信息");
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
</script>
<div style="padding: 5px;">
	<s:hidden id="id" name="department.id" value="%{department.id}" theme="simple" />
	<s:hidden id="employeeId" name="department.employeeId" value="%{organization.employeeId}" theme="simple" />
	<s:hidden id="orgId" name="department.organization.id" value="%{department.organization.id}" theme="simple" />
	<s:hidden id="deptId" name="department.parentDepartment.id" value="%{department.parentDepartment.id}" theme="simple" />
	<table>
		<tr height="30">
			<td align="right">负责人:&nbsp;</td>
			<td><span id="employeeName"><s:property value="getEmployee(department.employeeId)" /></span> <span class="btn"><a href="#" onclick="chooseEmployee();">选择</a></span></td>
			<td align="right">上级部门:&nbsp;</td>
			<td><span id="parentDeptName"><s:property value="%{department.parentDepartment.name}" /></span> <span class="btn"><a href="#" onclick="chooseDepartment();">选择</a></span></td>
			<td align="right">所属机构:&nbsp;</td>
			<td><span id="parentOrgName"><s:property value="%{department.organization.orgName}" /></span> <span class="btn"><a href="#" onclick="chooseOrganization();">选择</a></span></td>
		</tr>
		<tr height="30">
			<td align="right">编码:&nbsp;</td>
			<td><s:textfield id="code" name="department.code" value="%{department.code}" theme="simple" /></td>
			<td align="right">名称:&nbsp;</td>
			<td><s:textfield id="name" name="department.name" value="%{department.name}" theme="simple" /></td>
			<td align="right">电话:&nbsp;</td>
			<td><s:textfield id="telephone" name="department.telephone" value="%{department.telephone}" theme="simple" /></td>
		</tr>
		<tr height="30">
			<td align="right">传真:&nbsp;</td>
			<td><s:textfield id="fax" name="department.fax" value="%{department.fax}" theme="simple" /></td>
			<td align="right">电邮:&nbsp;</td>
			<td><s:textfield id="email" name="department.email" value="%{department.email}" theme="simple" /></td>
			<td align="right">备注:&nbsp;</td>
			<td colspan="3"><s:textfield id="memo" name="department.memo" value="%{department.memo}" theme="simple" /></td>
		</tr>
	</table>
</div>
