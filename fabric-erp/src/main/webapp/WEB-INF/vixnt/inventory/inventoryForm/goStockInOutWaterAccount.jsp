<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" href="${nvix}/vixntcommon/base/js/plugin/daterangepicker/daterangepicker-bs3.css">
<script src="${nvix}/vixntcommon/base/js/plugin/daterangepicker/moment.js"></script>
<script src="${nvix}/vixntcommon/base/js/plugin/daterangepicker/daterangepicker.js"></script>
<script src="${nvix}/vixntcommon/base/js/plugin/echarts/echarts.min.js"></script>
<script src="${nvix}/vixntcommon/base/js/plugin/bootstrap-suggest/bootstrap-suggest.min.js"></script>
<style>
	.mytxt-color-wathet { /* 目标按钮浅蓝色 */
	background-color:#428BCA !important;
	}
</style>
<div id="content">

	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-bar-chart-o"></i> 库存管理 <span>> 库存报表 </span><span>> 出入库流水账 </span>
			</h1>
			<input type="hidden" value="Today" id="queryTime">
			<input type="hidden" value="" id="querySupplier">
			<input type="hidden" value="" id="queryInvWarehouse">
		</div>
	</div>

	<div class="row" style="margin-bottom:10px;">
				<div class="" style="float:left;margin-left:15px;">
					<a href="javascript:void(0);" class="btn bg-color-blueLight mytxt-color-wathet  txt-color-white btn-sm" id="Today"  onclick="clickTime('Today');" >今日</a>
					<a href="javascript:void(0);" class="btn bg-color-blueLight  txt-color-white btn-sm" id="ThisWeek"  onclick="clickTime('ThisWeek');"  >本周</a>
					<a href="javascript:void(0);" class="btn bg-color-blueLight  txt-color-white btn-sm"  id="ThisMonthOT"  onclick="clickTime('ThisMonthOT');"  >本月</a>
					<a href="javascript:void(0);" class="btn bg-color-blueLight  txt-color-white btn-sm" id="ThisQuarterOT"  onclick="clickTime('ThisQuarterOT');"  >本季度</a>
					<a href="javascript:void(0);" class="btn bg-color-blueLight  txt-color-white btn-sm" id="ThisYearOT"  onclick="clickTime('ThisYearOT');"  >本年</a>
				</div>
				
				<div class="col-sm-2">
					<form class="form-horizontal" style="display:inline-block">
		                 <fieldset>
		                  <div class="control-group">
		                    <div class="controls">
		                     <div class="input-prepend input-group">
		                       <input type="text" name="reservation" id="reservation" class="form-control" value="${todayStr } - ${todayStr }" /> 
		                       <span class="add-on input-group-addon"><i class="glyphicon glyphicon-calendar fa fa-calendar"></i></span>
		                     </div>
		                    </div>
		                  </div>
		                 </fieldset>
	               </form>
				</div>
				<div class="col-sm-2" style="float:left;margin-left:-10px;">
						<div class="input-group" >
	                              <input type="text" class="form-control" id="sltSupplier">
                              <div class="input-group-btn">
                                  <button type="button" class="btn btn-white dropdown-toggle" data-toggle="dropdown">
                                      <span class="caret"></span>
                                  </button>
                                  <ul class="dropdown-menu dropdown-menu-right" role="menu">
                                  </ul>
                              </div>
                          </div>
				</div>
				<script type="text/javascript">
					 var testdataBsSuggest = $("#sltSupplier").bsSuggest({
					     indexId: 1,
					     indexKey: 1,
					     idField: "id",                 
					     keyField: "",              
					     effectiveFields: ["word"], 
					     listStyle: {
					         "padding-top":0, "max-height": "123px", "max-width": "300px",
					         "overflow": "auto", "width": "auto",
					         "transition": "0.3s", "-webkit-transition": "0.3s", "-moz-transition": "0.3s", "-o-transition": "0.3s"       
					     },                            
					     data:{
					    	 "value" : [
					    		{"id" : "all","word" : "出库和入库"},
								{"id" : "out","word" : "出库"},
								{"id" : "in","word" : "入库"}
					   		]
					     }
					 });
					 /**选择供应商时**/
					 	$("input#sltSupplier").on("onSetSelectValue", function (event, result) {
						    var sID = result.id;
						    if(sID=='all'){
						    	sID="";
						    }
						    qCondition.supplierID=sID;
							transmitCondition();
						});
					 	testdataBsSuggest.attr("data-id", "all").val("出库和入库");
					 </script>
				<div class="col-sm-2" style="float:left;margin-left:-10px;">
						<div class="input-group" >
	                              <input type="text" class="form-control" id="sltInvWarehouse">
                              <div class="input-group-btn">
                                  <button type="button" class="btn btn-white dropdown-toggle" data-toggle="dropdown">
                                      <span class="caret"></span>
                                  </button>
                                  <ul class="dropdown-menu dropdown-menu-right" role="menu">
                                  </ul>
                              </div>
                          </div>
				</div>
				<script type="text/javascript">
					 var testdataBsSuggest = $("#sltInvWarehouse").bsSuggest({
					     indexId: 1,
					     indexKey: 1,
					     idField: "id",                 
					     keyField: "",              
					     effectiveFields: ["word"], 
					     listStyle: {
					         "padding-top":0, "max-height": "123px", "max-width": "300px",
					         "overflow": "auto", "width": "auto",
					         "transition": "0.3s", "-webkit-transition": "0.3s", "-moz-transition": "0.3s", "-o-transition": "0.3s"       
					     },                            
					     data:${jsonBsSuggestJava}
					 });
					 /**选择仓库时**/
					 	$("input#sltInvWarehouse").on("onSetSelectValue", function (event, result) {
						    var houseID = result.id;
						    if(houseID=='all'){
						    	houseID="";
						    }
						    qCondition.invWarehouseID=houseID;
			        		transmitCondition();
						});
					 	testdataBsSuggest.attr("data-id", "all").val("全部仓库");
					 </script>
			</div>
