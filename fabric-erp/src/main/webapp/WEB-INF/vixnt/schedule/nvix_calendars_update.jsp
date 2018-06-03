<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div class="widget-body">
	<form id="calendarsForm" class="form-horizontal" style="padding: 20px 15px;" action="${nvix}/nvixnt/nvixScheduleAction!saveOrUpdate.action">
		<input type="hidden" id="id" name="calendars.id" value="${calendars.id}" /> 
		<input type="hidden" id="allDay" name="calendars.allDay" value="${calendars.allDay}" />
		<fieldset>
			<div class="form-group">
				<label class="col-md-3 control-label"><span class="text-danger">*</span>类型:</label>
				<div class="col-md-9">
					<div data-toggle="buttons" class="btn-group">
						<label class="btn btn-default <c:if test='${calendars.priority == "bg-color-pink txt-color-white"}'>active</c:if>"> <input type="radio" value="bg-color-pink txt-color-white" id="" name="calendars.priority"
							<c:if test='${calendars.priority == "bg-color-pink txt-color-white"}'>checked="checked"</c:if>>任务
						</label> <label class="btn btn-default <c:if test='${calendars.priority == "bg-color-blue txt-color-white"}'>active</c:if>"> <input type="radio" value="bg-color-blue txt-color-white" id="" name="calendars.priority"
							<c:if test='${calendars.priority == "bg-color-blue txt-color-white"}'>checked="checked"</c:if>>待办
						</label> <label class="btn btn-default <c:if test='${calendars.priority == "bg-color-orange txt-color-white"}'>active</c:if>"> <input type="radio" value="bg-color-orange txt-color-white" id="" name="calendars.priority"
							<c:if test='${calendars.priority == "bg-color-orange txt-color-white"}'>checked="checked"</c:if>>消息
						</label> <label class="btn btn-default <c:if test='${calendars.priority == "bg-color-greenLight txt-color-white"}'>active</c:if>"> <input type="radio" value="bg-color-greenLight txt-color-white" id="" name="calendars.priority"
							<c:if test='${calendars.priority == "bg-color-greenLight txt-color-white"}'>checked="checked"</c:if>>提醒
						</label> <label class="btn btn-default <c:if test='${calendars.priority == "bg-color-blueLight txt-color-white"}'>active</c:if>"> <input type="radio" value="bg-color-blueLight txt-color-white" id="" name="calendars.priority"
							<c:if test='${calendars.priority == "bg-color-blueLight txt-color-white"}'>checked="checked"</c:if>>通知
						</label>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-3 control-label"><span class="text-danger">*</span> 主题:</label>
				<div class="col-md-9">
					<input id="scheduleName" name="calendars.scheduleName" value="${calendars.scheduleName}" class="form-control validate[required]" type="text" maxlength="40" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-3 control-label"><span class="text-danger">*</span> 描述:</label>
				<div class="col-md-9">
					<textarea name="calendars.calendarsContent" class="form-control" maxlength="40">${calendars.calendarsContent } </textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-3 control-label"><span class="text-danger">*</span> 开始时间:</label>
				<div class="col-md-9">
					<input id="startDay" name="calendars.startDay" value="<s:date name="%{calendars.startDay}" format="yyyy-MM-dd HH:mm:ss"/>" class="form-control validate[required]" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-md-3 control-label"><span class="text-danger">*</span> 结束时间:</label>
				<div class="col-md-9">
					<input id="endDay" name="calendars.endDay" value="<s:date name="%{calendars.endDay}" format="yyyy-MM-dd HH:mm:ss"/>" class="form-control validate[required]" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
				</div>
			</div>
		</fieldset>
	</form>
</div>