<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="itemCatalogForm" class="form-horizontal" style="padding: 20px 20px;" action="${nvix}/nvixnt/mdm/nvixntItemCatalogAction!saveOrUpdate.action">
	<input type="hidden" id="id" name="itemCatalog.id" value="${itemCatalog.id}" /> 
	<input type="hidden" id="parentItemCatalogId" name="itemCatalog.parentItemCatalog.id" value="${itemCatalog.parentItemCatalog.id}" /> 
	<input type="hidden" id="parentItemCatalogCode" name="itemCatalog.parentItemCatalog.codeRule" value="${itemCatalog.parentItemCatalog.codeRule}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label">父分类:</label>
			<s:if test="itemCatalog.parentitemCatalog.id != null">
				<div class="col-md-4">
					<input id="name" value="${itemCatalog.parentItemCatalog.name}" data-prompt-position="topLeft" class="form-control" type="text" readonly="readonly" />
				</div>
			</s:if>
			<s:else>
				<div class="col-md-4">
					<div class="row">
						<div class="col-sm-12">
							<div id="treeMenu" class="input-group">
								<input id="parentItemCatalogName" name="itemCatalog.parentItemCatalog.name" type="text" onfocus="showICMenu(); return false;" value="${itemCatalog.parentItemCatalog.name}" type="text" readonly="readonly" class="form-control" />
								<div class="input-group-btn">
									<button type="button" class="btn btn-default" onclick="$('#parentItemCatalogId').val('');$('#parentItemCatalogName').val('');">清空</button>
								</div>
								<div id="treeMenuContent" class="menuContent" style="display: none; position: absolute; border: 1px solid gray; background-color: #dfdfdf; z-index: 9999;">
									<ul id="parentItemCatalogTree" class="ztree"></ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</s:else>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>编码:</label>
			<div class="col-md-4">
				<input id="codeRule" name="itemCatalog.codeRule" value="${itemCatalog.codeRule}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>名称:</label>
			<div class="col-md-4">
				<input id="name" name="itemCatalog.name" value="${itemCatalog.name}" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label">预设验证标准:</label>
			<div class="col-md-4">
				<input id="preExamineStandard" name="itemCatalog.preExamineStandard" value="${itemCatalog.preExamineStandard}" class="form-control" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">预设采购天数:</label>
			<div class="col-md-4">
				<input id="prePurchaseDays" name="itemCatalog.prePurchaseDays" value="${itemCatalog.prePurchaseDays}" class="form-control" type="text" />
			</div>
			<label class="col-md-2 control-label">预设制造天数:</label>
			<div class="col-md-4">
				<input id="preProduceDays" name="itemCatalog.preProduceDays" value="${itemCatalog.preProduceDays}" class="form-control" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">预设检验天数:</label>
			<div class="col-md-4">
				<input id="preExamineDays" name="itemCatalog.preExamineDays" value="${itemCatalog.preExamineDays}" class="form-control" type="text" />
			</div>
			<label class="col-md-2 control-label">预设备料天数:</label>
			<div class="col-md-4">
				<input id="preBackupDays" name="itemCatalog.preBackupDays" value="${itemCatalog.preBackupDays}" class="form-control" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">预设库存计量单位:</label>
			<div class="col-md-4">
				<input id="preInventoryUnit" name="itemCatalog.preInventoryUnit" value="${itemCatalog.preInventoryUnit}" class="form-control" type="text" />
			</div>
			<label class="col-md-2 control-label">预设批号控制标识:</label>
			<div class="col-md-4">
				<input id="preBatchCode" name="itemCatalog.preBatchCode" value="${itemCatalog.preBatchCode}" class="form-control" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">预设虚项标识:</label>
			<div class="col-md-4">
				<input id="preVirtualItem" name="itemCatalog.preVirtualItem" value="${itemCatalog.preVirtualItem}" class="form-control" type="text" />
			</div>
			<label class="col-md-2 control-label">预设是否单项发料:</label>
			<div class="col-md-4">
				<input id="isSingalItemDelivery" name="itemCatalog.isSingalItemDelivery" value="${itemCatalog.isSingalItemDelivery}" class="form-control" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">预设整包发料标识:</label>
			<div class="col-md-4">
				<input id="preWholePackaged" name="itemCatalog.preWholePackaged" value="${itemCatalog.preWholePackaged}" class="form-control" type="text" />
			</div>
			<label class="col-md-2 control-label">预设承认标识:</label>
			<div class="col-md-4">
				<input id="preSingal" name="itemCatalog.preSingal" value="${itemCatalog.preSingal}" class="form-control" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">预设循环盘点周期:</label>
			<div class="col-md-4">
				<input id="preCycleCheck" name="itemCatalog.preCycleCheck" value="${itemCatalog.preCycleCheck}" class="form-control" type="text" />
			</div>
			<label class="col-md-2 control-label">预设损耗率:</label>
			<div class="col-md-4">
				<div class="input-group">
					<input id="preAttritionRate" name="itemCatalog.preAttritionRate" value="${itemCatalog.preAttritionRate}" class="form-control" type="text" /> <span class="input-group-addon"><i class="fa">(1-100)%</i></span>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">预设损耗发料标识:</label>
			<div class="col-md-4">
				<input id="preAttritionDelivery" name="itemCatalog.preAttritionDelivery" value="${itemCatalog.preAttritionDelivery}" class="form-control" type="text" />
			</div>
			<label class="col-md-2 control-label">预设海关商品编码:</label>
			<div class="col-md-4">
				<input id="preCustomhouseProductCode" name="itemCatalog.preCustomhouseProductCode" value="${itemCatalog.preCustomhouseProductCode}" class="form-control" type="text" />
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	/** 初始化分类下拉列表树 */
	var showICMenu = initDropListTree("treeMenu", "${nvix}/nvixnt/mdm/nvixntItemCatalogAction!findTreeToJson.action", "parentItemCatalogId", "parentItemCatalogName", "parentItemCatalogTree", "treeMenuContent");
</script>