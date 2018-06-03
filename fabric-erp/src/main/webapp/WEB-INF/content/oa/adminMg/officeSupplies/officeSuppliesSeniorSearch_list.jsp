<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>

<div class="content" style="margin-top: 15px; margin-bottom: -10px; overflow: hidden">
	<div class="box order_table" style="line-height: normal;">
		<table class="table-padding020">
			<tr height="30">
				<td>型号 : <input type="text" name="model" id="model" class="int" /></td>
				<td>名称 : <input type="text" name="officeSuppliesName" id="officeSuppliesName" class="int" /></td>
			</tr>
			<tr height="30">
				<td>编码 : <input type="text" name="officeSuppliesCode" id="officeSuppliesCode" class="int" /></td>
				<td>库存 : <input type="text" name="currentInventory" id="currentInventory" class="int" /></td>
			</tr>
			<tr height="30">
				<td>最低库存 : <input type="text" name="lowestVigilance" id="lowestVigilance" class="int" /></td>
				<td>最高库存 : <input type="text" name="maximumVigilance" id="maximumVigilance" class="int" /></td>
			</tr>
			<tr height="30">
				<td>供应商 : <input type="text" name="supplier" id="supplier" class="int" /></td>
			</tr>
		</table>
	</div>
</div>