<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="personnelCategoryForm" class="form-horizontal" style="padding: 35px 15px;" action="${nvix}/nvixnt/nvixntPersonnelCategoryAction!saveOrUpdate.action">
	<input type="hidden" id="id" name="personnelCategory.id" value="${personnelCategory.id}" />
<input type="hidden" id="parentPersonnelCategoryId" name="personnelCategory.parentPersonnelCategory.id" value="${personnelCategory.parentPersonnelCategory.id}" /> 
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label">父分类:</label>
			<s:if test="personnelCategory.parentpersonnelCategory.id != null">
				<div class="col-md-4">
					<input id="name" value="${personnelCategory.parentPersonnelCategory.name}" data-prompt-position="topLeft" class="form-control" type="text" readonly="readonly" />
				</div>
			</s:if>
			<s:else>
				<div class="col-md-4">
					<div class="row">
						<div class="col-sm-12">
							<div id="treeMenu" class="input-group">
								<input id="parentPersonnelCategoryName" type="text" onfocus="showICMenu(); return false;" value="${personnelCategory.parentPersonnelCategory.name}" type="text" readonly="readonly" class="form-control" />
								<div class="input-group-btn">
									<button type="button" class="btn btn-default" onclick="$('#parentPersonnelCategoryId').val('');$('#parentPersonnelCategoryName').val('');">清空</button>
								</div>
								<div id="treeMenuContent" class="menuContent" style="display: none; position: absolute; border: 1px solid gray; background-color: #dfdfdf; z-index: 9999;">
									<ul id="parentPersonnelCategoryTree" class="ztree"></ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</s:else>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 编码:</label>
			<div class="col-md-4">
				<input id="code" name="personnelCategory.code" value="${personnelCategory.code}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 名称:</label>
			<div class="col-md-4">
				<input id="name" name="personnelCategory.name" value="${personnelCategory.name}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">备注:</label>
			<div class="col-md-10">
				<textarea name="personnelCategory.memo" class="form-control">${personnelCategory.memo} </textarea>
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	$("#personnelCategoryForm").validationEngine();
	var showICMenu = initDropListTree("treeMenu", "${nvix}/nvixnt/nvixntPersonnelCategoryAction!findTreeToJson.action", "parentPersonnelCategoryId", "parentPersonnelCategoryName", "parentPersonnelCategoryTree", "treeMenuContent");
</script>