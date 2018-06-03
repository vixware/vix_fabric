<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="wxpQyDepartmentForm">
		<s:hidden id="wxpQyDepartmentId" name="wxpQyDepartmentId" value="%{wxpQyDepartment.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">父部门ID:&nbsp;</td>
					<td><input type="text" id="parentId" name="parentId" class="validate[required] text-input" value="${wxpQyDepartment.parentId}" style="width: 450px;" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">部门名称:&nbsp;</td>
					<td><input type="text" id="wxpQyDepartmentName" name="wxpQyDepartmentName" class="validate[required] text-input" value="${wxpQyDepartment.name}" style="width: 450px;" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">次序:&nbsp;</td>
					<td><input type="text" id="sortOrder" name="sortOrder" value="${wxpQyDepartment.sortOrder}" style="width: 450px;" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>