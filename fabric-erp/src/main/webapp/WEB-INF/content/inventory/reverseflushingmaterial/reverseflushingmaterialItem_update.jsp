<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="daForm">
		<s:hidden id="daId" name="reverseFlushingMaterialItem.id" value="%{reverseFlushingMaterialItem.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">${vv:varView("vix_mdm_item")}编码:&nbsp;</th>
					<td><input id="itemcode" name="itemcode" value="${reverseFlushingMaterialItem.itemcode}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">${vv:varView("vix_mdm_item")}名称:&nbsp;</th>
					<td><input id="itemname" name="itemname" value="${reverseFlushingMaterialItem.itemname}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">${vv:varView("vix_mdm_item")}类型:&nbsp;</th>
					<td><input id="itemtype" name="itemtype" value="${reverseFlushingMaterialItem.itemtype}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">规格型号:&nbsp;</th>
					<td><input id="specification" name="specification" value="${reverseFlushingMaterialItem.specification}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">数量:&nbsp;</th>
					<td><input id="requisitionCount" name="requisitionCount" value="${reverseFlushingMaterialItem.requisitionCount}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">单价:&nbsp;</th>
					<td><input id="price" name="price" value="${reverseFlushingMaterialItem.price}" type="text" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>
<script type="text/javascript">
	$("#daForm").validationEngine();
</script>