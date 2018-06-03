<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<script src="${vix}/plugin/validengine/js/languages/jquery.validationEngine-en.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/plugin/validengine/js/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="priceConditionsForm">
		<s:hidden id="daId" name="price.id" value="%{price.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">开始数量:&nbsp;</th>
					<td><input id="startQuantity" name="price.startQuantity" value="${price.startQuantity}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">截止数量:&nbsp;</th>
					<td><input id="cutoffQuantity" name="price.cutoffQuantity" value="${price.cutoffQuantity}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">折扣:&nbsp;</th>
					<td><input id="discount" name="price.discount" value="${price.discount}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">数量（从）:&nbsp;</th>
					<td><input id="numberFrom" name="conditions.numberFrom" value="${conditions.numberFrom}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">数量（到）:&nbsp;</th>
					<td><input id="numberto" name="price.numberto" value="${price.numberto}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">采购价格条件:&nbsp;</th>
					<td><input id="purchasepriceCondition" name="price.purchasepriceCondition" value="${price.purchasepriceCondition}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<script type="text/javascript">
	$ ( "#priceConditionsForm" ).validationEngine ( );
</script>