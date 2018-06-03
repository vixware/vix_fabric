<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="craftsRouteDetailForm" class="form-horizontal" style="padding: 20px 20px;" action="${nvix}/nvixnt/produce/nvixCraftsRouteAction!saveOrUpdateCraftsRouteDetail.action">
	<input type="hidden" id="detailid" name="craftsRouteDetail.id" value="${craftsRouteDetail.id}" /> 
	<input type="hidden" id="workCenterId" name="craftsRouteDetail.workCenter.id" value="${craftsRouteDetail.workCenter.id}" /> 
	<input type="hidden" id="craftsRouteId" name="craftsRouteDetail.craftsRoute.id" value="${craftsRouteDetail.craftsRoute.id}" /> 
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>工序编号:</label>
			<div class="col-md-4">
				<input id="processCode" name="craftsRouteDetail.processCode" value="${craftsRouteDetail.processCode}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>工序名称:</label>
			<div class="col-md-4">
				<input id="standardProcedure" name="craftsRouteDetail.standardProcedure" value="${craftsRouteDetail.standardProcedure}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>工作中心:</label>
			<div class="col-md-4">
				<div class="input-group">
					<input id="workCenterName" value="${craftsRouteDetail.workCenter.orgName}" onfocus="chooseWorkCenter();" data-prompt-position="topLeft" class="form-control validate[required]" type="text" style="cursor: pointer;" readonly="readonly" /> 
					<span class="input-group-addon" style="cursor: pointer;" onclick="chooseWorkCenter();">选择</span>
				</div>
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>工序说明:</label>
			<div class="col-md-4">
				<input id="processExplain" name="craftsRouteDetail.processExplain" value="${craftsRouteDetail.processExplain}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">报告点:</label>
			<div class="col-md-4">
				<input id="repPoint" name="craftsRouteDetail.repPoint" value="${craftsRouteDetail.repPoint}" data-prompt-position="topLeft" class="form-control" type="text" />
			</div>
			<label class="col-md-2 control-label">倒冲工序:</label>
			<div class="col-md-4">
				<input id="reverseBlankingProcess" name="craftsRouteDetail.reverseBlankingProcess" value="${craftsRouteDetail.reverseBlankingProcess}" data-prompt-position="topLeft" class="form-control" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">委外工序:</label>
			<div class="col-md-4">
				<input id="qutsourcingProcess" name="craftsRouteDetail.qutsourcingProcess" value="${craftsRouteDetail.qutsourcingProcess}" data-prompt-position="topLeft" class="form-control" type="text" />
			</div>
			<label class="col-md-2 control-label">厂商名称:</label>
			<div class="col-md-4">
				<input id="tradeName" name="craftsRouteDetail.tradeName" value="${craftsRouteDetail.tradeName}" data-prompt-position="topLeft" class="form-control" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">备注:</label>
			<div class="col-md-10">
				<input id="memo" name="craftsRouteDetail.memo" value="${craftsRouteDetail.memo}" data-prompt-position="topLeft" class="form-control" type="text" />
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	$("#craftsRouteDetailForm").validationEngine();
	function chooseWorkCenter() {
		chooseListData('${nvix}/nvixnt/produce/nvixWorkCenterAction!goChooseWorkCenter.action', 'single', '选择工作中心', 'workCenter');
	}
</script>