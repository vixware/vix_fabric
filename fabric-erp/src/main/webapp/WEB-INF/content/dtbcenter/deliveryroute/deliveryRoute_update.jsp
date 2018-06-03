<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
	function saveOrUpdateOrder() {
		if ($('#order').validationEngine('validate')) {
			$.post('${vix}/dtbcenter/deliveryRouteAction!saveOrUpdate.action', {
			'dispatchRoute.id' : $("#id").val(),
			'dispatchRoute.pathSerial' : $("#pathSerial").val(),
			'dispatchRoute.name' : $("#name").val(),
			'dispatchRoute.source' : $("#source").val(),
			'dispatchRoute.target' : $("#target").val(),
			'updateField' : updateField,
			'dispatchRoute.mileage' : $("#mileage").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/dtbcenter/deliveryRouteAction!goList.action');
			});
		}
	}
	$("#order").validationEngine();
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/dtbcenter_schedule_line.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="ldm_dtbcenter_management" /> </a></li>
				<li><a href="#"><s:text name="ldm_stowage_scheduling_management" /> </a></li>
				<li><a href="#" onclick="loadContent('${vix}/dtbcenter/deliveryRouteAction!goList.action');"><s:text name="ldm_delivery_route" /> </a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="dispatchRoute.id" value="${dispatchRoute.id}" />
<div class="content">
	<form id="order">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateOrder()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"> </a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /> </a> <a href="#"><img width="22" height="22" title="保存并提交审批"
							src="${vix}/common/img/dt_submit.png" /> </a> <a href="#" onclick="loadContent('${vix}/dtbcenter/deliveryRouteAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong><b><s:text name="ldm_delivery_route" /> </b> </strong>
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
											<th>路线编号：</th>
											<td><input id="pathSerial" name="pathSerial" value="${dispatchRoute.pathSerial }" type="text" size="30" class="validate[required,custom[integer]] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
											<th>路线名称：</th>
											<td><input id="name" name="name" value="${dispatchRoute.name}" class="validate[required] text-input" type="text" size="30" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>起始地：</th>
											<td><input id="source" name="source" value="${dispatchRoute.source}" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
											<th>目的地：</th>
											<td><input id="target" name="target" value="${dispatchRoute.target}" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>里程：</th>
											<td><input id="mileage" name="mileage" value="${dispatchRoute.mileage}" type="text" size="30" class="validate[required,custom[integer]] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
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