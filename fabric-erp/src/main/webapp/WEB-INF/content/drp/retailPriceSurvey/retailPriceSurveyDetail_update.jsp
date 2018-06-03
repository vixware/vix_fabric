<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#retailPriceSurveyDetailForm").validationEngine();
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="retailPriceSurveyDetailForm">
		<s:hidden id="oiId" name="oiId" value="%{retailPriceSurveyDetail.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">产品名称:&nbsp;</th>
					<td><input id="itemName" name="itemName" value="${retailPriceSurveyDetail.itemName}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">零售店名称:&nbsp;</th>
					<td><input id="retailStores" name="retailStores" value="${retailPriceSurveyDetail.retailStores}" type="text" class="validate[required] text-input" /></td>
				</tr>
				<tr height="40">
					<th align="right">标准零售价格:&nbsp;</th>
					<td><input id="standardRetailPrice" name="standardRetailPrice" value="${retailPriceSurveyDetail.standardRetailPrice}" type="text" class="validate[required,custom[integer]] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">金额折扣:&nbsp;</th>
					<td><input id="discountAmount" name="discountAmount" value="${retailPriceSurveyDetail.discountAmount}" type="text" class="validate[required,custom[integer]] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">是否捆绑销售:&nbsp;</th>
					<td><input id="isBundling" name="isBundling" value="${retailPriceSurveyDetail.isBundling}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">是否M送N销售:&nbsp;</th>
					<td><input id="isMSendNSales" name="isMSendNSales" value="${retailPriceSurveyDetail.isMSendNSales}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
			</table>
		</div>
	</form>
</div>