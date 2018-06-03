<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#salesTicketForm").validationEngine();
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="salesTicketForm">
		<s:hidden id="stId" name="salesTicket.id" value="%{salesTicket.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">计划开票日期:&nbsp;</th>
					<td><input id="planTicketDate" name="salesTicket.planTicketDate" value="${salesTicket.planTicketDate}" type="text" class="validate[required] text-input" onfocus="showTime('planTicketDate','yyyy-MM-dd HH:mm:ss')" /> <span style="color: red;">*</span><img onclick="showTime('planTicketDate','yyyy-MM-dd HH:mm:ss')"
						src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
					<th align="right">名称:&nbsp;</th>
					<td><input id="stTitle" name="salesTicket.title" value="${salesTicket.title}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">纳税人识别号:&nbsp;</th>
					<td><input id="taxpayerPlayer" name="salesTicket.taxpayerPlayer" value="${salesTicket.taxpayerPlayer}" type="text" /></td>
					<th align="right">开户行及帐号:&nbsp;</th>
					<td><input id=bank name="salesTicket.bank" value="${salesTicket.bank}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">税率:&nbsp;</th>
					<td><input id="bankAccount" name="salesTicket.bankAccount" value="${salesTicket.bankAccount}" type="text" /></td>
					<th align="right">税额:&nbsp;</th>
					<td><input id="taxRate" name="salesTicket.taxRate" value="${salesTicket.taxRate}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">发票金额:&nbsp;</th>
					<td><input id="ticketAmount" name="salesTicket.ticketAmount" value="${salesTicket.ticketAmount}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">开票数量:&nbsp;</th>
					<td><input id="ticketCount" name="salesTicket.ticketCount" value="${salesTicket.ticketCount}" type="text" class="validate[required] text-input" /><span style="color: red;">*</span></td>
				</tr>
				<tr height="40">
					<th align="right">描述:&nbsp;</th>
					<td><input id="memo" name="salesTicket.memo" value="${salesTicket.memo}" type="text" /></td>
					<th align="right">开票类型:&nbsp;</th>
					<td><input id="ticketType" name="salesTicket.ticketType" value="${salesTicket.ticketType}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">开票内容:&nbsp;</th>
					<td><input id="ticketContent" name="salesTicket.content" value="${salesTicket.content}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">是否冻结:&nbsp;</th>
					<td><s:if test="salesTicket.isFreeze == 0">
							<input type="radio" id="isFreeze1" name="isFreeze" value="1" />是
							<input type="radio" id="isFreeze2" name="isFreeze" value="0" checked="checked" />否
						</s:if> <s:elseif test="salesTicket.isFreeze == 1">
							<input type="radio" id="isFreeze1" name="isFreeze" value="1" checked="checked" />是
							<input type="radio" id="isFreeze2" name="isFreeze" value="0" />否
						</s:elseif> <s:else>
							<input type="radio" id="isFreeze1" name="isFreeze" value="1" />是
							<input type="radio" id="isFreeze2" name="isFreeze" value="0" checked="checked" />否
						</s:else></td>
				</tr>
			</table>
			<div style="padding: 8px;">
				<table id="salesTicketDetail" class="easyui-datagrid" style="height: 150px; width: 550px; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#salesTicketDetailTb',url: '${vix}/sales/order/salesTicketAction!getSalesTicketDetailJson.action?id=${salesTicket.id}'">
					<thead>
						<tr>
							<th data-options="field:'id',align:'center',width:60">编号</th>
							<th data-options="field:'saleOrderItem.item.name',width:220,align:'center',editor:'text'">${vv:varView('vix_mdm_item')}</th>
							<th data-options="field:'deliveryPlanItemCount',width:120,align:'center',editor:'text'">数量</th>
						</tr>
					</thead>
				</table>
				<div id="salesTicketDetailTb" style="height: auto">
					<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="addTicketDetail(0)"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
						data-options="iconCls:'icon-edit',plain:true" onclick="updateTicketDetail()"><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left"><s:text name="cmn_update" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-remove',plain:true"
						onclick="removeTicketDetail()"><span class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a>
				</div>
				<script type="text/javascript">
					$('#salesTicketDetail').datagrid();
					function removeTicketDetail(){
						var row = $('#salesTicketDetail').datagrid('getSelected');
						if(row){
							asyncbox.confirm('是否删除该发票明细?','提示信息',function(action){
								if(action == 'ok'){
									var index = $('#salesTicketDetail').datagrid('getRowIndex',row);
									$('#salesTicketDetail').datagrid('deleteRow', index);
									$.ajax({
										  url:'${vix}/sales/order/salesTicketAction!deleteDetailById.action?id='+row.id,
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
					function updateTicketDetail(){
						var row = $('#salesTicketDetail').datagrid('getSelected');
						if(row){
							addTicketDetail(row.id);
						}else{
							showMessage("请选择一条数据!");
						}
					}
					function addTicketDetail(id){
						$.ajax({
							  url:'${vix}/sales/order/salesTicketAction!goSaveOrUpdateDetail.action?id='+id,
							  cache: false,
							  success: function(html){
								  asyncbox.open({
									 	modal:true,
										width : 660,
										height :200,
										title:"发票明细",
										html:html,
										callback : function(action){
											if(action == 'ok'){
												if($('#salesTicketDetailForm').validationEngine('validate')){
													$.post('${vix}/sales/order/salesTicketAction!saveOrUpdateDetail.action',
															{'salesTicketDetail.id':$("#sdpdId").val(),
															  'salesTicketDetail.saleOrderItem.id':$("#saleOrderItemId").val(),
															  'salesTicketDetail.ticketItemCount':$("#ticketItemCount").val(),
															  'salesTicketDetail.salesTicket.id':$("#sdpId").val()
															},
															function(result){
																showMessage(result);
																setTimeout("",1000);
																$('#salesTicketDetail').datagrid("reload");
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