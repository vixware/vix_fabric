<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-truck"></i> 供应商管理<span>> 车辆管理 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('dtbcenter_vixntvehicle','${nvix}/nvixnt/vixntVehicleAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>车辆信息</h2>
		</header>
		<div class="widget-body">
			<form class="form-horizontal" id="vehicleForm">
				<input type="hidden" id="vehicleId" name="vehicle.id" value="${vehicle.id}" />
				<fieldset>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 车辆编号:</label>
						<div class="col-md-3">
							<input id="vehicleCode" name="vehicle.vehicleCode" value="${vehicle.vehicleCode}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 车牌号:</label>
						<div class="col-md-3">
							<input id="vehicleNO" name="vehicle.vehicleNO" value="${vehicle.vehicleNO}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 司机:</label>
						<div class="col-md-3">
							<input id="driver" name="vehicle.driver" value="${vehicle.driver}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span> 电话:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="driverTelephone" name="vehicle.driverTelephone" value="${vehicle.driverTelephone}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" /> <span class="input-group-addon"><i class="fa fa-phone"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>车辆状态:</label>
						<div class="col-md-3">
							<select id="status" name="vehicle.status" class="form-control validate[required]">
								<option value="1" <c:if test='${vehicle.status == "1"}'>selected="selected"</c:if>>空闲</option>
								<option value="2" <c:if test='${vehicle.status == "2"}'>selected="selected"</c:if>>占用</option>
							</select>
						</div>
						<label class="col-md-2 control-label"> 车辆类型:</label>
						<div class="col-md-3">
							<select id="type" name="vehicle.type" class="form-control">
								<option value="1" <c:if test='${vehicle.type eq "1"}'>selected="selected"</c:if>>农用车</option>
								<option value="2" <c:if test='${vehicle.type eq "2"}'>selected="selected"</c:if>>冷藏车</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"> 载重:</label>
						<div class="col-md-3">
							<div class="row">
								<div class="col-sm-12">
									<div class="input-group">
										<input class="form-control" id="carryingcapacity" name="vehicle.carryingcapacity" value="${vehicle.carryingcapacity }" type="text"> <span class="input-group-addon">(吨)</span>
									</div>
								</div>
							</div>
						</div>
						<label class="control-label col-md-2">容量:</label>
						<div class="col-md-3">
							<div class="row">
								<div class="col-sm-12">
									<div class="input-group">
										<input class="form-control" id="capacity" name="vehicle.capacity" value="${vehicle.capacity }" type="text"> <span class="input-group-addon">(立方)</span>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-2">车长:</label>
						<div class="col-md-3">
							<div class="row">
								<div class="col-sm-12">
									<div class="input-group">
										<input class="form-control" id="vehicleLength" name="vehicle.vehicleLength" value="${vehicle.vehicleLength }" type="text"> <span class="input-group-addon">(厘米)</span>
									</div>
								</div>
							</div>
						</div>
						<label class="control-label col-md-2">车宽:</label>
						<div class="col-md-3">
							<div class="row">
								<div class="col-sm-12">
									<div class="input-group">
										<input class="form-control" id="vehicleWidth" name="vehicle.vehicleWidth" value="${vehicle.vehicleWidth }" type="text"> <span class="input-group-addon">(厘米)</span>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-2">车高:</label>
						<div class="col-md-3">
							<div class="row">
								<div class="col-sm-12">
									<div class="input-group">
										<input class="form-control" id="vehicleHeight" name="vehicle.vehicleHeight" value="${vehicle.vehicleHeight }" type="text"> <span class="input-group-addon">(厘米)</span>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">备注:</label>
						<div class="col-md-8">
							<textarea id="memo" name="vehicle.memo" class="form-control" rows="4">${vehicle.memo }</textarea>
						</div>
					</div>
				</fieldset>
				<div class="form-actions">
					<div class="row">
						<div class="col-md-12">
							<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
								<i class="fa fa-save"></i> 保存
							</button>
							<button class="btn btn-default" type="button" onclick="loadContent('dtbcenter_vixntvehicle','${nvix}/nvixnt/vixntVehicleAction!goList.action');">
								<i class="fa fa-rotate-left"></i> 返回
							</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
	$("#vehicleForm").validationEngine();
	function saveOrUpdate() {
		if ($("#vehicleForm").validationEngine('validate')) {
			$("#vehicleForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/vixntVehicleAction!saveOrUpdate.action",
			dataType : "text",
			success : function(id) {
				loadContent('dtbcenter_vixntvehicle', '${nvix}/nvixnt/vixntVehicleAction!goList.action');
			}
			});
		} else {
			return false;
		}
	};
</script>