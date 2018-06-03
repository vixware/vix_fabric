<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#expensesDetailForm").validationEngine();
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="expensesDetailForm">
		<s:hidden id="dcdId" name="expensesDetail.id" value="%{expensesDetail.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">存货名称:&nbsp;</th>
					<td><input id="goodsName" name="expensesDetail.goodsName" value="${expensesDetail.goodsName}" type="text" /> <span style="color: red;">*</span></td>
					<th align="right">规格型号:&nbsp;</th>
					<td><input id="specification" name="expensesDetail.specification" value="${expensesDetail.specification}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">费用项目:&nbsp;</th>
					<td><input id="expensesItem" name="expensesDetail.expensesItem" value="${expensesDetail.expensesItem}" type="text" /> <span style="color: red;">*</span></td>
					<th align="right">支出金额:&nbsp;</th>
					<td><input id="amount" name="expensesDetail.amount" value="${expensesDetail.amount}" type="text" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>