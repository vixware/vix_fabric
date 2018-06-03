<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	function saveOrUpdateVehicle() {
		if ($('#vehicleForm').validationEngine('validate')) {
			$.post('${vix}/dtbcenter/transferFeesetupAction!saveOrUpdate.action', {
				'vehicle.id' : $("#id").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/dtbcenter/transferFeesetupAction!goList.action');
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
				<li><a href="#"><img src="${vix}/common/img/dtbcenter_cost_settings.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="ldm_dtbcenter_management" /> </a></li>
				<li><a href="#"><s:text name="ldm_operating_management" /> </a></li>
				<li><a href="#" onclick="loadContent('${vix}/dtbcenter/transferFeesetupAction!goList.action');">运费制定</a></li>
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
					<span class="no_line"> <a onclick="" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"> </a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /> </a> <a href="#"
						onclick="loadContent('${vix}/dtbcenter/transferFeesetupAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong><b>运费信息 </b> </strong>
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
											<td align="right">订单编号：</td>
											<td><input id="vehicleCode" name="vehicleCode" value="${vehicle.vehicleCode }" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
											<td align="right">运费名称：</td>
											<td><input id="vehicleNO" name="vehicleNO" value="${vehicle.vehicleNO }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">单价：</td>
											<td><input id="vehicleCode" name="vehicleCode" value="${vehicle.vehicleCode }" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
											<td align="right">单位：</td>
											<td><input id="vehicleNO" name="vehicleNO" value="${vehicle.vehicleNO }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">状态：</td>
											<td><input id="vehicleCode" name="vehicleCode" value="${vehicle.vehicleCode }" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
											<td align="right">制定人：</td>
											<td><input id="vehicleNO" name="vehicleNO" value="${vehicle.vehicleNO }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">描述：</td>
											<td><input id="description" name="description" value="${vehicle.description }" type="text" size="30" /></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
				</dd>
			</dl>
		</div>
	</form>
</div>