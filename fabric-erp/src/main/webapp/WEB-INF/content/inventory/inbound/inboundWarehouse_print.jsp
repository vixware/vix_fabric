<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<div id="div_print">
		<table width="100%" cellspacing="0" cellpadding="4" border="0" bgcolor="#F7F7F7">
			<tbody>
				<tr>
					<td align="center" style="font-size: 15px">入库单</td>
				</tr>
			</tbody>
		</table>
		<table width="100%" cellspacing="0" cellpadding="4" border="0" bgcolor="#F7F7F7" class="pickinglisttable">
			<tbody>
				<tr>
					<th align="right" height="25">编码:</th>
					<td height="25">&nbsp;&nbsp;${stockRecords.code}</td>
					<th align="right" height="25">日期:</th>
					<td height="25">&nbsp;&nbsp;<fmt:formatDate value="${stockRecords.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				</tr>
				<tr>
					<th align="right" height="25">仓库:</th>
					<td height="25">&nbsp;&nbsp;${stockRecords.invWarehouse.name}</td>
					<th align="right" height="25">制单人:</th>
					<td height="25">&nbsp;&nbsp;${stockRecords.creator}</td>
				</tr>
				<tr>
					<th align="right" height="25">备注:</th>
					<td height="25" colspan="3">&nbsp;&nbsp;${stockRecords.memo}</td>
				</tr>
			</tbody>
		</table>
		<table width="100%" cellspacing="0" cellpadding="0" border="1" class="pickinglisttable">
			<tbody>
				<tr>
					<th height="25" align="center">货位</th>
					<th height="25" align="center">商品编码</th>
					<th height="25" align="center">商品名称</th>
					<th height="25" align="center">规格型号</th>
					<th height="25" align="center">SKU编码</th>
					<th height="25" align="center">价格</th>
					<th height="25" align="center">单位</th>
					<th height="25" align="center">数量</th>
				</tr>
				<s:iterator value="stockRecords.subStockRecordLines" var="stockRecordLines" status="s">
					<tr bgcolor="#FFFFFF">
						<td height="25" align="center">${stockRecordLines.invShelf.code}</td>
						<td height="25" align="center">${stockRecordLines.itemcode}</td>
						<td height="25" align="center">${stockRecordLines.itemname}</td>
						<td height="25" align="center">${stockRecordLines.specification}</td>
						<td height="25" align="center">${stockRecordLines.skuCode}</td>
						<td height="25" align="center">${stockRecordLines.unitcost}</td>
						<td height="25" align="center">${stockRecordLines.unit}</td>
						<td height="25" align="center">${stockRecordLines.quantity}</td>
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