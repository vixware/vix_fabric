<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
	$("#saleOrderItemForm").validationEngine();
	function chooseInventoryCurrentStock() {
		$.ajax({
		url : '${vix}/drp/reDeemAction!goChooseInventoryCurrentStock.action',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 1000,
			height : 500,
			title : "选择商品",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						var rv = returnValue.split(",");
						$("#itemcode").val(rv[0]);
						$("#itemname").val(rv[1]);
						$("#requiredPoint").val(rv[3]);
						$("#unit").val(rv[4]);
						$("#price").val(rv[5]);
					} else {
						asyncbox.success("请选择商品信息!", "提示信息");
					}
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	}
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="saleOrderItemForm">
		<s:hidden id="id" name="id" value="%{memberShipCard.id}" theme="simple" />
		<s:hidden id="saleOrderItemId" name="saleOrderItemId" value="%{saleOrderItem.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">商品编码:&nbsp;</td>
					<td><input type="text" id="itemcode" name="itemcode" class="validate[required] text-input" value="${saleOrderItem.itemCode}" onchange="fieldChanged(this);" /><input class="btn" type="button" value="选择" onclick="chooseInventoryCurrentStock();" /><span style="color: red;">*</span></td>
					<td align="right">商品名称:&nbsp;</td>
					<td><input type="text" id="itemname" name="itemname" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">计量单位:&nbsp;</td>
					<td><input type="text" id="unit" name="unit" value="${saleOrderItem.unit}" onchange="fieldChanged(this);" /></td>
					<td align="right">单价:&nbsp;</td>
					<td><input type="text" id="price" name="price" value="${saleOrderItem.price}" onchange="fieldChanged(this);" /></td>
				</tr>
				<tr height="40">
					<td align="right">所需积分:&nbsp;</td>
					<td><input type="text" id="requiredPoint" name="requiredPoint" onchange="fieldChanged(this);" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>