<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript" src="${vix}/plugin/jQuery_TextExt_1.3.1.js"></script>
<script src="${vix}/common/js/ajaxfileupload.js" type="text/javascript"></script>

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
$("table tr").mouseover(function(){$(this).addClass("over");}).mouseout(function(){$(this).removeClass("over");});
$("table tr:even").addClass("alt");
 
/** 保存销售退货单 */
function saveOrUpdateReturnForm(){
	if($('#saleReturnFormForm').validationEngine('validate')){
		$.post('${vix}/sales/delivery/saleReturnFormAction!saveOrUpdate.action',
				{
					'saleReturnForm.id':$("#id").val(),
					'saleReturnForm.returnOrderCode':$("#returnOrderCode").val(),
					'saleReturnForm.returnGoodsType.id':$("#returnGoodsTypeId").val(),
					'saleReturnForm.customerAccount.id':$("#customerAccountId").val(),
					'saleReturnForm.source':$("#source").val(),
					'saleReturnForm.transferStyle':$("#transferStyle").val(),
					'saleReturnForm.returnTime':$("#returnTime").val(),
					'saleReturnForm.startTime':$("#startTime").val(),
					'saleReturnForm.endTime':$("#endTime").val(),
					'saleReturnForm.returnReason':$("#returnReason").val()
				},
				function(result){
					showMessage(result);
					setTimeout("",1000);
					loadContent('${vix}/sales/delivery/saleReturnFormAction!goList.action?menuId=menuOrder');
				}
			 );
	}
}

$("#saleReturnFormForm").validationEngine();
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
				<li><a href="#"><img src="${vix}/common/img/sale/saleReturn.png" alt="" />
					<s:text name="cmn_supplyChain" /></a></li>
				<li><a href="#"><s:text name="sa_salesmanage" /></a></li>
				<a href="#" onclick="loadContent('${vix}/sales/delivery/saleReturnFormAction!goList.action');">销售退货单</a>
			</ul>
		</div>
	</h2>
