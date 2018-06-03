<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	function saveOrUpdateChannelDistributor(tag) {
		if ($('#channelDistributorForm').validationEngine('validate')) {
			$.post('${vix}/drp/channelAction!saveOrUpdate.action', {
			'channelDistributor.id' : $("#channelDistributorId").val(),
			'channelDistributor.organization.id' : $("#organizationId").val(),
			'channelDistributor.parentChannelDistributor.id' : $("#channelDistributorId").val(),
			'channelDistributor.code' : $("#code").val(),
			'channelDistributor.name' : $("#name").val(),
			'channelDistributor.shortName' : $("#shortName").val(),
			'channelDistributor.status' : $("#status").val(),
			'channelDistributor.type' : $("#type").val(),
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
			'channelDistributor.type' : $("#type").val(),
			'channelDistributor.permit' : $("#permit").val(),
			'channelDistributor.currencyType.id' : $("#currencyTypeId").val(),
			'channelDistributor.maplink' : $("#maplink").val(),
			'channelDistributor.picture' : $("#picture").val(),
			'channelDistributor.accessory' : $("#accessory").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				if (tag == 1) {
					loadContent('${vix}/drp/channelAction!goList.action');
				} else {
					saveOrUpdate(0);
				}
			});
		} else {
			return false;
		}
	}
	$("#channelDistributorForm").validationEngine();
	function chooseParentOrganization() {
		$.ajax({
		url : '${vix}/drp/channelAction!goChooseOrganization.action',
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
							$("#organizationId").val(result[0]);
							$("#organizationName").val(result[1]);
						}
					} else {
						$("#organizationId").val("");
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
	function chooseParentChannelDistributor() {
		$.ajax({
		url : '${vix}/drp/channelAction!goChooseOrganization.action',
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
							$("#channelDistributorId").val(result[0]);
							$("#channelDistributorName").val(result[1]);
						}
					} else {
						$("#channelDistributorId").val("");
						$("#channelDistributorName").val("");
						asyncbox.success("请选择公司信息!", "提示信息");
					}
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	}
	function initdata() {
		$("#type").val('${channelDistributor.type }');
		$("#level").val('${channelDistributor.level }');
		$("#status").val('${channelDistributor.status }');
		$("#currencyTypeId").val('${channelDistributor.currencyType.id }');
	}
	initdata();
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/drp_distributor.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="drp_management" /> </a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/channelAction!goList.action');">渠道管理 </a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="channelDistributorId" name="channelDistributorId" value="${channelDistributor.id}" />
<div class="content">
	<form id="channelDistributorForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateChannelDistributor(1)" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"> </a> <a href="#" onclick="saveOrUpdateChannelDistributor(2)"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /> </a> <a href="#"
						onclick="loadContent('${vix}/drp/channelAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong><b>新增渠道</b> </strong>
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
											<td width="15%" align="right">编码：</td>
											<td width="35%"><input id="code" name="code" value="${channelDistributor.code }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td width="15%" align="right">名称：</td>
											<td width="35%"><input id="name" name="name" value="${channelDistributor.name }" type="text" size="30" class="validate[required] text-input"><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">简称：</td>
											<td><input id="shortName" name="shortName" value="${channelDistributor.shortName }" type="text" size="30" /></td>
											<td align="right">法人：</td>
											<td><input id="artificialPerson" name="artificialPerson" value="${channelDistributor.artificialPerson }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">电话：</td>
											<td><input id="telephone" name="telephone" value="${channelDistributor.telephone }" type="text" size="30" /></td>
											<td align="right">邮箱：</td>
											<td><input id="email" name="email" value="${channelDistributor.email }" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">营业执照：</td>
											<td><input id="permit" name="permit" value="${channelDistributor.permit }" type="text" size="30" /></td>
											<td align="right">税号：</td>
											<td><input id="taxCode" name="taxCode" value="${channelDistributor.taxCode }" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">所属地区：</td>
											<td><input id="region" name="region" value="${channelDistributor.region }" type="text" size="30" /></td>
											<td align="right">地址：</td>
											<td><input id="address" name="address" value="${channelDistributor.address }" type="text" size="30" /></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
				</dd>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b><strong>其他信息</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<td width="15%" align="right">开户银行：</td>
											<td width="35%"><input id="openingBank" name="openingBank" value="${channelDistributor.openingBank }" type="text" size="30" /></td>
											<td width="15%" align="right">银行帐号：</td>
											<td width="35%"><input id="bankAccount" name="bankAccount" value="${channelDistributor.bankAccount }" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">币种：</td>
											<td><s:select id="currencyTypeId" headerKey="-1" headerValue="--请选择--" list="%{currencyTypeList}" listValue="name" listKey="id" value="" multiple="false" theme="simple"></s:select> <span style="color: red;">*</span></td>
											<td align="right">注册资金：</td>
											<td><input id="registeredCapital" name="registeredCapital" value="${channelDistributor.registeredCapital }" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">所属行业：</td>
											<td><select id="industry" name="industry">
													<option value="">请选择</option>
											</select></td>
											<td align="right">员工人数：</td>
											<td><input id="employeeCounts" name="employeeCounts" value="${channelDistributor.employeeCounts }" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">类型：</td>
											<td><select id="type" name="type" class="validate[required] text-input">
													<option value="">请选择</option>
													<option value="1">渠道</option>
													<option value="2">经销商</option>
													<option value="3">代理销售商</option>
													<option value="4">门店</option>
											</select><span style="color: red;">*</span></td>
											<td align="right">所属公司：</td>
											<td><input type="text" id="organizationName" name="organizationName" readonly="readonly" value="${channelDistributor.organization.orgName}" class="validate[required] text-input" size="30" /><input class="btn" type="button" value="选择" onclick="chooseParentOrganization();" /> <span style="color: red;">*</span> <input type="hidden"
												id="organizationId" name="organizationId" value="${channelDistributor.organization.id}" />
										</tr>
										<tr>
											<td align="right">等级：</td>
											<td><select id="level" name="level">
													<option value="">请选择</option>
											</select></td>
											<td align="right">上级代理：</td>
											<td><input id="channelDistributorName" name="channelDistributorName" value="${channelDistributor.parentChannelDistributor.name }" type="text" size="30" readonly="readonly" /><input type="hidden" id="channelDistributorId" name="channelDistributorId" value="${channelDistributor.parentChannelDistributor.id}" /><input class="btn"
												type="button" value="选择" onclick="chooseParentChannelDistributor();" /></td>
										</tr>
										<tr>
											<td align="right">地图链接：</td>
											<td><input id="maplink" name="maplink" value="${channelDistributor.maplink }" type="text" size="30" /></td>
											<td align="right">状态：</td>
											<td><select id="status" name="status">
													<option value="">请选择</option>
													<option value="0">营业</option>
													<option value="1">暂停营业</option>
											</select></td>
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