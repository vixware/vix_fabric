<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="contractTypeCombineForm" class="form-horizontal" style="padding: 35px 15px;" action="${nvix}/nvixnt/contract/vixntContractTypeCombineAction!saveOrUpdate.action">
	<input type="hidden" id="id" name="contractTypeCombine.id" value="${contractTypeCombine.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 名称:</label>
			<div class="col-md-8">
				<input id="name" name="contractTypeCombine.name" value="${contractTypeCombine.name}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 是否启用:</label>
			<div class="col-md-8">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test='contractTypeCombine.disabled == "1"'>active</s:if>"> <input type="radio" value="1" id="disabled" name="contractTypeCombine.disabled" class="validate[required]" <s:if test='contractTypeCombine.disabled == "1"'>checked="checked"</s:if>>启用
					</label> <label class="btn btn-default <s:if test='contractTypeCombine.disabled == "0"'>active</s:if>"> <input type="radio" value="0" id="disabled" name="contractTypeCombine.disabled" <s:if test='contractTypeCombine.disabled == "0"'>checked="checked"</s:if>>禁用
					</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">备注:</label>
			<div class="col-md-8">
				<textarea name="contractTypeCombine.memo" class="form-control">${contractTypeCombine.memo } </textarea>
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	$("#contractTypeCombineForm").validationEngine();
</script>