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
					<div>
						<form role="search" class="navbar-form navbar-left">
							<div class="form-group">
								<div id="treeMenu" class="input-group col-md-12">
									<input type="hidden" id="chooseProductCategoryId" name="productCategoryId" value="${productCategoryId}" /> 
									<input id="chooseProductCategoryName" type="text" onfocus="showMenu(); return false;" value="${productCategoryName}" placeholder="商品分类" class="form-control" />
									<div id="menuContent" class="menuContent" style="display: none; position: absolute; border: 1px solid gray; background-color: #dfdfdf; z-index: 9999;">
										<ul id="chooseProductCategoryZtree" class="ztree"></ul>
									</div>
								</div>
							</div>
							<div class="form-group">
								<input type="text" value="" placeholder="商品名称" class="form-control" id="searchProductName">
							</div>
							<button onclick="chooseEcProductTable.ajax.reload();" type="button" class="btn btn-info">
								<i class="glyphicon glyphicon-search"></i> 检索
							</button>
							<button type="button" class="btn btn-default" onclick="$('#searchProductName').val('');$('#chooseProductCategoryId').val('');$('#chooseProductCategoryName').val('');chooseEcProductTable.ajax.reload();">
								<i class="glyphicon glyphicon-repeat"></i> 清空
							</button>
						</form>
					</div>
					<table id="chooseEcProduct" class="table table-striped table-bordered table-hover" width="100%">
						<thead>
							<tr>
								<th width="15%">编码</th>
								<th width="70%">名称</th>
								<th width="15%">采购价格</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div class="col-sm-4" style="text-align: left; padding: 0px 0px;">
		<form id="purchasePlanItemsForm" class="form-horizontal" style="padding: 5px 5px;" action="">
			<div class="jarviswidget">
				<header>
					<h2>采购计划明细</h2>
				</header>
				<div>
					<div class="widget-body">
						<fieldset style="height: 375px;">
							<input type="hidden" id="purchasePlanItemsId" name="purchasePlanItems.id" value="${purchasePlanItems.id }" /> 
							<input type="hidden" id="purchasePlanItemsPurchasePlanId" name="purchasePlanItems.purchasePlan.id" value="${purchasePlanItems.purchasePlan.id }" />
							<div class="form-group">
								<label class="col-md-4 control-label"> <span class="text-danger">*</span>商品编码:
								</label>
								<div class="col-md-8">
									<input id="itemCode" name="purchasePlanItems.itemCode" value="${purchasePlanItems.itemCode}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-4 control-label"><span class="text-danger">*</span>商品名称:</label>
								<div class="col-md-8">
									<input id="itemName" name="purchasePlanItems.itemName" value="${purchasePlanItems.itemName}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-4 control-label"><span class="text-danger">*</span>规格型号:</label>
								<div class="col-md-8">
									<input id="specification" name="purchasePlanItems.specification" value="${purchasePlanItems.specification}" data-prompt-position="topLeft" class="form-control validate" type="text" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-4 control-label"><span class="text-danger">*</span>价格:</label>
								<div class="col-md-8">
									<input id="unitcost" name="purchasePlanItems.unitcost" value="${purchasePlanItems.unitcost}" data-prompt-position="topLeft" class="form-control validate[required,custom[number]]" type="text" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-4 control-label"><span class="text-danger">*</span>单位:</label>
								<div class="col-md-8">
									<select id="measureUnitId" name="purchasePlanItems.measureUnit.id" class="form-control validate[required]">
										<c:forEach items="${measureUnitList}" var="entity">
											<option value="${entity.id}" <c:if test="${entity.id == purchasePlanItems.measureUnit.id}">selected="selected"</c:if>>${entity.name}</option>
										</c:forEach>
									</select>	
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-4 control-label"><span class="text-danger">*</span>数量:</label>
								<div class="col-md-8">
									<s:if test="null == purchasePlanItems">
										<input id="amount" name="purchasePlanItems.amount" value="1" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
									</s:if>
									<s:else>
										<input id="amount" name="purchasePlanItems.amount" value="${purchasePlanItems.amount}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
									</s:else>
								</div>
							</div>
						</fieldset>
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
		if(data.purchasePrice){
			return '￥' + data.purchasePrice;
		}
		return '';
	}
	} ];

	var chooseEcProductTable = initDataTable("chooseEcProduct", "${nvix}/nvixnt/purchase/vixntPurchasePlanAction!goDetailSingleList.action", chooseEcProductColumns, function(page, pageSize, orderField, orderBy) {
		var productCategoryId = $("#chooseProductCategoryId").val();
		var supplierId = $("#supplierId").val();
		var name = $("#searchProductName").val();
		name = Url.encode(name);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"selectName" : name,
		"categoryId" : productCategoryId,
		"supplierId" : supplierId
		};
	}, 10, '1', '0');

	$('#chooseEcProduct tbody').on('click', 'tr', function() {
		var data = chooseEcProductTable.row(".selected").data();
		if (data != undefined) {
			$("#itemId").val(data.id);
			$("#itemCode").val(data.code);
			$("#itemName").val(data.name);
			$("#measureUnitId").val(data.measureUnit.id);
			$("#skuCode").val(data.skuCode);
			$("#unitcost").val(data.price);
			$("#specification").val(data.specification);
			$("#amount").val("1");
		} else {
			clearStockInDetailForm();
		}
	});

	/** 初始化分类下拉列表树 */
	var showMenu = initDropListTree("treeMenu", "${nvix}/nvixnt/vixntInboundWarehouseAction!findTreeToJson.action", "chooseProductCategoryId", "chooseProductCategoryName", "chooseProductCategoryZtree", "menuContent");

	function mergeStockInDetail() {
		if ($('#purchasePlanItemsForm').validationEngine('validate')) {
			/** 打开遮盖层 */
			var loadIndex = layer.load(2);
			//入库单主体ID
			$("#purchasePlanItemsPurchasePlanId").val($("#purchasePlanId").val());
			$("#purchasePlanItemsForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/purchase/vixntPurchasePlanAction!saveOrUpdatePurchasePlanItems.action",
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(result) {
				/** 关闭遮盖层 */
				layer.close(loadIndex);
				purchasePlanItemsTable.ajax.reload();
				showMessage(result, 'success');
				clearStockInDetailForm();
				$.ajax({
					url : '${nvix}/nvixnt/purchase/vixntPurchasePlanAction!changePurchasePlanPrice.action?id=' + $("#purchasePlanId").val(),
					cache : false,
					success : function(result) {
						var r = result.split(":");
						$("#amount").val(r[1]);
						$("#price").val(r[0]);
					}
				});
			}
			});
		}
	};

	/** 清空输入项 */
	function clearStockInDetailForm() {
		$("#itemId").val("");
		$("#itemCode").val("");
		$("#itemName").val("");
		/* $("#skuCode").val(""); */
		$("#price").val("");
		$("#amount").val("");
		$("#unit").val("");
		/* $("#purchasePlanItemsCreateTime").val("");
		$("#invWarehouseId").val("");
		$("#invWarehouseName").val(""); */
		$("#purchasePlanItemsId").val("");
	};
	function goChooseWarehouse() {
		chooseListData('${nvix}/nvixnt/vixntTakeStockAction!goChooseWarehouse.action', 'single', '选择仓库', 'invWarehouse');
	};
	function goFixedPrice() {
		var itemId = $("#itemId").val();
		var amount = $("#amount").val();
		var createTime = $("#purchasePlanItemsCreateTime").val();
		if(!itemId){
			layer.alert("请选择商品");
			return;
		}else if(!amount){
			layer.alert("请填写商品数量");
			return;
		}else if(!createTime){
			layer.alert("请选择采购日期");
			return;
		}else{
			chooseListData('${nvix}/nvixnt/vixntPurchaseItemPriceAction!goFixedPrice.action?id='+itemId+'&count='+amount+'&billCreateDate='+createTime, 'single', '选择定价', 'fixedPrice');
		}
	};
</script>