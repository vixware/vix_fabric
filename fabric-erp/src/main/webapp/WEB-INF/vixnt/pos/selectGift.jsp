<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<input id="itemId" type="hidden" value="">
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>赠品列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div style="margin: 5px;">
					<div class="form-group" style="margin: 0 0px;">
						<div class="col-md-3">
							<input type="text" value="" placeholder="商品编码" class="form-control" id="code">
						</div>
						<div class="col-md-3">
							<input type="text" value="" placeholder="商品名称" class="form-control" id="name">
						</div>
						<button onclick="itemTable.ajax.reload();" type="button" class="btn btn-info listMenu-float-left">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button onclick="$('#code').val('');$('#name').val('');itemTable.ajax.reload();" type="button" class="btn btn-default listMenu-float-left">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
						<%-- <div class="listMenu-float-right">
							<button onclick="savePendingOrder('${requireGoodsOrder.id}');" type="button" class="btn btn-success">
								<i class="glyphicon glyphicon-plus"></i>
								<s:text name="保存" />
							</button>
						</div> --%>
					</div>
				</div>
				<table id="itemTabId" class="table table-striped table-bordered table-hover" width="100%"></table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">

	var itemColumns = [ {
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		return "<input id='entityId' name='entityId' value='" + data.itemId + "' type='radio' onchange=\"selectItem('" + data.itemId + "');\"/>";
	}
	}, {
	"title" : "编码",
	"data" : function(data) {
		return data.itemCode;
	}
	}, {
	"title" : "名称",
	"data" : function(data) {
		return data.itemName;
	}
	}, {
	"title" : "数量",
	"data" : function(data) {
		return data.giftCount;
	}
	} ];
	
	var itemTable = initDataTable("itemTabId", "${nvix}/nvixnt/vixntPosAction!goItemList.action", itemColumns, function(page, pageSize, orderField, orderBy) {
		var code = $("#code").val();
		var name = $("#name").val();
		name = Url.encode(name);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"code" : code,
		"name" : name
		};
	},5, "1", "0");
	
	function selectItem(id){
		$("#itemId").val(id);
	}
</script>
