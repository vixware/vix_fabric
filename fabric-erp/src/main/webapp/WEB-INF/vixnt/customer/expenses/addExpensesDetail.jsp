<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="expensesDetailForm" class="form-horizontal" style="padding:40px 40px;" action="${nvix}/nvixnt/nvixExpensesAction!saveOrUpdateExpensesDetail.action" method="post" enctype="multipart/form-data">
	<input id="expensesDetailId" name="expensesDetail.id" value="${expensesDetail.id }" type="hidden"/>
	<input id="expensesId" name="expensesDetail.expenses.id" value="${expensesDetail.expenses.id }" type="hidden"/>
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>费用项目:</label>
			<div class="col-md-4">
				<input id="expensesItem" type="text" name="expensesDetail.expensesItem" value="${expensesDetail.expensesItem}" data-prompt-position="topLeft" class="form-control validate[required]"/>
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>商品名称:</label>
			<div class="col-md-4">
				<input id="goodsName" type="text" name="expensesDetail.goodsName" value="${expensesDetail.goodsName}" data-prompt-position="topLeft" class="form-control validate[required]"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>规格型号:</label>
			<div class="col-md-4">
				<input id="specification" name="expensesDetail.specification" value="${expensesDetail.specification}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>支出金额:</label>
			<div class="col-md-4">
				<input id="amount" name="expensesDetail.amount" value="${expensesDetail.amount}" data-prompt-position="topLeft" class="form-control validate[required,custom[number]]" type="text" />
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	$("#expensesDetailForm").validationEngine();
</script>