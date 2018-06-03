<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="daForm">
		<s:hidden id="daId" name="stockRecordLines.id" value="%{stockRecordLines.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">${vv:varView("vix_mdm_item")}编码:&nbsp;</th>
					<td><input id="itemcode" name="stockRecordLines.itemcode" value="${stockRecordLines.itemcode}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">${vv:varView("vix_mdm_item")}名称:&nbsp;</th>
					<td><input id="itemname" name="stockRecordLines.itemname" value="${stockRecordLines.itemname}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">${vv:varView("vix_mdm_item")}类型:&nbsp;</th>
					<td><input id="itemtype" name="stockRecordLines.itemtype" value="${stockRecordLines.itemtype}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">规格型号:&nbsp;</th>
					<td><input id="specification" name="stockRecordLines.specification" value="${stockRecordLines.specification}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">数量:&nbsp;</th>
					<td><input id="quantity" name="stockRecordLines.quantity" value="${stockRecordLines.quantity}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">单价:&nbsp;</th>
					<td><input id="unitcost" name="stockRecordLines.unitcost" value="${stockRecordLines.unitcost}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">金额:&nbsp;</th>
					<td colspan="3"><input id="price" name="stockRecordLines.price" value="${stockRecordLines.price}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<script type="text/javascript">
	$("#daForm").validationEngine();
</script>