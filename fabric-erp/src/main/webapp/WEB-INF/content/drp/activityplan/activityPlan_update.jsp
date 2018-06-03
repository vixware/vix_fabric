<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
	function saveOrUpdateActivityPlan() {
		var activityPlanDetailData = $("#activityPlanDetail").datagrid("getRows");
		var activityPlanDetailJson = JSON.stringify(activityPlanDetailData);
		if ($('#order').validationEngine('validate')) {
			$.post('${vix}/drp/activityPlanAction!saveOrUpdate.action', {
			'activityPlan.id' : $("#id").val(),
			'activityPlan.name' : $("#name").val(),
			'activityPlan.principal' : $("#principal").val(),
			'activityPlan.salesOrganization' : $("#salesOrganization").val(),
			'activityPlan.salesDepartment' : $("#salesDepartment").val(),
			'activityPlan.activityBudget' : $("#activityBudget").val(),
			'activityPlan.currency' : $("#currency").val(),
			'activityPlan.startTime' : $("#startTime").val(),
			'activityPlan.endTime' : $("#endTime").val(),
			'updateField' : updateField,
			'activityPlanDetailJson' : activityPlanDetailJson
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/drp/activityPlanAction!goList.action');
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
				<li><a href="#"><img src="${vix}/common/img/drp_marketing_activity.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="drp_management" /> </a></li>
				<li><a href="#"><s:text name="drp_marketing_plan_management" /> </a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/activityPlanAction!goList.action?pageNo=${pageNo}');"><s:text name="drp_activity_plan" /> </a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${activityPlan.id}" />
<div class="content">
	<form id="order">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateActivityPlan()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/drp/activityPlanAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a></span> <strong><b><s:text name="drp_activity_plan" /></b></strong>
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
											<th>主题：</th>
											<td><input id="name" name="name" value="${activityPlan.name}" type="text" size="30" onchange="fieldChanged(this);" /></td>
											<th>负责人：</th>
											<td><input id="principal" name="principal" value="${activityPlan.principal}" type="text" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<th>销售组织：</th>
											<td><input id="salesOrganization" name="salesOrganization" value="${activityPlan.salesOrganization }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
											<th>销售部门：</th>
											<td><input id="salesDepartment" name="salesDepartment" value="${activityPlan.salesDepartment }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>活动预算：</th>
											<td><input id="activityBudget" name="activityBudget" value="${activityPlan.activityBudget }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
											<th>币种：</th>
											<td><input id="currency" name="currency" value="${activityPlan.currency }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>活动开始时间：</th>
											<td><input id="startTime" name="startTime" value="<s:date name="%{activityPlan.createTime}" format="yyyy-MM-dd HH:mm:ss"/>" type="text" /><img onclick="showTime('startTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
											<th>活动结束时间：</th>
											<td><input id="endTime" name="endTime" value="<s:date name="%{activityPlan.createTime}" format="yyyy-MM-dd HH:mm:ss"/>" type="text" /><img onclick="showTime('endTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
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
							<li class="current"><a onclick="javascript:tab(4,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />活动计划明细</a></li>
						</ul>
					</div>
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<div style="padding: 8px;">
							<table id="activityPlanDetail" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true,rownumbers : true,toolbar: '#dlAddressTb',url: '${vix}/drp/activityPlanAction!getRefundRuleDetailJson.action?id=${activityPlan.id}',onClickRow: onDlClickRow">
								<thead>
									<tr>
										<th data-options="field:'name',width:100,align:'center',editor:'text'">主题</th>
										<th data-options="field:'itemcode',width:100,align:'center',editor:'text'">商品编码</th>
										<th data-options="field:'itemname',width:100,align:'center',editor:'text'">商品名称</th>
										<th data-options="field:'planContact',width:300,align:'center',editor:'text'">计划内容</th>
										<th data-options="field:'principal',width:100,align:'center',editor:'text'">负责人</th>
										<th data-options="field:'budget',width:100,align:'center',editor:'numberbox'">预算</th>
									</tr>
								</thead>
							</table>
							<div id="dlAddressTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" onclick="appendDlAddress()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span> </a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" onclick="removeDlAddress()"> <span
									class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span>
								</a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" onclick="saveDlAddress()"><span class="l-btn-left"><span class="l-btn-text icon-save l-btn-icon-left"><s:text name="cmn_save" /></span></span> </a>
							</div>
							<script type="text/javascript">
								$('#activityPlanDetail').datagrid();
								var editIndexDlAddress = undefined;
								function endDlEditing() {
									if (editIndexDlAddress == undefined) {
										return true;
									}
									if ($('#activityPlanDetail').datagrid('validateRow', editIndexDlAddress)) {
										$('#activityPlanDetail').datagrid('endEdit', editIndexDlAddress);
										editIndexDlAddress = undefined;
										return true;
									} else {
										return false;
									}
								}
								function onDlClickRow(index) {
									if (editIndexDlAddress != index) {
										if (endDlEditing()) {
											$('#activityPlanDetail').datagrid('selectRow', index).datagrid('beginEdit', index);
											editIndexDlAddress = index;
										} else {
											$('#activityPlanDetail').datagrid('selectRow', editIndexDlAddress);
										}
									}
								}
								function appendDlAddress() {
									if (endDlEditing()) {
										$('#activityPlanDetail').datagrid('appendRow', {
											status : 'P'
										});
										editIndexDlAddress = $('#activityPlanDetail').datagrid('getRows').length - 1;
										$('#activityPlanDetail').datagrid('selectRow', editIndexDlAddress).datagrid('beginEdit', editIndexDlAddress);
									}
								}
								function removeDlAddress() {
									if (editIndexDlAddress == undefined) {
										return;
									}
									$('#activityPlanDetail').datagrid('cancelEdit', editIndexDlAddress).datagrid('deleteRow', editIndexDlAddress);
									editIndexDlAddress = undefined;
								}
								function saveDlAddress() {
									if (endDlEditing()) {
										$('#activityPlanDetail').datagrid('acceptChanges');
									}
								}
							</script>
						</div>
					</div>
				</div>
			</dl>
		</div>
	</form>
</div>