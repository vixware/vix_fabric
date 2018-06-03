<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="organizationUnitForm" class="form-horizontal" style="padding: 20px 15px;" action="${nvix}/nvixnt/wxpOrganizationUnitAction!saveOrUpdate.action">
	<input type="hidden" id="id" name="organizationUnit.id" value="${organizationUnit.id}" /> 
	<input type="hidden" id="organizationId" name="organizationUnit.organization.id" value="${organizationUnit.organization.id}" /> 
	<input type="hidden" id="parentOrganizationUnitId" name="organizationUnit.parentOrganizationUnit.id"
		value="${organizationUnit.parentOrganizationUnit.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 编码:</label>
			<div class="col-md-4">
				<input id="orgCode" name="organizationUnit.orgCode" value="${organizationUnit.orgCode}" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 名称:</label>
			<div class="col-md-4">
				<input id="fs" name="organizationUnit.fs" value="${organizationUnit.fs}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 部门类型:</label>
			<div class="col-md-4">
				<select id="organizationUnitTypeId" name="organizationUnit.organizationUnitType.id" class="form-control validate[required]">
					<option value="">------请选择------</option>
					<c:forEach items="${organizationUnitTypeList}" var="entity">
						<option value="${entity.id}" <c:if test="${organizationUnit.organizationUnitType.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
					</c:forEach>
				</select>
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 全称:</label>
			<div class="col-md-4">
				<input id="fullName" name="organizationUnit.fullName" value="${organizationUnit.fullName}" class="form-control validate[required]" type="text" />
			</div>
		</div>
	</fieldset>
</form>