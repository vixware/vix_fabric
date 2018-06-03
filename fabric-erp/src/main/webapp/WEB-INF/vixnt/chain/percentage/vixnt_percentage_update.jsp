<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="employeeTypeForm" class="form-horizontal" style="padding: 35px 15px;" action="${nvix}/nvixnt/vixntPercentageAction!saveOrUpdate.action">
	<input type="hidden" id="id" name="employeeType.id" value="${employeeType.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 名称:</label>
			<div class="col-md-3">
				<input id="name" name="employeeType.name" value="${employeeType.name}" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label"> <span class="text-danger">*</span>是否启用:
			</label>
			<div class="col-md-3">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="employeeType.enabled == 0">active</s:if>"> <input type="radio" value="0" id="enabled" name="employeeType.enabled" <s:if test="employeeType.enabled == 0">checked="checked"</s:if> class="validate[required]">是
					</label> <label class="btn btn-default <s:if test="employeeType.enabled == 1">active</s:if>"> <input type="radio" value="1" id="enabled" name="employeeType.enabled" <s:if test="employeeType.enabled == 1">checked="checked"</s:if>>否
					</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 提成类型:</label>
			<div class="col-md-3">
				<select id="percentageType" name="employeeType.percentageType" class="form-control">
					<option value="1" <c:if test='${employeeType.percentageType eq "1"}'>selected="selected"</c:if>>固定金额</option>
					<option value="2" <c:if test='${employeeType.percentageType eq "2"}'>selected="selected"</c:if>>存值比例</option>
				</select>
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 结算类型:</label>
			<div class="col-md-3">
				<select id="settlementsType" name="employeeType.settlementsType" class="form-control">
					<option value="1" <c:if test='${employeeType.settlementsType eq "1"}'>selected="selected"</c:if>>按订单结算</option>
					<option value="2" <c:if test='${employeeType.settlementsType eq "2"}'>selected="selected"</c:if>>自然月结算</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"> 提成金额:</label>
			<div class="col-md-3">
				<input id="amount" name="employeeType.amount" value="${employeeType.amount}" class="form-control" type="text" />
			</div>
			<label class="col-md-2 control-label"> 提成比例:</label>
			<div class="col-md-3">
				<input id="proportion" name="employeeType.proportion" value="${employeeType.proportion}" class="form-control" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>开始时间:</label>
			<div class="col-md-3">
				<div class="input-group">
					<input id="startTime" name="employeeType.startTime" value="${employeeType.startTimeStr}" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" /> <span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',el:'startTime'});"><i
						class="fa fa-calendar"></i></span>
				</div>
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>结束时间:</label>
			<div class="col-md-3">
				<div class="input-group">
					<input id="endTime" name="employeeType.endTime" value="${employeeType.endTimeStr}" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" /> <span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',el:'endTime'});"><i
						class="fa fa-calendar"></i></span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">备注:</label>
			<div class="col-md-8">
				<textarea name="employeeType.memo" class="form-control">${employeeType.memo } </textarea>
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	$("#employeeTypeForm").validationEngine();
</script>