<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="addressInfoForm" class="form-horizontal" style="padding:40px 40px;" action="${nvix}/nvixnt/system/nvixntAddressInfoAction!saveOrUpdate.action">
	<input type="hidden" id="id" name="addressInfo.id" value="${addressInfo.id }" />
	<input type="hidden" id="parentAddressInfoId" name="addressInfo.parentAddressInfo.id" value="${addressInfo.parentAddressInfo.id}"/>
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label">上级地址:</label>
			<div class="col-md-4">
				<div class="row">
					<div class="col-sm-12">
						<div id="treeMenu" class="input-group">
							<input id="parentAddressInfoName" type="text" onfocus="showMenu(); return false;" readonly="readonly" value="${addressInfo.parentAddressInfo.name}" class="form-control"/>
							<div class="input-group-btn">
								<button type="button" class="btn btn-default" onclick="$('#parentAddressInfoId').val('');$('#parentAddressInfoName').val('');">
									清空
								</button>
							</div>
							<div id="menuContent" class="menuContent" style="display:none; position: absolute;border:1px solid gray;background-color: #dfdfdf;z-index: 9999;">
								<ul id="parentAddressInfoTrees" class="ztree"></ul>
							</div>
						</div>
					</div>
				</div>
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>编码:</label>
			<div class="col-md-4">
				<input id="code" name="addressInfo.code" value="${addressInfo.code}" type="text" data-prompt-position="topLeft" class="form-control validate[required]" <c:if test="${addressInfo.id != null && addressInfo.id != '' }">readonly="readonly"</c:if> />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>名称:</label>
			<div class="col-md-4">
				<input id="name" name="addressInfo.name" value="${addressInfo.name}" type="text" data-prompt-position="topLeft" class="form-control validate[required]" />
			</div>
			<label class="col-md-2 control-label">首字母:</label>
			<div class="col-md-4">
				<input id="firstLetter" name="addressInfo.firstLetter" value="${addressInfo.firstLetter}" type="text" readonly="readonly" class="form-control" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">顺序:</label>
			<div class="col-md-4">
				<input id="orderBy" name="addressInfo.orderBy" value="${addressInfo.orderBy}" type="text" class="form-control" />
			</div>
			<label class="col-md-2 control-label">备注:</label>
			<div class="col-md-4">
				<input id="memo" name="addressInfo.memo" value="${addressInfo.memo}" type="text" class="form-control" />
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	$("#addressInfoForm").validationEngine();

	/** 初始化分类下拉列表树 */
	var showMenu = initDropListTree("treeMenu","${nvix}/nvixnt/system/nvixntAddressInfoAction!findTreeToJson.action","parentAddressInfoId","parentAddressInfoName","parentAddressInfoTrees","menuContent");
</script>