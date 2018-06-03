<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="projectStatusForm" class="form-horizontal" style="padding: 20px 20px;" action="${nvix}/nvixnt/nvixProjectStatusAction!saveOrUpdate.action">
	<input type="hidden" id="id" name="projectStatus.id" value="${projectStatus.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>名称:</label>
			<div class="col-md-4">
				<input id="name" name="projectStatus.name" value="${projectStatus.name}" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>是否默认:</label>
			<div class="col-md-4">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <c:if test="${projectStatus.isDefault == 1}">active</c:if>"> 
						<input type="radio" value="1" id="isDefault" name="projectStatus.isDefault" class="validate[required]" <c:if test="${projectStatus.isDefault == 1}">checked="checked"</c:if>>是
					</label> 
					<label class="btn btn-default <c:if test="${projectStatus.isDefault == 0}">active</c:if>"> 
						<input type="radio" value="0" id="isDefault" name="projectStatus.isDefault" class="validate[required]" <c:if test="${projectStatus.isDefault == 0}">checked="checked"</c:if>>否
					</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>是否启用:</label>
			<div class="col-md-4">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <c:if test="${projectStatus.enable == 1}">active</c:if>"> 
						<input type="radio" value="1" id="enable" name="projectStatus.enable" class="validate[required]" <c:if test="${projectStatus.enable == 1}">checked="checked"</c:if>>是
					</label> 
					<label class="btn btn-default <c:if test="${projectStatus.enable == 0}">active</c:if>"> 
						<input type="radio" value="0" id="enable" name="projectStatus.enable" class="validate[required]" <c:if test="${projectStatus.enable == 0}">checked="checked"</c:if>>否
					</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">备注:</label>
			<div class="col-md-10">
				<input id="memo" name="projectStatus.memo" value="${projectStatus.memo}" class="form-control" type="text" />
			</div>
		</div>
	</fieldset>
</form>