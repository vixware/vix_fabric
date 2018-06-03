<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" media="screen" href="${nvix}/vixntcommon/base/css/personal.css"></link>
<form id="wxpRoleForm" class="form-horizontal" style="padding: 20px 15px;" action="${nvix}/nvixnt/nvixntRoleAction!saveOrUpdate.action">
	<input type="hidden" id="id" name="entityForm.id" value="${entity.id}" /> <input type="hidden" id="addUserAccountRoleIds" name="addUserAccountRoleIds" value="" /> <input type="hidden" id="deleteUserAccountRoleIds" name="deleteUserAccountRoleIds" value="" />
	<input type="hidden" id="roleType" name="entityForm.roleType" value="${entity.roleType}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 角色名:</label>
			<div class="col-md-4">
				<input id="name" name="entityForm.name" value="${entity.name}" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label" style="display: none"><span class="text-danger">*</span> 角色编码:</label>
			<div class="col-md-4">
				<input id="roleCode" name="entityForm.roleCode" value="${entity.roleCode}" style="display: none" <s:if test="%{entity.id!=null && entity.id>0}">readonly="readonly"</s:if> class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 开始时间:</label>
			<div class="col-md-4">
				<input id="startTime" name="entityForm.startTime" value="${entity.startTimeTimeStr}" class="form-control validate[required]" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 截止时间:</label>
			<div class="col-md-4">
				<input id="endTime" name="entityForm.endTime" value="${entity.endTimeTimeStr}" class="form-control validate[required]" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">备注:</label>
			<div class="col-md-10">
				<textarea name="entityForm.memo" class="form-control">${entity.memo } </textarea>
			</div>
		</div>
	</fieldset>
</form>