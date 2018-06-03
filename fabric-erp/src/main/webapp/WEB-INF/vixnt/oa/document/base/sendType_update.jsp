<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="sendTypeForm" class="form-horizontal" style="padding: 20px 20px;" action="${nvix}/nvixnt/document/nvixSendTypeAction!saveOrUpdate.action">
	<input type="hidden" id="id" name="sendType.id" value="${sendType.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>编码:</label>
			<div class="col-md-8">
				<input id="code" name="sendType.code" value="${sendType.code}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>名称:</label>
			<div class="col-md-8">
				<input id="name" name="sendType.name" value="${sendType.name}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">备注:</label>
			<div class="col-md-8">
				<input id="memo" name="sendType.memo" value="${sendType.memo}" class="form-control" type="text" />
			</div>
		</div>
	</fieldset>
</form>