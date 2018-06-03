<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$(function() {
		$("#scheduleType").val('${deliveryReceipt.scheduleType}');
		$("#vehicleType").val('${deliveryReceipt.vehicleType}');
		if (document.getElementById("scheduleTime").value == "") {
			var myDate = new Date();
			$("#scheduleTime").val(myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate());
		}
	});
	function saveOrUpdateOrder() {
		if ($('#order').validationEngine('validate')) {
			$.post('${vix}/dtbcenter/insuranceLevelAction!saveOrUpdate.action', {
			'deliveryReceipt.id' : $("#id").val(),
			'deliveryReceipt.scheduleCode' : $("#scheduleCode").val(),
			'deliveryReceipt.scheduleTime' : $("#scheduleTime").val(),
			'deliveryReceipt.scheduleType' : $("#scheduleType").val(),
			'deliveryReceipt.status' : $("#status").val(),
			'deliveryReceipt.vehicleProperty' : $("#vehicleProperty").val(),
			'deliveryReceipt.vehicleType' : $("#vehicleType").val(),
			'deliveryReceipt.vehicleNO' : $("#vehicleNO").val(),
			'deliveryReceipt.vehicleNumber' : $("#vehicleNumber").val(),
			'deliveryReceipt.driver' : $("#driver").val(),
			'deliveryReceipt.driverMobile' : $("#driverMobile").val(),
			'deliveryReceipt.dispatchTime' : $("#dispatchTime").val(),
			'deliveryReceipt.mileage' : $("#mileage").val(),
			'deliveryReceipt.carryingCapacity' : $("#carryingCapacity").val(),
			'deliveryReceipt.carryingCubage' : $("#carryingCubage").val(),
			'deliveryReceipt.sendInPlan' : $("#sendInPlan").val(),
			'deliveryReceipt.scheduler' : $("#scheduler").val(),
			'deliveryReceipt.source' : $("#source").val(),
			'deliveryReceipt.target' : $("#target").val(),
			'deliveryReceipt.carrier' : $("#carrier").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/dtbcenter/insuranceLevelAction!goList.action');
			});
		} else {
			return false;
		}
	}
	/*表单验证  */
	$("#order").validationEngine();
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/drp_channel.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="dtbcenter" /> </a></li>
				<li><a href="#"><s:text name="dtbcenter_transreource" /> </a></li>
				<li><a href="#"><s:text name="dtbcenter_trafficmanagement" /> </a></li>
				<li><a href="#" onclick="loadContent('${vix}/dtbcenter/insuranceLevelAction!goList.action?pageNo=${pageNo}');">保险登记管理 </a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${deliveryReceipt.id}" />
<div class="content">
	<form id="order">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateOrder()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"> </a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /> </a> <a href="#"><img width="22" height="22" title="保存并提交审批"
							src="${vix}/common/img/dt_submit.png" /> </a> <a href="#" onclick="loadContent('${vix}/dtbcenter/insuranceLevelAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong><b>保险单</b> </strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b><strong>基本信息</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<th>保单号：</th>
											<td><input id="scheduleCode" name="scheduleCode" value="${deliveryReceipt.scheduleCode }" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
											<th>投保时间：</th>
											<td><input id="scheduleTime" name="scheduleTime" value="${deliveryReceipt.scheduleTime}" type="text" class="validate[required] text-input" readonly="readonly" /> <img onclick="showTime('scheduleTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<th>状态：</th>
											<td><select id="scheduleType" name="scheduleType" class="validate[required] text-input">
													<option value="">请选择</option>
													<option value="1">类型1</option>
													<option value="2">类型2</option>
													<option value="3">类型3</option>
											</select></td>
											<th>车牌号：</th>
											<td><input id="status" name="status" value="${deliveryReceipt.status}" class="validate[required] text-input" type="text" size="30" /></td>
										</tr>
										<tr>
											<th>发动机号：</th>
											<td><input id="vehicleProperty" name="vehicleProperty" value="${deliveryReceipt.vehicleProperty}" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
											<th>车辆类型：</th>
											<td><select id="vehicleType" name="vehicleType" class="validate[required] text-input">
													<option value="">请选择</option>
													<option value="1">类型1</option>
													<option value="2">类型2</option>
													<option value="3">类型3</option>
											</select></td>
										</tr>
										<tr>
											<th>保险公司：</th>
											<td><input id="vehicleNumber" name="vehicleNumber" value="${deliveryReceipt.vehicleNumber }" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
											<th>联系电话：</th>
											<td><input id="driverMobile" name="driverMobile" value="${deliveryReceipt.driverMobile }" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>投保单位：</th>
											<td><input id="vehicleNO" name="vehicleNO" value="${deliveryReceipt.vehicleNO }" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
											<th>投保人电话：</th>
											<td><input id="vehicleNO" name="vehicleNO" value="${deliveryReceipt.vehicleNO }" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>保险办理人：</th>
											<td><input id="vehicleNO" name="vehicleNO" value="${deliveryReceipt.vehicleNO }" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
											<th>保险生效日期：</th>
											<td><input id="scheduleTime" name="scheduleTime" value="${deliveryReceipt.scheduleTime}" type="text" class="validate[required] text-input" readonly="readonly" /> <img onclick="showTime('scheduleTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
				</dd>
			</dl>
		</div>
		<!--submenu-->
	</form>
</div>