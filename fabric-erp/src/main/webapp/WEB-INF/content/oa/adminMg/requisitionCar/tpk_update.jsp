<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">

</script>
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
		<form id="tpkForm">
			<input type="hidden" id="id" name="id" value="${tpk.id}" />
			<table style="border: none;">
				<tr>
					<th class="tr" width="150">车牌号：</th>
					<td><s:select id="vehicleRequestId" headerKey="-1" headerValue="--请选择--" list="%{vehicleRequestList}" listValue="plateNumber" listKey="id" value="%{tpk.vehicleRequest.id}" multiple="false" theme="simple">
						</s:select> <span style="color: red;">*</span></td>
				</tr>
				<tr>
					<th class="tr">司机姓名：</th>
					<td><input id="carname" name="carname" value="${tpk.carname }" type="text" style="width: 200px;" class="ipt" /> <span style="color: red;">*</span></td>
				</tr>
				<tr>
					<th class="tr">里程数（公里）：</th>
					<td><input id="mileage" name="mileage" value="${tpk.mileage}" type="text" style="width: 200px;" class="ipt" /> <span style="color: red;">*</span></td>
				</tr>
				<tr>
					<th class="tr">油耗数（升）：</th>
					<td><input id="guzzling" name="guzzling" value="${tpk.guzzling}" type="text" style="width: 200px;" class="ipt" /> <span style="color: red;">*</span></td>
				</tr>
				<tr>
					<th class="tr">油耗数（升/公里）：</th>
					<td><input id="guzzlingNumber" name="guzzlingNumber" value="${tpk.guzzlingNumber}" type="text" style="width: 200px;" class="ipt" /> <span style="color: red;">*</span></td>
				</tr>
			</table>
		</form>
	</div>
	<div class="c_foot">
		<span class="left_bg"></span> <span class="right_bg"></span>
	</div>
</div>