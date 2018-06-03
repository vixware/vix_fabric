<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
	function saveOrUpdateCompetingChannelInfo() {
		if ($('#competingChannelInfoForm').validationEngine('validate')) {
			$.post('${vix}/drp/competitorInformationAction!saveOrUpdate.action', {
			'competingChannelInfo.id' : $("#id").val(),
			'competingChannelInfo.name' : $("#name").val(),
			'competingChannelInfo.shortName' : $("#shortName").val(),
			'competingChannelInfo.englishName' : $("#englishName").val(),
			'competingChannelInfo.telephone' : $("#telephone").val(),
			'competingChannelInfo.saleOrg' : $("#saleOrg").val(),
			'competingChannelInfo.indetity' : $("#indetity").val(),
			'competingChannelInfo.telefax' : $("#telefax").val(),
			'competingChannelInfo.catalog' : $("#catalog").val(),
			'competingChannelInfo.type' : $("#type").val(),
			'competingChannelInfo.region' : $("#region").val(),
			'competingChannelInfo.employeeCounts' : $("#employeeCounts").val(),
			'competingChannelInfo.parentCompanyCode' : $("#parentCompanyCode").val(),
			'competingChannelInfo.parentCompanyName' : $("#parentCompanyName").val(),
			'competingChannelInfo.url' : $("#url").val(),
			'competingChannelInfo.email' : $("#email").val(),
			'competingChannelInfo.industry' : $("#industry").val(),
			'competingChannelInfo.currency' : $("#currency").val(),
			'competingChannelInfo.catalogs' : $("#catalogs").val(),
			'competingChannelInfo.registeredCapital' : $("#registeredCapital").val(),
			'competingChannelInfo.openingBank' : $("#openingBank").val(),
			'competingChannelInfo.bankAccount' : $("#bankAccount").val(),
			'competingChannelInfo.taxCode' : $("#taxCode").val(),
			'competingChannelInfo.artificialPerson' : $("#artificialPerson").val(),
			'competingChannelInfo.description' : editor.html(),
			'updateField' : updateField,
			'competingChannelInfo.classes' : $("#classes").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/drp/competitorInformationAction!goList.action');
			});
		} else {
			return false;
		}
	}
	$("#competingChannelInfoForm").validationEngine();
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/drp_channel.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="drp_management" /> </a></li>
				<li><a href="#"><s:text name="drp_market_research_management" /> </a></li>
				<li><a href="#">竞争信息管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/competitorInformationAction!goList.action');">竞争者管理</a></li>
			</ul>
		</div>
	</h2>
</div>
<div class="content">
	<form id="competingChannelInfoForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateCompetingChannelInfo()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/drp/competitorInformationAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a></span> <strong><b><s:text name="drp_competitor_information" /></b></strong>
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