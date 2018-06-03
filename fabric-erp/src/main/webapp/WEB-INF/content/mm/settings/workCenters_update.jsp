<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<%-- <script src="${vix}/plugin/validengine/js/languages/jquery.validationEngine-en.js" type="text/javascript" charset="utf-8"></script>
<script src="${vix}/plugin/validengine/js/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script> --%>

<script type="text/javascript">
$("#orderItemForm").validationEngine();

function chooseWarehouse() {
    $.ajax({
    url : '${vix}/inventory/inboundWarehouseAction!goChooseWarehouse.action',
    cache : false,
    success : function(html) {
	    asyncbox.open({
	    modal : true,
	    width : 560,
	    height : 340,
	    title : "选择仓库",
	    html : html,
	    callback : function(action,returnValue) {
		    if (action == 'ok') {
			    if (returnValue != undefined) {
				    var result = returnValue.split(",");
				    $("#wipWarehouse").val(result[1]);
			    } else {
				    $("#wipWarehouse").val("");
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
	<form id="supplierCategoryForm">
		<s:hidden id="newId" name="workCenter.id" value="%{workCenter.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">工作中心编码:&nbsp;</th>
					<td><input id="org" name="workCenter.org" value="${workCenter.org}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">工作中心名称:&nbsp;</th>
					<td><input id="orgName" name="workCenter.orgName" value="${workCenter.orgName}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">机器数量或人数:&nbsp;</th>
					<td><input id="machinesNumber" name="workCenter.machinesNumber" value="${workCenter.machinesNumber}" type="text" /></td>
					<th align="right">类型:&nbsp;</th>
					<td><input id="types" name="workCenter.types" value="${workCenter.types}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">机器类型:&nbsp;</th>
					<td><input id="machineType" name="workCenter.machineType" value="${workCenter.machineType}" type="text" /></td>
					<th align="right">每小时人工工资:&nbsp;</th>
					<td><input id="hourlyWages" name="workCenter.hourlyWages" value="${workCenter.hourlyWages}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">需要装配和操作人数:&nbsp;</th>
					<td><input id="assemblyOperationNumber" name="workCenter.assemblyOperationNumber" value="${workCenter.assemblyOperationNumber}" type="text" /></td>
					<th align="right">每小时制造费用:&nbsp;</th>
					<td><input id="manufacturingCostPerHour" name="workCenter.manufacturingCostPerHour" value="${workCenter.manufacturingCostPerHour}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">机台类型说明:&nbsp;</th>
					<td><input id="machineTypeDescription" name="workCenter.machineTypeDescription" value="${workCenter.machineTypeDescription}" type="text" /></td>
					<th align="right">工作中心效率%:&nbsp;</th>
					<td><input id="orgEfficiency" name="workCenter.orgEfficiency" value="${workCenter.orgEfficiency}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">部门:&nbsp;</th>
					<td><input id="departments" name="workCenter.departments" value="${workCenter.departments}" type="text" /></td>
					<th align="right">WIP仓库:&nbsp;</th>
					<td><input id="wipWarehouse" name="workCenter.wipWarehouse" value="${workCenter.wipWarehouse}" type="text" /> <input class="btn" type="button" value="选择" onclick="chooseWarehouse();" /></td>
				</tr>
				<tr height="40">
					<th align="right">成本中心:&nbsp;</th>
					<td><input id="costCenter" name="workCenter.costCenter" value="${workCenter.costCenter}" type="text" /></td>
					<th align="right">WIP储位:&nbsp;</th>
					<td><input id="wipStorage" name="workCenter.wipStorage" value="${workCenter.wipStorage}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">位置:&nbsp;</th>
					<td><input id="position" name="workCenter.position" value="${workCenter.position}" type="text" /></td>
					<th align="right">预设排队时间(h):&nbsp;</th>
					<td><input id="defaultQueueTime" name="workCenter.defaultQueueTime" value="${workCenter.defaultQueueTime}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">每天每班工作时数:&nbsp;</th>
					<td><input id="everyHoursWork" name="workCenter.everyHoursWork" value="${workCenter.everyHoursWork}" type="text" /></td>
					<th align="right">预设等待时间(h):&nbsp;</th>
					<td><input id="presetWaitTime" name="workCenter.presetWaitTime" value="${workCenter.presetWaitTime}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">每天班次数量:&nbsp;</th>
					<td><input id="numberDailyFlights" name="workCenter.numberDailyFlights" value="${workCenter.numberDailyFlights}" type="text" /></td>
					<th align="right">预设搬运时间(h):&nbsp;</th>
					<td><input id="handlingTime" name="workCenter.handlingTime" value="${workCenter.handlingTime}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">是否生产线:&nbsp;</th>
					<td><input id="whetherProductionLine" name="workCenter.whetherProductionLine" value="${workCenter.whetherProductionLine}" type="text" /></td>
					<th align="right">每小时人工工资:&nbsp;</th>
					<td><input id="hourCost" name="workCenter.hourCost" value="${workCenter.hourCost}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">预设上班班次:&nbsp;</th>
					<td><input id="presetWorkShifts" name="workCenter.presetWorkShifts" value="${workCenter.presetWorkShifts}" type="text" /></td>
					<th align="right">工作日期:&nbsp;</th>
					<td><input id="workDate" name="workDate" value="<fmt:formatDate value='${workCenter.workDate}' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('workDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
				<tr height="40">
					<th align="right">创建者:&nbsp;</th>
					<td><input id="establishmentUser" name="workCenter.establishmentUser" value="${workCenter.establishmentUser}" type="text" /></td>
					<th align="right">建立日期:&nbsp;</th>
					<td><input id="creDate" name="creDate" value="<fmt:formatDate value='${workCenter.creDate}' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('creDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
			</table>
		</div>
	</form>
</div>