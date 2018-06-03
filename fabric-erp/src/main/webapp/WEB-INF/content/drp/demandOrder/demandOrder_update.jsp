<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
	//保存采购计划
	function saveOrUpdate() {
		if ($('#purchaseOrderForm').validationEngine('validate')) {
			$.post('${vix}/drp/demandOrderAction!saveOrUpdate.action', {
			'purchaseOrder.id' : $("#purchaseOrderId").val(),
			'purchaseOrder.code' : $("#code").val(),
			'purchaseOrder.name' : $("#name").val(),
			'purchaseOrder.channelDistributor.id' : $("#channelDistributorId").val(),
			'salesChannelDistributorId' : $("#salesChannelDistributorId").val(),
			'treeType' : $("#treeType").val(),
			'purchaseOrder.creator' : $("#creator").val(),
			'updateField' : updateField,
			'purchaseOrder.createTime' : $("#createTime").val()
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/drp/demandOrderAction!goList.action');
			});
		}
	}
	$("#purchaseOrderForm").validationEngine();
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
	}
	function chooseSalesChannelDistributor() {
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
							$("#salesChannelDistributorId").val(result[0]);
							$("#salesChannelDistributorName").val(result[1]);
							$("#treeType").val(result[2]);
						}
					} else {
						$("#salesChannelDistributorId").val("");
						$("#salesChannelDistributorName").val("");
						$("#treeType").val("");
						asyncbox.success("请选择公司信息!", "提示信息");
					}
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	}
	function initdata() {
		//加载创建时间(新增)
		if (document.getElementById("createTime").value == "") {
			var myDate = new Date();
			$("#createTime").val(myDate.getFullYear() + "-" + (myDate.getMonth() + 1) + "-" + myDate.getDate() + " " + myDate.getHours() + ":" + myDate.getMinutes() + ":" + myDate.getSeconds());
		}
	};
	initdata();
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /> </a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /> </a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/pur_plan.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#">分销管理 </a></li>
				<li><a href="#">经销商管理</a></li>
				<li><a href="#">要货单</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="purchaseOrderId" name="purchaseOrderId" value="${purchaseOrder.id}" />
<input type="hidden" id="selectId" name="selectId" value="${parentId}" />
<div class="content">
	<form id="purchaseOrderForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdate()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"> </a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /> </a> <a href="#"
						onclick="loadContent('${vix}/drp/demandOrderAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong><b>要货单 </b> </strong>
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
											<td><input id="code" name="code" value="${purchaseOrder.code }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /> <span style="color: red;">*</span></td>
											<th>主题：</th>
											<td><input id="name" name="name" value="${purchaseOrder.name }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>要货方：</th>
											<td><input id="channelDistributorName" name="channelDistributorName" value="${purchaseOrder.channelDistributor.name }" type="text" size="30" onchange="fieldChanged(this);" /><input type="hidden" id="channelDistributorId" name="channelDistributorId" value="${purchaseOrder.channelDistributor.id}" /> <input class="btn" type="button"
												value="选择" onclick="chooseParentOrganization();" /></td>
											<th>供货方：</th>
											<td><input id="salesChannelDistributorName" name="salesChannelDistributorName" value="${purchaseOrder.salesChannelDistributor.name }" type="text" size="30" /> <input type="hidden" id="salesChannelDistributorId" name="salesChannelDistributorId" value="${purchaseOrder.salesChannelDistributor.id}" /> <input type="hidden"
												id="treeType" name="treeType" /> <input class="btn" type="button" value="选择" onclick="chooseSalesChannelDistributor();" /></td>
										</tr>
										<tr>
											<th>创建人：</th>
											<td><input id="creator" name="creator" value="${purchaseOrder.creator }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
											<th>要货日期：</th>
											<td><input id="createTime" type="text" size="25" onchange="fieldChanged(this);" value="<s:date name="%{purchaseOrder.createTime}" format="yyyy-MM-dd HH:mm:ss"/>" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" /></td>
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
								url : '${vix}/drp/demandOrderAction!goPurchaseOrderLineItem.action?id=' + id + "&purchaseOrderId=" + $("#purchaseOrderId").val() + "&parentId=" + $("#selectId").val(),
								cache : false,
								success : function(html) {
									asyncbox.open({
									modal : true,
									width : 900,
									height : 525,
									title : "商品",
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
							url : '${vix}/drp/demandOrderAction!getPurchaseOrderLineItemJson.action?id=${purchaseOrder.id}',
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
							hidden : true,
							align : 'center'
							}, {
							field : 'itemCode',
							title : '商品编码',
							width : 150,
							align : 'center'
							}, {
							field : 'itemName',
							title : '商品名称',
							width : 250,
							align : 'center'
							} ] ],
							columns : [ [ {
							field : 'unit',
							title : '单位',
							width : 150,
							align : 'center'
							}, {
							field : 'amount',
							title : '数量',
							width : 150,
							align : 'center',
							editor : 'numberbox',
							required : 'true'
							}, {
							field : 'price',
							title : '价格',
							width : 150,
							align : 'center',
							editor : 'numberbox',
							required : 'true'
							}, {
							field : 'netTotal',
							title : '金额',
							width : 150,
							align : 'center',
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
									url : '${vix}/drp/demandOrderAction!deletePurchaseOrderLineItemById.action?id=' + rows[i].id,
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
