<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="scanSalesOrderItemForm" class="form-horizontal" style="padding:40px 15px;" >
	<fieldset>
		<div class="form-group">
			<label class="col-md-3 control-label"><span class="text-danger">*</span>商品编码:</label>
			<div class="col-md-8">
				<input id="barCode" name="barCode" value="${barCode}" type="text" data-prompt-position="topLeft" class="form-control validate[required]"  onchange="barCodeEnter($('#barCode').val());"/> 
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	$("#scanSalesOrderItemForm").validationEngine();
	
	function barCodeEnter(){
		var barCode = $("#barCode").val();
		if(null != barCode && barCode != '' && barCode != 'undefined'){
			$.post('${nvix}/nvixnt/nvixSalesOrderAction!saveOrUpdateOrderItem.action',
					{
					  'barCode':barCode,
					  'saleOrderItem.salesOrder.id':$("#id").val()
					},
					function(result){
						showMessage(result);
						$("#barCode").val('');
					}
				); 
		}
	}
</script>