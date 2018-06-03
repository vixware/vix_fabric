<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="warningTypeForm" class="form-horizontal" style="padding: 40px;" action="${nvix}/nvixnt/system/nvixntWarningSourceAction!saveOrUpdateWarningType.action">
	<input type="hidden" id="warningTypeId" name="warningType.id" value="${warningType.id}" /> 
	<input type="hidden" id="warningTypeModuleCategoryId" name="warningType.moduleCategory.id" value="${warningType.moduleCategory.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 名称:</label>
			<div class="col-md-4">
				<input id="typeName" name="warningType.typeName" value="${warningType.typeName}" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 执行频率:</label>
			<div class="col-md-4">
				<input id="executionFrequency" name="warningType.executionFrequency" value="${warningType.executionFrequency}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"> 单位:</label>
			<div class="col-md-4">
				<select id="executionFrequencyUtil" name="warningType.executionFrequencyUtil" data-prompt-position="topLeft" class="form-control">
					<option value="">------请选择------</option>
					<option value="H" <c:if test="${warningType.executionFrequencyUtil == 'H'}">selected="selected"</c:if>>时</option>
					<option value="M" <c:if test="${warningType.executionFrequencyUtil == 'M'}">selected="selected"</c:if>>分</option>
					<option value="S" <c:if test="${warningType.executionFrequencyUtil == 'S'}">selected="selected"</c:if>>秒</option>
				</select>
			</div>
			<label class="col-md-2 control-label">执行日期:</label>
				<div class="col-md-4">
					<div class="input-group">
						<input id="executeTime" name="warningType.executeTime" value="${warningType.executeTimeStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" />
						 <span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'executeTime'});">
						 <i class="fa fa-calendar"></i></span>
					</div>
				</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"> 预警类型:</label>
			<div class="col-md-4">
				<select id="classCode" name="warningType.classCode" data-prompt-position="topLeft" class="form-control">
					<option value="">------请选择------</option>
					<option value="inventory" <c:if test="${warningType.classCode eq 'inventory'}">selected="selected"</c:if>>库存预警</option>
					<option value="qualityGuaranteePeriodTask" <c:if test="${warningType.classCode eq 'qualityGuaranteePeriodTask'}">selected="selected"</c:if>>保质期预警</option>
					<option value="crm" <c:if test="${warningType.classCode eq 'crm'}">selected="selected"</c:if>>客户生日提醒</option>
				</select>
			</div>
			<label class="col-md-2 control-label">描述:</label>
			<div class="col-md-4">
				<input id="typeDescription" name="warningType.typeDescription" value="${warningType.typeDescription}" class="form-control" type="text" />
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	$(function(){
		$("#warningTypeModuleCategoryId").val($("#moduleCategoryId").val());
	})
</script>