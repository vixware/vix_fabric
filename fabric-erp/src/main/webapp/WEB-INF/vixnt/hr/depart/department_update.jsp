<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="organizationUnitForm" class="form-horizontal" style="padding: 20px 15px;" action="${nvix}/nvixnt/hr/nvixDepartmentAction!saveOrUpdate.action">
	<input type="hidden" id="id" name="organizationUnit.id" value="${organizationUnit.id}" /> 
	<input type="hidden" id="organizationId" name="organizationUnit.organization.id" value="${organizationUnit.organization.id}" /> 
	<input type="hidden" id="parentOrganizationUnitId" name="organizationUnit.parentOrganizationUnit.id" value="${organizationUnit.parentOrganizationUnit.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 简称:</label>
			<div class="col-md-4">
				<input id="fs" name="organizationUnit.fs" value="${organizationUnit.fs}" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 全称:</label>
			<div class="col-md-4">
				<input id="fullName" name="organizationUnit.fullName" value="${organizationUnit.fullName}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 编码:</label>
			<div class="col-md-4">
				<input id="orgCode" name="organizationUnit.orgCode" value="${organizationUnit.orgCode}" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 业务类型:</label>
			<div class="col-md-4">
				<input id="bizUnitType" name="organizationUnit.bizUnitType" value="${organizationUnit.bizUnitType}" class="form-control" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>组织单元类型:</label>
			<div class="col-md-3">
				<select id="unitType" name="organizationUnit.unitType" class="form-control validate[required]">
				    <option value="">请选择</option>
					<option value="JZBM" <c:if test='${organizationUnit.unitType == "JZBM"}'>selected="selected"</c:if>>基准部门</option>
					<option value="XSBGS" <c:if test='${organizationUnit.unitType == "XSBGS"}'>selected="selected"</c:if>>销售办公室</option>
					<option value="XSZ" <c:if test='${organizationUnit.unitType == "XSZ"}'>selected="selected"</c:if>>销售组</option>
					<option value="XSZZ" <c:if test='${organizationUnit.unitType == "XSZZ"}'>selected="selected"</c:if>>销售组织</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>启用时间:</label>
			<div class="col-md-3">
				<div class="input-group">
					<input id="startUsingDate" name="organizationUnit.startUsingDate" value="${organizationUnit.startUsingDateStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
					<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'startUsingDateStr'});">
					<i class="fa fa-calendar"></i></span>
				</div>
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>停用时间:</label>
			<div class="col-md-3">
				<div class="input-group">
					<input id="stopUsingDate" name="organizationUnit.stopUsingDate" value="${organizationUnit.stopUsingDateStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
					<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'stopUsingDateStr'});">
					<i class="fa fa-calendar"></i></span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">描述:</label>
			<div class="col-md-8">
				<textarea id="description" name="organizationUnit.description" class="form-control" rows="4">${organizationUnit.description}</textarea>
			</div>
		</div>
	</fieldset>
</form>