<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<h1 class="page-title txt-color-blueDark">
					<s:if test=" fromPage=='PurchasePageUnu' ">  
						<i class="fa-fw fa fa-home"></i> 供应商管理 <span>> 供应商统计报表 </span>
					</s:if>
					
					<input type="hidden" value="${changeSQL }" id="changeSQL"  />  
					<input type="hidden" value="${fromPage }" id="fromPage"  />
					<input type="hidden" value="${setSupplierID }" id="setSupplierIDApage"  />
			</h1>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-6 col-md-6 col-lg-8 text-align-right">
			<div class="page-title">
			  <s:if test="fromPage=='PurchasePageUnu'">
				<button class="btn btn-default" type="button" onclick="loadContent('','${nvix}/nvixnt/vixntSupplierViewDataAction!goSupplierView.action');"> 
					<i class="fa fa-rotate-left"></i> 返回    
				</button>
			  </s:if>
			</div>
		</div>
	</div>
	
		<div class="jarviswidget">
			<header>
				<span class="widget-icon"> <i class="fa fa-table"></i></span>
				<h2>统计列表</h2>
			</header>
			<div>
				<div class="widget-body no-padding">
					<div>
						<form role="search" class="navbar-form navbar-left">
							<div class="form-group">
								商品名称: <input type="text" value="" class="form-control" id="selectName" style="width: 130px;">
								商品编码: <input type="text" value="" class="form-control" id="phone" style="width: 130px;">
							</div>
							<button onclick="customerAccountTable.ajax.reload();" type="button" class="btn btn-primary" id="">
								<i class="glyphicon glyphicon-search"></i> 检索
							</button>
							<button onclick="$('#selectName').val('');$('#phone').val('');customerAccountTable.ajax.reload();" type="button" class="btn btn-">
								<i class="glyphicon glyphicon-repeat"></i> 清空
							</button>
						</form>
					</div>
					<table id="customerAccountTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
				</div>
			</div>
		</div> 
	
	</div>
</div>
<script type="text/javascript">
//开始统计列表
pageSetUp();
var customerAccountColumns =[ {
	"title" : "编号",
	"width" : "5%",
	"defaultContent" : ''
	}, {
	"title" : "采购商品编码",
	"data" : function(data) {
	return data.qitemcode;
	}
	}, {
	"title" : "采购商品名称",
	"data" : function(data) {
	return data.qitemname;
	}
	}, {
	"title" : "采购商品总数量",
	"data" : function(data) {  
	 return (data.qsumamount).toFixed(2);
	}
	}, {
	"title" : "采购商品总价格",
	"data" : function(data) {
		return (data.qprice).toFixed(2);
	}
	} ];

var customerAccountTable = initDataTable("customerAccountTableId", "${nvix}/nvixnt/vixntSupplierViewDataAction!queryDataToList.action", customerAccountColumns, function(page, pageSize, orderField, orderBy) {
	var phone = $("#phone").val();
	phone = Url.encode(phone);
	var selectName = $("#selectName").val();
	selectName = Url.encode(selectName);
	var changeSQL = $("#changeSQL").val();  
	changeSQL = Url.encode(changeSQL);
	var setSupplierID = $("#setSupplierIDApage").val();
	setSupplierID = Url.encode(setSupplierID);
	return {
	"page" : page,
	"pageSize" : pageSize,
	"phone" : phone,
	"changeSQL" : changeSQL,
	"setSupplierID" : setSupplierID,
	"selectName" : selectName
	};
}, 10, '0');

</script>