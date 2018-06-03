<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<input type="hidden" id="chooseType" value="${chooseType}" />
<div class="jarviswidget" style="padding: 6px;">
	<header style="height: 1px; border-color: #ddd !important"></header>
	<div>
		<div class="widget-body no-padding">
			<div style="margin: 5px;">
				<div class="form-group" style="margin: 0 5px;">
					<div class="col-md-4">
						<div id="treeMenu" class="input-group col-md-12">
							<input type="hidden" id="chooseProductCategoryId" value="" /> 
							<input id="chooseProductCategoryName" type="text" onfocus="showMenu(); return false;" value="" placeholder="商品分类" class="form-control" />
							<div id="menuContent" class="menuContent" style="display: none; position: absolute; border: 1px solid gray; background-color: #dfdfdf; z-index: 9999;">
								<ul id="chooseProductCategoryZtree" class="ztree"></ul>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<input type="text" value="" placeholder="商品名称" class="form-control" id="searchName">
					</div>
					<!-- <div class="col-md-4">
						<input type="text" value="" placeholder="编码" class="form-control" id="itemCode">
					</div> -->
					<button onclick="chooseItemTable.ajax.reload();" type="button" class="btn btn-info">
						<i class="glyphicon glyphicon-search"></i> 检索
					</button>
					<button type="button" class="btn btn-default" onclick="$('#chooseProductCategoryId').val('');$('#chooseProductCategoryName').val('');$('#searchName').val('');chooseItemTable.ajax.reload();">
						<i class="glyphicon glyphicon-repeat"></i> 清空
					</button>
				</div>
			</div>
			<table id="chooseItemTableId" class="table table-striped table-bordered table-hover" width="100%">
				<thead>
					<tr>
						<th width="10%">
							<s:if test="chooseType == 'multi'">
								<div class="btn-group">
									<button data-toggle="dropdown" class="btn dropdown-toggle btn-xs btn-default" aria-expanded="false">
										选择 <i class="fa fa-caret-down"></i>
									</button>
									<ul class="dropdown-menu js-status-update pull-left">
										<li><a id="all" onclick="changeDataTableSelect('chooseCustomerAccountClipTableId','all');" href="javascript:void(0);">全选</a></li>
										<li><a id="reverse" onclick="changeDataTableSelect('chooseCustomerAccountClipTableId','reverse');" href="javascript:void(0);">反选</a></li>
										<li><a id="cancle" onclick="changeDataTableSelect('chooseCustomerAccountClipTableId','cancle');" href="javascript:void(0);">取消</a></li>
									</ul>
								</div>
							</s:if> 
							<s:else>选择</s:else></th>
						<th width="15%">商品名称</th>
						<th width="15%">商品编码</th>
						
					</tr>
				</thead>
			</table>
		</div>
	</div>
</div>
<script type="text/javascript">
	var chooseType = $("#chooseType").val();
	var chooseItemTableColumns = [ {
		"data" : function(data) {
			if (chooseType == 'single') {
				return "<input id='entityId' name='entityId' value='" + data.id + "' type='radio' onchange=\"radioChange('${entityName}','" + data.id + "','" + data.name + "');\"/>";
			}
			return "";
		}
	}, {
		"data" : function(data) {
			return data.name;
		}
	} , {
		"data" : function(data) {
			return data.code;
		}
	}];
	var selectType = chooseType == "multi" ? "2" : "1";
	var isGenerateIndex = chooseType == "multi" ? "1" : "0";
	var chooseItemTable = initDataTable("chooseItemTableId", "${nvix}/nvixnt/produce/nvixBomNodeAction!getChooseItemJson.action", chooseItemTableColumns, function(page, pageSize, orderField, orderBy) {
		var selectName = $("#searchName").val();
		var categoryId = $("#chooseProductCategoryId").val();
		selectName = Url.encode(selectName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"categoryId" : categoryId,
		"itemName" : selectName
		};
	}, 5, selectType, isGenerateIndex);
	
	/** 初始化分类下拉列表树 */
	var showMenu = initDropListTree("treeMenu", "${nvix}/nvixnt/mdm/nvixntMaterialAction!findTreeToJson.action", "chooseProductCategoryId", "chooseProductCategoryName", "chooseProductCategoryZtree", "menuContent");
</script>