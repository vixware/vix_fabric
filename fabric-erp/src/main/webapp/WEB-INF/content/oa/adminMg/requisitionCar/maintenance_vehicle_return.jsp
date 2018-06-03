<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<style>
.content {
	margin-bottom: 0;
}

.cardTable {
	padding: 0 10px;
}

.cardTable table th, .cardTable table td {
	padding: 5px;
	vertical-align: top;
	border: #CCC solid 1px;
}

.cardTable table th {
	background: #DCE7F1;
}

.cardTable table .tr {
	text-align: right;
}

.cardTable .popupArea {
	height: 300px;
}

.cardTable .checkbox {
	vertical-align: middle;
}

.cardTable label {
	margin-right: 10px;
}
</style>
<link href="${vix}/common/css/token-input.css" rel="stylesheet" type="text/css" />
<link href="${vix}/common/css/token-input-facebook.css" type="text/css" id="font" rel="stylesheet">
<link href="${vix}/common/css/jquery.jnotify.css" type="text/css" id="font" rel="stylesheet">
<link href="${vix}/common/css/grid.css" rel="stylesheet" type="text/css" />
<script src="${vix}/common/js/core.js" type="text/javascript"></script>
<script src="${vix}/common/js/mousewheel.js" type="text/javascript"></script>
<script src="${vix}/common/js/combo.js" type="text/javascript"></script>
<script src="${vix}/common/js/nicEdit.js" type="text/javascript"></script>

<div class="content">
	<div class="c_head">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
	<div class="box cardTable">
		<form id="carForm">
			<input type="hidden" id="id" name="id" value="${carMaintenance.id}" />
			<table style="border: none;">
				<tr>
					<th class="tr" width="150">车牌号：</th>
					<td><s:select id="vehicleRequestId" headerKey="-1" headerValue="--请选择--" list="%{vehicleRequestList}" listValue="model" listKey="id" value="%{carMaintenance.vehicleRequest.id}" multiple="false" theme="simple">
						</s:select></td>
				</tr>
				<tr>
					<th class="tr" width="150">维护日期：</th>
					<td><input readonly="readonly" id="maintenanceDate" class="sweet-tooltip" data-text-tooltip="时间格式 yyyy-MM-dd" name="maintenanceDate" value="<fmt:formatDate value='${carMaintenance.maintenanceDate}' type='both' pattern='yyyy-MM-dd' />" type="text" size="14" /></td>
				</tr>
				<tr>
					<th class="tr" width="150">维护类型：</th>
					<td><s:select id="maintenanceTypeId" headerKey="-1" headerValue="--请选择--" list="%{maintenanceTypeList}" listValue="name" listKey="id" value="%{carMaintenance.maintenanceType.id}" multiple="false" theme="simple">
						</s:select></td>
				</tr>
				<tr>
					<th class="tr">维护原因：</th>
					<td colspan="2"><textarea readonly="readonly" id="maintenanceReason" name="remark" class="maintenanceReason" rows="2" style="width: 400px">${carMaintenance.maintenanceReason }</textarea></td>
				</tr>
				<tr>
					<th class="tr">维护情况：</th>
					<td colspan="2"><textarea id="maintenance" name="maintenance" class="example" rows="2" style="width: 400px">${carMaintenance.maintenance }</textarea></td>
				</tr>
				<tr>
					<th class="tr">维护费用：</th>
					<td><input id="maintenanceCost" name="maintenanceCost" value="${carMaintenance.maintenanceCost}" type="text" style="width: 200px;" class="ipt" /></td>
				</tr>
				<tr>
					<th class="tr">备注：</th>
					<td colspan="2"><textarea id="remark" name="remark" class="example" rows="2" style="width: 400px">${carMaintenance.remark }</textarea></td>
				</tr>
			</table>
		</form>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
</div>