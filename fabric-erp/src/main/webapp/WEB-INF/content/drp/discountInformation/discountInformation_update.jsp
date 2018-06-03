<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	function saveOrUpdateChannelDistributor() {
		if ($('#channelDistributorForm').validationEngine('validate')) {
			$.post('${vix}/drp/discountInformationAction!saveOrUpdate.action', {
			'channelDistributor.id' : $("#id").val(),
			'organizationId' : $("#parentId").val(),
			'channelDistributor.code' : $("#code").val(),
			'channelDistributor.name' : $("#name").val(),
			'channelDistributor.shortName' : $("#shortName").val(),
			'channelDistributor.status' : $("#status").val(),
			'channelDistributor.telephone' : $("#telephone").val(),
			'channelDistributor.industry' : $("#industry").val(),
			'channelDistributor.employeeCounts' : $("#employeeCounts").val(),
			'channelDistributor.email' : $("#email").val(),
			'channelDistributor.catalog' : $("#catalog").val(),
			'channelDistributor.level' : $("#level").val(),
			'channelDistributor.artificialPerson' : $("#artificialPerson").val(),
			'channelDistributor.registeredCapital' : $("#registeredCapital").val(),
			'channelDistributor.currency' : $("#currency").val(),
			'channelDistributor.openingBank' : $("#openingBank").val(),
			'channelDistributor.bankAccount' : $("#bankAccount").val(),
			'channelDistributor.taxCode' : $("#taxCode").val(),
			'channelDistributor.region' : $("#region").val(),
			'channelDistributor.address' : $("#address").val(),
			'channelDistributor.permit' : $("#permit").val(),
			'channelDistributor.maplink' : $("#maplink").val(),
			'channelDistributor.picture' : $("#picture").val(),
			'channelDistributor.accessory' : $("#accessory").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/drp/discountInformationAction!goList.action');
			});
		} else {
			return false;
		}
	}
	$("#channelDistributorForm").validationEngine();
	function chooseParentOrganization() {
		$.ajax({
		url : '${vix}/drp/discountInformationAction!goChooseOrganization.action',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 560,
			height : 340,
			title : "选择父公司",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						var result = returnValue.split(",");
						if (result[0] == $("#id").val()) {
							asyncbox.success("不允许引用本公司为父公司!", "提示信息");
						} else {
							$("#parentId").val(result[0]);
							$("#organizationName").val(result[1]);
						}
					} else {
						$("#parentId").val("");
						$("#organizationName").val("");
						asyncbox.success("请选择公司信息!", "提示信息");
					}
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	}
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/drp_distributor.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="drp_management" /> </a></li>
				<li><a href="#">经销商门户 </a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/discountInformationAction!goList.action');">折扣信息 </a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="channelDistributor.id" value="${channelDistributor.id}" />
<div class="content">
	<form id="channelDistributorForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateChannelDistributor()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/drp/discountInformationAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a></span> <strong><b>新增</b></strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b><strong>基本信息</strong>
								</dt>
								<dd style="display: block;">
									<table>
										<tr height="30">
											<td align="right">编号:&nbsp;</td>
											<td><input id="code" name="priceCondition.code" value="${priceCondition.code}" /></td>
											<td align="right">主题:&nbsp;</td>
											<td><input id="subject" name="priceCondition.subject" value="${priceCondition.subject}" /></td>
										</tr>
										<tr height="30">
											<td align="right">经销商编码:&nbsp;</td>
											<td><input id="code" name="priceCondition.code" value="${priceCondition.code}" /></td>
											<td align="right">经销商名称:&nbsp;</td>
											<td><input id="subject" name="priceCondition.subject" value="${priceCondition.subject}" /></td>
										</tr>
										<tr height="30">
											<td align="right">${vv:varView("vix_mdm_item")}编码:&nbsp;</td>
											<td><input id="code" name="priceCondition.code" value="${priceCondition.code}" /></td>
											<td align="right">${vv:varView("vix_mdm_item")}名称:&nbsp;</td>
											<td><input id="subject" name="priceCondition.subject" value="${priceCondition.subject}" /></td>
										</tr>
										<tr height="30">
											<th align="right">订货量(从):&nbsp;</th>
											<td><input id="from" name="priceConditionRule.from" value="${priceConditionRule.from}" type="text" /></td>
											<th align="right">订货量(到):&nbsp;</th>
											<td><input id="to" name="priceConditionRule.to" value="${priceConditionRule.to}" type="text" /></td>
										</tr>
										<tr height="30">
											<td align="right">折扣:&nbsp;</td>
											<td><input id="conditionName" name="priceCondition.conditionName" value="${priceCondition.conditionName}" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">状态:&nbsp;</td>
											<td><input id="status" name="priceCondition.status" value="${priceCondition.status}" /></td>
										</tr>
										<tr height="30">
											<td align="right">开始时间:&nbsp;</td>
											<td><input id="startTime" name="priceCondition.startTime" value="${priceCondition.startTime}" type="text" class="validate[required] text-input" /><img onclick="showTime('startTime','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="middle"></td>
											<td align="right">结束时间:&nbsp;</td>
											<td><input id="endTime" name="priceCondition.endTime" value="${priceCondition.endTime}" type="text" class="validate[required] text-input" /><img onclick="showTime('endTime','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="middle"></td>
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