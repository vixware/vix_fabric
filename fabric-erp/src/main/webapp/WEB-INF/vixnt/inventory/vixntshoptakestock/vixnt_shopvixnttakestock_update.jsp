<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-cubes"></i> 门店管理 <span>&gt; 门店业务管理</span> <span>&gt; 门店盘点管理</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-primary" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/vixntShopTakeStockAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<h2>盘点单</h2>
		</header>
		<div>
			<div class="widget-body">
				<form class="form-horizontal" id="stockTakingForm">
					<fieldset>
						<input type="hidden" id="stockTakingId" name="stockTaking.id" value="${stockTaking.id}" /> <input type="hidden" id="warehouseId" name="stockTaking.invWarehouse.id" value="${stockTaking.invWarehouse.id}" /> <input type="hidden" id="channelDistributorId"
							name="stockTaking.channelDistributor.id" value="${stockTaking.channelDistributor.id}" />
						<div class="form-group">
							<label class="col-md-2 control-label"><span class="text-danger">*</span> 盘点单号:</label>
							<div class="col-md-3">
								<input id="code" name="stockTaking.code" value="${stockTaking.code}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
							</div>
							<label class="col-md-2 control-label"><span class="text-danger">*</span> 主题:</label>
							<div class="col-md-3">
								<input id="name" name="stockTaking.name" value="${stockTaking.name}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-2 control-label"><span class="text-danger">*</span> 盘点仓库:</label>
							<div class="col-md-3">
								<div class="row">
									<div class="col-sm-12">
										<div class="input-group">
											<input id="warehouseName" value="${stockTaking.invWarehouse.name }" class="form-control validate[required]" type="text" readonly="readonly" />
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
							<label class="col-md-2 control-label"><span class="text-danger">*</span>盘点日期:</label>
							<div class="col-md-3">
								<div class="input-group">
									<input id="createTime" name="stockTaking.createTime" value="${stockTaking.createTimeTimeStr}" data-prompt-position="topLeft" class="form-control validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd hh:mm:ss'})" type="text" /> <span class="input-group-addon"
										onclick="WdatePicker({dateFmt:'yyyy-MM-dd hh:mm:ss',el:'createTime'});"><i class="fa fa-calendar"></i></span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-2">制单人:</label>
							<div class="col-md-3">
								<input id="creator" name="stockTaking.creator" value="${stockTaking.creator}" type="text" class="form-control">
							</div>
						</div>
						<div id="" class="jarviswidget jarviswidget-color-white">
							<header>
								<span class="widget-icon"><i class="fa fa-table"></i></span>
								<h2>盘点单明细</h2>
							</header>
							<div>
								<div class="widget-body no-padding">
									<div style="margin: 5px;">
										<div class="form-group" style="margin: 0 0px;">
											<div class="col-md-3">
												<input type="text" value="" placeholder="商品名称" class="form-control" id="itemname">
											</div>
											<button onclick="stockTakingItemTable.ajax.reload();" type="button" class="btn btn-info listMenu-float-left">
												<i class="glyphicon glyphicon-search"></i> 检索
											</button>
											<button onclick="$('#itemname').val('');stockTakingItemTable.ajax.reload();" type="button" class="btn btn-default listMenu-float-left">
												<i class="glyphicon glyphicon-repeat"></i> 清空
											</button>
										</div>
									</div>
									<table id="stockTakingItemTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
								</div>
							</div>
						</div>
					</fieldset>
					<div class="form-actions">
						<div class="row">
							<div class="col-md-12">
								<button class="btn btn-primary" type="button" onclick="saveOrUpdate();">
									<i class="fa fa-save"></i> 保存
								</button>
								<button class="btn btn-default" type="button" onclick="loadContent('', '${nvix}/nvixnt/vixntShopTakeStockAction!goList.action');">
									<i class="fa fa-rotate-left"></i> 返回
								</button>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var stockTakingItemColumns = [ {
	"title" : "编号",
	"width" : "5%",
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title" : "商品编码",
	"name" : "itemcode",
	"data" : function(data) {
		return data.itemcode;
	}
	}, {
	"title" : "商品名称",
	"name" : "itemname",
	"data" : function(data) {
		return data.itemname;
	}
	}, {
	"title" : "规格型号",
	"name" : "specification",
	"data" : function(data) {
		return data.specification;
	}
	}, {
	"title" : "SKU编码",
	"name" : "skuCode",
	"data" : function(data) {
		return data.skuCode;
	}
	}, {
	"title" : "库存数量",
	"name" : "cvquantity",
	"data" : function(data) {
		return "<span class='badge bg-color-blueLight'>" + data.cvquantity + "</span>";
	}
	}, {
	"title" : "盘点数量",
	"name" : "cvcquantity",
	"data" : function(data) {
		if (data.cvcquantity != null && data.cvcquantity != '') {
			return "<span class='badge bg-color-greenLight'>" + data.cvcquantity + "</span>";
		}
		return ""
	}
	}, {
	"title" : "差值",
	"name" : "dvalue",
	"data" : function(data) {
		if (data.dvalue != 0) {
			return "<span class='badge bg-color-red'>" + data.dvalue + "</span>";
		} else {
			return "<span class='badge bg-color-greenLight'>" + data.dvalue + "</span>";
		}
	}
	}, {
	"title" : "操作",
	"width" : "8%",
	"orderable" : false,
	"data" : function(data) {
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteStockTakingItemById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return del;
	}
	} ];
	var stockTakingItemTable = initDataTable("stockTakingItemTableId", "${nvix}/nvixnt/vixntShopTakeStockAction!goStockTakingItemList.action?id=${stockTaking.id}", stockTakingItemColumns, function(page, pageSize, orderField, orderBy) {
		var itemname = $("#itemname").val();
		itemname = Url.encode(itemname);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"itemname" : itemname,
		"orderBy" : orderBy
		};
	});
	function goChooseWarehouse() {
		chooseListData('${nvix}/nvixnt/vixntShopTakeStockAction!goChooseWarehouse.action', 'single', '选择仓库', 'warehouse');
	};
	function deleteStockTakingItemById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/vixntShopTakeStockAction!deleteStockTakingItemById.action?id=' + id, '是否删除盘点单明细?', stockTakingItemTable);
	};
	$("#stockTakingForm").validationEngine();
	function saveOrUpdate() {
		if ($("#stockTakingForm").validationEngine('validate')) {
			$("#stockTakingForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/vixntShopTakeStockAction!saveOrUpdate.action",
			dataType : "text",
			success : function(id) {
				loadContent('', '${nvix}/nvixnt/vixntShopTakeStockAction!goList.action');
			}
			});
		} else {
			return false;
		}
	};
</script>