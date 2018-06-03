<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="userFeedbackForm" class="form-horizontal" style="padding: 40px;" action="${nvix}/nvixnt/system/nvixntUserFeedbackAction!saveOrUpdate.action">
	<input type="hidden" id="userFeedbackId" name="userFeedback.id" value="${userFeedback.id}" /> 
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>反馈内容:</label>
			<div class="col-md-10">
				<textarea rows="3" id="center" name="userFeedback.center"  class="form-control validate[required]">${userFeedback.center}</textarea>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"> 联系人:</label>
			<div class="col-md-4">
				<input id="person" name="userFeedback.person" value="${userFeedback.person}" class="form-control" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"> 联系电话:</label>
			<div class="col-md-4">
				<input id="mobilePhone" name="userFeedback.mobilePhone" value="${userFeedback.mobilePhone}" class="form-control" type="text" />
			</div>
			<label class="col-md-2 control-label">邮箱:</label>
			<div class="col-md-4">
				<input id="email" name="userFeedback.email" value="${userFeedback.email}" class="form-control" type="text" />
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
</script>