<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="systemVarForm" class="form-horizontal" style="padding: 40px 40px;" action="${nvix}/nvixnt/nvixntSystemVarAction!saveOrUpdate.action">
	<input type="hidden" id="id" name="systemVar.id" value="${systemVar.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>变量:</label>
			<div class="col-md-4">
				<input id="varCode" name="systemVar.varCode" value="${systemVar.varCode}" <s:if test="%{systemVar.id != null}">readonly="readonly"</s:if> class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>值:</label>
			<div class="col-md-4">
				<input id="defaultValue" name="systemVar.defaultValue" value="${systemVar.defaultValue}" <s:if test="%{systemVar.id != null}">readonly="readonly"</s:if> class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">描述:</label>
			<div class="col-md-10">
				<textarea id="description" name="systemVar.description" class="form-control">${systemVar.description } </textarea>
			</div>
		</div>
	</fieldset>
</form>