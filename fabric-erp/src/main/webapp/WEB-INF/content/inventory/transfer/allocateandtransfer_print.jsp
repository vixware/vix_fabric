<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<div id="div_print">
		<table width="100%" cellspacing="0" cellpadding="4" border="0" bgcolor="#F7F7F7">
			<tbody>
				<tr>
					<td align="center" style="font-size: 15px">调拨单</td>
				</tr>
			</tbody>
		</table>
		<table width="100%" cellspacing="0" cellpadding="4" border="0" bgcolor="#F7F7F7" class="wimTransvouchTable">
			<tbody>
				<tr>
					<th align="right" height="25">编码:</th>
					<td height="25">&nbsp;&nbsp;${wimTransvouch.code}</td>
					<th align="right" height="25">日期:</th>
					<td height="25">&nbsp;&nbsp;<fmt:formatDate value="${wimTransvouch.tvdate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				</tr>
				<tr>
					<th align="right" height="25">转出仓库:</th>
					<td height="25">&nbsp;&nbsp;${wimTransvouch.outInvWarehouse.name}</td>
					<th align="right" height="25">转入仓库:</th>
					<td height="25">&nbsp;&nbsp;${wimTransvouch.inInvWarehouse.name}</td>
				</tr>
				<tr>
					<th align="right" height="25">调拨人:</th>
					<td height="25">&nbsp;&nbsp;${wimTransvouch.allocationPerson}</td>
					<th align="right" height="25">备注:</th>
					<td height="25">&nbsp;&nbsp;${wimTransvouch.memo}</td>
				</tr>
			</tbody>
		</table>
		<table width="100%" cellspacing="0" cellpadding="0" border="1" class="wimTransvouchTable">
			<tbody>
				<tr>
					<th height="25" align="center">调入货位</th>
					<th height="25" align="center">商品编码</th>
					<th height="25" align="center">商品名称</th>
					<th height="25" align="center">SKU编码</th>
					<th height="25" align="center">单位</th>
					<th height="25" align="center">数量</th>
				</tr>
				<s:iterator value="wimTransvouch.wimTransvouchitem" var="wimTransvouchitem" status="s">
					<tr bgcolor="#FFFFFF">
						<td height="25" align="center">${wimTransvouchitem.invShelf.code}</td>
						<td height="25" align="center">${wimTransvouchitem.itemcode}</td>
						<td height="25" align="center">${wimTransvouchitem.itemname}</td>
						<td height="25" align="center">${wimTransvouchitem.skuCode}</td>
						<td height="25" align="center">${wimTransvouchitem.masterUnit}</td>
						<td height="25" align="center">${wimTransvouchitem.tvquantity}</td>
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