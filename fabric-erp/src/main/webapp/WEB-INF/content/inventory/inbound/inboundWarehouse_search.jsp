<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	var inventoryname = "";
	function loadInventoryName() {
		inventoryname = $('#inventoryname').val();
		inventoryname = Url.encode(inventoryname);
		inventoryname = Url.encode(inventoryname);
	}
	var warehousecode = "";
	function loadWarehouseCode() {
		warehousecode = $('#warehousecode').val();
		warehousecode = Url.encode(warehousecode);
		warehousecode = Url.encode(warehousecode);
	}
	var warehousePerson = "";
	function loadWarehousePerson() {
		warehousePerson = $('#warehousePerson').val();
		warehousePerson = Url.encode(warehousePerson);
		warehousePerson = Url.encode(warehousePerson);
	}
	var checkperson = "";
	function loadCheckPerson() {
		checkperson = $('#checkperson').val();
		checkperson = Url.encode(checkperson);
		checkperson = Url.encode(checkperson);
	}
	var itemname = "";
	function loadItemName() {
		itemname = $('#itemname').val();
		itemname = Url.encode(itemname);
		itemname = Url.encode(itemname);
	}
	var specification = "";
	function loadSpecification() {
		specification = $('#specification').val();
		specification = Url.encode(specification);
		specification = Url.encode(specification);
	}
</script>
<div class="content" style="margin-top: 15px; margin-bottom: -10px; overflow: hidden">
	<div class="box order_table" style="line-height: normal;">
		<table class="table-padding020">
			<tr height="30">
				<th>单据编码：</th>
				<td><input id="code1" name="code1" type="text" /></td>
				<th>主题：</th>
				<td><input id="inventoryname" name="inventoryname" type="text"></td>
			</tr>
			<tr height="30">
				<th>仓库名称 ：</th>
				<td><input id="warehousecode" name="warehousecode" type="text" /></td>
				<th>入库时间：</th>
				<td><input id="createTime" name="createTime" type="text" /><img onclick="showTime('createTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
			</tr>
			<tr height="30">
				<th>库管员：</th>
				<td><input id="warehousePerson" name="warehousePerson" type="text" /></td>
				<th>验收人：</th>
				<td><input id="checkperson" name="checkperson" type="text" /></td>
			</tr>
			<tr height="30">
				<th>商品编码：</th>
				<td><input id="itemcode" name="itemcode" type="text" /></td>
				<th>商品名称：</th>
				<td><input id="itemname" name="itemname" type="text" /></td>
			</tr>
			<tr height="30">
				<th>规格型号：</th>
				<td><input id="specification" name="specification" type="text" /></td>
				<th>SKU：</th>
				<td><input id="skuCode" name="skuCode" type="text" /></td>
			</tr>
		</table>
	</div>
</div>