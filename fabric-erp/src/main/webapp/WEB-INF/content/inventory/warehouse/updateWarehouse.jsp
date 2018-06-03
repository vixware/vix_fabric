<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
	$("#warehouseform").validationEngine();
	function chooseParentOrganization() {
		$.ajax({
		url : '${vix}/drp/distributionSystemRelationShipAction!goChooseOrganization.action',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 560,
			height : 340,
			title : "选择父公司",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						var result = returnValue.split(",");
						if (result[0] == $("#id").val()) {
							asyncbox.success("不允许引用本公司为父公司!", "提示信息");
						} else {
							$("#companyCode").val(result[1]);
						}
					} else {
						$("#companyCode").val("");
						asyncbox.success("请选择公司信息!", "提示信息");
					}
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
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
						$("#parentInvWarehouseId").val(result[0]);
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

	/**
	 * 负责人选择
	 */
	function chooseManager() {
		$.ajax({
		url : '${vix}/common/select/commonSelectEmpAction!goList.action',
		data : {
			chkStyle : "radio"
		},
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 660,
			height : 340,
			title : "选择负责人",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						var selectIds = "";
						var selectNames = "";
						var result = returnValue.split(",");
						var resultLength = result.length;
						if (result[resultLength - 1].length > 1) {
							var v = result[resultLength - 1].split(":");
							selectIds += "," + v[0];
							selectNames += "," + v[1];
						}
						$("#employeeId").val(selectIds);
						selectNames = selectNames.substring(1, selectNames.length);
						$("#employeeName").val(selectNames);
					}
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	}

	function chooseProvinces() {
		$.ajax({
		url : '${vix}/inventory/warehouseAction!goChooseProvinces.action',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 550,
			height : 350,
			title : "选择省份",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != '') {
						var selectIds = "";
						var selectNames = "";
						var result = returnValue.split(",");
						for (var i = 0; i < result.length; i++) {
							if (result[i].length > 1) {
								var v = result[i].split(":");
								selectIds += "," + v[0];
								selectNames += "," + v[1];
							}
						}
						if (selectIds != '') {
							$("#provincesId").val(selectIds.substring(1));
							if ($("#provincesName").val() != '') {
								$("#provincesName").val($("#provincesName").val() + selectNames);
							} else {
								$("#provincesName").val(selectNames.substring(1));
							}
						}
					} else {
						asyncbox.success("请选择省份!", "<s:text name='vix_message'/>");
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
	<form id="warehouseform">
		<s:hidden id="invWarehouseid" name="invWarehouseid" value="%{invWarehouse.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">仓库编码:&nbsp;</td>
					<td><input type="text" id="invWarehouseCode" name="invWarehouseCode" class="validate[required] text-input" value="${invWarehouse.code}" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
					<td align="right">仓库名称:&nbsp;</td>
					<td><input type="text" id="invWarehouseName" name="invWarehouseName" class="validate[required] text-input" value="${invWarehouse.name}" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">仓库类型：</td>
					<td><select id="properties" name="properties" class="validate[required] text-input" onchange="fieldChanged(this);">
							<option value="1" selected="selected">普通仓</option>
							<option value="2">赠品仓</option>
							<option value="3">WIP-在制品区[工作中心]</option>
							<option value="4">正品仓</option>
							<option value="5">残次品仓</option>
							<option value="6">其他仓</option>
					</select><span style="color: red;">*</span></td>
					<td align="right">计价方式:&nbsp;</td>
					<td><select id="priceStyle" name="priceStyle" class="validate[required] text-input" onchange="fieldChanged(this);">
							<option value="1" selected="selected">全月平均法</option>
					</select></td>
				</tr>
				<tr height="40">
					<td align="right">库管员:&nbsp;</td>
					<td><input type="text" id="warehousePerson" name="warehousePerson" value="${invWarehouse.warehousePerson}" onchange="fieldChanged(this);" /></td>
					<td align="right">电话:&nbsp;</td>
					<td><input type="text" id="telephone" name="telephone" value="${invWarehouse.telephone}" onchange="fieldChanged(this);" class="validate[required],validate[custom[phone]] text-input" /></td>
				</tr>
				<tr height="40">
					<td align="right">公司:&nbsp;</td>
					<td><input type="text" id="companyCode" name="companyCode" value="${invWarehouse.companyCode}" readonly="readonly" onchange="fieldChanged(this);" /><input class="btn" type="button" value="选择" onclick="chooseParentOrganization();" /></td>
					<td align="right">上级仓库:&nbsp;</td>
					<td><input type="hidden" id="parentInvWarehouseId" name="parentInvWarehouseId" value="${invWarehouse.parentInvWarehouse.id}" onchange="fieldChanged(this);" /> <input type="text" id="parentName" name="parentName" value="${invWarehouse.parentInvWarehouse.name}" readonly="readonly" /><input class="btn" type="button" value="选择"
						onclick="choosewarehouse();" /></td>
				</tr>
				<tr height="40">
					<td align="right">负责人:&nbsp;</td>
					<td><input type="hidden" id="employeeId" name="employeeId" value="${invWarehouse.employee.id}" onchange="fieldChanged(this);" /> <input type="text" id="employeeName" name="employeeName" readonly="readonly" value="${invWarehouse.employee.name}" /> <input class="btn" type="button" value="选择" onclick="chooseManager();" /></td>
					<td align="right">资金定额:&nbsp;</td>
					<td><input type="text" id="fundQuota" name="fundQuota" value="${invWarehouse.fundQuota}" onchange="fieldChanged(this);" /></td>
				</tr>
				<tr>
					<th>发货省份：</th>
					<td colspan="3"><input type="hidden" id="provincesId" name="provincesId" value="${provincesId}" onchange="fieldChanged(this);" /> <textarea id="provincesName" name="provincesName" cols="3" rows="2" style="height: 75px; width: 500px; margin-top: 10px;">${invWarehouse.provincesName } </textarea><input class="btn" type="button" value="选择省份"
						onclick="chooseProvinces();" /></td>
				</tr>
				<tr height="40">
					<td align="right">备注:&nbsp;</td>
					<td colspan="3"><input type="text" id="memo" name="memo" value="${invWarehouse.memo}" size="60" onchange="fieldChanged(this);" /></td>
				</tr>
				<tr height="40">
					<td align="right">地址:&nbsp;</td>
					<td colspan="3"><input type="text" id="postion" name="postion" size="60" class="validate[required] text-input" value="${invWarehouse.postion}" onchange="fieldChanged(this);" /> <span style="color: red;">*</span></td>
				</tr>
			</table>
		</div>
	</form>
</div>