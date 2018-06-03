<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<div id="div_print">
		<table width="100%" cellspacing="0" cellpadding="4" border="0" bgcolor="#F7F7F7">
			<tbody>
				<tr>
					<td align="center" style="font-size: 15px">小票</td>
				</tr>
			</tbody>
		</table>
		<table width="100%" cellspacing="0" cellpadding="4" border="0" bgcolor="#F7F7F7" class="pickinglisttable">
			<tbody>
				<tr>
					<th align="right" height="25">日期:</th>
					<td height="25">&nbsp;&nbsp;<fmt:formatDate value="${salesOrder.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<th align="right" height="25">制单人:</th>
					<td height="25">&nbsp;&nbsp;${salesOrder.creator}</td>
				</tr>
			</tbody>
		</table>
		<table width="100%" cellspacing="0" cellpadding="0" border="1" class="pickinglisttable">
			<tbody>
				<tr>
					<th height="25" align="center">名称</th>
					<th height="25" align="center">单价</th>
					<th height="25" align="center">数量</th>
					<th height="25" align="center">小计</th>
				</tr>
				<s:iterator value="salesOrder.saleOrderItems" var="saleOrderItem" status="s">
					<tr bgcolor="#FFFFFF">
						<td height="25" align="center">${saleOrderItem.title}</td>
						<td height="25" align="center">${saleOrderItem.price}</td>
						<td height="25" align="center">${saleOrderItem.amount}</td>
						<td height="25" align="center">${saleOrderItem.netTotal}</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</div>
</div>
<style>
.pickinglisttable tr {
	font-size: 10px;
}
</style>