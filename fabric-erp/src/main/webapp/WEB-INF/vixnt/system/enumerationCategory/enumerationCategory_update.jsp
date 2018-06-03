<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="enumerationCategoryForm" class="form-horizontal" style="padding: 20px 20px;" action="${nvix}/nvixnt/system/nvixEnumerationCategoryAction!saveOrUpdate.action">
	<input type="hidden" id="id" name="enumerationCategory.id" value="${enumerationCategory.id}" />
	<input type="hidden" id="parentEnumCategoryId" name="enumerationCategory.parentEnumerationCategory.id" value="${parentId}"/>
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label">父分类:</label>
			<s:if test="enumerationCategory.parentEnumerationCategory.id != null">
				<div class="col-md-8">
					<input id="name" value="${enumerationCategory.parentEnumerationCategory.name}" data-prompt-position="topLeft" class="form-control" type="text" readonly="readonly" />
				</div>
			</s:if>
			<s:else>
				<div class="col-md-8">
					<div class="row">
						<div class="col-sm-12">
							<div id="treeMenu" class="input-group">
								<input id="parentEnumCategory" type="text" onfocus="showICMenu(); return false;" value="${enumerationCategory.parentEnumerationCategory.name}" type="text" class="form-control" />
								<div class="input-group-btn">
									<button type="button" class="btn btn-default" onclick="$('#parentEnumCategoryId').val('');$('#parentEnumCategory').val('');">清空</button>
								</div>
								<div id="treeMenuContent" class="menuContent" style="display: none; position: absolute; border: 1px solid gray; background-color: #dfdfdf; z-index: 9999;">
									<ul id="parentEnumCategoryTree" class="ztree"></ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</s:else>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>名称:</label>
			<div class="col-md-8">
				<input id="name" name="enumerationCategory.name" value="${enumerationCategory.name}" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">备注:</label>
			<div class="col-md-8">
				<textarea rows="3" id="memo" name="enumerationCategory.memo" value="${enumerationCategory.memo}" class="form-control">${enumerationCategory.memo}</textarea>
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
/** 初始化分类下拉列表树 */
var showICMenu = initDropListTree("treeMenu","${nvix}/nvixnt/system/nvixEnumerationCategoryAction!findTreeToJson.action","parentEnumCategoryId","parentEnumCategory","parentEnumCategoryTree","treeMenuContent");
</script>