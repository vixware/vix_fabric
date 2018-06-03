<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#memberShipCardForm").validationEngine();
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="memberShipCardForm">
		<s:hidden id="memberShipCardId" name="memberShipCardId" value="%{memberShipCard.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">会员卡余额:&nbsp;</td>
					<td><input type="text" id=balance_amount name="balance_amount" value="${memberShipCard.balance_amount}" readonly="readonly" /></td>
					<td align="right">存入金额:&nbsp;</td>
					<td><input type="text" id="balanceAmount" name="balanceAmount" class="validate[required] text-input" /><span style="color: red;">*</span></td>
				</tr>
			</table>
		</div>
	</form>
</div>