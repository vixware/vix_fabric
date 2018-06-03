<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="contractSubjectForm" class="form-horizontal" style="padding: 35px 15px;" action="${nvix}/nvixnt/vixntSupplierContractAction!saveOrUpdateContractSubject.action" method="get">
	<input type="hidden" id="contractSubjectContractInquiryId" name="contractSubject.contract.id" value="${contractSubject.contract.id}" />
	<input type="hidden" id="id" name="contractSubject.id" value="${contractSubject.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"> <span style="color: red;">*</span> 标的编码:</label>
			<div class="col-md-3">
				<input id="subjectCode" name="contractSubject.subjectCode" value="${contractSubject.subjectCode}" type="text" class="form-control validate[required]" />
			</div>
			<label class="col-md-2 control-label">标的名称:</label>
			<div class="col-md-3">
				<input id="subjectName" name="contractSubject.subjectName" value="${contractSubject.subjectName}" type="text" class="form-control" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">来源:</label>
			<div class="col-md-3">
				<input id="source" name="contractSubject.source" value="${contractSubject.source}" type="text" class="form-control" />
			</div>
			<label class="col-md-2 control-label">存货分类编码:</label>
			<div class="col-md-3">
				<input id="stockClassificationCode" name="contractSubject.stockClassificationCode" value="${contractSubject.stockClassificationCode}" type="text" class="form-control" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">对应存货编码:</label>
			<div class="col-md-3">
				<input id="correspondingInventoryCode" name="contractSubject.correspondingInventoryCode" value="${contractSubject.correspondingInventoryCode}" type="text" class="form-control" />
			</div>
			<label class="col-md-2 control-label">项目大类:</label>
			<div class="col-md-3">
				<input id="projectCatalog" name="contractSubject.projectCatalog" value="${contractSubject.projectCatalog}" type="text" class="form-control" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">存货规格型号:</label>
			<div class="col-md-3">
				<input id="inventoriesSpecification" name="contractSubject.inventoriesSpecification" value="${contractSubject.inventoriesSpecification}" type="text" class="form-control" />
			</div>
			<label class="col-md-2 control-label">无税原币单价:</label>
			<div class="col-md-3">
				<input id="nnTOCurrencyPrice" name="contractSubject.nnTOCurrencyPrice" value="${contractSubject.nnTOCurrencyPrice}" type="text" class="form-control" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">含税原币单价</label>
			<div class="col-md-3">
				<input id="ttIOriginalCurrencyPrice" name="contractSubject.ttIOriginalCurrencyPrice" value="${contractSubject.ttIOriginalCurrencyPrice}" type="text" class="form-control" />
			</div>
			<label class="col-md-2 control-label">无税原币金额:</label>
			<div class="col-md-3">
				<input id="nnTaxAmountOriginalCurrency" name="contractSubject.nnTaxAmountOriginalCurrency" value="${contractSubject.nnTaxAmountOriginalCurrency}" type="text" class="form-control" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">含税原币金额:</label>
			<div class="col-md-3">
				<input id="ttATOriginalCurrency" name="contractSubject.ttATOriginalCurrency" value="${contractSubject.ttATOriginalCurrency}" type="text" class="form-control" />
			</div>
			<label class="col-md-2 control-label">执行数量:</label>
			<div class="col-md-3">
				<input id="executionQuantity" name="contractSubject.executionQuantity" value="${contractSubject.executionQuantity}" type="text" class="form-control" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">执行无税金额原币:</label>
			<div class="col-md-3">
				<input id="eeTAOriginalCurrency" name="contractSubject.eeTAOriginalCurrency" value="${contractSubject.eeTAOriginalCurrency}" type="text" class="form-control" />
			</div>
			<label class="col-md-2 control-label">执行含税金额原币:</label>
			<div class="col-md-3">
				<input id="eeTAIncLriginalCurrency" name="contractSubject.eeTAIncLriginalCurrency" value="${contractSubject.eeTAIncLriginalCurrency}" type="text" class="form-control" />
			</div>
		</div>
	</fieldset>			
</form>
