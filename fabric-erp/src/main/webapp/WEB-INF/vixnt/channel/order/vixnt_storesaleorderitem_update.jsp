<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<fieldset>
	<input id="channelId" type="hidden" value="${channelId}">
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
								<input type="text" value="" placeholder="商品名称" class="form-control" id="productname">
							</div>
							<button onclick="chooseItemTable.ajax.reload();" type="button" class="btn btn-info">
								<i class="glyphicon glyphicon-search"></i>
							</button>
							<button onclick="$('#productname').val('');chooseItemTable.ajax.reload();" type="button" class="btn btn-default">
								<i class="glyphicon glyphicon-repeat"></i>
							</button>
						</div>
					</div>
					<table id="chooseEcProduct" class="table table-striped table-bordered table-hover" width="100%">
						<thead>
							<tr>
								<th>编码</th>
								<th>名称</th>
								<th>价格</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div class="col-sm-4" style="text-align: left; padding: 0px 0px;">
		<form id="saleOrderItemForm" class="form-horizontal" style="padding: 5px 5px;" action="${nvix}/nvixnt/vixntStoreRequireGoodsAction!saveOrUpdateSaleOrderItem.action">
			<div class="jarviswidget">
				<header>
					<h2>订单明细</h2>
				</header>
				<div>
					<div class="widget-body">
						<fieldset style="height: 375px;">
							<input type="hidden" id="saleOrderItemId" name="requireGoodsOrderItem.id" value="${requireGoodsOrderItem.id }" /> 
							<input type="hidden" id="itemId" value="" /> 
							<input type="hidden" id="saleOrderItemSalesOrderId" name="id" value="${requireGoodsOrderItem.requireGoodsOrder.id }" />
							<div class="form-group">
								<label class="col-md-4 control-label"><span class="text-danger">*</span>商品编码:</label>
								<div class="col-md-8">
									<input id="itemCode" name="requireGoodsOrderItem.itemCode" value="${requireGoodsOrderItem.itemCode}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-4 control-label"><span class="text-danger">*</span>商品名称:</label>
								<div class="col-md-8">
									<input id="title" name="requireGoodsOrderItem.title" value="${requireGoodsOrderItem.title}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-4 control-label"><span class="text-danger">*</span>价格:</label>
								<div class="col-md-8">
									<div class="input-group">
										<span class="input-group-addon">￥</span> 
										<input id="price" name="requireGoodsOrderItem.price" value="${requireGoodsOrderItem.price}" onfocus="goFixedPrice();" data-prompt-position="topLeft" class="form-control validate[required,custom[number]]" readonly="readonly" type="text" />
										<span class="input-group-addon" style="cursor:pointer;" onclick="goFixedPrice();">定价</span>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-4 control-label"><span class="text-danger">*</span>数量:</label>
								<div class="col-md-8">
									<s:if test="null == requireGoodsOrderItem">
										<input id="amount" name="requireGoodsOrderItem.amount" value="1" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
									</s:if>
									<s:else>
										<input id="amount" name="requireGoodsOrderItem.amount" value="${requireGoodsOrderItem.amount}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
									</s:else>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-4 control-label">单位:</label>
								<div class="col-md-8">
									<input id="unit" name="requireGoodsOrderItem.unit" value="${requireGoodsOrderItem.unit}" class="form-control" type="text" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-4 control-label">规格型号:</label>
								<div class="col-md-8">
									<input id="specification" name="requireGoodsOrderItem.specification" value="${requireGoodsOrderItem.specification}" class="form-control" type="text" />
								</div>
							</div>
						</fieldset>
						<div class="form-actions">
							<div class="row">
								<div class="col-md-12">
									<button type="button" class="btn btn-success" onclick="saveOrUpdateSaleOrderItem();">
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
	}, {
	"name" : "purchasePrice",
	"data" : function(data) {
		return data.price;
	}
	} ];

	var chooseItemTable = initDataTable("chooseEcProduct", "${nvix}/nvixnt/channel/vixntChannelOrderAction!goChooseStoreItemList.action", chooseEcProductColumns, function(page, pageSize, orderField, orderBy) {
		var productCategoryId = $("#chooseProductCategoryId").val();
		var channelId = $("#channelId").val();
		var itemname = $("#productname").val();
		itemname = Url.encode(itemname);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"itemname" : itemname,
		"channelId" : channelId,
		"productCategoryId" : productCategoryId
		};
	}, 10, '1', '0');

	$('#chooseEcProduct tbody').on('click', 'tr', function() {
		var data = chooseItemTable.row(".selected").data();
		if (data != undefined) {
			$("#itemId").val(data.id);
			$("#itemCode").val(data.code);
			$("#title").val(data.name);
			//$("#price").val(data.purchasePrice);
			$("#price").val(data.price);
			$("#specification").val(data.specification);
			$("#unit").val(data.saleUnit);
			$("#amount").val("1");
		} else {
			clearSaleOrderItemForm();
		}
	});

	/** 初始化分类下拉列表树 */
	var showMenu = initDropListTree("treeMenu", "${nvix}/nvixnt/channel/vixntChannelOrderAction!findTreeToJson.action", "chooseProductCategoryId", "chooseProductCategoryName", "chooseProductCategoryZtree", "menuContent");

	function saveOrUpdateSaleOrderItem() {
		if ($('#saleOrderItemForm').validationEngine('validate')) {
			/** 打开遮盖层 */
			var loadIndex = layer.load(2);
			$("#saleOrderItemSalesOrderId").val($("#requireGoodsOrderId").val());
			$("#saleOrderItemForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/channel/vixntChannelOrderAction!saveOrUpdateSaleOrderItem.action",
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(result) {
				/** 关闭遮盖层 */
				layer.close(loadIndex);
				saleOrderItemTable.ajax.reload();
				clearSaleOrderItemForm();
				showMessage(result, 'success');
				$.ajax({
				url : '${nvix}/nvixnt/channel/vixntChannelOrderAction!getStockRecordTotal.action?id=' + $("#saleOrderItemSalesOrderId").val(),
				cache : false,
				success : function(json) {
					$("#requireGoodsOrderAmount").val(json);
				}
				});
			}
			});
		}
	};

	/** 清空输入项 */
	function clearSaleOrderItemForm() {
		$("#saleOrderItemId").val("");
		$("#itemId").val("");
		$("#itemCode").val("");
		$("#title").val("");
		$("#price").val("");
		$("#unit").val("");
		$("#amount").val("");
	};
	function goChooseSupplier() {
		chooseListData('${nvix}/nvixnt/purchase/vixntPurchaseOrderAction!goChooseSupplier.action', 'single', '选择供应商', 'supplier');
	};
	
	// 定价
	function goFixedPrice() {
		var itemId = $("#itemId").val();
		var amount = $("#amount").val();
		var channelId = $("#channelId").val();
		if(!itemId){
			layer.alert("请选择商品");
			return;
		}else if(!amount){
			layer.alert("请填写商品数量");
			return;
		}else{
			chooseListData('${nvix}/nvixnt/channel/vixntChannelOrderItemPriceAction!goFixedPrice.action?id='+itemId+'&count='+amount+"&channelId="+channelId, 'single', '选择定价', 'fixedPrice');
		}
	};
</script>