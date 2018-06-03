<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" media="screen" href="${nvix}/vixntcommon/base/css/personal.css"></link>
<form id="uploaderTypeKeyWordForm" class="form-horizontal" style="padding: 20px 15px;" action="${nvix}/nvixnt/nvixFileAction!saveOrUpdateUploaderTypeKeyWord.action">
	<input type="hidden" id="uploaderTypeKeyWordId" name="uploaderTypeKeyWord.id" value="${uploaderTypeKeyWord.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 编码:</label>
			<div class="col-md-4">
				<input id="uploaderTypeKeyWordCode" name="uploaderTypeKeyWord.code" value="${uploaderTypeKeyWord.code}" class="form-control required" type="text" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 关键字:</label>
			<div class="col-md-4">
				<input id="uploaderTypeKeyWordName" name="uploaderTypeKeyWord.name" value="${uploaderTypeKeyWord.name}" class="form-control required" type="text" />
			</div>
		</div>
	</fieldset>
</form>