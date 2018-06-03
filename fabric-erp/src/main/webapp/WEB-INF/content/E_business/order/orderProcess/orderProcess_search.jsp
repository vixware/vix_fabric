<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<div class="content" style="margin-top: 15px; margin-bottom: -10px; overflow: hidden">
	<div class="box order_table" style="line-height: normal;">
		<table class="table-padding020">
			<tr height="30">
				<th>订单编码：</th>
				<td><input id="outerId" name="outerId" type="text"></td>
				<th>订单日期：</th>
				<td><input id="createTime" readonly="readonly" name="createTime" type="text" onclick="showTime('createTime','yyyy-MM-dd')" /> <img onclick="showTime('createTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
			</tr>
			<tr height="30">
				<th>店铺名称：</th>
				<td><input id="channelName" name="channelName" type="text"></td>
				<th>客户名称：</th>
				<td><input id="receiverName" name="receiverName" type="text" /></td>
			</tr>
			<tr height="30">
				<td align="right">联系电话:&nbsp;</td>
				<td><input type="text" id="receiverMobile" name="receiverMobile" /></td>
				<td align="right">购买数量:&nbsp;</td>
				<td><input type="text" id="num" name="num" /></td>
			</tr>
			<tr height="30">
				<td align="right">支付类型:&nbsp;</td>
				<td><input type="text" id="payTypeCn" name="payTypeCn" /></td>
				<th>收货地址：</th>
				<td><input id="receiverAddress" name="receiverAddress" type="text" /></td>
			</tr>
		</table>
	</div>
</div>
