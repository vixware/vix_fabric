<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<input type="hidden" id="chooseType" value="${chooseType}" />
<input type="hidden" id="parentId" value="${parentId}" />
<fieldset>
	<div class="col-sm-5" style="text-align: left; padding: 5px 5px;">
		<div class="jarviswidget">
			<header>
				<span class="widget-icon"> <i class="fa fa-table"></i></span>
				<h2>采购订单列表</h2>
			</header>
			<div>
				<div class="widget-body no-padding">
					<table id="purchaseOrderTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
				</div>
			</div>
		</div>
	</div>
	<div class="col-sm-7" style="text-align: left; padding: 0px 0px;">
		<div class="jarviswidget" style="padding: 6px;">
			<header>
				<span class="widget-icon"> <i class="fa fa-table"></i>
				</span>
				<h2>商品列表</h2>
			</header>
			<div>
				<div class="widget-body no-padding">
					<div style="margin: 5px;">
						<div class="form-group" style="margin: 0 5px;">
							<div class="col-md-4">
								<input type="text" value="" placeholder="名称" class="form-control" id="selectName">
							</div>
							<button onclick="itemTable.ajax.reload();" type="button" class="btn btn-info">
								<i class="glyphicon glyphicon-search"></i> 检索
							</button>
							<button type="button" class="btn btn-default" onclick="$('#selectName').val('');itemTable.ajax.reload();">
								<i class="glyphicon glyphicon-repeat"></i> 清空
							</button>
						</div>
					</div>
					<table id="itemtableid" class="table table-striped table-bordered table-hover" width="100%">
						<thead>
							<tr>
								<th width="10%"><s:if test="chooseType == 'multi'">
										<div class="btn-group">
											<button data-toggle="dropdown" class="btn dropdown-toggle btn-xs btn-default" aria-expanded="false">
												选择 <i class="fa fa-caret-down"></i>
											</button>
											<ul class="dropdown-menu js-status-update pull-left">
												<li><a id="all" onclick="changeDataTableSelect('itemtableid','all');" href="javascript:void(0);">全选</a></li>
												<li><a id="reverse" onclick="changeDataTableSelect('itemtableid','reverse');" href="javascript:void(0);">反选</a></li>
												<li><a id="cancle" onclick="changeDataTableSelect('itemtableid','cancle');" href="javascript:void(0);">取消</a></li>
											</ul>
										</div>
									</s:if> <s:else>选择</s:else></th>
								<th>编码</th>
								<th>名称</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
</fieldset>
<script type="text/javascript">
	var purchaseOrderColumns = [ {
	"title" : "编号",
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"title" : "主题",
	"width" : "25%",
	"name" : "name",
	"data" : function(data) {
		return data.name;
	}
	} ];
	var purchaseOrderTable = initDataTable("purchaseOrderTableId", "${nvix}/nvixnt/purchase/vixntPurchaseOrderAction!goNvixOrderBatchList.action", purchaseOrderColumns, function(page, pageSize, orderField, orderBy) {
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy
		};
	});

	var chooseType = $("#chooseType").val();
	chooseMap.setColumnName("name");
	var itemColumns = [ {
	"orderable" : false,
	"data" : function(data) {
		if (chooseType == 'single') {
			return "<input id='entityId' name='entityId' value='" + data.id + "' type='radio' onchange=\"radioChange('${entityName}','" + data.id + "','" + data.name + "');\"/>";
		}
		return "";
	}
	}, {
	"name" : "itemCode",
	"data" : function(data) {
		return data.itemCode;
	}
	}, {
	"name" : "itemName",
	"data" : function(data) {
		return data.itemName;
	}
	} ];
	var selectType = chooseType == "multi" ? "2" : "1";
	var isGenerateIndex = chooseType == "multi" ? "1" : "0";
	var itemTable = initDataTable("itemtableid", "${nvix}/nvixnt/purchase/vixntPurchaseOrderAction!goNvixPickingListDetailList.action", itemColumns, function(page, pageSize, orderField, orderBy) {
		var parentId = $("#parentId").val();
		var selectName = $("#selectName").val();
		selectName = Url.encode(selectName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"parentId" : parentId,
		"selectName" : selectName
		};
	}, 10, selectType, isGenerateIndex);

	$('#purchaseOrderTableId tbody').on('click', 'tr', function() {
		var table = $("#purchaseOrderTableId").DataTable();
		var ids = "";
		var data = table.rows(".selected").data();
		for (var i = 0; i < data.length; i++) {
			ids += data[i].id;
		}
		$('#parentId').val(ids);
		itemTable.ajax.reload();
	});
</script>