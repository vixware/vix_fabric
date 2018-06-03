<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="credentialTypeForm" class="form-horizontal" style="padding: 20px 20px;" action="${nvix}/nvixnt/nvixCredentialTypeAction!saveOrUpdate.action">
	<input type="hidden" id="id" name="credentialType.id" value="${credentialType.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>名称:</label>
			<div class="col-md-4">
				<input id="name" name="credentialType.name" value="${credentialType.name}" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>是否默认:</label>
			<div class="col-md-4">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <c:if test="${credentialType.isDefault == 1}">active</c:if>"> 
						<input type="radio" value="1" id="isDefault" name="credentialType.isDefault" class="validate[required]" <c:if test="${credentialType.isDefault == 1}">checked="checked"</c:if>>是
					</label> 
					<label class="btn btn-default <c:if test="${credentialType.isDefault == 0}">active</c:if>"> 
						<input type="radio" value="0" id="isDefault" name="credentialType.isDefault" class="validate[required]" <c:if test="${credentialType.isDefault == 0}">checked="checked"</c:if>>否
					</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>是否启用:</label>
			<div class="col-md-4">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <c:if test="${credentialType.enable == 1}">active</c:if>"> 
						<input type="radio" value="1" id="enable" name="credentialType.enable" class="validate[required]" <c:if test="${credentialType.enable == 1}">checked="checked"</c:if>>是
					</label> 
					<label class="btn btn-default <c:if test="${credentialType.enable == 0}">active</c:if>"> 
						<input type="radio" value="0" id="enable" name="credentialType.enable" class="validate[required]" <c:if test="${credentialType.enable == 0}">checked="checked"</c:if>>否
					</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">备注:</label>
			<div class="col-md-10">
				<input id="memo" name="credentialType.memo" value="${credentialType.memo}" class="form-control" type="text" />
			</div>
		</div>
	</fieldset>
</form>