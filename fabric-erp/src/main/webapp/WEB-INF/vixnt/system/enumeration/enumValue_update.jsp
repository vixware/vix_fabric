<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="enumValueForm" class="form-horizontal" style="padding: 40px 40px;" action="${nvix}/nvixnt/system/nvixEnumerationAction!saveOrUpdateEnumValue.action">
	<input type="hidden" id="id" name="enumValue.id" value="${enumValue.id}" /> 
	<input type="hidden" id="parentId" name="enumValue.enumeration.id" value="${parentId}" /> 
	<input type="hidden" id="enumValueCode" name="enumValue.enumValueCode" value="${enumValue.enumValueCode}" /> 
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>显示名称:</label>
			<div class="col-md-4">
				<input id="displayName" name="enumValue.displayName" value="${enumValue.displayName}" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>值:</label>
			<div class="col-md-4">
				<input id="value" name="enumValue.value" value="${enumValue.value}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">是否缺省:</label>
			<div class="col-md-4">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="enumValue.isDefault == 1">active</s:if>">
						<input type="radio" value="1" id="isDefault" name="enumValue.isDefault" <s:if test="enumValue.isDefault == 1">checked="checked"</s:if>>是
					</label>
					<label class="btn btn-default <s:if test="enumValue.isDefault == 0">active</s:if>">
						<input type="radio" value="0" id="isDefault" name="enumValue.isDefault" <s:if test="enumValue.isDefault == 0">checked="checked"</s:if>>否
					</label>
				</div>
			</div>
			<label class="col-md-2 control-label">是否启用:</label>
			<div class="col-md-4">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="enumValue.enable == 1">active</s:if>">
						<input type="radio" value="1" id="enable" name="enumValue.enable" <s:if test="enumValue.enable == 1">checked="checked"</s:if>>是
					</label>
					<label class="btn btn-default <s:if test="enumValue.enable == 0">active</s:if>">
						<input type="radio" value="0" id="enable" name="enumValue.enable" <s:if test="enumValue.enable == 0">checked="checked"</s:if>>否
					</label>
				</div>
			</div>
		</div>
	</fieldset>
</form>