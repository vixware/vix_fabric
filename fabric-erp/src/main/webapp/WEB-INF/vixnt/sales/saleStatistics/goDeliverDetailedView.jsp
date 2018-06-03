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
					发货明细表</span>
			</h1>
			<input type="hidden" value="Today" id="queryTime">
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
		                       <input type="text" name="reservation" id="reservation" class="form-control" value="${todayStr } - ${todayStr }"  /> 
		                       <span class="add-on input-group-addon"><i class="glyphicon glyphicon-calendar fa fa-calendar"></i></span>
		                     </div>
		                    </div>
		                  </div>
		                 </fieldset>
	               </form>
				</div>
			</div>
			
<form action="" method="post"  id="exportMD" target="form_iframe" style="margin: 0"></form>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>发货明细表</h2>
			<a href="javascript:void(0);" class="btn bg-color-blueLight mytxt-color-wathet  txt-color-white btn-sm" id=""  onclick="exportExcel();" style="float:right;padding:2px 10px 2px;margin:4px 10px 3px 5px;" >导出</a>
		</header>
		<div>
			<div class="widget-body no-padding">
				<table id="orderQueryTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
			</div>
		</div>
	</div>

</div>

<script type="text/javascript">
/** 把页面查询条件封装成js对象 **/
function queryCondition(time){
    this.time = time;
}
var qCondition = new queryCondition("Today");//不能删除，后面用
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
/** 用js把数组的‘数字元’转成‘万元’后返回新数组  **/
function myNumArrToMillionYuan(objArr) {
	var newArr =[];
	if(Array.isArray(objArr)){
		for(var x=0;x<objArr.length;x++){
			var money = Number(objArr[x]);
			var millionYuan = money/10000;
			newArr[x]= parseFloat(millionYuan.toFixed(2));
		}
	}
    return newArr;  
}
/** 传递查询参数 **/
function transmitCondition(){
	$('#queryTime').val(qCondition.time);
	orderQueryTable.ajax.reload();
}
	$(document).ready(function() {
		pageSetUp();//初始化小图
	});
</script>

<script type="text/javascript">
pageSetUp();
var orderQueryColumns = [ { 
"title" : "编号",
"width" : "8%",
"defaultContent" : ''
}, {
"title" : "发货时间",
"data" : function(data) {
	return (data.fhtime != null ? data.fhtime : "" );
}
}, {
"title" : "订单号",
"data" : function(data) {
	return (data.ordercode != null ? data.ordercode : "" );
}
}, {
"title" : "收货人",
"data" : function(data) {
	return (data.receivername != null ? data.receivername : "" );
}
}, {
"title" : "收货城市",
"data" : function(data) {
	return (data.receivercity != null ? data.receivercity : "" );
}
}, {
"title" : "收货人电话",
"data" : function(data) {
	return (data.receivermobile != null ? data.receivermobile : "" );
}
}, {
"title" : "产品编号",
"data" : function(data) {
	return (data.goodscode != null ? data.goodscode : "" );
}
}, {
"title" : "产品名称",
"data" : function(data) {
	return (data.goodsname != null ? data.goodsname : "" );
}
}, {
"title" : "数量",
"data" : function(data) {
	return (data.amount != null ? data.amount : "" );
}
},{
"title" : "单价",
"data" : function(data) {
	return (data.price != null ? Number(data.price).toFixed(2) : "" );  
}
}, {
"title" : "状态",
"data" : function(data) { 
	return (data.receiverstate != null ? data.receiverstate : "" );  
}
}];   
	var orderQueryTable = initDataTable("orderQueryTableId", "${nvix}/nvixnt/nvixntSalesAnalysisAction!queryDeliverTable.action", orderQueryColumns, function(page, pageSize, orderField, orderBy) {
		var queryTime = $("#queryTime").val();               
		queryTime = Url.encode(queryTime);
		return {
		"page" : page,
		"pageSize" : pageSize,
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
	form.action = "${nvix}/nvixnt/nvixntSalesAnalysisAction!outExcelToSaleDeliverTable.action?queryTime="+queryTime;
	form.submit();
}
</script>