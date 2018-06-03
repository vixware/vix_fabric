<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="saleActivityTypeForm" class="form-horizontal" style="padding: 20px 20px;" action="${nvix}/nvixnt/nvixSaleActivityTypeAction!saveOrUpdate.action">
	<input type="hidden" id="id" name="saleActivity.id" value="${saleActivity.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>名称:</label>
			<div class="col-md-4">
				<input id="name" name="saleActivity.name" value="${saleActivity.name}" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>是否默认:</label>
			<div class="col-md-4">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <c:if test="${saleActivity.isDefault == 1}">active</c:if>"> 
						<input type="radio" value="1" id="isDefault" name="saleActivity.isDefault" class="validate[required]" <c:if test="${saleActivity.isDefault == 1}">checked="checked"</c:if>>是
					</label> 
					<label class="btn btn-default <c:if test="${saleActivity.isDefault == 0}">active</c:if>"> 
						<input type="radio" value="0" id="isDefault" name="saleActivity.isDefault" class="validate[required]" <c:if test="${saleActivity.isDefault == 0}">checked="checked"</c:if>>否
					</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>是否启用:</label>
			<div class="col-md-4">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <c:if test="${saleActivity.enable == 1}">active</c:if>"> 
						<input type="radio" value="1" id="enable" name="saleActivity.enable" class="validate[required]" <c:if test="${saleActivity.enable == 1}">checked="checked"</c:if>>是
					</label> 
					<label class="btn btn-default <c:if test="${saleActivity.enable == 0}">active</c:if>"> 
						<input type="radio" value="0" id="enable" name="saleActivity.enable" class="validate[required]" <c:if test="${saleActivity.enable == 0}">checked="checked"</c:if>>否
					</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">备注:</label>
			<div class="col-md-10">
				<input id="memo" name="saleActivity.memo" value="${saleActivity.memo}" class="form-control" type="text" />
			</div>
		</div>
	</fieldset>
</form>