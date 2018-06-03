<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#orderItemForm").validationEngine();
	function chooseItem() {
		$.ajax({
		url : '${vix}/mdm/item/itemAction!goChooseItem.action',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 960,
			height : 580,
			title : "选择${vv:varView("vix_mdm_item")}",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != '') {
						$.ajax({
						url : '${vix}/mdm/item/itemAction!getItemEntityJson.action?itemIdSpecId=' + returnValue,
						cache : false,
						success : function(json) {
							var obj = eval(json);
							$("#itemcode").val(obj.code);
							$("#itemname").val(obj.name);
							$("#unitcost").val(obj.price);
							$("#producedate").val(getDate(obj.produceDate));
							$("#massdate").val(obj.qualityPeriod);
							$("#batchcode").val(obj.batchCode);
							$("#specification").val(obj.specification);
							$("#unit").val(obj.purchaseUnit);
						}
						});
					} else {
						asyncbox.success("请选择${vv:varView("vix_mdm_item")}!", "<s:text name='vix_message'/>");
						return false;
					}
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	}
	/** 选择单选供应商 */
	function chooseRadioSupplier() {
		$.ajax({
		url : '${vix}/inventory/inboundWarehouseAction!goChooseSupplier.action',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 1000,
			height : 500,
			title : "选择供应商",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						var rv = returnValue.split(",");
						$("#suppliercode").val(rv[1]);
					} else {
						asyncbox.success("请选择分类信息!", "提示信息");
					}
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	}
	function getDate(val) {
		if (val != null && val != "") {
			var d = new Date(val);
			return format(d);
		} else
			return "";
	}
	function format(date) {
		var y = date.getFullYear();
		var m = date.getMonth() + 1;
		var d = date.getDate();
		return y + '-' + m + '-' + d;
	}
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="orderItemForm">
		<s:hidden id="oiId" name="wimStockrecordlines.id" value="%{wimStockrecordlines.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">${vv:varView("vix_mdm_item")}名称:&nbsp;</th>
					<td><input id="itemname" name="itemname" value="${wimStockrecordlines.itemname}" type="text" /><input class="btn" type="button" value="选择" onclick="chooseItem();" /></td>
					<th align="right">规格型号:&nbsp;</th>
					<td><input id="specification" name="specification" value="${wimStockrecordlines.specification}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">${vv:varView("vix_mdm_item")}编码:&nbsp;</th>
					<td><input id="itemcode" name="itemcode" value="${wimStockrecordlines.itemcode}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">${vv:varView("vix_mdm_item")}价格:&nbsp;</th>
					<td><input id="unitcost" name="unitcost" value="${wimStockrecordlines.unitcost}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">生产日期:&nbsp;</th>
					<td><input id="producedate" name="producedate" class="validate[required] text-input" value="${wimStockrecordlines.producedate}" type="text" /><img onclick="showTime('producedate','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"><span style="color: red;">*</span></td>
					<th align="right">保质期天数:&nbsp;</th>
					<td><input id="massdate" name="massdate" value="${wimStockrecordlines.massdate}" type="text" class="validate[required,custom[integer]] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">单位:&nbsp;</th>
					<td><input id="unit" name="unit" value="${wimStockrecordlines.unit}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">${vv:varView("vix_mdm_item")}数量:&nbsp;</th>
					<td><input id="quantity" name="quantity" value="${wimStockrecordlines.quantity}" type="text" class="validate[required,custom[integer]] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr>
					<th align="right">供应商：&nbsp;</th>
					<td><input id="suppliercode" name="suppliercode" value="${wimStockrecordlines.suppliercode }" type="text" class="validate[required] text-input" /> <input class="btn" type="button" value="选择" onclick="chooseRadioSupplier();" /></td>
					<th align="right">批号:&nbsp;</th>
					<td><input id="batchcode" name="batchcode" value="${wimStockrecordlines.batchcode}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr>
					<th align="right">采购订单编码:&nbsp;</th>
					<td><input id="purchaseorderitemcode" name="purchaseorderitemcode" value="${wimStockrecordlines.purchaseorderitemcode}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
			</table>
		</div>
	</form>
</div>