<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<input id="pendingOrderId" type="hidden" value="">
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>挂单列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div style="margin: 5px;">
					<div class="form-group" style="margin: 0 0px;">
						<div class="col-md-3">
							<input type="text" value="" placeholder="挂单号" class="form-control" id="code">
						</div>
						<button onclick="pendingOrderTable.ajax.reload();" type="button" class="btn btn-info listMenu-float-left">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button onclick="$('#code').val('');pendingOrderTable.ajax.reload();" type="button" class="btn btn-default listMenu-float-left">
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
				<table id="pendingOrderTabId" class="table table-striped table-bordered table-hover" width="100%"></table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">

	var pendingOrderColumns = [ {
	"width" : "10%",
	"orderable" : false,
	"data" : function(data) {
		return "<input id='entityId' name='entityId' value='" + data.id + "' type='radio' onchange=\"selectPendingOrder('" + data.id + "');\"/>";
	}
	}, {
	"title" : "名称",
	"data" : function(data) {
		return data.name;
	}
	}, {
	"title" : "挂单号",
	"data" : function(data) {
		return data.code;
	}
	}, {
	"title" : "挂单时间",
	"data" : function(data) {
		return data.createTimeTimeStr;
	}
	}, {
	"title" : "操作",
	"orderable" : false,
	"width" : "10%",
	"data" : function(data) {
		/* var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"checkPendingOrder('" + data.id + "');\" title='取出挂单'><span class='txt-color-blue pull-right'><i class='fa  fa-check'></i></span></a>"; */
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deletePendingOrderById('" + data.id + "');\" title='删除挂单'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return del;
	}
	} ];
	
	var pendingOrderTable = initDataTable("pendingOrderTabId", "${nvix}/nvixnt/vixntPosAction!goPendingOrderList.action", pendingOrderColumns, function(page, pageSize, orderField, orderBy) {
		var code = $("#code").val();
		return {
		"page" : page,
		"pageSize" : pageSize,
		"orderField" : orderField,
		"orderBy" : orderBy,
		"code" : code
		};
	},5, "1", "0");
	
	function savePendingOrder(orderId){
		if(orderId){
			$.ajax({
				url : "${nvix}/nvixnt/vixntPosAction!isPendingOrder.action",
				cache : false,
				data : {
					"orderId" : orderId
				},
				success : function(result){
					var r= result.split(":");
					if(r[0] != '0'){
						$.ajax({
							url : "${nvix}/nvixnt/vixntPosAction!savePendingOrder.action",
							cache : false,
							data : {
								"orderId" : orderId
							},
							success : function(result){
								var r= result.split(":");
								if(r[0] != '0'){
									pendingOrderTable.ajax.reload();
									$("#order").load("${nvix}/nvixnt/vixntPosAction!loadOrder.action?orderId=''");
									$("#orderCountAndPrice").load("${nvix}/nvixnt/vixntPosAction!loadOrderCountAndPrice.action?orderId=''");
								}else{
									layer.alert(r[1]);
									return ;
								}
							}
						})
					}else{
						layer.alert(r[1]);
						return ;
					}
				}
			})
		}else{
			layer.alert("暂无挂单的订单");
		}
	}
	
	function selectPendingOrder(id){
		$("#pendingOrderId").val(id);
	}
	function deletePendingOrderById(id) {
		deleteEntityByConfirm('${nvix}/nvixnt/vixntPosAction!deletePendingOrderById.action?id=' + id, '是否删除该挂单记录?', pendingOrderTable);
	};
</script>
