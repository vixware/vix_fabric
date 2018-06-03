<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#salesDeliveryPlanDetailForm").validationEngine();
function chooseSaleOrderItem(){
	$.ajax({
		  url:'${vix}/sales/order/salesOrderAction!goChooseSalesOrderItem.action?id='+$("#id").val(),
		  cache: false,
		  success: function(html){
			  asyncbox.open({
				 	modal:true,
					width : 960,
					height : 500,
					title:"选择订单明细",
					html:html,
					callback : function(action,returnValue){
						if(action == 'ok'){
							if(returnValue != undefined){
								var result = returnValue.split(":");
								$("#saleOrderItemId").val(result[0]);
								$("#orderItemName").val(result[1]);
							}else{
								$("#saleOrderItemId").val("");
								$("#orderItemName").val("");
							}
						}
					},
					btnsbar : $.btn.OKCANCEL
				});
		  }
	});
}
</script>
<div class="content" style="margin-top: 15px; overflow: hidden">
	<form id="salesDeliveryPlanDetailForm">
		<s:hidden id="sdpdId" name="salesDeliveryPlanDetail.id" value="%{salesDeliveryPlanDetail.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">订单子项:&nbsp;</th>
					<td><input id="saleOrderItemId" name="salesDeliveryPlanDetail.saleOrderItem.id" value="${salesDeliveryPlanDetail.saleOrderItem.id}" type="hidden" /> <input id="orderItemName" name="salesDeliveryPlanDetail.saleOrderItem.item.name" value="${salesDeliveryPlanDetail.saleOrderItem.item.name}" type="text" class="validate[required] text-input" />
						<a href="###" onclick="chooseSaleOrderItem();" class="abtn"><span>选择</span></a><span style="color: red;">*</span></td>
					<th align="right">数量:&nbsp;</th>
					<td><input id="deliveryPlanItemCount" name="salesDeliveryPlanDetail.deliveryPlanItemCount" value="${salesDeliveryPlanDetail.deliveryPlanItemCount}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
			</table>
		</div>
	</form>
</div>