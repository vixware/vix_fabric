<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="invWarehouseForm" class="form-horizontal" style="padding: 40px 40px;" action="${nvix}/nvixnt/vixntStoreGoodsAction!saveOrUpdate.action">
	<input type="hidden" id="id" name="id" value="${id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label">数量:</label>
			<div class="col-md-4">
				<input id="avaquantity" name="avaquantity" value="" class="form-control" type="text" />
			</div>
		</div>
	</fieldset>
</form>
