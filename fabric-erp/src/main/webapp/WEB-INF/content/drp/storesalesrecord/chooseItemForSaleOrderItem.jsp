<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#saleOrderItemForm").validationEngine();
	function chooseItem() {
		$.ajax({
		url : '${vix}/drp/storeSalesrecordAction!goInventoryCurrentStock.action',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 960,
			height : 580,
			title : "选择商品",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != '') {
						$.ajax({
						url : '${vix}/drp/storeSalesrecordAction!getInventoryCurrentStockJson.action?inventoryCurrentStockId=' + returnValue,
						cache : false,
						success : function(json) {
							var obj = eval(json);
							$("#itemId").val(obj.id);
							$("#itemCode").val(obj.code);
							$("#itemprice").val(obj.price);
							$("#itemname").val(obj.name);
						}
						});
					} else {
						asyncbox.success("请选择商品!", "<s:text name='vix_message'/>");
						return false;
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
		<s:hidden id="saleOrderItemId" name="saleOrderItemId" value="%{saleOrderItem.id}" theme="simple" />
		<s:hidden id="itemId" name="itemId" value="%{saleOrderItem.item.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">商品编码:&nbsp;</th>
					<td><input id="itemCode" name="itemCode" value="${saleOrderItem.itemCode}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">商品名称:&nbsp;</th>
					<td><input id="itemname" name="itemname" value="${saleOrderItem.item.name}" type="text" /><input class="btn" type="button" value="选择" onclick="chooseItem();" /></td>
				</tr>
				<tr height="40">
					<th align="right">价格:&nbsp;</th>
					<td><input id="itemprice" name="itemprice" value="${saleOrderItem.price}" type="text" class="validate[required,custom[integer]] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">数量:&nbsp;</th>
					<td><input id="saleOrderItemAmount" name="saleOrderItemAmount" value="${saleOrderItem.amount}" type="text" class="validate[required,custom[integer]] text-input" /> <span style="color: red;">*</span></td>
				</tr>
			</table>
		</div>
	</form>
</div>