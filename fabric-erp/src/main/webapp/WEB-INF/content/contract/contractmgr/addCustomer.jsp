<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#supplierForm").validationEngine();
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="supplierForm">
		<s:hidden id="srmId" name="supplier.id" value="%{supplier.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">客户编码:&nbsp;</th>
					<td><input id="code" name="customerAccount.code" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">客户名称:&nbsp;</th>
					<td><input id="name" name="customerAccount.name" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">助记名称:&nbsp;</th>
					<td><input id="code" name="customerAccount.code" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">客户种类:&nbsp;</th>
					<td><input id="customerType" name="customerAccount.customerType" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">联系人姓名:&nbsp;</th>
					<td><input id="code" name="customerAccount.code" type="text" class="validate[required] text-input" /></td>
					<th align="right">电话:&nbsp;</th>
					<td><input id="phoneOffice" name="customerAccount.phoneOffice" type="text" class="validate[required] text-input" /></td>
				</tr>
				<tr height="40">
					<th align="right">手机:&nbsp;</th>
					<td><input id="code" name="customerAccount.code" type="text" class="validate[required] text-input" /></td>
					<th align="right">邮件地址：</th>
					<td><input id="msnAccount" name="msnAccount" value="${customerAccount.msnAccount}" type="text" size="20" class="validate[required,custom[email]] text-input" /></td>
				</tr>
				<tr>
					<th align="right">备注：&nbsp;</th>
					<td colspan="3"><textarea id="remark" class="example" rows="1" style="width: 470px">${customerAccount.remark}</textarea></td>
				</tr>
			</table>
		</div>
	</form>
</div>