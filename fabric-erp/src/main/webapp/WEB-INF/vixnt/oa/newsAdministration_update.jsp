<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" media="screen" href="${nvix}/vixntcommon/base/css/personal.css"></link>
<form id="trendsForm" class="form-horizontal" style="padding: 20px 15px;" action="${nvix}/nvixnt/newsAdministrationAction!saveOrUpdate.action">
	<input type="hidden" id="id" name="trends.id" value="${trends.id}" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 标题:</label>
			<div class="col-md-4">
				<input id="title" name="trends.title" value="${trends.title}" class="form-control required" type="text" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 发布人:</label>
			<div class="col-md-4">
				<input id="uploadPersonName" name="trends.uploadPersonName" value="${trends.uploadPersonName}" class="form-control required" type="text" />
			</div>
		</div>

		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 发布范围:</label>
			<div class="col-md-4">
				<div class="row">
					<div class="col-sm-12">
						<div id="treeMenu" class="input-group">
							<input id="orgId" type="hidden" value="" /> <input id="orgName" type="text" name="trends.bizOrgNames" onfocus="showMenu(); return false;" readonly="readonly" value="${trends.bizOrgNames}" class="form-control required" />
							<div class="input-group-btn">
								<button type="button" class="btn btn-default" onclick="$('#orgId').val('');$('#orgName').val('');">清空</button>
							</div>
							<div id="menuContent" class="menuContent" style="display: none; position: absolute; border: 1px solid gray; background-color: #dfdfdf; z-index: 9999;">
								<ul id="orgTrees" class="ztree"></ul>
							</div>
						</div>
					</div>
				</div>
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>发布时间:</label>
			<div class="col-md-4">
				<input id="createTimeStr" name="trends.createTimeStr" value="${trends.createTimeStr}" class="form-control required" type="text" onClick="WdatePicker()" />
			</div>
		</div>

		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span> 状态:</label>
			<div class="col-md-4">
				<label class="radio radio-inline"> <input type="radio" name="trends.isPublish" value="1" <c:if test="${trends.isPublish == 1}">checked="checked"</c:if> /> 发布
				</label> <label class="radio radio-inline"> <input type="radio" name="trends.isPublish" value="0" data-prompt-position="topLeft" class="validate[required]" <c:if test="${trends.isPublish == 0}">checked="checked"</c:if> /> 终止
				</label>
			</div>
		</div>

	</fieldset>
</form>
<script type="text/javascript">
	$("#workPlansForm").validate();
	var showMenu = initDropListTree("treeMenu","${nvix}/common/select/commonSelectOrgUnitAction!findOrgAndUnitTreeToJson.action?treeType=multi","orgId","orgName","orgTrees","menuContent");
	document.getElementsByName("trends.isPublish")[1].checked="checked";
</script>