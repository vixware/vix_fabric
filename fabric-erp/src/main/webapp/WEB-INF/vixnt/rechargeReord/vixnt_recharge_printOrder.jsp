<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div class="content" style="text-align: left; padding: 25px 25px;">
	<c:if test="${rechargeRecord != null}">
		<div style="page-break-after: always; marks: none">
			<table width="100%" cellspacing="0" cellpadding="4" border="0">
				<tr>
					<td align="center" style="font-size: 15px">${rechargeRecord.channelDistributor.name}</td>
				</tr>
			</table>
			<table width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<th width="30%" align="right" style="font-size: 13px">订单编码:</th>
					<td width="70%" align="left" style="font-size: 13px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rechargeRecord.code}</td>
				</tr>
				<tr>
					<th width="30%" align="right" style="font-size: 13px">充值时间:</th>
					<td width="70%" align="left" style="font-size: 13px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${rechargeRecord.payDateTimeStr}</td>
				</tr>
			</table>
			<table width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<th width="15%" valign="top" align="right" style="font-size: 13px"></th>
					<th width="30%" align="right" style="font-size: 13px">充值金额:</th>
					<td width="35%" style="font-size: 13px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;￥${rechargeRecord.payMoney}</td>
				</tr>
			</table>
		</div>
	</c:if>
</div>