<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	function saveOrUpdateInstallationOrder() {
		if ($('#installationOrderForm').validationEngine('validate')) {
			$.post('${vix}/drp/installationAction!saveOrUpdate.action', {
			'installationOrder.id' : $("#id").val(),
			'installationOrder.code' : $("#code").val(),
			'installationOrder.content' : $("#installationContent").val(),
			'installationOrder.installationLocation' : $("#installationLocation").val(),
			'installationOrder.serviceRequirements' : $("#serviceRequirements").val(),
			'installationOrder.workingHours' : $("#workingHours").val(),
			'installationOrder.contacts' : $("#contacts").val(),
			'installationOrder.phone' : $("#phone").val(),
			'installationOrder.transactor' : $("#transactor").val(),
			'installationOrder.itemname' : $("#itemname").val(),
			'installationOrder.tool' : $("#tool").val(),
			'installationOrder.installCost' : $("#installCost").val(),
			'installationOrder.installdate' : $("#installdate").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/drp/installationAction!goList.action');
			});
		} else {
			return false;
		}
	}
	$("#installationOrderForm").validationEngine();
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/drp_channel.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="drp_management" /> </a></li>
				<li><a href="#">业务功能 </a></li>
				<li><a href="#">安装服务管理 </a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/installationAction!goList.action');">安装管理 </a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${installationOrder.id}" />
<div class="content">
	<form id="installationOrderForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateInstallationOrder()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"><img width="22" height="22" title="保存并提交审批"
							src="${vix}/common/img/dt_submit.png" /></a> <a href="#" onclick="loadContent('${vix}/drp/installationAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a></span> <strong><b>新增</b></strong>
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
											<td align="right">编号：</td>
											<td><input id="code" name="code" value="${installationOrder.code }" type="text" size="20" class="validate[required] text-input"><span style="color: red;">*</span></td>
											<td align="right">安装内容：</td>
											<td><input id="installationContent" name="installationContent" value="${installationOrder.content }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">安装地点：</td>
											<td><input id="installationLocation" name="installationLocation" value="${installationOrder.installationLocation }" type="text" size="30" /></td>
											<td align="right">安装日期：</td>
											<td><input id="installdate" name="installdate" value="<s:date format="yyyy-MM-dd" name="installationOrder.installdate" />" type="text" class="validate[required] text-input" readonly="readonly" /><img onclick="showTime('installdate','yyyy-MM-dd hh:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22"
												align="absmiddle"><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">安装要求：</td>
											<td><input id="serviceRequirements" name="serviceRequirements" value="${installationOrder.serviceRequirements }" type="text" size="30" /></td>
											<td align="right">安装工时：</td>
											<td><input id="workingHours" name="workingHours" value="${installationOrder.workingHours }" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">联系人：</td>
											<td><input id="contacts" name="contacts" value="${installationOrder.contacts }" type="text" size="30" /></td>
											<td align="right">联系电话：</td>
											<td><input id="phone" name="phone" value="${installationOrder.phone }" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">负责人：</td>
											<td><input id="transactor" name="transactor" value="${installationOrder.transactor }" type="text" size="30" /></td>
											<td align="right">材料：</td>
											<td><input id="itemname" name="itemname" value="${installationOrder.itemname }" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">所带工具：</td>
											<td><input id="tool" name="tool" value="${installationOrder.tool }" type="text" size="30" /></td>
											<td align="right">安装费用：</td>
											<td><input id="installCost" name="installCost" value="${installationOrder.installCost }" type="text" size="30" /></td>
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