<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="currencyTypeForm" class="form-horizontal" style="padding: 40px 40px;" action="${nvix}/nvixnt/nvixntCurrencyTypeAction!saveOrUpdate.action">
	<input type="hidden" id="id" name="currencyType.id" value="${currencyType.id}" /> 
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>编码:</label>
			<div class="col-md-4">
				<input id="code" name="currencyType.code" value="${currencyType.code}" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>名称:</label>
			<div class="col-md-4">
				<input id="name" name="currencyType.name" value="${currencyType.name}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">是否本位货币:</label>
			<div class="col-md-4">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="currencyType.isBaseCurrency == 1">active</s:if>">
						<input type="radio" value="1" id="isBaseCurrency" name="currencyType.isBaseCurrency" <s:if test="currencyType.isBaseCurrency == 1">checked="checked"</s:if>>是
					</label>
					<label class="btn btn-default <s:if test="currencyType.isBaseCurrency == 0">active</s:if>">
						<input type="radio" value="0" id="isBaseCurrency" name="currencyType.isBaseCurrency" <s:if test="currencyType.isBaseCurrency == 0">checked="checked"</s:if>>否
					</label>
				</div>
			</div>
		</div>
	</fieldset>
</form>