</div>
<input type="hidden" id="id" name="id" value="${saleReturnForm.id }" />
<div class="content">
	<form id="saleReturnFormForm">
		<div class="order">
			<dl>
				<dt>
					<span class="no_line"> <a onclick="saveOrUpdateReturnForm();" href="#"><img width="22" height="22" alt="保存" src="${vix}/common/img/dt_save.png"></a> <s:if test="isAllowAudit == 1">
							<a onclick="approvalStockRecords(1);" href="###"><img width="22" height="22" alt="保存并提交审批" src="${vix}/common/img/dt_submit.png" /></a>
						</s:if> <a href="#"><img width="22" height="22" alt="保存并新增" src="${vix}/common/img/dt_saveandupdate.png" /></a> <a href="#" onclick="loadContent('${vix}/sales/delivery/saleReturnFormAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" alt="返回" src="${vix}/common/img/dt_goback.png" /></a>
					</span> <strong> <b>新增销售退货单</b> <i></i>
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
											<td align="right">退货单编码：</td>
											<td><input id="returnOrderCode" name="returnOrderCode" value="${saleReturnForm.returnOrderCode }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span>
											<td align="right">单据类型：</td>
											<td><s:select id="returnGoodsTypeId" list="%{returnGoodsTypeList}" headerKey="" headerValue="--请选择--" listValue="name" listKey="id" value="%{saleReturnForm.returnGoodsType.id}" multiple="false" theme="simple"></s:select></td>
										</tr>
										<tr>
											<td align="right">客户名称：</td>
											<td><input id="customerName" name="saleReturnForm.customerAccount.name" value="${saleReturnForm.customerAccount.name}" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span> <input type="hidden" id="customerAccountId" name="customerAccountId" value="${saleReturnForm.customerAccount.id}" /> <span><a
													class="abtn" href="#" onclick="chooseCustomerAccount();"><span>选择</span></a></span></td>
											<td align="right">来源：</td>
											<td><input id="source" name="source" value="${saleReturnForm.source }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
										</tr>
										<tr>
											<td align="right">运输方式：</td>
											<td><input id="transferStyle" name="transferStyle" value="${saleReturnForm.transferStyle }" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span></td>
											<td align="right">退货日期：</td>
											<td><input id="returnTime" name="returnTime" value="<fmt:formatDate value='${saleReturnForm.returnTime }' type='both' pattern='yyyy-MM-dd' />" readonly="readonly" type="text" class="validate[required] text-input" /><span style="color: red;">*</span> <img onclick="showTime('returnTime','yyyy-MM-dd')"
												src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<td align="right">开始日期:</td>
											<td><input id="startTime" name="startTime" value="${saleReturnForm.startTime}" onfocus="showTime('startTime','yyyy-MM-dd')" onchange="salesOrderFieldChanged(this);" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span><img onclick="showTime('startTime','yyyy-MM-dd')"
												src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
											<td align="right">结束日期:</td>
											<td><input id="endTime" name="endTime" value="${saleReturnForm.endTime}" onfocus="showTime('endTime','yyyy-MM-dd')" onchange="salesOrderFieldChanged(this);" type="text" size="30" class="validate[required] text-input" /><span style="color: red;">*</span><img onclick="showTime('endTime','yyyy-MM-dd')"
												src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
										</tr>
										<tr>
											<td align="right">退货原因：</td>
											<td colspan="3"><textarea id="returnReason" class="example" rows="2" style="width: 500px">${saleReturnForm.returnReason }</textarea></td>
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

								<dd style="display: none;"></dd>
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
					<div id="a1" style="position: relative; height: 300px; z-index: 1; background: #FFF;">
						<div style="padding: 8px;">
							<table id="dlReturnFormItem" class="easyui-datagrid" style="height: auto; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlReturnFormItemTb',url: '${vix}/sales/delivery/saleReturnFormItemAction!getSaleReturnFormItemJson.action?id=${saleReturnForm.id}'">
								<thead>
									<tr>
										<th data-options="field:'id',align:'center',width:120">编号</th>
										<th data-options="field:'itemCode',align:'center',width:120,editor:'text'">${vv:varView('vix_mdm_item')}编码</th>
										<th data-options="field:'itemName',width:200,align:'center',editor:'text'">${vv:varView('vix_mdm_item')}名称</th>
										<th data-options="field:'specification',width:120,align:'center',editor:'text'">规格型号</th>
										<th data-options="field:'itemType',width:120,align:'center',editor:'text'">${vv:varView('vix_mdm_item')}类型</th>
										<th data-options="field:'amount',width:120,align:'center',editor:'numberbox'">退货数量</th>
										<th data-options="field:'netTotal',width:120,align:'center',editor:'numberbox'">金额</th>
										<th data-options="field:'netPrice',width:120,align:'center',editor:'numberbox'">单价</th>
									</tr>
								</thead>
							</table>
							<div id="dlReturnFormItemTb" style="height: auto">
								<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="addSaleReturnFormItem(0)"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)"
									class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-edit',plain:true" onclick="updateSaleReturnFormItem()"><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left"><s:text name="cmn_update" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
									data-options="iconCls:'icon-remove',plain:true" onclick="removeSaleReturnFormItem()"><span class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a>
							</div>
							<script type="text/javascript">
								$('#dlReturnFormItem').datagrid();
								function removeSaleReturnFormItem(){
									var row = $('#dlReturnFormItem').datagrid('getSelected');
									if(row){
										asyncbox.confirm('是否删除该明细信息?','提示信息',function(action){
											if(action == 'ok'){
												var index = $('#dlReturnFormItem').datagrid('getRowIndex',row);
												$('#dlReturnFormItem').datagrid('deleteRow', index);
												$.ajax({
													  url:'${vix}/sales/delivery/saleReturnFormItemAction!deleteById.action?id='+row.id,
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
								function updateSaleReturnFormItem(){
									var row = $('#dlReturnFormItem').datagrid('getSelected');
									if(row){
										addSaleReturnFormItem(row.id);
									}else{
										showMessage("请选择一条数据!");
									}
								}
								function addSaleReturnFormItem(id){
									$.ajax({
										  url:'${vix}/sales/delivery/saleReturnFormItemAction!goSaveOrUpdate.action?id='+id,
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
															if($('#saleReturnFormItemForm').validationEngine('validate')){
																$.post('${vix}/sales/delivery/saleReturnFormItemAction!saveOrUpdate.action',
																		{'saleReturnFormItem.id':$("#ddiId").val(),
																		  'saleReturnFormItem.specification':$("#specification").val(),
																		  'saleReturnFormItem.itemCode':$("#itemCode").val(),
																		  'saleReturnFormItem.itemName':$("#itemName").val(),
																		  'saleReturnFormItem.count':$("#ddiCount").val(),
																		  'saleReturnFormItem.price':$("#ddiPrice").val(),
																		  'saleReturnFormItem.unit':$("#ddiUnit").val(),
																		  'saleReturnFormItem.saleReturnForm.id':$("#id").val()
																		},
																		function(result){
																			showMessage(result);
																			$('#dlReturnFormItem').datagrid("load");
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
							<table id="dlApprovalOpinion" class="easyui-datagrid" style="height: auto; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#dlApprovalOpinionTb',url: '${vix}/sales/delivery/saleReturnFormApprovalRecordAction!getApprovalRecordJson.action?id=${saleReturnForm.id}'">
								<thead>
									<tr>
										<th data-options="field:'id',align:'center',width:120">编号</th>
										<th data-options="field:'checkPerson',width:200,align:'center',editor:'text'">审批人</th>
										<th data-options="field:'content',width:200,align:'center',editor:'text'">审批意见</th>
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
													  url:'${vix}/sales/delivery/saleReturnFormApprovalRecordAction!deleteById.action?id='+row.id,
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
										  url:'${vix}/sales/delivery/saleReturnFormApprovalRecordAction!goSaveOrUpdate.action?id=',
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
															$.post('${vix}/sales/delivery/saleReturnFormApprovalRecordAction!saveOrUpdate.action',
																	{'approvalRecord.content':$("#srfContent").val(),
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
								//格式化日期
								function formatDatebox(value) {
							         if (value == null || value == '') {
							             return '';
							         }
							     var dt;
							     if (value instanceof Date) {
							         dt = value;
							     }
							     else {
							         dt = new Date(value);
							         if (isNaN(dt)) {
							             value = value.replace(/\/Date\((-?\d+)\)\//, '$1'); 
							             dt = new Date();
							             dt.setTime(value);
							         }
							     }
							 
							    return dt.format("yyyy-MM-dd");
							 }
							</script>
						</div>
					</div>
					<div id="a3" style="width: 100%; visibility: hidden; position: absolute; top: -5000px;">
						<script type="text/javascript">
					$('#soAttach').datagrid({
						url: '${vix}/sales/delivery/saleReturnFormAction!getAttachmentsJson.action?id=${saleReturnForm.id}',
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
									  url:'${vix}/sales/delivery/saleReturnFormAction!addAttachments.action',
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
														uploadFile('${vix}/sales/delivery/saleReturnFormAction!uploadAttachments.action?id=${saleReturnForm.id}','fileToUpload');
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
									$.ajax({url:'${vix}/sales/delivery/saleReturnFormAction!deleteAttachFile.action?afId='+rows[i].id,cache: false});
								}
							}
						}]
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
<!-- content -->