<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#salesDeliveryPlanForm").validationEngine();
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="salesDeliveryPlanForm">
		<s:hidden id="sdpId" name="salesDeliveryPlan.id" value="%{salesDeliveryPlan.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">发运时间:&nbsp;</th>
					<td><input id="sdpDeliveryTime" name="salesDeliveryPlan.deliveryTime" value="${salesDeliveryPlan.deliveryTime}" type="text" class="validate[required] text-input" onfocus="showTime('sdpDeliveryTime','yyyy-MM-dd HH:mm')" /> <span style="color: red;">*</span><img onclick="showTime('sdpDeliveryTime','yyyy-MM-dd HH:mm')"
						src="${vix}/common/img/datePicker.gif" width="16" height="22" align="absmiddle"></td>
					<th align="right">国家:&nbsp;</th>
					<td><input id="country" name="salesDeliveryPlan.country" value="${salesDeliveryPlan.country}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">省:&nbsp;</th>
					<td><input id="province" name="salesDeliveryPlan.province" value="${salesDeliveryPlan.province}" type="text" /></td>
					<th align="right">城市:&nbsp;</th>
					<td><input id="city" name="salesDeliveryPlan.city" value="${salesDeliveryPlan.city}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">目的地:&nbsp;</th>
					<td><input id="target" name="salesDeliveryPlan.target" value="${salesDeliveryPlan.target}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right">承运人:&nbsp;</th>
					<td><input id="carrier" name="salesDeliveryPlan.carrier" value="${salesDeliveryPlan.carrier}" type="text" /></td>
				</tr>
				<tr height="40">
					<th align="right">收货人:&nbsp;</th>
					<td><input id="reciever" name="salesDeliveryPlan.reciever" value="${salesDeliveryPlan.reciever}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
					<th align="right"></th>
					<td></td>
				</tr>
			</table>
			<div style="padding: 8px;">
				<table id="salesDeliveryPlanDetail" class="easyui-datagrid" style="height: 150px; width: 550px; margin: 6px;" data-options="iconCls: 'icon-edit',singleSelect: true,toolbar: '#salesDeliveryPlanDetailTb',url: '${vix}/sales/order/salesDeliveryPlanAction!getSalesDeliveryPlanDetailJson.action?id=${salesDeliveryPlan.id}'">
					<thead>
						<tr>
							<th data-options="field:'id',align:'center',width:60">编号</th>
							<th data-options="field:'saleOrderItem.item.name',width:220,align:'center',editor:'text'">${vv:varView('vix_mdm_item')}</th>
							<th data-options="field:'deliveryPlanItemCount',width:120,align:'center',editor:'text'">数量</th>
						</tr>
					</thead>
				</table>
				<div id="salesDeliveryPlanDetailTb" style="height: auto">
					<a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-add',plain:true" onclick="addDeliveryPlanDetail(0)"><span class="l-btn-left"><span class="l-btn-text icon-add l-btn-icon-left"><s:text name="cmn_add" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain"
						data-options="iconCls:'icon-edit',plain:true" onclick="updateDeliveryPlanDetail()"><span class="l-btn-left"><span class="l-btn-text icon-edit l-btn-icon-left"><s:text name="cmn_update" /></span></span></a> <a href="javascript:void(0)" class="easyui-linkbutton l-btn l-btn-plain" data-options="iconCls:'icon-remove',plain:true"
						onclick="removeDeliveryPlanDetail()"><span class="l-btn-left"><span class="l-btn-text icon-remove l-btn-icon-left"><s:text name="cmn_delete" /></span></span></a>
				</div>
				<script type="text/javascript">
					$('#salesDeliveryPlanDetail').datagrid();
					function removeDeliveryPlanDetail(){
						var row = $('#salesDeliveryPlanDetail').datagrid('getSelected');
						if(row){
							asyncbox.confirm('是否删除该发运计划明细?','提示信息',function(action){
								if(action == 'ok'){
									var index = $('#salesDeliveryPlanDetail').datagrid('getRowIndex',row);
									$('#salesDeliveryPlanDetail').datagrid('deleteRow', index);
									$.ajax({
										  url:'${vix}/sales/order/salesDeliveryPlanAction!deleteDetailById.action?id='+row.id,
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
					function updateDeliveryPlanDetail(){
						var row = $('#salesDeliveryPlanDetail').datagrid('getSelected');
						if(row){
							addDeliveryPlanDetail(row.id);
						}else{
							showMessage("请选择一条数据!");
						}
					}
					function addDeliveryPlanDetail(id){
						$.ajax({
							  url:'${vix}/sales/order/salesDeliveryPlanAction!goSaveOrUpdateDetail.action?id='+id,
							  cache: false,
							  success: function(html){
								  asyncbox.open({
									 	modal:true,
										width : 660,
										height :200,
										title:"发运计划明细",
										html:html,
										callback : function(action){
											if(action == 'ok'){
												if($('#salesDeliveryPlanDetailForm').validationEngine('validate')){
													$.post('${vix}/sales/order/salesDeliveryPlanAction!saveOrUpdateDetail.action',
															{'salesDeliveryPlanDetail.id':$("#sdpdId").val(),
															  'salesDeliveryPlanDetail.saleOrderItem.id':$("#saleOrderItemId").val(),
															  'salesDeliveryPlanDetail.deliveryPlanItemCount':$("#deliveryPlanItemCount").val(),
															  'salesDeliveryPlanDetail.salesDeliveryPlan.id':$("#sdpId").val()
															},
															function(result){
																showMessage(result);
																setTimeout("",1000);
																$('#salesDeliveryPlanDetail').datagrid("reload");
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