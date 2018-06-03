<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript">
<!--
	$(".newvoucher dt b").click(function(){
		$(this).toggleClass("downup");
		$(this).parent("dt").next("dd").toggle();
	});
	/** input 获取焦点input效果绑定  */
	$(".order_table input[type='text']").focusin( function() {
	   $(this).css({'border':'1px solid #f00','background':'#f5f5f5'});
	});
	$(".order_table  input[type='text']").focusout( function() {
	   $(this).css({'border':'1px solid #ccc','background':'#fff'});
	});

	pager("start","${vix}/template/orderAction!goOrderItemSingleList.action?rows=6&id="+$("#id").val(),'orderUpdate');
	function currentPager(tag){
		pager(tag,"${vix}/template/orderAction!goOrderItemSingleList.action?rows=6&id="+$("#id").val(),'orderUpdate');
	}
	function chooseCustomerAccount(){
		$.ajax({
			  url:'${vix}/mdm/crm/customerAccountAction!goChooseCustomerAccount.action',
			  cache: false,
			  success: function(html){
				  asyncbox.open({
					 	modal:true,
						width : 960,
						height : 500,
						title:"选择客户",
						html:html,
						callback : function(action,returnValue){
							if(action == 'ok'){
								if(returnValue != undefined){
									var result = returnValue.split(":");
									$("#customerAccountId").val(result[0]);
									$("#customerName").val(result[1]);
								}else{
									$("#customerAccountId").val("");
									$("#customerName").val("");
								}
							}
						},
						btnsbar : $.btn.OKCANCEL
					});
			  }
		});
	}
	$("#salesInvoiceForm").validationEngine();
