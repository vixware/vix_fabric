<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	function saveOrUpdate() {
		if ($('#order').validationEngine('validate')) {
			$.post('${vix}/inventory/limitsTakeAction!saveOrUpdate.action', {
			'stockLimitedTaking.id' : $("#id").val(),
			'stockLimitedTaking.code' : $("#code").val(),
			'stockLimitedTaking.createTime' : $("#createTime").val(),
			'stockLimitedTaking.departmentCode' : $("#departmentCode").val(),
			'stockLimitedTaking.purpose' : $("#purpose").val(),
			'stockLimitedTaking.pickingPeople' : $("#pickingPeople").val(),
			'stockLimitedTaking.warehousecode' : $("#warehousecode").val(),
			'stockLimitedTaking.warehousePerson' : $("#warehousePerson").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				//loadContent('${vix}/inventory/limitsTakeAction!goList.action');
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
				<li><a href="#"><img src="${vix}/common/img/inv_limitstake.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="wim_stock_control" /> </a></li>
				<li><a href="#" onclick="loadContent('${vix}/inventory/limitsTakeAction!goList.action');"><s:text name="inventory_limitstake_business" /> </a></li>
				<a href="#"><s:text name="inventory_limitstake" /> </a>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${stockLimitedTaking.id}" />
<div class="content">
	<form id="order">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdate()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"><img width="22" height="22" title="保存并提交审批"
							src="${vix}/common/img/dt_submit.png" /></a> <a href="#" onclick="loadContent('${vix}/inventory/limitsTakeAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a></span> <strong><b><s:text name="inventory_limitstake" /></b></strong>
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
											<th>编号：</th>
											<td><input id="code" name="stockLimitedTaking.code" value="${stockLimitedTaking.code }" type="text" size="20" class="validate[required] text-input"> <span style="color: red;">*</span></td>
											<th>领料时间：</th>
											<td><input id="createTime" name="stockLimitedTaking.createTime" value="${stockLimitedTaking.createTime}" type="text" /><img onclick="showTime('createTime','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<th>领料部门：</th>
											<td><input id="departmentCode" name="stockLimitedTaking.departmentCode" value="${stockLimitedTaking.departmentCode }" type="text" size="20" /></td>
											<th>用途：</th>
											<td><input id="purpose" name="stockLimitedTaking.purpose" value="${stockLimitedTaking.purpose }" type="text" size="20" /></td>
										</tr>
										<tr>
											<th>领料人：</th>
											<td><input id="pickingPeople" name="stockLimitedTaking.pickingPeople" value="${stockLimitedTaking.pickingPeople }" type="text" size="20" /></td>
											<th>发料仓库：</th>
											<td><input id="warehousecode" name="stockLimitedTaking.warehousecode" value="${stockLimitedTaking.warehousecode}" type="text" size="20" /></td>
										</tr>
										<tr>
											<th>库管员：</th>
											<td><input id="warehousePerson" name="stockLimitedTaking.warehousePerson" value="${stockLimitedTaking.warehousePerson}" type="text" size="20" /></td>
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
								if (!id)
									id = 0;
								if (!_pad_check_entity_saved_id(saveOrUpdate, saveDeliveryAddress))
									return;

								$.ajax({
								url : '${vix}/inventory/limitsTakeAction!goListItem.action?id=' + id,
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
							url : '${vix}/inventory/limitsTakeAction!getStockLimitedTakingItemJson.action?id=${stockLimitedTaking.id}',
							width : 'auto',
							height : 450,
							pagination : true,
							rownumbers : true,
							sortOrder : 'desc',
							striped : true,
							frozenColumns : [ [ {
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
							id : 'saBtnadd',
							text : '新增',
							iconCls : 'icon-add',
							handler : saveDeliveryAddress
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
									url : '${vix}/inventory/limitsTakeAction!deleteWimStockrecordlinesById.action?id=' + rows[i].id,
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
	</form>
</div>