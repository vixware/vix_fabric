<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	if ($(".ms").length > 0) {
		$(".ms").hover(function() {
			$(">ul", this).stop().slideDown(100);
		}, function() {
			$(">ul", this).stop().slideUp(100);
		});
		$(".ms ul li").hover(function() {
			$(">a", this).addClass("hover");
			$(">ul", this).stop().slideDown(100);
		}, function() {
			$(">a", this).removeClass("hover");
			$(">ul", this).stop().slideUp(100);
		});
	}
	function goShowBeforeAndAfterPurchaseInquire(id, str) {
		$.ajax({
		url : '${vix}/purchase/purchaseInquireAction!goShowBeforeAndAfterPurchaseInquire.action?id=' + id + "&str=" + str,
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
				<li><a href="#" onclick="">采购询价 </a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${purchaseInquire.id }" />
<input type="hidden" id="groupCode" name="groupCode" value="${purchaseInquire.groupCode }" />
<div class="content">
	<form id="stockRecordsForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a href="#"><img width="22" height="22" title="禁用" src="${vix}/common/img/dt_disable.png" /> </a> <a href="#"><img width="22" height="22" title="锁定" src="${vix}/common/img/dt_locked.png" /> </a><a href="#"><img width="22" height="22" title="删除" src="${vix}/common/img/dt_delete.png" /> </a> <a href="#"><img
							width="22" height="22" title="审批通过" src="${vix}/common/img/dt_aprroval.png" /> </a> <a href="#"><img width="22" height="22" title="驳回" src="${vix}/common/img/dt_reject.png"> </a> <a href="#" onclick="goShowBeforeAndAfterPurchaseInquire('${purchaseInquire.id}','before');"><img width="22" height="22" title="上一条"
							src="${vix}/common/img/dt_before.png"> </a> <a href="#" onclick="goShowBeforeAndAfterPurchaseInquire('${purchaseInquire.id}','after');"><img width="22" height="22" title="下一条" src="${vix}/common/img/dt_next.png"> </a> <a href="#" onclick="goPrintPurchaseInquire('${purchaseInquire.id }');"><img width="22" height="22" title="打印"
							src="${vix}/common/img/dt_print.png"> </a>
						<div class="ms" style="float: none; display: inline;">
							<a href="#"><img width="22" height="22" alt="关联单据" src="${vix}/common/img/dt_report.png"> </a>
							<ul style="display: none; overflow: hidden; height: 124px; margin-top: 0px; margin-bottom: 0px; padding-top: 0px; padding-bottom: 0px;">
								<li><a href="#" onclick=""><img src="${vix}/common/img/icon_10.png" alt="">引用单据</a></li>
							</ul>
						</div> <a href="#"><img width="22" height="22" title="导出" src="${vix}/common/img/dt_export.png"> </a> <a href="#" onclick="loadContent('${vix}/purchase/purchaseInquireAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a></span> <strong><b>采购询价 </b><i></i> </strong>
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
											<td align="right">询价单编码：</td>
											<td><input id="code" name="code" type="text" size="30" value="${purchaseInquire.code }" /></td>
											<td align="right">询价单主题：</td>
											<td><input id="inquireName" name="inquireName" type="text" size="30" value="${purchaseInquire.name }" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">项目：</td>
											<td><input id="project" name="project" value="${purchaseInquire.project }" type="text" size="30" /> &nbsp;&nbsp;&nbsp; <input class="btn" type="button" value="选择" onclick="chooseRadioProject();" /></td>
											<td align="right">业务类型：</td>
											<td><select id="bizType" name="bizType" style="width: 50">
													<option value="">请选择</option>
													<s:iterator value="listPurchaseInquireBizType()" var="entity">
														<option value="${entity.code}">${entity.name}</option>
													</s:iterator>
											</select></td>
										</tr>
										<tr>
											<td align="right">单据类型：</td>
											<td><select id="formType" name="formType" style="width: 50" class="validate[required] text-input">
													<option value="PUR_INQUERY">采购询价</option>
											</select><span style="color: red;">*</span></td>
											<td align="right">供应商名称：</td>
											<td><input id="supplierName" name="supplierName" value="${purchaseOrder.supplierName }" type="text" size="30" class="validate[required] text-input" /> <span style="color: red;">*</span> <input type="hidden" id="supplierCode" name="supplierCode" value="${purchaseInquire.supplierCode }" /> <input type="hidden" id="supplierId"
												name="supplierId" value="${purchaseInquire.supplierId }" /> <input class="btn" type="button" value="选择" onclick="chooseRadioSupplier();" /> <input class="btn" type="button" value="快速新建" onclick="createSupplier();" /></td>
										</tr>
										<tr>
											<td align="right">币种：</td>
											<td><select id="currency" name="currency">
													<option value="">请选择</option>
													<c:forEach var="cy" items="${currencyTypeList }">
														<option value="${cy.code }">${cy.name }</option>
													</c:forEach>
											</select></td>
											<td align="right">税率：</td>
											<td><input id="rate" name="rate" value="${purchaseInquire.rate }" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">采购人：</td>
											<td><input id="purchasePerson" name="purchasePerson" value="${purchaseInquire.purchasePerson }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">询价日期：</td>
											<td><input id="appDate" name="appDate" value="<s:date name="purchaseInquire.appDate" format="yyyy-MM-dd"/>" type="text" class="validate[required] text-input" /> <img onclick="showTime('appDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"><span style="color: red;">*</span></td>
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
							<table id="item" class="easyui-datagrid" style="height: 380px; margin: 6px;" data-options="fitColumns:true,singleSelect: true,toolbar: '#itemTb',url: '${vix}/purchase/purchaseInquireAction!getPurchaseInquiryDetailJson.action'">
								<thead>
									<tr>
										<th data-options="field:'itemCode',width:80,align:'center'">编码</th>
										<th data-options="field:'itemName',width:120,align:'center'">名称</th>
										<th data-options="field:'skuCode',align:'center',width:120,editor:'text'">SKU码</th>
										<th data-options="field:'barCode',align:'center',width:120,editor:'text'">BAR码</th>
										<th data-options="field:'specification',width:100,align:'center'">规格型号</th>
										<th data-options="field:'unit',width:80,align:'center'">主计量单位</th>
										<th data-options="field:'amount',width:60,align:'center'">数量</th>
										<th data-options="field:'price',width:60,align:'center'">无税单价</th>
										<th data-options="field:'taxRate',width:60,align:'center'">税率</th>
										<th data-options="field:'total',width:60,align:'center'">含税总价</th>
										<th data-options="field:'requireTime',width:80,align:'center'">需求日期</th>
										<th data-options="field:'supplier',width:120,align:'center'">建议供应商</th>
									</tr>
								</thead>
							</table>
							<div id="itemTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="addItem(0)"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
									data-options="iconCls:'icon-add',plain:true" onclick="updateItem()"><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left"><s:text name="cmn_update" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-edit',plain:true" onclick="showItemPrice()"><span
									class="l-btn-left"><img alt="" src="${vix}/common/img/system/itemPrice.png"><span style="padding: 0;" class="l-btn-text l-btn-icon-left">定价</span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-remove',plain:true" onclick="removeItem()"><span class="l-btn-left"><span
										class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a>
							</div>
							<script type="text/javascript">
								function reloadDataGrid1() {
									$('#item').datagrid({
										queryParams : {
											id : $('#id').val()
										}
									});
									//修改下方数量
									$.ajax({
									url : '${vix}/purchase/purchaseInquireAction!getPurchaseInquireDetailsCount.action?id=' + $("#id").val(),
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
								function addItem(id) {
									if (!id)
										id = 0;
									if (!_pad_check_entity_saved_id(saveOrUpdateOrder, addItem))
										return;

									$.ajax({
									url : '${vix}/purchase/purchaseInquireAction!goSaveOrUpdatePurchaseInquireItem.action?id=' + id,
									cache : false,
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
								function removeItem() {
									var row = $('#item').datagrid('getSelected');
									if (row) {
										asyncbox.confirm('是否删除该订单明细?', '提示信息', function(action) {
											if (action == 'ok') {
												var index = $('#item').datagrid('getRowIndex', row);
												$('#item').datagrid('deleteRow', index);
												$.ajax({
												url : '${vix}/purchase/purchaseInquireAction!deletePurchaseInquireItem.action?id=' + row.id,
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

								function showItemPrice() {
									var row = $('#item').datagrid('getSelected');
									if (row) {
										var itemId = row.itemId;
										var count = row.amount;
										var requireTime = row.requireTime;
										showPrice(itemId, count, requireTime);
									} else {
										showMessage("请选择一条数据!");
									}
								}
								function updateItem() {
									var row = $('#item').datagrid('getSelected');
									if (row) {
										addItem(row.id);
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
							<tr>
								<td align="right">审批人：</td>
								<td><input id="checkPerson" name="checkPerson" value="${purchaseInquire.checkPerson }" type="text" size="30" /></td>
								<td align="right">审批日期：</td>
								<td><input id="checkTime" name="checkTime" value="<s:date name="purchaseInquire.checkTime" format="yyyy-MM-dd"/>" type="text" /> <img onclick="showTime('checkTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
							</tr>
						</table>
					</div>
				</dd>
			</dl>
		</div>
	</form>
</div>