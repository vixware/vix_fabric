<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="otherForm" class="form-horizontal" style="padding: 40px 40px;" action="${nvix}/nvixnt/otherRuleAction!saveOrUpdate.action" method="post">
	<input id="id" name="otherRule.id" value="${otherRule.id }" type="hidden" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-3 control-label"><span class="text-danger">*</span>编码:</label>
			<div class="col-md-3">
				<input id="code" name="otherRule.code" value="${otherRule.code}" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-3 control-label"><span class="text-danger">*</span>名称:</label>
			<div class="col-md-3">
				<input id="name" name="otherRule.name" value="${otherRule.name}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">中途外出:</label>
			<div class="col-md-3">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="otherRule.goOut == 1">active</s:if>"> <input type="radio" value="1" id="goOut" name="otherRule.goOut" <s:if test="otherRule.goOut == 1">checked="checked"</s:if>>是
					</label> <label class="btn btn-default <s:if test="otherRule.goOut == 0">active</s:if>"> <input type="radio" value="0" id="goOut" name="otherRule.goOut" <s:if test="otherRule.goOut == 0">checked="checked"</s:if>>否
					</label>
				</div>
			</div>
			<label class="col-md-3 control-label">外出扣工时起算时间:</label>
			<div class="col-md-3">
				<div class="input-group">
					<input id="minLeave" name="otherRule.minLeave" value="${otherRule.minLeave}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" /> <span class="input-group-addon"><i class="fa">分钟</i></span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">单次刷卡算缺勤:</label>
			<div class="col-md-3">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="otherRule.isOnce == 1">active</s:if>"> <input type="radio" value="1" id="isOnce" name="otherRule.isOnce" <s:if test="otherRule.isOnce == 1">checked="checked"</s:if>>是
					</label> <label class="btn btn-default <s:if test="otherRule.isOnce == 0">active</s:if>"> <input type="radio" value="0" id="isOnce" name="otherRule.isOnce" <s:if test="otherRule.isOnce == 0">checked="checked"</s:if>>否
					</label>
				</div>
			</div>
			<label class="col-md-3 control-label">请假计算起始时间:</label>
			<div class="col-md-3">
				<div class="input-group">
					<input id="leaveStartTime" name="otherRule.leaveStartTime" value="${otherRule.leaveStartTime}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" /> <span class="input-group-addon"><i class="fa">分钟</i></span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">忽略排班及自动套班:</label>
			<div class="col-md-3">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="otherRule.isEnabelNeglect == 1">active</s:if>"> <input type="radio" value="1" id="isEnabelNeglect" name="otherRule.isEnabelNeglect" <s:if test="otherRule.isEnabelNeglect == 1">checked="checked"</s:if>>是
					</label> <label class="btn btn-default <s:if test="otherRule.isEnabelNeglect == 0">active</s:if>"> <input type="radio" value="0" id="isEnabelNeglect" name="otherRule.isEnabelNeglect" <s:if test="otherRule.isEnabelNeglect == 0">checked="checked"</s:if>>否
					</label>
				</div>
			</div>
			<label class="col-md-3 control-label">跨天刷卡不显示为32:00格式:</label>
			<div class="col-md-3">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="otherRule.isEnableNextDay == 1">active</s:if>"> <input type="radio" value="1" id="isEnableNextDay" name="otherRule.isEnableNextDay" <s:if test="otherRule.isEnableNextDay == 1">checked="checked"</s:if>>是
					</label> <label class="btn btn-default <s:if test="otherRule.isEnableNextDay == 0">active</s:if>"> <input type="radio" value="0" id="isEnableNextDay" name="otherRule.isEnableNextDay" <s:if test="otherRule.isEnableNextDay == 0">checked="checked"</s:if>>否
					</label>
				</div>
			</div>
		</div>
	</fieldset>
</form>