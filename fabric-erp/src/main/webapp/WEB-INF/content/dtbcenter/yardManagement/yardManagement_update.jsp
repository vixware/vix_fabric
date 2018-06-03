<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	function saveOrUpdateVehicle() {
		if ($('#vehicleForm').validationEngine('validate')) {
			$.post('${vix}/dtbcenter/yardManagementAction!saveOrUpdate.action', {
			'vehicle.id' : $("#id").val(),
			'vehicle.vehicleCode' : $("#vehicleCode").val(),
			'vehicle.vehicleNO' : $("#vehicleNO").val(),
			'vehicle.type' : $("#type").val(),
			'vehicle.name' : $("#name").val(),
			'vehicle.carryingcapacity' : $("#carryingcapacity").val(),
			'vehicle.capacity' : $("#capacity").val(),
			'vehicle.driver' : $("#driver").val(),
			'vehicle.driverTelephone' : $("#driverTelephone").val(),
			'vehicle.purchaseDate' : $("#purchaseDate").val(),
			'vehicle.licensePlateDate' : $("#licensePlateDate").val(),
			'vehicle.carrier' : $("#carrier").val(),
			'vehicle.useYear' : $("#useYear").val(),
			'vehicle.documnetDate' : $("#documnetDate").val(),
			'vehicle.vehicleStauts' : $("#vehicleStauts").val(),
			'vehicle.mileageTopLimit' : $("#mileageTopLimit").val(),
			'vehicle.odometerReading' : $("#odometerReading").val(),
			'vehicle.oilWearWithHeavy' : $("#oilWearWithHeavy").val(),
			'vehicle.oilWearWithEmpty' : $("#oilWearWithEmpty").val(),
			'vehicle.producer' : $("#producer").val(),
			'vehicle.dateOfManufacture' : $("#dateOfManufacture").val(),
			'vehicle.engineCode' : $("#engineCode").val(),
			'vehicle.frameNumber' : $("#frameNumber").val(),
			'vehicle.description' : $("#description").val(),
			'vehicle.carryingcapacityUnit' : $("#carryingcapacityUnit").val(),
			'vehicle.vehicleLength' : $("#vehicleLength").val(),
			'vehicle.vehicleLengthUnit' : $("#vehicleLengthUnit").val(),
			'vehicle.vehicleWidth' : $("#vehicleWidth").val(),
			'vehicle.vehicleWidthUnit' : $("#vehicleWidthUnit").val(),
			'vehicle.vehicleHeight' : $("#vehicleHeight").val(),
			'vehicle.vehicleHeightUnit' : $("#vehicleHeightUnit").val(),
			'vehicle.capacityUnit' : $("#capacityUnit").val(),
			'vehicle.chassisNumber' : $("#chassisNumber").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/dtbcenter/yardManagementAction!goList.action');
			});
		}
	}
	$("#vehicleForm").validationEngine();
	$(function() {
		//加载创建时间(新增)
		if (document.getElementById("createTime").value == "") {
			var myDate = new Date();
			$("#createTime").val(myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate());
		}
	});
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/transportationresourcemanagement.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="dtbcenter" /> </a></li>
				<li><a href="#"><s:text name="dtbcenter_transreource" /> </a></li>
				<li><a href="#" onclick="loadContent('${vix}/dtbcenter/yardManagementAction!goList.action');">车场管理</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${vehicle.id}" />
<div class="content">
	<form id="vehicleForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateVehicle()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"> </a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /> </a> <a href="#"><img width="22" height="22" title="保存并提交审批"
							src="${vix}/common/img/dt_submit.png" /> </a> <a href="#" onclick="loadContent('${vix}/dtbcenter/yardManagementAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong><b>车场信息 </b> </strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b> <strong>基本信息</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<td align="right">车场编号：</td>
											<td><input id="vehicleCode" name="vehicleCode" value="${vehicle.vehicleCode }" type="text" size="20" class="validate[required] text-input"> <span style="color: red;">*</span></td>
											<td align="right">车场名称：</td>
											<td><input id="vehicleNO" name="vehicleNO" value="${vehicle.name }" type="text" size="20" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">位置：</td>
											<td><input id="vehicleCode" name="vehicleCode" value="${vehicle.name }" type="text" size="20" class="validate[required] text-input"> <span style="color: red;">*</span></td>
											<td align="right">容量：</td>
											<td><input id="vehicleNO" name="vehicleNO" value="${vehicle.vehicleNO }" type="text" size="20" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">负责人：</td>
											<td><input id="vehicleCode" name="vehicleCode" value="${vehicle.name }" type="text" size="20" class="validate[required] text-input"> <span style="color: red;">*</span></td>
											<td align="right">状态：</td>
											<td><select id="vehicleStauts" name="vehicleStauts" class="validate[required] text-input">
													<option value="">请选择</option>
													<option value="空闲" selected="selected">空闲</option>
													<option value="使用">使用</option>
											</select><span style="color: red;">*</span></td>
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