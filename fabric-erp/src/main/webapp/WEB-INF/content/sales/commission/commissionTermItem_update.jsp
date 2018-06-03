<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<script type="text/javascript"> 
<!-- 
$("#commissionTermItemForm").validationEngine();
//-->
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="commissionTermItemForm">
		<s:hidden id="criId" name="commissionTermItem.id" value="%{commissionTermItem.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr>
					<td align="right">编码 ：</td>
					<td><input type="text" size="30" id="crCode" name="commissionTermItem.crCode" value="${commissionTermItem.crCode}" /></td>
					<td align="right">名称：</td>
					<td><input type="text" size="30" id="crName" name="commissionTermItem.crName" value="${commissionTermItem.crName}" /></td>
				</tr>
				<tr>
					<td align="right">类型：</td>
					<td><input type="text" size="30" id="type" name="commissionTermItem.type" value="${commissionTermItem.type}" /></td>
					<td align="right">数量单位 ：</td>
					<td><input type="text" size="30" id="amountUnit" name="commissionTermItem.amountUnit" value="${commissionTermItem.amountUnit}" /></td>
				</tr>

				<tr>
					<td align="right">金额单位：</td>
					<td><input type="text" size="30" id="sumUnit" name="commissionTermItem.sumUnit" value="${commissionTermItem.sumUnit}" /></td>
					<td align="right">返款比率 ：</td>
					<td><input type="text" size="30" id="returnTerm" name="commissionTermItem.returnTerm" value="${commissionTermItem.returnTerm}" /></td>
				</tr>
				<tr>
					<td align="right">从 ：</td>
					<td><input type="text" size="30" id="from" name="commissionTermItem.from" value="${commissionTermItem.from}" /></td>
					<td align="right">到：</td>
					<td><input type="text" size="30" id="to" name="commissionTermItem.to" value="${commissionTermItem.to}" /></td>
				</tr>
				<tr>
					<td align="right">返款金额：</td>
					<td><input type="text" size="30" id="returnAmount" name="commissionTermItem.returnAmount" value="${commissionTermItem.returnAmount}" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>