<form action="" method="post" name="exportMD" id="exportMD" target="form_iframe" style="margin: 0"></form>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>出入库流水账</h2>
			<a href="javascript:void(0);" class="btn bg-color-blueLight mytxt-color-wathet  txt-color-white btn-sm" id=""  onclick="exportExcel();" style="float:right;padding:2px 10px 2px;margin:4px 10px 3px 5px;" >导出</a>
		</header>
		<div>
			<div class="widget-body no-padding">
				<table id="strorageLstTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
			</div>
		</div>
	</div>


</div>

<script type="text/javascript">
function exportExcel() {
	form = document.getElementById("exportMD");
	var queryTime = $("#queryTime").val();
	var inOrOutStock = $("#querySupplier").val(); 
	var invWarehouseID = $("#queryInvWarehouse").val();  
	form.action = "${nvix}/nvixnt/vixntStockQueryStatisticsAction!outExcelToStorageTable.action?queryTime="+queryTime+"&inOrOutStock="+inOrOutStock+"&invWarehouseID="+invWarehouseID;
	form.submit();
}
/** 把页面查询条件封装成js对象 **/
function queryCondition(time,supplierID,invWarehouseID){
    this.time = time;
    this.supplierID = supplierID;
    this.invWarehouseID = invWarehouseID;
}
var qCondition = new queryCondition("Today", "","");//不能删除，后面用
/** 点击时间按钮 **/
function clickTime(id){
	 $('#Today').removeClass('mytxt-color-wathet');
	 $('#ThisWeek').removeClass('mytxt-color-wathet');
	 $('#ThisMonthOT').removeClass('mytxt-color-wathet');
	 $('#ThisQuarterOT').removeClass('mytxt-color-wathet');
	 $('#ThisYearOT').removeClass('mytxt-color-wathet');
     $('#'+id).addClass('mytxt-color-wathet');
     qCondition.time=id;
     transmitCondition();
}
/** 传递最终查询参数 **/
function transmitCondition(){
	$('#queryTime').val(qCondition.time);
	$('#querySupplier').val(qCondition.supplierID);
	$('#queryInvWarehouse').val(qCondition.invWarehouseID);
	strorageLstTable.ajax.reload();
}
	$(document).ready(function() {
		pageSetUp();//初始化小图
	});
