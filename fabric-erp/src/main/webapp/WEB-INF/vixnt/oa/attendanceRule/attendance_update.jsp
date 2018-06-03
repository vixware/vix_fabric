<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="attSettingForm" class="form-horizontal" style="padding: 40px 40px;" action="${nvix}/nvixnt/attendanceSettingAction!saveOrUpdate.action" method="post" enctype="multipart/form-data">
	<input type="hidden" id="id" name="attendanceRule.id" value="${attendanceRule.id}" /> <input type="hidden" id="createTime" name="attendanceRule.createTime" value="${attendanceRule.createTime}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>编码:</label>
			<div class="col-md-4">
				<input id="code" name="attendanceRule.code" value="${attendanceRule.code}" type="text" data-prompt-position="topLeft" class="form-control validate[required]" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>类型名称:</label>
			<div class="col-md-4">
				<input id="name" name="attendanceRule.ruleType" value="${attendanceRule.ruleType}" type="text" data-prompt-position="topLeft" class="form-control validate[required]" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>状态:</label>
			<div class="col-md-4">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="attendanceRule.isEnable == 1">active</s:if>"> <input type="radio" value="1" id="isEnable" name="attendanceRule.isEnable" <s:if test="attendanceRule.isEnable == 1">checked="checked"</s:if> class="validate[required]">启用
					</label> <label class="btn btn-default <s:if test="attendanceRule.isEnable == 0">active</s:if>"> <input type="radio" value="0" id="isEnable" name="attendanceRule.isEnable" <s:if test="attendanceRule.isEnable == 0">checked="checked"</s:if>>禁用
					</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">备注:</label>
			<div class="col-md-10">
				<textarea rows="4" id="memo" name="attendanceRule.memo" value="${attendanceRule.memo}" class="form-control">${attendanceRule.memo}</textarea>
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	$("#attSettingForm").validationEngine();
</script>