<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#retailPriceSurveyDetailForm").validationEngine();
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="retailPriceSurveyDetailForm">
		<s:hidden id="oiId" name="oiId" value="%{channelPrice.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">商品名称:&nbsp;</th>
					<td><input id="itemName" name="itemName" value="${channelPrice.itemName}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">渠道价格:&nbsp;</th>
					<td><input id="channelPrice" name="channelPrice" value="${channelPrice.channelPrice}" type="text" class="validate[required] text-input" /></td>
				</tr>
				<tr height="40">
					<th align="right">促销价格:&nbsp;</th>
					<td><input id="promotionalPrice" name="promotionalPrice" value="${channelPrice.promotionalPrice}" type="text" class="validate[required,custom[integer]] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">摆放返点:&nbsp;</th>
					<td><input id="layoutRebate" name="layoutRebate" value="${channelPrice.layoutRebate}" type="text" class="validate[required,custom[integer]] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">市场准入:&nbsp;</th>
					<td><input id="marketAccess" name="marketAccess" value="${channelPrice.marketAccess}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">账期:&nbsp;</th>
					<td><input id="paymentTerm" name="paymentTerm" value="${channelPrice.paymentTerm}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">现付贴现:&nbsp;</th>
					<td><input id="cashDiscount" name="cashDiscount" value="${channelPrice.cashDiscount}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">赠品率:&nbsp;</th>
					<td><input id="premiumRate" name="premiumRate" value="${channelPrice.premiumRate}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">批量折扣:&nbsp;</th>
					<td><input id="bulkDiscount" name="bulkDiscount" value="${channelPrice.bulkDiscount}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">时段返点:&nbsp;</th>
					<td><input id="timePeriod" name="timePeriod" value="${channelPrice.timePeriod}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">年终返点:&nbsp;</th>
					<td><input id="yearEndRebate" name="yearEndRebate" value="${channelPrice.yearEndRebate}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
			</table>
		</div>
	</form>
</div>