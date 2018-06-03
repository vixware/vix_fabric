<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<input type="hidden" value="${RGOrderID }" id="RGOrderID"  />  
		<div class="jarviswidget">
			<header>
				<span class="widget-icon"> <i class="fa fa-table"></i></span>
				<h2>订单明细列表</h2>
			</header>
			<div>
				<div class="widget-body no-padding">
					<div>
						<form role="search" class="navbar-form navbar-left">
							<div class="form-group">
								<input type="text" value="" placeholder="商品名称" class="form-control" id="selectNameB" style="width: 130px;">
								<input type="text" value=""  placeholder="商品编码" class="form-control" id="phoneB" style="width: 130px;">
							</div>
							<button type="button" class="btn btn-info"   onclick="orderDataTable.ajax.reload();">
								<i class="glyphicon glyphicon-search"></i> 检索
							</button>
							<button onclick="$('#selectNameB').val('');$('#phoneB').val('');orderDataTable.ajax.reload();" type="button" class="btn btn-default">
								<i class="glyphicon glyphicon-repeat"></i> 清空
							</button>
						</form>
					</div>
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
var orderDataTable = initDataTable("orderDataTableId", "${nvix}/nvixnt/vixntMemberManageDataAction!goOrderDetailsPageData.action", orderDataTableColumns, function(page, pageSize, orderField, orderBy) {
	var selectNameB = $("#selectNameB").val();
	selectNameB = Url.encode(selectNameB);
	var phoneB = $("#phoneB").val();
	phoneB = Url.encode(phoneB);
	var RGOrderID = $("#RGOrderID").val();
	RGOrderID = Url.encode(RGOrderID);
	return {
	"page" : page,
	"pageSize" : pageSize,
	"phoneB" : phoneB,
	"RGOrderID" : RGOrderID,
	"selectNameB" : selectNameB
	};
}, 5);
</script>