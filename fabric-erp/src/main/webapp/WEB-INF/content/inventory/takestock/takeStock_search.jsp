<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<div class="content" style="margin-top: 15px; margin-bottom: -10px; overflow: hidden">
	<div class="box order_table" style="line-height: normal;">
		<table class="table-padding020">
			<tr height="30">
				<th>编码 :</th>
				<td><input type="text" name="stocktakingcode" id="stocktakingcode" class="int" /></td>
				<th>仓库:</th>
				<td><input type="text" name="warehousename" id="warehousename" class="int" /></td>
			</tr>
			<tr height="30">
				<th>商品编码：</th>
				<td><input id="itemcode" name="itemcode" type="text" class="int" /></td>
				<th>商品名称：</th>
				<td><input id="itemname" name="itemname" type="text" class="int" /></td>
			</tr>
			<tr height="30">
				<th>盘点人 :</th>
				<td><input type="text" name="personcode" id="personcode" class="int" /></td>
			</tr>
		</table>
	</div>
</div>