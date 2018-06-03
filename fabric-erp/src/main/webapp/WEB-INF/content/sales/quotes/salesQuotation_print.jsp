<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<div id="div_print">
		<table width="100%" cellspacing="0" cellpadding="4" border="0" bgcolor="#F7F7F7">
			<tbody>
				<tr>
					<td align="center" style="font-size: 15px">销售报价单</td>
				</tr>
			</tbody>
		</table>
		<table width="100%" cellspacing="0" cellpadding="4" border="0" bgcolor="#F7F7F7" class="pickinglisttable">
			<tr>
				<td align="right" height="25">报价单编码:</td>
				<td height="25">${salesQuotation.code}</td>
				<td align="right" height="25">报价主题:</td>
				<td height="25">${salesQuotation.quoteSubject}</td>
			</tr>
			<tr>
				<td align="right" height="25">客户名称:</td>
				<td height="25">${salesQuotation.customerAccount.name}</td>
				<td align="right" height="25">报价单类型:</td>
				<td height="25">${salesQuotation.type}</td>
			</tr>
			<tr>
				<td align="right" height="25">部门:</td>
				<td height="25">${salesQuotation.dept.fullName}</td>
				<td align="right" height="25">销售组织:</td>
				<td height="25">${salesQuotation.salesOrg.fullName}</td>
			</tr>
			<tr>
				<td align="right" height="25">业务类型:</td>
				<td height="25">${salesQuotation.bizType}</td>
				<td align="right" height="25">地域:</td>
				<td height="25">${salesQuotation.regional.name}</td>
			</tr>
			<tr>
				<td align="right" height="25">城市:</td>
				<td height="25">${salesQuotation.city}</td>
				<td align="right" height="25">税率 :</td>
				<td height="25">${salesQuotation.tax}%范围(1-100)</td>
			</tr>
			<tr>
				<td align="right" height="25">交货日期:</td>
				<td height="25"><s:property value='formatDateToString(salesQuotation.dilveryDate)' /></td>
				<td align="right" height="25">单据日期:</td>
				<td height="25"><s:property value='formatDateToString(salesQuotation.billDate)' /></td>
			</tr>
			<tr>
				<td align="right" height="25">报价有效期从:</td>
				<td height="25"><s:property value='formatDateToString(salesQuotation.validQuotationFrom)' /></td>
				<td align="right" height="25">报价有效期至:</td>
				<td height="25"><s:property value='formatDateToString(salesQuotation.validQuotationTo)' /></td>
			</tr>
			<tr>
				<td align="right" height="25">备注:</td>
				<td colspan="3">${salesQuotation.memo}</td>
			</tr>
			</tbody>
		</table>
		<table width="100%" cellspacing="0" cellpadding="0" border="1" class="pickinglisttable">
			<tbody>
				<tr>
					<th height="25" align="center">编号</th>
					<th height="25" align="center">编码</th>
					<th height="25" align="center">名称</th>
					<th height="25" align="center">计量单位</th>
					<th height="25" align="center">数量</th>
					<th height="25" align="center">价格</th>
					<th height="25" align="center">无税价格</th>
					<th height="25" align="center">含税价格</th>
					<th height="25" align="center">备注</th>
				</tr>
				<s:iterator value="salesQuotation.salesQuotationItems" var="salesQuotationItems" status="s">
					<tr bgcolor="#FFFFFF">
						<td height="25" align="center">${salesQuotationItems.id}</td>
						<td height="25" align="center">${salesQuotationItems.itemCode}</td>
						<td height="25" align="center">${salesQuotationItems.itemName}</td>
						<td height="25" align="center">${salesQuotationItems.unit}</td>
						<td height="25" align="center">${salesQuotationItems.amount}</td>
						<td height="25" align="center">${salesQuotationItems.price}</td>
						<td height="25" align="center">${salesQuotationItems.netPrice}</td>
						<td height="25" align="center">${salesQuotationItems.taxPrice}</td>
						<td height="25" align="center">${salesQuotationItems.memo}</td>
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