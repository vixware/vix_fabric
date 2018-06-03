<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-table fa-fw "></i> 生产管理 <span>> BOM管理 </span><span>> BOM结构</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-primary" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/produce/nvixBomStructAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget" id="" data-widget-colorbutton="false" data-widget-editbutton="false">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>BOM结构</h2>
		</header>
		<div class="widget-body">
			<form id="bomStructForm" class="form-horizontal">
				<input type="hidden" id="id" name="bomStruct.id" value="${bomStruct.id}" /> 
				<fieldset>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>物料:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input type="hidden" id="itemId" name="bomStruct.item.id" value="${bomStruct.item.id}" /> 
								<input id="itemName" name="bomStruct.item.name" value="${bomStruct.item.name}" onfocus="chooseItem();" data-prompt-position="topLeft" class="form-control validate[required]" type="text" style="cursor: pointer;" readonly="readonly" /> 
								<span class="input-group-addon" style="cursor: pointer;" onclick="chooseItem();">选择</span>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>规格型号:</label>
						<div class="col-md-3">
							<input id="specification" name="bomStruct.specification" value="${bomStruct.specification}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>bom类型:</label>
						<div class="col-md-3">
							<input id="bomType" name="bomStruct.bomType" value="${bomStruct.bomType}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>bom类别:</label>
						<div class="col-md-3">
							<input id="bomClass" name="bomStruct.bomClass" value="${bomStruct.bomClass}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>配置Bom名称:</label>
						<div class="col-md-3">
							<input id="configItemBomName" name="bomStruct.configItemBomName" value="${bomStruct.configItemBomName}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>母件损耗率:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="parentPartAttritionRate" name="bomStruct.parentPartAttritionRate" value="${bomStruct.parentPartAttritionRate}" data-prompt-position="topLeft" class="form-control validate[required,custom[number],min[1],max[100]]" type="text" />
								<span class="input-group-addon">(1-100)%</span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>版本:</label>
						<div class="col-md-3">
							<input id="version" name="bomStruct.version" value="${bomStruct.version}" data-prompt-position="topLeft" class="form-control validate[required,custom[integer],min[1]]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>版本日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="versionDate" name="bomStruct.versionDate" value="${bomStruct.versionDateStr}" data-prompt-position="topLeft" class="form-control validate[required,custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'versionDate'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>版本描述:</label>
						<div class="col-md-3">
							<input id="versionDescription" name="bomStruct.versionDescription" value="${bomStruct.versionDescription}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>默认基础数量:</label>
						<div class="col-md-3">
							<input id="defaultBaseAmount" name="bomStruct.defaultBaseAmount" value="${bomStruct.defaultBaseAmount}" data-prompt-position="topLeft" class="form-control validate[required,custom[number],min[1]]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><!-- <span class="text-danger">*</span> -->产品配置限定模型:</label>
						<div class="col-md-3">
							<input id="productConfigureModel" name="bomStruct.productConfigureModel" value="${bomStruct.productConfigureModel}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>创建日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="createTime" name="bomStruct.createTime" value="${bomStruct.createTimeStr}" data-prompt-position="topLeft" class="form-control validate[custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> 
								<span class="input-group-addon" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'createTime'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">备注:</label>
						<div class="col-md-8">
							<textarea id="memo" name="bomStruct.memo" class="form-control" rows="4">${bomStruct.memo}</textarea>
						</div>
					</div>
				</fieldset>
				<div class="form-actions">
					<div class="row">
						<div class="col-md-12">
							<button type="button" class="btn btn-primary" onclick="saveOrUpdate('');">
								<i class="fa fa-save"></i> 保存
							</button>
							<button type="button" class="btn btn-default" onclick="loadContent('', '${nvix}/nvixnt/produce/nvixBomStructAction!goList.action');">
								<i class="fa fa-rotate-left"></i> 返回
							</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
	$("#bomStructForm").validationEngine();

	function saveOrUpdate(id) {
		//表单验证
		if ($("#bomStructForm").validationEngine('validate')) {
			$("#bomStructForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/produce/nvixBomStructAction!saveOrUpdate.action?id=" + $("#id").val(),
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(result) {
				showMessage(result);
				loadContent('', '${nvix}/nvixnt/produce/nvixBomStructAction!goList.action');
			}
			});
		}
	}
	
	function chooseItem() {
		chooseListData('${nvix}/nvixnt/produce/nvixBomStructAction!goChooseItem.action', 'single', '选择物料', 'item');
	}
</script>