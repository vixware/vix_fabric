<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<fieldset>
	<div class="col-sm-8" style="text-align: left; padding: 5px 5px;">
		<div id="" class="jarviswidget">
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
									<input type="hidden" id="outInvWarehouseId" name="" value="${outInvWarehouseId}" /> <input type="hidden" id="chooseProductCategoryId" name="productCategoryId" value="${productCategoryId}" /> <input id="chooseProductCategoryName" type="text" onfocus="showMenu(); return false;"
										value="${productCategoryName}" class="form-control" />
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
							<button onclick="chooseEcProductTable.ajax.reload();" type="button" class="btn btn-primary">
								<i class="glyphicon glyphicon-search"></i> 检索
							</button>
						</div>
					</div>
					<table id="chooseEcProduct" class="table table-striped table-bordered table-hover" width="100%">
					</table>
				</div>
			</div>
		</div>
	</div>
	<div class="col-sm-4" style="text-align: left; padding: 0px 0px;">
		<div class="jarviswidget">
			<header>
				<h2>调拨单明细</h2>
			</header>
			<div>
				<div class="widget-body">
					<form id="purchaseOrderLineItemForm" class="form-horizontal" style="padding: 5px 5px;" action="">
						<fieldset style="height: 375px;">
							<input type="hidden" id="wimTransvouchId" name="wimTransvouchitem.wimTransvouch.id" value="${wimTransvouchitem.wimTransvouch.id }" /> <input type="hidden" id="wimTransvouchitemId" name="wimTransvouchitem.id" value="${wimTransvouchitem.id }" /> <input type="hidden" id="avaquantity" name=""
								value="" /> <input type="hidden" id="afterquantity" name="" value="" />
							<div class="form-group">
								<label class="col-md-4 control-label"> <span class="text-danger">*</span>商品编码:
								</label>
								<div class="col-md-8">
									<input id="itemcode" name="wimTransvouchitem.itemcode" value="${wimTransvouchitem.itemcode}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-4 control-label"><span class="text-danger">*</span>商品名称:</label>
								<div class="col-md-8">
									<input id="itemname" name="wimTransvouchitem.itemname" value="${wimTransvouchitem.itemname}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-4 control-label"><span class="text-danger">*</span>价格:</label>
								<div class="col-md-8">
									<div class="input-group">
										<span class="input-group-addon">￥</span> <input id="salecost" name="wimTransvouchitem.salecost" value="${wimTransvouchitem.salecost}" data-prompt-position="topLeft" class="form-control validate[required,custom[number]]" readonly="readonly" type="text" />
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-4 control-label"><span class="text-danger">*</span>数量:</label>
								<div class="col-md-8">
									<input id="tvquantity" name="wimTransvouchitem.tvquantity" value="${wimTransvouchitem.tvquantity}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" onblur="checkTvquantity();" />
								</div>
							</div>
						</fieldset>
					</form>
					<div class="form-actions">
						<div class="row">
							<div class="col-md-12">
								<button type="button" class="btn btn-success" onclick="mergeStockInDetail();">
									<i class="fa fa-save"></i> &nbsp; 保存
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</fieldset>
<script type="text/javascript">
	var chooseEcProductColumns = [ {
	"title" : "编码",
	"width" : "15%",
	"name" : "code",
	"data" : function(data) {
		return data.itemcode;
	}
	}, {
	"title" : "商品名称",
	"width" : "70%",
	"name" : "name",
	"data" : function(data) {
		return data.itemname;
	}
	}, {
	"title" : "库存数量",
	"width" : "15%",
	"name" : "price",
	"data" : function(data) {
		return data.quantity;
	}
	} ];

	var chooseEcProductTable = initDataTable("chooseEcProduct", "${nvix}/nvixnt/vixntStandingBookAction!goSingleList.action", chooseEcProductColumns, function(page, pageSize, orderField, orderBy) {
		var productCategoryId = $("#chooseProductCategoryId").val();
		var outInvWarehouseId = $("#outInvWarehouseId").val();
		var name = $("#searchProductName").val();
		name = Url.encode(name);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"name" : name,
		"outInvWarehouseId" : outInvWarehouseId,
		"productCategoryId" : productCategoryId
		};
	}, 10, '1', '0');

	$('#chooseEcProduct tbody').on('click', 'tr', function() {
		var data = chooseEcProductTable.row(".selected").data();
		if (data != undefined) {
			$("#itemcode").val(data.itemcode);
			$("#itemname").val(data.itemname);
			$("#salecost").val(data.price);
			$("#tvquantity").val("1");
			$("#avaquantity").val(data.avaquantity);
		} else {
			clearStockInDetailForm();
		}
	});

	/** 初始化分类下拉列表树 */
	var showMenu = initDropListTree("treeMenu", "${nvix}/nvixnt/vixntInboundWarehouseAction!findTreeToJson.action", "chooseProductCategoryId", "chooseProductCategoryName", "chooseProductCategoryZtree", "menuContent");
	function checkTvquantity() {
		$("#afterquantity").val($("#avaquantity").val() - $("#tvquantity").val());
	}
	function mergeStockInDetail() {
		if ($('#purchaseOrderLineItemForm').validationEngine('validate')) {
			if ($("#afterquantity").val() < 0) {
				layer.alert("库存不足!");
				return false;
			} else {
				/** 打开遮盖层 */
				var loadIndex = layer.load(2);
				//入库单主体ID
				$("#purchaseOrderLineItemPurchaseOrderId").val($("#purchaseOrderId").val());
				$("#purchaseOrderLineItemForm").ajaxSubmit({
				type : "post",
				url : "${nvix}/nvixnt/vixntAllocateTransferAction!saveOrUpdateWimTransvouchitem.action",
				contentType : "application/x-www-form-urlencoded; charset=utf-8",
				success : function(result) {
					/** 关闭遮盖层 */
					layer.close(loadIndex);
					itemBrandTable.ajax.reload();
					showMessage(result, 'success');
					clearStockInDetailForm();
				}
				});
			}
		}
	};
	/** 清空输入项 */
	function clearStockInDetailForm() {
		$("#itemcode").val("");
		$("#itemname").val("");
		$("#salecost").val("");
		$("#tvquantity").val("");
		$("#avaquantity").val("");
	};
</script>