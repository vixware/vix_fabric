<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="orderTypeForm" class="form-horizontal" style="padding: 35px 15px;" action="${nvix}/nvixnt/purchase/vixntPurchaseOrderTypeAction!saveOrUpdate.action">
	<input type="hidden" id="id" name="orderType.id" value="${orderType.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 编码:</label>
			<div class="col-md-3">
				<input id="code" name="orderType.code" value="${orderType.code}" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 名称:</label>
			<div class="col-md-3">
				<input id="name" name="orderType.name" value="${orderType.name}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">备注:</label>
			<div class="col-md-8">
				<textarea name="orderType.memo" class="form-control">${orderType.memo} </textarea>
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	$("#orderTypeForm").validationEngine();
</script>