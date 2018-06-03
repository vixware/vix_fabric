<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="memberLevelForm" class="form-horizontal" style="padding: 35px 15px;" action="${nvix}/nvixnt/vixntMemberLevelManagementAction!saveOrUpdate.action">
	<input type="hidden" id="id" name="memberLevel.id" value="${memberLevel.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 名称:</label>
			<div class="col-md-8">
				<input id="name" name="memberLevel.name" value="${memberLevel.name}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 是否默认:</label>
			<div class="col-md-3">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test='memberLevel.isDefault == "Y"'>active</s:if>"> <input type="radio" value="Y" id="status" name="memberLevel.isDefault" class="validate[required]" <s:if test='memberLevel.isDefault == "Y"'>checked="checked"</s:if>>是
					</label> <label class="btn btn-default <s:if test='memberLevel.isDefault == "N"'>active</s:if>"> <input type="radio" value="N" id="status" name="memberLevel.isDefault" <s:if test='memberLevel.isDefault == "N"'>checked="checked"</s:if>>否
					</label>
				</div>
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 等级折扣:</label>
			<div class="col-md-3">
				<div class="input-group">
					<input id="discount" name="memberLevel.discount" value="${memberLevel.discount}" class="form-control validate[required]" type="text" /> <span class="input-group-addon">%</span>
				</div>
			</div>
		</div>
		<div class="form-group" id="pointdiv">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 积分起:</label>
			<div class="col-md-3">
				<input id="fromPoints" name="memberLevel.fromPoints" value="${memberLevel.fromPoints}" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 积分止:</label>
			<div class="col-md-3">
				<input id="toPoints" name="memberLevel.toPoints" value="${memberLevel.toPoints}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">备注:</label>
			<div class="col-md-8">
				<textarea name="memberLevel.memo" class="form-control">${memberLevel.memo } </textarea>
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	$("#memberLevelForm").validationEngine();
</script>