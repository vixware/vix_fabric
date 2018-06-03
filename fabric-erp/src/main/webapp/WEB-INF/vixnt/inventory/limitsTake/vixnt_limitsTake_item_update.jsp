<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<fieldset>
	<div class="col-sm-8" style="text-align: left; padding: 5px 5px;">
		<div class="jarviswidget">
			<header>
				<span class="widget-icon"> <i class="fa fa-cubes"></i>
				</span>
				<h2>商品列表</h2>
			</header>
			<div>
				<div class="widget-body no-padding">
					<div style="margin: 5px;">
						<div class="form-group" style="margin: 0 0px;">
							<div class="col-md-4">
								<input type="text" value="" placeholder="商品名称" class="form-control" id="productName">
							</div>
							<button onclick="chooseEcProductTable.ajax.reload();" type="button" class="btn btn-info">
								<i class="glyphicon glyphicon-search"></i> 检索
							</button>
						</div>
					</div>
					<table id="chooseEcProduct" class="table table-striped table-bordered table-hover" width="100%">
						<thead>
							<tr>
								<th width="10%">编号</th>
								<th width="15%">编码</th>
								<th width="60%">名称</th>
								<th width="15%">库存数量</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div class="col-sm-4" style="text-align: left; padding: 0px 0px;">
		<form id="stockRecordLinesForm" class="form-horizontal" style="padding: 5px 5px;">
			<div class="jarviswidget">
				<header>
					<h2>出库单明细</h2>
				</header>
				<div>
					<div class="widget-body">
						<fieldset>
							<input type="hidden" id="stockRecordLinesId" name="stockRecordLines.id" value="${stockRecordLines.id }" /> <input type="hidden" id="stockRecordLinesStockRecordsId" name="id" value="${stockRecordLines.stockRecords.id }" />
							<div class="form-group">
								<label class="col-md-4 control-label"><span class="text-danger">*</span>商品编码:</label>
								<div class="col-md-8">
									<input id="itemcode" name="stockRecordLines.itemcode" value="${stockRecordLines.itemcode}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-4 control-label"><span class="text-danger">*</span>商品名称:</label>
								<div class="col-md-8">
									<input id="itemname" name="stockRecordLines.itemname" value="${stockRecordLines.itemname}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-4 control-label"> SKU编码: </label>
								<div id="skuTd" class="col-md-8">
									<input id="skuCode" name="stockRecordLines.skuCode" value="${stockRecordLines.skuCode}" data-prompt-position="topLeft" class="form-control" type="text" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-4 control-label"><span class="text-danger">*</span>价格:</label>
								<div class="col-md-8">
									<div class="input-group">
										<input id="unitcost" name="stockRecordLines.unitcost" value="${stockRecordLines.unitcost}" data-prompt-position="topLeft" class="form-control validate[required,custom[number]]" readonly="readonly" type="text" /><span class="input-group-addon">￥</span> 
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-4 control-label">单位:</label>
								<div class="col-md-8">
									<input id="measureUnitId" name="stockRecordLines.measureUnit.id" value="${stockRecordLines.measureUnit.id}" type="hidden" /> <input id="measureUnitName" name="stockRecordLines.measureUnit.name" value="${stockRecordLines.measureUnit.name}" class="form-control" type="text" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-4 control-label"><span class="text-danger">*</span>数量:</label>
								<div class="col-md-8">
									<input id="avaquantity" name="" value=""  type="hidden" />
									<s:if test="null == stockRecordLines">
										<input id="quantity" name="stockRecordLines.quantity" value="1" data-prompt-position="topLeft" class="form-control validate[required]" type="text" onblur="checkAvaquantity();"/>
									</s:if>
									<s:else>
										<input id="quantity" name="stockRecordLines.quantity" value="${stockRecordLines.quantity}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" onblur="checkAvaquantity();"/>
									</s:else>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-4 control-label">货位:</label>
								<div class="col-md-8">
									<input id="invShelfId" name="stockRecordLines.invShelf.id" value="${stockRecordLines.invShelf.id}" type="hidden" /> <input id="invShelfName" name="stockRecordLines.invShelf.name" value="${stockRecordLines.invShelf.name}" onfocus="chooseLocation();" data-prompt-position="topLeft"
										class="form-control" type="text" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-4 control-label">批次号:</label>
								<div class="col-md-8">
									<input id="batchcode" name="stockRecordLines.batchcode" value="${stockRecordLines.batchcode}" class="form-control" type="text" />
								</div>
							</div>
						</fieldset>
						<div class="form-actions">
							<div class="row">
								<div class="col-md-12">
									<button type="button" class="btn btn-success" onclick="mergeStockInDetail();">
										<s:if test="%{stockRecordLines.id == '' || stockRecordLines.id == null}">
											<i class="fa fa-save"></i> &nbsp; 保存并新增
										</s:if>
										<s:else>
											<i class="fa fa-edit"></i> &nbsp; 修改
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
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"name" : "itemcode",
	"data" : function(data) {
		return data.itemcode;
	}
	}, {
	"name" : "itemname",
	"data" : function(data) {
		return data.itemname;
	}
	}, {
	"name" : "avaquantity",
	"data" : function(data) {
		return data.avaquantity;
	}
	} ];

	var chooseEcProductTable = initDataTable("chooseEcProduct", "${nvix}/nvixnt/vixntLimitsTakeAction!goInventoryCurrentStockListContent.action", chooseEcProductColumns, function(page, pageSize, orderField, orderBy) {
		var name = $("#productName").val();
		name = Url.encode(name);
		var warehouseId = $('#warehouseId').val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"name" : name,
		"warehouseId" : warehouseId
		};
	});

	$('#chooseEcProduct tbody').on('click', 'tr', function() {
		var data = chooseEcProductTable.row(".selected").data();
		if (data != undefined) {
			$("#itemcode").val(data.itemcode);
			$("#itemname").val(data.itemname);
			$("#skuCode").val(data.skuCode);
			$("#unitcost").val(data.price);
			$("#measureUnitId").val(data.measureUnitId);
			$("#measureUnitName").val(data.measureUnitName);
			$("#quantity").val("1");
			$("#batchcode").val(data.barCode);
			$("#invShelfId").val(data.invShelfId);
			$("#invShelfName").val(data.invShelfName);
			$("#avaquantity").val(data.avaquantity);
		} else {
			clearStockInDetailForm();
		}
	});
	function checkAvaquantity(){
		if($("#avaquantity").val() - $("#quantity").val() >= 0){
			return true;
		}else{
			layer.alert("库存不足!");
			return false;
		}
	}
	/* 选择库位*/
	function chooseLocation() {
		var wareHouseId = $("#warehouseId").val();
		if (wareHouseId == '') {
			layer.alert("请选择仓库!", function(index) {
				layer.close(index);
			});
		} else {
			chooseListData('${nvix}/nvixnt/vixntTakeStockAction!goChooseShelf.action?wareHouseId=' + wareHouseId, 'single', '选择货位', 'invShelf');
		}
	};

	function mergeStockInDetail() {
		if($("#avaquantity").val() - $("#quantity").val() >= 0){
		if ($('#stockRecordLinesForm').validationEngine('validate')) {
				/** 打开遮盖层 */
				var loadIndex = layer.load(2);
				//入库单主体ID
				$("#stockRecordLinesStockRecordsId").val($("#stockRecordsId").val());
				$("#stockRecordLinesForm").ajaxSubmit({
				type : "post",
				url : "${nvix}/nvixnt/vixntLimitsTakeAction!saveOrUpdateWimStockRecordLines.action",
				contentType : "application/x-www-form-urlencoded; charset=utf-8",
				success : function(result) {
					/** 关闭遮盖层 */
					layer.close(loadIndex);
					stockRecordLinesTable.ajax.reload();
					showMessage(result, 'success');
					$.ajax({
					url : '${nvix}/nvixnt/vixntLimitsTakeAction!getStockRecordTotal.action?id=' + $("#stockRecordsId").val(),
					cache : false,
					success : function(json) {
						$("#totalAmount").val(json);
					}
					});
				}
				});
			}
		}else{
			layer.alert("库存不足!");
			return false;
		}
	};

	/** 清空输入项 */
	function clearStockInDetailForm() {
		$("#itemcode").val("");
		$("#itemname").val("");
		$("#skuCode").val("");
		$("#unitcost").val("");
		$("#measureUnitId").val("");
		$("#measureUnitName").val("");
		$("#quantity").val("");
		$("#batchcode").val("");
		$("#invShelfId").val("");
		$("#invShelfName").val("");
		$("#avaquantity").val("");
	};
</script>