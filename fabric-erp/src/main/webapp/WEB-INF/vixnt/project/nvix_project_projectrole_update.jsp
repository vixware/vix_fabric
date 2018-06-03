<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" media="screen" href="${nvix}/vixntcommon/base/css/personal.css"></link>
<form id="projectRoleForm" class="form-horizontal" style="padding: 20px 15px;" action="${nvix}/nvixnt/nvixProjectAction!saveOrUpdateProjectRole.action">
	<input type="hidden" id="projectRoleId" name="projectRole.id" value="${projectRole.id}" /> <input type="hidden" id="projectId" name="projectRole.project.id" value="${projectRole.project.id}" /> <input type="hidden" id="parentProjectRoleId" name="projectRole.parentProjectRole.id" value="${projectRole.parentProjectRole.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 编码:</label>
			<div class="col-md-4">
				<input id="projectRoleCode" name="projectRole.code" value="${projectRole.code}" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 名称:</label>
			<div class="col-md-4">
				<input id="projectRoleName" name="projectRole.name" value="${projectRole.name}" class="form-control validate[required]" type="text" />
			</div>
		</div>
	</fieldset>
</form>