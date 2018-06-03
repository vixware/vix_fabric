<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="applicationFormForm" class="form-horizontal" style="padding: 35px 15px;" action="${nvix}/nvixnt/contract/vixntLaborContractAction!saveOrUpdateApplicationForm.action">
	<input type="hidden" id="applicationFormcontractId" name="applicationForm.contract.id" value="${applicationForm.contract.id}" />
	<input type="hidden" id="id" name="applicationForm.id" value="${applicationForm.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-3 control-label"> <span style="color: red;">*</span> 审批人:</label>
			<div class="col-md-8">
				<input id="underTakePerson" name="applicationForm.underTakePerson" value="${applicationForm.underTakePerson}" type="text" class="form-control validate[required]" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">审批日期:</label>
			<div class="col-md-8">
				<div class="input-group">
					<input id="underTakeDate" name="applicationForm.underTakeDate" value="${applicationForm.underTakeDate}" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> <span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'underTakeDate'});"><i
						class="fa fa-calendar"></i></span>
				</div>
			</div>
		</div>
	</fieldset>			
</form>
