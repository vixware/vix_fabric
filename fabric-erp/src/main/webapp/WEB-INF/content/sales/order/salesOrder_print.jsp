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
			<tbody>
				<tr>
					<td align="right" height="25">编码:</td>
					<td height="25">${salesOrder.code}</td>
					<td align="right" width="10%">主题:</td>
					<td height="25">${salesOrder.title}</td>
				</tr>
				<tr>
					<td align="right" height="25">单据日期:</td>
					<td height="25"><s:property value='formatDateToString(salesOrder.billDate)' /></td>
					<s:if test="tag == 'fromQuote'">
						<td align="right" height="25">来源单据:</td>
						<td height="25"><span>销售报价单</span></td>
					</s:if>
					<s:else>
						<td align="right" height="25">来源单据:</td>
						<td height="25">无</td>
					</s:else>
				</tr>
				<tr>
					<td align="right" height="25">客户名称:</td>
					<td height="25">${salesOrder.customerAccount.name}</td>
					<td align="right" height="25">联系人:</td>
					<td height="25">${salesOrder.contactPerson.name}</td>
				</tr>
				<tr>
					<td align="right" height="25">项目:</td>
					<td height="25">${salesOrder.crmProject.subject}</td>
					<td align="right" height="25">业务类型:</td>
					<td height="25">${salesOrder.bizType}</td>
				</tr>
				<tr>
					<td align="right" height="25">币种:</td>
					<td height="25">${salesOrder.currencyType.name}</td>
					<td align="right" height="25">地域:</td>
					<td height="25">${salesOrder.regional.name}</td>
				</tr>
				<tr>
					<td align="right" height="25">销售组织:</td>
					<td height="25">${salesOrder.saleOrg.fullName}</td>
					<td align="right" height="25">计划发运日期:</td>
					<td height="25"><s:property value='formatDateToTimeString(salesOrder.deliveryTimeInPlan)' /></td>
				</tr>
				<tr>
					<td align="right" height="25">承诺日期:</td>
					<td height="25"><s:property value='formatDateToTimeString(salesOrder.promiseTime)' /></td>
					<td align="right" height="25">过账日期:</td>
					<td height="25"><s:property value='formatDateToTimeString(salesOrder.postingTime)' /></td>
				</tr>
				<tr>
					<td align="right" height="25">状态:</td>
					<td height="25"><s:if test="salesOrder.status == 0">禁用</s:if> <s:elseif test="salesOrder.status == 1">激活</s:elseif></td>
					<td align="right" height="25">业务员:</td>
					<td height="25">${salesOrder.salePerson.name}</td>
				</tr>
				<tr>
					<td align="right" height="25">开始日期:</td>
					<td height="25"><s:property value='formatDateToTimeString(salesOrder.startTime)' /></td>
					<td align="right" height="25">结束日期:</td>
					<td height="25"><s:property value='formatDateToTimeString(salesOrder.endTime)' /></td>
				</tr>
				<tr>
					<td align="right" height="25">备注</td>
					<td colspan="3">${salesOrder.memo}</td>
				</tr>
				<s:hidden id="id" name="salesOrder.id" value="%{salesOrder.id}" theme="simple" />
			</tbody>
		</table>
		<table width="100%" cellspacing="0" cellpadding="0" border="1" class="pickinglisttable">
			<tbody>
				<tr>
					<th height="25" align="center">编号</th>
					<th height="25" align="center">条形码</th>
					<th height="25" align="center">SKU</th>
					<th height="25" align="center">编码</th>
					<th height="25" align="center">名称</th>
					<th height="25" align="center">规格型号</th>
					<th height="25" align="center">主计量单位</th>
					<th height="25" align="center">数量</th>
					<th height="25" align="center">含税单价</th>
					<th height="25" align="center">金额</th>
					<th height="25" align="center">无税单价</th>
					<th height="25" align="center">无税金额</th>
					<th height="25" align="center">税率</th>
					<th height="25" align="center">折扣率</th>
					<th height="25" align="center">预发货日期</th>
					<th height="25" align="center">备注</th>
				</tr>
				<s:iterator value="salesOrder.saleOrderItem" var="salesOrder" status="s">
					<tr bgcolor="#FFFFFF">
						<td height="25" align="center">${salesOrder.id}</td>
						<td height="25" align="center">${salesOrder.barCode}</td>
						<td height="25" align="center">${salesOrder.itemName}</td>
						<td height="25" align="center">${salesOrder.skuCode}</td>
						<td height="25" align="center">${salesOrder.itemCode}</td>
						<td height="25" align="center">${salesOrder.itemName}</td>
						<td height="25" align="center">${salesOrder.specification}</td>
						<td height="25" align="center">${salesOrder.measureUnit}</td>
						<td height="25" align="center">${salesOrder.amount}</td>
						<td height="25" align="center">${salesOrder.taxPrice}</td>
						<td height="25" align="center">${salesOrder.price}</td>
						<td height="25" align="center">${salesOrder.netPrice}</td>
						<td height="25" align="center">${salesOrder.netTotal}</td>
						<td height="25" align="center">${salesOrder.tax}</td>
						<td height="25" align="center">${salesOrder.discount}</td>
						<td height="25" align="center">${salesOrder.requireDate}</td>
						<td height="25" align="center">${salesOrder.memo}</td>
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