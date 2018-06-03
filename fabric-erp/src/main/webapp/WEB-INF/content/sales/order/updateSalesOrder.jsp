<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<link href="${vix}/common/css/newservice.css" rel="stylesheet" />
<script src="${vix}/common/js/ajaxfileupload.js" type="text/javascript"></script>
<script type="text/javascript">

	/** input 获取焦点input效果绑定  */
	$(".order_table input[type='text']").focusin( function() {
	   $(this).css({'border':'1px solid #f00','background':'#f5f5f5'});
	});
	$(".order_table  input[type='text']").focusout( function() {
	   $(this).css({'border':'1px solid #ccc','background':'#fff'});
	});
 
/** 保存订单 */
function saveOrUpdateOrder(tag){
	var orderItemStr = "";
	/** 发运计划 */
	var dpData = $("#deliveryPlan").datagrid("getRows");
	var dpJson = JSON.stringify(dpData);
	if($('#order').validationEngine('validate')){
		$.post('${vix}/sales/order/salesOrderAction!saveOrUpdate.action',
			{'salesOrder.id':$("#id").val(),
			  'salesOrder.code':$("#code").val(),
			  'salesOrder.groupCode':$("#groupCode").val(),
			  'salesOrder.title':$("#title").val(),
			  'salesOrder.billDate':$("#bdString").val(),
			  'salesOrder.status':$(":radio[name=status][checked]").val(),
			  'salesOrder.customerAccount.id':$("#customerAccountId").val(),
			  'salesOrder.crmProject.id':$("#crmProjectId").val(),
			  'salesOrder.currencyType.id':$("#currencyTypeId").val(),
			  'salesOrder.regional.id':$("#regionalId").val(),
			  'salesOrder.contactPerson.id':$("#contactPersonId").val(),
			  'salesOrder.bizType':$("#bizType").val(),
			  'salesOrder.currency':$("#currency").val(),
			  'salesOrder.salePerson.id':$("#salePersonId").val(),
			  'salesOrder.saleOrg.id':$("#saleOrgId").val(),
			  'salesOrder.deliveryTimeInPlan':$("#deliveryTimeInPlan").val(),
			  'salesOrder.promiseTime':$("#promiseTime").val(),
			  'salesOrder.postingTime':$("#postingTime").val(),
			  'salesOrder.amount':$("#amount").val(),
			  'salesOrder.freight':$("#freight").val(),
			  'salesOrder.taxRate':$("#taxRate").val(),
			  'salesOrder.tax':$("#tax").val(),
			  'salesOrder.amountTotal':$("#amountTotal").val(),
			  'salesOrder.isDeleted':$("#isDeleted").val(),
			  'salesOrder.memo':$("#memo").val(),
			  'salesOrder.deliveryTime':$("#deliveryTime").val(),
			  'salesOrder.startTime':$("#startTime").val(),
			  'salesOrder.endTime':$("#endTime").val(),
			  'approval':tag
			},
			function(result){
				showMessage(result);
				setTimeout("",1000);
				loadContent('${vix}/sales/order/salesOrderAction!goList.action?menuId=menuOrder');
			}
		);
	}
}

