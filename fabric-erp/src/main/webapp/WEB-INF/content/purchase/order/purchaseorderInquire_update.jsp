<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript">  
//js定义
var updateField = "";
function fieldChanged(input) {
	updateField += $(input).attr("id") + ",";
}
$(function(){
	//设置单据类型选中(修改)
	$("#orderType").val('${purchaseOrder.orderTypeCode}');
	//设置业务类型选中(修改)
	$("#bizType").val('${purchaseOrder.businessTypeCode}');
	//设置币种选中(修改)
	$("#currency").val('${purchaseOrder.currencyCode}');
	//设置状态选中(修改)
	$("#status").val('${purchaseOrder.status}');
	//新增带入创建时间
	if(${null == purchaseOrder.createTime }){
		var myDate = new Date();
		$("#createTime").val(myDate.getFullYear()+"-"+(myDate.getMonth()+1)+"-"+myDate.getDate());
	}
});
/** 保存采购订单 */
function saveOrUpdateOrder(){
	var orderItemStr = "";
	/** 明细 */
	var dlData = $("#dlLineItem").datagrid("getRows");
	var dlJson = JSON.stringify(dlData);
	/** 地址 */
	var raData = $("#dlReceivedAddress").datagrid("getRows");
	var raJson = JSON.stringify(raData);
	/** 发运计划 */
	var dpData = $("#dlDeliveryPlan").datagrid("getRows");
	var dpJson = JSON.stringify(dpData);
	/** 发票 */
	var piData = $("#dlInvoice").datagrid("getRows");
	var piJson = JSON.stringify(piData);
	/** 价格条件 */
	var pcData = $("#dlPriceConditions").datagrid("getRows");
	var pcJson = JSON.stringify(pcData);
	/** 审批 */
	var aoData = $("#dlApprovalOpinion").datagrid("getRows");
	var aoJson = JSON.stringify(aoData);
	if($('#purchaseOrderForm').validationEngine('validate')){
		$.post('${vix}/purchase/purchaseOrderAction!saveOrUpdate.action',
				{
					'purchaseOrder.id':$("#id").val(),
					'purchaseOrder.code':$("#code").val(),
					'purchaseOrder.name':$("#name").val(),
					'purchaseOrder.orderTypeCode':$("#orderTypeCode").val(),
					'purchaseOrder.orderType':$("#orderType").find("option:selected").text(),
					'purchaseOrder.businessTypeCode':$("#bizTypeCode").val(),
					'purchaseOrder.businessType':$("#bizType").find("option:selected").text(),
					'purchaseOrder.totalAmount':$("#orderAmount").val(),
					'purchaseOrder.supplierCode':$("#supplierCode").val(),
					'purchaseOrder.supplierName':$("#supplierName").val(),
					'purchaseOrder.supplierId':$("#supplierId").val(),
					'purchaseOrder.purchaseOrganization':$("#purchaseOrganization").val(),
					'purchaseOrder.purchasePerson':$("#purchasePerson").val(),
					'purchaseOrder.currencyCode':$("#currencyCode").val(),
					'purchaseOrder.currency':$("#currency").find("option:selected").text(),
					'purchaseOrder.taxRate':$("#taxRate").val(),
					'purchaseOrder.postingDate':$("#postingDate").val(),
					'purchaseOrder.deliveryDate':$("#deliveryDate").val(),
					'purchaseOrder.createTime':$("#createTime").val(),
					'purchaseOrder.contactPerson':$("#contactPerson").val(),
					'purchaseOrder.mark':$("#mark").val(),
					'purchaseOrder.project':$("#project").val(),
					'purchaseOrder.rate':$("#rate").val(),
					'purchaseOrder.prepayments':$("#prepayments").val(),
					'purchaseOrder.status':$("#status").val(),
					'purchaseOrder.memo':$("#memo").val(),
					'purchaseOrder.version':$("#version").val(),
					'purchaseOrder.payPeriod':$("#payPeriod").val(),
					'purchaseOrder.account':$("#account").val(),
					'purchaseOrder.payerCode':$("#payerCode").val(),
					'purchaseOrder.payerName':$("#payerName").val(),
					'purchaseOrder.contractTime':$("#contractTime").val(),
					'purchaseOrder.carrier':$("#carrier").val(),
					'purchaseOrder.subtotal':$("#subtotal").val(),
					'purchaseOrder.shipping':$("#shipping").val(),
					'purchaseOrder.tax':$("#tax").val(),
					'updateField' : updateField,
					'orderItemStr':orderItemStr,
					'dlJson':dlJson,
					'raJson':raJson,
					'dpJson':dpJson,
					'piJson':piJson,
					'pcJson':pcJson,
					'aoJson':aoJson
				},
				function(result){
					showMessage(result);
					setTimeout("",1000);
					loadContent('${vix}/purchase/purchaseOrderAction!goList.action');
				}
			 );
	}
}
	
