<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<script src="${vix}/common/js/ajaxfileupload.js" type="text/javascript"></script>
<script src="${vix}/common/core/js/all.js" type="text/javascript"></script>

<script type="text/javascript">
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

$("table tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("table tr:even").addClass("alt");
 
//pager("start","${vix}/template/orderAction!goOrderItemSingleList.action?rows=6&id="+$("#id").val(),'orderUpdate');
/* function currentPager(tag){
	pager(tag,"${vix}/template/orderAction!goOrderItemSingleList.action?rows=6&id="+$("#id").val(),'orderUpdate');
} */
/** 保存发货单 */
function saveOrUpdateDeliveryDocumentForm(){
	if($('#deliveryDocumentForm').validationEngine('validate')){
		$.post('${vix}/sales/delivery/deliveryDocumentAction!saveOrUpdate.action',
				{
					'deliveryDocument.id':$("#id").val(),
					'deliveryDocument.ddCode':$("#ddCode").val(),
					'deliveryDocument.customerAccount.id':$("#customerAccountId").val(),
					'deliveryDocument.salesBillType.id':$("#salesBillTypeId").val(),
					'deliveryDocument.salesBusinessType.id':$("#salesBusinessTypeId").val(),
					'deliveryDocument.amount':$("#amount").val(),
					'deliveryDocument.salePerson.id':$("#salePersonId").val(),
					'deliveryDocument.source':$("#source").val(),
					'deliveryDocument.target':$("#target").val(),
					'deliveryDocument.deliveryTime':$("#deliveryTime").val(),
					'deliveryDocument.shippedDate':$("#shippedDate").val()
				},
				function(result){
					showMessage(result);
					setTimeout("",1000);
					loadContent('${vix}/sales/delivery/deliveryDocumentAction!goList.action?menuId=menuOrder');
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
								$("#customerAccountId").val(result[0].replace(',',''));
								$("#customerName").val(result[1].replace(',',''));
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
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
$("#deliveryDocumentForm").validationEngine();
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
				<li><a href="#"><img src="${vix}/common/img/sale/saleDelivery.png" alt="" />
					<s:text name="cmn_supplyChain" /></a></li>
				<li><a href="#"><s:text name="sa_salesmanage" /></a></li>
				<a href="#" onclick="loadContent('${vix}/sales/delivery/deliveryDocumentAction!goList.action');"><s:text name="sa_salesinvoices" /></a>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${deliveryDocument.id }" />
<div class="content">
	<form id="deliveryDocumentForm">
		<div class="order">
			<dl>
				<dt>
					<span class="no_line"> <a onclick="saveOrUpdateDeliveryDocumentForm();" href="#"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <s:if test="isAllowAudit == 1">
							<a onclick="approvalStockRecords(1);" href="###"><img width="22" height="22" alt="保存并提交审批" src="${vix}/common/img/dt_submit.png" /></a>
						</s:if> <a href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a>
						<div class="ms">
							<a href="#"><img width="22" height="22" alt="" src="img/icon_add.gif"></a>
							<ul style="display: none;">
								<li><a href="#" class=""><img src="img/icon_10.png" alt="">销售报价单</a></li>
								<li><a href="#" class=""><img src="img/icon_10.png" alt="">销售订单</a></li>
							</ul>
						</div> <a href="#" onclick="loadContent('${vix}/sales/delivery/deliveryDocumentAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" alt="返回" src="${vix}/common/img/dt_goback.png" /></a>
					</span> <strong> <b>新增销售发货单</b> <i></i>
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
											<td align="right">发货单编码：</td>
											<td><input id="ddCode" name="ddCode" value="${deliveryDocument.ddCode }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span>
											<td align="right">客户名称：</td>
											<td><input id="customerName" name="deliveryDocument.customerAccount.name" value="${deliveryDocument.customerAccount.name}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span> <input type="hidden" id="customerAccountId" name="customerAccountId"
												value="${deliveryDocument.customerAccount.id}" /> <span><a class="abtn" href="#" onclick="chooseCustomerAccount();"><span>选择</span></a></span></td>
										</tr>
										<tr>
											<td align="right">发运地：</td>
											<td><input id="source" name="source" value="${deliveryDocument.source }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">目的地：</td>
											<td><input id="target" name="target" value="${deliveryDocument.target }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">单据类型：</td>
											<td><s:select id="salesBillTypeId" list="%{salesBillTypeList}" headerKey="" headerValue="--请选择--" listValue="name" listKey="id" value="%{deliveryDocument.salesBillType.id}" multiple="false" theme="simple"></s:select></td>
											<td align="right">业务类型：</td>
											<td><s:select id="salesBusinessTypeId" list="%{salesBusinessTypeList}" headerKey="" headerValue="--请选择--" listValue="name" listKey="id" value="%{deliveryDocument.salesBusinessType.id}" multiple="false" theme="simple"></s:select></td>
										</tr>
										<tr>
											<td align="right">金额：</td>
											<td><input id="amount" name="amount" value="${deliveryDocument.amount }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">业务员：</td>
											<td><input id="salePersonName" name="deliveryDocument.salePerson.name" value="${deliveryDocument.salePerson.name}" type="text" size="30" /> <input type="hidden" id="salePersonId" value="${deliveryDocument.salePerson.id}" /> <a class="abtn" href="#" onclick="chooseEmployee();"><span>选择</span></a></td>
										</tr>
										<tr>
											<td align="right">交货日期：</td>
											<td><input id="deliveryTime" name="deliveryTime" value="<fmt:formatDate value='${deliveryDocument.deliveryTime }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" class="validate[required] text-input" size="30" /> <span style="color: red;">*</span> <img onclick="showTime('deliveryTime','yyyy-MM-dd')"
												src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
											<td align="right">发货日期：</td>
											<td><input id="shippedDate" name="shippedDate" value="<fmt:formatDate value='${deliveryDocument.shippedDate }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" class="validate[required] text-input" size="30" /> <span style="color: red;">*</span> <img onclick="showTime('shippedDate','yyyy-MM-dd')"
												src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
									</table>
								</dd>
							</dl>
						</div>
						<div class="voucher newvoucher" style="display: none;">
							<dl>
								<dt>
									<b></b> <span style="display: none;"> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a> <a href="#"><img src="${vix}/common/img/wrench_screwdriver.png" alt="" />计算器</a>
									</span> <strong>其他信息</strong>
								</dt>

								<dd style="display: none;">

									<table style="border: none;">
										<tr>
											<td align="right">创建日期：</td>
											<td><input id="jiaozhangDate" name="" type="text" /> <img onclick="showTime('jiaozhangDate','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
											<td align="right">过账日期：</td>
											<td><input id="guozhangDate" name="" type="text" /> <img onclick="showTime('guozhangDate','yyyy-MM-dd HH:mm')" src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<td align="right">发运状态：</td>
											<td><input name="" type="text" size="30" /></td>
											<td align="right">付款条件：</td>
											<td><select style="width: 50"><option>请选择</option></select></td>
										</tr>
										<tr>
											<td align="right">金额：</td>
											<td><input name="" type="text" size="30" /></td>
											<td align="right">币种：</td>
											<td><select style="width: 50"><option>请选择</option></select></td>
										</tr>
										<tr>
											<td align="right">状态：</td>
											<td><select style="width: 50"><option>请选择</option></select></td>
											<td align="right">收款条件：</td>
											<td><select style="width: 50"><option>请选择</option></select></td>
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
							<li class="current"><a onclick="javascript:tab(3,1,'a',event);"><img src="${vix}/common/img/mail.png" alt="" />明细</a></li>
							<li><a onclick="javascript:$('#a2').attr('style','');tab(3,2,'a',event)"><img src="${vix}/common/img/mail.png" alt="" />审批</a></li>
							<li><a onclick="javascript:$('#a3').attr('style','');tab(3,3,'a',event)"><img src="${vix}/common/img/attachment.png" alt="" />附件</a></li>
						</ul>
					</div>
					<div id="a1" style="position: relative; z-index: 1; background: #FFF;">
						<div style="padding: 8px; height: 300px;">
							<table id="dlDocumentItem" class="easyui-datagrid" style="height: auto; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlDocumentItemTb',url: '${vix}/sales/delivery/deliveryDocumentAction!getDeliveryDocumentItemJson.action?id=${deliveryDocument.id}'">
								<thead>
									<tr>
										<th data-options="field:'id',align:'center',width:120">编号</th>
										<th data-options="field:'itemCode',align:'center',width:120,editor:'text'">${vv:varView('vix_mdm_item')}编码</th>
										<th data-options="field:'itemName',width:200,align:'center',editor:'text'">${vv:varView('vix_mdm_item')}名称</th>
										<th data-options="field:'specification',width:120,align:'center',editor:'text'">规格型号</th>
										<th data-options="field:'itemType',width:120,align:'center',editor:'text'">${vv:varView('vix_mdm_item')}类型</th>
										<th data-options="field:'count',width:120,align:'center',editor:'numberbox'">订货数量</th>
										<th data-options="field:'netTotal',width:120,align:'center',editor:'numberbox'">金额</th>
										<th data-options="field:'netPrice',width:120,align:'center',editor:'numberbox'">单价</th>
										<th data-options="field:'recieveAddress',width:120,align:'center',editor:'text'">收货地址</th>
									</tr>
								</thead>
							</table>
							<div id="dlDocumentItemTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="addDeliveryDocumentItem(0)"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)"
									class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-edit',plain:true" onclick="updateDeliveryDocumentItem()"><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left"><s:text name="cmn_update" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
									data-options="iconCls:'icon-remove',plain:true" onclick="removeDeliveryDocumentItem()"><span class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a>
							</div>
							<script type="text/javascript">
								$('#dlDocumentItem').datagrid();
								function removeDeliveryDocumentItem(){
									var row = $('#dlDocumentItem').datagrid('getSelected');
									if(row){
										asyncbox.confirm('是否删除该明细信息?','提示信息',function(action){
											if(action == 'ok'){
												var index = $('#dlDocumentItem').datagrid('getRowIndex',row);
												$('#dlDocumentItem').datagrid('deleteRow', index);
												$.ajax({
													  url:'${vix}/sales/delivery/deliveryDocumentItemAction!deleteById.action?id='+row.id,
													  cache: false,
													  success: function(html){
														  showMessage(html);
														  $('#dlDocumentItem').datagrid("load");
													  }
												});
											}
										});
									}else{
										showMessage("请选择一条数据!");
									}
								}
								function updateDeliveryDocumentItem(){
									var row = $('#dlDocumentItem').datagrid('getSelected');
									if(row){
										addDeliveryDocumentItem(row.id);
									}else{
										showMessage("请选择一条数据!");
									}
								}
								function addDeliveryDocumentItem(id){
									$.ajax({
										  url:'${vix}/sales/delivery/deliveryDocumentItemAction!goSaveOrUpdate.action?id='+id,
										  cache: false,
										  success: function(html){
											  asyncbox.open({
												 	modal:true,
													width : 660,
													height : 260,
													title:"明细信息",
													html:html,
													callback : function(action){
														if(action == 'ok'){
															if($('#deliveryDocumentItemForm').validationEngine('validate')){
																$.post('${vix}/sales/delivery/deliveryDocumentItemAction!saveOrUpdate.action',
																		{'deliveryDocumentItem.id':$("#ddiId").val(),
																		  'deliveryDocumentItem.specification':$("#specification").val(),
																		  'deliveryDocumentItem.itemCode':$("#itemCode").val(),
																		  'deliveryDocumentItem.itemName':$("#itemName").val(),
																		  'deliveryDocumentItem.count':$("#ddiCount").val(),
																		  'deliveryDocumentItem.price':$("#ddiPrice").val(),
																		  'deliveryDocumentItem.unit':$("#ddiUnit").val(),
																		  'deliveryDocumentItem.recieveAddress':$("#recieveAddress").val(),
																		  'deliveryDocumentItem.deliveryDocument.id':$("#id").val()
																		},
																		function(result){
																			showMessage(result);
																			$('#dlDocumentItem').datagrid("load");
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
					<div id="a2" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<div style="padding: 8px; height: 300px;">
							<table id="dlApprovalOpinion" class="easyui-datagrid" style="height: auto; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlApprovalOpinionTb',url: '${vix}/sales/delivery/deliveryDocumentApprovalRecordAction!getApprovalRecordJson.action?id=${deliveryDocument.id}'">
								<thead>
									<tr>
										<th data-options="field:'id',align:'center',width:120">编号</th>
										<th data-options="field:'checkPerson',width:200,align:'center',editor:'text'">审批人</th>
										<th data-options="field:'content',width:400,align:'center',editor:'text'">审批意见</th>
									</tr>
								</thead>
							</table>
							<div id="dlApprovalOpinionTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="addApprovalOpinion()"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
									data-options="iconCls:'icon-remove',plain:true" onclick="removeApprovalOpinion()"><span class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a>
							</div>
							<script type="text/javascript">
								$('#dlApprovalOpinion').datagrid();
								function removeApprovalOpinion(){
									var row = $('#dlApprovalOpinion').datagrid('getSelected');
									if(row){
										asyncbox.confirm('是否删除该审批意见?','提示信息',function(action){
											if(action == 'ok'){
												var index = $('#dlApprovalOpinion').datagrid('getRowIndex',row);
												$('#dlApprovalOpinion').datagrid('deleteRow', index);
												$.ajax({
													  url:'${vix}/sales/delivery/deliveryDocumentApprovalRecordAction!deleteById.action?id='+row.id,
													  cache: false,
													  success: function(html){
														  showMessage(html);
														  $('#dlApprovalOpinion').datagrid("load");
													  }
												});
											}
										});
									}else{
										showMessage("请选择一条数据!");
									}
								}
								
								function addApprovalOpinion(){
									$.ajax({
										  url:'${vix}/sales/delivery/deliveryDocumentApprovalRecordAction!goSaveOrUpdate.action?id=',
										  cache: false,
										  success: function(html){
											  asyncbox.open({
												 	modal:true,
													width : 660,
													height : 260,
													title:"明细信息",
													html:html,
													callback : function(action){
														if(action == 'ok'){
															$.post('${vix}/sales/delivery/deliveryDocumentApprovalRecordAction!saveOrUpdate.action',
																	{'approvalRecord.content':$("#ddContent").val(),
																	  'id':$("#id").val()
																	},
																	function(result){
																		showMessage(result);
																		$('#dlApprovalOpinion').datagrid("load");
																	}
																);
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
						<script type="text/javascript">
					$('#soAttach').datagrid({
						url: '${vix}/sales/delivery/deliveryDocumentAction!getAttachmentsJson.action?id=${deliveryDocument.id}',
						title: '订单附件',
						width: 900,
						height: 'auto',
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
									  url:'${vix}/sales/delivery/deliveryDocumentAction!addAttachments.action',
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
														uploadFile('${vix}/sales/delivery/deliveryDocumentAction!uploadAttachments.action?id=${deliveryDocument.id}','fileToUpload');
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
									$.ajax({url:'${vix}/sales/delivery/deliveryDocumentAction!deleteAttachFile.action?afId='+rows[i].id,cache: false});
								}
							}
						}]
					});
				</script>
						<div style="padding: 8px; height: 300px;">
							<table id="soAttach"></table>
						</div>
					</div>
				</div>
			</dl>
		</div>
	</form>
</div>
<!-- content -->