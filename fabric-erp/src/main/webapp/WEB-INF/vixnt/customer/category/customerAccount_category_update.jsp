<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="customerAccountCategoryForm" class="form-horizontal" style="padding: 20px 20px;" action="${nvix}/nvixnt/nvixCustomerCategoryAction!saveOrUpdate.action">
	<input type="hidden" id="id" name="customerAccountCategory.id" value="${customerAccountCategory.id}" />
	<input type="hidden" id="parentCustomerAccountCategoryId" name="customerAccountCategory.parentCustomerAccountCategory.id" value="${customerAccountCategory.parentCustomerAccountCategory.id}"/>
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label">父分类:</label>
			<s:if test="itemCatalog.parentitemCatalog.id != null">
				<div class="col-md-4">
					<input id="name" value="${customerAccountCategory.parentCustomerAccountCategory.name}" data-prompt-position="topLeft" class="form-control" type="text" style="cursor: pointer;" readonly="readonly" />
				</div>
			</s:if>
			<s:else>
				<div class="col-md-4">
					<div class="row">
						<div class="col-sm-12">
							<div id="treeMenu" class="input-group">
								<input id="parentCustomerAccountCategoryName" name="customerAccountCategory.parentCustomerAccountCategory.name" type="text" onfocus="showICMenu(); return false;" value="${customerAccountCategory.parentCustomerAccountCategory.name}" type="text" style="cursor: pointer;" readonly="readonly" class="form-control" />
								<div class="input-group-btn">
									<button type="button" class="btn btn-default" onclick="$('#parentCustomerAccountCategoryId').val('');$('#parentCustomerAccountCategoryName').val('');">清空</button>
								</div>
								<div id="treeMenuContent" class="menuContent" style="display: none; position: absolute; border: 1px solid gray; background-color: #dfdfdf; z-index: 9999;">
									<ul id="parentCustomerAccountCategoryTree" class="ztree"></ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</s:else>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>编码:</label>
			<div class="col-md-4">
				<input id="code" name="customerAccountCategory.code" value="${customerAccountCategory.code}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>名称:</label>
			<div class="col-md-4">
				<input id="name" name="customerAccountCategory.name" value="${customerAccountCategory.name}" class="form-control validate[required]" type="text" />
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
/** 初始化分类下拉列表树 */
var showICMenu = initDropListTree("treeMenu","${nvix}/nvixnt/nvixCustomerCategoryAction!findTreeToJson.action","parentCustomerAccountCategoryId","parentCustomerAccountCategoryName","parentCustomerAccountCategoryTree","treeMenuContent");
</script>