<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
	function saveOrUpdateMemberShipInformation() {
		if ($('#order').validationEngine('validate')) {
			$.post('${vix}/drp/membershipInformationAction!saveOrUpdate.action', {
			'customerAccount.id' : $("#id").val(),
			'customerAccount.channelDistributor.id' : $("#channelDistributorId").val(),
			'customerAccount.code' : $("#code").val(),
			'customerAccount.name' : $("#name").val(),
			'customerAccount.mobilePhone' : $("#mobilePhone").val(),
			'customerAccount.creditLevel' : $("#creditLevel").val(),
			'customerAccount.shippingAddressPostalcode' : $("#shippingAddressPostalcode").val(),
			'customerAccount.pointValue' : $("#pointValue").val(),
			'customerAccount.sex' : $("#sex").val(),
			'customerAccount.address' : $("#address").val(),
			'customerAccount.identityType' : $("#identityType").val(),
			'customerAccount.identityId' : $("#identityId").val(),
			'customerAccount.birthday' : $("#birthday").val(),
			'customerAccount.email' : $("#email").val(),
			'customerAccount.startTime' : $("#startTime").val(),
			'customerAccount.endTime' : $("#endTime").val(),
			'updateField' : updateField,
			'customerAccount.status' : $("#status").val(),
			'customerAccount.memo' : memo.html()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/drp/membershipInformationAction!goList.action');
			});
		}
	}
	$("#order").validationEngine();
	function chooseParentOrganization() {
		$.ajax({
		url : '${vix}/drp/distributionSystemRelationShipAction!goChooseOrganization.action',
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
						$("#channelDistributorId").val(result[0]);
						$("#channelDistributorName").val(result[1]);
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

	$(function() {
		if (document.getElementById("sex").value == "") {
			$("#sex").val('${customerAccount.sex}');
		}
		if (document.getElementById("status").value == "") {
			$("#status").val('${customerAccount.status}');
		}
		if (document.getElementById("creditLevel").value == "") {
			$("#creditLevel").val('${customerAccount.creditLevel}');
		}
		if (document.getElementById("identityType").value == "") {
			$("#identityType").val('${customerAccount.identityType}');
		}
	});
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<s:if test="source =='drp' ">
					<li><a href="#"><img src="${vix}/common/img/drp_distribution_management.png" alt="" /> <s:text name="supplyChain" /> </a></li>
					<li><a href="#"><s:text name="drp_management" /> </a></li>
					<li><a href="#"><s:text name="cbm_membership_management" /> </a></li>
					<li><a href="#" onclick="loadContent('${vix}/drp/membershipInformationAction!goList.action?source=drp');"><s:text name="drp_membership_information" /> </a></li>
				</s:if>
				<s:else>
					<li><a href="#"><img src="${vix}/common/img/drp_shop.png" alt="" /> <s:text name="supplyChain" /> </a></li>
					<li><a href="#">连锁经营管理 </a></li>
					<li><a href="#">会员管理 </a></li>
					<li><a href="#" onclick="loadContent('${vix}/drp/membershipInformationAction!goList.action');">会员信息</a></li>
				</s:else>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${customerAccount.id}" />
<div class="content">
	<form id="order">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateMemberShipInformation()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/drp/membershipInformationAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a></span> <strong><b><s:text name="chain_member" /></b></strong>
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
											<th width="15%">编码：</th>
											<td width="35%"><input id="code" name="code" value="${customerAccount.code }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /> <span style="color: red;">*</span></td>
											<th width="15%">姓名：</th>
											<td width="35%"><input id="name" name="name" value="${customerAccount.name }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>证件类型：</th>
											<td><select id="identityType" name="identityType" onchange="fieldChanged(this);">
													<option value="">请选择</option>
													<option value="身份证" selected="selected">身份证</option>
													<option value="驾驶证">驾驶证</option>
													<option value="军人证">军人证</option>
											</select></td>
											<th>证件号码：</th>
											<td><input id="identityId" name="identityId" value="${customerAccount.identityId}" type="text" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<th>性别：</th>
											<td><select id="sex" name="sex" onchange="fieldChanged(this);">
													<option value="">请选择</option>
													<option value="男" selected="selected">男</option>
													<option value="女">女</option>
											</select></td>
											<th>生日：</th>
											<td><input id="birthday" name="birthday" value="<s:date name="%{customerAccount.birthday}" format="yyyy-MM-dd HH:mm:ss"/>" onchange="fieldChanged(this);" type="text" class="validate[required] text-input" /><img onclick="showTime('birthday','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22"
												align="absmiddle"><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>电话：</th>
											<td><input id="mobilePhone" name="mobilePhone" value="${customerAccount.mobilePhone }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /> <span style="color: red;">*</span></td>
											<th>邮件：</th>
											<td><input id="email" name="email" value="${customerAccount.email}" type="text" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<th>地址：</th>
											<td><input id="address" name="address" value="${customerAccount.address}" type="text" size="30" onchange="fieldChanged(this);" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<th>邮编：</th>
											<td><input id="shippingAddressPostalcode" name="shippingAddressPostalcode" value="${customerAccount.shippingAddressPostalcode}" type="text" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<th>开始时间：</th>
											<td><input id="startTime" name="startTime" value="<s:date name="%{customerAccount.startTime}" format="yyyy-MM-dd HH:mm:ss"/>" type="text" onchange="fieldChanged(this);" class="validate[required] text-input" /><img onclick="showTime('startTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22"
												align="absmiddle"><span style="color: red;">*</span></td>
											<th>结束时间：</th>
											<td><input id="endTime" name="endTime" value="<s:date name="%{customerAccount.endTime}" format="yyyy-MM-dd HH:mm:ss"/>" type="text" onchange="fieldChanged(this);" class="validate[required] text-input" /><img onclick="showTime('endTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22"
												align="absmiddle"><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>门店：</th>
											<td><input id="channelDistributorName" name="channelDistributorName" value="${customerAccount.channelDistributor.name }" type="text" onchange="fieldChanged(this);" class="validate[required] text-input" /><input type="hidden" id="channelDistributorId" name="channelDistributorId" value="${customerAccount.channelDistributor.id}" /> <input
												class="btn" type="button" value="选择" onclick="chooseParentOrganization();" /><span style="color: red;">*</span></td>
											<th>等级：</th>
											<td><select id="creditLevel" name="creditLevel" onchange="fieldChanged(this);">
													<option value="">请选择</option>
											</select></td>
										</tr>
										<tr>
											<th>积分：</th>
											<td><input id="pointValue" name="pointValue" value="${customerAccount.pointValue}" type="text" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<td align="right">备注：</td>
											<td colspan="3"><textarea id="memo" name="memo">${channelDistributor.memo }</textarea> <script type="text/javascript" src="${vix}/plugin/KindEditor/kindeditor-min.js"></script> <script type="text/javascript">
												var memo = KindEditor.create('textarea[name="memo"]', {
												basePath : '${vix}/plugin/KindEditor/',
												width : 750,
												height : 200,
												cssPath : '${vix}/plugin/KindEditor/plugins/code/prettify.css',
												uploadJson : '${vix}/plugin/KindEditor/jsp/upload_json.jsp',
												fileManagerJson : '${vix}/plugin/KindEditor/jsp/file_manager_json.jsp',
												allowFileManager : true
												});
											</script></td>
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