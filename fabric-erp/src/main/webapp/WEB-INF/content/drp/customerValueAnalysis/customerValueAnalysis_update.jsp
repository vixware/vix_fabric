<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	function saveOrUpdateChannelDistributor() {
		if ($('#channelDistributorForm').validationEngine('validate')) {
			$.post('${vix}/drp/customerValueAnalysisAction!saveOrUpdate.action', {
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
				loadContent('${vix}/drp/customerValueAnalysisAction!goList.action');
			});
		} else {
			return false;
		}
	}
	$("#channelDistributorForm").validationEngine();
	function chooseParentOrganization() {
		$.ajax({
		url : '${vix}/drp/customerValueAnalysisAction!goChooseOrganization.action',
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
				<li><a href="#"><img src="${vix}/common/img/drp_channel.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="drp_management" /> </a></li>
				<li><a href="#">商品智能分析与展现 </a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/customerValueAnalysisAction!goList.action');">客户价值分析 </a></li>
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
					<span class="no_line"> <a onclick="saveOrUpdateChannelDistributor()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"><img width="22" height="22" title="保存并提交审批"
							src="${vix}/common/img/dt_submit.png" /></a> <a href="#" onclick="loadContent('${vix}/drp/customerValueAnalysisAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a></span> <strong><b>新增</b></strong>
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
											<td align="right">上级公司：</td>
											<td><input type="text" id="organizationName" name="organizationName" readonly="readonly" value="${channelDistributor.organization.orgName}" class="validate[required] text-input" /><input class="btn" type="button" value="选择" onclick="chooseParentOrganization();" /> <span style="color: red;">*</span> <input type="hidden"
												id="parentId" name="parentId" value="${channelDistributor.organization.id}" />
										</tr>
										<tr>
											<td align="right">名称：</td>
											<td><input id="name" name="name" value="${channelDistributor.name }" type="text" size="20" class="validate[required] text-input"><span style="color: red;">*</span></td>
											<td align="right">简称：</td>
											<td><input id="shortName" name="shortName" value="${channelDistributor.shortName }" type="text" size="20" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">编码：</td>
											<td><input id="code" name="code" value="${channelDistributor.code }" type="text" size="35" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">电话：</td>
											<td><input id="telephone" name="telephone" value="${channelDistributor.telephone }" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">所属行业：</td>
											<td><select id="industry" name="industry" class="validate[required] text-input">
													<option value="">请选择</option>
													<option value="贸易企业" selected="selected">贸易企业</option>
													<option value="化工企业">化工企业</option>
											</select><span style="color: red;">*</span></td>
											<td align="right">员工人数：</td>
											<td><input id="employeeCounts" name="employeeCounts" value="${channelDistributor.employeeCounts }" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">邮箱：</td>
											<td><input id="email" name="email" value="${channelDistributor.email }" type="text" size="30" /></td>
											<td align="right">营业执照：</td>
											<td><input id="permit" name="permit" value="${channelDistributor.permit }" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">所属分类：</td>
											<td><select id="catalog" name="catalog" class="validate[required] text-input">
													<option value="">请选择</option>
													<option value="分类1" selected="selected">分类1</option>
													<option value="分类2">分类2</option>
											</select><span style="color: red;">*</span></td>
											<td align="right">等级：</td>
											<td><select id="level" name="level" class="validate[required] text-input">
													<option value="">请选择</option>
													<option value="A级">A级</option>
													<option value="B级">B级</option>
													<option value="C级" selected="selected">C级</option>
											</select><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">状态：</td>
											<td><select id="status" name="status" class="validate[required] text-input">
													<option value="">请选择</option>
													<option value="营业">营业</option>
													<option value="暂停营业" selected="selected">暂停营业</option>
											</select><span style="color: red;">*</span></td>
											<td align="right">地图链接：</td>
											<td><input id="maplink" name="maplink" value="${channelDistributor.maplink }" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">法人：</td>
											<td><input id="artificialPerson" name="artificialPerson" value="${channelDistributor.artificialPerson }" type="text" size="30" /></td>
											<td align="right">注册资金：</td>
											<td><input id="registeredCapital" name="registeredCapital" value="${channelDistributor.registeredCapital }" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">币种：</td>
											<td><select id="currency" name="currency">
													<option value="人民币￥" selected="selected">人民币￥</option>
													<option value="美元$">美元$</option>
													<option value="欧元O">欧元O</option>
											</select></td>
											<td align="right">图片：</td>
											<td><input id="picture" name="picture" value="${channelDistributor.picture }" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">开户银行：</td>
											<td><input id="openingBank" name="openingBank" value="${channelDistributor.openingBank }" type="text" size="30" /></td>
											<td align="right">银行帐号：</td>
											<td><input id="bankAccount" name="bankAccount" value="${channelDistributor.bankAccount }" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">税号：</td>
											<td><input id="taxCode" name="taxCode" value="${channelDistributor.taxCode }" type="text" size="30" /></td>
											<td align="right">所属地区：</td>
											<td><input id="region" name="region" value="${channelDistributor.region }" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">地址：</td>
											<td><input id="address" name="address" value="${channelDistributor.address }" type="text" size="30" /></td>
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