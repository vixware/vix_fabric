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
			<tr height="30">
				<td align="right">采购人：</td>
				<td><input type="text" name="purchasePerson" id="purchasePerson" class="int" /></td>
				<td align="right">申请日期:</td>
				<td><input id="createTime" name="createTime" readonly="readonly" type="text" onclick="showTime('createTime','yyyy-MM-dd')" /><img onclick="showTime('createTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
			</tr>
			<tr height="30">
				<td align="right">状态：</td>
				<td><select id="status" name="status">
						<option value="">请选择</option>
						<option value="S1">待确认</option>
						<option value="S2">审批中</option>
						<option value="S3">已发货</option>
						<option value="S4">已完成</option>
				</select></td>
			</tr>
		</table>
	</div>
</div>
