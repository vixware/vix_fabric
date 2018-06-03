<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="salesInvoiceItemForm" class="form-horizontal" style="padding:40px 40px;" action="${nvix}/nvixnt/nvixSalesInvoiceAction!saveOrUpdateSalesInvoiceItem.action" method="post" enctype="multipart/form-data">
	<input id="salesInvoiceItemId" name="salesInvoiceItem.id" value="${salesInvoiceItem.id }" type="hidden"/>
	<input id="salesInvoiceId" name="salesInvoiceItem.salesInvoice.id" value="${salesInvoiceItem.salesInvoice.id }" type="hidden"/>
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>商品编码:</label>
			<div class="col-md-4">
				<input id="itemCode" type="text" name="salesInvoiceItem.itemCode" value="${salesInvoiceItem.itemCode}" data-prompt-position="topLeft" class="form-control validate[required]" style="cursor: pointer;" readonly="readonly"/>
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>商品名称:</label>
			<div class="col-md-4">
				<div class="row">
					<div class="col-sm-12">
						<div class="input-group">
							<input id="itemName" type="text" name="salesInvoiceItem.itemName" value="${salesInvoiceItem.itemName}" data-prompt-position="topLeft" class="form-control validate[required]"
								onfocus="chooseListData('${nvix}/nvixnt/nvixnt/nvixSalesInvoiceAction!goChooseItem.action','','选择商品','item');" style="cursor: pointer;" readonly="readonly"/>
							<div class="input-group-btn">
								<button type="button" class="btn btn-default" onclick="$('#itemCode').val('');$('#itemName').val('');$('#itemPrice').val('');">清空</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>单价:</label>
			<div class="col-md-4">
				<input id="itemPrice" name="salesInvoiceItem.price" value="${salesInvoiceItem.price}" data-prompt-position="topLeft" class="form-control validate[required,custom[number]]" type="text" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>数量:</label>
			<div class="col-md-4">
				<input id="amount" name="salesInvoiceItem.amount" value="${salesInvoiceItem.amount}" data-prompt-position="topLeft" class="form-control validate[required,custom[number]]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">税率:</label>
			<div class="col-md-4">
				<div class="input-group">
					<input id="tax" name="salesInvoiceItem.tax" value="${salesInvoiceItem.tax}" data-prompt-position="topLeft" class="form-control validate[custom[number],min[1],max[100]]" type="text" />
					<span class="input-group-addon">(1-100)%</span>
				</div>
			</div>
			<label class="col-md-2 control-label">货币类型:</label>
			<div class="col-md-4">
				<select id="currencyTypeId" name="salesInvoiceItem.currencyType.id" class="form-control">
					<option value="">------请选择------</option>
					<c:forEach items="${currencyTypeList}" var="entity">
						<option value="${entity.id}" <c:if test="${salesInvoiceItem.currencyType.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
					</c:forEach>
				</select>
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	$("#salesInvoiceItemForm").validationEngine();
</script>