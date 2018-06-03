<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="overTimeForm" class="form-horizontal" style="padding: 40px 40px;" action="${nvix}/nvixnt/overTimeAction!saveOrUpdate.action" method="post">
	<input id="id" name="overTimeRule.id" value="${overTimeRule.id }" type="hidden" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>编码:</label>
			<div class="col-md-3">
				<input id="code" name="overTimeRule.code" value="${overTimeRule.code}" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-3 control-label"><span class="text-danger">*</span>名称:</label>
			<div class="col-md-3">
				<input id="name" name="overTimeRule.name" value="${overTimeRule.name}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<h6>工时加班倍率</h6>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>启用工时加班倍率:</label>
			<div class="col-md-3">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="overTimeRule.isEnableOTRate == 1">active</s:if>"> <input type="radio" value="1" id="isEnableOTRate" name="overTimeRule.isEnableOTRate" class="form-control validate[required]" <s:if test="overTimeRule.isEnableOTRate == 1">checked="checked"</s:if>>是
					</label> <label class="btn btn-default <s:if test="overTimeRule.isEnableOTRate == 0">active</s:if>"> <input type="radio" value="0" id="isEnableOTRate" name="overTimeRule.isEnableOTRate" <s:if test="overTimeRule.isEnableOTRate == 0">checked="checked"</s:if>>否
					</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">日常加班倍率:</label>
			<div class="col-md-2">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="overTimeRule.isDailyOTRate == 1">active</s:if>"> <input type="radio" value="1" id="isDailyOTRate" name="overTimeRule.isDailyOTRate" <s:if test="overTimeRule.isDailyOTRate == 1">checked="checked"</s:if>>是
					</label> <label class="btn btn-default <s:if test="overTimeRule.isDailyOTRate == 0">active</s:if>"> <input type="radio" value="0" id="isDailyOTRate" name="overTimeRule.isDailyOTRate" <s:if test="overTimeRule.isDailyOTRate == 0">checked="checked"</s:if>>否
					</label>
				</div>
			</div>
			<label class="col-md-2 control-label">倍率:</label>
			<div class="col-md-2">
				<div class="input-group">
					<input id="dailyOTRate" name="overTimeRule.dailyOTRate" value="${overTimeRule.dailyOTRate}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" /> <span class="input-group-addon"><i class="fa">倍</i></span>
				</div>
			</div>
			<label class="col-md-2 control-label">需要加班确认单:</label>
			<div class="col-md-2">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="overTimeRule.dailyConfirmation == 1">active</s:if>"> <input type="radio" value="1" id="dailyConfirmation" name="overTimeRule.dailyConfirmation" <s:if test="overTimeRule.dailyConfirmation == 1">checked="checked"</s:if>>是
					</label> <label class="btn btn-default <s:if test="overTimeRule.dailyConfirmation == 0">active</s:if>"> <input type="radio" value="0" id="dailyConfirmation" name="overTimeRule.dailyConfirmation" <s:if test="overTimeRule.dailyConfirmation == 0">checked="checked"</s:if>>否
					</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">周末加班倍率:</label>
			<div class="col-md-2">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="overTimeRule.isWeekendOTRatel == 1">active</s:if>"> <input type="radio" value="1" id="isWeekendOTRatel" name="overTimeRule.isWeekendOTRatel" <s:if test="overTimeRule.isWeekendOTRatel == 1">checked="checked"</s:if>>是
					</label> <label class="btn btn-default <s:if test="overTimeRule.isWeekendOTRatel == 0">active</s:if>"> <input type="radio" value="0" id="isWeekendOTRatel" name="overTimeRule.isWeekendOTRatel" <s:if test="overTimeRule.isWeekendOTRatel == 0">checked="checked"</s:if>>否
					</label>
				</div>
			</div>
			<label class="col-md-2 control-label">倍率:</label>
			<div class="col-md-2">
				<div class="input-group">
					<input id="weekendOTRatel" name="overTimeRule.weekendOTRatel" value="${overTimeRule.weekendOTRatel}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" /> <span class="input-group-addon"><i class="fa">倍</i></span>
				</div>
			</div>
			<label class="col-md-2 control-label">需要加班确认单:</label>
			<div class="col-md-2">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="overTimeRule.weekendConfirmation == 1">active</s:if>"> <input type="radio" value="1" id="weekendConfirmation" name="overTimeRule.weekendConfirmation" <s:if test="overTimeRule.weekendConfirmation == 1">checked="checked"</s:if>>是
					</label> <label class="btn btn-default <s:if test="overTimeRule.weekendConfirmation == 0">active</s:if>"> <input type="radio" value="0" id="weekendConfirmation" name="overTimeRule.weekendConfirmation" <s:if test="overTimeRule.weekendConfirmation == 0">checked="checked"</s:if>>否
					</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">假日加班倍率:</label>
			<div class="col-md-2">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="overTimeRule.isHolidayOTRatel == 1">active</s:if>"> <input type="radio" value="1" id="isHolidayOTRatel" name="overTimeRule.isHolidayOTRatel" <s:if test="overTimeRule.isHolidayOTRatel == 1">checked="checked"</s:if>>是
					</label> <label class="btn btn-default <s:if test="overTimeRule.isHolidayOTRatel == 0">active</s:if>"> <input type="radio" value="0" id="isHolidayOTRatel" name="overTimeRule.isHolidayOTRatel" <s:if test="overTimeRule.isHolidayOTRatel == 0">checked="checked"</s:if>>否
					</label>
				</div>
			</div>
			<label class="col-md-2 control-label">倍率:</label>
			<div class="col-md-2">
				<div class="input-group">
					<input id="holidayOTRatel" name="overTimeRule.holidayOTRatel" value="${overTimeRule.holidayOTRatel}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" /> <span class="input-group-addon"><i class="fa">倍</i></span>
				</div>
			</div>
			<label class="col-md-2 control-label">需要加班确认单:</label>
			<div class="col-md-2">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="overTimeRule.holidayConfirmation == 1">active</s:if>"> <input type="radio" value="1" id="holidayConfirmation" name="overTimeRule.holidayConfirmation" <s:if test="overTimeRule.holidayConfirmation == 1">checked="checked"</s:if>>是
					</label> <label class="btn btn-default <s:if test="overTimeRule.holidayConfirmation== 0">active</s:if>"> <input type="radio" value="0" id="holidayConfirmation" name="overTimeRule.holidayConfirmation" <s:if test="overTimeRule.holidayConfirmation == 0">checked="checked"</s:if>>否
					</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">工时不足用加班补足:</label>
			<div class="col-md-2">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="overTimeRule.compensationWorkTimes == 1">active</s:if>"> <input type="radio" value="1" id="compensationWorkTimes" name="overTimeRule.compensationWorkTimes" <s:if test="overTimeRule.compensationWorkTimes == 1">checked="checked"</s:if>>是
					</label> <label class="btn btn-default <s:if test="overTimeRule.compensationWorkTimes == 0">active</s:if>"> <input type="radio" value="0" id="compensationWorkTimes" name="overTimeRule.compensationWorkTimes" <s:if test="overTimeRule.compensationWorkTimes == 0">checked="checked"</s:if>>否
					</label>
				</div>
			</div>
			<label class="col-md-2 control-label">轮休时间不记加班:</label>
			<div class="col-md-2">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="overTimeRule.needOT == 1">active</s:if>"> <input type="radio" value="1" id="needOT" name="overTimeRule.needOT" <s:if test="overTimeRule.needOT == 1">checked="checked"</s:if>>是
					</label> <label class="btn btn-default <s:if test="overTimeRule.needOT == 0">active</s:if>"> <input type="radio" value="0" id="needOT" name="overTimeRule.needOT" <s:if test="overTimeRule.needOT == 0">checked="checked"</s:if>>否
					</label>
				</div>
			</div>
			<label class="col-md-2 control-label">周日不加班算旷工:</label>
			<div class="col-md-2">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="overTimeRule.weekendAbsenteeism == 1">active</s:if>"> <input type="radio" value="1" id="weekendAbsenteeism" name="overTimeRule.weekendAbsenteeism" <s:if test="overTimeRule.weekendAbsenteeism == 1">checked="checked"</s:if>>是
					</label> <label class="btn btn-default <s:if test="overTimeRule.weekendAbsenteeism == 0">active</s:if>"> <input type="radio" value="0" id="weekendAbsenteeism" name="overTimeRule.weekendAbsenteeism" <s:if test="overTimeRule.weekendAbsenteeism == 0">checked="checked"</s:if>>否
					</label>
				</div>
			</div>
		</div>
		<div class="form-group">

			<label class="col-md-2 control-label">假日不加班算旷工:</label>
			<div class="col-md-2">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="overTimeRule.holidayAbsenteeism == 1">active</s:if>"> <input type="radio" value="1" id="holidayAbsenteeism" name="overTimeRule.holidayAbsenteeism" <s:if test="overTimeRule.holidayAbsenteeism == 1">checked="checked"</s:if>>是
					</label> <label class="btn btn-default <s:if test="overTimeRule.holidayAbsenteeism == 0">active</s:if>"> <input type="radio" value="0" id="holidayAbsenteeism" name="overTimeRule.holidayAbsenteeism" <s:if test="overTimeRule.holidayAbsenteeism == 0">checked="checked"</s:if>>否
					</label>
				</div>
			</div>
			<label class="col-md-2 control-label">不计入班段平时工时:</label>
			<div class="col-md-2">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="overTimeRule.isDisableOT == 1">active</s:if>"> <input type="radio" value="1" id="isDisableOT" name="overTimeRule.isDisableOT" <s:if test="overTimeRule.isDisableOT == 1">checked="checked"</s:if>>是
					</label> <label class="btn btn-default <s:if test="overTimeRule.isDisableOT == 0">active</s:if>"> <input type="radio" value="0" id="isDisableOT" name="overTimeRule.isDisableOT" <s:if test="overTimeRule.isDisableOT == 0">checked="checked"</s:if>>否
					</label>
				</div>
				<span class="help-block">(加班时间)</span>
			</div>
			<label class="col-md-2 control-label">假日加班倍率计算:</label>
			<div class="col-md-2">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="overTimeRule.hORC == 1">active</s:if>"> <input type="radio" value="1" id="hORC" name="overTimeRule.hORC" <s:if test="overTimeRule.hORC == 1">checked="checked"</s:if>>是
					</label> <label class="btn btn-default <s:if test="overTimeRule.hORC == 0">active</s:if>"> <input type="radio" value="0" id="hORC" name="overTimeRule.hORC" <s:if test="overTimeRule.hORC == 0">checked="checked"</s:if>>否
					</label>
				</div>
				<span class="help-block">按(假日登记)中的倍率进行计算</span>
			</div>
		</div>
		<h6>加班规则计算</h6>
		<div class="form-group">
			<label class="col-md-2 control-label">提前上班:</label>
			<div class="col-md-2">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="overTimeRule.earlyWork == 1">active</s:if>"> <input type="radio" value="1" id="earlyWork" name="overTimeRule.earlyWork" <s:if test="overTimeRule.earlyWork == 1">checked="checked"</s:if>>是
					</label> <label class="btn btn-default <s:if test="overTimeRule.earlyWork == 0">active</s:if>"> <input type="radio" value="0" id="earlyWork" name="overTimeRule.earlyWork" <s:if test="overTimeRule.earlyWork == 0">checked="checked"</s:if>>否
					</label>
				</div>
			</div>
			<label class="col-md-2 control-label">提前时间:</label>
			<div class="col-md-2">
				<div class="input-group">
					<input id="oTHs" name="overTimeRule.oTHs" value="${overTimeRule.oTHs}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" /> <span class="input-group-addon"><i class="fa">分钟</i></span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">推迟下班:</label>
			<div class="col-md-2">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="overTimeRule.delayedWork == 1">active</s:if>"> <input type="radio" value="1" id="delayedWork" name="overTimeRule.delayedWork" <s:if test="overTimeRule.delayedWork == 1">checked="checked"</s:if>>是
					</label> <label class="btn btn-default <s:if test="overTimeRule.delayedWork == 0">active</s:if>"> <input type="radio" value="0" id="delayedWork" name="overTimeRule.delayedWork" <s:if test="overTimeRule.delayedWork == 0">checked="checked"</s:if>>否
					</label>
				</div>
			</div>
			<label class="col-md-2 control-label">推迟时间:</label>
			<div class="col-md-2">
				<div class="input-group">
					<input id="offDutyHours" name="overTimeRule.offDutyHours" value="${overTimeRule.offDutyHours}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" /> <span class="input-group-addon"><i class="fa">分钟</i></span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">日常加班时长:</label>
			<div class="col-md-2">
				<div class="input-group">
					<input id="workingDayMaxOTHsOne" name="overTimeRule.workingDayMaxOTHsOne" value="${overTimeRule.workingDayMaxOTHsOne}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" /> <span class="input-group-addon"><i class="fa">小时</i></span>
				</div>
				<span class="help-block">超出部分扣除</span>
			</div>
			<label class="col-md-2 control-label">额外扣除:</label>
			<div class="col-md-2">
				<div class="input-group">
					<input id="workingDayMaxOTHsTwo" name="overTimeRule.workingDayMaxOTHsTwo" value="${overTimeRule.workingDayMaxOTHsTwo}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" /> <span class="input-group-addon"><i class="fa">小时</i></span>
				</div>
				<span class="help-block">(为0时多余加时全部扣除)</span>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">周日假日加班时长:</label>
			<div class="col-md-2">
				<div class="input-group">
					<input id="weekendMaxOTHsOne" name="overTimeRule.weekendMaxOTHsOne" value="${overTimeRule.weekendMaxOTHsOne}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" /> <span class="input-group-addon"><i class="fa">小时</i></span>
				</div>
				<span class="help-block">超出部分计平时加班</span>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">周日假日加班超过:</label>
			<div class="col-md-2">
				<div class="input-group">
					<input id="weekendMaxOTHsTwo" name="overTimeRule.weekendMaxOTHsTwo" value="${overTimeRule.weekendMaxOTHsTwo}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" /> <span class="input-group-addon"><i class="fa">小时</i></span>
				</div>
			</div>
			<label class="col-md-2 control-label">额外扣除:</label>
			<div class="col-md-2">
				<div class="input-group">
					<input id="weekendMaxOTHsThree" name="overTimeRule.weekendMaxOTHsThree" value="${overTimeRule.weekendMaxOTHsThree}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" /> <span class="input-group-addon"><i class="fa">小时</i></span>
				</div>
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">

</script>