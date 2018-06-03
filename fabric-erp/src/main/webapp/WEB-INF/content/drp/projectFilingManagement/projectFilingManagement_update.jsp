<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
	function saveOrUpdateProjectFilingManagement() {
		if ($('#projectFilingManagementForm').validationEngine('validate')) {
			$.post('${vix}/drp/projectFilingManagementAction!saveOrUpdate.action', {
			'projectFilingManagement.id' : $("#id").val(),
			'projectFilingManagement.code' : $("#code").val(),
			'projectFilingManagement.channelDistributor.id' : $("#channelDistributorId").val(),
			'projectFilingManagement.name' : $("#name").val(),
			'projectFilingManagement.customer' : $("#customer").val(),
			'projectFilingManagement.contact' : $("#contact").val(),
			'projectFilingManagement.startTime' : $("#startTime").val(),
			'projectFilingManagement.endTime' : $("#endTime").val(),
			'projectFilingManagement.status' : $("#status").val(),
			'updateField' : updateField,
			'projectFilingManagement.creator' : $("#creator").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/drp/projectFilingManagementAction!goList.action');
			});
		} else {
			return false;
		}
	}
	$("#projectFilingManagementForm").validationEngine();
	function chooseParentOrganization() {
		$.ajax({
		url : '${vix}/drp/distributerManagementAction!goChooseOrganization.action',
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
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/drp_distribution_management.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="drp_management" /> </a></li>
				<li><a href="#">经销商管理 </a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/projectFilingManagementAction!goList.action');">项目备案管理 </a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${projectFilingManagement.id}" />
<div class="content">
	<form id="projectFilingManagementForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateProjectFilingManagement()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"> </a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /> </a> <a href="#"
						onclick="loadContent('${vix}/drp/projectFilingManagementAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong><b>新增 </b> </strong>
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
											<td align="right">经销商：</td>
											<td><input id="channelDistributorName" name="channelDistributorName" value="${projectFilingManagement.channelDistributor.name }" type="text" size="30" /><input type="hidden" id="channelDistributorId" name="channelDistributorId" value="${projectFilingManagement.channelDistributor.id}" /> <input class="btn" type="button" value="选择"
												onclick="chooseParentOrganization();" /></td>
										</tr>
										<tr>
											<td align="right">项目编码：</td>
											<td><input id="code" name="code" value="${projectFilingManagement.code }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
											<td align="right">项目名称：</td>
											<td><input id="name" name="name" value="${projectFilingManagement.name }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">客户：</td>
											<td><input id="customer" name="customer" value="${projectFilingManagement.customer }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
											<td align="right">联系人：</td>
											<td><input id="contact" name="contact" value="${projectFilingManagement.contact }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">开始时间：</td>
											<td><input id="startTime" name="startTime" value="<s:date name="%{projectFilingManagement.startTime}" format="yyyy-MM-dd HH:mm:ss"/>" onchange="fieldChanged(this);" type="text" class="validate[required] text-input" readonly="readonly" size="30" /><img onclick="showTime('startTime','yyyy-MM-dd')"
												src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"><span style="color: red;">*</span></td>
											<td align="right">结束时间：</td>
											<td><input id="endTime" name="endTime" value="<s:date name="%{projectFilingManagement.endTime}" format="yyyy-MM-dd HH:mm:ss"/>" onchange="fieldChanged(this);" type="text" class="validate[required] text-input" readonly="readonly" size="30" /><img onclick="showTime('endTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif"
												width="16" height="22" align="absmiddle"><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">项目状态：</td>
											<td><input id="status" name="status" value="${projectFilingManagement.status }" type="text" size="30" onchange="fieldChanged(this);" /></td>
											<td align="right">备案人：</td>
											<td><input id="creator" name="creator" value="${projectFilingManagement.creator }" type="text" size="30" onchange="fieldChanged(this);" /></td>
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