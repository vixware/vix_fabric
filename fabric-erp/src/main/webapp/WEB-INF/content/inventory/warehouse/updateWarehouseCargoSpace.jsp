<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$("#cargospaceform").validationEngine();
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
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
						$("#invWarehouseId").val(result[0]);
						$("#invWarehouseName").val(result[1]);
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
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="cargospaceform">
		<s:hidden id="invShelfid" name="invShelf.id" value="%{invShelf.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">仓库:&nbsp;</td>
					<td><input type="hidden" id="invWarehouseId" name="invWarehouseId" value="${invShelf.invWarehouse.id}" onchange="fieldChanged(this);" /> <input type="text" id="invWarehouseName" name="invWarehouseName" value="${invShelf.invWarehouse.name}" readonly="readonly" /><input class="btn" type="button" value="选择" onclick="choosewarehouse();" /></td>
					<td align="right">货架:&nbsp;</td>
					<td><input type="hidden" id="parentInvShelfId" name="parentInvShelfId" value="${invShelf.parentInvShelf.id}" onchange="fieldChanged(this);" /> <input type="text" id="parentName" name="parentName" value="${invShelf.parentInvShelf.name}" readonly="readonly" /></td>
				</tr>
				<tr height="40">
					<td align="right">货位编码:&nbsp;</td>
					<td><input type="text" id="invShelfCode" name="invShelfCode" value="${invShelf.code}" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
					<td align="right">货位名称:&nbsp;</td>
					<td><input type="text" id="invShelfName" name="invShelfName" value="${invShelf.name}" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">优先级:&nbsp;</td>
					<td><s:textfield id="priority" name="invShelf.priority" value="%{invShelf.priority}" theme="simple" onchange="fieldChanged(this);" /></td>
					<td align="right">最大存货数量:&nbsp;</td>
					<td><s:textfield id="maximum" name="invShelf.maximum" value="%{invShelf.maximum}" theme="simple" onchange="fieldChanged(this);" /></td>
				</tr>
				<tr height="40">
					<td align="right">长:&nbsp;</td>
					<td><s:textfield id="shelfLength" name="invShelf.shelfLength" value="%{invShelf.shelfLength}" theme="simple" onchange="fieldChanged(this);" /></td>
					<td align="right">宽:&nbsp;</td>
					<td><s:textfield id="shelfWidth" name="invShelf.shelfWidth" value="%{invShelf.shelfWidth}" theme="simple" onchange="fieldChanged(this);" /></td>
				</tr>
				<tr height="40">
					<td align="right">高:&nbsp;</td>
					<td><s:textfield id="shelfHeight" name="invShelf.shelfHeight" value="%{invShelf.shelfHeight}" theme="simple" onchange="fieldChanged(this);" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>