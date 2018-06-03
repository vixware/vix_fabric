<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>

<div class="content" style="margin-top: 15px; margin-bottom: -10px; overflow: hidden">
	<div class="box order_table" style="line-height: normal;">
		<table class="table-padding020">
			<tr height="30">
				<td align="right">编码：</td>
				<td><input type="text" name="code" id="code" class="int" /></td>
				<td align="right">主题 :</td>
				<td><input type="text" name="name" id="name" class="int" /></td>
			</tr>
			<tr>
				<td align="right">询价人：</td>
				<td><input id="purchasePerson" name="purchasePerson" type="text" /></td>
				<td align="right">询价日期:</td>
				<td><input id="appDate" name="appDate" readonly="readonly" type="text" onclick="showTime('appDate','yyyy-MM-dd')" /><img onclick="showTime('createTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
			</tr>
		</table>
	</div>
</div>
