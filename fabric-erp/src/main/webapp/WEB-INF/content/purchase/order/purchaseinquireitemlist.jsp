<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<s:if test="purchaseInquireItemsList.size > 0">
	<table style="width: 100%;">
		<tr>
			<th>选择</th>
			<th>编码</th>
			<th>名称</th>
		</tr>
		<s:iterator var="var" value="purchaseInquireItemsList">
			<tr height="30">
				<td><input type="checkbox" checked="checked"></td>
				<td>${var.itemCode}</td>
				<td>${var.itemName}</td>
			</tr>
		</s:iterator>
	</table>
</s:if>