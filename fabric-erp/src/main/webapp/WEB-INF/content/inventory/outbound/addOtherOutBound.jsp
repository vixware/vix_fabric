<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
	/** 保存材料出库单 */
	function saveOrUpdate() {
		/** 明细 */
		var biztype = 3;
		if ($('#order').validationEngine('validate')) {
			$.post('${vix}/inventory/outBoundAction!saveOrUpdate.action', {
			'stockRecords.id' : $("#stockRecordsId").val(),
			'stockRecords.code' : $("#code").val(),
			'stockRecords.name' : $("#name").val(),
			'stockRecords.createTime' : $("#createTime").val(),
			'stockRecords.bizclasscode' : $("#bizclasscode").val(),
			'stockRecords.memo' : memo.html(),
			'stockRecords.bizsource' : $("#bizsource").val(),
			'stockRecords.invWarehouse.id' : $("#warehouseId").val(),
			'stockRecords.warehousePerson' : $("#warehousePerson").val(),
			'stockRecords.totalAmount' : $("#totalAmount").val(),
			'updateField' : updateField,
			'biztype' : biztype
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/inventory/outBoundAction!goList.action');
			});
		}
	}
	function saveAndNew() {
		/** 明细 */
		var biztype = 3;
		if ($('#order').validationEngine('validate')) {
			$.post('${vix}/inventory/outBoundAction!saveOrUpdate.action', {
			'stockRecords.id' : $("#stockRecordsId").val(),
			'stockRecords.code' : $("#code").val(),
			'stockRecords.name' : $("#name").val(),
			'stockRecords.createTime' : $("#createTime").val(),
			'stockRecords.bizclasscode' : $("#bizclasscode").val(),
			'stockRecords.memo' : memo.html(),
			'stockRecords.bizsource' : $("#bizsource").val(),
			'stockRecords.invWarehouse.id' : $("#warehouseId").val(),
			'stockRecords.warehousePerson' : $("#warehousePerson").val(),
			'stockRecords.totalAmount' : $("#totalAmount").val(),
			'updateField' : updateField,
			'biztype' : biztype
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				saveOrUpdateOtherOutBound(0);
			});
		}
	}

	function approvalStockRecords(tag) {
		/** 明细 */
		var biztype = 3;
		if ($('#order').validationEngine('validate')) {
			$.post('${vix}/inventory/outBoundAction!saveOrUpdate.action', {
			'stockRecords.id' : $("#stockRecordsId").val(),
			'stockRecords.code' : $("#code").val(),
			'stockRecords.name' : $("#name").val(),
			'stockRecords.createTime' : $("#createTime").val(),
			'stockRecords.bizclasscode' : $("#bizclasscode").val(),
			'stockRecords.memo' : memo.html(),
			'stockRecords.bizsource' : $("#bizsource").val(),
			'stockRecords.invWarehouse.id' : $("#warehouseId").val(),
			'stockRecords.warehousePerson' : $("#warehousePerson").val(),
			'stockRecords.totalAmount' : $("#totalAmount").val(),
			'updateField' : updateField,
			'biztype' : biztype,
			'tag' : tag
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/inventory/outBoundAction!goList.action');
			});
		}
	}
	$("#order").validationEngine();

	function chooseWarehouse() {
		$.ajax({
		url : '${vix}/inventory/warehouseAction!goChooseWarehouse.action',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 560,
			height : 340,
			title : "选择仓库",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						var result = returnValue.split(",");
						$("#warehouseId").val(result[0]);
						$("#warehouseName").val(result[1]);
					} else {
						asyncbox.success("请选择仓库信息!", "提示信息");
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
				<li><a href="#"><img src="${vix}/common/img/inv_outbound.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="wim_stock_control" /> </a></li>
				<li><a href="#" onclick="loadContent('${vix}/inventory/outBoundAction!goList.action?pageNo=${pageNo}');"> <s:text name="inventory_outbound_business" />
				</a></li>
				<a href="#"><s:text name="inventory_other_outbound" /> </a>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="stockRecordsId" name="stockRecordsId" value="${stockRecords.id}" />
<div class="content">
	<form id="order">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdate()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#" onclick="saveAndNew()"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <s:if test="isAllowAudit == 1">
							<a onclick="approvalStockRecords(1);" href="###"><img width="22" height="22" alt="保存并提交审批" src="${vix}/common/img/dt_submit.png" /></a>
						</s:if> <a href="#" onclick="loadContent('${vix}/inventory/outBoundAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a></span> <strong><b><s:text name="inventory_other_outbound" /></b></strong>
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
											<td><input id="code" name="code" value="${stockRecords.code }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
											<th>主题：</th>
											<td><input id="name" name="name" value="${stockRecords.name }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>仓库：</th>
											<td><input type="hidden" id="warehouseId" name="warehouseId" value="${stockRecords.invWarehouse.id }" onchange="fieldChanged(this);" /><input type="text" name="warehouseName" id="warehouseName" value="${stockRecords.invWarehouse.name }" size="30" class="validate[required] text-input" /><span style="color: red;">*</span><input
												class="btn" type="button" value="选择" onclick="chooseWarehouse();" /></td>
											<th>出库时间：</th>
											<td><input id="createTime" name="createTime" value="${stockRecords.createTime}" onchange="fieldChanged(this);" type="text" /><img onclick="showTime('createTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>出库类型：</th>
											<td><select id="bizclasscode" name="bizclasscode" onchange="fieldChanged(this);">
													<option value="">请选择</option>
													<option value="1">销售出库</option>
													<option value="2">材料出库</option>
													<option value="3" selected="selected">其他出库</option>
											</select></td>
											<th>库管员：</th>
											<td><input id="warehousePerson" name="warehousePerson" value="${stockRecords.warehousePerson }" type="text" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<th>备注：</th>
											<td colspan="3"><textarea id="memo" name="memo" onchange="fieldChanged(this);">${stockRecords.memo }</textarea> <script type="text/javascript" src="${vix}/plugin/KindEditor/kindeditor-min.js"></script> <script type="text/javascript">
												var memo = KindEditor.create('textarea[name="memo"]', {
												basePath : '${vix}/plugin/KindEditor/',
												width : 650,
												height : 200,
												cssPath : '${vix}/plugin/KindEditor/plugins/code/prettify.css',
												uploadJson : '${vix}/plugin/KindEditor/jsp/upload_json.jsp',
												fileManagerJson : '${vix}/plugin/KindEditor/jsp/file_manager_json.jsp',
												allowFileManager : true
												});
											</script></td>
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
							<li class="current"><a onclick="javascript:tab(4,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />明细</a></li>
						</ul>
					</div>
					<div id="a1" class="right_content" style="position: relative; z-index: 1; background: #FFF;">
						<script type="text/javascript">
							function saveDeliveryAddress(id) {
								$.ajax({
								url : '${vix}/inventory/outBoundAction!goSaveOrUpdateStockRecordLines.action?id=' + id,
								cache : false,
								success : function(html) {
									asyncbox.open({
									modal : true,
									width : 950,
									height : 575,
									title : "明细",
									html : html,
									callback : function(action) {
										if (action == 'cancel' || action == 'close') {
											$('#dlAddress2').datagrid("reload");
										}
									},
									btnsbar : [ {
									text : '关闭',
									action : 'cancel'
									} ]
									});
								}
								});
							}
							$('#dlAddress2').datagrid({
							url : '${vix}/inventory/outBoundAction!getOrderItemJson.action?id=${stockRecords.id}',
							width : 'auto',
							height : 450,
							pagination : true,
							rownumbers : true,
							sortOrder : 'desc',
							striped : true,
							frozenColumns : [ [ {
							field : 'id',
							title : '编号',
							width : 60,
							align : 'center'
							}, {
							field : 'itemcode',
							title : '商品编码',
							width : 100,
							align : 'center'
							} ] ],
							columns : [ [ {
							field : 'itemname',
							title : '商品名称',
							width : 100,
							align : 'center'
							}, {
							field : 'specification',
							title : '规格型号',
							width : 100,
							align : 'center'
							}, {
							field : 'skuCode',
							title : 'SKU编码',
							width : 100,
							align : 'center'
							}, {
							field : 'barCode',
							title : '条形码',
							width : 100,
							align : 'center'
							}, {
							field : 'unit',
							title : '单位',
							width : 100,
							align : 'center'
							}, {
							field : 'quantity',
							title : '数量',
							width : 100,
							align : 'right',
							editor : 'numberbox',
							required : 'true'
							}, {
							field : 'unitcost',
							title : '单价',
							width : 100,
							align : 'right',
							editor : 'numberbox',
							required : 'true'
							}, {
							field : 'price',
							title : '金额',
							width : 100,
							align : 'right',
							editor : 'numberbox',
							required : 'true'
							} ] ],
							toolbar : [ {
							id : 'da2Btnadd',
							text : '新增',
							iconCls : 'icon-add',
							handler : function() {
								$('#btnsave').linkbutton('enable');
								saveDeliveryAddress(0);
							}
							}, '-', {
							id : 'btnedit',
							text : '修改',
							iconCls : 'icon-edit',
							handler : function() {
								var row = $('#dlAddress2').datagrid("getSelected"); //获取你选择的所有行
								if (row) {
									saveDeliveryAddress(row.id);
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
									url : '${vix}/inventory/outBoundAction!deleteWimStockrecordlinesById.action?id=' + rows[i].id,
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
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<th>制单人：</th>
											<td><input id="creator" name="creator" value="${stockRecords.creator }" type="text" onchange="fieldChanged(this);" /></td>
											<th>总计：</th>
											<td><input id="totalAmount" name="totalAmount" value="${stockRecords.totalAmount }" type="text" onchange="fieldChanged(this);" /></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
				</dd>
			</dl>
		</div>
	</form>
</div>