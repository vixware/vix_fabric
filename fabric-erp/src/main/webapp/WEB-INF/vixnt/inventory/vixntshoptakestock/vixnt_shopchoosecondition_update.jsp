<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="warehouseform" class="form-horizontal" style="padding: 40px 40px;" action="${nvix}/nvixnt/vixntShopTakeStockAction!takeStockByCondition.action">
	<input type="hidden" id="warehouseId" name="warehouseId" /> <input type="hidden" id="invShelfId" name="invShelfId" />
	<input id="itemCatalogId" name="itemCatalogId" type="hidden" />
	<fieldset>
		<div class="form-group">
			<label class="col-md-2 control-label"> 商品分类:</label>
			<div class="col-md-10">
				<div class="row">
					<div class="col-sm-12">
						<div id="itemCategoryTreeMenu" class="input-group">
							
							<input id="itemCatalogName" name="" type="text" onfocus="showItemCategory(); return false;" type="text" readonly="readonly" class="form-control" />
							<div class="input-group-btn">
								<button type="button" class="btn btn-default" onclick="$('#itemCatalogId').val('');$('#itemCatalogName').val('');">清空</button>
							</div>
							<div id="itemCategoryMenu" class="menuContent" style="display: none; position: absolute; border: 1px solid gray; background-color: #dfdfdf; z-index: 9999;">
								<ul id="itemCategoryTree" class="ztree"></ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"> 仓库:</label>
			<div class="col-md-10">
				<div class="row">
					<div class="col-sm-12">
						<div class="input-group">
							<input id="warehouseName" name="warehouseName" class="form-control" type="text" readonly="readonly" />
							<div class="input-group-btn">
								<button onclick="goChooseWarehouse();" type="button" class="btn btn-info">
									<i class="glyphicon glyphicon-search"></i>
								</button>
								<button onclick="$('#warehouseId').val('');$('#warehouseName').val('');" type="button" class="btn btn-default">
									<i class="glyphicon glyphicon-repeat"></i>
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-2 control-label"> 货位:</label>
			<div class="col-md-10">
				<div class="row">
					<div class="col-sm-12">
						<div class="input-group">
							<input id="invShelfName" name="invShelfName" class="form-control" type="text" readonly="readonly" />
							<div class="input-group-btn">
								<button onclick="goChooseShelf();" type="button" class="btn btn-info">
									<i class="glyphicon glyphicon-search"></i>
								</button>
								<button onclick="$('#invShelfId').val('');$('#invShelfName').val('');" type="button" class="btn btn-default">
									<i class="glyphicon glyphicon-repeat"></i>
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</fieldset>
</form>
<script type="text/javascript">
	function goChooseWarehouse() {
		chooseListData('${nvix}/nvixnt/vixntShopTakeStockAction!goChooseWarehouse.action', 'single', '选择仓库', 'warehouse');
	};
	function goChooseShelf() {
		chooseListData('${nvix}/nvixnt/vixntShopTakeStockAction!goChooseShelf.action?wareHouseId=' + $('#warehouseId').val(), 'single', '选择货位', 'invShelf');
	};
	/** 初始化分类下拉列表树 */
	var showItemCategory = initDropListTree("itemCategoryTreeMenu", "${nvix}/nvixnt/mdm/nvixntItemCatalogAction!findTreeToJson.action", "itemCatalogId", "itemCatalogName", "itemCategoryTree", "itemCategoryMenu");
</script>