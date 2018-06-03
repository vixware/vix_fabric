<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="secretGradeForm" class="form-horizontal" style="padding: 20px 20px;" action="${nvix}/nvixnt/document/nvixSecretGradeAction!saveOrUpdate.action">
	<input type="hidden" id="id" name="secretGrade.id" value="${secretGrade.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>编码:</label>
			<div class="col-md-8">
				<input id="code" name="secretGrade.code" value="${secretGrade.code}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>名称:</label>
			<div class="col-md-8">
				<input id="name" name="secretGrade.name" value="${secretGrade.name}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">备注:</label>
			<div class="col-md-8">
				<input id="memo" name="secretGrade.memo" value="${secretGrade.memo}" class="form-control" type="text" />
			</div>
		</div>
	</fieldset>
</form>