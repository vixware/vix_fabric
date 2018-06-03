<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	//js定义
	var updateField = "";
	function fieldChanged(input) {
		updateField += $(input).attr("id") + ",";
	}
	function saveOrUpdateOrder() {
		var saleOrderCostItemData = $("#saleOrderCostItemtable").datagrid("getRows");
		var saleOrderCostItemJson = JSON.stringify(saleOrderCostItemData);
		if ($('#order').validationEngine('validate')) {
			$.post('${vix}/dtbcenter/distributionCostAction!saveOrUpdate.action', {
			'saleOrderCost.id' : $("#id").val(),
			'saleOrderCost.saleOrderCode' : $("#saleOrderCode").val(),
			'saleOrderCost.directCost' : $("#directCost").val(),
			'saleOrderCost.indirectCost' : $("#indirectCost").val(),
			'saleOrderCost.total' : $("#total").val(),
			'saleOrderCost.unit' : $("#unit").val(),
			'updateField' : updateField,
			'saleOrderCostItemJson' : saleOrderCostItemJson
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/dtbcenter/distributionCostAction!goList.action');
			});
		}
	}
	$("#order").validationEngine();
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
							title : "选择费用项目",
							html : html,
							callback : function(action, returnValue) {
								if (action == 'ok') {
									if (returnValue != '') {
										$
												.ajax({
												url : '${vix}/dtbcenter/distributionCostAction!converterCostItemToSaleOrderCostItem.action?costItemid=' + returnValue + "&id=${saleOrderCost.id}",
												cache : false,
												success : function(result) {
													showMessage(result);
													setTimeout("", 1000);
													$('#saleOrderCostItemtable').datagrid("reload");
												}
												});
									} else {
										asyncbox.success("请选择费用项目!", "<s:text name='vix_message'/>");
										return false;
									}
								}
							},
							btnsbar : $.btn.OKCANCEL
							});
				}
				});
	}
	/** 选择订单 */
	function chooseRadioSupplier() {
		$.ajax({
		url : '${vix}/dtbcenter/distributionCostAction!goChooseSalesOrder.action',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 1000,
			height : 500,
			title : "选择订单",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						var rv = returnValue.split(",");
						$("#saleOrderCode").val(rv[0]);
					} else {
						asyncbox.success("请选择分类信息!", "提示信息");
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
				<li><a href="#"><img src="${vix}/common/img/drp_channel.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#"><s:text name="ldm_dtbcenter_management" /> </a></li>
				<li><a href="#">经营管理 </a></li>
				<li><a href="#" onclick="loadContent('${vix}/dtbcenter/distributionCostAction!goList.action');">配送成本分析</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="saleOrderCost.id" value="${saleOrderCost.id}" />
<div class="content">
	<form id="order">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateOrder()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"> </a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /> </a> <a href="#"><img width="22" height="22" title="保存并提交审批"
							src="${vix}/common/img/dt_submit.png" /> </a> <a href="#"><img width="22" height="22" title="取消" src="${vix}/common/img/dt_cancelback.png" /> </a> <a href="#" onclick="loadContent('${vix}/dtbcenter/distributionCostAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong><b>配送成本分析 </b> </strong>
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
											<th>销售订单编号：</th>
											<td><input id="saleOrderCode" name="saleOrderCode" value="${saleOrderCost.saleOrderCode }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <input class="btn" type="button" value="选择" onclick="chooseRadioSupplier();" /><span style="color: red;">*</span></td>
											<th>直接成本：</th>
											<td><input id="directCost" name="directCost" value="${saleOrderCost.directCost}" class="validate[required] text-input" type="text" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<th>间接成本：</th>
											<td><input id="indirectCost" name="indirectCost" value="${saleOrderCost.indirectCost}" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
											<th>费用合计：</th>
											<td><input id="total" name="total" value="${saleOrderCost.total }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
										</tr>
										<tr>
											<th>单位：</th>
											<td><input id="unit" name="unit" value="${saleOrderCost.unit }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);"> <span style="color: red;">*</span></td>
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
							<li class="current"><a onclick="javascript:tab(4,1,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />费用明细</a></li>
						</ul>
					</div>
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<script type="text/javascript">
							$('#saleOrderCostItemtable').datagrid({
							url : '${vix}/dtbcenter/distributionCostAction!getSaleOrderCostItemJson.action?id=${saleOrderCost.id}',
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
							field : 'costCode',
							title : '费用代码',
							width : 100,
							align : 'center'
							}, {
							field : 'costName',
							title : '费用名称',
							width : 100,
							align : 'center'
							}, {
							field : 'groupCode',
							title : '组别代码',
							width : 100,
							align : 'right'
							}, {
							field : 'costType',
							title : '类型',
							width : 100,
							align : 'right'
							}, {
							field : 'payments',
							title : '费用额',
							width : 100,
							editor : 'numberbox',
							align : 'right'
							}, {
							field : 'unit',
							title : '单位',
							width : 100,
							align : 'center'
							} ] ],
							toolbar : [ {
							id : 'da2Btnadd',
							text : '添加',
							iconCls : 'icon-add',
							handler : function() {
								$('#btnsave').linkbutton('enable');
								saveDeliveryAddress('${vix}/dtbcenter/distributionCostAction!goChooseCostItem.action');
							}
							}, '-', {
							text : '修改',
							iconCls : 'icon-edit',
							handler : function() {
								if (editIndexDlAddress != index) {
									if (endDlEditing()) {
										$('#dlAddress').datagrid('selectRow', index).datagrid('beginEdit', index);
										editIndexDlAddress = index;
									} else {
										$('#dlAddress').datagrid('selectRow', editIndexDlAddress);
									}
								}
							}
							}, '-', {
							text : '保存',
							iconCls : 'icon-save',
							handler : function() {
								if (endDlEditing()) {
									$('#dlAddress').datagrid('acceptChanges');
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
							<table id="saleOrderCostItemtable"></table>
						</div>
					</div>
				</div>
			</dl>
		</div>
		<!--submenu-->
	</form>
</div>