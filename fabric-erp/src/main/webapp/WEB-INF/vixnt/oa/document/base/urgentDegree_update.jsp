<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="urgentDegreeForm" class="form-horizontal" style="padding: 20px 20px;" action="${nvix}/nvixnt/document/nvixUrgentDegreeAction!saveOrUpdate.action">
	<input type="hidden" id="id" name="urgentDegree.id" value="${urgentDegree.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>编码:</label>
			<div class="col-md-8">
				<input id="code" name="urgentDegree.code" value="${urgentDegree.code}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>名称:</label>
			<div class="col-md-8">
				<input id="name" name="urgentDegree.name" value="${urgentDegree.name}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">备注:</label>
			<div class="col-md-8">
				<input id="memo" name="urgentDegree.memo" value="${urgentDegree.memo}" class="form-control" type="text" />
			</div>
		</div>
	</fieldset>
</form>