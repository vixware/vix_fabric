<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
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
			title : "选择上级仓库",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						var result = returnValue.split(",");
						$("#parentId").val(result[0]);
						$("#parentName").val(result[1]);
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
	$("#area").validationEngine();
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="area">
		<s:hidden id="invWarehousezoneid" name="invWarehousezone.id" value="%{invWarehousezone.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">仓库:&nbsp;</td>
					<td><input type="hidden" id="parentId" name="parentId" value="${invWarehousezone.invWarehouse.id}" onchange="fieldChanged(this);" /> <input type="text" id="parentName" name="parentName" value="${invWarehousezone.invWarehouse.name}" readonly="readonly" /><input class="btn" type="button" value="选择" onclick="choosewarehouse();" /></td>
				</tr>
				<tr height="40">
					<td align="right">货区代码:&nbsp;</td>
					<td><input type="text" id="code" name="code" class="validate[required] text-input" value="${invWarehousezone.code}" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
					<td align="right">货区名称:&nbsp;</td>
					<td><input type="text" id="name" name="name" class="validate[required] text-input" value="${invWarehousezone.name}" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">起始时间:&nbsp;</td>
					<td><input id="startTime" name="startTime" value="${invWarehousezone.startTime}" type="text" onchange="fieldChanged(this);" /><img onclick="showTime('startTime','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
					<td align="right">结束时间:&nbsp;</td>
					<td><input id="endTime" name="endTime" value="${invWarehousezone.endTime}" type="text" onchange="fieldChanged(this);" /><img onclick="showTime('endTime','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
			</table>
		</div>
	</form>
</div>