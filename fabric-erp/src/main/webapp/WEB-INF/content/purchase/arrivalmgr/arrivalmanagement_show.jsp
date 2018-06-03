<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
	$(function() {
		//设置单据类型选中(修改)
		$("#orderType").val('${purchaseArrival.orderType}');
		//设置业务类型选中(修改)
		$("#businessType").val('${purchaseArrival.businessType}');
		//设置币种选中(修改)
		$("#currency").val('${purchaseArrival.currency}');

		$("#purchaseArrivalForm").validationEngine();

		_pad_addInputCheckNumEvent('taxRate');
	});

	function goShowBeforeAndAfterPurchaseArrival(id, str) {
		$.ajax({
		url : '${vix}/purchase/purchaseArrivalAction!goShowBeforeAndAfterPurchaseArrival.action?id=' + id + "&str=" + str,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
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
				<li><a href="#"><img src="${vix}/common/img/inv_inbound.png" alt="" /> <s:text name="supplyChain" /> </a></li>
				<li><a href="#">采购管理 </a></li>
				<li><a href="#" onclick="">到货管理 </a></li>
			</ul>
		</div>
	</h2>
</div>

<div class="content">
	<form id="purchaseArrivalForm">
		<input type="hidden" id="id" name="id" value="${purchaseArrival.id }" /> <input type="hidden" id="groupCode" name="groupCode" value="${purchaseArrival.groupCode }" />
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a href="#"><img width="22" height="22" title="禁用" src="${vix}/common/img/dt_disable.png" /> </a> <a href="#"><img width="22" height="22" title="锁定" src="${vix}/common/img/dt_locked.png" /> </a><a href="#"><img width="22" height="22" title="删除" src="${vix}/common/img/dt_delete.png" /> </a> <a href="#"><img
							width="22" height="22" title="审批通过" src="${vix}/common/img/dt_aprroval.png" /> </a> <a href="#"><img width="22" height="22" title="驳回" src="${vix}/common/img/dt_reject.png"> </a> <a href="#" onclick="goShowBeforeAndAfterPurchaseArrival('${purchaseArrival.id}','before');"><img width="22" height="22" title="上一条"
							src="${vix}/common/img/dt_before.png"> </a> <a href="#" onclick="goShowBeforeAndAfterPurchaseArrival('${purchaseArrival.id}','after');"><img width="22" height="22" title="下一条" src="${vix}/common/img/dt_next.png"> </a> <a href="#" onclick="goPrintPurchaseArrival('${purchaseArrival.id }');"><img width="22" height="22" title="打印"
							src="${vix}/common/img/dt_print.png"> </a>
						<div class="ms" style="float: none; display: inline;">
							<a href="#"><img width="22" height="22" alt="关联单据" src="${vix}/common/img/dt_report.png"> </a>
							<ul style="display: none; overflow: hidden; height: 124px; margin-top: 0px; margin-bottom: 0px; padding-top: 0px; padding-bottom: 0px;">
								<li><a href="#" onclick=""><img src="${vix}/common/img/icon_10.png" alt="">引用单据</a></li>
							</ul>
						</div> <a href="#"><img width="22" height="22" title="导出" src="${vix}/common/img/dt_export.png"> </a> <a href="#" onclick="loadContent('${vix}/purchase/purchaseArrivalAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a></span> <strong><b>到货管理 </b><i></i> </strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b> <span style="display: none;"> </span> <strong>基本信息</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<td align="right">编码：</td>
											<td><input id="code" name="code" value="${purchaseArrival.code }" type="text" size="30" /></td>
											<td align="right">主题：</td>
											<td><input id="name" name="name" value="${purchaseArrival.name }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">业务类型：</td>
											<td><select id="businessType" name="businessType" style="width: 50">
													<option value="">请选择</option>
													<s:iterator value="listPurchaseArrivalBizType()" var="entity">
														<option value="${entity.code}">${entity.name}</option>
													</s:iterator>
											</select></td>
											<td align="right">单据类型：</td>
											<td><select id="orderType" name="orderType" style="width: 50">
													<option value="PUR_ARRIVAL">采购到货单</option>
											</select></td>
										</tr>
										<tr>
											<td align="right">项目：</td>
											<td><input id="project" name="project" value="${purchaseArrival.project }" type="text" size="30" class="text-input" /> <input class="btn" type="button" value="选择" onclick="chooseProject();" /></td>
											<td align="right">需求部门：</td>
											<td><input id="requireDepartment" name="requireDepartment" value="${purchaseArrival.requireDepartment }" type="text" size="30" /> <input class="btn" type="button" value="选择" onclick="chooseRequireDepartment();" /></td>
										</tr>
										<tr>
											<td align="right">采购组织：</td>
											<td><input id="purchaseOrgId" name="purchaseOrgId" value="${purchaseArrival.purchaseOrgId }" type="hidden" /> <input id="purchaseOrg" name="purchaseOrg" value="${purchaseArrival.purchaseOrg }" type="text" size="30" /> <input class="btn" type="button" value="选择" onclick="chooseParentCategory();" /></td>
											<td align="right">供应商名称：</td>
											<td><input id="supplierName" name="supplierName" value="${purchaseArrival.supplierName }" type="text" size="30" class="validate[required] text-input" /> <span style="color: red;">*</span> <input type="hidden" id="supplierCode" name="supplierCode" value="${purchaseArrival.supplierCode }" /> <input type="hidden" id="supplierId"
												name="supplierId" value="${purchaseArrival.supplierId }" /> <input class="btn" type="button" value="选择" onclick="chooseRadioSupplier();" /></td>
										</tr>
										<tr>
											<td align="right">采购人：</td>
											<td><input id="purchasePerson" name="purchasePerson" value="${purchaseArrival.purchasePerson }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">联系人：</td>
											<td><input id="contactPerson" name="contactPerson" value="${purchaseArrival.contactPerson }" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">币种：</td>
											<td><select id="currency" name="currency" style="width: 50">
													<option value="">请选择</option>
													<c:forEach var="cy" items="${currencyTypeList }">
														<option value="${cy.code }">${cy.name }</option>
													</c:forEach>
											</select></td>
											<td align="right">税率：</td>
											<td><input id="taxRate" name="taxRate" value="${purchaseArrival.taxRate }" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">过账日期：</td>
											<td><input id="postingDate" readonly="readonly" name="postingDate" value="<s:date name="purchaseArrival.postingDate" format="yyyy-MM-dd"/>" type="text" onclick="showTime('postingDate','yyyy-MM-dd')" /> <img onclick="showTime('postingDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
											<td align="right">交货日期：</td>
											<td><input id="deliveryDate" readonly="readonly" name="deliveryDate" value="<s:date name="purchaseArrival.deliveryDate" format="yyyy-MM-dd"/>" type="text" class="validate[required] text-input" onclick="showTime('deliveryDate','yyyy-MM-dd')" /><span style="color: red;">*</span> <img onclick="showTime('deliveryDate','yyyy-MM-dd')"
												src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<td align="right">备注：</td>
											<td colspan="3"><textarea id="memo" name="memo" class="example" rows="2" style="width: 650px">${purchaseArrival.memo }</textarea></td>
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
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<div style="padding: 8px;">
							<table id="dlLineItem" class="easyui-datagrid" style="height: 300px; margin: 6px;" data-options="rownumbers:true,pagination: true,iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlLineItemTb',url: '${vix}/purchase/purchaseArrivalAction!getPurchaseArrivalItemsJson.action'">
								<thead>
									<tr>
										<th data-options="field:'itemCode',align:'center',width:120,editor:'text'">编码</th>
										<th data-options="field:'itemName',width:200,align:'center',editor:'text'">名称</th>
										<th data-options="field:'skuCode',align:'center',width:120,editor:'text'">SKU码</th>
										<th data-options="field:'barCode',align:'center',width:120,editor:'text'">BAR码</th>
										<th data-options="field:'specification',width:120,align:'center',editor:'text'">规格型号</th>
										<th data-options="field:'amount',width:120,align:'center',editor:'numberbox'">订货数量</th>
										<th data-options="field:'unit',width:120,align:'center',editor:'numberbox'">计量单位</th>
										<th data-options="field:'price',width:100,align:'center',editor:'numberbox'">单价</th>
										<th data-options="field:'total',width:100,align:'center',editor:'numberbox'">含税价</th>
										<th data-options="field:'taxRate',width:120,align:'center',editor:'numberbox'">税率</th>
										<th data-options="field:'recieveWareHouse',width:120,align:'center',editor:'text'">收货仓库</th>
									</tr>
								</thead>
							</table>
							<div id="dlLineItemTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="addItem1(0)"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
									data-options="iconCls:'icon-edit',plain:true" onclick="editItem1()"><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left">修改</span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-remove',plain:true" onclick="removeItem1()"><span class="l-btn-left"><span
										class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a>
							</div>
							<script type="text/javascript">
								function reloadDataGrid1() {
									$('#dlLineItem').datagrid({
										queryParams : {
											id : $('#id').val()
										}
									});
									//修改下方数量
									$.ajax({
									url : '${vix}/purchase/purchaseArrivalAction!getPurchaseArrivalItemsCount.action?id=' + $("#id").val(),
									cache : false,
									type : 'json',
									success : function(json) {
										if (json) {
											$("#detail_total_fee").val(json.totalFee);
											$("#detail_total_fee_tax").val(json.totalFeeTax);
											$("#detail_total_tax").val(json.totalTax);
										}
									}
									});
								}
								reloadDataGrid1();

								function addItem1(id) {
									if (!id)
										id = 0;
									if (!_pad_check_entity_saved_id(saveOrUpdateOrder, addItem1))
										return;

									$.ajax({
									url : '${vix}/purchase/purchaseArrivalAction!goAddPurchaseArrivalItems.action',
									data : 'supplierId=' + $("#supplierId").val() + '&id=' + id,
									cache : false,
									type : 'post',
									success : function(html) {
										asyncbox.open({
										modal : true,
										width : 1000,
										height : 580,
										title : "明细",
										html : html,
										callback : function(action) {
											if (action == 'cancel' || action == 'close') {
												reloadDataGrid1();
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
								function editItem1() {
									var rows = $('#dlLineItem').datagrid('getSelected');
									if (null == rows) {
										alert("请选择一条数据！");
										return;
									}
									addItem1(rows.id);
								}

								function removeItem1() {
									var row = $('#dlLineItem').datagrid('getSelected');
									if (row) {
										asyncbox.confirm('是否删除该订单明细?', '提示信息', function(action) {
											if (action == 'ok') {
												$.ajax({
												url : '${vix}/purchase/purchaseArrivalAction!deletePurchaseOrderItem.action?id=' + row.id,
												cache : false,
												success : function(html) {
													if (html == 'success') {
														showMessage('数据已删除');
														reloadDataGrid1();
													}
												}
												});
											}
										});
									} else {
										showMessage("请选择一条数据!");
									}
								}
							</script>
						</div>
					</div>
				</div>

				<dd class="clearfix">
					<div class="order_table">
						<table class="addtable_c">
							<tr>
								<td align="right">${vv:varView("vix_mdm_item")}总计:</td>
								<td><input id="detail_total_fee" type="text" size="30" /></td>
								<td align="right">税额:</td>
								<td><input id="detail_total_tax" type="text" size="30" /></td>
								<td align="right">含税总计:</td>
								<td><input id="detail_total_fee_tax" type="text" size="30" /></td>
							</tr>
						</table>
					</div>
				</dd>
			</dl>
		</div>
		<!--oder-->
		<div class="sub_menu sub_menu_bot" style="display: none;">
			<div class="drop">
				<p>
					<a href="#" onclick="saveOrUpdateOrder()"><span>保存</span></a> <a href="#" onclick="loadContent('${vix}/template/orderAction!goList.action');"><span>返回</span></a> <a href="#"><span>编辑</span></a> <a href="#"><span>复制</span></a> <a href="#"><span>删除</span></a> <a href="#"><span>关闭并新建</span></a> <a href="#"><span>关闭</span></a> <a href="#"
						id="text"><span>弹出窗口测试</span></a>
				</p>
				<ul>
					<li><a href="#"><span>帮助</span></a>
						<ul>
							<li><a href="#">帮助</a></li>
							<li><a href="#">帮助</a></li>
							<li><a href="#">帮助</a></li>
							<li><a href="#">帮助</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
		<!--submenu-->
	</form>
</div>
<!-- content -->