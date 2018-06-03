<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="ruleDetailForm" class="form-horizontal" style="padding: 40px 40px;" action="${nvix}/nvixnt/attendanceRuleSetAction!saveOrUpdateRuleDetail.action" method="post" enctype="multipart/form-data">
	<input id="id" name="basicRule.id" value="${basicRule.id }" type="hidden" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-3 control-label"><span class="text-danger">*</span>编码:</label>
			<div class="col-md-3">
				<input id="code" name="basicRule.code" value="${basicRule.code}" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-3 control-label"><span class="text-danger">*</span>名称:</label>
			<div class="col-md-3">
				<input id="name" name="basicRule.name" value="${basicRule.name}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label"><span class="text-danger">*</span>是否启用:</label>
			<div class="col-md-3">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="basicRule.isEnable == 1">active</s:if>"> <input type="radio" value="1" id="isEnable" name="basicRule.isEnable" <s:if test="basicRule.isEnable == 1">checked="checked"</s:if> class="validate[required]">启用
					</label> <label class="btn btn-default <s:if test="basicRule.isEnable == 0">active</s:if>"> <input type="radio" value="0" id="isEnable" name="basicRule.isEnable" <s:if test="basicRule.isEnable == 0">checked="checked"</s:if>>禁用
					</label>
				</div>
			</div>
		</div>
		<h6>刷卡规则</h6>
		<div class="form-group">
			<label class="col-md-3 control-label"><span class="text-danger">*</span>最长班次时段不超过:</label>
			<div class="col-md-3">
				<div class="input-group">
					<input id="maxShiftTime" name="basicRule.maxShiftTime" value="${basicRule.maxShiftTime}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" /> <span class="input-group-addon"><i class="fa">分钟</i></span>
				</div>
			</div>
			<label class="col-md-3 control-label"><span class="text-danger">*</span>最短班次时段不少于:</label>
			<div class="col-md-3">
				<div class="input-group">
					<input id="minShiftTime" name="basicRule.minShiftTime" value="${basicRule.minShiftTime}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" /> <span class="input-group-addon"><i class="fa">分钟</i></span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label"><span class="text-danger">*</span>刷卡间隔时长:</label>
			<div class="col-md-3">
				<div class="input-group">
					<input id="interval" name="basicRule.interval" value="${basicRule.interval}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" /> <span class="input-group-addon"><i class="fa">分钟</i></span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">上班最早刷卡优先:</label>
			<div class="col-md-3">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="basicRule.firstPunchCard == 1">active</s:if>"> <input type="radio" value="1" id="firstPunchCard" name="basicRule.firstPunchCard" <s:if test="basicRule.firstPunchCard == 1">checked="checked"</s:if>>是
					</label> <label class="btn btn-default <s:if test="basicRule.firstPunchCard == 0">active</s:if>"> <input type="radio" value="0" id="firstPunchCard" name="basicRule.firstPunchCard" <s:if test="basicRule.firstPunchCard == 0">checked="checked"</s:if>>否
					</label>
				</div>
			</div>
			<label class="col-md-3 control-label">下班最后刷卡优先:</label>
			<div class="col-md-3">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="basicRule.sex == 1">active</s:if>"> <input type="radio" value="1" id="lastPunchCard" name="basicRule.lastPunchCard" <s:if test="basicRule.lastPunchCard == 1">checked="checked"</s:if>>是
					</label> <label class="btn btn-default <s:if test="basicRule.sex == 0">active</s:if>"> <input type="radio" value="0" id="lastPunchCard" name="basicRule.lastPunchCard" <s:if test="basicRule.lastPunchCard == 0">checked="checked"</s:if>>否
					</label>
				</div>
			</div>
		</div>
		<h6>迟到早退规则</h6>
		<div class="form-group">
			<label class="col-md-3 control-label">工时中扣除迟到时间:</label>
			<div class="col-md-3">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="basicRule.deductionLateTime == 1">active</s:if>"> <input type="radio" value="1" id="deductionLateTime" name="basicRule.deductionLateTime" <s:if test="basicRule.deductionLateTime == 1">checked="checked"</s:if>>是
					</label> <label class="btn btn-default <s:if test="basicRule.deductionLateTime == 0">active</s:if>"> <input type="radio" value="0" id="deductionLateTime" name="basicRule.deductionLateTime" <s:if test="basicRule.deductionLateTime == 0">checked="checked"</s:if>>否
					</label>
				</div>
			</div>
			<label class="col-md-3 control-label">工时中扣除早退时间:</label>
			<div class="col-md-3">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="basicRule.deductionEarlyTime == 1">active</s:if>"> <input type="radio" value="1" id="deductionEarlyTime" name="basicRule.deductionEarlyTime" <s:if test="basicRule.deductionEarlyTime == 1">checked="checked"</s:if>>是
					</label> <label class="btn btn-default <s:if test="basicRule.deductionEarlyTime == 0">active</s:if>"> <input type="radio" value="0" id="deductionEarlyTime" name="basicRule.deductionEarlyTime" <s:if test="basicRule.deductionEarlyTime == 0">checked="checked"</s:if>>否
					</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">迟到早退不计入旷工:</label>
			<div class="col-md-3">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="basicRule.doNotIncludedAbsenteeism == 1">active</s:if>"> <input type="radio" value="1" id="doNotIncludedAbsenteeism" name="basicRule.doNotIncludedAbsenteeism" <s:if test="basicRule.doNotIncludedAbsenteeism == 1">checked="checked"</s:if>>是
					</label> <label class="btn btn-default <s:if test="basicRule.doNotIncludedAbsenteeism == 0">active</s:if>"> <input type="radio" value="0" id="doNotIncludedAbsenteeism" name="basicRule.doNotIncludedAbsenteeism" <s:if test="basicRule.doNotIncludedAbsenteeism == 0">checked="checked"</s:if>>否
					</label>
				</div>
			</div>
			<label class="col-md-3 control-label">节假日计算迟到早退:</label>
			<div class="col-md-3">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="basicRule.numerationHolidayLAE == 1">active</s:if>"> <input type="radio" value="1" id="numerationHolidayLAE" name="basicRule.numerationHolidayLAE" <s:if test="basicRule.numerationHolidayLAE == 1">checked="checked"</s:if>>是
					</label> <label class="btn btn-default <s:if test="basicRule.numerationHolidayLAE == 0">active</s:if>"> <input type="radio" value="0" id="numerationHolidayLAE" name="basicRule.numerationHolidayLAE" <s:if test="basicRule.numerationHolidayLAE == 0">checked="checked"</s:if>>否
					</label>
				</div>
			</div>
		</div>
	</fieldset>
</form>