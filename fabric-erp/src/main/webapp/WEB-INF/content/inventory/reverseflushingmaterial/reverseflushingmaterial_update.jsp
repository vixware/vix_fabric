<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	function saveOrUpdateOrder() {
		var dlData = $("#dlAddress2").datagrid("getRows");
		var dlJson = JSON.stringify(dlData);
		if ($('#order').validationEngine('validate')) {
			$.post('${vix}/inventory/reverseFlushingMaterialAction!saveOrUpdate.action', {
			'reverseFlushingMaterial.id' : $("#id").val(),
			'reverseFlushingMaterial.code' : $("#code").val(),
			'reverseFlushingMaterial.department' : $("#department").val(),
			'reverseFlushingMaterial.pickingPeople' : $("#pickingPeople").val(),
			'reverseFlushingMaterial.sendingPeople' : $("#sendingPeople").val(),
			'reverseFlushingMaterial.issueType' : $("#issueType").val(),
			'reverseFlushingMaterial.purpose' : $("#purpose").val(),
			'reverseFlushingMaterial.createTime' : $("#createTime").val(),
			'reverseFlushingMaterial.warehousecode' : $("#warehousecode").val(),
			'reverseFlushingMaterial.warehousePerson' : $("#warehousePerson").val(),
			'dlJson' : dlJson
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/inventory/reverseFlushingMaterialAction!goList.action');
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
				<li><a href="#"><img src="${vix}/common/img/inv_reverseFlushingMaterial.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="wim_stock_control" /> </a></li>
				<li><a href="#" onclick="loadContent('${vix}/inventory/reverseFlushingMaterialAction!goList.action?pageNo=${pageNo}');"><s:text name="wim_reverse_flushing_material" /> </a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="reverseFlushingMaterial.id" value="${reverseFlushingMaterial.id}" />
<div class="content">
	<form id="order">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateOrder()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"> </a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /> </a> <a href="#"><img width="22" height="22" title="保存并提交审批"
							src="${vix}/common/img/dt_submit.png" /> </a> <a href="#"><img width="22" height="22" title="取消" src="${vix}/common/img/dt_cancelback.png" /> </a> <a href="#" onclick="loadContent('${vix}/inventory/reverseFlushingMaterialAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png">
					</a>
					</span> <strong><b><s:text name="wim_store_requisition" /> </b> </strong>
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
											<th>领料单号：</th>
											<td><input id="code" name="code" value="${reverseFlushingMaterial.code }" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
											<th>领料部门：</th>
											<td><input id="department" name="department" value="${reverseFlushingMaterial.department}" class="validate[required] text-input" type="text" size="30" /></td>
										</tr>
										<tr>
											<th>仓库：</th>
											<td><input id="warehousecode" name="warehousecode" value="${reverseFlushingMaterial.warehousecode }" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
											<th>库管员：</th>
											<td><input id="warehousePerson" name="warehousePerson" value="${reverseFlushingMaterial.warehousePerson}" class="validate[required] text-input" type="text" size="30" /></td>
										</tr>
										<tr>
											<th>领料人：</th>
											<td><input id="pickingPeople" name="pickingPeople" value="${reverseFlushingMaterial.pickingPeople}" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
											<th>发料人：</th>
											<td><input id="sendingPeople" name="sendingPeople" value="${reverseFlushingMaterial.sendingPeople}" type="text" size="30" /></td>
										</tr>
										<tr>
											<th>领料类型：</th>
											<td><input id="issueType" name="issueType" value="${reverseFlushingMaterial.issueType}" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
											<th>领料用途：</th>
											<td><input id="purpose" name="purpose" value="${reverseFlushingMaterial.purpose}" type="text" size="30" /></td>
										</tr>
										<tr>
											<th>领料日期：</th>
											<td><input id="createTime" name="createTime" value="${reverseFlushingMaterial.createTime}" type="text" class="validate[required] text-input" readonly="readonly" /> <img onclick="showTime('createTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
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
							<li class="current"><a onclick="javascript:tab(4,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />领料单明细</a></li>
						</ul>
					</div>
					<div id="a1" class="right_content" style="position: relative; z-index: 1; background: #FFF;">
						<script type="text/javascript">
							function saveDeliveryAddress(url) {
								$.ajax({
								url : url,
								cache : false,
								success : function(html) {
									asyncbox.open({
									modal : true,
									width : 724,
									height : 340,
									html : html,
									callback : function(action, returnValue) {
										if (action == 'ok') {
											if ($('#daForm').validationEngine('validate')) {
												$.post('${vix}/inventory/reverseFlushingMaterialAction!saveOrUpdateReverseFlushingMaterialItem.action', {
												'id' : $("#id").val(),
												'reverseFlushingMaterialItem.id' : $("#daId").val(),
												'reverseFlushingMaterialItem.itemcode' : $("#itemcode").val(),
												'reverseFlushingMaterialItem.itemname' : $("#itemname").val(),
												'reverseFlushingMaterialItem.itemtype' : $("#itemtype").val(),
												'reverseFlushingMaterialItem.specification' : $("#specification").val(),
												'reverseFlushingMaterialItem.requisitionCount' : $("#requisitionCount").val(),
												'reverseFlushingMaterialItem.price' : $("#price").val()
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
							$('#dlAddress2').datagrid({
							url : '${vix}/inventory/reverseFlushingMaterialAction!getReverseFlushingMaterialItemJson.action?id=${reverseFlushingMaterial.id}',
							title : '领料单明细',
							width : 'auto',
							height : 450,
							fitColumns : false,
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
							}, {
							field : 'requisitionCount',
							title : '数量',
							width : 100,
							align : 'right'
							}, {
							field : 'price',
							title : '单价',
							width : 100,
							align : 'right'
							} ] ],
							toolbar : [ {
							id : 'da2Btnadd',
							text : '添加',
							iconCls : 'icon-add',
							handler : function() {
								$('#btnsave').linkbutton('enable');
								saveDeliveryAddress('${vix}/inventory/reverseFlushingMaterialAction!goSaveOrUpdateReverseFlushingMaterialItem.action?id=0');
							}
							}, '-', {
							id : 'btnedit',
							text : '修改',
							iconCls : 'icon-edit',
							handler : function() {
								var row = $('#dlAddress2').datagrid("getSelected"); //获取你选择的所有行
								if (row) {
									saveDeliveryAddress('${vix}/inventory/reverseFlushingMaterialAction!goSaveOrUpdateReverseFlushingMaterialItem.action?id=' + row.id);
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
									url : '${vix}/inventory/reverseFlushingMaterialAction!deleteReverseFlushingMaterialItemById.action?id=' + rows[i].id,
									cache : false
									});
								}
							}
							} ]
							});
						</script>
						<div style="padding: 8px;">
							<table id="dlAddress2"></table>
						</div>
					</div>
				</div>
			</dl>
		</div>
		<!--submenu-->
	</form>
</div>