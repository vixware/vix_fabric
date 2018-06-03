<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<script src="${vix}/plugin/validengine/js/languages/jquery.validationEngine-en.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/plugin/validengine/js/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="contractMarketForm">
		<s:hidden id="daId" name="market.id" value="%{market.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">供应商:&nbsp;</th>
					<td><input id="supplier" name="market.supplier" value="${market.supplier}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">编码:&nbsp;</th>
					<td><input id="materialCode" name="market.materialCode" value="${market.materialCode}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">采购组织:&nbsp;</th>
					<td><input id="purchasingOrganization" name="market.purchasingOrganization" value="${market.purchasingOrganization}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">描述:&nbsp;</th>
					<td><input id="descriptions" name="market.descriptions" value="${market.descriptions}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">采购数量起:&nbsp;</th>
					<td><input id="purchasequantityRise" name="market.purchasequantityRise" value="${market.purchasequantityRise}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">采购数量止:&nbsp;</th>
					<td><input id="purchasequantityTo" name="market.purchasequantityTo" value="${market.purchasequantityTo}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">定价类型:&nbsp;</th>
					<td><input id="pricingType" name="market.pricingType" value="${market.pricingType}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">定价等级:&nbsp;</th>
					<td><input id="pricingGrade" name="market.pricingGrade" value="${market.pricingGrade}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">计算类型:&nbsp;</th>
					<td><input id="calculationType" name="market.calculationType" value="${market.calculationType}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">定价类别:&nbsp;</th>
					<td><input id="pricingCategory" name="market.pricingCategory" value="${market.pricingCategory}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">价格（折扣）:&nbsp;</th>
					<td><input id="price" name="market.price" value="${market.price}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">有效时间:&nbsp;</th>
					<td><input id="effectiveTime" name="effectiveTime" value="<fmt:formatDate value='${warning.effectiveTime}' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('effectiveTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
				<tr height="40">
					<th align="right">开始时间:&nbsp;</th>
					<td><input id="startTime" name="startTime" value="<fmt:formatDate value='${warning.startTime}' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('startTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
					<th align="right">结束时间:&nbsp;</th>
					<td><input id="endTime" name="endTime" value="<fmt:formatDate value='${warning.endTime }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('endTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<script type="text/javascript">
	$ ( "#contractMarketForm" ).validationEngine ( );
</script>