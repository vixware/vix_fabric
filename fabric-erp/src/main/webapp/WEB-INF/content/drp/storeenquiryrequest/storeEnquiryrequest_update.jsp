<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
	function saveOrUpdate() {
		if ($('#salesOrderForm').validationEngine('validate')) {
			$.post('${vix}/drp/storeEnquiryrequestAction!saveOrUpdate.action', {
			'salesOrder.id' : $("#id").val(),
			'salesOrder.code' : $("#salesOrderCode").val(),
			'salesOrder.name' : $("#salesOrderName").val(),
			'salesOrder.channelDistributor.id' : $("#channelDistributorId").val(),
			'salesOrder.supplier.id' : $("#supplierId").val(),
			'salesOrder.creator' : $("#creator").val(),
			'salesOrder.bizType' : $("#bizType").val(),
			'updateField' : updateField,
			'salesOrder.createTime' : $("#createTime").val(),
			'salesOrder.deliveryTime' : $("#deliveryTime").val(),
			'salesOrder.memo' : memo.html()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/drp/storeEnquiryrequestAction!goList.action');
			});
		}
	}
	$("#salesOrderForm").validationEngine();

	function chooseParentOrganization() {
		$.ajax({
		url : '${vix}/drp/distributionSystemRelationShipAction!goChooseOrganization.action',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 560,
			height : 340,
			title : "选择父公司",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						var result = returnValue.split(",");
						if (result[0] == $("#id").val()) {
							asyncbox.success("不允许引用本公司为父公司!", "提示信息");
						} else {
							$("#channelDistributorId").val(result[0]);
							$("#channelDistributorName").val(result[1]);
						}
					} else {
						$("#channelDistributorId").val("");
						$("#channelDistributorName").val("");
						asyncbox.success("请选择公司信息!", "提示信息");
					}
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	};
	/** 选择单选供应商 */
	function chooseRadioSupplier() {
		$.ajax({
		url : '${vix}/purchase/purchaseOrderAction!goChooseRadioSupplier.action',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 1000,
			height : 500,
			title : "选择供应商",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						//alert(returnValue); 
						var rv = returnValue.split(",");
						$("#supplierCode").val(rv[2]);
						$("#supplierName").val(rv[1]);
						$("#supplierId").val(rv[0]);
					} else {
						asyncbox.success("请选择分类信息!", "提示信息");
					}
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	};
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/drp_shop.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="drp_management" /> </a></li>
				<li><a href="#"><s:text name="drp_store_management" /> </a></li>
				<li><a href="#" onclick="loadContent('${vix}/drp/storeEnquiryrequestAction!goList.action');"><s:text name="drp_store_enquiry_request" /> </a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${salesOrder.id }" />
<input type="hidden" id="selectId" name="selectId" value="${parentId}" />
<input type="hidden" id="bizType" name="bizType" value="${salesOrder.bizType}" />
<div class="content">
	<form id="salesOrderForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdate()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"> </a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /> </a> <a href="#"
						onclick="loadContent('${vix}/drp/storeEnquiryrequestAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong><b>要货信息</b> </strong>
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
										</tr>
										<tr>
											<td align="right">编码：</td>
											<td><input id="salesOrderCode" name="salesOrderCode" value="${salesOrder.code }" type="text" size="30" onchange="fieldChanged(this);" /></td>
											<td align="right">主题：</td>
											<td><input id="salesOrderName" name="salesOrderName" value="${salesOrder.name }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>门店：</th>
											<td><input id="channelDistributorName" name="channelDistributorName" value="${salesOrder.channelDistributor.name }" type="text" size="30" onchange="fieldChanged(this);" /><input type="hidden" id="channelDistributorId" name="channelDistributorId" value="${salesOrder.channelDistributor.id}" /> <input class="btn" type="button"
												value="选择" onclick="chooseParentOrganization();" /></td>
											<td align="right">创建人：</td>
											<td><input id="creator" name="creator" value="${salesOrder.creator }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">要货时间：</td>
											<td><input id="deliveryTime" name="deliveryTime" value="<fmt:formatDate value='${salesOrder.deliveryTime }' type='both' pattern='yyyy-MM-dd' />" type="text" onchange="fieldChanged(this);" class="validate[required] text-input" /><span style="color: red;">*</span> <img onclick="showTime('deliveryTime','yyyy-MM-dd')"
												src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
											<td align="right">供应商名称：</td>
											<td><input id="supplierName" name="supplierName" value="${salesOrder.supplier.name }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /> <span style="color: red;">*</span> <input type="hidden" id="supplierCode" name="supplierCode" value="${purchaseOrder.supplierCode }" /> <input
												type="hidden" id="supplierId" name="supplierId" value="${salesOrder.supplier.id }" /> <input class="btn" type="button" value="选择" onclick="chooseRadioSupplier();" /></td>
										</tr>
										<tr>
											<td align="right">备注：</td>
											<td colspan="3"><textarea id="memo" name="memo">${salesOrder.memo }</textarea> <script type="text/javascript" src="${vix}/plugin/KindEditor/kindeditor-min.js"></script> <script type="text/javascript">
												var memo = KindEditor.create('textarea[name="memo"]', {
												basePath : '${vix}/plugin/KindEditor/',
												width : 750,
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
							<li class="current"><a onclick="javascript:tab(6,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />明细</a></li>
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
									title : '${vv:varView("vix_mdm_item")}明细',
									modal : true,
									width : 1000,
									height : 400,
									html : html,
									callback : function(action, returnValue) {
									},
									btnsbar : $.btn.OKCANCEL
									});
								}
								});
							}
							$('#saleOrderItem').datagrid({
							url : '${vix}/drp/storeEnquiryrequestAction!getSaleOrderItemJson.action?id=${salesOrder.id}',
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
							fitColumns : true,
							showFooter : true,
							idField : 'id',
							frozenColumns : [ [ {
							field : 'id',
							title : '编号',
							width : 60,
							hidden : true,
							align : 'center'
							}, {
							field : 'itemCode',
							title : '${vv:varView("vix_mdm_item")}编码',
							width : 200,
							align : 'center'
							}, {
							field : 'title',
							title : '${vv:varView("vix_mdm_item")}名称',
							width : 200,
							align : 'center'
							} ] ],
							columns : [ [ {
							field : 'unit',
							title : '单位',
							width : 200,
							align : 'center'
							}, {
							field : 'price',
							title : '单价',
							width : 150,
							align : 'right',
							editor : 'numberbox',
							required : 'true'
							}, {
							field : 'amount',
							title : '数量',
							width : 150,
							align : 'right',
							editor : 'numberbox',
							required : 'true'
							}, {
							field : 'netTotal',
							title : '金额',
							width : 150,
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
								saveDeliveryAddress('${vix}/drp/storeEnquiryrequestAction!goInventoryList.action?salesOrderId=' + $('#id').val());
							}
							}, '-', {
							id : 'btnedit',
							text : '修改',
							iconCls : 'icon-edit',
							handler : function() {
								var row = $('#saleOrderItem').datagrid("getSelected"); //获取你选择的所有行
								if (row) {
									saveDeliveryAddress('${vix}/drp/storeEnquiryrequestAction!goInventoryList.action?id=' + row.id);
								}
							}
							}, '-', {
							text : '删除',
							iconCls : 'icon-remove',
							handler : function() {
								var rows = $('#saleOrderItem').datagrid("getSelections"); //获取你选择的所有行	
								//循环所选的行
								for (var i = 0; i < rows.length; i++) {
									var index = $('#saleOrderItem').datagrid('getRowIndex', rows[i]);//获取某行的行号
									$('#saleOrderItem').datagrid('deleteRow', index); //通过行号移除该行
									$.ajax({
									url : '${vix}/drp/storeEnquiryrequestAction!deleteSaleOrderItemById.action?id=' + rows[i].id,
									cache : false
									});
								}
							}
							} ]
							});
						</script>
						<div style="padding: 8px;">
							<table id="saleOrderItem"></table>
						</div>
					</div>
				</div>
			</dl>
		</div>
	</form>
</div>