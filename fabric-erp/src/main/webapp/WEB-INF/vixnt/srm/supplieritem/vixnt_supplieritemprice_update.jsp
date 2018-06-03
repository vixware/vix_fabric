<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="priceForm" class="form-horizontal" style="padding: 40px 40px;" action="${nvix}/nvixnt/vixntSupplierItemAction!updatePrice.action">
	<input type="hidden" id="id" name="id" value="${id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label">采购价格:</label>
			<div class="col-md-4">
				<input id="price" name="price" value="" class="form-control" type="text" />
			</div>
		</div>
	</fieldset>
</form>
