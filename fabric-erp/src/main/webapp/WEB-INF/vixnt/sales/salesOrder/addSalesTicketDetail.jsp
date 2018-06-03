<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="salesTicketDetailForm" class="form-horizontal" style="padding:40px 40px;" action="${nvix}/nvixnt/nvixSalesOrderAction!saveOrUpdateSalesTicketDetail.action" method="post" enctype="multipart/form-data">
	<input id="salesTicketDetailId" name="salesTicketDetail.id" value="${salesTicketDetail.id }" type="hidden"/>
	<input id="saleTicketId" name="salesTicketDetail.salesTicket.id" value="${salesTicketDetail.salesTicket.id }" type="hidden"/>
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>订单子项:</label>
			<div class="col-md-4">
				<div class="row">
					<div class="col-sm-12">
						<div class="input-group">
							<input id="saleOrderItemId" name="salesTicketDetail.saleOrderItem.id" value="${salesTicketDetail.saleOrderItem.id}" type="hidden"/>
							<input id="saleOrderItemName" type="text" value="${salesTicketDetail.saleOrderItem.item.name}" data-prompt-position="topLeft" class="form-control validate[required]"
								onfocus="chooseListData('${nvix}/nvixnt/nvixSalesOrderAction!goChooseSalesOrderItem.action','','选择订单子项','saleOrderItem',null,720,450);"/>
							<div class="input-group-btn">
								<button type="button" class="btn btn-default" onclick="$('#saleOrderItemId').val('');$('#saleOrderItemName').val('');$('#saleOrderItemCount').val('');">清空</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>数量:</label>
			<div class="col-md-3">
				<input id="saleOrderItemCount" name="salesTicketDetail.ticketItemCount" value="${salesTicketDetail.ticketItemCount}" data-prompt-position="topLeft" class="form-control validate[required,custom[number]]" type="text" />
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	$("#salesTicketDetailForm").validationEngine();
</script>