<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript" src="${vix}/plugin/priceFormat/jquery.price_format.1.7.js"></script>
<script type="text/javascript">
//打印
function goPrintSaleReturnForm(id) {
	$.ajax({
	url : '${vix}/sales/delivery/saleReturnFormAction!goPrintSaleReturnForm.action?id=' + id,
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
<s:if test="tag != 'window'">
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
					<li><a href="#" onclick="loadContent('${vix}/sales/delivery/saleReturnFormAction!goList.action');">销售退换货单</a></li>
				</ul>
			</div>
		</h2>
	</div>
</s:if>
<div class="content">
	<div class="order">
		<dl>
			<dt id="orderTitleFixd">
				<%-- <s:if test="tag != 'window'">
					<span class="no_line">
						<s:if test="taskId == null">
							<a href="#" onclick="loadContent('${vix}/sales/order/salesOrderAction!goList.action');"><img width="22" height="22" alt="返回" src="${vix}/common/img/dt_goback.png"/></a>
						</s:if>
						<s:else>
							<a onclick="goAudit('${taskId}');" href="###"><img width="22" height="22" alt="提交审批" src="${vix}/common/img/dt_aprroval.png"/></a>
							<a href="#" onclick="loadContent('${vix}/common/model/jobTodoAction!goList.action');"><img width="22" height="22" alt="返回" src="${vix}/common/img/dt_goback.png"/></a>
						</s:else>
					</span>
				</s:if> --%>
				<span class="no_line"> <a href="#"><img width="22" height="22" title="禁用" src="${vix}/common/img/dt_disable.png" /> </a> <a href="#"><img width="22" height="22" title="锁定" src="${vix}/common/img/dt_locked.png" /> </a> <a href="#"><img width="22" height="22" title="删除" src="${vix}/common/img/dt_delete.png" /> </a> <a href="#"><img
						width="22" height="22" title="审批通过" src="${vix}/common/img/dt_aprroval.png" /> </a> <a href="#"><img width="22" height="22" title="驳回" src="${vix}/common/img/dt_reject.png"> </a> <a href="#" onclick="goShowBeforeAndAfter('${saleReturnForm.id }','before');"><img width="22" height="22" title="上一条" src="${vix}/common/img/dt_before.png">
				</a> <a href="#" onclick="goShowBeforeAndAfter('${saleReturnForm.id }','after');"><img width="22" height="22" title="下一条" src="${vix}/common/img/dt_next.png"> </a> <a href="#" onclick="goPrintSaleReturnForm('${saleReturnForm.id}');"><img width="22" height="22" title="打印" src="${vix}/common/img/dt_print.png"> </a>
					<div class="ms" style="float: none; display: inline;">
						<a href="#"><img width="22" height="22" alt="关联单据" src="${vix}/common/img/dt_report.png"> </a>
						<ul style="display: none; overflow: hidden; height: 124px; margin-top: 0px; margin-bottom: 0px; padding-top: 0px; padding-bottom: 0px;">
							<li><a href="#" onclick="goPrintSalesQuotation('${saleReturnForm.id}');"><img src="${vix}/common/img/icon_10.png" alt="">引用单据</a></li>
						</ul>
					</div> <a href="#"><img width="22" height="22" title="导出" src="${vix}/common/img/dt_export.png"> </a> <a href="#" onclick="loadContent('${vix}/sales/delivery/saleReturnFormAction!goList.action?pageNo=${pageNo}');"><img width="22" height="22" title="返回" src="${vix}/common/img/dt_goback.png"> </a>
				</span> <strong> <b> 销售退换货单 </b> <i></i>
				</strong>
			</dt>
			<dd class="clearfix">
				<div class="order_table">
					<div class="voucher newvoucher" style="margin: 0;">
						<dl>
							<dt>
								<b></b> <strong>基本信息</strong>
							</dt>
							<dd style="display: block;">
								<table class="addtable_c">
									<tr>
										<td align="right">退货单编码：</td>
										<td>${saleReturnForm.returnOrderCode }</td>
										<td align="right">单据类型：</td>
										<td>${saleReturnForm.returnGoodsType.name}</td>
									</tr>
									<tr>
										<td align="right">客户名称：</td>
										<td>${saleReturnForm.customerAccount.name}</td>
										<td align="right">来源：</td>
										<td>${saleReturnForm.source }</td>
									</tr>
									<tr>
										<td align="right">运输方式：</td>
										<td>${saleReturnForm.transferStyle }</td>
										<td align="right">退货日期：</td>
										<td><s:property value='formatDateToString(saleReturnForm.returnTime )' /></td>
									</tr>
									<tr>
										<td align="right">开始日期:</td>
										<td><s:property value='formatDateToString(saleReturnForm.startTime )' /></td>
										<td align="right">结束日期:</td>
										<td><s:property value='formatDateToString(saleReturnForm.endTime )' /></td>
									</tr>
									<tr>
										<td align="right">退货原因：</td>
										<td>${saleReturnForm.returnReason }</td>
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
						url: '${vix}/sales/saleReturnAction!getAttachmentsJson.action?id=${saleReturnForm.id}',
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
									  url:'${vix}/sales/salesDeliveryAction!addAttachments.action',
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
														uploadFile('${vix}/sales/saleReturnAction!uploadAttachments.action?id=${saleReturnForm.id}','fileToUpload');
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
									$.ajax({url:'${vix}/sales/saleReturnAction!deleteAttachFile.action?afId='+rows[i].id,cache: false});
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
	<!--oder-->
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