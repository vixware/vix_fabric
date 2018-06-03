<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<fieldset>
	<div class="col-sm-12" style="text-align: left; padding: 5px 5px;">
		<div id="stockInDetailDiv" class="jarviswidget">
			<header>
				<h2>商品列表</h2>
			</header>
			<div>
				<div class="widget-body no-padding">
					<div style="margin: 5px;">
						<div class="form-group" style="margin: 0 0px;">
							<div class="col-md-5">
								<div id="treeMenu" class="input-group col-md-12">
									<input type="hidden" id="chooseProductCategoryId" name="productCategoryId" value="${productCategoryId}" /> <input id="chooseProductCategoryName" type="text" onfocus="showMenu(); return false;" value="${productCategoryName}" class="form-control" />
									<div class="input-group-btn">
										<button type="button" class="btn btn-default" onclick="$('#chooseProductCategoryId').val('');$('#chooseProductCategoryName').val('');chooseEcProductTable.ajax.reload();">清空</button>
									</div>
									<div id="menuContent" class="menuContent" style="display: none; position: absolute; border: 1px solid gray; background-color: #dfdfdf; z-index: 9999;">
										<ul id="chooseProductCategoryZtree" class="ztree"></ul>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<input type="text" value="" placeholder="商品名称" class="form-control" id="searchProductName">
							</div>
							<button onclick="chooseEcProductTable.ajax.reload();" type="button" class="btn btn-info">
								<i class="glyphicon glyphicon-search"></i> 检索
							</button>
						</div>
					</div>
					<table id="chooseEcProduct" class="table table-striped table-bordered table-hover" width="100%">
						<thead>
							<tr>
								<th width="15%">编码</th>
								<th width="60%">名称</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
		<form id="stockRecordLinesForm" class="form-horizontal">
			<div class="jarviswidget">
				<header>
					<h2>拆装信息</h2>
				</header>
				<div>
					<div class="widget-body">
						<fieldset>
							<input type="hidden" id="itemId" name="itemId" value="" /> <input type="hidden" id="parentItemId" name="parentItemId" value="" />
							<div class="form-group">
								<label class="col-md-2 control-label">名称:</label>
								<div class="col-md-4">
									<input id="itemname" name="" value="" data-prompt-position="topLeft" class="form-control" type="text" />
								</div>
								<label class="col-md-2 control-label"><span class="text-danger">*</span>数量:</label>
								<div class="col-md-4">
									<input id="num" name="num" value="" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
								</div>
							</div>
						</fieldset>
						<div class="form-actions">
							<div class="row">
								<div class="col-md-12">
									<button type="button" class="btn btn-success" onclick="bingItem();">
										<i class="fa fa-save"></i> &nbsp; 保存
									</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
</fieldset>
<script type="text/javascript">
	var chooseEcProductColumns = [ {
	"name" : "code",
	"data" : function(data) {
		return data.code;
	}
	}, {
	"name" : "name",
	"data" : function(data) {
		return data.name;
	}
	} ];

	var chooseEcProductTable = initDataTable("chooseEcProduct", "${nvix}/nvixnt/vixntProductDisassemblyAction!goChooseItemListContent.action", chooseEcProductColumns, function(page, pageSize, orderField, orderBy) {
		var productCategoryId = $("#chooseProductCategoryId").val();
		var searchItemName = $("#searchProductName").val();
		searchItemName = Url.encode(searchItemName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"name" : searchItemName,
		"productCategoryId" : productCategoryId
		};
	}, 5, '1', '0');

	$('#chooseEcProduct tbody').on('click', 'tr', function() {
		var data = chooseEcProductTable.row(".selected").data();
		if (data != undefined) {
			$("#itemId").val(data.id);
			$("#itemname").val(data.name);
		} else {
			clearStockInDetailForm();
		}
	});

	/** 初始化分类下拉列表树 */
	var showMenu = initDropListTree("treeMenu", "${nvix}/nvixnt/vixntStoreInboundWarehouseAction!findTreeToJson.action", "chooseProductCategoryId", "chooseProductCategoryName", "chooseProductCategoryZtree", "menuContent");

	function bingItem() {
		$("#parentItemId").val($('#id').val());
		if ($('#stockRecordLinesForm').validationEngine('validate')) {
			/** 打开遮盖层 */
			var loadIndex = layer.load(2);
			$("#stockRecordLinesForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/vixntProductDisassemblyAction!bingItem.action",
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(result) {
				/** 关闭遮盖层 */
				layer.close(loadIndex);
				showMessage(result, 'success');
				stockRecordLinesTable.ajax.reload();
				clearStockInDetailForm();
			}
			});
		}
	};
	function clearStockInDetailForm() {
		$("#itemId").val("");
		$("#itemname").val("");
		$("#num").val("");
	};
</script>