<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<input id="id" type="hidden" value="${requireGoodsOrder.id}">
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>消费明细列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div style="margin: 5px;">
					<div class="form-group" style="margin: 0 0px;">
						<div class="col-md-3">
							<input type="text" value="" placeholder="商品名称" class="form-control" id="selectItemName">
						</div>
						<div class="col-md-3">
							<input type="text" value="" placeholder="商品编码" class="form-control" id="code">
						</div>
						<button onclick="itemTable.ajax.reload();" type="button" class="btn btn-info listMenu-float-left">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button onclick="$('#selectItemName').val('');$('#code').val('');itemTable.ajax.reload();" type="button" class="btn btn-default listMenu-float-left">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</div>
				</div>
				<table id="itemTabId" class="table table-striped table-bordered table-hover" width="100%"></table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">

	var itemColumns = [ {
	"title" : "编号",
	"width" : "8%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "商品名称",
	"data" : function(data) {
		if(data.isServiceItem == 'Y'){
			return data.title+"(服务项目)";
		}else if(data.isServiceItem != 'Y' && data.price == 0){
			return data.title+"(赠品)";
		}else{
			return data.title;
		}
	}
	}, {
	"title" : "商品编码",
	"data" : function(data) {
		return data.itemCode;
	}
	}, {
	"title" : "商品价格",
	"data" : function(data) {
		return data.price;
	}
	}, {
	"title" : "商品数量",
	"data" : function(data) {
		return data.num;
	}
	},{
	"title" : "商品总价",
	"data" : function(data) {
		return data.netTotal;
	}
	} ];
	
	var itemTable = initDataTable("itemTabId", "${nvix}/nvixnt/nvixExpenseRecordAction!goItemSingleList.action", itemColumns, function(page, pageSize, orderField, orderBy) {
		var code = $("#code").val();
		var id = $("#id").val();
		var selectName = $("#selectItemName").val();
		selectName = Url.encode(selectName);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"id" : id,
		"code" : code,
		"selectName" : selectName
		};
	},5);
</script>
