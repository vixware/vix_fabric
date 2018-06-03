<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="mockupUsersForm" class="form-horizontal" style="padding: 20px 15px;" action="${nvix}/nvixnt/nvixntMockupUsersAction!saveOrUpdate.action">
	<input type="hidden" id="id" name="mockupUsers.id" value="${mockupUsers.id}" /> 
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 账号:</label>
			<div class="col-md-8">
				<input id="userName" name="mockupUsers.userName" value="${mockupUsers.userName}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 名称:</label>
			<div class="col-md-8">
				<input id="name" name="mockupUsers.name" value="${mockupUsers.name}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 密码:</label>
			<div class="col-md-8">
				<input id="passWord" name="mockupUsers.passWord" value="${mockupUsers.passWord}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 编码:</label>
			<div class="col-md-8">
				<input id="cmId" name="mockupUsers.cmId" value="${mockupUsers.cmId}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 公司名称:</label>
			<div class="col-md-8">
				<input id="acct" name="mockupUsers.acct" value="${mockupUsers.acct}" class="form-control validate[required]" type="text" />
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	$("#mockupUsersForm").validationEngine();
</script>