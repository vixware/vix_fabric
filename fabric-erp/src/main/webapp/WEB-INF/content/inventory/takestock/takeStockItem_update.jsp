<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="daForm">
		<s:hidden id="daId" name="wimStockrecordlines.id" value="%{wimStockrecordlines.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">${vv:varView("vix_mdm_item")}编码:&nbsp;</th>
					<td><input id="itemcode" name="itemcode" value="${wimStockrecordlines.itemcode}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">${vv:varView("vix_mdm_item")}名称:&nbsp;</th>
					<td><input id="itemname" name="itemname" value="${wimStockrecordlines.itemname}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">${vv:varView("vix_mdm_item")}类型:&nbsp;</th>
					<td><input id="itemtype" name="itemtype" value="${wimStockrecordlines.itemtype}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">规格型号:&nbsp;</th>
					<td><input id="specification" name="specification" value="${wimStockrecordlines.specification}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">账面数量:&nbsp;</th>
					<td><input id="cvquantity" name="cvquantity" value="${wimStockrecordlines.cvquantity}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">盘点数量:&nbsp;</th>
					<td><input id="cvcquantity" name="cvcquantity" value="${wimStockrecordlines.cvcquantity}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">批号:&nbsp;</th>
					<td colspan="3"><input id="cvbatch" name="cvbatch" value="${wimStockrecordlines.cvbatch}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr>
					<th align="right">失效日期:&nbsp;</th>
					<td><input id="cvdisdate" name="cvdisdate" value="${wimStockrecordlines.cvdisdate}" type="text" /><img onclick="showTime('cvdisdate','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<script type="text/javascript">
	$("#daForm").validationEngine();
</script>