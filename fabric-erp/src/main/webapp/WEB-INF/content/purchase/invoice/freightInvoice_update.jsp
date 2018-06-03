<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif"> <s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif"> <s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="img/pur_invoice.png" alt="" /> <s:text name="supplyChain" /></a></li>
				<li><a href="#"><s:text name="pur_purchase_manage" /></a></li>
				<li><a href="#" onclick="loadContent('${vix}/purchase/purchaseInvoiceAction!goList.action');">运费发票</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${purchaseInvoice.id }" />
<div class="content">
	<form id="purchaseInvoiceForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateOrder()" href="#"><img width="22" height="22" title="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" title="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/purchase/purchaseInvoiceAction!goList.action');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"></a></span> <strong><b>新增运费发票 </b></strong>
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
											<td align="right">发票编码：</td>
											<td><input id="code" name="code" value="${purchaseInvoice.code }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">发票主题：</td>
											<td><input id="name" name="name" value="${purchaseInvoice.name }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">发票类型：</td>
											<td><select id="orderType" name="orderType" style="width: 50" class="validate[required] text-input">
													<option value="">请选择</option>
													<option value="1">类型1</option>
													<option value="2">类型2</option>
											</select><span style="color: red;">*</span></td>
											<td align="right">业务类型：</td>
											<td><select id="businessType" name="businessType" style="width: 50">
													<option value="">请选择</option>
													<option value="1">类型1</option>
													<option value="2">类型2</option>
											</select></td>
										</tr>
										<tr>
											<td align="right">公司名称：</td>
											<td><input id="companyId" name="companyId" value="${purchaseInvoice.companyId }" type="text" size="30" /></td>
											<td align="right">币种名称：</td>
											<td><select id="currency" name="currency" style="width: 50" class="validate[required] text-input">
													<option value="">请选择</option>
													<option value="1">人民币</option>
													<option value="2">美元</option>
											</select><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">供应商名称：</td>
											<td><input id="supplierName" name="supplierName" value="${purchaseInvoice.supplierName }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">部门名称：</td>
											<td><input id="requireDepartment" name="requireDepartment" value="${purchaseInvoice.requireDepartment }" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">业务员：</td>
											<td><input id="purchasePerson" name="purchasePerson" value="${purchaseInvoice.purchasePerson }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">总额：</td>
											<td><input id="totalAmount" name="totalAmount" value="${purchaseInvoice.totalAmount }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">创建日期：</td>
											<td><input id="createTime" name="createTime" value="<fmt:formatDate value='${purchaseInvoice.createTime }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" class="validate[required] text-input" /> <img onclick="showTime('createTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22"
												align="absmiddle"><span style="color: red;">*</span></td>
											<td align="right">联系人：</td>
											<td><input id="contactPerson" name="contactPerson" value="${purchaseInvoice.contactPerson }" type="text" size="30" /></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
						<div style="margin: 15px 70px; display: none;">
							<textarea id="content" name="content"></textarea>
							<script type="text/javascript" src="${vix}/plugin/KindEditor/kindeditor-min.js"></script>
							<script type="text/javascript">
								var editor = KindEditor.create('textarea[name="content"]', {
								basePath : '${vix}/plugin/KindEditor/',
								width : 1100,
								height : 300,
								cssPath : '${vix}/plugin/KindEditor/plugins/code/prettify.css',
								uploadJson : '${vix}/plugin/KindEditor/jsp/upload_json.jsp',
								fileManagerJson : '${vix}/plugin/KindEditor/jsp/file_manager_json.jsp',
								allowFileManager : true
								});
							</script>
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
							<table id="dlLineItem" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="rownumbers:true,pagination: true,fitColumns: true,iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlLineItemTb',url: '${vix}/purchase/purchaseInvoiceAction!getPurchaseInvoiceItemJson.action?id=${purchaseInvoice.id}',onClickRow: onDlClickRow">
								<thead>
									<tr>
										<th data-options="field:'id',align:'center',width:120">编号</th>
										<th data-options="field:'itemCode',align:'center',width:120,editor:'text'">${vv:varView("vix_mdm_item")}编码</th>
										<th data-options="field:'itemName',width:200,align:'center',editor:'text'">${vv:varView("vix_mdm_item")}名称</th>
										<th data-options="field:'amount',width:200,align:'center',editor:'numberbox'">数量</th>
										<th data-options="field:'amount2',width:200,align:'center',editor:'text'">计量单位</th>
										<th data-options="field:'price',width:200,align:'center',editor:'numberbox'">单价</th>
										<th data-options="field:'netTotal',width:200,align:'center',editor:'numberbox'">金额</th>
										<th data-options="field:'tax',width:200,align:'center',editor:'numberbox'">税率</th>
										<th data-options="field:'taxAmount',width:200,align:'center',editor:'numberbox'">税额</th>
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
								var editIndexDlLineItem = undefined;
								function endDlEditing() {
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
								function onDlClickRow(index) {
									if (editIndexDlLineItem != index) {
										if (endDlEditing()) {
											$('#dlLineItem').datagrid('selectRow', index).datagrid('beginEdit', index);
											editIndexDlLineItem = index;
										} else {
											$('#dlLineItem').datagrid('selectRow', editIndexDlLineItem);
										}
									}
								}
								function appendDlLineItem() {
									if (endDlEditing()) {
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
									if (endDlEditing()) {
										$('#dlLineItem').datagrid('acceptChanges');
									}
								}
							</script>
						</div>
					</div>
				</div>
			</dl>
		</div>
	</form>
</div>