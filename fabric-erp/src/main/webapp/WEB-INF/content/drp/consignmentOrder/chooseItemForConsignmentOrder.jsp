<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#consignmentOrderItemForm").validationEngine();
	function chooseItem() {
		$.ajax({
		url : '${vix}/inventory/inboundWarehouseAction!goChooseItem.action',
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
						url : '${vix}/inventory/inboundWarehouseAction!getItemEntityJson.action?itemIdSpecId=' + returnValue,
						cache : false,
						success : function(json) {
							var obj = eval(json);
							$("#itemCode").val(obj.itemcode);
							$("#itemName").val(obj.itemname);
							$("#price").val(obj.price);
							$("#specification").val(obj.specification);
							$("#unit").val(obj.masterUnit);
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
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="consignmentOrderItemForm">
		<s:hidden id="consignmentOrderItemId" name="consignmentOrderItemId" value="%{consignmentOrderItem.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">编码:&nbsp;</th>
					<td><input id="itemCode" name="itemCode" value="${consignmentOrderItem.itemCode}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">商品名称:&nbsp;</th>
					<td><input id="itemName" name="itemName" value="${consignmentOrderItem.itemName}" type="text" class="validate[required] text-input" /><input class="btn" type="button" value="选择" onclick="chooseItem();" /></td>
				</tr>
				<tr height="40">
					<th align="right">规格型号:&nbsp;</th>
					<td><input id="specification" name="specification" value="${consignmentOrderItem.specification}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">单位:&nbsp;</th>
					<td><input id="unit" name="unit" value="${consignmentOrderItem.unit}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">价格:&nbsp;</th>
					<td><input id="price" name="price" value="${consignmentOrderItem.price}" type="text" class="validate[required,custom[integer]] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">数量:&nbsp;</th>
					<td><input id="amount" name="amount" value="${consignmentOrderItem.amount}" type="text" class="validate[required,custom[integer]] text-input" /> <span style="color: red;">*</span></td>
				</tr>
			</table>
		</div>
	</form>
</div>