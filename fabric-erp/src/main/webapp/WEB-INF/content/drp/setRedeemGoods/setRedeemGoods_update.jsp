<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
	$("#setRedeemGoodsForm").validationEngine();
	function chooseInventoryCurrentStock() {
		$.ajax({
		url : '${vix}/drp/setRedeemGoodsAction!goChooseInventoryCurrentStock.action',
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
						$("#inventoryCurrentStockId").val(rv[2]);
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
	<form id="setRedeemGoodsForm">
		<s:hidden id="inventoryCurrentStockId" name="inventoryCurrentStockId" value="%{setRedeemGoods.inventoryCurrentStock.id}" theme="simple" />
		<s:hidden id="setRedeemGoodsId" name="setRedeemGoodsId" value="%{setRedeemGoods.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">商品编码:&nbsp;</td>
					<td><input type="text" id="itemcode" name="itemcode" class="validate[required] text-input" value="${setRedeemGoods.inventoryCurrentStock.itemcode}" onchange="fieldChanged(this);" /><input class="btn" type="button" value="选择" onclick="chooseInventoryCurrentStock();" /><span style="color: red;">*</span></td>
					<td align="right">商品名称:&nbsp;</td>
					<td><input type="text" id="itemname" name="itemname" class="validate[required] text-input" value="${setRedeemGoods.inventoryCurrentStock.itemname}" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th>开始时间：</th>
					<td><input id="startTime" name="startTime" value="${setRedeemGoods.startTime}" type="text" class="validate[required] text-input" onchange="fieldChanged(this);" /><img onclick="showTime('startTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"><span style="color: red;">*</span></td>
					<th>结束时间：</th>
					<td><input id="endTime" name="endTime" value="${setRedeemGoods.endTime}" type="text" class="validate[required] text-input" onchange="fieldChanged(this);" /><img onclick="showTime('endTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">所需积分:&nbsp;</td>
					<td><input type="text" id="redeemPoints" name="redeemPoints" value="${setRedeemGoods.redeemPoints}" onchange="fieldChanged(this);" /></td>
					<td align="right">状态:&nbsp;</td>
					<td><select id="status" name="status" onchange="fieldChanged(this);">
							<option value="">请选择</option>
							<option value="正常" selected="selected">正常</option>
							<option value="停用">停用</option>
					</select></td>
				</tr>
			</table>
		</div>
	</form>
</div>