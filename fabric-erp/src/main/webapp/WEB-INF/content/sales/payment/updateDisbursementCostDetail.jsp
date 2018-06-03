<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#disbursementCostDetailForm").validationEngine();
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="disbursementCostDetailForm">
		<s:hidden id="dcdId" name="disbursementCostDetail.id" value="%{disbursementCostDetail.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">存货名称:&nbsp;</th>
					<td><input id="goodsName" name="disbursementCostDetail.goodsName" value="${disbursementCostDetail.goodsName}" type="text" /> <span style="color: red;">*</span></td>
					<th align="right">规格型号:&nbsp;</th>
					<td><input id="specification" name="disbursementCostDetail.specification" value="${disbursementCostDetail.specification}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">费用项目:&nbsp;</th>
					<td><input id="costItem" name="disbursementCostDetail.costItem" value="${disbursementCostDetail.costItem}" type="text" /> <span style="color: red;">*</span></td>
					<th align="right">代垫金额:&nbsp;</th>
					<td><input id="amount" name="disbursementCostDetail.amount" value="${disbursementCostDetail.amount}" type="text" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>