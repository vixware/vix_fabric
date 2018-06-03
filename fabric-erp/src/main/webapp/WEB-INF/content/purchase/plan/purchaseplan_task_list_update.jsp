<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script src="${vix}/common/js/ajaxfileupload.js" type="text/javascript"></script>
<script type="text/javascript">
	function goBeforeOrder(id) {
		$.ajax({
		url : '${vix}/business/orderProcessAction!goBeforeOrder.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#ordercontent").html(html);
		}
		});
	};
	function goAfterOrder(id) {
		$.ajax({
		url : '${vix}/business/orderProcessAction!goAfterOrder.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#ordercontent").html(html);
		}
		});
	};
</script>
<input type="hidden" id="orderId" name="orderId" value="${order.id}" />
<div class="content" id="ordercontent">
	<form id="order">
		<div class="order">
			<dl>
				<br />
				<dt id="orderTitleFixd">
					<span class="no_line"> <a href="#" onclick="goBeforeOrder(${order.id });"><img width="22" height="22" title="上一条" src="${vix}/common/img/m_previous.gif"> </a> <a href="#" onclick="goAfterOrder(${order.id });"> <img width="22" height="22" title="下一条" src="${vix}/common/img/m_next.gif">
					</a>
					</span>
				</dt>
				<br />
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
											<td align="right">编码：</td>
											<td><input id="purchasePlanCode" name="purchasePlanCode" value="${purchasePlan.purchasePlanCode }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
											<td align="right">名称：</td>
											<td><input id="purchasePlanName" name="purchasePlanName" value="${purchasePlan.purchasePlanName }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">采购数量：</td>
											<td><input id="amount" name="amount" value="${purchasePlan.amount }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
											<td align="right">采购金额：</td>
											<td><input id="price" name="price" value="${purchasePlan.price }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">创建人：</td>
											<td><input id="creator" name="creator" value="${purchasePlan.creator }" type="text" size="30" class="validate[required] text-input" /onchange="fieldChanged(this);"><span style="color: red;">*</span></td>
											<td align="right">创建时间：</td>
											<td><input id="createTime" name="createTime" value="<fmt:formatDate value='${purchasePlan.createTime }' type='both' pattern='yyyy-MM-dd hh:mm:ss' />" type="text" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span> <img onclick="showTime('createTime','yyyy-MM-dd hh:mm:ss')"
												src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<td align="right">供应商名称：</td>
											<td><input id="supplierName" name="supplierName" value="${purchasePlan.supplier.name  }" type="text" size="30" onchange="fieldChanged(this);" /><input type="hidden" id="supplierId" name="supplierId" value="${purchasePlan.supplier.id }" /> <input class="btn" type="button" value="选择" onclick="chooseRadioSupplier();" /></td>
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
					<div id="a1" class="right_content" style="position: relative; z-index: 1; background: #FFF;">
						<script type="text/javascript">
							function saveDeliveryAddress(id) {
								$.ajax({
								url : '${vix}/purchase/purchasePlanAction!goSaveOrUpdatePurchasePlanItems.action?id=' + id,
								cache : false,
								success : function(html) {
									asyncbox.open({
									modal : true,
									width : 1000,
									height : 250,
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
							url : '${vix}/purchase/purchasePlanAction!getPurchasePlanItemsJson.action?id=${purchasePlan.id}',
							width : 850,
							height : 250,
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
							width : 100,
							align : 'center'
							}, {
							field : 'itemName',
							title : '商品名称',
							width : 100,
							align : 'center'
							} ] ],
							columns : [ [ {
							field : 'specification',
							title : '规格型号',
							width : 100,
							align : 'center'
							}, {
							field : 'measureUnit',
							title : '单位',
							width : 100,
							align : 'center',
							formatter : function(value, rec, index) {
								var measureUnitName = '';
								if (value != null) {
									measureUnitName = value.name;
								}
								return measureUnitName;
							}
							}, {
							field : 'unitcost',
							title : '单价',
							width : 100,
							align : 'center',
							editor : 'numberbox',
							required : 'true'
							}, {
							field : 'amount',
							title : '数量',
							width : 100,
							align : 'center',
							editor : 'numberbox',
							required : 'true'
							}, {
							field : 'price',
							title : '金额',
							width : 100,
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
									url : '${vix}/purchase/purchasePlanAction!deletePurchasePlanItemsById.action?id=' + rows[i].id,
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
					<div id="a2" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<script type="text/javascript">
							$('#soAttach')
									.datagrid({
									url : '${vix}/purchase/purchasePlanAction!getAttachmentsJson.action?id=${purchasePlan.id}',
									title : '订单附件',
									width : 850,
									height : '250',
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
											asyncbox
													.open({
													modal : true,
													width : 364,
													height : 160,
													title : "上传附件",
													html : html,
													callback : function(action, returnValue) {
														if (action == 'ok') {
															uploadFile('${vix}/purchase/purchasePlanAction!uploadAttachments.action?id=${purchasePlan.id}', 'fileToUpload');
															//$('#soAttach').datagrid({ url:"${vix}/purchase/purchasePlanAction!getAttachmentsJson.action?id=${purchasePlan.id}",method:"post"});
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