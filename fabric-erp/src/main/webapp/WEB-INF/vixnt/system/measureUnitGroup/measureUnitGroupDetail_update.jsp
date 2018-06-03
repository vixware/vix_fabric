<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="measureUnitGroupDetailForm" class="form-horizontal" style="padding: 20px 20px;" action="${nvix}/nvixnt/nvixntMeasureUnitGroupAction!saveOrUpdateMeasureUnitGroupDetail.action">
	<input id="parentId" name="parentId" value="${parentId }" type="hidden" /> 
	<input type="hidden" id="id" name="measureUnit.id" value="${measureUnit.id}" /> 
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>编码:</label>
			<div class="col-md-4">
				<input id="code" name="measureUnit.code" value="${measureUnit.code}" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>名称:</label>
			<div class="col-md-4">
				<input id="name" name="measureUnit.name" value="${measureUnit.name}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>英文名:</label>
			<div class="col-md-4">
				<input id="englishCode" name="measureUnit.englishCode" value="${measureUnit.englishCode}" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label">对应条码:</label>
			<div class="col-md-4">
				<input id="barCode" name="measureUnit.barCode" value="${measureUnit.barCode}" class="form-control" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">主计量单位:</label>
			<div class="col-md-4">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="measureUnit.isMeasurementUnit == 1">active</s:if>">
						<input type="radio" value="1" id="isMeasurementUnit" name="measureUnit.isMeasurementUnit" <s:if test="measureUnit.isMeasurementUnit == 1">checked="checked"</s:if>>是
					</label>
					<label class="btn btn-default <s:if test="measureUnit.isMeasurementUnit == 0">active</s:if>">
						<input type="radio" value="0" id="isMeasurementUnit" name="measureUnit.isMeasurementUnit" <s:if test="measureUnit.isMeasurementUnit == 0">checked="checked"</s:if>>否
					</label>
				</div>
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>换算率:</label>
			<div class="col-md-4">
				<input id="rate" name="measureUnit.rate" value="${measureUnit.rate}" class="form-control validate[required]" type="text" />
			</div>
		</div>
	</fieldset>
</form>