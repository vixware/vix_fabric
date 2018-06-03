<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>

<div class="content" style="margin-top: 15px; margin-bottom: -10px; overflow: hidden">
	<div class="box order_table" style="line-height: normal;">
		<table class="table-padding020">
			<tr height="30">
				<td align="right">编码：</td>
				<td><input id="code" name="code" type="text" size="30" /></td>
				<td align="right">主题：</td>
				<td><input id="name" name="name" type="text" size="30" /></td>
			</tr>
			<tr>
				<td align="right">供应商名称：</td>
				<td><input id="supplierName" name="supplierName" type="text" size="30" /></td>
				<td align="right">需求部门：</td>
				<td><input id="requireDepartment" name="requireDepartment" type="text" size="30" /></td>
			</tr>
			<tr>
				<td align="right">过账日期：</td>
				<td><input id="postingDate" name="postingDate" type="text" readonly="readonly" onclick="showTime('postingDate','yyyy-MM-dd')" /> <img onclick="showTime('postingDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				<td align="right">交货日期：</td>
				<td><input id="deliveryDate" name="deliveryDate" readonly="readonly" type="text" onclick="showTime('deliveryDate','yyyy-MM-dd')" /> <img onclick="showTime('deliveryDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
			</tr>
			<tr>
				<td align="right">采购人：</td>
				<td><input id="purchasePerson" name="purchasePerson" type="text" size="30" /></td>
				<td align="right">联系人：</td>
				<td><input id="contactPerson" name="contactPerson" type="text" size="30" /></td>
			</tr>
		</table>
	</div>
</div>
