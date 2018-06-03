<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="receiveTypeForm" class="form-horizontal" style="padding: 20px 20px;" action="${nvix}/nvixnt/document/nvixReceiveTypeAction!saveOrUpdate.action">
	<input type="hidden" id="id" name="receiveType.id" value="${receiveType.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>编码:</label>
			<div class="col-md-8">
				<input id="code" name="receiveType.code" value="${receiveType.code}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>名称:</label>
			<div class="col-md-8">
				<input id="name" name="receiveType.name" value="${receiveType.name}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">备注:</label>
			<div class="col-md-8">
				<input id="memo" name="receiveType.memo" value="${receiveType.memo}" class="form-control" type="text" />
			</div>
		</div>
	</fieldset>
</form>