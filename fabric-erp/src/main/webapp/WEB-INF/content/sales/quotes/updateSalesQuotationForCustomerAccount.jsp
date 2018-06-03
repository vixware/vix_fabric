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
 
$("#salesQuotationForm").validationEngine();
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
								$("#salesManId").val(result[0]);
								$("#salesManName").val(result[1]);
							}else{
								$("#salesManId").val("");
								$("#salesManName").val("");
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
function chooseSqOrgUnit(){
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
								var uId = resObj[0].realId;
								$("#sqOrganizationUnitId").val(uId);
								$("#sqOrganizationUnitName").val(resObj[0].name);
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
function textChanged(){
	var bc = $("#barCode").val();
	if($("#selectOption").val() == '2' && bc != ''){    
		$.post('${vix}/sales/quotes/salesQuotationAction!saveOrUpdateSalesQuotationItem.action',
			{'barCode':bc,
			  'salesQuotationItem.salesQuotation.id':$("#id").val()
			},
			function(result){
				showMessage(result);
				$("#barCode").val("");
				setTimeout("",1000);
				$('#salesQuotationItem').datagrid("reload");
			}
		);
	}
}
function barCodeEnter(){
	var bc = $("#barCode").val();
	var event=arguments.callee.caller.arguments[0]||window.event;
	if(event.keyCode == 13 && bc != ''){    
		$.post('${vix}/sales/quotes/salesQuotationAction!saveOrUpdateSalesQuotationItem.action',
				{'barCode':bc,
				  'salesQuotationItem.salesQuotation.id':$("#id").val()
				},
				function(result){
					showMessage(result);
					$("#barCode").val("");
					setTimeout("",1000);
					$('#salesQuotationItem').datagrid("reload");
				}
			);
	}
}
//面包屑
if($('.sub_menu').length){
	$("#breadCrumb").jBreadCrumb();
}
//-->
</script>
<input type="hidden" id="sqId" name="sqId" value="${salesQuotation.id}" />
<input type="hidden" id="isDeleted" name="isDeleted" value="${salesQuotation.isDeleted}" />
<input type="hidden" id="billGroupCodeId" name="billGroupCodeId" value="${billGroupCode.id}" />
<div class="content">
	<form id="salesQuotationForm">
		<div class="order">
			<dl>
				<dt id="orderTitleFixd">
					<strong> <b> <s:if test="salesQuotation.code != null ">
							${salesQuotation.quoteSubject}
						</s:if> <s:else>
							新增销售报价单
						</s:else>
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
									<table>
										<tr>
											<td align="right" width="15%">报价单编码:</td>
											<td width="35%"><input id="code" name="salesQuotation.code" value="${salesQuotation.code}" type="text" size="20" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right" width="10%">报价主题:</td>
											<td width="40%"><input id="quoteSubject" name="salesQuotation.quoteSubject" value="${salesQuotation.quoteSubject}" type="text" size="20" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">部门:</td>
											<td><input id="sqOrganizationUnitName" name="salesQuotation.dept.fullName" value="${salesQuotation.dept.fullName}" type="text" /> <input type="hidden" id="sqOrganizationUnitId" name="cpOrganizationUnitId" value="${salesQuotation.dept.id}" /> <span><a class="abtn" href="#" onclick="chooseSqOrgUnit();"><span>选择</span></a></span></td>
											<td align="right">销售组织:</td>
											<td><input type="hidden" id="saleOrgId" value="${salesQuotation.salesOrg.id}" /> <input id="saleOrgName" name="salesOrder.saleOrg.fullName" value="${salesQuotation.salesOrg.fullName}" type="text" size="20" /> <a class="abtn" href="###" onclick="chooseSaleOrg();"><span>选择</span></a></td>
										</tr>
										<tr>
											<td align="right">业务类型:</td>
											<td><input id="bizType" name="salesQuotation.bizType" value="${salesQuotation.bizType}" type="text" size="20" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">报价单类型:</td>
											<td><s:select id="typeId" headerKey="-1" headerValue="--请选择--" list="%{billsTypeList}" listValue="typeName" listKey="typeCode" value="%{salesQuotation.type}" multiple="false" theme="simple"></s:select></td>
										</tr>
										<tr>
											<td align="right">城市:</td>
											<td><input id="city" name="salesQuotation.city" value="${salesQuotation.city}" type="text" size="20" /></td>
											<td align="right">税率 :</td>
											<td><input id="tax" name="salesQuotation.tax" value="${salesQuotation.tax}" type="text" size="20" />% 范围(1-100)</td>
										</tr>
										<tr>
											<td align="right">交货日期:</td>
											<td><input id="dilveryDate" name="salesQuotation.dilveryDate" value="<s:property value='formatDateToString(salesQuotation.dilveryDate)'/>" onfocus="showTime('dilveryDate','yyyy-MM-dd HH:mm')" type="text" size="20" /> <img onclick="showTime('dilveryDate','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif" width="16"
												height="22" align="absmiddle"></td>
											<td align="right">单据日期:</td>
											<td><input type="hidden" id="bdString" value="<s:property value='formatDateToTimeString(salesQuotation.billDate)'/>" /> <input id="billDate" name="salesQuotation.billDate" value="<s:property value='formatDateToString(salesQuotation.billDate)'/>" onfocus="showTime('billDate','yyyy-MM-dd HH:mm')" type="text" size="20" /> <img
												onclick="showTime('billDate','yyyy-MM-dd')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<td align="right">报价有效期从:</td>
											<td><input id="validQuotationFrom" name="salesQuotation.validQuotationFrom" value="<s:property value='formatDateToString(salesQuotation.validQuotationFrom)'/>" onfocus="showTime('validQuotationFrom','yyyy-MM-dd HH:mm')" type="text" size="20" class="validate[required] text-input" /> <img
												onclick="showTime('validQuotationFrom','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"><span style="color: red;">*</span></td>
											<td align="right">报价有效期至:</td>
											<td><input id="validQuotationTo" name="salesQuotation.validQuotationTo" value="<s:property value='formatDateToString(salesQuotation.validQuotationTo)'/>" onfocus="showTime('validQuotationTo','yyyy-MM-dd HH:mm')" type="text" size="20" class="validate[required] text-input" /> <img
												onclick="showTime('validQuotationTo','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">地域:</td>
											<td colspan="3"><s:select id="regionalId" list="%{regionalList}" headerKey="-1" headerValue="--请选择--" listValue="name" listKey="id" value="%{salesQuotation.regional.id}" multiple="false" theme="simple"></s:select></td>
										</tr>
										<tr>
											<td align="right">备注</td>
											<td colspan="3"><textarea id="memo" rows="3" cols="70" style="font-size: 12px;">${salesQuotation.memo}</textarea></td>
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
											<td><select id="selectOption" name="selectOption" onchange="$('barCode').focus();" class="validate[required] text-input">
													<option value="1">手动录入</option>
													<option value="2">扫描录入</option>
											</select></td>
											<th>商品编码：</th>
											<td><input id="barCode" name="barCode" type="text" onchange="textChanged();" onkeypress="textChanged();" /></td>
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
							<li class="current"><a onclick="javascript:$('#a1').attr('style','');tab(5,1,'a',event);"><img src="${vix}/common/img/mail.png" alt="" />报价单明细</a></li>
						</ul>
					</div>
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<div style="padding: 8px;">
							<table id="salesQuotationItem" class="easyui-datagrid" style="height: 380px; margin: 6px;" data-options="fitColumns:true,singleSelect: true,toolbar: '#salesQuotationItemTb',url: '${vix}/sales/quotes/salesQuotationAction!getSalesQuotationItemJson.action?id=${salesQuotation.id}'">
								<thead>
									<tr>
										<th data-options="field:'id',align:'center',width:60">编号</th>
										<th data-options="field:'item.id'"></th>
										<th data-options="field:'itemCode',width:150,align:'center'">编码</th>
										<th data-options="field:'itemName',width:120,align:'center'">名称</th>
										<th data-options="field:'unit',width:120,align:'center'">计量单位</th>
										<th data-options="field:'amount',width:120,align:'center'">数量</th>
										<th data-options="field:'price',width:120,align:'center'">价格</th>
										<th data-options="field:'netPrice',width:120,align:'center'">无税价格</th>
										<th data-options="field:'taxPrice',width:120,align:'center'">含税价格</th>
										<th data-options="field:'memo',width:180,align:'center'">备注</th>
									</tr>
								</thead>
							</table>
							<div id="salesQuotationItemTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="addItem(0)"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
									data-options="iconCls:'icon-edit',plain:true" onclick="updateItem()"><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left"><s:text name="cmn_update" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-edit',plain:true" onclick="showItemPrice()"><span
									class="l-btn-left"><img alt="" src="${vix}/common/img/system/itemPrice.png"><span style="padding: 0;" class="l-btn-text l-btn-icon-left">定价</span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-remove',plain:true" onclick="removeItem()"><span class="l-btn-left"><span
										class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a>
							</div>
							<script type="text/javascript">
							$('#salesQuotationItem').datagrid();
							$('#salesQuotationItem').datagrid('hideColumn','item.id');
							function removeItem(){
								var row = $('#salesQuotationItem').datagrid('getSelected');
								if(row){
									asyncbox.confirm('是否删除该报价单明细?','提示信息',function(action){
										if(action == 'ok'){
											var index = $('#salesQuotationItem').datagrid('getRowIndex',row);
											$('#salesQuotationItem').datagrid('deleteRow', index);
											$.ajax({
												  url:'${vix}/sales/quotes/salesQuotationAction!deleteSalesQuotationItemById.action?id='+row.id,
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
							function showItemPrice(){
								var row = $('#salesQuotationItem').datagrid('getSelected');
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
								var row = $('#salesQuotationItem').datagrid('getSelected');
								if(row){
									addItem(row.id);
								}else{
									showMessage("请选择一条数据!");
								}
							}
							function addItem(id){
								$.ajax({
									  url:'${vix}/sales/quotes/salesQuotationAction!goSaveOrUpdateSalesQuotationItem.action?id='+id,
									  cache: false,
									  success: function(html){
										  asyncbox.open({
											 	modal:true,
												width : 1000,
												height : 540,
												title:"添加${vv:varView('vix_mdm_item')}",
												html:html,
												callback : function(action){
													if(action == 'cancel' || action == 'close'){
														$('#salesQuotationItem').datagrid("reload");
														$.ajax({
															  url:'${vix}/sales/quotes/salesQuotationAction!getSalesQuotationItemTotal.action?id='+$("#id").val(),
															  cache: false,
															  success: function(json){
																  $("#amount").val(json);
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
				</div>
				<dd class="clearfix">
					<div class="order_table">
						<table>
							<tr>
								<td width="15%" align="right">报价人:</td>
								<td width="35%"><input type="hidden" id="salesManId" value="${salesQuotation.salesMan.id}" /> <input id="salesManName" name="salesQuotation.salesMan.name" value="${salesQuotation.salesMan.name}" type="text" size="20" /> <a class="abtn" href="###" onclick="chooseEmployee();"><span>选择</span></a></td>
								<td width="10%" align="right">金额:</td>
								<td width="40%"><input id="amount" name="amount" value="${salesQuotation.amount}" type="text" size="20" /></td>
							</tr>
						</table>
					</div>
				</dd>
			</dl>
		</div>
	</form>
</div>
<!-- content -->
