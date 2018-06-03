<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="supplierAptitudeInfoForm" class="form-horizontal" style="padding: 35px 15px;" action="${nvix}/nvixnt/vixntSupplierAction!saveOrUpdateAptitudeInfo.action">
	<input type="hidden" id="supplierAptitudeInfoSupplierId" name="supplierAptitudeInfo.supplier.id" value="${supplierAptitudeInfo.supplier.id}" />
	<input type="hidden" id="id" name="supplierAptitudeInfo.id" value="${supplierAptitudeInfo.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-3 control-label"> <span style="color: red;">*</span> 资质名称:</label>
			<div class="col-md-8">
				<input id="aptitudeInfoName" name="supplierAptitudeInfo.name" value="${supplierAptitudeInfo.name}" type="text" class="form-control validate[required]" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">资质有效期:</label>
			<div class="col-md-8">
				<input id="aptitudeInfoValidPeriod" name="supplierAptitudeInfo.validPeriod" value="${supplierAptitudeInfo.validPeriod}" type="text" class="form-control" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">资质描述:</label>
			<div class="col-md-8">
				<input id="aptitudeInfoDescription" name="supplierAptitudeInfo.description" value="${supplierAptitudeInfo.description}" type="text" class="form-control" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">资质文件:</label>
			<div class="col-md-8">
				<input id="aptitudeInfoFiles" name="supplierAptitudeInfo.files" value="${supplierAptitudeInfo.files}" type="text" class="form-control" />
			</div>
		</div>
	</fieldset>			
</form>
