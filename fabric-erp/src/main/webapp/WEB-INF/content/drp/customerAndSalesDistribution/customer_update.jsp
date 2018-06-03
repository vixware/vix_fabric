<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#customerForm").validationEngine();
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="customerForm">
		<s:hidden id="customerId" name="customerId" value="%{customer.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">编码:&nbsp;</th>
					<td><input id="customerCode" name="customerCode" value="${customer.code}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">姓名:&nbsp;</th>
					<td><input id="customerName" name="customerName" value="${customer.name}" type="text" class="validate[required] text-input" /></td>
				</tr>
				<tr height="40">
					<th align="right">客户类型:&nbsp;</th>
					<td><input id="type" name="type" value="${customer.type}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">电话传真:&nbsp;</th>
					<td><input id="phoneFax" name="phoneFax" value="${customer.phoneFax}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">积分值:&nbsp;</th>
					<td><input id="pointValue" name="pointValue" value="${customer.pointValue}" type="text" class="validate[required,custom[integer]] text-input" /> <span style="color: red;">*</span></td>
				</tr>
			</table>
		</div>
	</form>
</div>