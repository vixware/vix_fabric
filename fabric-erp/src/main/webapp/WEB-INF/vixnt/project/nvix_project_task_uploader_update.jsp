<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" media="screen" href="${nvix}/vixntcommon/base/css/personal.css"></link>
<form id="uploaderForm" class="form-horizontal" style="padding: 20px 15px;" action="${nvix}/nvixnt/nvixProjectAction!saveOrUpdateUploader.action">
	<input type="hidden" id="vixTaskId" name="id" value="${vixTask.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"> <span class="text-danger">*</span>附件:
			</label>
			<div class="col-md-6">
				<input type="file" id="docToUpload" name="docToUpload" class="validate[required]">
			</div>
		</div>
	</fieldset>
</form>