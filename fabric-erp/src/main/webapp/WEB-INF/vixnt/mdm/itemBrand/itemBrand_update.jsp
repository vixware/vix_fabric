<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="itemBrandForm" class="form-horizontal" style="padding: 40px 40px;" action="${nvix}/nvixnt/mdm/nvixntItemBrandAction!saveOrUpdate.action">
	<input type="hidden" id="id" name="itemBrand.id" value="${itemBrand.id}" /> 
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>编码:</label>
			<div class="col-md-4">
				<input id="code" name="itemBrand.code" value="${itemBrand.code}" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>名称:</label>
			<div class="col-md-4">
				<input id="name" name="itemBrand.name" value="${itemBrand.name}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">公司名称:</label>
			<div class="col-md-4">
				<input id="brandCompany" name="itemBrand.brandCompany" value="${itemBrand.brandCompany}" class="form-control" type="text" />
			</div>
			<label class="col-md-2 control-label">公司地址:</label>
			<div class="col-md-4">
				<input id="companyAddress" name="itemBrand.companyAddress" value="${itemBrand.companyAddress}" class="form-control" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">品牌排序:</label>
			<div class="col-md-4">
				<input id="orderBy" name="itemBrand.orderBy" value="${itemBrand.orderBy}" class="form-control validate[custom[number]]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">品牌描述:</label>
			<div class="col-md-10">
				<textarea rows="3" id="memo" name="itemBrand.memo" value="${itemBrand.memo}" class="form-control">${itemBrand.memo}</textarea>
			</div>
		</div>
	</fieldset>
</form>