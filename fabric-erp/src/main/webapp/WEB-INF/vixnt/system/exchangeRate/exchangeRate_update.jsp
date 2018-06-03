<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="exchangeRateForm" class="form-horizontal" style="padding: 20px 20px;" action="${nvix}/nvixnt/nvixntExchangeRateAction!saveOrUpdate.action">
	<input type="hidden" id="id" name="exchangeRate.id" value="${exchangeRate.id}" /> 
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label">本币:</label>
			<div class="col-md-8">
				<select id="currentCT" name="exchangeRate.currentCurrencyType.id" data-prompt-position="topLeft" class="form-control">
					<option value="">------请选择------</option>
					<c:forEach items="${listCurrencyType}" var="entity">
						 <option value="${entity.id}" <c:if test="${exchangeRate.currentCurrencyType.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>汇率:</label>
			<div class="col-md-8">
				<input id="exchangeRate" name="exchangeRate.exchangeRate" value="${exchangeRate.exchangeRate}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">外币:</label>
			<div class="col-md-8">
				<select id="foreignCT" name="exchangeRate.foreignCurrencyType.id" data-prompt-position="topLeft" class="form-control">
					<option value="">------请选择------</option>
					<c:forEach items="${listCurrencyType}" var="entity">
						 <option value="${entity.id}" <c:if test="${exchangeRate.foreignCurrencyType.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
					</c:forEach>
				</select>
			</div>
		</div>
	</fieldset>
</form>