<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="supplierLevelForm" class="form-horizontal" style="padding: 35px 15px;" action="${nvix}/nvixnt/vixntSupplierLevelAction!saveOrUpdate.action">
	<input type="hidden" id="id" name="supplierLevel.id" value="${supplierLevel.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 名称:</label>
			<div class="col-md-3">
				<input id="name" name="supplierLevel.name" value="${supplierLevel.name}" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 是否默认:</label>
			<div class="col-md-3">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test='supplierLevel.isDefault == "0"'>active</s:if>"> <input type="radio" value="0" id="status" name="supplierLevel.isDefault" class="validate[required]" <s:if test='supplierLevel.isDefault == "0"'>checked="checked"</s:if>>是
					</label> <label class="btn btn-default <s:if test='supplierLevel.isDefault == "1"'>active</s:if>"> <input type="radio" value="1" id="status" name="supplierLevel.isDefault" <s:if test='supplierLevel.isDefault == "1"'>checked="checked"</s:if>>否
					</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 是否启用:</label>
			<div class="col-md-3">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test='supplierLevel.isUse == "0"'>active</s:if>"> <input type="radio" value="0" id="status" name="supplierLevel.isUse" class="validate[required]" <s:if test='supplierLevel.isUse == "0"'>checked="checked"</s:if>>是
					</label> <label class="btn btn-default <s:if test='supplierLevel.isUse == "1"'>active</s:if>"> <input type="radio" value="1" id="status" name="supplierLevel.isUse" <s:if test='supplierLevel.isUse == "1"'>checked="checked"</s:if>>否
					</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">备注:</label>
			<div class="col-md-8">
				<textarea name="supplierLevel.memo" class="form-control">${supplierLevel.memo} </textarea>
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	$("#supplierLevelForm").validationEngine();
</script>