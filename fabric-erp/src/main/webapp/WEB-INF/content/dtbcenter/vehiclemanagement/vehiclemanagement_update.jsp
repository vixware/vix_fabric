<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
	function saveOrUpdateVehicle() {
		if ($('#vehicleForm').validationEngine('validate')) {
			$.post('${vix}/dtbcenter/vehicleManagementAction!saveOrUpdate.action', {
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
			'updateField' : updateField,
			'vehicle.chassisNumber' : $("#chassisNumber").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/dtbcenter/vehicleManagementAction!goList.action');
			});
		}
	}
	$("#vehicleForm").validationEngine();
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
				<li><a href="#" onclick="loadContent('${vix}/dtbcenter/vehicleManagementAction!goList.action');"><s:text name="dtbcenter_vehiclemanagement" /> </a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="vehicle.id" value="${vehicle.id}" />
<div class="content">
	<form id="vehicleForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateVehicle()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"> </a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /> </a> <a href="#"><img width="22" height="22" title="保存并提交审批"
							src="${vix}/common/img/dt_submit.png" /> </a> <a href="#" onclick="loadContent('${vix}/dtbcenter/vehicleManagementAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong><b><s:text name="dtbcenter_vehicleinformation" /> </b> </strong>
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
											<td align="right">车辆编号：</td>
											<td><input id="vehicleCode" name="vehicleCode" value="${vehicle.vehicleCode }" type="text" size="20" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
											<td align="right">车牌号：</td>
											<td><input id="vehicleNO" name="vehicleNO" value="${vehicle.vehicleNO }" type="text" size="20" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">车辆类型：</td>
											<td><select id="type" name="type" class="validate[required] text-input" onchange="fieldChanged(this);">
													<option value="">请选择</option>
													<option value="货车" selected="selected">货车</option>
													<option value="箱车">箱车</option>
													<option value="客车">客车</option>
											</select><span style="color: red;">*</span></td>
											<td align="right">名称：</td>
											<td><input id="name" name="name" value="${vehicle.name }" type="text" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<td align="right">车辆状态：</td>
											<td><select id="vehicleStauts" name="vehicleStauts" class="validate[required] text-input" onchange="fieldChanged(this);">
													<option value="">请选择</option>
													<option value="空闲" selected="selected">空闲</option>
													<option value="途中">途中</option>
											</select><span style="color: red;">*</span></td>
											<td align="right">载重：</td>
											<td><input id="carryingcapacity" name="carryingcapacity" value="${vehicle.carryingcapacity }" type="text" size="20" class="validate[required] text-input" onchange="fieldChanged(this);" /><select id="carryingcapacityUnit" name="carryingcapacityUnit" class="validate[required] text-input">
													<option value="吨" selected="selected">吨</option>
													<option value="千克">千克</option>
											</select><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">车长：</td>
											<td><input id="vehicleLength" name="vehicleLength" value="${vehicle.vehicleLength }" type="text" size="20" class="validate[required] text-input" onchange="fieldChanged(this);" /><select id="vehicleLengthUnit" name="vehicleLengthUnit" class="validate[required] text-input">
													<option value="cm" selected="selected">cm</option>
													<option value="m">m</option>
											</select><span style="color: red;">*</span></td>
											<td align="right">车宽：</td>
											<td><input id="vehicleWidth" name="vehicleWidth" value="${vehicle.vehicleWidth }" type="text" size="20" class="validate[required] text-input" onchange="fieldChanged(this);" /><select id="vehicleWidthUnit" name="vehicleWidthUnit" class="validate[required] text-input">
													<option value="cm" selected="selected">cm</option>
													<option value="m">m</option>
											</select><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">车高：</td>
											<td><input id="vehicleHeight" name="vehicleHeight" value="${vehicle.vehicleHeight }" type="text" size="20" class="validate[required] text-input" onchange="fieldChanged(this);" /><select id="vehicleHeightUnit" name="vehicleHeightUnit" class="validate[required] text-input">
													<option value="cm" selected="selected">cm</option>
													<option value="m">m</option>
											</select><span style="color: red;">*</span></td>
											<td align="right">容量：</td>
											<td><input id="capacity" name="capacity" value="${vehicle.capacity }" type="text" size="20" onchange="fieldChanged(this);" /><select id="capacityUnit" name="capacityUnit">
													<option value="cm³" selected="selected">cm³</option>
													<option value="m³">m³</option>
											</select></td>
										</tr>
										<tr>
											<td align="right">主要驾驶员：</td>
											<td><select id="driver" name="driver" onchange="fieldChanged(this);">
													<option value="">请选择</option>
													<option value="张三" selected="selected">张三</option>
													<option value="李四">李四</option>
													<option value="王五">王五</option>
											</select></td>
											<td align="right">驾驶员电话：</td>
											<td><input id="driverTelephone" name="driverTelephone" value="${vehicle.driverTelephone }" type="text" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<td align="right">购买日期：</td>
											<td><input id="purchaseDate" name="purchaseDate" value="${vehicle.purchaseDate}" type="text" readonly="readonly" onchange="fieldChanged(this);" /><img onclick="showTime('purchaseDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
											<td align="right">上牌日期：</td>
											<td><input id="licensePlateDate" name="licensePlateDate" value="${vehicle.licensePlateDate}" type="text" readonly="readonly" onchange="fieldChanged(this);" /><img onclick="showTime('licensePlateDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<td align="right">所属承运商：</td>
											<td><input id="carrier" name="carrier" value="${vehicle.carrier }" type="text" size="30" onchange="fieldChanged(this);" /></td>
											<td align="right">使用年限：</td>
											<td><input id="useYear" name="useYear" value="${vehicle.useYear }" type="text" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<td align="right">建档日期：</td>
											<td><input id="documnetDate" name="documnetDate" value="${vehicle.documnetDate}" type="text" readonly="readonly" onchange="fieldChanged(this);" /><img onclick="showTime('documnetDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
											<td align="right">行驶里程上限：</td>
											<td><input id="mileageTopLimit" name="mileageTopLimit" value="${vehicle.mileageTopLimit }" type="text" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<td align="right">里程表读数：</td>
											<td><input id="odometerReading" name="odometerReading" value="${vehicle.odometerReading }" type="text" size="30" onchange="fieldChanged(this);" /></td>
											<td align="right">重车百公里油耗：</td>
											<td><input id="oilWearWithHeavy" name="oilWearWithHeavy" value="${vehicle.oilWearWithHeavy }" type="text" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<td align="right">轻车百公里油耗：</td>
											<td><input id="oilWearWithEmpty" name="oilWearWithEmpty" value="${vehicle.oilWearWithEmpty }" type="text" size="30" onchange="fieldChanged(this);" /></td>
											<td align="right">制造商：</td>
											<td><input id="producer" name="producer" value="${vehicle.producer }" type="text" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<td align="right">出厂日期：</td>
											<td><input id="dateOfManufacture" name="dateOfManufacture" value="${vehicle.dateOfManufacture}" type="text" readonly="readonly" onchange="fieldChanged(this);" /><img onclick="showTime('dateOfManufacture','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
											<td align="right">发动机号：</td>
											<td><input id="engineCode" name="engineCode" value="${vehicle.engineCode }" type="text" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<td align="right">车架号：</td>
											<td><input id="frameNumber" name="frameNumber" value="${vehicle.frameNumber }" type="text" size="30" onchange="fieldChanged(this);" /></td>
											<td align="right">底盘号：</td>
											<td><input id="chassisNumber" name="chassisNumber" value="${vehicle.chassisNumber }" type="text" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<td align="right">描述：</td>
											<td><input id="description" name="description" value="${vehicle.description }" type="text" size="30" onchange="fieldChanged(this);" /></td>
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