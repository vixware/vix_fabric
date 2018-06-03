<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
	function saveOrUpdate() {
		var provincesData = $("#provinces").datagrid("getRows");
		var provincesJson = JSON.stringify(provincesData);
		if ($('#deliveryArea').validationEngine('validate')) {
			$.post('${vix}/business/deliveryAreaAction!saveOrUpdate.action', {
			'deliveryArea.id' : $("#deliveryAreaId").val(),
			'deliveryArea.code' : $("#deliveryAreaCode").val(),
			'deliveryArea.name' : $("#deliveryAreaName").val(),
			'deliveryArea.description' : $("#deliveryAreaDescription").val(),
			'updateField' : updateField,
			'provincesJson' : provincesJson
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/business/deliveryAreaAction!goList.action');
			});
		}
	}
	$("#deliveryArea").validationEngine();
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/inv_inbound.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#">网店管理 </a></li>
				<li><a href="#">物流管理 </a></li>
				<li><a href="#">区域设置 </a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="deliveryAreaId" name="deliveryAreaId" value="${deliveryArea.id}" />
<div class="content">
	<form id="deliveryArea">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdate();" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"> </a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /> </a> <a href="#"
						onclick="loadContent('${vix}/business/deliveryAreaAction!goList.action');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong><b>区域</b> </strong>
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
											<th>区域编码：</th>
											<td><input id="deliveryAreaCode" name="deliveryAreaCode" value="${deliveryArea.code }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
											<th>区域名称：</th>
											<td><input id="deliveryAreaName" name="deliveryAreaName" value="${deliveryArea.name }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>区域描述：</th>
											<td><input id="deliveryAreaDescription" name="deliveryAreaDescription" value="${deliveryArea.description}" type="text" size="30" onchange="fieldChanged(this);" /></td>
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
							<li class="current"><a onclick="javascript:tab(4,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />包含省份</a></li>
						</ul>
					</div>
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<div style="padding: 8px;">
							<table id="provinces" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="iconCls: 'icon-edit',rownumbers : true,singleSelect: true,toolbar: '#provincesTb',url: '${vix}/business/deliveryAreaAction!getProvincesJson.action?id=${deliveryArea.id}',onClickRow: onDlClickRow">
								<thead>
									<tr>
										<th data-options="field:'code',width:200,align:'center',editor:'text'">编码</th>
										<th data-options="field:'name',width:300,align:'center',editor:'text'">名称</th>
									</tr>
								</thead>
							</table>
							<div id="provincesTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" onclick="appendDlAddress()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /> </span> </span> </a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" onclick="removeDlAddress()"> <span
									class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /> </span> </span>
								</a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" onclick="saveDlAddress()"><span class="l-btn-left"><span class="l-btn-text icon-save l-btn-icon-left"><s:text name="cmn_save" /> </span> </span> </a>
							</div>
							<script type="text/javascript">
								$('#provinces').datagrid();
								var editIndexDlAddress = undefined;
								function endDlEditing() {
									if (editIndexDlAddress == undefined) {
										return true;
									}
									if ($('#provinces').datagrid('validateRow', editIndexDlAddress)) {
										$('#provinces').datagrid('endEdit', editIndexDlAddress);
										editIndexDlAddress = undefined;
										return true;
									} else {
										return false;
									}
								}
								function onDlClickRow(index) {
									if (editIndexDlAddress != index) {
										if (endDlEditing()) {
											$('#provinces').datagrid('selectRow', index).datagrid('beginEdit', index);
											editIndexDlAddress = index;
										} else {
											$('#provinces').datagrid('selectRow', editIndexDlAddress);
										}
									}
								}
								function appendDlAddress() {
									if (endDlEditing()) {
										$('#provinces').datagrid('appendRow', {
											status : 'P'
										});
										editIndexDlAddress = $('#provinces').datagrid('getRows').length - 1;
										$('#provinces').datagrid('selectRow', editIndexDlAddress).datagrid('beginEdit', editIndexDlAddress);
									}
								}
								function removeDlAddress() {
									if (editIndexDlAddress == undefined) {
										return;
									}
									$('#provinces').datagrid('cancelEdit', editIndexDlAddress).datagrid('deleteRow', editIndexDlAddress);
									editIndexDlAddress = undefined;
								}
								function saveDlAddress() {
									if (endDlEditing()) {
										$('#provinces').datagrid('acceptChanges');
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