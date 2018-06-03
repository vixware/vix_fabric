<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="regionalForm" class="form-horizontal" style="padding: 40px 40px;" action="${nvix}/nvixnt/nvixntRegionalAction!saveOrUpdate.action">
	<input type="hidden" id="id" name="regional.id" value="${regional.id}" /> 
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>编码:</label>
			<div class="col-md-4">
				<input id="code" name="regional.code" value="${regional.code}" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>名称:</label>
			<div class="col-md-4">
				<input id="name" name="regional.name" value="${regional.name}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">是否启用:</label>
			<div class="col-md-4">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="regional.enable == 1">active</s:if>">
						<input type="radio" value="1" id="enable" name="regional.enable" <s:if test="regional.enable == 1">checked="checked"</s:if>>启用
					</label>
					<label class="btn btn-default <s:if test="regional.enable == 0">active</s:if>">
						<input type="radio" value="0" id="enable" name="regional.enable" <s:if test="regional.enable == 0">checked="checked"</s:if>>禁用
					</label>
				</div>
			</div>
			<label class="col-md-2 control-label">备注:</label>
			<div class="col-md-4">
				<textarea rows="3" id="memo" name="regional.memo" value="${regional.memo}" class="form-control">${regional.memo}</textarea>
			</div>
		</div>
	</fieldset>
</form>