<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#exchangeRateForm").validationEngine();
//-->
</script>
<form id="exchangeRateForm">
	<div style="padding: 5px;">
		<s:hidden id="id" name="exchangeRate.id" value="%{exchangeRate.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="30">
					<td align="right">本币:&nbsp;</td>
					<td><s:select id="currentCT" listValue="name" listKey="id" value="%{exchangeRate.currentCurrencyType.id}" multiple="false" theme="simple" headerKey="-1" headerValue="---请选择---" list="%{listCurrencyType}" /></td>
					<td align="right">汇率:&nbsp;</td>
					<td><input id="exchangeRateVal" name="exchangeRate.exchangeRate" value="${exchangeRate.exchangeRate}" class="validate[required] text-input" /><span style="color: red;">*</span></td>
					<td align="right">外币:&nbsp;</td>
					<td><s:select id="foreignCT" listValue="name" listKey="id" value="%{exchangeRate.foreignCurrencyType.id}" multiple="false" theme="simple" headerKey="-1" headerValue="---请选择---" list="%{listCurrencyType}" /></td>
				</tr>
			</table>
		</div>
	</div>
</form>