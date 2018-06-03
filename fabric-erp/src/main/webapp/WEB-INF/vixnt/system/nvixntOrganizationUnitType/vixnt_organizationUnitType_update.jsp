<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="organizationUnitTypeForm" class="form-horizontal" style="padding: 20px 15px;" action="${nvix}/nvixnt/system/nvixntOrganizationUnitTypeAction!saveOrUpdate.action">
	<input type="hidden" id="id" name="organizationUnitType.id" value="${organizationUnitType.id}" /> 
	<input type="hidden" id="code" name="organizationUnitType.code" value="${organizationUnitType.code}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 名称:</label>
			<div class="col-md-10">
				<input id="name" name="organizationUnitType.name" value="${organizationUnitType.name}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">备注:</label>
			<div class="col-md-10">
				<textarea name="organizationUnitType.memo" class="form-control">${organizationUnitType.memo } </textarea>
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	$("#organizationUnitTypeForm").validationEngine();
</script>