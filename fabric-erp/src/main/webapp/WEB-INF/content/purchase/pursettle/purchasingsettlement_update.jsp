<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">
	function chooseProduct() {
		$.ajax({
		url : '${vix}/template/productAction!goChooseProduct.action?tag=choose',
		cache : false,
		success : function(html) {
			asyncbox.open({
			modal : true,
			width : 1000,
			height : 520,
			title : "选择商品",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					$.ajax({
					url : '${vix}/template/orderAction!saveOrUpdateOrderItem.action?id=' + $("#id").val() + "&productIds=" + returnValue,
					cache : false,
					success : function(result) {
						asyncbox.success(result, "提示信息", function(action) {
							pager("start", "${vix}/template/orderAction!goOrderItemSingleList.action?id=" + $("#id").val(), 'orderUpdate');
						});
					}
					});
				}
			},
			btnsbar : $.btn.OKCANCEL
			});
		}
		});
	}
	pager("start", "${vix}/template/orderAction!goOrderItemSingleList.action?rows=6&id=" + $("#id").val(), 'orderUpdate');
	function currentPager(tag) {
		pager(tag, "${vix}/template/orderAction!goOrderItemSingleList.action?rows=6&id=" + $("#id").val(), 'orderUpdate');
	}
	/** 保存采购计划 */
	function saveOrUpdatePlan() {
		var orderItemStr = "";
		/** 明细 */
		var dlData = $("#dlLineItem").datagrid("getRows");
		var piJson = JSON.stringify(dlData);
		if ($('#purchasePlanForm').validationEngine('validate')) {
			$.post('${vix}/purchase/purchasePlanAction!saveOrUpdate.action', {
			'purchasePlan.id' : $("#id").val(),
			'purchasePlan.purchasePlanCode' : $("#purchasePlanCode").val(),
			'purchasePlan.purchasePlanName' : $("#purchasePlanName").val(),
			'purchasePlan.style' : $("#bizStyle").val(),
			'purchasePlan.amount' : $("#amount").val(),
			'purchasePlan.supplierName' : $("#supplierName").val(),
			'purchasePlan.createTime' : $("#createTime").val(),
			'purchasePlan.executeDepartment' : $("#executeDepartment").val(),
			'purchasePlan.executePerson' : $("#executePerson").val(),
			'purchasePlan.description' : $("#description").val(),
			'orderItemStr' : orderItemStr,
			'piJson' : piJson
			}, function(result) {
				showMessage(result);
				setTimeout("", 1000);
				loadContent('${vix}/purchase/purchasePlanAction!goList.action?menuId=menuOrder');
			});
		}
	}

	$("#purchasePlanForm").validationEngine();
	/** 选择单选供应商 */
	function chooseRadioSupplier() {
		$.ajax({
		url : '${vix}/purchase/purchasePlanAction!goChooseRadioSupplier.action',
		cache : false,
		success : function(html) {
			$(".ab_outer .list td input[type='checkbox']").css("margin-left", 10);
			$(".ab_c .content").css("margin-bottom", "0");
			$('.ab_c .content').parent().css('position', 'relative');
			asyncbox.open({
			modal : true,
			width : 1000,
			height : 500,
			title : "选择供应商",
			html : html,
			callback : function(action, returnValue) {
				if (action == 'ok') {
					if (returnValue != undefined) {
						var rv = returnValue.split(",");
						$("#supplierCode").val(rv[0]);
						$("#supplierName").val(rv[1]);
					} else {
						$("#supplierName").val(rv[1]);
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
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/pur_settlement.png" alt="" /> <s:text name="cmn_supplyChain" /></a></li>
				<li><a href="#"><s:text name="pur_purchase_manage" /></a></li>
				<li><a href="#" onclick="loadContent('${vix}/purchase/purchasingSettlementAction!goList.action');"><s:text name='pur_purchase_settlement' /></a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${purchasePlan.id }" />
<div class="content">
	<form id="purchasePlanForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdatePlan();" href="#"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"><img width="22" height="22" title="保存并提交审批"
							src="${vix}/common/img/dt_submit.png" /></a> <a href="#" onclick="loadContent('${vix}/purchase/purchasingSettlementAction!goList.action');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong> <b>新增采购结算</b> <i></i>
					</strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b> <span style="display: none;"> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" /> <s:text name="calculator" /></a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" /> <s:text name="calculator" /></a> <a href="#"><img
											src="${vix}/common/img/wrench_screwdriver.png" alt="" /> <s:text name="calculator" /></a>
									</span> <strong>基本信息</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<td align="right">结算号：</td>
											<td><input type="text" size="30" /></td>
											<td align="right">主题：</td>
											<td><input type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">业务类型：</td>
											<td><select id="bizStyle" name="style" style="width: 50">
													<option value="">请选择</option>
													<option value="1">类型1</option>
													<option value="2">类型2</option>
											</select></td>
											<td align="right">采购类型：</td>
											<td><select id="bizStyle" name="style" style="width: 50">
													<option value="">请选择</option>
													<option value="1">类型1</option>
													<option value="2">类型2</option>
											</select></td>
										</tr>
										<tr>
											<td align="right">供应商名称：</td>
											<td><input id="supplierName" name="supplierName" value="${purchasePlan.supplierName }" type="text" size="30" /> <input type="hidden" id="supplierCode" name="supplierCode" value="${purchasePlan.supplierCode }" /> <input class="btn" type="button" value="选择" onclick="chooseRadioSupplier();" /></td>
											<td align="right">结算日期：</td>
											<td><input id="createTime" name="createTime" value="<fmt:formatDate value='${purchasePlan.createTime }' type='both' pattern='yyyy-MM-dd' />" type="text" /> <img onclick="showTime('createTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<td align="right">部门名称：</td>
											<td><input id="executeDepartment" name="executeDepartment" value="${purchasePlan.executeDepartment }" type="text" size="30" /></td>
											<td align="right">职员：</td>
											<td><input id="executePerson" name="executePerson" value="${purchasePlan.executePerson }" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">备注：</td>
											<td colspan="3"><textarea id="description" name="description" class="example" rows="1" style="width: 600px">${purchasePlan.description }</textarea></td>
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
							<li><a onclick="javascript:$('#a2').attr('style','');tab(6,2,'a',event);"><img src="${vix}/common/img/attachment.png" alt="" />附件</a></li>
						</ul>
					</div>
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<div style="padding: 8px;">
							<table id="dlLineItem" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="fitColumns: true,rownumbers:true,pagination: true,iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlLineItemTb',url: '${vix}/purchase/purchasePlanAction!getPurchasePlanItemsJson.action?id=${purchasePlan.id}',onClickRow: onDlClickRow1">
								<thead>
									<tr>
										<th data-options="field:'id',align:'center',width:100">入库单号</th>
										<th data-options="field:'itemCode',align:'center',width:100,editor:'text'">发票号</th>
										<th data-options="field:'itemName',width:100,align:'center',editor:'text'">${vv:varView("vix_mdm_item")}编码</th>
										<th data-options="field:'specification',width:100,align:'center',editor:'text'">${vv:varView("vix_mdm_item")}名称</th>
										<th data-options="field:'itemType',width:100,align:'center',editor:'text'">结算数量</th>
										<th data-options="field:'amount',width:100,align:'center',editor:'numberbox'">结算辅计量数量</th>
										<th data-options="field:'unit',width:100,align:'center',editor:'text'">结算单价</th>
										<th data-options="field:'price',width:100,align:'center',editor:'numberbox'">结算金额</th>
										<th data-options="field:'total',width:100,align:'center',editor:'numberbox'">费用金额</th>
										<th data-options="field:'receivingWarehouse',width:100,align:'center',editor:'text'">暂估单价</th>
										<th data-options="field:'receivingWarehouse2',width:100,align:'center',editor:'text'">暂估金额</th>
										<th data-options="field:'receivingWarehouse3',width:100,align:'center',editor:'text'">税额</th>
										<th data-options="field:'receivingWarehouse4',width:100,align:'center',editor:'text'">税合计</th>
									</tr>
								</thead>
							</table>
							<div id="dlLineItemTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="appendDlLineItem()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
									data-options="iconCls:'icon-remove',plain:true" onclick="removeDlLineItem()"><span class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-save',plain:true"
									onclick="saveDlLineItem()"><span class="l-btn-left"><span class="l-btn-text icon-save l-btn-icon-left"><s:text name="cmn_save" /></span></span></a>
							</div>
							<script type="text/javascript">
								$('#dlLineItem').datagrid();
								DataGrid1.ScrollBars = VbHorizontal
								var editIndexDlLineItem = undefined;
								function endDlEditing1() {
									if (editIndexDlLineItem == undefined) {
										return true;
									}
									if ($('#dlLineItem').datagrid('validateRow', editIndexDlLineItem)) {
										$('#dlLineItem').datagrid('endEdit', editIndexDlLineItem);
										editIndexDlLineItem = undefined;
										return true;
									} else {
										return false;
									}
								}
								function onDlClickRow1(index) {
									if (editIndexDlLineItem != index) {
										if (endDlEditing1()) {
											$('#dlLineItem').datagrid('selectRow', index).datagrid('beginEdit', index);
											editIndexDlLineItem = index;
										} else {
											$('#dlLineItem').datagrid('selectRow', editIndexDlLineItem);
										}
									}
								}
								function appendDlLineItem() {
									if (endDlEditing1()) {
										$('#dlLineItem').datagrid('appendRow', {
											status : 'P'
										});
										editIndexDlLineItem = $('#dlLineItem').datagrid('getRows').length - 1;
										$('#dlLineItem').datagrid('selectRow', editIndexDlLineItem).datagrid('beginEdit', editIndexDlLineItem);
									}
								}
								function removeDlLineItem() {
									if (editIndexDlLineItem == undefined) {
										return;
									}
									$('#dlLineItem').datagrid('cancelEdit', editIndexDlLineItem).datagrid('deleteRow', editIndexDlLineItem);
									editIndexDlLineItem = undefined;
								}
								function saveDlLineItem() {
									if (endDlEditing1()) {
										$('#dlLineItem').datagrid('acceptChanges');
									}
								}
							</script>
						</div>
					</div>
					<div id="a2" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<script type="text/javascript">
							$('#soAttach').datagrid({
							url : '${vix}/purchase/purchasePlanAction!getAttachmentsJson.action?id=${purchasePlan.id}',
							title : '订单附件',
							width : 900,
							height : '450',
							fitColumns : true,
							columns : [ [ {
							field : 'id',
							title : '编号',
							width : 80
							}, {
							field : 'name',
							title : '名称',
							width : 180
							}, ] ],
							toolbar : [ {
							id : 'saBtnadd',
							text : '添加',
							iconCls : 'icon-add',
							handler : function() {
								$('#btnsave').linkbutton('enable');
								$.ajax({
								url : '${vix}/purchase/purchasePlanAction!addAttachments.action',
								cache : false,
								success : function(html) {
									asyncbox.open({
									modal : true,
									width : 364,
									height : 160,
									title : "上传附件",
									html : html,
									callback : function(action, returnValue) {
										if (action == 'ok') {
											uploadFile('${vix}/purchase/purchasePlanAction!uploadAttachments.action?id=${purchasePlan.id}', 'fileToUpload');
											$('#soAttach').datagrid("reload");
										}
									},
									btnsbar : $.btn.OKCANCEL
									});
								}
								});
							}
							}, '-', {
							text : '删除',
							iconCls : 'icon-remove',
							handler : function() {
								var rows = $('#soAttach').datagrid("getSelections"); //获取你选择的所有行	
								//循环所选的行
								for (var i = 0; i < rows.length; i++) {
									var index = $('#soAttach').datagrid('getRowIndex', rows[i]);//获取某行的行号
									$('#soAttach').datagrid('deleteRow', index); //通过行号移除该行
									$.ajax({
									url : '${vix}/purchase/purchasePlanAction!deleteAttachments.action?afId=' + rows[i].id,
									cache : false
									});
								}
							}
							} ]
							});
						</script>
						<div style="padding: 8px;">
							<table id="soAttach"></table>
						</div>
					</div>
				</div>
			</dl>
		</div>
	</form>
</div>
