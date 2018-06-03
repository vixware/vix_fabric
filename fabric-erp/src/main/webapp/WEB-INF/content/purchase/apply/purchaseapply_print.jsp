<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<div id="div_print">
		<table width="100%" cellspacing="0" cellpadding="4" border="0" bgcolor="#F7F7F7">
			<tbody>
				<tr>
					<td align="center" style="font-size: 15px">采购申请单</td>
				</tr>
			</tbody>
		</table>
		<table width="100%" cellspacing="0" cellpadding="4" border="0" bgcolor="#F7F7F7" class="pickinglisttable">
			<tbody>
				<tr>
					<th align="right" height="25">编码:</th>
					<td height="25">&nbsp;&nbsp;${purchaseApply.code}</td>
					<th align="right" height="25">日期:</th>
					<td height="25">&nbsp;&nbsp;<fmt:formatDate value="${purchaseApply.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				</tr>
				<tr>
					<th align="right" height="25">主题:</th>
					<td height="25">&nbsp;&nbsp;${purchaseApply.name}</td>
					<th align="right" height="25">采购人:</th>
					<td height="25">&nbsp;&nbsp;${purchaseApply.creator}</td>
				</tr>
				<tr>
					<th align="right" height="25">备注:</th>
					<td height="25" colspan="3">&nbsp;&nbsp;${purchaseApply.memo}</td>
				</tr>
			</tbody>
		</table>
		<table width="100%" cellspacing="0" cellpadding="0" border="1" class="pickinglisttable">
			<tbody>
				<tr>
					<th height="25" align="center">商品编码</th>
					<th height="25" align="center">商品名称</th>
					<th height="25" align="center">规格型号</th>
					<th height="25" align="center">SKU编码</th>
					<th height="25" align="center">价格</th>
					<th height="25" align="center">单位</th>
					<th height="25" align="center">数量</th>
				</tr>
				<s:iterator value="purchaseApply.purchaseApplyDetails" var="purchaseApplyDetails" status="s">
					<tr bgcolor="#FFFFFF">
						<td height="25" align="center">${purchaseApplyDetails.itemcode}</td>
						<td height="25" align="center">${purchaseApplyDetails.itemname}</td>
						<td height="25" align="center">${purchaseApplyDetails.specification}</td>
						<td height="25" align="center">${purchaseApplyDetails.skuCode}</td>
						<td height="25" align="center">${purchaseApplyDetails.unitcost}</td>
						<td height="25" align="center">${purchaseApplyDetails.unit}</td>
						<td height="25" align="center">${purchaseApplyDetails.quantity}</td>
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