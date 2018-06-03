<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="contractWarningForm" class="form-horizontal" style="padding: 35px 15px;" action="${nvix}/nvixnt/vixntSupplierContractAction!saveOrUpdateContractWarning.action">
	<input type="hidden" id="contractWarningcontractId" name="contractWarning.contract.id" value="${contractWarning.contract.id}" />
	<input type="hidden" id="id" name="contractWarning.id" value="${contractWarning.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-3 control-label"> <span style="color: red;">*</span> 预警信息:</label>
			<div class="col-md-8">
				<input id="warnningContent" name="contractWarning.warnningContent" value="${contractWarning.warnningContent}" type="text" class="form-control validate[required]" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">类型:</label>
			<div class="col-md-8">
				<input id="warnningType" name="contractWarning.warnningType" value="${contractWarning.warnningType}" type="text" class="form-control" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label"> <span style="color: red;">*</span> 预警时间:</label>
			<div class="col-md-8">
				<div class="input-group">
					<input id="warnningTime" name="contractWarning.warnningTime" value="${contractWarning.warnningTime}" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> <span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'warnningTime'});"><i
						class="fa fa-calendar"></i></span>
				</div>
			</div>
		</div>
	</fieldset>			
</form>
