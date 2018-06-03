<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="holidayRuleForm" class="form-horizontal" style="padding: 40px 40px;" action="${nvix}/nvixnt/attendance/holiDayRuleAction!saveOrUpdate.action" method="post">
	<input type="hidden" id="id" name="holidayRule.id" value="${holidayRule.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>假日名称:</label>
			<div class="col-md-4">
				<input id="name" name="holidayRule.name" value="${holidayRule.name}" type="text" data-prompt-position="topLeft" class="form-control validate[required]" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">假日开始时间:</label>
			<div class="col-md-4">
				<div class="input-group">
					<input id="holidayStartTime" name="holidayRule.holidayStartTime" value="${holidayRule.holidayStartTimeStr}" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" /> <span class="input-group-addon"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',el:'holidayStartTime'});"><i class="fa fa-calendar"></i></span>
				</div>
			</div>
			<label class="col-md-2 control-label">假日结束时间:</label>
			<div class="col-md-4">
				<div class="input-group">
					<input id="holidayEndTime" name="holidayRule.holidayEndTime" value="${holidayRule.holidayEndTimeStr}" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" /> <span class="input-group-addon"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',el:'holidayEndTime'});"><i class="fa fa-calendar"></i></span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">备注:</label>
			<div class="col-md-10">
				<textarea rows="4" id="memo" name="holidayRule.memo" value="${holidayRule.memo}" class="form-control">${holidayRule.memo}</textarea>
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	$("#holidayRuleForm").validationEngine();
</script>