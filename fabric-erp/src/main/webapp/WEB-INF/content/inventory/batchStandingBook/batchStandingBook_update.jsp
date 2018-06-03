<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
	function saveOrUpdateInvMainBatch() {
		if ($('#invMainBatchForm').validationEngine('validate')) {
			$.post('${vix}/inventory/batchStandingBookAction!saveOrUpdate.action', {
			'invMainBatch.id' : $("#id").val(),
			'invMainBatch.batchCode' : $("#batchCode").val(),
			'invMainBatch.batchState' : $("#batchState").val(),
			'invMainBatch.invOrganization' : $("#invOrganization").val(),
			'invMainBatch.itemName' : $("#itemName").val(),
			'invMainBatch.status' : $("#status").val(),
			'invMainBatch.produceDate' : $("#produceDate").val(),
			'invMainBatch.qualityPeriod' : $("#qualityPeriod").val(),
			'invMainBatch.expiryDate' : $("#expiryDate").val(),
			'invMainBatch.supplierName' : $("#supplierName").val(),
			'updateField' : updateField,
			'invMainBatch.level' : $("#level").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/inventory/batchStandingBookAction!goList.action');
			});
		}
	}
	$("#invMainBatchForm").validationEngine();
	function saveDeliveryAddress(url) {
		$
				.ajax({
				url : url,
				cache : false,
				success : function(html) {
					asyncbox
							.open({
							modal : true,
							width : 900,
							height : 550,
							title : "选择订单",
							html : html,
							callback : function(action, returnValue) {
								if (action == 'ok') {
									if (returnValue != '') {
										$
												.ajax({
												url : '${vix}/inventory/batchStandingBookAction!createRelationship.action?salesOrderid=' + returnValue + "&id=${invMainBatch.id}",
												cache : false,
												success : function(result) {
													showMessage(result);
													setTimeout("", 1000);
													$('#delieryNotificationtable').datagrid("reload");
												}
												});
									} else {
										asyncbox.success("请选择订单!", "<s:text name='vix_message'/>");
										return false;
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
				<li><a href="#"><img src="${vix}/common/img/inv_batch.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="wim_stock_control" /> </a></li>
				<li><a href="#">批次管理 </a></li>
				<li><a href="#" onclick="loadContent('${vix}/inventory/batchStandingBookAction!goList.action?pageNo=${pageNo}');">批次台账 </a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${invMainBatch.id}" />
<div class="content">
	<form id="invMainBatchForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateInvMainBatch()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"> </a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /> </a> <a href="#"><img width="22" height="22" title="保存并提交审批"
							src="${vix}/common/img/dt_submit.png" /> </a> <a href="#"><img width="22" height="22" title="取消" src="${vix}/common/img/dt_cancelback.png" /> </a> <a href="#" onclick="loadContent('${vix}/inventory/batchStandingBookAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png">
					</a>
					</span> <strong><b>批次主文件</b> </strong>
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
											<th>批次编码：</th>
											<td><input id="batchCode" name="batchCode" value="${invMainBatch.batchCode }" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
											<th>批次说明：</th>
											<td><input id="batchState" name="batchState" value="${invMainBatch.batchState}" type="text" size="30" /></td>
										</tr>
										<tr>
											<th>库存组织：</th>
											<td><input id="invOrganization" name="invOrganization" value="${invMainBatch.invOrganization }" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
											<th>${vv:varView("vix_mdm_item")}名称：</th>
											<td><input id="itemName" name="itemName" value="${invMainBatch.itemName}" type="text" size="30" /></td>
										</tr>
										<tr>
											<th>批次状态：</th>
											<td><input id="status" name="status" value="${invMainBatch.status }" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
											<th>生产日期：</th>
											<td><input id="produceDate" name="produceDate" value="${invMainBatch.produceDate}" type="text" /><img onclick="showTime('produceDate','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<th>保质期：</th>
											<td><input id="qualityPeriod" name="qualityPeriod" value="${invMainBatch.qualityPeriod }" type="text" size="30" class="validate[required] text-input"> <span style="color: red;">*</span></td>
											<th>失效日期：</th>
											<td><input id="expiryDate" name="expiryDate" value="${invMainBatch.expiryDate}" type="text" /><img onclick="showTime('expiryDate','yyyy-MM-dd HH:mm:ss')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<th>供应商：</th>
											<td><input id="supplierName" name="supplierName" value="${invMainBatch.supplierName}" type="text" size="30" /></td>
											<th>等级：</th>
											<td><select id="level" name="level">
													<option value="" selected="selected">请选择</option>
											</select></td>
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
							<li class="current"><a onclick="javascript:tab(4,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />收发记录子表</a></li>
						</ul>
					</div>
					<div id="a1" class="right_content" style="position: relative; z-index: 1; background: #FFF;">
						<script type="text/javascript">
							$('#delieryNotificationtable').datagrid({
							url : '${vix}/inventory/batchStandingBookAction!getInvStockRecordLinesJson.action?id=${invMainBatch.id}',
							width : "100%",
							height : 450,
							nowrap : true,
							autoRowHeight : false,
							striped : true,
							collapsible : true,
							sortName : 'id',
							sortOrder : 'desc',
							remoteSort : true,
							pagination : true,
							rownumbers : true,
							showFooter : true,
							idField : 'id',
							columns : [ [ {
							field : 'itemcode',
							title : '编码',
							width : 100,
							align : 'center'
							}, {
							field : 'itemname',
							title : '名称',
							width : 100,
							align : 'center'
							}, {
							field : 'itemtype',
							title : '${vv:varView("vix_mdm_item")}类型 ',
							width : 100,
							align : 'center'
							}, {
							field : 'specification',
							title : '规格型号',
							width : 100,
							align : 'right'
							}, {
							field : 'unit',
							title : '单位',
							width : 100,
							align : 'right'
							}, {
							field : 'quantity',
							title : '数量',
							width : 100,
							align : 'right',
							editor : 'numberbox'
							}, {
							field : 'salecost',
							title : '单价',
							width : 100,
							align : 'center',
							editor : 'numberbox'
							} ] ],
							toolbar : [ {
							id : 'da2Btnadd',
							text : '新增',
							iconCls : 'icon-add',
							handler : function() {
								$('#btnsave').linkbutton('enable');
								saveDeliveryAddress('${vix}/inventory/batchStandingBookAction!goChooseInvStockRecordLines.action');
							}
							}, '-', {
							text : '删除',
							iconCls : 'icon-remove',
							handler : function() {
								var rows = $('#delieryNotificationtable').datagrid("getSelections"); //获取你选择的所有行	
								//循环所选的行
								for (var i = 0; i < rows.length; i++) {
									var index = $('#delieryNotificationtable').datagrid('getRowIndex', rows[i]);//获取某行的行号
									$('#delieryNotificationtable').datagrid('deleteRow', index); //通过行号移除该行
									$.ajax({
									url : '${vix}/inventory/batchStandingBookAction!goChooseInvStockRecordLines.action?id=' + rows[i].id,
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
							<table id="delieryNotificationtable"></table>
						</div>
					</div>
				</div>
			</dl>
		</div>
		<!--submenu-->
	</form>
</div>