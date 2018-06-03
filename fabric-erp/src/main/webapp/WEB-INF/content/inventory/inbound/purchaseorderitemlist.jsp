<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<s:if test="purchaseOrderLineItemList.size > 0">
	<table style="width: 100%;">
		<tr>
			<th>选择</th>
			<th>编码</th>
			<th>名称</th>
			<th>规格</th>
		</tr>
		<s:iterator var="purchaseOrderLineItem" value="purchaseOrderLineItemList">
			<tr height="30">
				<td><input type="checkbox" checked="checked"></td>
				<td>${purchaseOrderLineItem.itemCode}</td>
				<td>${purchaseOrderLineItem.itemName}</td>
				<td>${purchaseOrderLineItem.specification}</td>
			</tr>
		</s:iterator>
	</table>
</s:if>