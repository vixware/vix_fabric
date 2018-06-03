<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#tableUpdate tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("#tableUpdate tr:even").addClass("alt");
function chooseUser(){
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
							$("#userId").val(result[0]);
							$("#userAccount").html(result[1]);
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
					title:"选择部门",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							var result = returnValue.split(",");
							$("#deptId").val(result[0]);
							$("#deptName").html(result[1]);
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
		<s:hidden id="id" name="employee.id" value="%{employee.id}" theme="simple" />
		<s:hidden id="userId" name="employee.user.id" value="%{employee.user.id}" theme="simple" />
		<s:hidden id="deptId" name="employee.department.id" value="%{employee.department.id}" theme="simple" />
		<tr height="30">
			<td align="right">登陆账号:&nbsp;</td>
			<td colspan="2"><span id="userAccount"><s:property value="employee.user.account" /></span> <span class="btn"><a href="#" onclick="chooseUser();">选择</a></span></td>
			<td align="right">所属部门:&nbsp;</td>
			<td colspan="2"><span id="deptName"><s:property value="%{employee.department.name}" /></span> <span class="btn"><a href="#" onclick="chooseDepartment();">选择</a></span></td>
		</tr>
		<tr height="30">
			<td align="right">名称:&nbsp;</td>
			<td><s:textfield id="name" name="employee.name" value="%{employee.name}" theme="simple" /></td>
			<td align="right">编码:&nbsp;</td>
			<td><s:textfield id="code" name="employee.code" value="%{employee.code}" theme="simple" /></td>
			<td align="right">生日:&nbsp;</td>
			<td><s:textfield id="birthday" name="employee.birthday" value="%{employee.birthday}" theme="simple" /></td>
		</tr>
		<tr height="30">
			<td align="right">性别:&nbsp;</td>
			<td><s:if test="employee.gender == 0">
					<input type="radio" id="gender1" name="gender" value="1" />男
					<input type="radio" id="gender2" name="gender" value="0" checked="checked" />女
				</s:if> <s:elseif test="employee.gender == 1">
					<input type="radio" id="gender1" name="gender" value="1" checked="checked" />男
					<input type="radio" id="gender2" name="gender" value="0" />女
				</s:elseif> <s:else>
					<input type="radio" id="gender1" name="gender" value="1" />男
					<input type="radio" id="gender2" name="gender" value="0" />女
				</s:else></td>
			<td align="right">婚否:&nbsp;</td>
			<td><s:if test="employee.isMarriage == 0">
					<input type="radio" id="isMarriage1" name="isMarriage" value="1" />已婚
					<input type="radio" id="isMarriage2" name="isMarriage" value="0" checked="checked" />未婚
				</s:if> <s:elseif test="employee.isMarriage == 1">
					<input type="radio" id="isMarriage1" name="isMarriage" value="1" checked="checked" />已婚
					<input type="radio" id="isMarriage2" name="isMarriage" value="0" />未婚
				</s:elseif> <s:else>
					<input type="radio" id="isMarriage1" name="isMarriage" value="1" />已婚
					<input type="radio" id="isMarriage2" name="isMarriage" value="0" />未婚
				</s:else></td>
			<td align="right">毕业院校:&nbsp;</td>
			<td><s:textfield id="graduation" name="employee.graduation" value="%{employee.graduation}" theme="simple" /></td>
		</tr>
		<tr height="30">
			<td align="right">专业:&nbsp;</td>
			<td><s:textfield id="professional" name="employee.professional" value="%{employee.professional}" theme="simple" /></td>
			<td align="right">籍贯:&nbsp;</td>
			<td><s:textfield id="birthplace" name="employee.birthplace" value="%{employee.birthplace}" theme="simple" /></td>
			<td align="right">现住址:&nbsp;</td>
			<td><s:textfield id="currentResidence" name="employee.currentResidence" value="%{employee.currentResidence}" theme="simple" /></td>
		</tr>
		<tr height="30">
			<td align="right">民族:&nbsp;</td>
			<td><s:textfield id="national" name="employee.national" value="%{employee.national}" theme="simple" /></td>
			<td align="right">是否离职:&nbsp;</td>
			<td colspan="3"><s:if test="employee.isDemission == 0">
					<input type="radio" id="isDemission1" name="isDemission" value="1" />已离职
					<input type="radio" id="isDemission2" name="isDemission" value="0" checked="checked" />在职
				</s:if> <s:elseif test="employee.isDemission == 1">
					<input type="radio" id="isDemission1" name="isDemission" value="1" checked="checked" />已离职
					<input type="radio" id="isDemission2" name="isDemission" value="0" />在职
				</s:elseif> <s:else>
					<input type="radio" id="isDemission1" name="isDemission" value="1" />已离职
					<input type="radio" id="isDemission2" name="isDemission" value="0" />在职
				</s:else></td>
		</tr>
	</table>
</div>
