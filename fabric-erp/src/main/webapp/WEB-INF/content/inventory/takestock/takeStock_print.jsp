<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<div id="div_print">
		<table width="100%" cellspacing="0" cellpadding="4" border="0" bgcolor="#F7F7F7">
			<tbody>
				<tr>
					<td align="center" style="font-size: 15px">盘点单</td>
				</tr>
			</tbody>
		</table>
		<table width="100%" cellspacing="0" cellpadding="4" border="0" bgcolor="#F7F7F7" class="wimTransvouchTable">
			<tbody>
				<tr>
					<th align="right" height="25">盘点单号:</th>
					<td height="25">&nbsp;&nbsp;${stockTaking.code}</td>
					<th align="right" height="25">盘点日期:</th>
					<td height="25">&nbsp;&nbsp;<fmt:formatDate value="${stockTaking.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				</tr>
				<tr>
					<th align="right" height="25">盘点仓库:</th>
					<td height="25">&nbsp;&nbsp;${stockTaking.invWarehouse.name}</td>
					<th align="right" height="25">制单人:</th>
					<td height="25">&nbsp;&nbsp;${stockTaking.maker}</td>
				</tr>
				<tr>
					<th align="right" height="25">备注:</th>
					<td height="25">&nbsp;&nbsp;${stockTaking.memo}</td>
				</tr>
			</tbody>
		</table>
		<table width="100%" cellspacing="0" cellpadding="0" border="1" class="wimTransvouchTable">
			<tbody>
				<tr>
					<th height="25" align="center">商品编码</th>
					<th height="25" align="center">商品名称</th>
					<th height="25" align="center">规格型号</th>
					<th height="25" align="center">账面数量</th>
					<th height="25" align="center">盘点数量</th>
				</tr>
				<s:iterator value="stockTaking.stockTakingItem" var="stockTakingItem" status="s">
					<tr bgcolor="#FFFFFF">
						<td height="25" align="center">${stockTakingItem.itemcode}</td>
						<td height="25" align="center">${stockTakingItem.itemname}</td>
						<td height="25" align="center">${stockTakingItem.specification}</td>
						<td height="25" align="center">${stockTakingItem.cvquantity}</td>
						<td height="25" align="center">${stockTakingItem.cvcquantity}</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</div>
</div>
<style>
.wimTransvouchTable tr {
	font-size: 10px;
}
</style>