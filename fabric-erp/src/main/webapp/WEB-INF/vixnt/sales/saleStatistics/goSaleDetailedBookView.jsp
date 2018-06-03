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
				<i class="fa fa-lg fa-fw fa-bar-chart-o"></i> 销售智能分析 <span>&gt;
					销售明细账</span>
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
					     data:${jsonObj}
					 });
					 /**选择地域时**/
					 	$("input#sltSupplier").on("onSetSelectValue", function (event, result) {
						    var sID = result.id;
						    if(sID=='all'){
						    	sID="";
						    }
						    qCondition.supplierID=sID;
							transmitCondition();
						});
					 	testdataBsSuggest.attr("data-id", "all").val("全部地区");
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
					     data:${jsonObjC}
					 });
					 /**选择客户时**/
					 	$("input#sltInvWarehouse").on("onSetSelectValue", function (event, result) {
						    var houseID = result.id;
						    if(houseID=='all'){
						    	houseID="";
						    }
						    qCondition.invWarehouseID=houseID;
			        		transmitCondition();
						});
					 	testdataBsSuggest.attr("data-id", "all").val("全部客户");
					 </script>
			</div>
			
<form action="" method="post"  id="exportMD" target="form_iframe" style="margin: 0"></form>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>销售明细表</h2>
			<a href="javascript:void(0);" class="btn bg-color-blueLight mytxt-color-wathet  txt-color-white btn-sm" id=""  onclick="exportExcel();" style="float:right;padding:2px 10px 2px;margin:4px 10px 3px 5px;" >导出</a>
		</header>
		<div>
			<div class="widget-body no-padding">
				<table id="salesDetailsListTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
			</div>
		</div>
	</div>


</div>

<script type="text/javascript">
/** 把页面查询条件封装成js对象   （此页面是复制过来的命名supplierID，invWarehouseID，没有改，代表含义已经变了，但是能用）   **/
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
	salesDetailsListTable.ajax.reload();
}
	$(document).ready(function() {
		pageSetUp();//初始化小图
	});
</script>
<script type="text/javascript">
pageSetUp();
var salesDetailsListColumns = [ {
"title" : "编号",
"width" : "8%",
"defaultContent" : ''
}, {
"title" : "单据日期",
"data" : function(data) {
	return (data.mcreatetime != null ? data.mcreatetime : "" );
}
}, {
"title" : "单据编码",
"data" : function(data) {
	return (data.djbm != null ? data.djbm : "" );
}
}, {
"title" : "地域",
"data" : function(data) {
	return (data.dyname != null ? data.dyname : "" );
}
}, {
"title" : "客户",
"data" : function(data) {
	return (data.khname != null ? data.khname : "" );   
}
}, {
"title" : "产品编码",
"data" : function(data) {
	return (data.cpbh != null ? data.cpbh : "" );
}
},{
"title" : "产品",
"data" : function(data) {       
	return (data.cpmc != null ? data.cpmc : "" );
}
},
{"title":"规格型号","orderable":false,"data" : function(data) {return (data.specification != null ? data.specification : "" );}},
{
"title" : "数量",
"data" : function(data) {       
	return (data.sl != null ? data.sl : "" );
}
}, {
	"title" : "主计量单位",
	"data" : function(data) {
		return (data.dwname != null ? data.dwname : "" );    
	}
}, {
"title" : "单价",
"data" : function(data) {
	return (data.dj != null ? Number(data.dj).toFixed(2) : "" );    
}
},                                                                           
{"title":"含税单价","orderable":false,"data" : function(data) {    
	return data.taxprice == null ? "" : (Number(data.taxprice).toFixed(2));
   }
},
{"title":"无税单价","orderable":false,"data" : function(data) {return data.netprice == null ? "" : (Number(data.netprice).toFixed(2));}},	  
{"title":"税率","orderable":false,"data" : function(data) {return data.tax == null ? "" : (data.tax + "&nbsp;%");}},
{"title":"折扣率","orderable":false,"data" : function(data) {return data.discount == null ? "" : (data.discount + "&nbsp;%");}}
];                                                                          
	var salesDetailsListTable = initDataTable("salesDetailsListTableId", "${nvix}/nvixnt/nvixntSalesStatisticsAction!searchSaleDetailedTableForBook.action", salesDetailsListColumns, function(page, pageSize, orderField, orderBy) {
		var queryTime = $("#queryTime").val();
		queryTime = Url.encode(queryTime);
		var regionalID = $("#querySupplier").val();
		regionalID = Url.encode(regionalID);
		var customerAccountID = $("#queryInvWarehouse").val();
		customerAccountID = Url.encode(customerAccountID);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"regionalID" : regionalID,
		"customerAccountID" : customerAccountID,
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
<script type="text/javascript">
/** 导出列表 ***/
function exportExcel() {
	form = document.getElementById("exportMD");
	var queryTime = $("#queryTime").val();
	queryTime = Url.encode(queryTime);
	var regionalID = $("#querySupplier").val();
	regionalID = Url.encode(regionalID);
	var customerAccountID = $("#queryInvWarehouse").val();
	customerAccountID = Url.encode(customerAccountID);  
	form.action = "${nvix}/nvixnt/nvixntSalesStatisticsAction!outExcelToSaleDetailedTableBook.action?queryTime="+queryTime+"&regionalID="+regionalID+"&customerAccountID="+customerAccountID;
	form.submit();
}
</script>