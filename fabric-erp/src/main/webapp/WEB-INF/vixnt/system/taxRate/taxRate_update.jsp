<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="taxRateForm" class="form-horizontal" style="padding: 20px 20px;" action="${nvix}/nvixnt/system/nvixntTaxRateAction!saveOrUpdate.action">
	<input type="hidden" id="id" name="taxRate.id" value="${taxRate.id}" /> 
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label">编码:</label>
			<div class="col-md-8">
					<input id="taxRate" name="taxRate.code" value="${taxRate.code}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">名称:</label>
			<div class="col-md-8">
					<input id="name" name="taxRate.name" value="${taxRate.name}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">税率:</label>
			<div class="col-md-8">
				<div class="input-group">
					<input id="taxRate" name="taxRate.taxRate" value="${taxRate.taxRate}" class="form-control validate[required,custom[number],min[1],max[100]]" type="text" />
					<span class="input-group-addon"><i class="fa">%(0-100)</i></span>
				</div>
			</div>
		</div>
	</fieldset>
</form>