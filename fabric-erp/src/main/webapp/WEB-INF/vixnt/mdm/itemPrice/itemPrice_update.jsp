<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<input type="hidden" id="id" name="itemPrice.id" value="${itemPrice.id}" /> 
<input type="hidden" id="startDate" name="itemPrice.startDate" value="${itemPrice.startDate}" /> 
<input type="hidden" id="endDate" name="itemPrice.endDate" value="${itemPrice.endDate}" /> 
<form id="itemPriceForm" class="form-horizontal" style="padding: 40px 40px;" action="${nvix}/nvixnt/nvixntItemPriceAction!saveOrUpdate.action">
	<input type="hidden" id="id" name="currencyType.id" value="${currencyType.id}" /> 
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>最高价格:</label>
			<div class="col-md-4">
				<input id="maxPrice" name="itemPrice.maxPrice" value="${itemPrice.maxPrice}" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>最低价格:</label>
			<div class="col-md-4">
				<input id="minPrice" name="itemPrice.minPrice" value="${itemPrice.minPrice}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>SKU:</label>
			<div class="col-md-4">
				<select id="itemSku" name="itemPrice.itemSku" data-prompt-position="topLeft" class="form-control validate[required]">
					<option value="">------请选择------</option>
				</select>
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>价格:</label>
			<div class="col-md-4">
				<input id="price" name="itemPrice.price" value="${itemPrice.price}" class="form-control validate[required]" type="text" />
			</div>
		</div>
	</fieldset>
</form>