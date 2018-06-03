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
			$.post('${vix}/dtbcenter/claimInformationAction!saveOrUpdate.action', {
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
				loadContent('${vix}/dtbcenter/claimInformationAction!goList.action');
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
				<li><a href="#" onclick="loadContent('${vix}/dtbcenter/claimInformationAction!goList.action?pageNo=${pageNo}');">理赔信息管理 </a></li>
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
							src="${vix}/common/img/dt_submit.png" /> </a> <a href="#" onclick="loadContent('${vix}/dtbcenter/claimInformationAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong><b>理赔信息 </b> </strong>
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
											<th>理赔编号：</th>
											<td><input id="scheduleCode" name="scheduleCode" value="${deliveryReceipt.scheduleCode }" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
											<th>理赔类型：</th>
											<td><input id="scheduleCode" name="scheduleCode" value="${deliveryReceipt.scheduleCode }" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>状态：</th>
											<td><select id="scheduleType" name="scheduleType" class="validate[required] text-input">
													<option value="">请选择</option>
													<option value="1">类型1</option>
													<option value="2">类型2</option>
													<option value="3">类型3</option>
											</select></td>
											<th>事故编号：</th>
											<td><input id="status" name="status" value="${deliveryReceipt.status}" class="validate[required] text-input" type="text" size="30" /></td>
										</tr>
										<tr>
											<th>车牌号：</th>
											<td><input id="vehicleProperty" name="vehicleProperty" value="${deliveryReceipt.vehicleProperty}" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
											<th>保险办理人：</th>
											<td><input id="vehicleProperty" name="vehicleProperty" value="${deliveryReceipt.vehicleProperty}" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>直接损失：</th>
											<td><input id="vehicleNO" name="vehicleNO" value="${deliveryReceipt.vehicleNO }" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
											<th>申请理赔时间：</th>
											<td><input id="dispatchTime" name="dispatchTime" value="${deliveryReceipt.dispatchTime}" type="text" readonly="readonly" /> <img onclick="showTime('dispatchTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<th>申请理赔金额：</th>
											<td><input id="driver" name="driver" value="${deliveryReceipt.driver }" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
											<th>赔偿比率：</th>
											<td><input id="driverMobile" name="driverMobile" value="${deliveryReceipt.driverMobile }" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>赔偿方式：</th>
											<td><input id="mileage" name="mileage" value="${deliveryReceipt.mileage }" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
											<th>赔偿金额：</th>
											<td><input id="mileage" name="mileage" value="${deliveryReceipt.mileage }" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>赔偿时间：</th>
											<td><input id="sendInPlan" name="sendInPlan" value="${deliveryReceipt.sendInPlan}" type="text" readonly="readonly" /> <img onclick="showTime('sendInPlan','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
											<th>领取人：</th>
											<td><input id="carryingCubage" name="carryingCubage" value="${deliveryReceipt.carryingCubage }" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>身份证号码：</th>
											<td><input id="scheduler" name="scheduler" value="${deliveryReceipt.scheduler }" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
											<th>领取时间：</th>
											<td><input id="sendInPlan" name="sendInPlan" value="${deliveryReceipt.sendInPlan}" type="text" readonly="readonly" /> <img onclick="showTime('sendInPlan','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<th>实际赔偿金额：</th>
											<td><input id="source" name="source" value="${deliveryReceipt.source }" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
											<th>创建人：</th>
											<td><input id="target" name="target" value="${deliveryReceipt.target }" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>创建时间：</th>
											<td><input id="sendInPlan" name="sendInPlan" value="${deliveryReceipt.sendInPlan}" type="text" readonly="readonly" /> <img onclick="showTime('sendInPlan','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
											<th>修改人：</th>
											<td><input id="target" name="target" value="${deliveryReceipt.target }" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>修改时间：</th>
											<td><input id="sendInPlan" name="sendInPlan" value="${deliveryReceipt.sendInPlan}" type="text" readonly="readonly" /> <img onclick="showTime('sendInPlan','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
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