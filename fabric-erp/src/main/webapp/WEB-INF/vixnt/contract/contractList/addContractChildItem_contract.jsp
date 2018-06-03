<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="contractChildItemForm" class="form-horizontal" style="padding: 35px 15px;" action="${nvix}/nvixnt/contract/vixntContractAction!saveOrUpdateContractChildItem.action">
	<input type="hidden" id="contractChildItemcontractId" name="contractChildItem.contract.id" value="${contractChildItem.contract.id}" />
	<input type="hidden" id="id" name="contractChildItem.id" value="${contractChildItem.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-3 control-label"> <span style="color: red;">*</span> 类型:</label>
			<div class="col-md-8">
				<input id="contractType" name="contractChildItem.contractType" value="${contractChildItem.contractType}" type="text" class="form-control validate[required]" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label">状态:</label>
			<div class="col-md-8">
				<input id="contractStatus" name="contractChildItem.contractStatus" value="${contractChildItem.contractStatus}" type="text" class="form-control" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-3 control-label"> <span style="color: red;">*</span> 内容:</label>
			<div class="col-md-8">
				<input id="theContract" name="contractChildItem.theContract" value="${contractChildItem.theContract}" type="text" class="form-control validate[required]" />
			</div>
		</div>
	</fieldset>			
</form>
