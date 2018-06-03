<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
	function saveOrUpdate() {
		var marketingCampaignDetailData = $("#marketingCampaignDetail").datagrid("getRows");
		var marketingCampaignDetailJson = JSON.stringify(marketingCampaignDetailData);
		if ($('#marketingCampaignForm').validationEngine('validate')) {
			$.post('${vix}/drp/marketingCampaignAction!saveOrUpdate.action', {
			'marketingCampaign.id' : $("#id").val(),
			'marketingCampaign.name' : $("#name").val(),
			'marketingCampaign.creator' : $("#creator").val(),
			'marketingCampaign.createTime' : $("#createTime").val(),
			'marketingCampaign.startTime' : $("#startTime").val(),
			'marketingCampaign.endTime' : $("#endTime").val(),
			'marketingCampaign.status' : $("#status").val(),
			'updateField' : updateField,
			'marketingCampaignDetailJson' : marketingCampaignDetailJson
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/drp/marketingCampaignAction!goList.action');
			});
		} else {
			return false;
		}
	}
	$("#marketingCampaignForm").validationEngine();
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/drp_distribution_management.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="drp_management" /> </a></li>
				<li><a href="#">营销组合管理 </a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/marketingCampaignAction!goList.action');">营销活动 </a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${marketingCampaign.id}" />
<div class="content">
	<form id="marketingCampaignForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdate()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/drp/marketingCampaignAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a></span> <strong><b>新增 </b></strong>
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
											<th>主题：</th>
											<td><input id="name" name="name" value="${marketingCampaign.name}" type="text" size="30" onchange="fieldChanged(this);" /></td>
											<th>创建时间：</th>
											<td><input id="createTime" name="createTime" value="<s:date name="%{marketingCampaign.createTime}" format="yyyy-MM-dd HH:mm:ss"/>" type="text" onchange="fieldChanged(this);" /><img onclick="showTime('createTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<th>创建人：</th>
											<td><input id="creator" name="creator" value="${marketingCampaign.creator}" type="text" size="30" onchange="fieldChanged(this);" /></td>
											<th>活动状态：</th>
											<td><select id="status" name="status" onchange="fieldChanged(this);">
													<option value="1">创建</option>
													<option value="2">下达</option>
											</select></td>
										</tr>
										<tr>
											<th>开始时间：</th>
											<td><input id="startTime" name="startTime" value="<s:date name="%{marketingCampaign.startTime}" format="yyyy-MM-dd HH:mm:ss"/>" type="text" onchange="fieldChanged(this);" /><img onclick="showTime('startTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
											<th>结束时间：</th>
											<td><input id="endTime" name="endTime" value="<s:date name="%{marketingCampaign.endTime}" format="yyyy-MM-dd HH:mm:ss"/>" type="text" onchange="fieldChanged(this);" /><img onclick="showTime('endTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
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
							<li class="current"><a onclick="javascript:tab(4,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />活动明细</a></li>
						</ul>
					</div>
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<div style="padding: 8px;">
							<table id="marketingCampaignDetail" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true, rownumbers : true,toolbar: '#dlAddressTb',url: '${vix}/drp/marketingCampaignAction!getMarketingCampaignDetailJson.action?id=${marketingCampaign.id}',onClickRow: onDlClickRow">
								<thead>
									<tr>
										<th data-options="field:'planContact',width:300,align:'center',editor:'text'">活动内容</th>
										<th data-options="field:'principal',width:150,align:'center',editor:'text'">负责人</th>
										<th data-options="field:'budget',width:150,align:'center',editor:'numberbox'">费用</th>
									</tr>
								</thead>
							</table>
							<div id="dlAddressTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" onclick="appendDlAddress()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span> </a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" onclick="removeDlAddress()"> <span
									class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span>
								</a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" onclick="saveDlAddress()"><span class="l-btn-left"><span class="l-btn-text icon-save l-btn-icon-left"><s:text name="cmn_save" /></span></span> </a>
							</div>
							<script type="text/javascript">
								$('#marketingCampaignDetail').datagrid();
								var editIndexDlAddress = undefined;
								function endDlEditing() {
									if (editIndexDlAddress == undefined) {
										return true;
									}
									if ($('#marketingCampaignDetail').datagrid('validateRow', editIndexDlAddress)) {
										$('#marketingCampaignDetail').datagrid('endEdit', editIndexDlAddress);
										editIndexDlAddress = undefined;
										return true;
									} else {
										return false;
									}
								}
								function onDlClickRow(index) {
									if (editIndexDlAddress != index) {
										if (endDlEditing()) {
											$('#marketingCampaignDetail').datagrid('selectRow', index).datagrid('beginEdit', index);
											editIndexDlAddress = index;
										} else {
											$('#marketingCampaignDetail').datagrid('selectRow', editIndexDlAddress);
										}
									}
								}
								function appendDlAddress() {
									if (endDlEditing()) {
										$('#marketingCampaignDetail').datagrid('appendRow', {
											status : 'P'
										});
										editIndexDlAddress = $('#marketingCampaignDetail').datagrid('getRows').length - 1;
										$('#marketingCampaignDetail').datagrid('selectRow', editIndexDlAddress).datagrid('beginEdit', editIndexDlAddress);
									}
								}
								function removeDlAddress() {
									if (editIndexDlAddress == undefined) {
										return;
									}
									$('#marketingCampaignDetail').datagrid('cancelEdit', editIndexDlAddress).datagrid('deleteRow', editIndexDlAddress);
									editIndexDlAddress = undefined;
								}
								function saveDlAddress() {
									if (endDlEditing()) {
										$('#marketingCampaignDetail').datagrid('acceptChanges');
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