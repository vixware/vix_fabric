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
				<i class="fa fa-lg fa-fw fa-bar-chart-o"></i> 库存管理 <span>> 库存报表 </span><span>> 限额领料汇总表 </span>
			</h1>
			<input type="hidden" value="Today" id="queryTime">
			<input type="hidden" value="" id="querySupplier">
			<input type="hidden" value="" id="queryItemnameID">
            <input type="hidden" value="" id="queryItemcodeID">
            <input type="hidden" value="" id="queryInvWarehouseID">
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
					     /* data:{
					    	 "value" : [
					    		{"id" : "all","word" : "全部仓库"},
								{"id" : "ck01","word" : "仓库1"},
								{"id" : "ck02","word" : "仓库2"}
					   		]
					     } */
					 });
					 /**选择仓库时**/
					 	$("input#sltInvWarehouse").on("onSetSelectValue", function (event, result) {
					 		 var sID = result.id;
							    if(sID=='all'){
							    	sID="";
							    }
							    qCondition.invWarehouseID=sID;
								transmitCondition();
						});
					 	 testdataBsSuggest.attr("data-id", "all").val("全部仓库");
					 </script>
					
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
					    		{"id" : "all","word" : "限额以内和超额领料等"},  
								{"id" : "excess","word" : "超额领料"},
								{"id" : "save","word" : "限额以内"}
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
					 	testdataBsSuggest.attr("data-id", "all").val("限额以内和超额领料等");
					 </script> 
					 
			</div>
<form action="" method="post" name="exportMD" id="exportMD" target="form_iframe" style="margin: 0"></form>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>限额领料汇总表</h2>
			<!-- <h2>时间:<span style="color:#428BCA">2018-03-05 - 2018-03-05</span></h2> -->
			<a href="javascript:void(0);" class="btn bg-color-blueLight mytxt-color-wathet  txt-color-white btn-sm" onclick="exportExcel();" style="float:right;padding:2px 10px 2px;margin:4px 10px 3px 5px;" >导出</a>
		</header>
		<div>
			<div class="widget-body no-padding">
						 <div id="">
								<form role="search" class="navbar-form navbar-left">
									<div class="form-group">
										物料名称: <input type="text" value="" class="form-control" id="selectItemname">
										物料编号: <input type="text" value="" class="form-control" id="selectItemcode">
									</div>
									<button onclick="arrivalListTable.ajax.reload();" type="button" class="btn btn-info">
										<i class="glyphicon glyphicon-search"></i> 检索
									</button>
									<button onclick="$('#selectItemname').val('');$('#selectItemcode').val('');arrivalListTable.ajax.reload();" type="button" class="btn btn-default">
										<i class="glyphicon glyphicon-repeat"></i> 清空
									</button>
								</form>
							</div>
				<table id="arrivalListTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
			</div>
		</div>
	</div>


</div>

<script type="text/javascript">
function exportExcel() {  
	
}
/** 把页面查询条件封装成js对象 **/
function queryCondition(time,supplierID,itemnameID,itemcodeID,invWarehouseID){
    this.time = time;
    this.supplierID = supplierID;
    this.itemnameID = itemnameID;  
    this.itemcodeID = itemcodeID; 
    this.invWarehouseID = invWarehouseID;
}
var qCondition = new queryCondition("Today", "", "", "", "");//不能删除，后面用
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
/** 传递查询参数 **/
function transmitCondition(){
	$('#queryTime').val(qCondition.time);
	$('#querySupplier').val(qCondition.supplierID);
	$('#queryItemnameID').val(qCondition.itemnameID);
	$('#queryItemcodeID').val(qCondition.itemcodeID);
	$('#queryInvWarehouseID').val(qCondition.invWarehouseID);
	arrivalListTable.ajax.reload();
}
	$(document).ready(function() {
		pageSetUp();//初始化小图
	});
</script>

<script type="text/javascript">
pageSetUp();
var arrivalListColumns = [ { 
"title" : "编号",
"width" : "5%",
"defaultContent" : ''
}, {
"title" : "领料时间",
"data" : function(data) {
	return (data.createtime != null ? data.createtime : "" ); 
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
	return (data.price != null ? Number(data.price).toFixed(2) : "" );
}
}, {
"title" : "仓库",
"data" : function(data) {
	return (data.warehousenamestr != null ? data.warehousenamestr : "" ); 
}
}, {
"title" : "部门负责人",
"data" : function(data) {
	 return (data.departmentmanager != null ? data.departmentmanager : "" );
}
}, {
"title" : "领料限额",
"data" : function(data) {
	return (data.limitcount != null ? Number(data.limitcount).toFixed(2) : "" );
}
}, {
"title" : "请领数量",
"data" : function(data) {
	 return (data.requisitioncount != null ? Number(data.requisitioncount).toFixed(2) : "" );
}
},{
"title" : "实发数量",
"data" : function(data) {
	return (data.actualquantity != null ? Number(data.actualquantity).toFixed(2) : "" );
}
},{
"title" : "领料人",
"data" : function(data) {
	return (data.pickingpeople != null ? data.pickingpeople : "" ); 
}
},{
"title" : "发料人",
"data" : function(data) {
	 return (data.sendingpeople != null ? data.sendingpeople : "" ); 
}
},{
"title" : "状态",
"data" : function(data) {
	if(data.limitcount != null && data.actualquantity != null ){
		if(  Number(data.actualquantity) > Number(data.limitcount)  ){
			 /* return "<span class='label label-primary'>超额</span>"; */ 
			 return "<span class='label label-warning'>超额</span>";
		}
	}
	return ""; 
}
}];  
	var arrivalListTable = initDataTable("arrivalListTableId", "${nvix}/nvixnt/vixntStockQueryStatisticsAction!searchStockQuotaReceiveSummaryTable.action", arrivalListColumns, function(page, pageSize, orderField, orderBy) {
		var queryTime = $("#queryTime").val();
		queryTime = Url.encode(queryTime);
		var supplierID = $("#querySupplier").val();
		supplierID = Url.encode(supplierID);	
			qCondition.itemnameID = $("#selectItemname").val();
			qCondition.itemcodeID = $("#selectItemcode").val();
			$('#queryItemnameID').val(qCondition.itemnameID);
			$('#queryItemcodeID').val(qCondition.itemcodeID);
		var itemnameVar = $('#queryItemnameID').val();
		itemnameVar = Url.encode(itemnameVar);
		var itemcodeVar = $('#queryItemcodeID').val();
		itemcodeVar = Url.encode(itemcodeVar);
		var invWarehouseID = $('#queryInvWarehouseID').val();
		invWarehouseID = Url.encode(invWarehouseID);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"excessOrsave" : supplierID,
		"itemname" : itemnameVar,
		"itemcode" : itemcodeVar,
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

