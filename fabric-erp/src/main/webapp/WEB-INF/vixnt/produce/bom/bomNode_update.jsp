<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="bomNodeForm" class="form-horizontal" style="padding: 20px 20px;" action="${nvix}/nvixnt/produce/nvixBomNodeAction!saveOrUpdate.action">
	<input type="hidden" id="id" name="bomNode.id" value="${bomNode.id}" /> 
	<input type="hidden" id="parentBomNodeId" name="bomNode.parentBomNode.id" value="${bomNode.parentBomNode.id}" /> 
	<input type="hidden" id="bomStructId" name="bomNode.bomStruct.id" value="${bomNode.bomStruct.id}" /> 
	<input type="hidden" id="itemId" name="bomNode.item.id" value="${bomNode.item.id}" /> 
	<fieldset>
		<div class="form-group">
			<s:if test="bomNode.id != null">
				<s:if test="bomNode.parentBomNode.id != null">
					<label class="col-md-2 control-label"><span class="text-danger">*</span>父Bom节点:</label>
					<div class="col-md-4">
						<input id="name" value="${bomNode.parentBomNode.subject}" data-prompt-position="topLeft" class="form-control" type="text" readonly="readonly" />
					</div>
				</s:if>
				<s:else>
					<label class="col-md-2 control-label"><span class="text-danger">*</span>Bom结构:</label>
					<div class="col-md-4">
						<input id="name" value="${bomNode.bomStruct.configItemBomName}" data-prompt-position="topLeft" class="form-control" type="text" readonly="readonly" />
					</div>
				</s:else>
			</s:if>
			<s:else>
				<c:if test="${treeType == 'S'}">
					<label class="col-md-2 control-label"><span class="text-danger">*</span>Bom结构:</label>
					<div class="col-md-4">
						<div class="input-group">
							<input id="bomStructName" name="bomNode.bomStruct.Name" value="${bomNode.bomStruct.configItemBomName}" onfocus="chooseBomStruct();" data-prompt-position="topLeft" class="form-control validate[required]" type="text" style="cursor: pointer;" readonly="readonly" /> 
							<span class="input-group-addon" style="cursor: pointer;" onclick="chooseBomStruct();">选择</span>
						</div>
					</div>
				</c:if>
				<c:if test="${treeType == 'N'}">
					<label class="col-md-2 control-label"><span class="text-danger">*</span>父Bom节点:</label>
					<div class="col-md-4">
						<div class="row">
							<div class="col-sm-12">
								<div id="treeMenu" class="input-group">
									<input id="parentBomNodeName" name="bomNode.parentBomNode.subject" type="text" onfocus="showICMenu(); return false;" value="${bomNode.parentBomNode.subject}" type="text" style="cursor: pointer;" readonly="readonly" class="form-control validate[required]" />
									<div class="input-group-btn">
										<button type="button" class="btn btn-default" onclick="$('#parentBomNodeId').val('');$('#parentBomNodeName').val('');">清空</button>
									</div>
									<div id="treeMenuContent" class="menuContent" style="display: none; position: absolute; border: 1px solid gray; background-color: #dfdfdf; z-index: 9999;">
										<ul id="parentBomNodeTree" class="ztree"></ul>
									</div>
								</div>
							</div>
						</div>
					</div>
				</c:if>
			</s:else>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>子件阶别:</label>
			<div class="col-md-4">
				<input id="level" name="bomNode.level" value="${bomNode.level}" data-prompt-position="topLeft" class="form-control validate[required,custom[integer],min[1]]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label">物料:</label>
			<div class="col-md-4">
				<div class="input-group">
					<input id="itemName" name="bomNode.item.name" value="${bomNode.item.name}" onfocus="chooseItem();" data-prompt-position="topLeft" class="form-control" type="text" style="cursor: pointer;" readonly="readonly" /> 
					<span class="input-group-addon" style="cursor: pointer;" onclick="chooseItem();">选择</span>
				</div>
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>主题:</label>
			<div class="col-md-4">
				<input id="subject" name="bomNode.subject" value="${bomNode.subject}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>配置节点类型:</label>
			<div class="col-md-4">
				<input id="nodeType1" name="bomNode.nodeType" value="${bomNode.nodeType}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>数量:</label>
			<div class="col-md-4">
				<input id="amount" name="bomNode.amount" value="${bomNode.amount}" data-prompt-position="topLeft" class="form-control validate[required,custom[number],min[1]]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>子件计量单位:</label>
			<div class="col-md-4">
				<input id="unit" name="bomNode.unit" value="${bomNode.unit}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>基本用量:</label>
			<div class="col-md-4">
				<input id="commonAmount" name="bomNode.commonAmount" value="${bomNode.commonAmount}" data-prompt-position="topLeft" class="form-control validate[required,custom[number],min[1]]" type="text" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"><span class="text-danger">*</span>基础数量:</label>
			<div class="col-md-4">
				<input id="baseAmount" name="bomNode.baseAmount" value="${bomNode.baseAmount}" data-prompt-position="topLeft" class="form-control validate[required,custom[number],min[1]]" type="text" />
			</div>
			<label class="col-md-2 control-label"><span class="text-danger">*</span>使用数量:</label>
			<div class="col-md-4">
				<input id="usedAmount" name="bomNode.usedAmount" value="${bomNode.usedAmount}" data-prompt-position="topLeft" class="form-control validate[required,custom[number],min[1]]" type="text" />
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	$("#bomNodeForm").validationEngine();
	/** 初始化分类下拉列表树 */
	var showICMenu = initDropListTree("treeMenu", "${nvix}/nvixnt/produce/nvixBomNodeAction!findTreeToJson.action", "parentBomNodeId", "parentBomNodeName", "parentBomNodeTree", "treeMenuContent");
	function chooseBomStruct() {
		chooseListData('${nvix}/nvixnt/produce/nvixBomStructAction!goChooseBomStruct.action', 'single', '选择BOM结构', 'bomStruct');
	}
	function chooseItem() {
		chooseListData('${nvix}/nvixnt/produce/nvixBomNodeAction!goChooseItem.action', 'single', '选择物料', 'item');
	}
</script>