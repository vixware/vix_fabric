
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<script type="text/javascript">
/** 页面加载 */
$(function(){
	//设置业务类型选中
	$("#bizType").val('${salesInvoice.bizType}');
	//设置单据类型选中
	$("#formType").val('${salesInvoice.formType}');
});
	$(".addtable .addtable_t").click(function(){
		$(this).toggleClass("addtable_td");
		$(this).next(".addtable_c").toggle();
	});
	$(".newvoucher dt b").click(function(){
		$(this).toggleClass("downup");
		$(this).parent("dt").next("dd").toggle();
	});
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
/** 保存销售发票 */
function saveOrUpdateOrder(){
	if($('#salesInvoiceForm').validationEngine('validate')){
		$.post('${vix}/sales/invoice/salesInvoiceAction!saveOrUpdate.action',
				{'salesInvoice.id':$("#id").val(),
					'salesInvoice.code':$("#code").val(),
					'salesInvoice.name':$("#name").val(),
					'salesInvoice.formType':$("#formType").val(),
					'salesInvoice.bizType':$("#bizType").val(),
					'salesInvoice.invoiceType':$("#invoiceType").val(),
					'salesInvoice.customerName':$("#customerName").val(),
					'salesInvoice.customerAccount.id':$("#customerAccountId").val(),
					'salesInvoice.payCondition':$("#payCondition").val(),
					'salesInvoice.amount':$("#amount").val(),
					'salesInvoice.tax':$("#tax").val(),
					'salesInvoice.deliveryTime':$("#deliveryTime").val(),
					'salesInvoice.createTime':$("#createTime").val()
				},
				function(result){
					showMessage(result);
					setTimeout("",1000);
					loadContent('${vix}/sales/invoice/salesInvoiceAction!goList.action?menuId=menuOrder');
				}
			 );
	}
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
if($(".ms").length>0){
	$(".ms").hover(function(){
		$(">ul",this).stop().slideDown(100);
	},function(){
		$(">ul",this).stop().slideUp(100);
	});
	$(".ms ul li").hover(function(){
		$(">a",this).addClass("hover");
		$(">ul",this).stop().slideDown(100);
	},function(){
		$(">a",this).removeClass("hover");
		$(">ul",this).stop().slideUp(100);
	});
}
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/sale/saleInvoice.png" alt="" />
					<s:text name="cmn_supplyChain" /></a></li>
				<li><a href="#"><s:text name="sa_salesmanage" /></a></li>
				<li><a href="#" onclick="loadContent('${vix}/sales/invoice/salesInvoiceAction!goList.action');">销售发票</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${salesInvoice.id }" />
<div class="content">
	<form id="salesInvoiceForm">
		<div class="order">
			<dl>
				<dt>
					<span class="no_line"> <a onclick="saveOrUpdateOrder();" href="#"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#"
						onclick="loadContent('${vix}/sales/invoice/salesInvoiceAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" alt="返回" src="${vix}/common/img/dt_goback.png" /></a>
					</span> <strong> <b> <s:if test="salesInvoice.name != null ">${salesInvoice.name}</s:if> <s:else>新增销售普通发票</s:else>
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
											<td align="right">客户名称：</td>
											<td><input id="customerName" name="salesInvoice.customerAccount.name" value="${salesInvoice.customerAccount.name}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span> <input type="hidden" id="customerAccountId" name="customerAccountId" value="${salesInvoice.customerAccount.id}" /> <span><a
													class="abtn" href="#" onclick="chooseCustomerAccount();"><span>选择</span></a></span></td>
											<td align="right">付款条件：</td>
											<td><input id="payCondition" name="payCondition" value="${salesInvoice.payCondition }" type="text" size="30" /></td>
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