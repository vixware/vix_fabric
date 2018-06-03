<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<input type="hidden" id="chooseType" value="${chooseType}" />
<input type="hidden" id="parentId" value="${parentId}" />
<div class="jarviswidget" style="padding: 6px;">
	<header>
		<span class="widget-icon"> <i class="fa fa-file-text-o "></i>
		</span>
		<h2>表单列表</h2>
	</header>
	<div>
		<div class="widget-body no-padding">
			<div style="margin: 5px;">
				<div class="form-group" style="margin: 0 5px;">
					<div class="col-md-5">
						<div id="treeMenu" class="input-group col-md-12">
							<input type="hidden" id="chooseProductCategoryId" name="productCategoryId" value="${productCategoryId}" /> <input id="chooseProductCategoryName" type="text" onfocus="showMenu(); return false;" value="${productCategoryName}" class="form-control" />
							<div class="input-group-btn">
								<button type="button" class="btn btn-default" onclick="$('#chooseProductCategoryId').val('');$('#chooseProductCategoryName').val('');itemTable.ajax.reload();">清空</button>
							</div>
							<div id="menuContent" class="menuContent" style="display: none; position: absolute; border: 1px solid gray; background-color: #dfdfdf; z-index: 9999;">
								<ul id="chooseProductCategoryZtree" class="ztree"></ul>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<input type="text" value="" placeholder="名称" class="form-control" id="selectName">
					</div>
					<button onclick="itemTable.ajax.reload();" type="button" class="btn btn-info">
						<i class="glyphicon glyphicon-search"></i> 检索
					</button>
					<button type="button" class="btn btn-default" onclick="$('#selectName').val('');itemTable.ajax.reload();">
						<i class="glyphicon glyphicon-repeat"></i> 清空
					</button>
				</div>
			</div>
			<table id="itemtableid" class="table table-striped table-bordered table-hover" width="100%">
				<thead>
					<tr>
						<th width="10%"><s:if test="chooseType == 'multi'">
								<div class="btn-group">
									<button data-toggle="dropdown" class="btn dropdown-toggle btn-xs btn-default" aria-expanded="false">
										选择 <i class="fa fa-caret-down"></i>
									</button>
									<ul class="dropdown-menu js-status-update pull-left">
										<li><a id="all" onclick="changeDataTableSelect('itemtableid','all');" href="javascript:void(0);">全选</a></li>
										<li><a id="reverse" onclick="changeDataTableSelect('itemtableid','reverse');" href="javascript:void(0);">反选</a></li>
										<li><a id="cancle" onclick="changeDataTableSelect('itemtableid','cancle');" href="javascript:void(0);">取消</a></li>
									</ul>
								</div>
							</s:if> <s:else>选择</s:else></th>
						<th>编码</th>
						<th>业务表名称</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</div>
<script type="text/javascript">
	var chooseType = $("#chooseType").val();
	chooseMap.setColumnName("name");
	var itemColumns = [ {
	"orderable" : false,
	"data" : function(data) {
		if (chooseType == 'single') {
			return "<input id='entityId' name='entityId' value='" + data.id + "' type='radio' onchange=\"radioChange('${entityName}','" + data.id + "','" + data.name + "');\"/>";
		}
		return "";
	}
	}, {
	"name" : "code",
	"data" : function(data) {
		return data.businessFormCode;
	}
	}, {
	"name" : "name",
	"data" : function(data) {
		return data.businessFormName;
	}
	} ];
	var selectType = chooseType == "multi" ? "2" : "1";
	var isGenerateIndex = chooseType == "multi" ? "1" : "0";
	var itemTable = initDataTable("itemtableid", "${nvix}/nvixnt/vreport/nvixFormBindingAction!goBusinessFormTemplateList.action?parentId=" + $("#parentId").val(), itemColumns, function(page, pageSize, orderField, orderBy) {
		var selectName = $("#selectName").val();
		selectName = Url.encode(selectName);
		var categoryId = $("#chooseProductCategoryId").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"categoryId" : categoryId,
		"selectName" : selectName
		};
	}, 10, selectType, isGenerateIndex);
	/** 初始化分类下拉列表树 */
	var showMenu = initDropListTree("treeMenu", "${nvix}/nvixnt/vixntInboundWarehouseAction!findTreeToJson.action", "chooseProductCategoryId", "chooseProductCategoryName", "chooseProductCategoryZtree", "menuContent", null, function() {
		itemTable.ajax.reload();
	});
</script>