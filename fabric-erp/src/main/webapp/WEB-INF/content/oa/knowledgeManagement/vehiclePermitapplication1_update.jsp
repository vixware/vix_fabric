<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">

</script>
<link href="${vix}/common/css/token-input.css" rel="stylesheet" type="text/css" />
<link href="${vix}/common/css/token-input-facebook.css" type="text/css" id="font" rel="stylesheet">
<link href="${vix}/common/css/jquery.jnotify.css" type="text/css" id="font" rel="stylesheet">
<link href="${vix}/common/css/grid.css" rel="stylesheet" type="text/css" />
<script src="${vix}/common/js/core.js" type="text/javascript"></script>
<script src="${vix}/common/js/mousewheel.js" type="text/javascript"></script>
<script src="${vix}/common/js/combo.js" type="text/javascript"></script>
<script src="${vix}/common/js/nicEdit.js" type="text/javascript"></script>
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
<div class="content1" style="margin-top: 15px; overflow: hidden">
	<form id="brandForm">
		<input type="hidden" id="id" name="id" value="${contractPaymentPlan.id}" />
		<div class="content">
			<div class="c_head">
				<span class="left_bg"></span> <span class="right_bg"></span>
			</div>
			<div class="c_head">
				<span class="left_bg"></span> <span class="right_bg"></span>
			</div>
			<div class="content">
				<div class="c_head">
					<span class="left_bg"></span> <span class="right_bg"></span>
				</div>
				<div class="box cardTable">
					<table width="100%">
						<tr>
							<th class="tr" width="150">型号：</th>
							<td>奥迪A6</td>
							<td rowspan="6" width="220"><img src="images/dashboard_06.jpg" /></td>
						</tr>
						<tr>
							<th class="tr">车牌号：</th>
							<td>A6-111111</td>
						</tr>
						<tr>
							<th class="tr">司机：</th>
							<td>测试</td>
						</tr>
						<tr>
							<th class="tr">手机号码：</th>
							<td>13766666666</td>
						</tr>
						<tr>
							<th class="tr">车辆类型：</th>
							<td>轿车</td>
						</tr>
						<tr>
							<th class="tr">购置日期：</th>
							<td>2013-05-06</td>
						</tr>
						<tr>
							<th class="tr">购买价格：</th>
							<td colspan="2">500000</td>
						</tr>
						<tr>
							<th class="tr">发动机号码：</th>
							<td colspan="2">xxx-xxx</td>
						</tr>
						<tr>
							<th class="tr">预定情况：</th>
							<td colspan="2">无预定信息</td>
						</tr>
						<tr>
							<th class="tr">申请权限（部门）：</th>
							<td colspan="2">全体部门</td>
						</tr>
						<tr>
							<th class="tr">申请权限人员：</th>
							<td colspan="2">系统管理员，测试</td>
						</tr>
						<tr>
							<th class="tr">当前状态：</th>
							<td colspan="2"><span class="red">可用</span></td>
						</tr>
						<tr>
							<th class="tr">备注：</th>
							<td colspan="2">&nbsp;</td>
						</tr>
					</table>

				</div>
				<div class="c_foot">
					<span class="left_bg"></span> <span class="right_bg"></span>
				</div>
			</div>