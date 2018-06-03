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
 
$("#customerizeProductForm").validationEngine();
 
function chooseCpEmployee(){
	$.ajax({
		  url:'${vix}/common/select/commonSelectEmpAction!goList.action?tag=choose',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 960,
					height : 500,
					title:"选择职员",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								returnValue = returnValue.replace(',','');
								var result = returnValue.split(":");
								$("#cpSalePersonId").val(result[0]);
								$("#cpSalePersonName").val(result[1]);
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
function chooseCpOrgUnit(){
	$.ajax({
		  url:'${vix}/common/select/commonSelectOrgUnitAction!goChooseOrgUnit.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 560,
					height : 340,
					title:"选择部门",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var resObj = $.parseJSON(returnValue);
								$("#cpOrganizationUnitId").val(resObj[0].realId);
								$("#cpOrganizationUnitName").val(resObj[0].name);
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}

function chooseProjectQuotationScheme(){
	$.ajax({
		url:'${vix}/sales/quotes/projectQuotationSchemeAction!goChooseProjectQuotationScheme.action',
		cache: false,
		success: function(html){
			asyncbox.open({
				 	modal:true,
					width : 960,
					height : 500,
					title:"选择项目式报价单",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if (returnValue != '') {
			                    $.ajax({
			                    	url : '${vix}/sales/order/salesOrderAction!convertProjectQuotationSchemeToSalesOrder.action?projectQuotationSchemeIds='+returnValue,
			                    	cache : false,
			                    	success : function(html) {
			                    		 $("#mainContent").html(html);
			                    	}
			                	});
		                    } else {
			                    asyncbox.success("请选择报价单!", "<s:text name='vix_message'/>");
			                    return false;
		                    }
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
//-->
</script>
<div class="content" style="margin-top: 15px; margin-bottom: -10px; overflow: hidden">
	<form id="customerizeProductForm">
		<input type="hidden" id="customerizeProductId" name="customerizeProductId" value="${customerizeProduct.id}" />
		<div class="box order_table" style="line-height: normal;">
			<table>
				<tr height="30">
					<td align="right" width="10%">主题:</td>
					<td width="35%"><input id="customerizeProductName" name="customerizeProduct.name" value="${customerizeProduct.name}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span></td>
					<td align="right" width="10%">项目式报价单:</td>
					<td width="40%"><input id="projectQuotationSchemeName" name="customerizeProduct.organizationUnit.fullName" value="${customerizeProduct.projectQuotationScheme.name}" type="text" /> <input type="hidden" id="projectQuotationSchemeId" name="projectQuotationSchemeId" value="${customerizeProduct.projectQuotationScheme.id}" /> <span><a
							class="abtn" href="###" onclick="chooseProjectQuotationScheme();"><span>选择</span></a></span></td>
				</tr>
				<tr height="30">
					<td align="right" width="10%">部门:</td>
					<td width="35%"><input id="cpOrganizationUnitName" name="customerizeProduct.organizationUnit.fullName" value="${customerizeProduct.organizationUnit.fullName}" type="text" /> <input type="hidden" id="cpOrganizationUnitId" name="cpOrganizationUnitId" value="${customerizeProduct.organizationUnit.id}" /> <span><a class="abtn"
							href="###" onclick="chooseCpOrgUnit();"><span>选择</span></a></span></td>
					<td align="right" width="10%">单据日期:</td>
					<td width="40%"><input id="customerizeProductBillDate" name="customerizeProduct.billDate" value="<s:property value='formatDateToString(customerizeProduct.billDate)'/>" type="text" /> <img onclick="showTime('billDate','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
				<tr height="30">
					<td align="right">开始日期:</td>
					<td><input id="customerizeProductStartDate" name="customerizeProduct.startDate" value="${customerizeProduct.startDate}" type="text" /> <img onclick="showTime('customerizeProductStartDate','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
					<td align="right">结束日期:</td>
					<td><input id="customerizeProductEndDate" name="customerizeProduct.endDate" value="${customerizeProduct.endDate}" type="text" /> <img onclick="showTime('customerizeProductEndDate','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
				</tr>
				<tr height="30">
					<td align="right">销售组织:</td>
					<td><input id="salesOrg" name="customerizeProduct.salesOrg" value="${customerizeProduct.salesOrg}" type="text" /></td>
					<td align="right">业务员:</td>
					<td><input id="cpSalePersonName" name="customerizeProduct.salePerson.name" value="${customerizeProduct.salePerson.name}" type="text" /> <input type="hidden" id="cpSalePersonId" name="salePersonId" value="${customerizeProduct.salePerson.id}" /> <span><a class="abtn" href="###" onclick="chooseCpEmployee();"><span>选择</span></a></span></td>
				</tr>
				<tr height="30">
					<td align="right">需求类型:</td>
					<td colspan="3"><input id="requirementType" name="customerizeProduct.requirementType" value="${customerizeProduct.requirementType}" type="text" /></td>
				</tr>
				<tr height="30">
					<td align="right">需求内容:</td>
					<td colspan="3"><textarea id="requirementContent" name="requirementContent" style="width: 706px; height: 84px;">${customerizeProduct.requirementContent}</textarea></td>
				</tr>
			</table>
		</div>
		<div style="padding: 0 7px;">
			<div class="right_menu">
				<ul>
					<li class="current"><a><img src="${vix}/common/img/mail.png" alt="" />需求方案明细</a></li>
				</ul>
			</div>
			<div id="a1" style="display: block; padding: 0;">
				<table id="customerizeProductItem" class="easyui-datagrid" style="padding: 0; height: 380px;" data-options="fitColumns:true,singleSelect: true,toolbar: '#customerizeProductItemTb',url: '${vix}/sales/quotes/customerizeProductAction!getCustomerizeProductItemJson.action?id=${customerizeProduct.id}'">
					<thead>
						<tr>
							<th data-options="field:'id',width:120,align:'center'">编号</th>
							<th data-options="field:'item.name',width:420,align:'center'">${vv:varView('vix_mdm_item')}</th>
							<th data-options="field:'amount',width:220,align:'center'">数量</th>
							<th data-options="field:'unit',width:220,align:'center'">单位</th>
						</tr>
					</thead>
				</table>
				<div id="customerizeProductItemTb" style="padding: 0; height: auto">
					<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="addItem(0)"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
						data-options="iconCls:'icon-edit',plain:true" onclick="updateItem()"><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left"><s:text name="cmn_update" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-remove',plain:true" onclick="removeItem()"><span
						class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a>
				</div>
				<script type="text/javascript">
						$('#customerizeProductItem').datagrid();
						function removeItem(){
							var row = $('#customerizeProductItem').datagrid('getSelected');
							if(row){
								var index = $('#customerizeProductItem').datagrid('getRowIndex',row);
								$('#customerizeProductItem').datagrid('deleteRow', index);
								$.ajax({
									  url:'${vix}/sales/quotes/customerizeProductAction!deleteCustomerizeProductItemById.action?id='+row.id,
									  cache: false,
									  success: function(html){
										  showMessage(html);
									  }
								});
							}
						}
						function updateItem(){
							var row = $('#customerizeProductItem').datagrid('getSelected');
							if(row){
								addItem(row.id);
							}
						}
						function addItem(id){
							$.ajax({
								  url:'${vix}/sales/quotes/customerizeProductAction!goSaveOrUpdateCustomerizeProductItem.action?id='+id,
								  cache: false,
								  success: function(html){
									  asyncbox.open({
										 	modal:true,
											width : 760,
											height : 400,
											title:"添加客户需求明细",
											html:html,
											callback : function(action){
												if(action == 'ok'){
													if($('#customerizeProductItemForm').validationEngine('validate')){
														$.post('${vix}/sales/quotes/customerizeProductAction!saveOrUpdateCustomerizeProductItem.action',
																{'customerizeProductItem.id':$("#customerizeProductItemId").val(),
																  'customerizeProductItem.item.id':$("#itemCustomerizeProductItemId").val(),
																  'customerizeProductItem.price':$("#price").val(),
																  'customerizeProductItem.amount':$("#cpiAmount").val(),
																  'customerizeProductItem.unit':$("#cpiUnit").val(),
																  'customerizeProductItem.customerizeProduct.id':$("#customerizeProductId").val()
																},
																function(result){
																	showMessage(result);
																	setTimeout("",1000);
																	$('#customerizeProductItem').datagrid("reload");
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
	</form>
</div>
<!-- content -->
