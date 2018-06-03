<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="updatePriceForm" class="form-horizontal" style="padding: 40px 40px;" action="${nvix}/nvixnt/channel/vixntChannelOrderAction!updatePriceAndAmount.action">
	<input type="hidden" id="id" name="id" value="${id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label">销售价格:</label>
			<div class="col-md-4">
				<input id="price" name="price" value="" class="form-control" type="text" />
			</div>
			<label class="col-md-2 control-label">会员价格:</label>
			<div class="col-md-4">
				<input id="vipPrice" name="vipPrice" value="" class="form-control" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">数量:</label>
			<div class="col-md-4">
				<input id="avaquantity" name="avaquantity" value="" class="form-control" type="text" />
			</div>
		</div>
	</fieldset>
</form>
