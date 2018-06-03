<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
	function saveOrUpdateTransvouch() {
		var dlData = $("#transvouchItem").datagrid("getRows");
		var dlJson = JSON.stringify(dlData);
		if ($('#transvouchForm').validationEngine('validate')) {
			$.post('${vix}/inventory/orderReservationAction!saveOrUpdate.action', {
			'wimTransvouch.id' : $("#id").val(),
			'wimTransvouch.tvcode' : $("#tvcode").val(),
			'wimTransvouch.outdepartmentCode' : $("#outdepartmentCode").val(),
			'wimTransvouch.tvdate' : $("#tvdate").val(),
			'wimTransvouch.indepartmentCode' : $("#indepartmentCode").val(),
			'wimTransvouch.outwarehousecode' : $("#outwarehousecode").val(),
			'wimTransvouch.inwarehousecode' : $("#inwarehousecode").val(),
			'wimTransvouch.outstockcatalogcode' : $("#outstockcatalogcode").val(),
			'wimTransvouch.instockcatalogcode' : $("#instockcatalogcode").val(),
			'updateField' : updateField,
			'dlJson' : dlJson
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/inventory/orderReservationAction!goList.action?menuId=menuTransfer');
			});
		}
	}
	$("#transvouchForm").validationEngine();
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/inv_transfer.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#">库存管理 </a></li>
				<li><a href="#">其他业务处理 </a></li>
				<li><a href="#" onclick="loadContent('${vix}/inventory/collateExistingQuantityAction!goList.action?pageNo=${pageNo}');">整理现存量</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="wimTransvouch.id" value="${wimTransvouch.id}" />
<div class="content">
	<form id="transvouchForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateTransvouch()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"> </a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /> </a> <a href="#"><img width="22" height="22" title="保存并提交审批"
							src="${vix}/common/img/dt_submit.png" /> </a> <a href="#"><img width="22" height="22" title="取消" src="${vix}/common/img/dt_cancelback.png" /> </a> <a href="#" onclick="loadContent('${vix}/inventory/collateExistingQuantityAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png">
					</a>
					</span> <strong><b>商品信息 </b> </strong>
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
											<th>仓库编码：</th>
											<td><input id="tvcode" name="wimTransvouch.tvcode" value="${wimTransvouch.tvcode }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
											<th>仓库名称：</th>
											<td><input id="indepartmentCode" name="wimTransvouch.indepartmentCode" value="${wimTransvouch.indepartmentCode}" type="text" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<th>货位编码：</th>
											<td><input id="outwarehousecode" name="wimTransvouch.outwarehousecode" value="${wimTransvouch.outwarehousecode}" type="text" size="30" onchange="fieldChanged(this);" /></td>
											<th>货位名称：</th>
											<td><input id="inwarehousecode" name="wimTransvouch.inwarehousecode" value="${wimTransvouch.inwarehousecode}" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<th>货位状态：</th>
											<td><input id="outwarehousecode" name="wimTransvouch.outwarehousecode" value="${wimTransvouch.outwarehousecode}" type="text" size="30" onchange="fieldChanged(this);" /></td>
											<th>${vv:varView("vix_mdm_item")}名称：</th>
											<td><input id="inwarehousecode" name="wimTransvouch.inwarehousecode" value="${wimTransvouch.inwarehousecode}" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<th>数量：</th>
											<td><input id="outwarehousecode" name="wimTransvouch.outwarehousecode" value="${wimTransvouch.outwarehousecode}" type="text" size="30" onchange="fieldChanged(this);" /></td>
											<th>单位：</th>
											<td><input id="inwarehousecode" name="wimTransvouch.inwarehousecode" value="${wimTransvouch.inwarehousecode}" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<th align="right">日期：</th>
											<td><input id="createTime" name="createTime" value="${wimStockrecords.createTime}" type="text" onchange="fieldChanged(this);" class="validate[required] text-input" readonly="readonly" /><img onclick="showTime('createTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"><span
												style="color: red;">*</span></td>
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
							<li class="current"><a onclick="javascript:tab(4,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />商品明细</a></li>
						</ul>
					</div>
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<div style="padding: 8px;">
							<table id="transvouchItem" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlAddressTb',url: '${vix}/inventory/orderReservationAction!getWimTransvouchItemJson.action?id=${wimTransvouch.id}',onClickRow: onDlClickRow">
								<thead>
									<tr>
										<th data-options="field:'id',width:60,align:'center'">编号</th>
										<th data-options="field:'cinvcode',width:100,align:'center',editor:'text'">存货编码</th>
										<th data-options="field:'tvquantity',width:100,align:'center',editor:'numberbox'">数量</th>
										<th data-options="field:'tvacost',width:100,align:'center',editor:'numberbox'">实际单价</th>
										<th data-options="field:'tvaprice',width:100,align:'center',editor:'numberbox'">实际金额</th>
										<th data-options="field:'tvpcost',width:100,align:'center',editor:'numberbox'">计划价或售价</th>
										<th data-options="field:'salecost',width:100,align:'center',editor:'numberbox'">零售单价</th>
										<th data-options="field:'saleprice',width:100,align:'center',editor:'numberbox'">金额</th>
									</tr>
								</thead>
							</table>
							<div id="dlAddressTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" onclick="appendDlAddress()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /> </span> </span> </a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" onclick="removeDlAddress()"> <span
									class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /> </span> </span>
								</a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" onclick="saveDlAddress()"><span class="l-btn-left"><span class="l-btn-text icon-save l-btn-icon-left"><s:text name="cmn_save" /> </span> </span> </a>
							</div>
							<script type="text/javascript">
								$('#transvouchItem').datagrid();
								var editIndexDlAddress = undefined;
								function endDlEditing() {
									if (editIndexDlAddress == undefined) {
										return true;
									}
									if ($('#transvouchItem').datagrid('validateRow', editIndexDlAddress)) {
										$('#transvouchItem').datagrid('endEdit', editIndexDlAddress);
										editIndexDlAddress = undefined;
										return true;
									} else {
										return false;
									}
								}
								function onDlClickRow(index) {
									if (editIndexDlAddress != index) {
										if (endDlEditing()) {
											$('#transvouchItem').datagrid('selectRow', index).datagrid('beginEdit', index);
											editIndexDlAddress = index;
										} else {
											$('#transvouchItem').datagrid('selectRow', editIndexDlAddress);
										}
									}
								}
								function appendDlAddress() {
									if (endDlEditing()) {
										$('#transvouchItem').datagrid('appendRow', {
											status : 'P'
										});
										editIndexDlAddress = $('#transvouchItem').datagrid('getRows').length - 1;
										$('#transvouchItem').datagrid('selectRow', editIndexDlAddress).datagrid('beginEdit', editIndexDlAddress);
									}
								}
								function removeDlAddress() {
									if (editIndexDlAddress == undefined) {
										return;
									}
									$('#transvouchItem').datagrid('cancelEdit', editIndexDlAddress).datagrid('deleteRow', editIndexDlAddress);
									editIndexDlAddress = undefined;
								}
								function saveDlAddress() {
									if (endDlEditing()) {
										$('#transvouchItem').datagrid('acceptChanges');
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
