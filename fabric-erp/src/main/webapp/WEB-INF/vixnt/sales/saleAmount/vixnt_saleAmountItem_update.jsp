<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="saleAmountItemForm" class="form-horizontal" style="padding: 35px 15px;" action="${nvix}/nvixnt/nvixntSaleAmountAction!saveOrUpdateSaleAmountItem.action">
	<input id="saleAmountItemReturnRuleId" name="saleAmountItem.saleAmount.id" value="${saleAmountItem.saleAmount.id}" type="hidden" />
	<input id="saleAmountItemId" name="saleAmountItem.id" value="${saleAmountItem.id}" type="hidden" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>期间(月份):</label>
			<div class="col-md-3">
				<div class="input-group">
					<input id="month" name="saleAmountItem.month" value="${saleAmountItem.month}" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'MM'})" type="text"/>
					<span class="input-group-addon" onclick="WdatePicker({dateFmt:'MM',el:'year'});"><i class="fa fa-calendar"></i></span>
				</div>
			</div>
			<label class="col-md-2 control-label">商品:</label>
			<div class="col-md-3">
				<div class="input-group">
					<input type="hidden" id="itemId" name="saleAmountItem.item.id" value="${saleAmountItem.item.id}"/>
					<input id="itemName" value="${saleAmountItem.item.name}" onfocus="chooseItem();" data-prompt-position="topLeft" class="form-control validate[required]" type="text" readonly="readonly" />
					<span class="input-group-addon" style="cursor:pointer;" onclick="chooseItem();">选择</span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">期间金额:</label>
			<div class="col-md-3">
				<input id="saleAmountQuota" type="text" name="saleAmountItem.saleAmountQuota" value="${saleAmountItem.saleAmountQuota}"  class="form-control validate[required]">
			</div>
			<label class="col-md-2 control-label">期间数量:</label>
			<div class="col-md-3">
				<input id="saleCountQuota" type="text" name="saleAmountItem.saleCountQuota" value="${saleAmountItem.saleCountQuota}"  class="form-control validate[required]">
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	$(function(){
		if(!$("#saleAmountItemReturnRuleId").val()){
			$("#saleAmountItemReturnRuleId").val($("#saleAmountId").val());
		}
	})
	function chooseItem(){
		chooseListData('${nvix}/nvixnt/vixntProductAssemblyAction!goChooseItem.action', 'single', '选择商品','item');
	}
	$("#saleAmountItemForm").validationEngine();
</script>