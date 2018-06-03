<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="dimensionDetailForm" class="form-horizontal" style="padding:40px 40px;" action="${nvix}/nvixnt/mdm/nvixDimensionAction!saveOrUpdateDimensionDetail.action">
	<input type="hidden" id="detailId" name = "dimensionDetail.id" value="${dimensionDetail.id}"/>
	<input type="hidden" id="dimensionId" name = "dimensionDetail.dimension.id" value="${dimensionDetail.dimension.id}"/>
	<fieldset>
		<div class="form-group">
			<label class="col-md-3 control-label"><span class="text-danger">*</span>名称:</label>
			<div class="col-md-8">
				<input id="name" name="dimensionDetail.name" value="${dimensionDetail.name}" data-prompt-position="topLeft" class="form-control validate[required]" type="text"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label"><span class="text-danger">*</span>顺序:</label>
			<div class="col-md-8">
				<input id="orderBy" name="dimensionDetail.orderBy" value="${dimensionDetail.orderBy}" data-prompt-position="topLeft" class="form-control validate[required,custom[number]]" type="text"/>
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	$("#dimensionDetailForm").validationEngine();
</script>