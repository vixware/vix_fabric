<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#warehouseform").validationEngine();
	function choosewarehouse() {
		$.ajax({
		url : '${vix}/inventory/warehouseAction!goChooseWarehouse.action',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 560,
			height : 340,
			title : "选择仓库",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						var result = returnValue.split(",");
						$("#warehouseId").val(result[0]);
						$("#warehouseName").val(result[1]);
					} else {
						asyncbox.success("请选择仓库信息!", "提示信息");
					}
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	}
	function chooseShelf() {
		$.ajax({
		url : '${vix}/inventory/warehouseAction!goChooseShelf.action',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 560,
			height : 340,
			title : "选择货位",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						var result = returnValue.split(",");
						$("#invShelfId").val(result[0]);
						$("#invShelfName").val(result[1]);
						setWarehouse(result[0]);
					} else {
						asyncbox.success("请选择货位信息!", "提示信息");
					}
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	}
	function setWarehouse(shelfId) {
		$.ajax({
		url : '${vix}/inventory/warehouseAction!loadWarehouseByShelf.action?shelfId=' + shelfId,
		cache : false,
		success : function(returnValue) {
			if (returnValue != undefined) {
				var result = returnValue.split(",");
				$("#warehouseId").val(result[0]);
				$("#warehouseName").val(result[1]);
			}
		}
		});
	}
</script>
<input type="hidden" id="orderId" name="orderId" value="${orderId}" />
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="warehouseform">
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">仓库:&nbsp;</td>
					<td><input type="hidden" id="warehouseId" name="warehouseId" /><input type="text" name="warehouseName" id="warehouseName" size="20" /><input class="btn" type="button" value="选择" onclick="choosewarehouse();" /></td>
					<td align="right">货位:&nbsp;</td>
					<td><input type="hidden" id="invShelfId" name="invShelfId" /><input type="text" name="invShelfName" id="invShelfName" size="20" /><input class="btn" type="button" value="选择" onclick="chooseShelf();" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>