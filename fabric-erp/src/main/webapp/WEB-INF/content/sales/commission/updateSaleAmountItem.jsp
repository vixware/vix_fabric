<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#saleAmountItemForm").validationEngine();
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="saleAmountItemForm">
		<div class="box order_table" style="line-height: normal;">
			<s:hidden id="saId" name="saleAmountItem.id" value="%{saleAmountItem.id}" theme="simple" />
			<table>
				<tr height="30">
					<td align="right">期间(月份):&nbsp;</td>
					<td><input id="month" name="saleAmountItem.month" value="${saleAmountItem.month}" onClick="WdatePicker({dateFmt:'MM'})" class="validate[required] text-input Wdate" /></td>
					<td align="right">期间数量:&nbsp;</td>
					<td><input id="saleCountQuota" name="saleAmountItem.saleCountQuota" value="${saleAmountItem.saleCountQuota}" /></td>
				</tr>
				<tr height="30">
					<td align="right">期间金额:&nbsp;</td>
					<td colspan="3"><input id="saleAmountQuota" name="saleAmountItem.saleAmountQuota" value="${saleAmountItem.saleAmountQuota}" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>