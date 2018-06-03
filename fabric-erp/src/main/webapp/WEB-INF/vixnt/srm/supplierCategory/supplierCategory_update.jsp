<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="supplierCategoryForm" class="form-horizontal" style="padding: 20px 20px;" action="${nvix}/nvixnt/vixntSupplierCategoryAction!saveOrUpdate.action">
	<input type="hidden" id="id" name="supplierCategory.id" value="${supplierCategory.id}" />
	<input type="hidden" id="supplierCategoryId" name="supplierCategory.supplierCategory.id" value="${supplierCategory.supplierCategory.id}"/>
	<input type="hidden" id="supplierCategoryCode" name="supplierCategory.supplierCategory.code" value="${supplierCategory.supplierCategory.code}"/>
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label">父分类:</label>
			<s:if test="supplierCategory.supplierCategory.id != null">
				<div class="col-md-4">
					<input id="name" value="${supplierCategory.supplierCategory.name}" data-prompt-position="topLeft" class="form-control" type="text" readonly="readonly" />
				</div>
			</s:if>
			<s:else>
				<div class="col-md-4">
					<div class="row">
						<div class="col-sm-12">
							<div id="treeMenu" class="input-group">
								<input id="supplierCategoryName" name="supplierCategory.supplierCategory.name" type="text" onfocus="showICMenu(); return false;" value="${supplierCategory.supplierCategory.name}" type="text" readonly="readonly" class="form-control" />
								<div class="input-group-btn">
									<button type="button" class="btn btn-default" onclick="$('#supplierCategoryId').val('');$('#supplierCategoryName').val('');">清空</button>
								</div>
								<div id="treeMenuContent" class="menuContent" style="display: none; position: absolute; border: 1px solid gray; background-color: #dfdfdf; z-index: 9999;">
									<ul id="supplierCategoryTree" class="ztree"></ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</s:else>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>编码:</label>
			<div class="col-md-4">
				<input id="code" name="supplierCategory.code" value="${supplierCategory.code}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>名称:</label>
			<div class="col-md-4">
				<input id="name" name="supplierCategory.name" value="${supplierCategory.name}" class="form-control validate[required]" type="text" />
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
/** 初始化分类下拉列表树 */
var showICMenu = initDropListTree("treeMenu","${nvix}/nvixnt/vixntSupplierCategoryAction!findTreeToJson.action","supplierCategoryId","supplierCategoryName","supplierCategoryTree","treeMenuContent");
</script>