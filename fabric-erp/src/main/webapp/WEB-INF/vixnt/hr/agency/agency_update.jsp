<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="organizationUnitForm" class="form-horizontal" style="padding: 20px 15px;" action="${nvix}/nvixnt/hr/nvixAgencyAction!saveOrUpdate.action">
	<input type="hidden" id="id" name="organization.id" value="${organization.id}" /> 
	<input type="hidden" id="parentOrganizationId" name="organization.parentOrganization.id" value="${organization.parentOrganization.id}" /> 
	
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 名称:</label>
			<div class="col-md-4">
				<input id="orgName" name="organization.orgName" value="${organization.orgName}" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 简称:</label>
			<div class="col-md-4">
				<input id="briefName" name="organization.briefName" value="${organization.briefName}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"> 拼音缩写:</label>
			<div class="col-md-4">
				<input id="shortName" name="organization.shortName" value="${organization.shortName}" class="form-control" type="text" />
			</div>
			<label class="col-md-2 control-label"> 英文名称:</label>
			<div class="col-md-4">
				<input id="enName" name="organization.enName" value="${organization.enName}" class="form-control" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"> 地址:</label>
			<div class="col-md-4">
				<input id="address" name="organization.address" value="${organization.address}" class="form-control" type="text" />
			</div>
			<label class="col-md-2 control-label"> 所属国家:</label>
			<div class="col-md-4">
				<input id="country" name="organization.country" value="${organization.country}" class="form-control" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"> 地区:</label>
			<div class="col-md-4">
				<input id="area" name="organization.area" value="${organization.area}" class="form-control" type="text" />
			</div>
			<label class="col-md-2 control-label"> 使用语言:</label>
			<div class="col-md-4">
				<input id="countryLanguage" name="organization.countryLanguage" value="${organization.countryLanguage}" class="form-control" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"> 编码:</label>
			<div class="col-md-4">
				<input id="code" name="organization.code" value="${organization.code}" class="form-control" type="text" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>成立时间:</label>
			<div class="col-md-3">
				<div class="input-group">
					<input id="companyCreateDate" name="organization.companyCreateDate" value="${organization.companyCreateDateStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
					<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'companyCreateDateStr'});">
					<i class="fa fa-calendar"></i></span>
				</div>
			</div>
		</div>
	    <div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 公司管理账号:</label>
			<div class="col-md-4">
				<input id="account" name="organization.account" value="${organization.compSuperAdmin.account}" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 机构类型:</label>
			<div class="col-md-3">
				<select id="orgType" name="organization.orgType" class="form-control validate[required]">
				    <option value="">请选择</option>
					<option value="jtgs" <c:if test='${organization.orgType == "jtgs"}'>selected="selected"</c:if>>集团公司</option>
					<option value="gs" <c:if test='${organization.orgType == "gs"}'>selected="selected"</c:if>>公司</option>
				</select>
			</div>
		</div>
	    <div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 密码:</label>
			<div class="col-md-4">
				<input id="password" name="organization.password" value="${organization.compSuperAdmin.password}" class="form-control validate[required]" type="password" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 确认密码:</label>
			<div class="col-md-4">
				<input id="passwordConfirm" name="organization.passwordConfirm" value="${organization.compSuperAdmin.passwordConfirm}" class="form-control" type="password" />
			</div>
		</div>
		<div class="form-group">
		    <label class="col-md-2 control-label"> 上级公司:</label>
		    <s:if test="organization.parentOrganization.id != null">
				<div class="col-md-4">
					<input id="name" value="${organization.parentOrganization.orgName}"  class="form-control" type="text" readonly="readonly"/>
				</div>
			</s:if>
			<s:else>
			    <div class="col-md-4">
					<input id="name" value=""  class="form-control" type="text" readonly="readonly"/>
				</div>
			</s:else>
			<label class="col-md-2 control-label"> 数据查看:</label>
			<div class="col-md-3">
				<select id="orgDataFilterType" name="organization.orgDataFilterType" class="form-control ">
				    <option value="">请选择</option>
					<option value="P" <c:if test='${organization.orgDataFilterType == "P"}'>selected="selected"</c:if>>本公司数据</option>
					<option value="N" <c:if test='${organization.orgDataFilterType == "N"}'>selected="selected"</c:if>>不使用</option>
				</select>
			</div>
	</fieldset>
</form>