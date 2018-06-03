<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="periodRuleForm" class="form-horizontal" style="padding: 40px 40px;" action="${nvix}/nvixnt/periodAction!saveOrUpdate.action" method="post">
	<input id="id" name="periodRule.id" value="${periodRule.id }" type="hidden" /> <input id="creator" name="periodRule.creator" value="${periodRule.creator }" type="hidden" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>时段编码:</label>
			<div class="col-md-4">
				<input id="code" name="periodRule.code" value="${periodRule.code}" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>时段名称:</label>
			<div class="col-md-4">
				<input id="name" name="periodRule.name" value="${periodRule.name}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">折合天数:</label>
			<div class="col-md-4">
				<input id="discountDay" name="periodRule.discountDay" value="${periodRule.discountDay}" class="form-control" type="text" />
			</div>
			<label class="col-md-2 control-label">工时:</label>
			<div class="col-md-4">
				<div class="input-group">
					<input id="manhour" name="periodRule.manhour" value="${periodRule.manhour}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" /> <span class="input-group-addon"><i class="fa">分钟</i></span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">是否跨天:</label>
			<div class="col-md-4">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="periodRule.isNextDay == 1">active</s:if>"> <input type="radio" value="1" id="isNextDay" name="periodRule.isNextDay" <s:if test="periodRule.isNextDay == 1">checked="checked"</s:if>>是
					</label> <label class="btn btn-default <s:if test="periodRule.isNextDay == 0">active</s:if>"> <input type="radio" value="0" id="isNextDay" name="periodRule.isNextDay" <s:if test="periodRule.isNextDay == 0">checked="checked"</s:if>>否
					</label>
				</div>
			</div>
			<label class="col-md-2 control-label">上下班一次未刷算旷工:</label>
			<div class="col-md-4">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="periodRule.isAbsenteeism == 1">active</s:if>"> <input type="radio" value="1" id="isAbsenteeism" name="periodRule.isAbsenteeism" <s:if test="periodRule.isAbsenteeism == 1">checked="checked"</s:if>>是
					</label> <label class="btn btn-default <s:if test="periodRule.isAbsenteeism == 0">active</s:if>"> <input type="radio" value="0" id="isAbsenteeism" name="periodRule.isAbsenteeism" <s:if test="periodRule.isAbsenteeism == 0">checked="checked"</s:if>>否
					</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">上班是否刷卡:</label>
			<div class="col-md-4">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="periodRule.workOnSwipingCard == 1">active</s:if>"> <input type="radio" value="1" id="workOnSwipingCard" name="periodRule.workOnSwipingCard" <s:if test="periodRule.workOnSwipingCard == 1">checked="checked"</s:if>>是
					</label> <label class="btn btn-default <s:if test="periodRule.workOnSwipingCard == 0">active</s:if>"> <input type="radio" value="0" id="workOnSwipingCard" name="periodRule.workOnSwipingCard" <s:if test="periodRule.workOnSwipingCard == 0">checked="checked"</s:if>>否
					</label>
				</div>
			</div>
			<label class="col-md-2 control-label">下班是否刷卡:</label>
			<div class="col-md-4">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="periodRule.workOffSwipingCard == 1">active</s:if>"> <input type="radio" value="1" id="workOffSwipingCard" name="periodRule.workOffSwipingCard" <s:if test="periodRule.workOffSwipingCard == 1">checked="checked"</s:if>>是
					</label> <label class="btn btn-default <s:if test="periodRule.workOffSwipingCard == 0">active</s:if>"> <input type="radio" value="0" id="workOffSwipingCard" name="periodRule.workOffSwipingCard" <s:if test="periodRule.workOffSwipingCard == 0">checked="checked"</s:if>>否
					</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">最早上班考勤时间:</label>
			<div class="col-md-4">
				<div class="input-group">
					<input id="earliestTime" name="periodRule.earliestTime" value="${periodRule.earliestTime}" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'HH:mm:ss'})" type="text" /> <span class="input-group-addon" onclick="WdatePicker({dateFmt:'HH:mm:ss',el:'earliestTime'});"><i
						class="fa fa-calendar"></i></span>
				</div>
			</div>
			<label class="col-md-2 control-label">早退记缺勤时间:</label>
			<div class="col-md-4">
				<div class="input-group">
					<input id="earlyAbsenceTime" name="periodRule.earlyAbsenceTime" value="${periodRule.earlyAbsenceTime}" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'HH:mm:ss'})" type="text" /> <span class="input-group-addon" onclick="WdatePicker({dateFmt:'HH:mm:ss',el:'earlyAbsenceTime'});"><i
						class="fa fa-calendar"></i></span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">上班时间:</label>
			<div class="col-md-4">
				<div class="input-group">
					<input id="workOnTime" name="periodRule.workOnTime" value="${periodRule.workOnTime}" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'HH:mm:ss'})" type="text" /> <span class="input-group-addon" onclick="WdatePicker({dateFmt:'HH:mm:ss',el:'workOnTime'});"><i class="fa fa-calendar"></i></span>
				</div>
			</div>
			<label class="col-md-2 control-label">下班时间:</label>
			<div class="col-md-4">
				<div class="input-group">
					<input id="workOffTime" name="periodRule.workOffTime" value="${periodRule.workOffTime}" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'HH:mm:ss'})" type="text" /> <span class="input-group-addon" onclick="WdatePicker({dateFmt:'HH:mm:ss',el:'workOffTime'});"><i class="fa fa-calendar"></i></span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">迟到记缺勤时间:</label>
			<div class="col-md-4">
				<div class="input-group">
					<input id="lateAbsencesTime" name="periodRule.lateAbsencesTime" value="${periodRule.lateAbsencesTime}" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'HH:mm:ss'})" type="text" /> <span class="input-group-addon" onclick="WdatePicker({dateFmt:'HH:mm:ss',el:'lateAbsencesTime'});"><i
						class="fa fa-calendar"></i></span>
				</div>
			</div>
			<label class="col-md-2 control-label">最晚下班考勤时间:</label>
			<div class="col-md-4">
				<div class="input-group">
					<input id="lastWorkOffTime" name="periodRule.lastWorkOffTime" value="${periodRule.lastWorkOffTime}" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'HH:mm:ss'})" type="text" /> <span class="input-group-addon" onclick="WdatePicker({dateFmt:'HH:mm:ss',el:'lastWorkOffTime'});"><i
						class="fa fa-calendar"></i></span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">允许迟到时间:</label>
			<div class="col-md-4">
				<div class="input-group">
					<input id="allowTheLateTime" name="periodRule.allowTheLateTime" value="${periodRule.allowTheLateTime}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" /> <span class="input-group-addon"><i class="fa">分钟</i></span>
				</div>
			</div>
			<label class="col-md-2 control-label">允许早退时间:</label>
			<div class="col-md-4">
				<div class="input-group">
					<input id="allowedEarlyTime" name="periodRule.allowedEarlyTime" value="${periodRule.allowedEarlyTime}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" /> <span class="input-group-addon"><i class="fa">分钟</i></span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">上班启用自动算加班:</label>
			<div class="col-md-4">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="periodRule.acoo == 1">active</s:if>"> <input type="radio" value="1" id="acoo1" name="periodRule.acoo" <s:if test="periodRule.acoo == 1">checked="checked"</s:if>>是
					</label> <label class="btn btn-default <s:if test="periodRule.acoo == 0">active</s:if>"> <input type="radio" value="0" id="acoo2" name="periodRule.acoo" <s:if test="periodRule.acoo == 0">checked="checked"</s:if>>否
					</label>
				</div>
			</div>
			<label class="col-md-2 control-label">下班启用自动算加班:</label>
			<div class="col-md-4">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="periodRule.acwo == 1">active</s:if>"> <input type="radio" value="1" id="acwo1" name="periodRule.acwo" <s:if test="periodRule.acwo == 1">checked="checked"</s:if>>是
					</label> <label class="btn btn-default <s:if test="periodRule.acwo == 0">active</s:if>"> <input type="radio" value="0" id="acwo2" name="periodRule.acwo" <s:if test="periodRule.acwo == 0">checked="checked"</s:if>>否
					</label>
				</div>
			</div>
		</div>
	</fieldset>
</form>