function approvalSalesOrder(){
	asyncbox.confirm('订单提交审批后,将不可修改,确定要审批订单吗?','提示信息',function(action){
		if(action == 'ok'){
			saveOrUpdateOrder('approval');
		}
	});
}
$("#order").validationEngine();
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
var updateField = "";
function salesOrderFieldChanged(input){
	updateField+= $(input).attr("id")+",";
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
								$("#customerAccountId").val(result[0].replace(',',''));
								$("#customerName").val(result[1].replace(',',''));
								loadContactPerson();
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

function addCustomerAccount(){
	$.ajax({
		  url:'${vix}/mdm/crm/customerAccountAction!goFastAddCustomerAccount.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 760,
					height : 280,
					title:"添加客户",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if($(":radio[name=customerType][checked]").val() == 'personal'){
								saveOrUpdatePersonalCustomerAccount();
							}else{
								saveOrUpdateEnterPriceCustomerAccount();
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}

function chooseSaleOrg(){
	$.ajax({
		  url:'${vix}/common/select/commonSelectOrgUnitAction!goChooseOrgUnit.action?canCheckComp=0',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 560,
					height : 340,
					title:"选择销售组织",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var resObj = $.parseJSON(returnValue);
								var uId = resObj[0].realId;
								$("#saleOrgId").val(uId);
								$("#saleOrgName").val(resObj[0].name);
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}

function chooseSalesQuotation(){
	$.ajax({
		  url:'${vix}/sales/quotes/salesQuotationAction!goChooseSalesQuotation.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 960,
					height : 500,
					title:"选择报价单",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if (returnValue != '') {
			                    $.ajax({
			                    	url : '${vix}/sales/order/salesOrderAction!convertSalesQuotationToSaleOrder.action?id=${salesOrder.id}&salesQuotationId='+returnValue,
			                    	cache : false,
			                    	success : function(result) {
				                    	showMessage(result);
				                    	setTimeout("", 1000);
				                    	$('#item').datagrid("reload");
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
$(".newvoucher dt b").click(function(){
	$(this).toggleClass("downup");
	$(this).parent("dt").next("dd").toggle();
});
function chooseEmployee(){
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
								var result = returnValue.split(":");
								$("#salePersonId").val(result[0].replace(',',''));
								$("#salePersonName").val(result[1].replace(',',''));
							}else{
								$("#salePersonId").val("");
								$("#salePersonName").val("");
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
function chooseCrmProject(){
	$.ajax({
		  url:'${vix}/crm/project/crmProjectAction!goChooseCrmProject.action',
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
				 	width : 960,
					height : 500,
					title:"选择项目",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var result = returnValue.split(":");
								$("#crmProjectId").val(result[0]);
								$("#crmProjectName").val(result[1]);
							}else{
								$("#crmProjectId").val("");
								$("#crmProjectName").val("");
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
function showPrice(itemId,count,customerAccount){
	var bdString = $("#bdString").val();
	var bcdDate =  $("#billDate").val() + " " + bdString.split(" ")[1];
	$.ajax({
		  url:'${vix}/mdm/item/saleItemPriceAction!goFixedPrice.action?id='+itemId+"&count="+count+"&billCreateDate="+bcdDate+"&customerAccountId="+customerAccount+"&regionalId="+$("#regionalId").val(),
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 960,
					height : 580,
					title:"定价",
					html:html,
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
function loadContactPerson(){
	$.ajax({
		  url:"${vix}/sales/order/salesOrderAction!loadCustomerContactPerson.action?id="+$("#id").val()+"&customerAccountId="+$("#customerAccountId").val(),
		  cache: false,
		  success: function(html){
			  $("#contactPersonId").html(html);
		  }
	});
}
loadContactPerson();

function textChanged(){
	var bc = $("#barCode").val();
	if($("#selectOption").val() == '2' && bc != ''){
		$.post('${vix}/sales/order/salesOrderAction!saveOrUpdateOrderItem.action',
				{
				  'barCode':bc,
				  'salesOrder.id':$("#id").val()
				},
				function(result){
					showMessage(result);
					setTimeout("",1000);
					$("#barCode").val('');
					$('#item').datagrid("reload");
				}
			);
	}
}

function barCodeEnter(event){
	var bc = $("#barCode").val();
	if($("#selectOption").val() == '1' && event.keyCode == 13 && bc != ''){
		$.post('${vix}/sales/order/salesOrderAction!saveOrUpdateOrderItem.action',
				{
				  'barCode':bc,
				  'salesOrder.id':$("#id").val()
				},
				function(result){
					showMessage(result);
					$("#barCode").val('');
					setTimeout("",1000);
					$('#item').datagrid("reload");
				}
			);
	}
}
function relatedSalesQuotation(){
	$.ajax({
		  url:"${vix}/sales/quotes/salesQuotationAction!goRelatedSalesQuotation.action?id="+$("#id").val()+"&type=salesOrder",
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 960,
					height : 500,
					title:"选择报价单",
					html:html,
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
//-->
</script>
<div class="sub_menu">
	<h2>
		<i> <a href="#"><img alt="" src="img/icon_14.gif">
			<s:text name="print" /></a> <a href="#"><img alt="" src="img/icon_15.gif">
			<s:text name="help" /></a>
		</i>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img src="${vix}/common/img/sale/saleOrder.png" alt="" />
					<s:text name="cmn_home" /></a></li>
				<li><a href="#">供应链</a></li>
				<li><a href="#">销售管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/sales/order/salesOrderAction!goList.action');">销售订单</a></li>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${salesOrder.id}" />
<input type="hidden" id="isDeleted" name="isDeleted" value="${salesOrder.isDeleted}" />
<input type="hidden" id="groupCode" name="groupCode" value="${salesOrder.groupCode}" />
<div class="content">
	<form id="order">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<span class="no_line"> <a onclick="saveOrUpdateOrder();" href="###"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <a href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <s:if test="isAllowAudit == 1">
							<a onclick="approvalSalesOrder();" href="###"><img width="22" height="22" alt="保存并提交审批" src="${vix}/common/img/dt_aprroval.png" /></a>
						</s:if>
						<div class="ms">
							<a href="#"><img width="22" height="22" alt="" src="img/icon_add.gif"></a>
							<ul style="display: none;">
								<li><a href="###" onclick="relatedSalesQuotation();"><img style="width: 14px; height: 14px;" src="${vix}/common/img/sale/saleQuotes.png" alt="">销售报价单</a></li>
								<li><a href="###" class=""><img src="img/icon_10.png" alt="">销售发货单</a></li>
							</ul>
						</div> <a href="#" onclick="loadContent('${vix}/sales/order/salesOrderAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" alt="返回" src="${vix}/common/img/dt_goback.png" /></a>
					</span> <strong> <b> <s:if test="salesOrder.code != null ">
							${salesOrder.title}
						</s:if> <s:else>新增订单</s:else>
					</b> <i></i>
					</strong>
				</dt>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dt>
									<b></b> <strong>基本信息</strong>
								</dt>
								<dd style="display: block;">
									<table class="addtable_c padding: 30px;">
										<tr>
											<td align="right" width="15%"></td>
											<td width="35%"></td>
											<td align="right" width="15%"></td>
											<td width="35%"></td>
										</tr>
										<tr>
											<td align="right" width="15%">编码:</td>
											<td width="35%"><input id="code" name="code" value="${salesOrder.code}" onchange="salesOrderFieldChanged(this);" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right" width="10%">主题:</td>
											<td width="40%"><input id="title" name="title" value="${salesOrder.title}" onchange="salesOrderFieldChanged(this);" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">单据日期:</td>
											<td><input type="hidden" id="bdString" value="<s:property value='formatDateToTimeString(salesOrder.billDate)'/>" /> <input id="billDate" name="billDate" value="<s:property value='formatDateToString(salesOrder.billDate)'/>" onfocus="showTime('billDate','yyyy-MM-dd')" onchange="salesOrderFieldChanged(this);" type="text" size="30"
												class="validate[required] text-input" /><span style="color: red;">*</span><img onclick="showTime('billDate','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
											<s:if test="tag == 'fromQuote'">
												<td align="right">来源单据:</td>
												<td><span>销售报价单</span> <a class="abtn" href="#" onclick="chooseSalesQuotation();"><span>取单</span></a></td>
											</s:if>
											<s:else>
												<td align="right">来源单据:</td>
												<td>无</td>
											</s:else>
										</tr>
										<tr>
											<td align="right" width="10%">客户名称:</td>
											<td width="40%"><input id="customerName" name="salesOrder.customerAccount.name" value="${salesOrder.customerAccount.name}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span> <input type="hidden" id="customerAccountId" name="customerAccountId" value="${salesOrder.customerAccount.id}" /> <span><a
													class="abtn" href="#" onclick="chooseCustomerAccount();"><span>选择</span></a></span> <span><a class="abtn" href="#" onclick="addCustomerAccount();"><span>新增</span></a></span></td>
											<td align="right">联系人:</td>
											<td><select id="contactPersonId"></select></td>
										</tr>
										<tr>
											<td align="right">项目:</td>
											<td><s:hidden id="crmProjectId" name="salesOrder.crmProject.id" value="%{salesOrder.crmProject.id}" theme="simple" /> <input type="text" id="crmProjectName" name="salesOrder.crmProject.name" value="${salesOrder.crmProject.subject}" readonly="readonly" size="30" /> <a href="###" onclick="chooseCrmProject();" class="abtn"><span>选择</span></a>
											</td>
											<td align="right">业务类型:</td>
											<td><s:select id="bizTypeId" headerKey="-1" headerValue="--请选择--" list="%{billsTypeList}" listValue="typeName" listKey="typeCode" value="%{salesOrder.bizType}" multiple="false" theme="simple"></s:select></td>
										</tr>
										<tr>
											<td align="right">币种:</td>
											<td><s:select id="currencyTypeId" headerKey="-1" headerValue="--请选择--" list="%{currencyTypeList}" listValue="name" listKey="id" value="%{salesOrder.currencyType.id}" multiple="false" theme="simple"></s:select></td>
											<td align="right">地域:</td>
											<td><s:select id="regionalId" list="%{regionalList}" headerKey="-1" headerValue="--请选择--" listValue="name" listKey="id" value="%{salesOrder.regional.id}" multiple="false" theme="simple"></s:select></td>
										</tr>
										<tr>
											<td align="right">销售组织:</td>
											<td><input type="hidden" id="saleOrgId" value="${salesOrder.saleOrg.id}" /> <input id="saleOrgName" name="salesOrder.saleOrg.fullName" value="${salesOrder.saleOrg.fullName}" type="text" size="30" /> <a class="abtn" href="#" onclick="chooseSaleOrg();"><span>选择</span></a></td>
											<td align="right">计划发运日期:</td>
											<td><input id="deliveryTimeInPlan" name="deliveryTimeInPlan" value="<s:property value='formatDateToTimeString(salesOrder.deliveryTimeInPlan)'/>" onfocus="showTime('deliveryTimeInPlan','yyyy-MM-dd HH:mm')" onchange="salesOrderFieldChanged(this);" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span><img
												onclick="showTime('deliveryTimeInPlan','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<td align="right">承诺日期:</td>
											<td><input id="promiseTime" name="promiseTime" value="<s:property value='formatDateToTimeString(salesOrder.promiseTime)'/>" onfocus="showTime('promiseTime','yyyy-MM-dd HH:mm')" onchange="salesOrderFieldChanged(this);" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span><img
												onclick="showTime('promiseTime','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
											<td align="right">过账日期:</td>
											<td><input id="postingTime" name="postingTime" value="<s:property value='formatDateToTimeString(salesOrder.postingTime)'/>" onfocus="showTime('postingTime','yyyy-MM-dd HH:mm')" onchange="salesOrderFieldChanged(this);" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span><img
												onclick="showTime('postingTime','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<td align="right">状态:</td>
											<td><s:if test="salesOrder.status == 0">
													<input type="radio" id="status1" name="status" value="1" />激活
												<input type="radio" id="status2" name="status" value="0" checked="checked" />禁用
											</s:if> <s:elseif test="salesOrder.status == 1">
													<input type="radio" id="status1" name="status" value="1" checked="checked" />激活
												<input type="radio" id="status2" name="status" value="0" />禁用
											</s:elseif> <s:else>
													<input type="radio" id="status1" name="status" value="1" checked="checked" />激活
												<input type="radio" id="status2" name="status" value="0" />禁用
											</s:else></td>
											<td align="right">业务员:</td>
											<td><input type="hidden" id="salePersonId" value="${salesOrder.salePerson.id}" /> <input id="salePersonName" name="salesOrder.salePerson.name" value="${salesOrder.salePerson.name}" type="text" size="30" /> <a class="abtn" href="#" onclick="chooseEmployee();"><span>选择</span></a></td>
										</tr>
										<tr>
											<td align="right">开始日期:</td>
											<td><input id="startTime" name="startTime" value="<s:property value='formatDateToTimeString(salesOrder.startTime)'/>" onfocus="showTime('startTime','yyyy-MM-dd HH:mm')" onchange="salesOrderFieldChanged(this);" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span><img
												onclick="showTime('startTime','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
											<td align="right">结束日期:</td>
											<td><input id="endTime" name="endTime" value="<s:property value='formatDateToTimeString(salesOrder.endTime)'/>" onfocus="showTime('endTime','yyyy-MM-dd HH:mm')" onchange="salesOrderFieldChanged(this);" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span><img
												onclick="showTime('endTime','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<td align="right">备注</td>
											<td colspan="3"><textarea id="memo" rows="3" cols="70" style="font-size: 12px;">${salesOrder.memo}</textarea></td>
										</tr>
										<tr>
											<td align="right" width="15%"></td>
											<td width="35%"></td>
											<td align="right" width="15%"></td>
											<td width="35%"></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
					</div>
				</dd>
				<dd class="clearfix">
					<div class="order_table">
						<div class="voucher newvoucher">
							<dl>
								<dd style="display: block;">
									<table style="border: none;">
										<tr>
											<th>录入方式选择</th>
											<td><select id="selectOption" name="selectOption" class="validate[required] text-input">
													<option value="1">手动录入</option>
													<option value="2">扫描录入</option>
											</select></td>
											<th>商品编码：</th>
											<td><input id="barCode" name="barCode" type="text" onchange="textChanged();" onkeypress="barCodeEnter(event);" /></td>
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
							<li class="current"><a onclick="javascript:$('#a1').attr('style','');tab(4,1,'a',event);"><img src="${vix}/common/img/mail.png" alt="" />明细</a></li>
							<li><a onclick="javascript:$('#a2').attr('style','');tab(4,2,'a',event);"><img src="${vix}/common/img/mail.png" alt="" />发运计划</a></li>
							<li><a onclick="javascript:$('#a3').attr('style','');tab(4,3,'a',event);"><img src="${vix}/common/img/mail.png" alt="" />发票</a></li>
							<li><a onclick="javascript:$('#a4').attr('style','');tab(4,4,'a',event);"><img src="${vix}/common/img/attachment.png" alt="" />附件</a></li>
						</ul>
					</div>
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<div style="padding: 8px;">
							<table id="item" class="easyui-datagrid" style="height: 380px; margin: 6px;" data-options="fitColumns:true,singleSelect: true,toolbar: '#itemTb',url: '${vix}/sales/order/salesOrderAction!getSaleOrderItemJson.action?id=${salesOrder.id}'">
								<thead>
									<tr>
										<th data-options="field:'item.id',align:'center',width:60"></th>
										<th data-options="field:'id',align:'center',width:60">编号</th>
										<th data-options="field:'barCode',width:80,align:'center'">条形码</th>
										<th data-options="field:'skuCode',width:80,align:'center'">SKU</th>
										<th data-options="field:'itemCode',width:80,align:'center'">编码</th>
										<th data-options="field:'itemName',width:120,align:'center'">名称</th>
										<th data-options="field:'specification',width:100,align:'center'">规格型号</th>
										<th data-options="field:'measureUnit',width:80,align:'center'">主计量单位</th>
										<th data-options="field:'amount',width:60,align:'center'">数量</th>
										<th data-options="field:'taxPrice',width:60,align:'center'">含税单价</th>
										<th data-options="field:'price',width:60,align:'center'">金额</th>
										<th data-options="field:'netPrice',width:60,align:'center'">无税单价</th>
										<th data-options="field:'netTotal',width:60,align:'center'">无税金额</th>
										<th data-options="field:'tax',width:60,align:'center'">税率</th>
										<th data-options="field:'discount',width:60,align:'center'">折扣率</th>
										<th data-options="field:'requireDate',width:80,align:'center'">预发货日期</th>
										<th data-options="field:'memo',width:120,align:'center'">备注</th>
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
							$('#item').datagrid();
							$('#item').datagrid('hideColumn','item.id');
							function removeItem(){
								var row = $('#item').datagrid('getSelected');
								if(row){
									asyncbox.confirm('是否删除该订单明细?','提示信息',function(action){
										if(action == 'ok'){
											var index = $('#item').datagrid('getRowIndex',row);
											$('#item').datagrid('deleteRow', index);
											$.ajax({
												  url:'${vix}/sales/order/salesOrderAction!deleteSalesOrderItemById.action?id='+row.id,
												  cache: false,
												  success: function(html){
													  showMessage(html);
													  $.ajax({
														  url:'${vix}/sales/order/salesOrderAction!getOrderItemTotal.action?id='+$("#id").val(),
														  cache: false,
														  success: function(json){
															  $("#amount").val(json);
															  calculateAmount();
														  }
													});
												  }
											});
										}
									});
								}else{
									showMessage("请选择一条数据!");
								}
							}
							function showItemPrice(){
								var row = $('#item').datagrid('getSelected');
								if(row){
									var itemId = row.item.id;
									var count = row.amount;
									var customerAccount = $("#customerAccountId").val();
									showPrice(itemId,count,customerAccount);
								}else{
									showMessage("请选择一条数据!");
								}
							}
							function updateItem(){
								var row = $('#item').datagrid('getSelected');
								if(row){
									addItem(row.id);
								}else{
									showMessage("请选择一条数据!");
								}
							}
							function addItem(id){
								$.ajax({
									  url:'${vix}/sales/order/salesOrderAction!goSaveOrUpdateSaleOrderItem.action?id='+id,
									  cache: false,
									  success: function(html){
										  asyncbox.open({
											 	modal:true,
											 	width : 1000,
												height : 540,
												title:"订单明细",
												html:html,
												callback : function(action){
													if(action == 'cancel' || action == 'close'){
														$('#item').datagrid("reload");
														$.ajax({
															  url:'${vix}/sales/order/salesOrderAction!getOrderItemTotal.action?id='+$("#id").val(),
															  cache: false,
															  success: function(json){
																  $("#amount").val(json);
																  calculateAmount();
															  }
														});
													}
												},
												btnsbar : [{
													text    : '关闭',
													action  : 'cancel'
												}]
											});
									  }
								});
							}
					</script>
						</div>
					</div>
					<div id="a2" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<div style="padding: 8px;">
							<table id="deliveryPlan" class="easyui-datagrid" style="height: 380px; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#deliveryPlanTb',url: '${vix}/sales/order/salesOrderAction!getSalesDeliveryPlanJson.action?id=${salesOrder.id}'">
								<thead>
									<tr>
										<th data-options="field:'id',width:60">编号</th>
										<th data-options="field:'salesOrder.code',width:120,align:'center',editor:'text'">订单编号</th>
										<th data-options="field:'deliveryTime',width:80,align:'center',editor:'datebox'">发运时间</th>
										<th data-options="field:'country',width:90,align:'center',editor:'text'">国家</th>
										<th data-options="field:'province',width:90,align:'center',editor:'text'">省</th>
										<th data-options="field:'city',width:100,align:'center',editor:'text'">城市</th>
										<th data-options="field:'target',width:200,align:'center',editor:'text'">目的地</th>
										<th data-options="field:'carrier',width:100,align:'center',editor:'text'">承运人</th>
										<th data-options="field:'reciever',width:100,align:'center',editor:'text'">收货人</th>
									</tr>
								</thead>
							</table>

							<div id="deliveryPlanTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="addDeliveryPlan(0)"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
									data-options="iconCls:'icon-add',plain:true" onclick="updateDeliveryPlan()"><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left"><s:text name="cmn_update" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-remove',plain:true"
									onclick="removeDeliveryPlan()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a>
							</div>
							<script type="text/javascript">
							$('#deliveryPlan').datagrid();
							function removeDeliveryPlan(){
								var row = $('#deliveryPlan').datagrid('getSelected');
								if(row){
									asyncbox.confirm('是否删除该发运计划?','提示信息',function(action){
										if(action == 'ok'){
											var index = $('#deliveryPlan').datagrid('getRowIndex',row);
											$('#deliveryPlan').datagrid('deleteRow', index);
											$.ajax({
												  url:'${vix}/sales/order/salesDeliveryPlanAction!deleteById.action?id='+row.id,
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
							function updateDeliveryPlan(){
								var row = $('#deliveryPlan').datagrid('getSelected');
								if(row){
									addDeliveryPlan(row.id);
								}else{
									showMessage("请选择一条数据!");
								}
							}
							function addDeliveryPlan(id){
								$.ajax({
									  url:'${vix}/sales/order/salesDeliveryPlanAction!goSaveOrUpdate.action?id='+id,
									  cache: false,
									  success: function(html){
										  asyncbox.open({
											 	modal:true,
												width : 660,
												height :440,
												title:"发运计划",
												html:html,
												callback : function(action){
													if(action == 'ok'){
														if($('#salesDeliveryPlanForm').validationEngine('validate')){
															$.post('${vix}/sales/order/salesDeliveryPlanAction!saveOrUpdate.action',
																	{'salesDeliveryPlan.id':$("#sdpId").val(),
																	  'salesDeliveryPlan.deliveryTime':$("#sdpDeliveryTime").val(),
																	  'salesDeliveryPlan.country':$("#country").val(),
																	  'salesDeliveryPlan.province':$("#province").val(),
																	  'salesDeliveryPlan.city':$("#city").val(),
																	  'salesDeliveryPlan.target':$("#target").val(),
																	  'salesDeliveryPlan.carrier':$("#carrier").val(),
																	  'salesDeliveryPlan.reciever':$("#reciever").val(),
																	  'salesDeliveryPlan.salesOrder.id':$("#id").val()
																	},
																	function(result){
																		showMessage(result);
																		setTimeout("",1000);
																		$('#deliveryPlan').datagrid("reload");
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
					<div id="a3" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<div style="padding: 8px;">
							<table id="salesTicket" class="easyui-datagrid" style="height: 380px; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#saleTicketTb',url: '${vix}/sales/order/salesOrderAction!getSalesTicketJson.action?id=${salesOrder.id}'">
								<thead>
									<tr>
										<th data-options="field:'id',align:'center',width:60">编号</th>
										<th data-options="field:'title',width:120,align:'center',editor:'text'">名称</th>
										<th data-options="field:'taxpayerPlayer',width:100,align:'center',editor:'text'">纳税人识别号</th>
										<th data-options="field:'ticketAmount',width:100,align:'center',editor:'text'">发票金额</th>
										<th data-options="field:'ticketCount',width:220,align:'center',editor:'text'">发票数量</th>
									</tr>
								</thead>
							</table>
							<div id="saleTicketTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="addSalesTicket(0)"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
									data-options="iconCls:'icon-edit',plain:true" onclick="updateSalesTicket()"><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left"><s:text name="cmn_update" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-remove',plain:true"
									onclick="removeSalesTicket()"><span class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a>
							</div>
							<script type="text/javascript">
						$('#salesTicket').datagrid();
						function removeSalesTicket(){
							var row = $('#salesTicket').datagrid('getSelected');
							if(row){
								asyncbox.confirm('是否删除该发票信息?','提示信息',function(action){
									if(action == 'ok'){
										var index = $('#salesTicket').datagrid('getRowIndex',row);
										$('#salesTicket').datagrid('deleteRow', index);
										$.ajax({
											  url:'${vix}/sales/order/salesTicketAction!deleteById.action?id='+row.id,
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
						function updateSalesTicket(){
							var row = $('#salesTicket').datagrid('getSelected');
							if(row){
								addSalesTicket(row.id);
							}else{
								showMessage("请选择一条数据!");
							}
						}
						function addSalesTicket(id){
							$.ajax({
								  url:'${vix}/sales/order/salesTicketAction!goSaveOrUpdate.action?id='+id,
								  cache: false,
								  success: function(html){
									  asyncbox.open({
										 	modal:true,
											width : 660,
											height : 360,
											title:"发票信息",
											html:html,
											callback : function(action){
												if(action == 'ok'){
													if($('#salesTicketForm').validationEngine('validate')){
														$.post('${vix}/sales/order/salesTicketAction!saveOrUpdate.action',
																{'salesTicket.id':$("#stId").val(),
																  'salesTicket.planTicketDate':$("#planTicketDate").val(),
																  'salesTicket.title':$("#stTitle").val(),
																  'salesTicket.content':$("#ticketContent").val(),
																  'salesTicket.taxpayerPlayer':$("#taxpayerPlayer").val(),
																  'salesTicket.bank':$("#bank").val(),
																  'salesTicket.bankAccount':$("#bankAccount").val(),
																  'salesTicket.taxRate':$("#bankAccount").val(),
																  'salesTicket.ticketAmount':$("#ticketAmount").val(),
																  'salesTicket.ticketCount':$("#ticketCount").val(),
																  'salesTicket.memo':$("#memo").val(),
																  'salesTicket.ticketType':$("#ticketType").val(),
																  'salesTicket.isFreeze':$(":radio[name=isFreeze][checked]").val(),
																  'salesTicket.salesOrder.id':$("#id").val()
																},
																function(result){
																	showMessage(result);
																	setTimeout("",1000);
																	$('#salesTicket').datagrid("reload");
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
					<div id="a4" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<div style="padding: 8px;">
							<table id="soAttach" class="easyui-datagrid" style="height: 380px; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#soAttachTb',url: '${vix}/sales/order/salesOrderAction!getAttachFileJson.action?id=${salesOrder.id}'">
								<thead>
									<tr>
										<th data-options="field:'id',align:'center',width:60">编号</th>
										<th data-options="field:'name',width:260,align:'center',editor:'text'">名称</th>
										<th data-options="field:'type',width:60,align:'center',editor:'text'">类型</th>
										<th data-options="field:'memo',width:320,align:'center',editor:'text'">备注</th>
									</tr>
								</thead>
							</table>
							<div id="soAttachTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="addSalesAttachFile(0)"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
									data-options="iconCls:'icon-edit',plain:true" onclick="updateSalesAttachFile()"><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left">下载</span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-remove',plain:true" onclick="removeSalesAttachFile()"><span
									class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a>
							</div>
							<script type="text/javascript">
							$('#soAttach').datagrid();
							function removeSalesAttachFile(){
								var row = $('#soAttach').datagrid('getSelected');
								if(row){
									asyncbox.confirm('是否删除该附件信息?','提示信息',function(action){
										if(action == 'ok'){
											var index = $('#soAttach').datagrid('getRowIndex',row);
											$('#soAttach').datagrid('deleteRow', index);
											$.ajax({
												  url:'${vix}/sales/order/salesAttachFileAction!deleteAttachFile.action?id='+row.id,
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
							function updateSalesAttachFile(){
								var row = $('#soAttach').datagrid('getSelected');
								if(row){
									$.ajax({
										  url:'${vix}/sales/order/salesAttachFileAction!downloadAttachedFile.action?id=' + row.id,
										  cache: false,
										  success: function(html){
											  showMessage(html);
										  }
									});
								}else{
									showMessage("请选择一条数据!");
								}
							}
							function addSalesAttachFile(id){
								$.ajax({
									  url:'${vix}/sales/order/salesAttachFileAction!goSaveOrUpdate.action?id='+$("#id").val(),
									  cache: false,
									  success: function(html){
										  asyncbox.open({
											 	modal:true,
												width : 660,
												height : 360,
												title:"附件信息",
												html:html,
												callback : function(action){
													if(action == 'ok'){
														$("#salesAttachFileForm").ajaxSubmit({
														     type: "post",
														     url: "${vix}/sales/order/salesAttachFileAction!uploadAttachment.action?id="+$("#id").val(),
														     dataType: "text",
														     success: function(result){
														    	 showMessage(result);
													    		$('#soAttach').datagrid("load");
															    	
														     }
														 });
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
				<dd class="clearfix">
					<div class="order_table">
						<table class="addtable_c">
							<tr>
								<td width="15%" align="right">小计:</td>
								<td width="35%"><input id="amount" name="amount" value="${salesOrder.amount}" type="text" size="30" readonly="readonly" /></td>
								<td width="10%" align="right">运费:</td>
								<td width="40%"><input id="freight" name="freight" type="text" value="${salesOrder.freight}" size="30" onblur="calculateAmount();" /></td>
							</tr>
							<tr>
								<td align="right">税率:</td>
								<td><input id="taxRate" name="taxRate" value="0.17" type="text" size="30" /></td>
								<td align="right">税额:</td>
								<td><input id="tax" name="tax" value="${salesOrder.tax}" type="text" size="30" /></td>
							</tr>
							<tr>
								<td align="right"></td>
								<td></td>
								<td align="right">总金额:</td>
								<td><input id="amountTotal" name="amountTotal" value="${salesOrder.amountTotal}" type="text" readonly="readonly" size="30" /></td>
							</tr>
						</table>
					</div>
				</dd>
			</dl>
		</div>
		<!--oder-->
	</form>
</div>
<!-- content -->
<script type="text/javascript">
	$(function(){
		$.fn.fix = function(options){
			var defaults = {
				'advance' : 0,
				'top' : 0
			}
			options = $.extend(defaults, options);
			return this.each(function(){
				var $_this = $(this);
				$_this.wrap('<div></div>');
				var wp = $_this.parent('div');
				wp.height(wp.height());
				var ofst = wp.offset();
				
				if(!$_this.is(':visible') && $(window).scrollTop() + options.advance > $_this.offset().top){
					$_this.css({'position':'fixed','z-index':9999,'top': options.top,'width':$_this.width()});
				}
				$(window).scroll(function(){
					if(!$_this.is(':visible')){
						return ;
					}
					
					if($(window).scrollTop() + options.advance > wp.offset().top){
						$_this.css({'position':'fixed','z-index':9999,'top': options.top,'width':$_this.width()});
					}else{
						$_this.css({'position':'static','z-index':'auto','top': 'auto','width':'auto'});
					}
				});
			});
		}
		$('#a1 .right_btn,#a3 .right_btn').fix({'advance':38,'top':38});
	});
	
	function calculateAmount(){
		var amount = $("#amount").val();
		var freight = $("#freight").val();
		if(amount != ''){
			if(freight == ''){
				$("#amountTotal").val(Number(amount));
				$("#tax").val(Number(amount)*0.17);
			}else{
				$("#amountTotal").val(Number(amount)+Number(freight));
				$("#tax").val(Number(amount)*0.17);
			}
		}
	}
	function tabs(title,content,style){
		$(title).click(function(){
			$(title).removeClass(style);
			$(this).addClass(style);
			$(content).hide();
			$(content+':eq('+$(title).index($(this))+')').show();
		});
	}
 
	$(window).scroll(function(){
		if($('#orderTitleFixd').parent('dl').offset().top - 43 < $(window).scrollTop()){
			if(!$('#orderTitleFixd').hasClass('fixed')){
				$('#orderTitleFixd').addClass('fixed');
				$('#orderTitleFixd').parent('dl').css('padding-top',30);
			}
		}else{
			$('#orderTitleFixd').removeClass('fixed');
			$('#orderTitleFixd').parent('dl').css('padding-top',0);
		}
	});
 
</script>