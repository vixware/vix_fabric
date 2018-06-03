<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="chooseConditionForm" class="form-horizontal" style="padding: 35px 15px;" action="${nvix}/nvixnt/nvixntSaleReturnBillAction!createSaleReturnBill.action">
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>客户:</label>
			<div class="col-md-3">
				<div class="input-group">
					<input type="hidden" id="customerId" name="customerAccountId" value=""/>
					<input id="customerName" value="" onfocus="chooseCustomer();" data-prompt-position="topLeft" class="form-control validate[required]" type="text" readonly="readonly" />
					<span class="input-group-addon" style="cursor:pointer;" onclick="">选择</span>
				</div>
			</div>			
			<label class="col-md-2 control-label">商品:</label>
			<div class="col-md-3">
				<div class="input-group">
					<input type="hidden" id="itemId" name="itemId" value=""/>
					<input id="itemName" value="" onfocus="chooseItem();" data-prompt-position="topLeft" class="form-control" type="text" readonly="readonly" />
					<span class="input-group-addon" style="cursor:pointer;" onclick="chooseItem();">选择</span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>开始时间:</label>
			<div class="col-md-3">
				<div class="input-group">
					<input id="startTime" name="startTime" value="" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy/MM/dd'})" type="text" /> <span class="input-group-addon"
						onclick="WdatePicker({dateFmt:'yyyy/MM/dd',el:'startTime'});"><i class="fa fa-calendar"></i></span>
				</div>
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>结束时间:</label>
			<div class="col-md-3">
				<div class="input-group">
					<input id="endTime" name="endTime" value="" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy/MM/dd'})" type="text" /> <span class="input-group-addon"
						onclick="WdatePicker({dateFmt:'yyyy/MM/dd',el:'endTime'});"><i class="fa fa-calendar"></i></span>
				</div>
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	$("#orderTypeForm").validationEngine();
	function chooseCustomer() {
		chooseListData('${nvix}/nvixnt/nvixCustomerAction!goChooseCustomerAccount.action', 'single', '选择客户', 'customer');
	}
	function chooseItem(){
		chooseListData('${nvix}/nvixnt/vixntProductAssemblyAction!goChooseItem.action', 'single', '选择商品','item');
	}
</script>