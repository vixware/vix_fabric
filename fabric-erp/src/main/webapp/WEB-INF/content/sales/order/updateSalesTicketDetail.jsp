<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/page/taglibs.jsp"%>
<script type="text/javascript">
$("#salesTicketForm").validationEngine();
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
	<form id="salesTicketDetailForm">
		<s:hidden id="sdpdId" name="salesTicketDetail.id" value="%{salesTicketDetail.id}" theme="simple" />
		<div class="box order_table" style="line-height: normal;">
			<table class="table-padding020">
				<tr height="40">
					<th align="right">订单子项:&nbsp;</th>
					<td><input id="saleOrderItemId" name="salesTicketDetail.saleOrderItem.id" value="${salesTicketDetail.saleOrderItem.id}" type="hidden" /> <input id="orderItemName" name="salesTicketDetail.saleOrderItem.item.name" value="${salesTicketDetail.saleOrderItem.item.name}" type="text" class="validate[required] text-input" /> <a href="###"
						onclick="chooseSaleOrderItem();" class="abtn"><span>选择</span></a><span style="color: red;">*</span></td>
					<th align="right">数量:&nbsp;</th>
					<td><input id="ticketItemCount" name="salesTicketDetail.ticketItemCount" value="${salesTicketDetail.ticketItemCount}" type="text" class="validate[required] text-input" /> <span style="color: red;">*</span></td>
				</tr>
			</table>
		</div>
	</form>
</div>