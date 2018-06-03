<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<link href="${vix}/common/css/newservice.css" rel="stylesheet" />
<script type="text/javascript">
	function saveOrUpdateModuleCategory() {
		var dlData = $("#dlAddress").datagrid("getRows");
		var dlJson = JSON.stringify(dlData);
		if ($('#moduleCategoryForm').validationEngine('validate')) {
			$.post('${vix}/system/warningSourceAction!saveOrUpdate.action', {
			'moduleCategory.id' : $("#id").val(),
			'moduleCategory.categoryCode' : $("#categoryCode").val(),
			'moduleCategory.categoryName' : $("#categoryName").val(),
			'moduleCategory.categoryDescription' : $("#categoryDescription").val(),
			'dlJson' : dlJson
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/system/warningSourceAction!goList.action');
			});
		}
	}
	$("#moduleCategoryForm").validationEngine();
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/sys_settings.png" alt="" /> 系统管理 </a></li>
				<li><a href="#">预警中心 </a></li>
				<li><a href="#" onclick="loadContent('${vix}/system/warningSourceAction!goList.action?pageNo=${pageNo}');">预警源设置 </a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${moduleCategory.id}" />
<div class="content">
	<form id="moduleCategoryForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateModuleCategory()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/system/warningSourceAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a></span> <strong><b>任务源</b></strong>
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
											<th>编码：</th>
											<td><input id="categoryCode" name="categoryCode" value="${moduleCategory.categoryCode }" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
											<th>名称：</th>
											<td><input id="categoryName" name="categoryName" value="${moduleCategory.categoryName }" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>描述：</th>
											<td><input id="categoryDescription" name="categoryDescription" value="${moduleCategory.categoryDescription}" type="text" size="30" /></td>
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
							<li class="current"><a onclick="javascript:tab(4,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />预警类型</a></li>
						</ul>
					</div>
					<div id="a1" class="right_content" style="position: relative; z-index: 1; background: #FFF;">
						<div style="padding: 8px;">
							<table id="dlAddress" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="iconCls: 'icon-edit',rownumbers : true,singleSelect: true,fitColumns : true,toolbar: '#dlAddressTb',url: '${vix}/system/warningSourceAction!getWarningTypeJson.action?id=${moduleCategory.id}',onClickRow: onDlClickRow">
								<thead>
									<tr>
										<th data-options="field:'typeName',width:100,align:'center',editor:'text'">名称</th>
										<th data-options="field:'executionFrequency',width:100,align:'center',editor:'numberbox'">执行频率</th>
										<th field="executionFrequencyUtil" width="100" formatter="utilFormatter" editor="{type:'combobox',options:{valueField:'executionFrequencyUtil',textField:'name',data:utilData}}">单位</th>
										<th data-options="field:'executeTime',width:100,align:'center',editor:'datebox'">执行时间</th>
										<th field="classCode" width="100" formatter="typeFormatter" editor="{type:'combobox',options:{valueField:'classCode',textField:'name',data:typeData}}">预警类型</th>
										<th data-options="field:'typeDescription',width:100,align:'center',editor:'text'">描述</th>
									</tr>
								</thead>
							</table>
							<div id="dlAddressTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" onclick="appendDlAddress()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span> </a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" onclick="removeDlAddress()"> <span
									class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span>
								</a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" onclick="saveDlAddress()"><span class="l-btn-left"><span class="l-btn-text icon-save l-btn-icon-left"><s:text name="cmn_save" /></span></span> </a>
							</div>
							<script type="text/javascript">
								var typeData = [ {
								classCode : 'vix.inventory',
								name : '库存预警'
								}, {
								classCode : 'vix.qualityGuaranteePeriodTask',
								name : '保质期预警'
								}, {
								classCode : 'vix.crm',
								name : '客户生日提醒'
								} ];
								function typeFormatter(value) {
									for (var i = 0; i < typeData.length; i++) {
										if (typeData[i].classCode == value)
											return typeData[i].name;
									}
									return value;
								}
								var utilData = [ {
								executionFrequencyUtil : 'H',
								name : '小时'
								}, {
								executionFrequencyUtil : 'M',
								name : '分钟'
								}, {
								executionFrequencyUtil : 'S',
								name : '秒'
								} ];
								function utilFormatter(value) {
									for (var i = 0; i < utilData.length; i++) {
										if (utilData[i].executionFrequencyUtil == value)
											return utilData[i].name;
									}
									return value;
								}
								$.fn.datebox.defaults.formatter = function(date) {
									var y = date.getFullYear();
									var m = date.getMonth() + 1;
									var d = date.getDate();
									return y + '/' + m + '/' + d;
								}
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
											status : ''
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
<!-- content -->
