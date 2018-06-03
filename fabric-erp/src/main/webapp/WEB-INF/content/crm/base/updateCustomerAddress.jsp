<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#customerAddressForm").validationEngine();
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="customerAddressForm">
		<div id="updateCredential">
			<s:hidden id="id" name="customerAddress.id" value="%{customerAddress.id}" theme="simple" />
			<div class="box order_table" style="line-height: normal;">
				<table>
					<tr height="30">
						<td align="right">国家:&nbsp;</td>
						<td><input id="country" name="customerAddress.country" value="${customerAddress.country}" class="validate[required] text-input" /><span style="color: red;">*</span></td>
						<td align="right">地区:&nbsp;</td>
						<td><input id="region" name="customerAddress.region" value="${customerAddress.region}" /></td>
					</tr>
					<tr height="30">
						<td align="right">省:&nbsp;</td>
						<td><input id="province" name="customerAddress.province" value="${customerAddress.province}" /></td>
						<td align="right">城市:&nbsp;</td>
						<td><input id="city" name="customerAddress.city" value="${customerAddress.city}" /></td>
					</tr>
					<tr height="30">
						<td align="right">街道:&nbsp;</td>
						<td><input id="street" name="customerAddress.street" value="${customerAddress.street}" /></td>
						<td align="right">邮编:&nbsp;</td>
						<td><input id="postCode" name="customerAddress.postCode" value="${customerAddress.postCode}" /></td>
					</tr>
					<tr height="30">
						<td align="right">地址:&nbsp;</td>
						<td colspan="3"><input id="address" name="customerAddress.address" value="${customerAddress.address}" /></td>
					</tr>
				</table>
			</div>
		</div>
	</form>
</div>