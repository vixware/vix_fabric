<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div class="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-cubes"></i> 库存管理 <span>> 调拨管理 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
				<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
					<i class="fa fa-save"></i> 保存
				</button>
				<button class="btn btn-default" type="button" onclick="loadContent('content_allocateTransfer','${nvix}/nvixnt/vixntAllocateTransferAction!goList.action');">
					<i class="fa fa-rotate-left"></i> 返回
				</button>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-list-alt"></i>
			</span>
			<h2>基本信息</h2>
		</header>
		<div class="widget-body">
			<form class="form-horizontal" id="wimTransvouchForm">
				<input type="hidden" id="wimTransvouchId" name="wimTransvouch.id" value="${wimTransvouch.id}" />
				<fieldset>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>单号:</label>
						<div class="col-md-3">
							<input id="code" name="wimTransvouch.code" value="${wimTransvouch.code}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>主题:</label>
						<div class="col-md-3">
							<input id="name" name="wimTransvouch.name" value="${wimTransvouch.name}" data-prompt-position="topLeft" class="form-control validate[required]" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label">调拨人:</label>
						<div class="col-md-3">
							<input id="creator" name="wimTransvouch.creator" value="${wimTransvouch.creator}" data-prompt-position="topLeft" class="form-control" type="text" />
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>调拨日期:</label>
						<div class="col-md-3">
							<div class="input-group">
								<input id="createtime" name="wimTransvouch.createtime" value="${wimTransvouch.createTimeStr}" data-prompt-position="topLeft" class="form-control validate[required,custom[date]]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> <span class="input-group-addon"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'createtime'});"><i class="fa fa-calendar"></i></span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-2 control-label"><span class="text-danger">*</span>转出仓库:</label>
						<div class="col-md-3">
							<div class="row">
								<div class="col-sm-12">
									<div class="input-group">
										<input type="hidden" id="outInvWarehouseId" name="wimTransvouch.outInvWarehouse.id" value="${wimTransvouch.outInvWarehouse.id}" onchange="fieldChanged(this);" /> <input id="outInvWarehouseName" name="wimTransvouch.outInvWarehouseName" value="${wimTransvouch.outInvWarehouseName}"
											type="text" class="validate[required] form-control" />
										<div class="input-group-btn">
											<button onclick="chooseWarehouse();" type="button" class="btn btn-primary">
												<i class="glyphicon glyphicon-search"></i>
											</button>
											<button onclick="$('#outInvWarehouseId').val('');$('#outInvWarehouseName').val('');" type="button" class="btn btn-default">
												<i class="glyphicon glyphicon-repeat"></i>
											</button>
										</div>
									</div>
								</div>
							</div>
						</div>
						<label class="col-md-2 control-label"><span class="text-danger">*</span>转入仓库:</label>
						<div class="col-md-3">
							<div class="row">
								<div class="col-sm-12">
									<div class="input-group">
										<input type="hidden" id="inInvWarehouseId" name="wimTransvouch.inInvWarehouse.id" value="${wimTransvouch.inInvWarehouse.id}" onchange="fieldChanged(this);" /> <input id="inInvWarehouseName" name="wimTransvouch.inInvWarehouseName" value="${wimTransvouch.inInvWarehouseName}" type="text"
											class="validate[required] form-control" />
										<div class="input-group-btn">
											<button onclick="chooseInWarehouse();" type="button" class="btn btn-primary">
												<i class="glyphicon glyphicon-search"></i>
											</button>
											<button onclick="$('#inInvWarehouseId').val('');$('#inInvWarehouseName').val('');" type="button" class="btn btn-default">
												<i class="glyphicon glyphicon-repeat"></i>
											</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div id="" class="jarviswidget">
						<header>
							<span class="widget-icon"><i class="fa fa-table"></i></span>
							<h2>明细</h2>
						</header>
						<div>
							<div class="widget-body no-padding">
								<div style="margin: 5px;">
									<div class="form-group" style="margin: 0 0px;">
										<div class="col-md-3">
											<input type="text" value="" placeholder="商品名称" class="form-control" id="searchName">
										</div>
										<button onclick="itemBrandTable.ajax.reload();" type="button" class="btn btn-primary listMenu-float-left">
											<i class="glyphicon glyphicon-search"></i> 检索
										</button>
										<button onclick="$('#searchName').val('');itemBrandTable.ajax.reload();" type="button" class="btn btn-default listMenu-float-left">
											<i class="glyphicon glyphicon-repeat"></i> 清空
										</button>
										<div class="listMenu-float-right">
											<button onclick="addDetail('');" type="button" class="btn btn-primary">
												<i class="glyphicon glyphicon-plus"></i>
												<s:text name="add" />
											</button>
										</div>
									</div>
								</div>
								<table id="detailTable" class="table table-striped table-bordered table-hover" width="100%"></table>
							</div>
						</div>
					</div>
				</fieldset>
			</form>
			<div class="form-actions">
				<div class="row">
					<div class="col-md-12">
						<button class="btn btn-success" type="button" onclick="saveOrUpdate();">
							<i class="fa fa-save"></i> 保存
						</button>
						<button class="btn btn-default" type="button" onclick="loadContent('content_allocateTransfer','${nvix}/nvixnt/vixntAllocateTransferAction!goList.action');">
							<i class="fa fa-rotate-left"></i> 返回
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	pageSetUp();
	var itemBrandColumns = [ {
	"title" : "编号",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "商品编码",
	"width" : "15%",
	"data" : function(data) {
		return data.itemcode;
	}
	}, {
	"title" : "商品名称",
	"width" : "50%",
	"data" : function(data) {
		return data.itemname;
	}
	}, {
	"title" : "调拨数量",
	"width" : "15%",
	"data" : function(data) {
		return data.tvquantity;
	}
	}, {
	"title" : "操作",
	"width" : "10%",
	"data" : function(data) {
		if (data.id != null) {
			var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"addDetail('" + data.id + "');\" title='更新'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
			var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteWimTransvouchitemById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
			return update + "&nbsp;&nbsp;" + del;
		}
		return '';
	}
	} ];

	var itemBrandTable = initDataTable("detailTable", "${nvix}/nvixnt/vixntAllocateTransferAction!getWimTransvouchItemJson.action?id=${wimTransvouch.id}", itemBrandColumns, function(page, pageSize, orderField, orderBy) {
		var searchName = $("#searchName").val();
		searchName = Url.encode(searchName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"searchName" : searchName
		};
	});
	function deleteWimTransvouchitemById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/vixntAllocateTransferAction!deleteWimTransvouchitemById.action?id=' + id, '是否删除调拨商品?', itemBrandTable);
	};
	function addDetail(id) {
		if ($("#wimTransvouchForm").validationEngine('validate')) {
			openWindowForShow('${nvix}/nvixnt/vixntAllocateTransferAction!goSaveOrUpdateTransferItem.action?wimTransvouchId=' + $("#wimTransvouchId").val() + "&outInvWarehouseId=" + $("#outInvWarehouseId").val() + "&inInvWarehouseId=" + $("#inInvWarehouseId").val(), '添加商品', 800, 600, itemBrandTable);
		}
	};
	function chooseWarehouse() {
		chooseListData('${nvix}/nvixnt/vixntInboundWarehouseAction!goChooseWarehouse.action', 'single', '选择仓库', 'outInvWarehouse');
	};
	function chooseInWarehouse() {
		chooseListData('${nvix}/nvixnt/vixntInboundWarehouseAction!goChooseWarehouse.action', 'single', '选择仓库', 'inInvWarehouse');
	};
	function saveOrUpdate() {
		if ($("#wimTransvouchForm").validationEngine('validate')) {
			$("#wimTransvouchForm").ajaxSubmit({
			type : "post",
			url : "${nvix}/nvixnt/vixntAllocateTransferAction!saveOrUpdate.action",
			dataType : "text",
			success : function(result) {
				showMessage(result, 'success');
				loadContent('inv_transfer', '${nvix}/nvixnt/vixntAllocateTransferAction!goList.action');
			}
			});
		} else {
			return false;
		}
	};
</script>