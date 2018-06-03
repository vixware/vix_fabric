<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="automaticRuleForm" class="form-horizontal" style="padding: 40px 40px;" action="${nvix}/nvixnt/automaticAction!saveOrUpdate.action" method="post">
	<input id="id" name="automaticRule.id" value="${automaticRule.id }" type="hidden" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>编码:</label>
			<div class="col-md-3">
				<input id="code" name="automaticRule.code" value="${automaticRule.code}" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-3 control-label"><span class="text-danger">*</span>名称:</label>
			<div class="col-md-3">
				<input id="name" name="automaticRule.name" value="${automaticRule.name}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">允许自动套班:</label>
			<div class="col-md-3">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="automaticRule.isDisables == 1">active</s:if>"> <input type="radio" value="1" id="isDisables" name="automaticRule.isDisables" <s:if test="automaticRule.isDisables == 1">checked="checked"</s:if>>是
					</label> <label class="btn btn-default <s:if test="automaticRule.isDisables == 0">active</s:if>"> <input type="radio" value="0" id="isDisables" name="automaticRule.isDisables" <s:if test="automaticRule.isDisables == 0">checked="checked"</s:if>>否
					</label>
				</div>
			</div>
		</div>
		<br />
		<div class="form-group">
			<label class="col-md-3 control-label">计算请假和缺勤:</label>
			<div class="col-md-3">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="automaticRule.calculationLAA == 1">active</s:if>"> <input type="radio" value="1" id="calculationLAA" name="automaticRule.calculationLAA" <s:if test="automaticRule.calculationLAA == 1">checked="checked"</s:if>>是
					</label> <label class="btn btn-default <s:if test="automaticRule.calculationLAA == 0">active</s:if>"> <input type="radio" value="0" id="calculationLAA" name="automaticRule.calculationLAA" <s:if test="automaticRule.calculationLAA == 0">checked="checked"</s:if>>否
					</label>
				</div>
			</div>
			<label class="col-md-3 control-label">回溯匹配最佳刷卡记录:</label>
			<div class="col-md-3">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="automaticRule.recall == 1">active</s:if>"> <input type="radio" value="1" id="recall" name="automaticRule.recall" <s:if test="automaticRule.recall == 1">checked="checked"</s:if>>是
					</label> <label class="btn btn-default <s:if test="automaticRule.recall == 0">active</s:if>"> <input type="radio" value="0" id="recall" name="automaticRule.recall" <s:if test="automaticRule.recall == 0">checked="checked"</s:if>>否
					</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">匹配最佳班次:</label>
			<div class="col-md-3">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="automaticRule.baseShift == 1">active</s:if>"> <input type="radio" value="1" id="baseShift" name="automaticRule.baseShift" <s:if test="automaticRule.baseShift == 1">checked="checked"</s:if>>是
					</label> <label class="btn btn-default <s:if test="automaticRule.baseShift == 0">active</s:if>"> <input type="radio" value="0" id="baseShift" name="automaticRule.baseShift" <s:if test="automaticRule.baseShift == 0">checked="checked"</s:if>>否
					</label>
				</div>
			</div>
			<label class="col-md-3 control-label">排班错误再使用自动套班:</label>
			<div class="col-md-3">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="automaticRule.selfMotion == 1">active</s:if>"> <input type="radio" value="1" id="selfMotion" name="automaticRule.selfMotion" <s:if test="automaticRule.selfMotion == 1">checked="checked"</s:if>>是
					</label> <label class="btn btn-default <s:if test="automaticRule.selfMotion == 0">active</s:if>"> <input type="radio" value="0" id="selfMotion" name="automaticRule.selfMotion" <s:if test="automaticRule.selfMotion == 0">checked="checked"</s:if>>否
					</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">自动匹配所有班次:</label>
			<div class="col-md-3">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="automaticRule.selfMotionAll == 1">active</s:if>"> <input type="radio" value="1" id="selfMotionAll" name="automaticRule.selfMotionAll" <s:if test="automaticRule.selfMotionAll == 1">checked="checked"</s:if>>是
					</label> <label class="btn btn-default <s:if test="automaticRule.selfMotionAll == 0">active</s:if>"> <input type="radio" value="0" id="selfMotionAll" name="automaticRule.selfMotionAll" <s:if test="automaticRule.selfMotionAll == 0">checked="checked"</s:if>>否
					</label>
				</div>
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
</script>