<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<div class="content" style="margin-top: 15px; margin-bottom: -10px; overflow: hidden">
	<div class="box order_table" style="line-height: normal;">
		<table class="table-padding020">
			<tr height="30">
				<th>编码：</th>
				<td><input id="code" name="code" type="text"></td>
				<th>主题：</th>
				<td><input id="name" name="name" type="text"></td>
			</tr>
			<tr height="30">
				<th>仓库：</th>
				<td><input id="warehousename" name="warehousename" type="text" /></td>
				<th>出库时间：</th>
				<td><input id="createTime" name="createTime" type="text" /><img onclick="showTime('createTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
			</tr>
			<tr height="30">
				<th>商品编码：</th>
				<td><input id="itemcode" name="itemcode" type="text" /></td>
				<th>商品名称：</th>
				<td><input id="itemname" name="itemname" type="text" /></td>
			</tr>
			<tr height="30">
				<th>库管员：</th>
				<td><input id="warehousePerson" name="warehousePerson" type="text" /></td>
			</tr>
		</table>
	</div>
</div>