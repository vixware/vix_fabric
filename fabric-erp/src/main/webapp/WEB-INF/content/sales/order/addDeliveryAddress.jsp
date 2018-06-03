<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#daForm").validationEngine();
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="daForm">
		<s:hidden id="daId" name="deliveryAddress.id" value="%{deliveryAddress.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">订单编号:&nbsp;</th>
					<td><input id="orderCode" name="deliveryAddress.orderCode" value="${deliveryAddress.orderCode}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">国家:&nbsp;</th>
					<td><input id="contry" name="deliveryAddress.contry" value="${deliveryAddress.contry}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">省:&nbsp;</th>
					<td><input id="province" name="deliveryAddress.province" value="${deliveryAddress.province}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">城市:&nbsp;</th>
					<td><input id="city" name="deliveryAddress.city" value="${deliveryAddress.city}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">区:&nbsp;</th>
					<td><input id="district" name="deliveryAddress.district" value="${deliveryAddress.district}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">邮编:&nbsp;</th>
					<td><input id="postCode" name="deliveryAddress.postCode" value="${deliveryAddress.postCode}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">详细地址:&nbsp;</th>
					<td colspan="3"><input id="detail" name="deliveryAddress.detail" value="${deliveryAddress.detail}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
			</table>
		</div>
	</form>
</div>