$("#purchaseOrderForm").validationEngine();
/** 选择单选供应商 */
function chooseRadioSupplier(){
	$.ajax({
		  url:'${vix}/purchase/purchaseOrderAction!goChooseRadioSupplier.action',
		  cache: false,
		  success: function(html){
			  $(".ab_outer .list td input[type='checkbox']").css("margin-left",10);
			  $(".ab_c .content").css("margin-bottom","0");
			  $('.ab_c .content').parent().css('position','relative');
			  asyncbox.open({
				 	modal:true,
					width : 1000,
					height : 500,
					title:"选择供应商",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var rv = returnValue.split(",");
								$("#supplierCode").val(rv[2]);
								$("#supplierName").val(rv[1]);
								$("#supplierId").val(rv[0]);
							}else{
								$("#supplierName").val(rv[1]);
								asyncbox.success("请选择分类信息!","提示信息");
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
function fillingOrderTypeCode(){
	$("#orderTypeCode").val($("#orderType").val());
}
function fillingBizTypeCode(){
	$("#bizTypeCode").val($("#bizType").val());
}
function fillingCurrencyCode(){
	$("#currencyCode").val($("#currency").val());
}
/** 选择采购计划单 */
function choosePurchaseInquire (){
	$.ajax ({
			url : '${vix}/purchase/purchaseOrderAction!goChoosePurchaseInquire.action' ,
			cache : false ,
			success : function (html){
				asyncbox
						.open ({
						modal : true ,
						width : 900 ,
						height : 550 ,
						title : "选择采购询价单" ,
						html : html ,
						callback : function (action , returnValue){
							if (action == 'ok'){
								if (returnValue != ''){
									$.ajax ({
											url : '${vix}/purchase/purchaseOrderAction!converterInquireToOrder.action?purchaseInquireid='+returnValue+"&id=${purchaseOrder.id}",
											cache : false ,
											success : function (result){
												showMessage (result);
												setTimeout ("" , 1000);
												$ ('#dlLineItem').datagrid ("reload");
											}
											});
								}else{
									asyncbox.success ("请选择订单!" , "<s:text name='vix_message'/>");
									return false;
								}
							}
						} ,
						btnsbar : $.btn.OKCANCEL
						});
			}
			});
}
/** 快速新建供应商 */
function createSupplier(){
	$.ajax({
		  url:'${vix}/purchase/purchaseOrderAction!goAddQuicklySupplier.action?',
		  cache: false,
		  success: function(html){
		 	  asyncbox.open({
				 	modal:true,
					width : 800,
					height : 500,
					title:"快速新建供应商",
					html:html,
					callback : function(action){
						if(action == 'ok'){
							if($('#supplierForm').validationEngine('validate')){
								$.post('${vix}/purchase/purchaseOrderAction!saveOrUpdateSupplier.action', {
										'supplier.id' : $("#srmId").val(),
										'supplier.code': $("#newSupplierCode").val(),
										'supplier.name': $("#newSupplierName").val(),
										'supplier.shortName': $("#newShortName").val(),
										'supplier.artificialPerson': $("#newArtificialPerson").val(),
										'supplier.supplierCategory.id':$("#newParentId").val(),
										'supplier.catalog':$("#newCatalog").val(),
										'supplier.type':$("#newType").val(),
										'supplier.openingBank':$("#newOpeningBank").val(),
										'supplier.bankAccount':$("#newBankAccount").val(),
										'supplier.contacts':$("#newContacts").val(),
										'supplier.telephone':$("#newTelephone").val(),
										'supplier.portraiture':$("#newPortraiture").val(),
										'supplier.email':$("#newEmail").val(),
										'supplier.deliveryAddress':$("#newDeliveryAddress").val()
										}, function(result){
											var rt = result.split(",");
											showMessage(rt[0]+rt[3]);
											setTimeout("",1000);
											$("#supplierCode").val(rt[1]);
											$("#supplierName").val(rt[2]);
										});
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
//弹出组织树
function chooseParentCategory(){
	$.ajax({
		  url:'${vix}/hr/rePlanAction!goChooseCategory.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 560,
					height : 340,
					title:"选择采购组织",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var result = returnValue.split(",");
									$("#purchaseOrganization").val(result[1]);
							}else{
								asyncbox.success("请选择分类信息!","提示信息");
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
/** 选择项目 */
function chooseRadioProject(){
	$.ajax({
		  url:'${vix}/purchase/purchaseOrderAction!goChooseRadioProject.action',
		  cache: false,
		  success: function(html){
			  $(".ab_outer .list td input[type='checkbox']").css("margin-left",10);
			  $(".ab_c .content").css("margin-bottom","0");
			  $('.ab_c .content').parent().css('position','relative');
			  asyncbox.open({
				 	modal:true,
					width : 1000,
					height : 500,
					title:"选择项目",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								//alert(returnValue); 
								var rv = returnValue.split(","); 
								$("#project").val(rv[1]);
							}else{
								asyncbox.success("请选择项目!","提示信息");
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
				<li><a href="#"><img src="img/pur_order.png" alt="" /> <s:text name="cmn_supplyChain" /></a></li>
				<li><a href="#"><s:text name="pur_purchase_manage" /></a></li>
				<li><a href="#" onclick="loadContent('${vix}/purchase/purchaseOrderAction!goList.action');"><s:text name="pur_purchase_order" /></a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${purchaseOrder.id}" />
<input type="hidden" id="mark" value="3" />
<div class="content">
	<form id="purchaseOrderForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateOrder();" href="#"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_savenew.png" /></a> <a href="#"><img width="22" height="22" alt="取消"
							src="${vix}/common/img/dt_cancelback.png" /></a> <a href="#"><img width="22" height="22" alt="禁用" src="${vix}/common/img/dt_disable.png" /></a> <a href="#"><img width="22" height="22" alt="删除" src="${vix}/common/img/dt_delete.png" /></a> <a href="#"><img width="22" height="22" alt="审批通过" src="${vix}/common/img/dt_aprroval.png" /></a> <a
						href="#"><img width="22" height="22" alt="驳回" src="${vix}/common/img/dt_reject.png"></a> <a href="#"><img width="22" height="22" alt="上一条" src="${vix}/common/img/dt_before.png"></a> <a href="#"><img width="22" height="22" alt="下一条" src="${vix}/common/img/dt_next.png"></a> <a href="#"><img width="22" height="22" alt="打印"
							src="${vix}/common/img/dt_print.png"></a>
						<div class="ms" style="float: none; display: inline;">
							<a href="#"><img width="22" height="22" alt="报表" src="${vix}/common/img/dt_report.png"></a>
							<ul style="display: none; overflow: hidden; height: 124px; margin-top: 0px; margin-bottom: 0px; padding-top: 0px; padding-bottom: 0px;">
								<li><a href="#"><img src="${vix}/common/img/icon_10.png" alt="">信息</a></li>
							</ul>
						</div> <a href="#"><img width="22" height="22" alt="导出" src="${vix}/common/img/dt_export.png"></a> <a href="#" onclick="loadContent('${vix}/purchase/purchaseOrderAction!goList.action');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
					</span> <strong> <b>新增采购订单(来源自采购询价单)</b> <i></i>
					</strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<strong>基本信息</strong>
								</dt>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<td align="right">取单类型：</td>
											<td colspan="3"><font color="red">采购询价单</font> <input class="btn" type="button" value="取单" onclick="choosePurchaseInquire();" /></td>
										</tr>
										<tr>
											<td align="right">订单编码：</td>
											<td><input id="code" name="code" value="${purchaseOrder.code }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
											<td align="right">订单主题：</td>
											<td><input id="name" name="name" value="${purchaseOrder.name }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">单据类型：</td>
											<td><select id="orderType" name="orderType" class="validate[required] text-input" onclick="fillingOrderTypeCode();" onchange="fieldChanged(this);">
													<option value="">请选择</option>
													<c:forEach var="ot" items="${orderTypeList }">
														<option value="${ot.code }">${ot.name }</option>
													</c:forEach>
											</select> <input type="hidden" id="orderTypeCode" name="orderTypeCode" value="${purchaseOrder.orderTypeCode }" /> <span style="color: red;">*</span></td>
											<td align="right">状态：</td>
											<td><select id="status" name="status" onchange="fieldChanged(this);">
													<option value="">请选择</option>
													<option value="S1">待确认</option>
													<option value="S2">审批中</option>
													<option value="S3">已发货</option>
													<option value="S4">已完成</option>
											</select></td>
										</tr>
										<tr>
											<td align="right">项目：</td>
											<td><input id="project" name="project" value="${purchaseOrder.project }" type="text" size="30" onchange="fieldChanged(this);" /> <input class="btn" type="button" value="选择" onclick="chooseRadioProject();" /></td>
											<td align="right">业务类型：</td>
											<td><select id="bizType" name="bizType" onclick="fillingBizTypeCode();" onchange="fieldChanged(this);">
													<option value="">请选择</option>
													<c:forEach var="bt" items="${bizTypeList }">
														<option value="${bt.code }">${bt.name }</option>
													</c:forEach>
											</select> <input type="hidden" id="bizTypeCode" name="bizTypeCode" value="${purchaseOrder.businessTypeCode }" /></td>
										</tr>
										<tr>
											<td align="right">供应商名称：</td>
											<td><input id="supplierName" name="supplierName" value="${purchaseOrder.supplierName }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /> <span style="color: red;">*</span> <input type="hidden" id="supplierCode" name="supplierCode" value="${purchaseOrder.supplierCode }" /> <input
												type="hidden" id="supplierId" name="supplierId" value="${purchaseOrder.supplierId }" /> <input class="btn" type="button" value="选择" onclick="chooseRadioSupplier();" /> <input class="btn" type="button" value="快速新建" onclick="createSupplier();" /></td>
											<td align="right">联系人：</td>
											<td><input id="contactPerson" name="contactPerson" value="${purchaseOrder.contactPerson }" type="text" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<td align="right">币种：</td>
											<td><select id="currency" name="currency" onclick="fillingCurrencyCode();" onchange="fieldChanged(this);">
													<option value="">请选择</option>
													<c:forEach var="cy" items="${currencyTypeList }">
														<option value="${cy.code }">${cy.name }</option>
													</c:forEach>
											</select> <input type="hidden" id="currencyCode" name="currencyCode" value="${purchaseOrder.currencyCode }" /></td>
											<td align="right">汇率：</td>
											<td><input id="rate" name="rate" value="${purchaseOrder.rate }" type="text" size="30" onchange="fieldChanged(this);" /></td>
										</tr>
										<tr>
											<td align="right">采购人：</td>
											<td><input id="purchasePerson" name="purchasePerson" value="${purchaseOrder.purchasePerson }" type="text" size="30" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span></td>
											<td align="right">采购组织：</td>
											<td><input id="purchaseOrganization" name="purchaseOrganization" value="${purchaseOrder.purchaseOrganization }" type="text" size="30" onchange="fieldChanged(this);" /> <input class="btn" type="button" value="选择" onclick="chooseParentCategory();" /></td>
										</tr>
										<tr>
											<td align="right">过账日期：</td>
											<td><input id="postingDate" name="postingDate" value="<fmt:formatDate value='${purchaseOrder.postingDate }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" onchange="fieldChanged(this);" /> <img onclick="showTime('postingDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22"
												align="absmiddle"></td>
											<td align="right">交货日期：</td>
											<td><input id="deliveryDate" name="deliveryDate" value="<fmt:formatDate value='${purchaseOrder.deliveryDate }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" onchange="fieldChanged(this);" /> <img onclick="showTime('deliveryDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22"
												align="absmiddle"></td>
										</tr>
										<tr>
											<td align="right">创建日期：</td>
											<td><input id="createTime" name="createTime" value="<fmt:formatDate value='${purchaseOrder.createTime }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" class="validate[required] text-input" onchange="fieldChanged(this);" /><span style="color: red;">*</span> <img onclick="showTime('createTime','yyyy-MM-dd')"
												src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<td align="right">备注：</td>
											<td colspan="3"><textarea id="memo" name="memo" class="example" rows="2" style="width: 700px" onchange="fieldChanged(this);">${purchaseOrder.memo }</textarea></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b></b> <span style="display: none;"> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a>
									</span> <strong>其他信息</strong>
								</dt>

								<dd style="display: none;">
									<table style="border: none;">
										<tr>
											<td align="right">预付账款：</td>
											<td><input id="prepayments" name="prepayments" value="${purchaseOrder.prepayments }" type="text" size="30" /></td>
											<td align="right">付款周期：</td>
											<td><input id="payPeriod" name="payPeriod" value="${purchaseOrder.payPeriod }" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">版本号：</td>
											<td><input id="version" name="version" value="${purchaseOrder.version }" type="text" size="30" /></td>
											<td align="right">会计科目：</td>
											<td><input id="account" name="account" value="${purchaseOrder.account }" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">付款人编码：</td>
											<td><input id="payerCode" name="payerCode" value="${purchaseOrder.payerCode }" type="text" size="30" /></td>
											<td align="right">付款人名称：</td>
											<td><input id="payerName" name="payerName" value="${purchaseOrder.payerName }" type="text" size="30" /></td>
										</tr>
										<tr>
											<td align="right">合同日期：</td>
											<td><input id="contractTime" name="contractTime" value="<fmt:formatDate value='${purchaseOrder.contractTime }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" /> <img onclick="showTime('contractTime','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
											<td align="right">承运人：</td>
											<td><input id="carrier" name="carrier" value="${purchaseOrder.carrier }" type="text" size="30" /></td>
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
							<li class="current"><a onclick="javascript:tab(8,1,'a',event);"><img src="${vix}/common/img/mail.png" alt="" />明细</a></li>
							<li><a onclick="javascript:$('#a2').attr('style','');tab(8,2,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />交货地址</a></li>
							<li><a onclick="javascript:$('#a3').attr('style','');tab(8,3,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />发运计划</a></li>
							<li><a onclick="javascript:$('#a4').attr('style','');tab(8,4,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />发票</a></li>
							<li><a onclick="javascript:$('#a5').attr('style','');tab(8,5,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />价格条件</a></li>
							<li><a onclick="javascript:$('#a6').attr('style','');tab(8,6,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />附件/文本</a></li>
							<li><a onclick="javascript:$('#a7').attr('style','');tab(8,7,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />审批</a></li>
						</ul>
					</div>
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<div style="padding: 8px;">
							<table id="dlLineItem" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="rownumbers:true,pagination: true,iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlLineItemTb',url: '${vix}/purchase/purchaseOrderAction!getPurchaseOrderLineItemsJson.action?id=${purchaseOrder.id}'">
								<thead>
									<tr>
										<th data-options="field:'itemCode',align:'center',width:120,editor:'text'">编码</th>
										<th data-options="field:'itemName',width:200,align:'center',editor:'text'">名称</th>
										<th data-options="field:'specification',width:120,align:'center',editor:'text'">规格型号</th>
										<th data-options="field:'itemType',width:120,align:'center',editor:'text'">类型</th>
										<th data-options="field:'amount',width:120,align:'center',editor:'numberbox'">订货数量</th>
										<th data-options="field:'price',width:120,align:'center',editor:'numberbox'">单价</th>
										<th data-options="field:'price',width:120,align:'center',editor:'numberbox'">单价</th>
										<th data-options="field:'netTotal',width:120,align:'center',editor:'numberbox'">金额</th>
										<th data-options="field:'recieveAddress',width:120,align:'center',editor:'text'">收货仓库</th>
									</tr>
								</thead>
							</table>
							<div id="dlLineItemTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="addItem()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
									data-options="iconCls:'icon-edit',plain:true" onclick="editItem()"><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left">修改</span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-remove',plain:true" onclick="removeDlLineItem()"><span
									class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a>
							</div>
							<script type="text/javascript">
							$('#dlLineItem').datagrid();
							function addItem(){
								$.ajax({
									  url:'${vix}/purchase/purchaseOrderAction!goAddPurchaseOrderLineItem.action?supplierId='+$("#supplierId").val(),
									  cache: false,
									  success: function(html){
										  asyncbox.open({
											 	modal:true,
												width : 700,
												height : 400,
												title:"添加明细",
												html:html,
												callback : function(action){
													if(action == 'ok'){
														if($('#orderItemForm').validationEngine('validate')){
															$.post('${vix}/purchase/purchaseOrderAction!saveOrUpdatePurchaseOrderLineItem.action', {
																	'id' : $("#id").val(),
																	'purchaseOrderLineItem.id' : $("#oiId").val(),
																	'purchaseOrderLineItem.itemCode' : $("#newItemCode").val(),
																	'purchaseOrderLineItem.itemName' : $("#newItemName").val(),
																	'purchaseOrderLineItem.specification' : $("#newSpecification").val(),
																	'purchaseOrderLineItem.itemType' : $("#newItemType").val(),
																	'purchaseOrderLineItem.price' : $("#newPrice").val(),
																	'purchaseOrderLineItem.amount' : $("#newAmount").val(),
																	'purchaseOrderLineItem.unit' : $("#newUnit").val(),
																	'purchaseOrderLineItem.netTotal' : $("#newNetTotal").val(),
																	'purchaseOrderLineItem.recieveAddress' : $("#newRecieveAddress").find("option:selected").text(),
																	'purchaseOrderLineItem.recieveAddressCode' : $("#newRecieveAddressCode").text()
																	}, function(result){
																		showMessage(result);
																		setTimeout("",1000);
																		$('#dlLineItem').datagrid("reload");
																	$.ajax({
																			  url:'${vix}/purchase/purchaseOrderAction!getOrderItemTotal.action?id='+$("#id").val(),
																			  cache: false,
																			  success: function(json){
																				  $("#orderAmount").val(json);
																			  }
																		});
																	});
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
							function editItem(){
								var rows = $('#dlLineItem').datagrid('getSelected');
								if(null == rows){
									alert("请选择行！");
									return;
								}
								$.ajax({
									  url:'${vix}/purchase/purchaseOrderAction!goAddPurchaseOrderLineItem.action?lineItemId='+rows.id+"&supplierId="+$("#supplierId").val(),
									  cache: false,
									  success: function(html){
										  asyncbox.open({
											 	modal:true,
												width : 700,
												height : 400,
												title:"添加明细",
												html:html,
												callback : function(action){
													if(action == 'ok'){
														if($('#orderItemForm').validationEngine('validate')){
															$.post('${vix}/purchase/purchaseOrderAction!saveOrUpdatePurchaseOrderLineItem.action', {
																	'id' : $("#id").val(),
																	'purchaseOrderLineItem.id' : $("#oiId").val(),
																	'purchaseOrderLineItem.itemCode' : $("#newItemCode").val(),
																	'purchaseOrderLineItem.itemName' : $("#newItemName").val(),
																	'purchaseOrderLineItem.specification' : $("#newSpecification").val(),
																	'purchaseOrderLineItem.itemType' : $("#newItemType").val(),
																	'purchaseOrderLineItem.price' : $("#newPrice").val(),
																	'purchaseOrderLineItem.amount' : $("#newAmount").val(),
																	'purchaseOrderLineItem.unit' : $("#newUnit").val(),
																	'purchaseOrderLineItem.netTotal' : $("#newNetTotal").val(),
																	'purchaseOrderLineItem.recieveAddress' : $("#newRecieveAddress").find("option:selected").text(),
																	'purchaseOrderLineItem.recieveAddressCode' : $("#newRecieveAddressCode").text()
																	}, function(result){
																		showMessage(result);
																		setTimeout("",1000);
																		$('#dlLineItem').datagrid("reload");
																	$.ajax({
																			  url:'${vix}/purchase/purchaseOrderAction!getOrderItemTotal.action?id='+$("#id").val(),
																			  cache: false,
																			  success: function(json){
																				  $("#orderAmount").val(json);
																			  }
																		});
																	});
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
							function removeItem(){
								var row = $('#dlLineItem').datagrid('getSelected');
								if(row){
									var index = $('#dlLineItem').datagrid('getRowIndex',row);
									$('#dlLineItem').datagrid('deleteRow', index);
								}
							}
					</script>
						</div>
					</div>
					<div id="a2" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<div style="padding: 8px;">
							<table id="dlReceivedAddress" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlReceivedAddressTb',url: '${vix}/purchase/purchaseOrderAction!getReceivedAddressJson.action?id=${purchaseOrder.id}',onClickRow: onDlClickRow2">
								<thead>
									<tr>
										<th data-options="field:'id',align:'center',width:120">编号</th>
										<th data-options="field:'code',width:200,align:'center',editor:'text'">地址代码</th>
										<th data-options="field:'country',width:200,align:'center',editor:'text'">国家</th>
										<th data-options="field:'provience',width:200,align:'center',editor:'text'">省</th>
										<th data-options="field:'city',width:200,align:'center',editor:'text'">市</th>
										<th data-options="field:'street',width:200,align:'center',editor:'text'">街道</th>
									</tr>
								</thead>
							</table>
							<div id="dlReceivedAddressTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="appendDlReceivedAddress()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)"
									class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-remove',plain:true" onclick="removeDlReceivedAddress()"><span class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
									data-options="iconCls:'icon-save',plain:true" onclick="saveDlReceivedAddress()"><span class="l-btn-left"><span class="l-btn-text icon-save l-btn-icon-left"><s:text name="cmn_save" /></span></span></a>
							</div>
							<script type="text/javascript">
							$('#dlReceivedAddress').datagrid();
							var editIndexDlReceivedAddress = undefined;
							function endDlEditing2(){
								if (editIndexDlReceivedAddress == undefined){return true;}
								if ($('#dlReceivedAddress').datagrid('validateRow', editIndexDlReceivedAddress)){
									$('#dlReceivedAddress').datagrid('endEdit', editIndexDlReceivedAddress);
									editIndexDlReceivedAddress = undefined;
									return true;
								} else {
									return false;
								}
							}
							function onDlClickRow2(index){
								if (editIndexDlReceivedAddress != index){
									if (endDlEditing2()){
										$('#dlReceivedAddress').datagrid('selectRow', index).datagrid('beginEdit', index);
										editIndexDlReceivedAddress = index;
									} else {
										$('#dlReceivedAddress').datagrid('selectRow', editIndexDlReceivedAddress);
									}
								}
							}
							function appendDlReceivedAddress(){
								if (endDlEditing2()){
									$('#dlReceivedAddress').datagrid('appendRow',{status:'P'});
									editIndexDlReceivedAddress = $('#dlReceivedAddress').datagrid('getRows').length-1;
									$('#dlReceivedAddress').datagrid('selectRow', editIndexDlReceivedAddress).datagrid('beginEdit', editIndexDlReceivedAddress);
								}
							}
							function removeDlReceivedAddress(){
								if (editIndexDlReceivedAddress == undefined){return;}
								$('#dlReceivedAddress').datagrid('cancelEdit', editIndexDlReceivedAddress)
										.datagrid('deleteRow', editIndexDlReceivedAddress);
								editIndexDlReceivedAddress = undefined;
							}
							function saveDlReceivedAddress(){
								if (endDlEditing2()){
									$('#dlReceivedAddress').datagrid('acceptChanges');
								}
							}
					</script>
						</div>
					</div>
					<div id="a3" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<div style="padding: 8px;">
							<table id="dlDeliveryPlan" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlDeliveryPlanTb',url: '${vix}/purchase/purchaseOrderAction!getDeliveryPlanJson.action?id=${purchaseOrder.id}',onClickRow: onDlClickRow3">
								<thead>
									<tr>
										<th data-options="field:'id',align:'center',width:120">编号</th>
										<th data-options="field:'itemCode',width:200,align:'center',editor:'text'">${vv:varView("vix_mdm_item")}编码</th>
										<th data-options="field:'itemName',width:200,align:'center',editor:'text'">${vv:varView("vix_mdm_item")}名称</th>
										<th data-options="field:'amount',width:200,align:'center',editor:'text'">订货数量</th>
										<th data-options="field:'recievePointRef',width:200,align:'center',editor:'text'">到货点</th>
										<th data-options="field:'deliver',width:200,align:'center',editor:'text'">发运人</th>
										<th data-options="field:'startTime',width:200,align:'center',editor:'datebox'">发运时间</th>
									</tr>
								</thead>
							</table>
							<div id="dlDeliveryPlanTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="appendDlDeliveryPlan()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)"
									class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-remove',plain:true" onclick="removeDlDeliveryPlan()"><span class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
									data-options="iconCls:'icon-save',plain:true" onclick="saveDlDeliveryPlan()"><span class="l-btn-left"><span class="l-btn-text icon-save l-btn-icon-left"><s:text name="cmn_save" /></span></span></a>
							</div>
							<script type="text/javascript">
							$('#dlDeliveryPlan').datagrid();
							var editIndexDlDeliveryPlan = undefined;
							function endDlEditing3(){
								if (editIndexDlDeliveryPlan == undefined){return true;}
								if ($('#dlDeliveryPlan').datagrid('validateRow', editIndexDlDeliveryPlan)){
									$('#dlDeliveryPlan').datagrid('endEdit', editIndexDlDeliveryPlan);
									editIndexDlDeliveryPlan = undefined;
									return true;
								} else {
									return false;
								}
							}
							function onDlClickRow3(index){
								if (editIndexDlDeliveryPlan != index){
									if (endDlEditing3()){
										$('#dlDeliveryPlan').datagrid('selectRow', index).datagrid('beginEdit', index);
										editIndexDlDeliveryPlan = index;
									} else {
										$('#dlDeliveryPlan').datagrid('selectRow', editIndexDlDeliveryPlan);
									}
								}
							}
							function appendDlDeliveryPlan(){
								if (endDlEditing3()){
									$('#dlDeliveryPlan').datagrid('appendRow',{status:'P'});
									editIndexDlDeliveryPlan = $('#dlDeliveryPlan').datagrid('getRows').length-1;
									$('#dlDeliveryPlan').datagrid('selectRow', editIndexDlDeliveryPlan).datagrid('beginEdit', editIndexDlDeliveryPlan);
								}
							}
							function removeDlDeliveryPlan(){
								if (editIndexDlDeliveryPlan == undefined){return;}
								$('#dlDeliveryPlan').datagrid('cancelEdit', editIndexDlDeliveryPlan)
										.datagrid('deleteRow', editIndexDlDeliveryPlan);
								editIndexDlDeliveryPlan = undefined;
							}
							function saveDlDeliveryPlan(){
								if (endDlEditing3()){
									$('#dlDeliveryPlan').datagrid('acceptChanges');
								}
							}
					</script>
						</div>
					</div>
					<div id="a4" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<div style="padding: 8px;">
							<table id="dlInvoice" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlInvoiceTb',url: '${vix}/purchase/purchaseOrderAction!getPurchaseInvoiceJson.action?id=${purchaseOrder.id}',onClickRow: onDlClickRow4">
								<thead>
									<tr>
										<th data-options="field:'id',align:'center',width:120">编号</th>
										<th data-options="field:'name',width:200,align:'center',editor:'text'">名称</th>
										<th data-options="field:'orderType',width:200,align:'center',editor:'text'">单据类型</th>
										<th data-options="field:'currency',width:200,align:'center',editor:'text'">币种</th>
										<th data-options="field:'totalAmount',width:200,align:'center',editor:'text'">金额</th>
										<th data-options="field:'taxRate',width:200,align:'center',editor:'text'">税率</th>
										<th data-options="field:'supplierName',width:200,align:'center',editor:'text'">供应商</th>
										<th data-options="field:'purchasePerson',width:200,align:'center',editor:'text'">采购人</th>
										<th data-options="field:'deliveryDate',width:200,align:'center',editor:'datebox'">交货日期</th>
									</tr>
								</thead>
							</table>
							<div id="dlInvoiceTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="appendDlInvoice()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
									data-options="iconCls:'icon-remove',plain:true" onclick="removeDlInvoice()"><span class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-save',plain:true"
									onclick="saveDlInvoice()"><span class="l-btn-left"><span class="l-btn-text icon-save l-btn-icon-left"><s:text name="cmn_save" /></span></span></a>
							</div>
							<script type="text/javascript">
							$('#dlInvoice').datagrid();
							var editIndexDlInvoice = undefined;
							function endDlEditing4(){
								if (editIndexDlInvoice == undefined){return true;}
								if ($('#dlInvoice').datagrid('validateRow', editIndexDlInvoice)){
									$('#dlInvoice').datagrid('endEdit', editIndexDlInvoice);
									editIndexDlInvoice = undefined;
									return true;
								} else {
									return false;
								}
							}
							function onDlClickRow4(index){
								if (editIndexDlInvoice != index){
									if (endDlEditing4()){
										$('#dlInvoice').datagrid('selectRow', index).datagrid('beginEdit', index);
										editIndexDlInvoice = index;
									} else {
										$('#dlInvoice').datagrid('selectRow', editIndexDlInvoice);
									}
								}
							}
							function appendDlInvoice(){
								if (endDlEditing4()){
									$('#dlInvoice').datagrid('appendRow',{status:'P'});
									editIndexDlInvoice = $('#dlInvoice').datagrid('getRows').length-1;
									$('#dlInvoice').datagrid('selectRow', editIndexDlInvoice).datagrid('beginEdit', editIndexDlInvoice);
								}
							}
							function removeDlInvoice(){
								if (editIndexDlInvoice == undefined){return;}
								$('#dlInvoice').datagrid('cancelEdit', editIndexDlInvoice)
										.datagrid('deleteRow', editIndexDlInvoice);
								editIndexDlInvoice = undefined;
							}
							function saveDlInvoice(){
								if (endDlEditing4()){
									$('#dlInvoice').datagrid('acceptChanges');
								}
							}
					</script>
						</div>
					</div>
					<div id="a5" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<div style="padding: 8px;">
							<table id="dlPriceConditions" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlPriceConditionsTb',url: '${vix}/purchase/purchaseOrderAction!getPriceConditionsJson.action?id=${purchaseOrder.id}',onClickRow: onDlClickRow5">
								<thead>
									<tr>
										<th data-options="field:'id',align:'center',width:120">编号</th>
										<th data-options="field:'itemCode',width:200,align:'center',editor:'text'">${vv:varView("vix_mdm_item")}编码</th>
										<th data-options="field:'itemName',width:200,align:'center',editor:'text'">${vv:varView("vix_mdm_item")}名称</th>
										<th data-options="field:'itemType',width:200,align:'center',editor:'text'">${vv:varView("vix_mdm_item")}类型</th>
										<th data-options="field:'amount',width:200,align:'center',editor:'numberbox'">订货数量</th>
										<th data-options="field:'netTotal',width:200,align:'center',editor:'numberbox'">金额</th>
										<th data-options="field:'coin',width:200,align:'center',editor:'text'">货币</th>
										<th data-options="field:'unit',width:200,align:'center',editor:'text'">单位</th>
									</tr>
								</thead>
							</table>
							<div id="dlPriceConditionsTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="appendDlPriceConditions()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)"
									class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-remove',plain:true" onclick="removeDlPriceConditions()"><span class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
									data-options="iconCls:'icon-save',plain:true" onclick="saveDlPriceConditions()"><span class="l-btn-left"><span class="l-btn-text icon-save l-btn-icon-left"><s:text name="cmn_save" /></span></span></a>
							</div>
							<script type="text/javascript">
							$('#dlPriceConditions').datagrid();
							var editIndexDlPriceConditions = undefined;
							function endDlEditing5(){
								if (editIndexDlPriceConditions == undefined){return true;}
								if ($('#dlPriceConditions').datagrid('validateRow', editIndexDlPriceConditions)){
									$('#dlPriceConditions').datagrid('endEdit', editIndexDlPriceConditions);
									editIndexDlPriceConditions = undefined;
									return true;
								} else {
									return false;
								}
							}
							function onDlClickRow5(index){
								if (editIndexDlPriceConditions != index){
									if (endDlEditing5()){
										$('#dlPriceConditions').datagrid('selectRow', index).datagrid('beginEdit', index);
										editIndexDlPriceConditions = index;
									} else {
										$('#dlPriceConditions').datagrid('selectRow', editIndexDlPriceConditions);
									}
								}
							}
							function appendDlPriceConditions(){
								if (endDlEditing5()){
									$('#dlPriceConditions').datagrid('appendRow',{status:'P'});
									editIndexDlPriceConditions = $('#dlPriceConditions').datagrid('getRows').length-1;
									$('#dlPriceConditions').datagrid('selectRow', editIndexDlPriceConditions).datagrid('beginEdit', editIndexDlPriceConditions);
								}
							}
							function removeDlPriceConditions(){
								if (editIndexDlPriceConditions == undefined){return;}
								$('#dlPriceConditions').datagrid('cancelEdit', editIndexDlPriceConditions)
										.datagrid('deleteRow', editIndexDlPriceConditions);
								editIndexDlPriceConditions = undefined;
							}
							function saveDlPriceConditions(){
								if (endDlEditing5()){
									$('#dlPriceConditions').datagrid('acceptChanges');
								}
							}
					</script>
						</div>
					</div>
					<div id="a6" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<script type="text/javascript">
					$('#soAttach').datagrid({
						url: '${vix}/purchase/purchaseOrderAction!getAttachmentsJson.action?id=${purchaseOrder.id}',
						title: '订单附件',
						width: 900,
						height: '450',
						fitColumns: true,
						columns:[[
							{field:'id',title:'编号',width:80},
							{field:'name',title:'名称',width:180},
						]],
						toolbar:[{
							id:'saBtnadd',
							text:'添加',
							iconCls:'icon-add',
							handler:function(){
								$('#btnsave').linkbutton('enable');
								$.ajax({
									  url:'${vix}/purchase/purchaseOrderAction!addAttachments.action',
									  cache: false,
									  success: function(html){
										  asyncbox.open({
											 	modal:true,
												width : 364,
												height : 160,
												title:"上传附件",
												html:html,
												callback : function(action,returnValue){
													if(action == 'ok'){
														uploadFile('${vix}/purchase/purchaseOrderAction!uploadAttachments.action?id=${purchaseOrder.id}','fileToUpload');
														$('#soAttach').datagrid("reload");
													}
												},
												btnsbar : $.btn.OKCANCEL
											});
									  }
								});
							}
						},'-',{
							text:'删除',
							iconCls:'icon-remove',
							handler:function(){
								var rows = $('#soAttach').datagrid("getSelections");	//获取你选择的所有行	
								//循环所选的行
								for(var i =0;i<rows.length;i++){
									var index = $('#soAttach').datagrid('getRowIndex',rows[i]);//获取某行的行号
									$('#soAttach').datagrid('deleteRow',index);	//通过行号移除该行
									$.ajax({url:'${vix}/purchase/purchaseOrderAction!deleteAttachments.action?afId='+rows[i].id,cache: false});
								}
							}
						}]
					});
				</script>
						<div style="padding: 8px;">
							<table id="soAttach"></table>
						</div>
					</div>
					<div id="a7" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<div style="padding: 8px;">
							<table id="dlApprovalOpinion" class="easyui-datagrid" style="height: 450px; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlApprovalOpinionTb',url: '${vix}/purchase/purchaseOrderAction!getApprovalOpinionJson.action?id=${purchaseOrder.id}',onClickRow: onDlClickRow7">
								<thead>
									<tr>
										<th data-options="field:'id',align:'center',width:120">编号</th>
										<th data-options="field:'checkPerson',width:200,align:'center',editor:'text'">审批人</th>
										<th data-options="field:'content',width:200,align:'center',editor:'text'">审批意见</th>
										<th data-options="field:'createTime',width:200,align:'center',editor:'datebox'">审批日期</th>
									</tr>
								</thead>
							</table>
							<div id="dlApprovalOpinionTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="appendDlApprovalOpinion()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)"
									class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-remove',plain:true" onclick="removeDlApprovalOpinion()"><span class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
									data-options="iconCls:'icon-save',plain:true" onclick="saveDlApprovalOpinion()"><span class="l-btn-left"><span class="l-btn-text icon-save l-btn-icon-left"><s:text name="cmn_save" /></span></span></a>
							</div>
							<script type="text/javascript">
							$('#dlApprovalOpinion').datagrid();
							var editIndexDlApprovalOpinion = undefined;
							function endDlEditing7(){
								if (editIndexDlApprovalOpinion == undefined){return true;}
								if ($('#dlApprovalOpinion').datagrid('validateRow', editIndexDlApprovalOpinion)){
									$('#dlApprovalOpinion').datagrid('endEdit', editIndexDlApprovalOpinion);
									editIndexDlApprovalOpinion = undefined;
									return true;
								} else {
									return false;
								}
							}
							function onDlClickRow7(index){
								if (editIndexDlApprovalOpinion != index){
									if (endDlEditing7()){
										$('#dlApprovalOpinion').datagrid('selectRow', index).datagrid('beginEdit', index);
										editIndexDlApprovalOpinion = index;
									} else {
										$('#dlApprovalOpinion').datagrid('selectRow', editIndexDlApprovalOpinion);
									}
								}
							}
							function appendDlApprovalOpinion(){
								if (endDlEditing7()){
									$('#dlApprovalOpinion').datagrid('appendRow',{status:'P'});
									editIndexDlApprovalOpinion = $('#dlApprovalOpinion').datagrid('getRows').length-1;
									$('#dlApprovalOpinion').datagrid('selectRow', editIndexDlApprovalOpinion).datagrid('beginEdit', editIndexDlApprovalOpinion);
								}
							}
							function removeDlApprovalOpinion(){
								if (editIndexDlApprovalOpinion == undefined){return;}
								$('#dlApprovalOpinion').datagrid('cancelEdit', editIndexDlApprovalOpinion)
										.datagrid('deleteRow', editIndexDlApprovalOpinion);
								editIndexDlInvoice = undefined;
							}
							function saveDlApprovalOpinion(){
								if (endDlEditing7()){
									$('#dlApprovalOpinion').datagrid('acceptChanges');
								}
							}
					</script>
						</div>
					</div>
				</div>
				<dd class="clearfix">
					<div class="order_table">
						<table>
							<tr>
								<td align="right">小计：</td>
								<td><input id="subtotal" name="subtotal" value="${purchaseOrder.subtotal }" type="text" size="30" onchange="fieldChanged(this);" /></td>
								<td align="right">运费：</td>
								<td><input id="shipping" name="shipping" value="${purchaseOrder.shipping }" type="text" size="30" onchange="fieldChanged(this);" /></td>
								<td align="right">税率：</td>
								<td><input id="taxRate" name="taxRate" value="${purchaseOrder.taxRate }" type="text" size="30" onchange="fieldChanged(this);" /></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td align="right">税额：</td>
								<td><input id="tax" name="tax" value="${purchaseOrder.tax }" type="text" size="30" onchange="fieldChanged(this);" /></td>
								<th width="12%">总金额：</th>
								<td width="12%"><input id="orderAmount" name="orderAmount" value="${purchaseOrder.totalAmount }" type="text" size="30" onchange="fieldChanged(this);" /></td>
							</tr>
						</table>
					</div>
				</dd>
			</dl>
		</div>
	</form>
</div>