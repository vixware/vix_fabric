<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
	function saveOrUpdateOrder() {
		if ($('#order').validationEngine('validate')) {
			$.post('${vix}/dtbcenter/loadingManagementAction!saveOrUpdate.action', {
			'stockTaking.id' : $("#id").val(),
			'stockTaking.stocktakingcode' : $("#stocktakingcode").val(),
			'stockTaking.warehousecode' : $("#warehousecode").val(),
			'stockTaking.createTime' : $("#createTime").val(),
			'stockTaking.sttype' : $("#sttype").val(),
			'stockTaking.departmentCode' : $("#departmentCode").val(),
			'stockTaking.itemposition' : $("#itemposition").val(),
			'stockTaking.stbatch' : $("#stbatch").val(),
			'stockTaking.accountdate' : $("#accountdate").val(),
			'updateField' : updateField,
			'stockTaking.personcode' : $("#personcode").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/dtbcenter/loadingManagementAction!goList.action');
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
				<li><a href="#"><img src="${vix}/common/img/dtbcenter_transportation_management.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="dtbcenter" /> </a></li>
				<li><a href="#"><s:text name="dtbcenter_transpotmgr" /> </a></li>
				<li><a href="#"><s:text name="dtbcenter_loadingManagement_business" /> </a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="stockTaking.id" value="${stockTaking.id}" />
<div class="content">
	<form id="order">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateOrder()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"> </a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /> </a> <a href="#"><img width="22" height="22" title="保存并提交审批"
							src="${vix}/common/img/dt_submit.png" /> </a> <a href="#" onclick="loadContent('${vix}/dtbcenter/loadingManagementAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong><b><s:text name="dtbcenter_loadingManagement" /> </b> </strong>
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
											<th>装车单编号：</th>
											<td><input id="stocktakingcode" name="stocktakingcode" value="${stockTaking.stocktakingcode }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
											<th>装车日期：</th>
											<td><input id="createTime" name="createTime" value="${stockTaking.createTime}" type="text" class="validate[required] text-input" readonly="readonly" onchange="fieldChanged(this);" /> <img onclick="showTime('createTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<th>接货单位：</th>
											<td><input id="warehousecode" name="warehousecode" value="${stockTaking.warehousecode}" class="validate[required] text-input" type="text" size="30" onchange="fieldChanged(this);" /></td>
											<th>所装车辆种类：</th>
											<td><select id="sttype" name="sttype" class="validate[required] text-input" onchange="fieldChanged(this);">
													<option value="">请选择</option>
													<option value="1">盘盈盘亏</option>
													<option value="2">委托代销商品出库</option>
													<option value="3">商品报损出库</option>
											</select></td>
										</tr>
										<tr>
											<th>车号：</th>
											<td><input id="departmentCode" name="departmentCode" value="${stockTaking.departmentCode}" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
											<th>司机：</th>
											<td><input id="itemposition" name="itemposition" value="${stockTaking.itemposition}" type="text" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<th>始发站：</th>
											<td><input id="stbatch" name="stbatch" value="${stockTaking.stbatch }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
											<th>到站：</th>
											<td><input id="personcode" name="personcode" value="${stockTaking.personcode }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>联系人：</th>
											<td><input id="stbatch" name="stbatch" value="${stockTaking.stbatch }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
											<th>联系方式：</th>
											<td><input id="personcode" name="personcode" value="${stockTaking.personcode }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>交货日期：</th>
											<td><input id="createTime" name="createTime" value="${stockTaking.createTime}" type="text" class="validate[required] text-input" readonly="readonly" onchange="fieldChanged(this);" /> <img onclick="showTime('createTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
											<th>交货地点：</th>
											<td><input id="personcode" name="personcode" value="${stockTaking.personcode }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
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
							<li class="current"><a onclick="javascript:tab(4,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />装车单明细</a></li>
						</ul>
					</div>
					<div id="a1" style="width: 100%; position: relative; z-index: 1; background: #FFF;">
						<script type="text/javascript">
							function saveDeliveryAddress(url) {
								$.ajax({
								url : url,
								cache : false,
								success : function(html) {
									asyncbox.open({
									modal : true,
									title : '装车单明细',
									width : 724,
									height : 340,
									html : html,
									callback : function(action, returnValue) {
										if (action == 'ok') {
											if ($('#daForm').validationEngine('validate')) {
												$.post('${vix}/dtbcenter/loadingManagementAction!saveOrUpdateStockTakingItem.action', {
												'id' : $("#id").val(),
												'stockTakingItem.id' : $("#daId").val(),
												'stockTakingItem.itemcode' : $("#itemcode").val(),
												'stockTakingItem.itemname' : $("#itemname").val(),
												'stockTakingItem.itemtype' : $("#itemtype").val(),
												'stockTakingItem.specification' : $("#specification").val(),
												'stockTakingItem.cvquantity' : $("#cvquantity").val(),
												'stockTakingItem.cvcquantity' : $("#cvcquantity").val(),
												'stockTakingItem.cvbatch' : $("#cvbatch").val(),
												'stockTakingItem.cvdisdate' : $("#cvdisdate").val()
												}, function(result) {
													showMessage(result);
													setTimeout("", 1000);
													$('#dlAddress2').datagrid("reload");
												});
											}
										}
									},
									btnsbar : $.btn.OKCANCEL
									});
								}
								});
							}
							$('#dlAddress2')
									.datagrid({
									url : '${vix}/dtbcenter/loadingManagementAction!getStockTakingItemJson.action?id=${stockTaking.id}',
									title : '装车单明细',
									width : '100%',
									height : 450,
									fitColumns : true,
									pagination : true,
									columns : [ [ {
									field : 'id',
									title : '编号',
									width : 100,
									align : 'center'
									}, {
									field : 'itemcode',
									title : '${vv:varView("vix_mdm_item")}编码',
									width : 100,
									align : 'center'
									}, {
									field : 'itemname',
									title : '${vv:varView("vix_mdm_item")}名称',
									width : 100,
									align : 'center'
									}, {
									field : 'itemtype',
									title : '${vv:varView("vix_mdm_item")}类型',
									width : 100,
									align : 'center'
									}, {
									field : 'specification',
									title : '规格型号',
									width : 100,
									align : 'center'
									} ] ],
									toolbar : [ {
									id : 'da2Btnadd',
									text : '新增',
									iconCls : 'icon-add',
									handler : function() {
										$('#btnsave').linkbutton('enable');
										saveDeliveryAddress('${vix}/dtbcenter/loadingManagementAction!goSaveOrUpdateStockTakingItem.action?id=0');
									}
									}, '-', {
									id : 'btnedit',
									text : '修改',
									iconCls : 'icon-edit',
									handler : function() {
										var row = $('#dlAddress2').datagrid("getSelected"); //获取你选择的所有行
										if (row) {
											saveDeliveryAddress('${vix}/dtbcenter/loadingManagementAction!goSaveOrUpdateStockTakingItem.action?id=' + row.id);
										}
									}
									}, '-', {
									text : '删除',
									iconCls : 'icon-remove',
									handler : function() {
										var rows = $('#dlAddress2').datagrid("getSelections"); //获取你选择的所有行	
										//循环所选的行
										for (var i = 0; i < rows.length; i++) {
											var index = $('#dlAddress2').datagrid('getRowIndex', rows[i]);//获取某行的行号
											$('#dlAddress2').datagrid('deleteRow', index); //通过行号移除该行
											$.ajax({
											url : '${vix}/dtbcenter/loadingManagementAction!deleteStockTakingItemById.action?id=' + rows[i].id,
											cache : false
											});
										}
									}
									} ]
									});
							function format(date) {
								var y = date.getFullYear();
								var m = date.getMonth() + 1;
								var d = date.getDate();
								return y + '-' + m + '-' + d;
							}
						</script>
						<div style="padding: 8px;">
							<table id="dlAddress2"></table>
						</div>
					</div>
				</div>
			</dl>
		</div>
	</form>
</div>