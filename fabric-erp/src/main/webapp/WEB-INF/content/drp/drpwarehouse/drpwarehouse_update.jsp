<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
	$("#warehouseform").validationEngine();
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
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="warehouseform">
		<s:hidden id="invWarehouseid" name="invWarehouseid" value="%{invWarehouse.id}" theme="simple" />
		<s:hidden id="channelDistributorId" name="channelDistributorId" value="%{invWarehouse.channelDistributor.id}" theme="simple" />
		<s:hidden id="organizationId" name="organizationId" value="%{invWarehouse.organization.id}" theme="simple" />
		<s:hidden id="type" name="type" value="%{invWarehouse.type}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="40">
					<td align="right">仓库代码:&nbsp;</td>
					<td><input type="text" id="invWarehouseCode" name="invWarehouseCode" class="validate[required] text-input" value="${invWarehouse.code}" style="width: 170px;" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
					<td align="right">仓库名称:&nbsp;</td>
					<td><input type="text" id="invWarehouseName" name="invWarehouseName" class="validate[required] text-input" value="${invWarehouse.name}" style="width: 170px;" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">仓库类型：</td>
					<td><select id="properties" name="properties" class="validate[required] text-input" style="width: 175px;" onchange="fieldChanged(this);">
							<option value="1" selected="selected">普通仓</option>
							<option value="2">赠品仓</option>
							<option value="3">WIP-在制品区[工作中心]</option>
							<option value="4">正品仓</option>
							<option value="5">残次品仓</option>
							<option value="6">其他仓</option>
					</select><span style="color: red;">*</span></td>
					<td align="right">计价方式:&nbsp;</td>
					<td><select id="priceStyle" name="priceStyle" class="validate[required] text-input" style="width: 175px;" onchange="fieldChanged(this);">
							<option value="1" selected="selected">全月平均法</option>
					</select></td>
				</tr>
				<tr height="40">
					<td align="right">库管员:&nbsp;</td>
					<td><input type="text" id="warehousePerson" name="warehousePerson" value="${invWarehouse.warehousePerson}" style="width: 170px;" onchange="fieldChanged(this);" /></td>
					<td align="right">电话:&nbsp;</td>
					<td><input type="text" id="telephone" name="telephone" value="${invWarehouse.telephone}" style="width: 170px;" onchange="fieldChanged(this);" /></td>
				</tr>
				<tr height="40">
					<td align="right">资金定额:&nbsp;</td>
					<td><input type="text" id="fundQuota" name="fundQuota" value="${invWarehouse.fundQuota}" style="width: 170px;" onchange="fieldChanged(this);" /></td>
					<td align="right">负责人:&nbsp;</td>
					<td><input type="hidden" id="employeeId" name="employeeId" value="${invWarehouse.employee.id}" onchange="fieldChanged(this);" /> <input type="text" id="employeeName" name="employeeName" readonly="readonly" value="${invWarehouse.employee.name}" style="width: 170px;" /> <input class="btn" type="button" value="选择"
						onclick="chooseManager();" /></td>
				</tr>
				<tr height="40">
					<td align="right">地址:&nbsp;</td>
					<td colspan="3"><input type="text" id="postion" name="postion" style="width: 465px;" class="validate[required] text-input" onchange="fieldChanged(this);" value="${invWarehouse.postion}" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<td align="right">备注:&nbsp;</td>
					<td colspan="3"><input type="text" id="memo" name="memo" value="${invWarehouse.memo}" style="width: 465px;" onchange="fieldChanged(this);" /></td>
				</tr>
			</table>
		</div>
	</form>
</div>