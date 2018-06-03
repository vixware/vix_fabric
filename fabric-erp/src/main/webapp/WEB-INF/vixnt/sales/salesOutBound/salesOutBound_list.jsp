<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-bar-chart-o"></i> 销售管理<span>&gt; 销售出库单</span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<div class="btn-group">
					<button class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
						<i class="glyphicon glyphicon-plus"></i>&nbsp;新增&nbsp;<span class="caret"></span>
					</button>
					<ul class="dropdown-menu pull-right">
						<li><a href="javascript:void(0);" onclick="goSaveOrUpdate('');">新增销售出库单</a></li>
						<li><a href="javascript:void(0);" onclick="chooseSalesOrder();">源自销售订单</a></li>
						<li><a href="javascript:void(0);" onclick="chooseSalesOrder();">源自销售报价</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<input id="saleOrderId" type="hidden">
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>出库单列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div>
					<form role="search" class="navbar-form navbar-left">
						<div class="form-group">
							名称: <input type="text" value="" class="form-control" id="mytitle">
						</div>
						<button onclick="stockRecordsTable.ajax.reload();" type="button" class="btn btn-info">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button onclick="var index = layer.open({title:'高级检索',type: 1, area: ['700px', '300px'], content: $('#advanceSearch')});" type="button" class="btn btn-primary">
							<i class="glyphicon glyphicon-search"></i> 高级检索
						</button>
						<button onclick="clearSearchCondition('mytitle,code,creator,warehouseId,warehouseName,createTime',stockRecordsTable);" type="button" class="btn btn-default">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</form>
					<div id="advanceSearch" style="padding:20px 15px;display:none;">
						<form id="advanceSearchForm" class="form-horizontal">
							<fieldset>
								<div class="form-group">
									<lable class="col-md-2 control-label">单据编码:</lable>
									<div class="col-md-4"> 
										<input type="text" value="" id="code" placeholder="单据编码" class="form-control"/>
									</div>
									<lable class="col-md-2 control-label">创建人:</lable>
									<div class="col-md-4"> 
										<input type="text" value="" id="creator" placeholder="创建人" class="form-control"/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-2 control-label"> 出库仓库:</label>
									<div class="col-md-4">
										<div class="row">
											<div class="col-sm-12">
												<div class="input-group">
													<input id="warehouseId" class="form-control" type="hidden" readonly="readonly" />
													<input id="warehouseName"class="form-control" type="text" readonly="readonly" />
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
									<lable class="col-md-2 control-label">出库日期:</lable>
									<div class="col-md-4"> 
										<input type="text" class="form-control" placeholder="出库日期" id="createTime" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy/MM/dd'})" />
									</div>
								</div>
								<div class="form-group" style="text-align:center;">
									<button onclick="advanceSearch();" type="button" class="btn btn-primary">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="clearSearchCondition('mytitle,code,creator,warehouseId,warehouseName,createTime',stockRecordsTable);"
										type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
								</div>
							</fieldset>
						</form>
					</div>
				</div>
				<table id="stockRecordsTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	var stockRecordsColumns = [ {
	"title" : "编号",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title" : "单据编码",
	"width" : "15%",
	"name" : "code",
	"data" : function(data) {
		return data.code;
	}
	}, {
	"title" : "主题",
	"width" : "25%",
	"name" : "name",
	"data" : function(data) {
		return data.name;
	}
	}, {
	"title" : "仓库名称",
	"width" : "15%",
	"name" : "warehouseName",
	"data" : function(data) {
		return data.warehouseName;
	}
	}, {
	"title" : "出库时间",
	"width" : "15%",
	"name" : "createTimeTimeStr",
	"data" : function(data) {
		return data.createTimeStr;
	}
	}, {
	"title" : "创建人",
	"width" : "10%",
	"name" : "creator",
	"data" : function(data) {
		return data.creator;
	}
	}, {
	"title" : "操作",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var show = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"show('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-eye'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" +show + "&nbsp;&nbsp;" +del;

	}
	} ];
	var stockRecordsTable = initDataTable("stockRecordsTableId", "${nvix}/nvixnt/nvixntSalesOutBoundAction!goSingleList.action", stockRecordsColumns, function(page, pageSize, orderField, orderBy) {
		var title = $("#mytitle").val();
		var code = $("#code").val();
		var creator = $("#creator").val();
		var warehouseId = $("#warehouseId").val();
		var createTime = $("#createTime").val();
		title = Url.encode(title);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"code" : code,
		"creator" : creator,
		"warehouseId" : warehouseId,
		"createTime" : createTime,
		"title" : title
		};
	});

	//新增
	function goSaveOrUpdate(id) {
		$.ajax({
		url : '${nvix}/nvixnt/nvixntSalesOutBoundAction!goSaveOrUpdate.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	function show(id) {
		$.ajax({
		url : '${nvix}/nvixnt/nvixntSalesOutBoundAction!goShowOutBound.action?id=' + id,
		cache : false,
		success : function(html) {
			$("#mainContent").html(html);
		}
		});
	};
	//删除
	function deleteById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/nvixntSalesOutBoundAction!deleteById.action?id=' + id, '是否删除销售出库单?', stockRecordsTable);
	};
	
	function chooseSalesOrder(){
		chooseListData('${nvix}/nvixnt/nvixntSalesOutBoundAction!goChooseSalesOrder.action', 'single', '选择销售订单', 'saleOrder',function(){
			$.ajax({
				url : '${nvix}/nvixnt/nvixntSalesOutBoundAction!createSalesOutBoundBySalesOrder.action?id=' + $("#saleOrderId").val(),
				cache : false,
				success : function(result) {
					var r = result.split(":");
					if(r[0] == "1"){
						showMessage(r[1]);
						loadContent('sale_outBound','${nvix}/nvixnt/nvixntSalesOutBoundAction!goList.action');
					}else{
						showMessage(r[1]);
						$.ajax({
							url : '${nvix}/nvixnt/nvixntSalesOutBoundAction!goSaveOrUpdate.action?id=' + r[2],
							cache : false,
							success : function(html) {
								$("#mainContent").html(html);
							}
							});
					}
				}
				}); 
		});
	}
	function goChooseWarehouse() {
		chooseListData('${nvix}/nvixnt/vixntInboundWarehouseAction!goChooseWarehouse.action', 'single', '选择仓库', 'warehouse');
	};
	function advanceSearch(){
		stockRecordsTable.ajax.reload();
		layer.closeAll('page');
	}
</script>