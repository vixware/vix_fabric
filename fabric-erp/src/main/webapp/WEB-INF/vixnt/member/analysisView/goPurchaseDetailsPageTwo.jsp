<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
<input type="hidden" value="${queryTime }" id="queryTimePageTwo"  /> 
 <input type="hidden" value="${setSupplierID }" id="setSupplierIDPageTwo"  /> 
  <input type="hidden" value="${id }" id="idPageTwo"  />
  <input type="hidden" value="${personName }" id="personName"  />  
		<div class="jarviswidget">
			<header>
				<span class="widget-icon"> <i class="fa fa-table"></i></span>
				<h2>订单明细统计列表</h2>   
			</header>
			<div>
				<div class="widget-body no-padding">
					<table id="orderDataTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
				</div>
			</div>
		</div> 
	</div>
<script type="text/javascript">
var orderDataTableColumns = [ {
	"title" : "编号",
	"width" : "8%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "采购人",
	"data" : function(data) {
		return $("#personName").val();
	}
	}, {
	"title" : "采购商品名称",
	"data" : function(data) {
		return data.itemName; 
	}
	}, {
	"title" : "采购商品编码",
	"data" : function(data) {
		return data.itemCode;
	}
	}, {
	"title" : "采购商品总数量",
	"data" : function(data) {
		return data.detailedTotalNum;
	}
	},{
	"title" : "采购商品总金额",
	"data" : function(data) {
		return data.detailedTotalMey;
	}
	} ];   
var orderDataTable = initDataTable("orderDataTableId", "${nvix}/nvixnt/vixntSupplierViewDataAction!goOrderDetailsPageDataTwo.action", orderDataTableColumns, function(page, pageSize, orderField, orderBy) {
	var setSupplierID = $("#setSupplierIDPageTwo").val();
	setSupplierID = Url.encode(setSupplierID);
	var queryTime = $("#queryTimePageTwo").val();
	queryTime = Url.encode(queryTime);
	var id = $("#idPageTwo").val();
	id = Url.encode(id);
	return {
	"page" : page,
	"pageSize" : pageSize,
	"queryTime" : queryTime,
	"id" : id,
	"setSupplierID" : setSupplierID
	};
}, 5);
</script>