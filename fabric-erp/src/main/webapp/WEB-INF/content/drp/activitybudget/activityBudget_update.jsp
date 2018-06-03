<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
	function saveOrUpdateOrder() {
		var dlData = $("#dlAddress").datagrid("getRows");
		var dlJson = JSON.stringify(dlData);
		if ($('#order').validationEngine('validate')) {
			$.post('${vix}/drp/activityBudgetAction!saveOrUpdate.action', {
			'activityBudget.id' : $("#id").val(),
			'activityBudget.salesOrganization' : $("#salesOrganization").val(),
			'activityBudget.department' : $("#department").val(),
			'activityBudget.startTime' : $("#startTime").val(),
			'activityBudget.endTime' : $("#endTime").val(),
			'activityBudget.name' : $("#name").val(),
			'activityBudget.principal' : $("#principal").val(),
			'activityBudget.budgetcontent' : $("#budgetcontent").val(),
			'activityBudget.budget' : $("#budget").val(),
			'updateField' : updateField,
			'dlJson' : dlJson
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/drp/activityBudgetAction!goList.action');
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
				<li><a href="#"><img src="${vix}/common/img/drp_channel.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="drp_management" /> </a></li>
				<li><a href="#"><s:text name="drp_marketing_plan_management" /> </a></li>
				<li><a href="#"><s:text name="drp_activity_udget" /> </a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${activityBudget.id}" />
<div class="content">
	<form id="order">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateOrder()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"><img width="22" height="22" title="保存并提交审批"
							src="${vix}/common/img/dt_submit.png" /></a> <a href="#" onclick="loadContent('${vix}/drp/activityBudgetAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a></span> <strong><b><s:text name="drp_activity_udget" /></b></strong>
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
											<th>销售组织：</th>
											<td><input id=salesOrganization name="salesOrganization" value="${activityBudget.salesOrganization }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
											<th>销售部门：</th>
											<td><input id="department" name="department" value="${activityBudget.department }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>活动开始时间：</th>
											<td><input id="startTime" name="startTime" value="${activityBudget.startTime}" type="text" onchange="fieldChanged(this);" /><img onclick="showTime('startTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
											<th>活动结束时间：</th>
											<td><input id="endTime" name="endTime" value="${activityBudget.endTime}" type="text" onchange="fieldChanged(this);" /><img onclick="showTime('endTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<th>主题：</th>
											<td><input id="name" name="name" value="${activityBudget.name}" type="text" size="30" onchange="fieldChanged(this);" /></td>
											<th>负责人：</th>
											<td><input id="principal" name="principal" value="${activityBudget.principal}" type="text" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<th>预算内容：</th>
											<td><input id="budgetcontent" name="budgetcontent" value="${activityBudget.budgetcontent}" type="text" size="30" onchange="fieldChanged(this);" /></td>
											<th>预算：</th>
											<td><input id="budget" name="budget" value="${activityBudget.budget}" type="text" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
				</dd>
				<div class="clearfix" style="background: #FFF; position: relative;">
					<div class="right_menu">
						<ul>
							<li class="current"><a onclick="javascript:tab(4,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />活动预算明细</a></li>
						</ul>
					</div>
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<div style="padding: 8px;">
							<table id="dlAddress" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlAddressTb',url: '${vix}/drp/activityBudgetAction!getWimTransvouchItemJson.action?id=${activityBudget.id}',onClickRow: onDlClickRow">
								<thead>
									<tr>
										<th data-options="field:'cinvcode',width:100,align:'center',editor:'text'">编码</th>
										<th data-options="field:'tvquantity',width:100,align:'center',editor:'text'">主题</th>
										<th data-options="field:'tvacost',width:100,align:'center',editor:'text'">计划内容</th>
										<th data-options="field:'tvaprice',width:100,align:'center',editor:'text'">负责人</th>
										<th data-options="field:'tvpcost',width:100,align:'center',editor:'datebox'">开始时间</th>
										<th data-options="field:'tvpcost',width:100,align:'center',editor:'datebox'">结束时间</th>
										<th data-options="field:'salecost',width:100,align:'center',editor:'numberbox'">预算</th>
									</tr>
								</thead>
							</table>
							<div id="dlAddressTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" onclick="appendDlAddress()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span> </a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" onclick="removeDlAddress()"> <span
									class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span>
								</a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" onclick="saveDlAddress()"><span class="l-btn-left"><span class="l-btn-text icon-save l-btn-icon-left"><s:text name="cmn_save" /></span></span> </a>
							</div>
							<script type="text/javascript">
								$('#dlAddress').datagrid();
								var editIndexDlAddress = undefined;
								function endDlEditing() {
									if (editIndexDlAddress == undefined) {
										return true;
									}
									if ($('#dlAddress').datagrid('validateRow', editIndexDlAddress)) {
										$('#dlAddress').datagrid('endEdit', editIndexDlAddress);
										editIndexDlAddress = undefined;
										return true;
									} else {
										return false;
									}
								}
								function onDlClickRow(index) {
									if (editIndexDlAddress != index) {
										if (endDlEditing()) {
											$('#dlAddress').datagrid('selectRow', index).datagrid('beginEdit', index);
											editIndexDlAddress = index;
										} else {
											$('#dlAddress').datagrid('selectRow', editIndexDlAddress);
										}
									}
								}
								function appendDlAddress() {
									if (endDlEditing()) {
										$('#dlAddress').datagrid('appendRow', {
											status : 'P'
										});
										editIndexDlAddress = $('#dlAddress').datagrid('getRows').length - 1;
										$('#dlAddress').datagrid('selectRow', editIndexDlAddress).datagrid('beginEdit', editIndexDlAddress);
									}
								}
								function removeDlAddress() {
									if (editIndexDlAddress == undefined) {
										return;
									}
									$('#dlAddress').datagrid('cancelEdit', editIndexDlAddress).datagrid('deleteRow', editIndexDlAddress);
									editIndexDlAddress = undefined;
								}
								function saveDlAddress() {
									if (endDlEditing()) {
										$('#dlAddress').datagrid('acceptChanges');
									}
								}
							</script>
						</div>
					</div>
				</div>
			</dl>
		</div>
		<!--submenu-->
	</form>
</div>