</script>
<script type="text/javascript">
pageSetUp();
var strorageLstColumns = [ { 
"title" : "编号",
"width" : "5%",
"defaultContent" : ''
}, {
"title" : "时间",
"data" : function(data) {  
	return (data.createtimetimestr != null ? data.createtimetimestr : "" );
}
},{
"title" : "订单号",
"data" : function(data) {  
	return (data.code != null ? data.code : "" );
}
}, {
"title" : "标志",
"data" : function(data) {
	if (data.flag == 1) {
		return "<span class='label label-info'>入库</span>";
	} else if (data.flag == 2) {
		return "<span class='label label-success'>出库</span>";
	}
	return "";
}
}, {
"title" : "物料",
"data" : function(data) {
	return (data.itemname != null ? data.itemname : "" ); 
}
}, {
"title" : "编号",
"data" : function(data) {
	return (data.itemcode != null ? data.itemcode : "" );
}
},{
"title" : "规格",
"data" : function(data) {       
	return (data.specification != null ? data.specification : "" ); 
}
}, {
"title" : "单位",
"data" : function(data) {  
	return (data.unit != null ? data.unit : "" ); 
}
}, {
"title" : "单价",
"data" : function(data) {
	 return (data.unitcost != null ? Number(data.unitcost).toFixed(2) : "" );
}
}, {
"title" : "入库数量",
"data" : function(data) {
	if (data.flag == 1) {
		return (data.quantity != null ? Number(data.quantity).toFixed(2) : "" );
	}
	return "";
}
}, {
"title" : "出库数量",
"data" : function(data) {
	if (data.flag == 2) {
		return (data.quantity != null ? Number(data.quantity).toFixed(2) : "" );
	}
	return ""; 
}
},{
"title" : "入库金额",
"data" : function(data) {
	if (data.flag == 1) {
		return (data.price != null ? Number(data.price).toFixed(2) : "" );
	}
	return "";
}
},{
"title" : "出库金额",
"data" : function(data) {
	if (data.flag == 2) {
		return (data.price != null ? Number(data.price).toFixed(2) : "" );
	}
	return "";
}
},{
"title" : "仓库名称",
"data" : function(data) {
	return (data.ckname != null ? data.ckname : "" ); 
}
}];
	var strorageLstTable = initDataTable("strorageLstTableId", "${nvix}/nvixnt/vixntStockQueryStatisticsAction!searchStockInOutWaterAccountTable.action", strorageLstColumns, function(page, pageSize, orderField, orderBy) {
		var queryTime = $("#queryTime").val();
		queryTime = Url.encode(queryTime);
		var inOrOutStock = $("#querySupplier").val();
		inOrOutStock = Url.encode(inOrOutStock);
		var invWarehouseID = $("#queryInvWarehouse").val();
		invWarehouseID = Url.encode(invWarehouseID);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"inOrOutStock" : inOrOutStock,
		"invWarehouseID" : invWarehouseID,
		"queryTime" : queryTime
		};
	}, 10, '0');
</script>
<script type="text/javascript">
        $(document).ready(function() {
        	/** 点击自定义时间时 **/
        	$('#reservation').daterangepicker(null, function(start, end, label) {});
        	/** 自定义时间被确认时 **/
        	$('#reservation').on('apply.daterangepicker', function(ev, picker) {
        	    var startstr = picker.startDate.format('YYYY-MM-DD');
        	    var endstr = picker.endDate.format('YYYY-MM-DD');
        	    var strTime = startstr+endstr;
        	    qCondition.time=strTime;
        	    transmitCondition();
        	});
        });
</script>
