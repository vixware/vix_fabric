<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<fieldset>
	<div class="col-sm-8" style="text-align: left; padding: 5px 5px;">
		<div id="stockInDetailDiv" class="jarviswidget">
			<header>
				<span class="widget-icon"> <i class="fa fa-table"></i>
				</span>
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
										<button type="button" class="btn btn-default" onclick="$('#chooseProductCategoryId').val('');$('#chooseProductCategoryName').val('');chooseItemTable.ajax.reload();">清空</button>
									</div>
									<div id="menuContent" class="menuContent" style="display: none; position: absolute; border: 1px solid gray; background-color: #dfdfdf; z-index: 9999;">
										<ul id="chooseProductCategoryZtree" class="ztree"></ul>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<input type="text" value="" placeholder="商品名称" class="form-control" id="searchProductName">
							</div>
							<button onclick="chooseItemTable.ajax.reload();" type="button" class="btn btn-info">
								<i class="glyphicon glyphicon-search"></i> 检索
							</button>
						</div>
					</div>
					<table id="chooseEcProduct" class="table table-striped table-bordered table-hover" width="100%">
						<thead>
							<tr>
								<th width="15%">编码</th>
								<th width="70%">名称</th>
								<th width="15%">价格</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div class="col-sm-4" style="text-align: left; padding: 0px 0px;">
		<form id="saleOrderItemForm" class="form-horizontal" style="padding: 5px 5px;" action="${nvix}/nvixnt/vixntSalesOrderAction!saveOrUpdateSaleOrderItem.action">
			<div class="jarviswidget">
				<header>
					<h2>要货单明细</h2>
				</header>
				<div>
					<div class="widget-body">
						<fieldset style="height: 80%;">
							<input type="hidden" id="saleOrderItemId" name="saleOrderItem.id" value="${saleOrderItem.id }" /> <input type="hidden" id="saleOrderItemSalesOrderId" name="id" value="${saleOrderItem.salesOrder.id }" />
							<div class="form-group">
								<label class="col-md-4 control-label"><span class="text-danger">*</span>商品编码:</label>
								<div class="col-md-8">
									<input id="itemCode" name="saleOrderItem.itemCode" value="${saleOrderItem.itemCode}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-4 control-label"><span class="text-danger">*</span>商品名称:</label>
								<div class="col-md-8">
									<input id="title" name="saleOrderItem.title" value="${saleOrderItem.title}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-4 control-label"><span class="text-danger">*</span>价格:</label>
								<div class="col-md-8">
									<div class="input-group">
										<span class="input-group-addon">￥</span> <input id="price" name="saleOrderItem.price" value="${saleOrderItem.price}" data-prompt-position="topLeft" class="form-control validate[required,custom[number]]" readonly="readonly" type="text" />
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-4 control-label"><span class="text-danger">*</span>数量:</label>
								<div class="col-md-8">
									<s:if test="null == saleOrderItem">
										<input id="amount" name="saleOrderItem.amount" value="1" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
									</s:if>
									<s:else>
										<input id="amount" name="saleOrderItem.amount" value="${saleOrderItem.amount}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
									</s:else>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-4 control-label">单位:</label>
								<div class="col-md-8">
									<input id="unit" name="saleOrderItem.unit" value="${saleOrderItem.unit}" class="form-control" type="text" />
								</div>
							</div>
						</fieldset>
						<div class="form-actions">
							<div class="row">
								<div class="col-md-12">
									<button type="button" class="btn btn-success" onclick="saveOrUpdateSaleOrderItem();">
										<s:if test="saleOrderItem.id != ''">
											<i class="fa fa-edit"></i> &nbsp; 修改
											</s:if>
										<s:else>
											<i class="fa fa-save"></i> &nbsp; 保存
											</s:else>
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
	}, {
	"name" : "price",
	"data" : function(data) {
		return data.price;
	}
	} ];

	var chooseItemTable = initDataTable("chooseEcProduct", "${nvix}/nvixnt/vixntInboundWarehouseAction!goChooseListContent.action", chooseEcProductColumns, function(page, pageSize, orderField, orderBy) {
		var productCategoryId = $("#chooseProductCategoryId").val();
		var name = $("#searchProductName").val();
		name = Url.encode(name);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"name" : name,
		"productCategoryId" : productCategoryId
		};
	}, 10, '1', '0');

	$('#chooseEcProduct tbody').on('click', 'tr', function() {
		var data = chooseItemTable.row(".selected").data();
		if (data != undefined) {
			$("#itemCode").val(data.code);
			$("#title").val(data.name);
			$("#price").val(data.price);
			if (null != data.measureUnit) {
				$("#unit").val(data.measureUnit.name);
			}
			$("#amount").val("1");
		} else {
			clearSaleOrderItemForm();
		}
	});

	/** 初始化分类下拉列表树 */
	var showMenu = initDropListTree("treeMenu", "${nvix}/nvixnt/vixntInboundWarehouseAction!findTreeToJson.action", "chooseProductCategoryId", "chooseProductCategoryName", "chooseProductCategoryZtree", "menuContent");

	function saveOrUpdateSaleOrderItem() {
		if ($('#saleOrderItemForm').validationEngine('validate')) {
			/** 打开遮盖层 */
			var loadIndex = layer.load(2);
			$("#saleOrderItemSalesOrderId").val($("#salesOrderId").val());
			$("#saleOrderItemForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/vixntSalesOrderAction!saveOrUpdateSaleOrderItem.action",
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(result) {
				/** 关闭遮盖层 */
				layer.close(loadIndex);
				saleOrderItemTable.ajax.reload();
				showMessage(result, 'success');
			}
			});
		}
	};

	/** 清空输入项 */
	function clearSaleOrderItemForm() {
		$("#itemCode").val("");
		$("#title").val("");
		$("#price").val("");
		$("#unit").val("");
		$("#amount").val("");
	};
</script>