//-->
</script>
<div class="content">
	<form id="salesInvoiceForm">
		<input type="hidden" id="siId" name="id" value="${salesInvoice.id }" />
		<div class="order">
			<dl>
				<dt>
					<strong> <b> <s:if test="salesInvoice.name != null ">${salesInvoice.name}</s:if> <s:else>新增销售普通发票</s:else>
					</b> <i></i>
					</strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b class="downup"></b> <span style="display: none;"> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />
										<s:text name="calculator" /></a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />
										<s:text name="calculator" /></a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />
										<s:text name="calculator" /></a>
									</span> <strong>基本信息</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<td align="right">发票编码：</td>
											<td><input id="code" name="code" value="${salesInvoice.code }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">发票主题：</td>
											<td><input id="name" name="name" value="${salesInvoice.code }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">发票类型：</td>
											<td><select id="invoiceType" name="invoiceType" style="width: 50">
													<option value="">请选择</option>
													<s:if test="salesInvoice.invoiceType == 1">
														<option value="1" selected="selected">销售普通发票</option>
														<option value="2">销售专用发票</option>
														<option value="3">红字普通销售发票</option>
														<option value="4">红字专用销售发票</option>
													</s:if>
													<s:elseif test="salesInvoice.invoiceType == 2">
														<option value="1">销售普通发票</option>
														<option value="2" selected="selected">销售专用发票</option>
														<option value="3">红字普通销售发票</option>
														<option value="4">红字专用销售发票</option>
													</s:elseif>
													<s:elseif test="salesInvoice.invoiceType == 3">
														<option value="1">销售普通发票</option>
														<option value="2">销售专用发票</option>
														<option value="3" selected="selected">红字普通销售发票</option>
														<option value="4">红字专用销售发票</option>
													</s:elseif>
													<s:elseif test="salesInvoice.invoiceType == 4">
														<option value="1">销售普通发票</option>
														<option value="2">销售专用发票</option>
														<option value="3">红字普通销售发票</option>
														<option value="4" selected="selected">红字专用销售发票</option>
													</s:elseif>
													<s:else>
														<option value="1">销售普通发票</option>
														<option value="2">销售专用发票</option>
														<option value="3">红字普通销售发票</option>
														<option value="4">红字专用销售发票</option>
													</s:else>
											</select></td>
											<td align="right">业务类型：</td>
											<td><select id="formType" name="formType" style="width: 50" class="validate[required] text-input">
													<option value="">请选择</option>
													<option value="1">类型1</option>
													<option value="2">类型2</option>
											</select><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">金额：</td>
											<td><input id="amount" name="amount" value="${salesInvoice.amount }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">税率：</td>
											<td><input id="tax" name="tax" value="${salesInvoice.tax }" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">交货日期：</td>
											<td><input id="deliveryTime" name="deliveryTime" value="<s:property value='formatDateToString(salesInvoice.deliveryTime)'/>" readonly="readonly" type="text" /> <img onclick="showTime('deliveryTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
											<td align="right">单据日期：</td>
											<td><input id="createTime" name="createTime" value="<s:property value='formatDateToString(salesInvoice.createTime)'/>" readonly="readonly" type="text" class="validate[required] text-input" /> <img onclick="showTime('createTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"><span
												style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">付款条件：</td>
											<td colspan="3"><input id="payCondition" name="payCondition" value="${salesInvoice.payCondition }" type="text" size="30" /></td>
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
							<li class="current"><a onclick="javascript:tab(1,1,'a',event);"><img src="${vix}/common/img/mail.png" alt="" />发票明细</a></li>
						</ul>
					</div>
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<div style="padding: 8px;">
							<table id="salesInvoiceItem" class="easyui-datagrid" style="height: 380px; margin: 6px;" data-options="fitColumns:true,singleSelect: true,toolbar: '#salesInvoiceItemTb',url: '${vix}/sales/invoice/salesInvoiceAction!getSalesInvoiceItemJson.action?id=${salesInvoice.id}'">
								<thead>
									<tr>
										<th data-options="field:'id',align:'center',width:60">编号</th>
										<th data-options="field:'itemCode',width:150,align:'center'">${vv:varView('vix_mdm_item')}编码</th>
										<th data-options="field:'itemName',width:120,align:'center'">${vv:varView('vix_mdm_item')}名称</th>
										<th data-options="field:'amount',width:120,align:'center'">数量</th>
										<th data-options="field:'tax',width:120,align:'center'">税额</th>
										<th data-options="field:'price',width:120,align:'center'">单价</th>
									</tr>
								</thead>
							</table>
							<div id="salesInvoiceItemTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="addItem(0)"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
									data-options="iconCls:'icon-edit',plain:true" onclick="updateItem()"><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left"><s:text name="cmn_update" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-remove',plain:true" onclick="removeItem()"><span
									class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a>
							</div>
							<script type="text/javascript">
								$('#salesInvoiceItem').datagrid();
								function removeItem(){
									var row = $('#salesInvoiceItem').datagrid('getSelected');
									if(row){
										asyncbox.confirm('是否删除该发票明细?','提示信息',function(action){
											if(action == 'ok'){
												var index = $('#salesInvoiceItem').datagrid('getRowIndex',row);
												$('#salesInvoiceItem').datagrid('deleteRow', index);
												$.ajax({
													  url:'${vix}/sales/invoice/salesInvoiceItemAction!deleteById.action?id='+row.id,
													  cache: false,
													  success: function(html){
														  showMessage(html);
													  }
												});
											}
										});
									}else{
										showMessage("请选择一条数据!");
									}
								}
								function updateItem(){
									var row = $('#salesInvoiceItem').datagrid('getSelected');
									if(row){
										addItem(row.id);
									}else{
										showMessage("请选择一条数据!");
									}
								}
								function addItem(id){
									$.ajax({
										  url:'${vix}/sales/invoice/salesInvoiceItemAction!goSaveOrUpdate.action?id='+id,
										  cache: false,
										  success: function(html){
											  asyncbox.open({
												 	modal:true,
													width : 660,
													height : 360,
													title:"添加明细",
													html:html,
													callback : function(action){
														if(action == 'ok'){
															if($('#salesInvoiceItemForm').validationEngine('validate')){
																$.post('${vix}/sales/invoice/salesInvoiceItemAction!saveOrUpdate.action',
																	 {'salesInvoiceItem.id':$("#salesInvoiceItemId").val(),
																	  'salesInvoiceItem.salesInvoice.id':$("#id").val(),
																      'salesInvoiceItem.amount':$("#salesInvoiceItemAmount").val(),
																	  'salesInvoiceItem.itemCode':$("#itemCode").val(),
																	  'salesInvoiceItem.itemName':$("#itemName").val(),
																	  'salesInvoiceItem.tax':$("#salesInvoiceItemTax").val(),
																	  'salesInvoiceItem.price':$("#salesInvoiceItemPrice").val(),
																	  'salesInvoiceItem.currencyType.id':$("#currencyTypeId").val()
																	},
																	function(result){
																		asyncbox.success(result,"<s:text name='vix_message'/>",function(){
																			$('#salesInvoiceItem').datagrid("reload");
																		});
																	}
																 ); 
											  				}else{
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
						</div>
					</div>
				</div>
			</dl>
		</div>
	</form>
</div>
<!-- content -->
