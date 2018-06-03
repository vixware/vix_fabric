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
				<i class="fa fa-lg fa-fw fa-bar-chart-o"></i> 采购智能分析 <span>&gt;
					采购明细</span>
			</h1>
			<input type="hidden" value="Today" id="queryTime">
			<input type="hidden" value="" id="querySupplier">
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
					 /**选择供应商时**/
					 	$("input#sltSupplier").on("onSetSelectValue", function (event, result) {
						    var sID = result.id;
						    if(sID=='all'){
						    	sID="";
						    }
						    qCondition.supplierID=sID;
							transmitCondition();
						});
					 	testdataBsSuggest.attr("data-id", "all").val("全部供应商");
					 </script>
			</div>

	<div class="row">
		<div class="col-sm-12 col-md-12">
				<div class="row">
					<div class="col-sm-12 col-md-12">
						<div id="wid-id-4" class="jarviswidget jarviswidget-sortable" data-widget-editbutton="false" role="widget">
							<header>
								<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i></span>
								<h2>采购走势图</h2>
							</header>
							<div>
								<div class="widget-body no-padding">
									<div id="dataViewD" style="height: 250px"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
		</div>
	</div>
	<form action="" method="post" name="exportMD" id="exportMD" target="form_iframe" style="margin: 0"></form>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>采购明细表</h2>  
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
function exportExcel() {  
	form = document.getElementById("exportMD");
	var queryTime = $("#queryTime").val();
	var supplierID = $("#querySupplier").val();
	form.action = "${nvix}/nvixnt/purchase/vixntPurchaseDetailedListAction!outExcelToPurchaseDetailed.action?queryTime="+queryTime+"&supplierID="+supplierID;
	form.submit();
}
/** 把页面查询条件封装成js对象 **/
function queryCondition(time,supplierID){
    this.time = time;
    this.supplierID = supplierID;
}
var qCondition = new queryCondition("Today", "");//不能删除，后面用
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
function queryPurchaseMoneyView() {
	var quTime = $('#queryTime').val();
	if(quTime=='ThisMonthOT'){ 
		quTime = 'ThisMonthAll';
	}else if(quTime=='ThisQuarterOT'){ 
		quTime = 'ThisQuarterAll';
	}else if(quTime=='ThisYearOT'){ 
		quTime = 'ThisYearAll';
	}
	var supplierID = $('#querySupplier').val();
	$.ajax({    
		url: "${nvix}/nvixnt/purchase/vixntPurchaseDetailedListAction!queryPurchaseMoneyView.action", 
	 	type: "POST",
	 	data: {queryTime:quTime,supplierID:supplierID},
	    dataType: "json",
		success:function(json){
		var yuanOrMillionYuan = "万元";
		var myChart = echarts.init(document.getElementById('dataViewD'));
		var myColors = ['#91e8e1', '#434348', '#90ed7d', '#f7a35c', '#8085e9','#FF3366', '#e4d354', '#8085e8', '#8d4653', '#91e8e1','#66FF00'];
		var option = {
		    	    title : {
		    	    },
		    	    tooltip : {
		    	        trigger: 'axis',
formatter: '{b}<br/> <span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'+myColors[0]+'"></span>{a0}: {c0}'+yuanOrMillionYuan+'<br/>'+
'',
		    	   		axisPointer: {
		       	        	 type: 'shadow',
		     	            crossStyle: {
		     	                color: '#FFFF33'
		     	            }
		     	        }
		    	    },
		    	    legend: {
		    	        data:['已完成采购订单总金额']
		    	    },
		    	    grid: {
		    	        left: '1%',
		    	        right: '1%',
		    	        bottom: '3%',
		    	        containLabel: true
		    	    },
		    	    calculable : true,
		    	    xAxis : [
		    	        {
		    	            type : 'category',
		    	            data : json.timeStr //["001", "001", "001", "001", "001", "001", "001", "001", "001", "001", "001", "001"]
		    	        }
		    	    ],
		    	    yAxis : [
		    	        {
		    	            type : 'value',name: '单位/'+yuanOrMillionYuan+''
		    	        }
		    	    ],
		    	    series : [
		    	        {
		    	            name:'已完成采购订单总金额',
		    	            type:'bar',
		    	            data: myNumArrToMillionYuan(json.valueArr),   //[211, 344, 466, 555, 666, 799, 921, 1043, 1143, 1133, 1233, 1383],
		    	            itemStyle :{
		    	            	normal:{
		    	            		color:''+myColors[0] 
		    	            	}
		    	            } 
		    	        }
		    	    ]
		    	};
			myChart.clear();
			myChart.setOption(option);
 		    $(window).resize(myChart.resize);
 }}); 		    
}
/** 传递查询参数 **/
function transmitCondition(){
	$('#queryTime').val(qCondition.time);
	$('#querySupplier').val(qCondition.supplierID);
	queryPurchaseMoneyView();
	orderQueryTable.ajax.reload();
}
	$(document).ready(function() {
		pageSetUp();//初始化小图
		queryPurchaseMoneyView();
	});
</script>

<script type="text/javascript">
pageSetUp();
var orderQueryColumns = [ { 
"title" : "编号",
"width" : "8%",
"defaultContent" : ''
}, {
"title" : "采购日期",
"data" : function(data) {
	return (data.mcreatetime != null ? data.mcreatetime : "" );
}
}, {
"title" : "采购订单号",
"data" : function(data) {
	return (data.code != null ? data.code : "" );
}
}, {
"title" : "供应商",
"data" : function(data) {
	return (data.suppliername != null ? data.suppliername : "" );
}
},{
"title" : "采购人",
"data" : function(data) {       
	return (data.purchaseperson != null ? data.purchaseperson : "" );
}
}, {
"title" : "产品编号",
"data" : function(data) {
	return (data.itemcode != null ? data.itemcode : "" );
}
}, {
"title" : "产品名称",
"data" : function(data) {
	return (data.itemname != null ? data.itemname : "" );
}
}, {
"title" : "数量",
"data" : function(data) {
	return (data.amount != null ? data.amount : "" );
}
}, {
"title" : "单位",
"data" : function(data) {
	return (data.unit != null ? data.unit : "" );
}
},{
"title" : "单价",
"data" : function(data) {
	return (data.price != null ? Number(data.price).toFixed(2) : "" );  
}
}, {
"title" : "状态",
"data" : function(data) { 
	var status = data.status;
	if(status=='0'){
		return "待配货";
	}else if(status=='1'){
		return "代发货";
	}else if(status=='2'){
		return "配送中";
	}else if(status=='4'){
		return "待分拣";
	}else if(status=='3'){
		return "已完成";
	}else{
		return "";
	}
}
}];   
	var orderQueryTable = initDataTable("orderQueryTableId", "${nvix}/nvixnt/purchase/vixntPurchaseDetailedListAction!goOrderQueryTable.action", orderQueryColumns, function(page, pageSize, orderField, orderBy) {
		var queryTime = $("#queryTime").val();
		queryTime = Url.encode(queryTime);
		var supplierID = $("#querySupplier").val();
		supplierID = Url.encode(supplierID);	
		return {
		"page" : page,
		"pageSize" : pageSize,
		"supplierID" : supplierID,
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
