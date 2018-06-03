<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="ruleSetForm" class="form-horizontal" style="padding: 40px 40px;" action="${nvix}/nvixnt/attendanceRuleSetAction!saveOrUpdateRuleSet.action" method="post" enctype="multipart/form-data">
	<input id="id" name="attRuleSet.id" value="${attRuleSet.id }" type="hidden" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>班次编码:</label>
			<div class="col-md-4">
				<input id="shiftCode" name="attRuleSet.shiftCode" value="${attRuleSet.shiftCode}" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>班次名称:</label>
			<div class="col-md-4">
				<input id="shiftName" name="attRuleSet.shiftName" value="${attRuleSet.shiftName}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>执行时间段:</label>
			<div class="col-md-4">
				<select id="periodRuleId" name="attRuleSet.periodRule.id" data-prompt-position="topLeft" class="form-control validate[required]">
					<option value="">------请选择------</option>
					<c:forEach items="${periodRuleList}" var="entity">
						<option value="${entity.id}" <c:if test="${attRuleSet.periodRule.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
					</c:forEach>
				</select>
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>启用时间:</label>
			<div class="col-md-4">
				<div class="input-group">
					<input id="openedDate" name="attRuleSet.openedDate" value="${attRuleSet.openedDateStr}" data-prompt-position="topLeft" class="form-control validate[required custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> <span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'openedDate'});"><i
						class="fa fa-calendar"></i></span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>是否启用:</label>
			<div class="col-md-4">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="attRuleSet.isEnable == 1">active</s:if>"> <input type="radio" value="1" id="isEnable" name="attRuleSet.isEnable" <s:if test="attRuleSet.isEnable == 1">checked="checked"</s:if> class="validate[required]">启用
					</label> <label class="btn btn-default <s:if test="attRuleSet.isEnable == 0">active</s:if>"> <input type="radio" value="0" id="isEnable" name="attRuleSet.isEnable" <s:if test="attRuleSet.isEnable == 0">checked="checked"</s:if>>禁用
					</label>
				</div>
			</div>
		</div>
		<%-- <div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>周期数:</label>
			<div class="col-md-4">
				<input id="cycle" name="attRuleSet.cycle" value="${attRuleSet.cycle}" class="form-control validate[required]" type="text"/>
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>周期单位:</label>
			<div class="col-md-4">
				<select id="decSize" name="attRuleSet.decSize" data-prompt-position="topLeft" class="form-control validate[required]">
					<optgroup label="">
						<option value="">------请选择------</option>
						<option value="1" <s:if test="attRuleSet.decSize == 1">selected="selected"</s:if>>日</option>
						<option value="2" <s:if test="attRuleSet.decSize == 2">selected="selected"</s:if>>周</option>
						<option value="3" <s:if test="attRuleSet.decSize == 3">selected="selected"</s:if>>月</option>
						<option value="4" <s:if test="attRuleSet.decSize == 4">selected="selected"</s:if>>年</option>
					</optgroup>
				</select>
			</div>
		</div> --%>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>执行日:</label>
			<div class="col-lg-9">
				<div class="btn-group" data-toggle="buttons">
					<label class="btn btn-default <s:if test="attRuleSet.exerciseDate.contains(',1,')">active</s:if>"><input type="checkbox" name="attRuleSet.exerciseDate" value="1" <s:if test="attRuleSet.exerciseDate.contains(',1,')">checked="checked"</s:if> />周一</label> <label
						class="btn btn-default <s:if test="attRuleSet.exerciseDate.contains(',2,')">active</s:if>"><input type="checkbox" name="attRuleSet.exerciseDate" value="2" <s:if test="attRuleSet.exerciseDate.contains(',2,')">checked="checked"</s:if> />周二</label> <label class="btn btn-default <s:if test="attRuleSet.exerciseDate.contains(',3,')">active</s:if>"><input
						type="checkbox" name="attRuleSet.exerciseDate" value="3" <s:if test="attRuleSet.exerciseDate.contains(',3,')">checked="checked"</s:if> />周三</label> <label class="btn btn-default <s:if test="attRuleSet.exerciseDate.contains(',4,')">active</s:if>"><input type="checkbox" name="attRuleSet.exerciseDate" value="4"
						<s:if test="attRuleSet.exerciseDate.contains(',4,')">checked="checked"</s:if> />周四</label> <label class="btn btn-default <s:if test="attRuleSet.exerciseDate.contains(',5,')">active</s:if>"><input type="checkbox" name="attRuleSet.exerciseDate" value="5" <s:if test="attRuleSet.exerciseDate.contains(',5,')">checked="checked"</s:if>>周五</label> <label
						class="btn btn-default <s:if test="attRuleSet.exerciseDate.contains(',6,')">active</s:if>"><input type="checkbox" name="attRuleSet.exerciseDate" value="6" <s:if test="attRuleSet.exerciseDate.contains(',6,')">checked="checked"</s:if>>周六</label> <label
						class="btn btn-default <s:if test="attRuleSet.exerciseDate.contains(',7,')">active</s:if>"><input type="checkbox" name="attRuleSet.exerciseDate" value="7" <s:if test="attRuleSet.exerciseDate.contains(',7,')">checked="checked"</s:if>>周日</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">基本规则:</label>
			<div class="col-md-4">
				<select id="basicRuleId" name="attRuleSet.basicRule.id" data-prompt-position="topLeft" class="form-control">
					<option value="">------请选择------</option>
					<c:forEach items="${basicRuleList}" var="entity">
						<option value="${entity.id}" <c:if test="${attRuleSet.basicRule.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
					</c:forEach>
				</select>
			</div>
			<label class="col-md-2 control-label">加班规则:</label>
			<div class="col-md-4">
				<select id="overTimeRuleId" name="attRuleSet.overTimeRule.id" data-prompt-position="topLeft" class="form-control">
					<option value="">------请选择------</option>
					<c:forEach items="${overTimeRuleList}" var="entity">
						<option value="${entity.id}" <c:if test="${attRuleSet.overTimeRule.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">自动套班:</label>
			<div class="col-md-4">
				<select id="automaticRuleId" name="attRuleSet.automaticRule.id" data-prompt-position="topLeft" class="form-control">
					<option value="">------请选择------</option>
					<c:forEach items="${automaticRuleList}" var="entity">
						<option value="${entity.id}" <c:if test="${attRuleSet.automaticRule.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
					</c:forEach>
				</select>
			</div>
			<label class="col-md-2 control-label">计算方式:</label>
			<div class="col-md-4">
				<select id="calculationRuleId" name="attRuleSet.calculationRule.id" data-prompt-position="topLeft" class="form-control">
					<option value="">------请选择------</option>
					<c:forEach items="${calculationRuleList}" var="entity">
						<option value="${entity.id}" <c:if test="${attRuleSet.calculationRule.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">其它规则:</label>
			<div class="col-md-4">
				<select id="otherRuleId" name="attRuleSet.otherRule.id" data-prompt-position="topLeft" class="form-control">
					<option value="">------请选择------</option>
					<c:forEach items="${otherRuleList}" var="entity">
						<option value="${entity.id}" <c:if test="${attRuleSet.otherRule.id == entity.id}">selected="selected"</c:if>>${entity.name}</option>
					</c:forEach>
				</select>
			</div>
		</div>
	</fieldset>
</form>