<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<script src="${vix}/plugin/validengine/js/languages/jquery.validationEngine-en.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/plugin/validengine/js/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script>
<link href="${vix}/common/css/easyui/themes/gray/easyui.css" rel="stylesheet" />
<link href="${vix}/common/css/easyui/themes/icon.css" rel="stylesheet" />
<script src="${vix}/common/js/jquery.easyui.min.js" type="text/javascript"></script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="daForm">
		<s:hidden id="daId" name="contractMarket.id" value="%{contractMarket.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">客户:&nbsp;</th>
					<td><input id="supplier" name="contractMarket.supplier" value="${contractMarket.supplier}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">${vv:varView("vix_mdm_item")}编码:&nbsp;</th>
					<td><input id="materialCode" name="contractMarket.materialCode" value="${contractMarket.materialCode}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">采购组织:&nbsp;</th>
					<td><input id="purchasingOrganization" name="contractMarket.purchasingOrganization" value="${contractMarket.purchasingOrganization}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">描述:&nbsp;</th>
					<td><input id="descriptions" name="contractMarket.descriptions" value="${contractMarket.descriptions}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">采购数量起:&nbsp;</th>
					<td><input id="purchasequantityRise" name="contractMarket.purchasequantityRise" value="${contractMarket.purchasequantityRise}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">采购数量止:&nbsp;</th>
					<td><input id="purchasequantityTo" name="contractMarket.purchasequantityTo" value="${contractMarket.purchasequantityTo}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">定价类型:&nbsp;</th>
					<td><input id="pricingType" name="contractMarket.pricingType" value="${contractMarket.pricingType}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">定价等级:&nbsp;</th>
					<td><input id="pricingGrade" name="contractMarket.pricingGrade" value="${contractMarket.pricingGrade}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">计算类型:&nbsp;</th>
					<td><input id="calculationType" name="contractMarket.calculationType" value="${contractMarket.calculationType}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">定价类别:&nbsp;</th>
					<td><input id="pricingCategory" name="contractMarket.pricingCategory" value="${contractMarket.pricingCategory}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">价格（折扣）:&nbsp;</th>
					<td><input id="price" name="contractMarket.price" value="${contractMarket.price}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">有效时间:&nbsp;</th>
					<td><input id="effectiveTime" name="effectiveTime" value="<fmt:formatDate value='${contractMarket.effectiveTime }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('effectiveTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
				<tr height="40">
					<th align="right">开始时间:&nbsp;</th>
					<td><input id="startTime" name="startTime" value="<fmt:formatDate value='${contractMarket.startTime }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('startTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
					<th align="right">结束时间:&nbsp;</th>
					<td><input id="endTime" name="endTime" value="<fmt:formatDate value='${contractMarket.endTime }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('endTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<script type="text/javascript">
	$ ( "#daForm" ).validationEngine ( );
</script>