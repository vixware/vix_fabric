<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<script type="text/javascript"> 
<!-- 
$("#commissionRatioItemForm").validationEngine();
//-->
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="commissionRatioItemForm">
		<s:hidden id="criId" name="commissionRatioItem.id" value="%{commissionRatioItem.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr>
					<td align="right">佣金比率编码 ：</td>
					<td><input type="text" size="30" id="crCode" name="commissionRatioItem.crCode" value="${commissionRatioItem.crCode}" /></td>
					<td align="right">佣金比率名称：</td>
					<td><input type="text" size="30" id="crName" name="commissionRatioItem.crName" value="${commissionRatioItem.crName}" /></td>
				</tr>
				<tr>
					<td align="right">类型：</td>
					<td><input type="text" size="30" id="type" name="commissionRatioItem.type" value="${commissionRatioItem.type}" /></td>
					<td align="right">数量单位 ：</td>
					<td><input type="text" size="30" id="amountUnit" name="commissionRatioItem.amountUnit" value="${commissionRatioItem.amountUnit}" /></td>
				</tr>

				<tr>
					<td align="right">金额单位：</td>
					<td><input type="text" size="30" id="sumUnit" name="commissionRatioItem.sumUnit" value="${commissionRatioItem.sumUnit}" /></td>
					<td align="right">返款比率 ：</td>
					<td><input type="text" size="30" id="returnRatio" name="commissionRatioItem.returnRatio" value="${commissionRatioItem.returnRatio}" /></td>
				</tr>
				<tr>
					<td align="right">从 ：</td>
					<td><input type="text" size="30" id="from" name="commissionRatioItem.from" value="${commissionRatioItem.from}" /></td>
					<td align="right">到：</td>
					<td><input type="text" size="30" id="to" name="commissionRatioItem.to" value="${commissionRatioItem.to}" /></td>
				</tr>
				<tr>
					<td align="right">返款金额：</td>
					<td><input type="text" size="30" id="returnAmount" name="commissionRatioItem.returnAmount" value="${commissionRatioItem.returnAmount}" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>