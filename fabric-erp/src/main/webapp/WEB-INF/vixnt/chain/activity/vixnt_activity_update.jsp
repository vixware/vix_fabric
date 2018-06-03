<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="integralActivityForm" class="form-horizontal" style="padding: 35px 15px;" action="${nvix}/nvixnt/vixntActivityAction!saveOrUpdate.action">
	<input type="hidden" id="id" name="integralActivity.id" value="${integralActivity.id}" /> 
	<input type="hidden" id="channelDistributorId" name="integralActivity.channelDistributor.id" value="${integralActivity.channelDistributor.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-3 control-label"><span class="text-danger">*</span> 名称:</label>
			<div class="col-md-3">
				<input id="name" name="integralActivity.name" value="${integralActivity.name}" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-3 control-label"><span class="text-danger">*</span> 注册是否赠送积分:</label>
			<div class="col-md-3">
				<select id="isZc" name="integralActivity.isZc" class="form-control">
					<option value="1" <c:if test='${integralActivity.isZc eq "1"}'>selected="selected"</c:if>>是</option>
					<option value="2" <c:if test='${integralActivity.isZc eq "2"}'>selected="selected"</c:if>>否</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label"><span class="text-danger">*</span> 赠送分值:</label>
			<div class="col-md-3">
				<input id="presentZc" name="integralActivity.presentZc" value="${integralActivity.presentZc}" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-3 control-label"><span class="text-danger">*</span> 是否限时赠送积分:</label>
			<div class="col-md-3">
				<select id="isXszc" name="integralActivity.isXszc" class="form-control">
					<option value="1" <c:if test='${integralActivity.isXszc eq "1"}'>selected="selected"</c:if>>是</option>
					<option value="2" <c:if test='${integralActivity.isXszc eq "2"}'>selected="selected"</c:if>>否</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label"><span class="text-danger">*</span>开始时间:</label>
			<div class="col-md-3">
				<div class="input-group">
					<input id="xsStartDate" name="integralActivity.xsStartDate" value="${integralActivity.xsStartDateStr}" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" /> <span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',el:'xsStartDate'});"><i
						class="fa fa-calendar"></i></span>
				</div>
			</div>
			<label class="col-md-3 control-label"><span class="text-danger">*</span>结束时间:</label>
			<div class="col-md-3">
				<div class="input-group">
					<input id="xsEndDate" name="integralActivity.xsEndDate" value="${integralActivity.xsEndDateStr}" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" type="text" /> <span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',el:'xsEndDate'});"><i
						class="fa fa-calendar"></i></span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label"><span class="text-danger">*</span> 积分赠送比率:</label>
			<div class="col-md-3">
				<div class="input-group">
					<input id="conversiorate" name="integralActivity.conversiorate" value="${integralActivity.conversiorate}" class="form-control validate[required,custom[integer]]" type="text" /> <span class="input-group-addon">1~N</span>
				</div>
			</div>
			<label class="col-md-3 control-label"><span class="text-danger">*</span> 积分兑换比率:</label>
			<div class="col-md-3">
				<div class="input-group">
					<input id="integralConsumptionRatio" name="integralActivity.integralConsumptionRatio" value="${integralActivity.integralConsumptionRatio}" class="form-control validate[required,custom[number]]" type="text" /> <span class="input-group-addon">0~1</span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">备注:</label>
			<div class="col-md-9">
				<textarea name="integralActivity.memo" class="form-control">${integralActivity.memo } </textarea>
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	$("#integralActivityForm").validationEngine();
</script>