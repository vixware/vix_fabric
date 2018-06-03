<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="invShelfForm" class="form-horizontal" style="padding: 40px 40px;" action="${nvix}/nvixnt/vixntWarehouseAction!saveOrUpdateShelf.action">
	<input type="hidden" id="id" name="invShelf.id" value="${invShelf.id}" /> <input type="hidden" id="invWarehouseId" name="invShelf.invWarehouse.id" value="${invShelf.invWarehouse.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>货位编码:</label>
			<div class="col-md-4">
				<input id="code" name="invShelf.code" value="${invShelf.code}" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>货位名称:</label>
			<div class="col-md-4">
				<input id="name" name="invShelf.name" value="${invShelf.name}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>仓库:</label>
			<div class="col-md-4">
				<input id="invWarehouseName" name="invShelf.invWarehouse.name" value="${invShelf.invWarehouse.name}" class="form-control validate[required]" type="text" readonly="readonly" />
			</div>
			<label class="col-md-2 control-label">最大库存数量:</label>
			<div class="col-md-4">
				<input id="maximum" name="invShelf.maximum" value="${invShelf.maximum}" class="form-control" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>是否默认:</label>
			<div class="col-md-4">
				<div data-toggle="buttons" class="btn-group">
					<label class="btn btn-default <s:if test="invShelf.isDefault == 1">active</s:if>"> <input type="radio" id="" name="invShelf.isDefault" data-prompt-position="topLeft" class="validate[required]" <s:if test="invShelf.isDefault == 1"> checked="checked"</s:if> value="1" />否
					</label> <label class="btn btn-default <s:if test="invShelf.isDefault == 0">active</s:if>"> <input type="radio" id="" name="invShelf.isDefault" data-prompt-position="topLeft" class="validate[required]" <s:if test="invShelf.isDefault == 0"> checked="checked"</s:if> value="0" />是
					</label>
				</div>
			</div>
			<label class="col-md-2 control-label">优先级:</label>
			<div class="col-md-4">
				<input id="priority" name="invShelf.priority" value="${invShelf.priority}" class="form-control" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">长:</label>
			<div class="col-md-4">
				<div class="input-group">
					<input id="shelfLength" name="invShelf.shelfLength" value="${invShelf.shelfLength}" class="form-control" type="text" /> <span class="input-group-addon">(cm)</span>
				</div>
			</div>
			<label class="col-md-2 control-label">宽:</label>
			<div class="col-md-4">
				<div class="input-group">
					<input id="shelfWidth" name="invShelf.shelfWidth" value="${invShelf.shelfWidth}" class="form-control" type="text" /> <span class="input-group-addon">(cm)</span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">高:</label>
			<div class="col-md-4">
				<div class="input-group">
					<input id="shelfHeight" name="invShelf.shelfHeight" value="${invShelf.shelfHeight}" class="form-control" type="text" /> <span class="input-group-addon">(cm)</span>
				</div>
			</div>
		</div>
	</fieldset>
</form>