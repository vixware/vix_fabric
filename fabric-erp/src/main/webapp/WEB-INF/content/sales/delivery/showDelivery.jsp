<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script src="${vix}/common/js/LodopFuncs.js" type="text/javascript"></script>
<script type="text/javascript">
//打印
function goPrintDelivery(id) {
	$.ajax({
	url : '${vix}/sales/delivery/deliveryDocumentAction!goPrintDelivery.action?id=' + id,
	cache : false,
	success : function(html) {
		LODOP = getLodop();
		LODOP.ADD_PRINT_HTM(0, 0, "100%", "100%", html);
		LODOP.SET_PRINT_MODE("PRINT_PAGE_PERCENT", "Auto-Width");
		LODOP.SET_PRINT_MODE("AUTO_CLOSE_PREWINDOW", 1);// 打印后自动关闭预览窗口
		LODOP.SET_SHOW_MODE("HIDE_PAPER_BOARD", 1);
		// LODOP.SET_PRINT_PAGESIZE(3,"240mm","45mm","");//这里3表示纵向打印且纸高“按内容的高度”；1385表示纸宽138.5mm；45表示页底空白4.5mm
		LODOP.SET_PREVIEW_WINDOW(1, 0, 0, 1024, 550, ""); // 2上下打印工具条都显示
		/* LODOP.PRINT(); */
		LODOP.PREVIEW();
	}
	});
};
</script>
<div class="sub_menu">
	<h2>
		<div id="breadCrumb" class="breadCrumb module">
			<ul>
				<li><a href="#"><img style="width: 14px; height: 14px;" src="${vix}/common/img/sale/saleQuotes.png" alt="" />
					<s:text name="cmn_home" /></a></li>
				<li><a href="#">销售管理</a></li>
				<li><a href="#" onclick="loadContent('${vix}/sales/delivery/deliveryDocumentAction!goList.action');">销售发货单</a></li>
			</ul>
		</div>
	</h2>
</div>
<div class="content">
	<div class="order">
		<dl>
			<dt id="orderTitleFixd">
				<span class="no_line"> <a href="#"><img width="22" height="22" title="禁用" src="${vix}/common/img/dt_disable.png" /> </a> <a href="#"><img width="22" height="22" title="锁定" src="${vix}/common/img/dt_locked.png" /> </a> <a href="#"><img width="22" height="22" title="删除" src="${vix}/common/img/dt_delete.png" /> </a> <a href="#"><img
						width="22" height="22" title="审批通过" src="${vix}/common/img/dt_aprroval.png" /> </a> <a href="#"><img width="22" height="22" title="驳回" src="${vix}/common/img/dt_reject.png"> </a> <a href="#" onclick="goShowBeforeAndAfter('${deliveryDocument.id }','before');"><img width="22" height="22" title="上一条" src="${vix}/common/img/dt_before.png">
				</a> <a href="#" onclick="goShowBeforeAndAfter('${deliveryDocument.id }','after');"><img width="22" height="22" title="下一条" src="${vix}/common/img/dt_next.png"> </a> <a href="#" onclick="goPrintDelivery('${deliveryDocument.id}');"><img width="22" height="22" title="打印" src="${vix}/common/img/dt_print.png"> </a>
					<div class="ms" style="float: none; display: inline;">
						<a href="#"><img width="22" height="22" alt="关联单据" src="${vix}/common/img/dt_report.png"> </a>
						<ul style="display: none; overflow: hidden; height: 124px; margin-top: 0px; margin-bottom: 0px; padding-top: 0px; padding-bottom: 0px;">
							<li><a href="#" onclick="goPrintDelivery('${deliveryDocument.id}');"><img src="${vix}/common/img/icon_10.png" alt="">引用单据</a></li>
						</ul>
					</div> <a href="#"><img width="22" height="22" title="导出" src="${vix}/common/img/dt_export.png"> </a> <a href="#" onclick="loadContent('${vix}/sales/delivery/deliveryDocumentAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
				</span> <strong> <b> 销售发货单 </b> <i></i>
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
										<td align="right">发货单编码：</td>
										<td>${deliveryDocument.ddCode }</td>
										<td align="right">客户名称：</td>
										<td>${deliveryDocument.customerAccount.name}<input type="hidden" id="customerAccountId" name="customerAccountId" value="${deliveryDocument.customerAccount.id}" />
										</td>
									</tr>
									<tr>
										<td align="right">发运地：</td>
										<td>${deliveryDocument.source }</td>
										<td align="right">目的地：</td>
										<td>${deliveryDocument.target }</td>
									</tr>
									<tr>
										<td align="right">单据类型：</td>
										<td>${deliveryDocument.salesBillType.name}</td>
										<td align="right">业务类型：</td>
										<td>${deliveryDocument.salesBusinessType.name}</td>
									</tr>
									<tr>
										<td align="right">金额：</td>
										<td>${deliveryDocument.amount }</td>
										<td align="right">业务员：</td>
										<td>${deliveryDocument.salePerson.name}<input type="hidden" id="salePersonId" value="${deliveryDocument.salePerson.id}" />
										</td>
									</tr>
									<tr>
										<td align="right">交货日期：</td>
										<td><s:property value='formatDateToString(deliveryDocument.deliveryTime )' /></td>
										<td align="right">发货日期：</td>
										<td><s:property value='formatDateToString(deliveryDocument.shippedDate )' /></td>
										</td>
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
			<dd class="clearfix">
				<div class="order_table">
					<table>
						<tr>
							<td width="15%" align="right">报价人:</td>
							<td width="35%">${salesQuotation.salesMan.name}</td>
							<td width="10%" align="right">金额:</td>
							<td width="40%">${salesQuotation.amount}</td>
						</tr>
					</table>
				</div>
			</dd>
		</dl>
	</div>
</div>
<!-- content -->
