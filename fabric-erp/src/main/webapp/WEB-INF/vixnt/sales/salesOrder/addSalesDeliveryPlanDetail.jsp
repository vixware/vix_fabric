<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="salesDeliveryPlanDetailForm" class="form-horizontal" style="padding:40px 40px;" action="${nvix}/nvixnt/nvixSalesOrderAction!saveOrUpdateSalesDeliveryPlanDetail.action" method="post" enctype="multipart/form-data">
	<input id="salesDeliveryPlanDetailId" name="salesDeliveryPlanDetail.id" value="${salesDeliveryPlanDetail.id }" type="hidden"/>
	<input id="salesDeliveryPlanId" name="salesDeliveryPlanDetail.salesDeliveryPlan.id" value="${salesDeliveryPlanDetail.salesDeliveryPlan.id }" type="hidden"/>
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>订单子项:</label>
			<div class="col-md-4">
				<div class="row">
					<div class="col-sm-12">
						<div class="input-group">
							<input id="saleOrderItemId" name="salesDeliveryPlanDetail.saleOrderItem.id" value="${salesDeliveryPlanDetail.saleOrderItem.id}" type="hidden"/>
							<input id="saleOrderItemName" type="text" value="${salesDeliveryPlanDetail.saleOrderItem.item.name}" data-prompt-position="topLeft" class="form-control validate[required]"
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
				<input id="saleOrderItemCount" name="salesDeliveryPlanDetail.deliveryPlanItemCount" value="${salesDeliveryPlanDetail.deliveryPlanItemCount}" data-prompt-position="topLeft" class="form-control validate[required,custom[number]]" type="text" />
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	$("#salesDeliveryPlanDetailForm").validationEngine();
</script>