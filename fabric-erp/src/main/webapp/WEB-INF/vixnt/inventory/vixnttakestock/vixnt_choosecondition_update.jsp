<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<form id="warehouseform" class="form-horizontal" style="padding: 40px 40px;" action="${nvix}/nvixnt/vixntTakeStockAction!takeStockByCondition.action">
	<input type="hidden" id="warehouseId" name="warehouseId" /> 
	<input type="hidden" id="invShelfId" name="invShelfId" />
	<fieldset>
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
		chooseListData('${nvix}/nvixnt/vixntTakeStockAction!goChooseWarehouse.action', 'single', '选择仓库', 'warehouse');
	};
	function goChooseShelf() {
		chooseListData('${nvix}/nvixnt/vixntTakeStockAction!goChooseShelf.action?wareHouseId=' + $('#warehouseId').val(), 'single', '选择货位', 'invShelf');
	};
</script>