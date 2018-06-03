<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
$("#orderItemForm").validationEngine();
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="supplierCategoryForm">
		<s:hidden id="newId" name="supplierCategory.id" value="%{supplierCategory.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">专家:&nbsp;</th>
					<td colspan="3"><input id="newScName" name="supplierCategory.supplierCategory.name" value="${supplierCategory.supplierCategory.name}" type="text" readonly="readonly" style="background-color: #C0C0C0;" /> <input type="hidden" id="newScId" name="supplierCategory.supplierCategory.id" value="${supplierCategory.supplierCategory.id}" /> <input
						class="btn" type="button" value="选择" onclick="chooseSupplierCategory();" /></td>
				</tr>
				<tr height="40">
					<th align="right">帐号:&nbsp;</th>
					<td><input id="newAccount" value="${supplierAccount.account}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">密码:&nbsp;</th>
					<td><input id="newPassword" value="${supplierAccount.password}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">启用日期:&nbsp;</th>
					<td><input id="newStartTime" value="<fmt:formatDate value='${supplierAccount.startTime }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('newStartTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
					<th align="right">停用日期:&nbsp;</th>
					<td><input id="newEndTime" value="<fmt:formatDate value='${supplierAccount.endTime }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('newEndTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
				<tr height="40">
					<th align="right">激活:&nbsp;</th>
					<td><select id="newEnable" class="validate[required] text-input">
							<option>请选择</option>
							<option value="1">是</option>
							<option value="0">否</option>
					</select> <span style="color: red;">*</span></td>
				</tr>
			</table>
		</div>
	</form>
</div>