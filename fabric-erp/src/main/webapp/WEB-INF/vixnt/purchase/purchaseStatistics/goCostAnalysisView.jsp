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
						<i class="fa fa-lg fa-fw fa-bar-chart-o"></i> 采购智能分析 <span>&gt; 采购成本分析</span>
					</h1>
					<input type="hidden" value="ThisMonthOT" id="queryTime">
					<input type="hidden" value="" id="querySupplier">
				</div>
			</div>
			
			<section id="widget-grid" class="">

				<div class="row">
					<div class="col-sm-3">
						<div class="well padding-10">
							<h5 class="margin-top-0">
								<i class="glyphicon glyphicon-calendar fa fa-calendar"></i> 选择时间
							</h5>
								<ul class="demo-btns tab-btn">
									<li>
										<a href="javascript:void(0);" class="btn bg-color-blueLight  txt-color-white btn-xs mytxt-color-wathet" id="ThisMonthOT" onclick="clickTime('ThisMonthOT','本月');" >本月</a>
									</li>
									<li>
										<a href="javascript:void(0);" class="btn bg-color-blueLight  txt-color-white btn-xs" id="ThisQuarterOT" onclick="clickTime('ThisQuarterOT','本季度');" >本季度</a>
									</li>
									<li>
										<a href="javascript:void(0);" class="btn bg-color-blueLight   txt-color-white btn-xs" id="ThisYearOT" onclick="clickTime('ThisYearOT','本年');" >本年</a>
									</li>
								</ul>
								<form class="form-horizontal">
				                 <fieldset>
				                  <div class="control-group">
				                    <div class="controls">
				                     <div class="input-prepend input-group">
				                       <input type="text"  name="reservation" id="reservation" class="form-control" value="${todayStrFront } - ${todayStrAfter }" /><span class="add-on input-group-addon"><i class="glyphicon glyphicon-calendar fa fa-calendar"></i></span> 
				                     </div>
				                    </div>
				                  </div>
				                 </fieldset>
			              	  </form>
						</div>
						
						<div class="well padding-10">
							<h5 class="margin-top-0">
								<i class="fa fa-tags"></i> 当前选择条件:
							</h5>
							<div class="row">
								<div class="col-lg-12">
									<ul class="list-group no-margin chooseSort">    
										<li class="list-group-item"> <span class="btn bg-color-red txt-color-white badge pull-right"><i class="fa fa-times"></i></span><span id='alreadySltTime'>今日</span></li>
										<li class="list-group-item"> <span class="btn bg-color-red txt-color-white badge pull-right"><i class="fa fa-times"></i></span><span id='alreadySltSupplier'>全部供应商</span></li>
									</ul>
								</div>
							</div>
						</div>
						<div class="well padding-10">
							<h5 class="margin-top-0">
								<i class="fa fa-fw fa-institution"></i> 供应商:
							</h5>
							<div class="row">
								<div class="input-group" style="width:97%;padding-left:15px;">
                                        <input type="text" class="form-control" id="sltSupplierSuggest">
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
					 var testdataBsSuggest = $("#sltSupplierSuggest").bsSuggest({
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
					 	$("input#sltSupplierSuggest").on("onSetSelectValue", function (event, result) {
						    var sID = result.id;
						    if(sID=='all'){
						    	sID="";
						    }
						    qCondition.supplierID=sID;
						    var html=result.key;
							if($('#alreadySltSupplier').length>0){
								$('#alreadySltSupplier').text(html);
							}else{
								$(".chooseSort").append("<li class='list-group-item'><span class='btn bg-color-red txt-color-white badge pull-right'><i class='fa fa-times'></i></span><span id='alreadySltSupplier'>"+html+"</span></li>")
							}
							transmitCondition();
						});
					 </script>
						</div>
					</div>
					<div class="col-sm-9">
							<div class="row">
								<div class="col-sm-12 col-md-12" id='slowLoadSupplierMoneyMomAndAn'> <!-- 慢加载环比同比仪表盘 -->
								</div>
							</div>
							<div class="row">
								<div class="col-sm-12 col-md-12">
										<div class="row">
											<div class="col-sm-6 col-md-6">
												<div id="wid-id-4" class="jarviswidget jarviswidget-sortable" data-widget-editbutton="false" role="widget">
													<header>
														<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i></span>
														<h2>供应商采购排行Top10</h2>
													</header>
													<div>
														<div class="widget-body no-padding">
															<div id="dataViewB" style="height: 250px"></div>
														</div>
													</div>
												</div>
											</div>
											<div class="col-sm-6 col-md-6">
												<div id="wid-id-4" class="jarviswidget jarviswidget-sortable" data-widget-editbutton="false" role="widget">
													<header>
														<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i></span>   
														<h2>物料采购排行Top10</h2>
													</header>
													<div>
														<div class="widget-body no-padding">
															<div id="dataViewC" style="height: 250px"></div>
														</div>
													</div>
												</div>
											</div>
										</div>
								</div>
							</div>
							<form action="" method="post"  id="exportMDSupplier" target="form_iframe" style="margin: 0"></form>
							<div class="jarviswidget" data-widget-editbutton="false" role="widget"> 
								<header>
									<span class="widget-icon"> <i class="fa fa-table"></i></span>
									<h2>供应商采购成本分析列表</h2>
									<a href="javascript:void(0);" class="btn bg-color-blueLight mytxt-color-wathet  txt-color-white btn-sm" id=""  onclick="exportExcelSupplier();" style="float:right;padding:2px 10px 2px;margin:4px 10px 3px 5px;" >导出</a>
								</header>
								<div>
									<div class="widget-body no-padding">
										<table id="supplierCostLstTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
									</div>
								</div>
							</div>
							<form action="" method="post"  id="exportMDMateriel" target="form_iframe" style="margin: 0"></form>
							<div class="jarviswidget" data-widget-editbutton="false" role="widget"> 
								<header>
									<span class="widget-icon"> <i class="fa fa-table"></i></span>
									<h2>物料采购成本分析列表</h2>
									<a href="javascript:void(0);" class="btn bg-color-blueLight mytxt-color-wathet  txt-color-white btn-sm" id=""  onclick="exportExcelMateriel();" style="float:right;padding:2px 10px 2px;margin:4px 10px 3px 5px;" >导出</a>
								</header>
								<div>
									<div class="widget-body no-padding">
										<table id="goodsTableId" class="table table-striped table-bordered table-hover" width="100%"></table>
									</div>
								</div>
							</div>
							
					</div>
				</div>
			</section>
		</div>
<script type="text/javascript">
/** 导出 物料采购成本分析列表 ***/
function exportExcelMateriel() {
	var formb = document.getElementById("exportMDMateriel");
	var queryTime = $("#queryTime").val();
	var supplierID = $("#querySupplier").val(); 
	formb.action = "${nvix}/nvixnt/purchase/vixntPurchaseDetailedListAction!outExcelToMaterielCostTable.action?queryTime="+queryTime+"&supplierID="+supplierID;
	formb.submit();
}
/** 导出供应商采购成本分析列表 ***/
function exportExcelSupplier() {
	form = document.getElementById("exportMDSupplier");
	var queryTime = $("#queryTime").val();
	var supplierID = $("#querySupplier").val(); 
	form.action = "${nvix}/nvixnt/purchase/vixntPurchaseDetailedListAction!outExcelToSupplierCostTable.action?queryTime="+queryTime+"&supplierID="+supplierID;
	form.submit();
}
/** 把页面查询条件封装成js对象 **/
function queryCondition(time,supplierID){
    this.time = time;
    this.supplierID = supplierID;
}
var qCondition = new queryCondition("ThisMonthOT", "");//不能删除，后面用
/** 点击时间按钮 **/
function clickTime(id,timeString){
	 $('#ThisMonthOT').removeClass('mytxt-color-wathet');
	 $('#ThisQuarterOT').removeClass('mytxt-color-wathet');
	 $('#ThisYearOT').removeClass('mytxt-color-wathet');
     $('#'+id).addClass('mytxt-color-wathet');
     qCondition.time=id;
     transmitCondition();
     if($('#alreadySltTime').length>0){
		$('#alreadySltTime').text(timeString);
	 }else{
		$(".chooseSort").append("<li class='list-group-item'><span class='btn bg-color-red txt-color-white badge pull-right'><i class='fa fa-times'></i></span><span id='alreadySltTime'>"+timeString+"</span></li>");
	 }
}
/** 传递查询参数 **/
function transmitCondition(){
	$('#queryTime').val(qCondition.time);
	$('#querySupplier').val(qCondition.supplierID);
	slowLoadSupplierMoneyMomAndAn();
	queryMdSupplierTop();
	queryMdMaterielTop();
	goodsTable.ajax.reload();
	supplierCostLstTable.ajax.reload();
}
</script>		
<script type="text/javascript">
/** 慢加载：供应商采购订单总金额环比 和  供应商采购订单总金额同比  **/
function slowLoadSupplierMoneyMomAndAn(){
	$.ajax({  
		url: "${nvix}/nvixnt/purchase/vixntPurchaseDetailedListAction!slowLoadSupplierMoneyMomAndAn.action", 
		type: "POST",
		data: {queryTime:$('#queryTime').val(),supplierID:$('#querySupplier').val()},
		success : function(html) {
			$("#slowLoadSupplierMoneyMomAndAn").html(html); 
		}
	});
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
function queryMdSupplierTop() {
		$.ajax({
			url: "${nvix}/nvixnt/purchase/vixntPurchaseDetailedListAction!querySupplierOrderMoneyTop.action", 
		 	type: "POST",
		 	data: {queryTime:$('#queryTime').val(),topNum:'10',supplierID:$('#querySupplier').val()},
		    dataType: "json",
			success:function(json){
				var yuanOrMillionYuan = "万元";
				var myChart = echarts.init(document.getElementById("dataViewB"));
				var	option = {
				   	    title: {
				   	    	left: 'left',
				   	        text: '',
				   	        subtext: ''
				   	    },
				   		 tooltip : {
			    	        trigger: 'axis',
			    	        formatter: '{b}<br/> <span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:#3EA0D8"></span>{a0} : {c0}'+yuanOrMillionYuan+'<br/>'
			    	        		/* +'<span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:#FF9900"></span>{a1} : {c1}%<br/>'
			    	        		+'<span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:#297029"></span>{a2} : {c2}%<br/>' */
			    	        ,  
			    	        axisPointer: {
			    	        	 type: 'shadow',
			    	            crossStyle: {
			    	                color: '#FFFF33'
			    	            }
			    	        }
			    	    },
				   	    legend: {
				   	        data: ['供应商采购金额']
			    	  		,left: 'center'
				   	    },
				   	    grid: {
				   	        left: '3%',
				   	        right: '4%',
				   	        bottom: '3%',
				   	        containLabel: true
				   	    },
				   	    xAxis: [{
				   	        type: 'value',
				   	        boundaryGap: [0, 0.01]
				   	    }],
				   	    yAxis: [{
				   	        type: 'category',    
				   	        data:json.nameArr
				   	    }],
				   	    series: [
				   	        {
				   	            name: '供应商采购金额',
				   	            type: 'bar',
				   	            data: myNumArrToMillionYuan(json.valueArr),
				   	            itemStyle: {
			                        normal: {
			                            color: function(params) {
			                                var colorList = [
			                                  '#53A0F2' 
			                                ];
			                                return colorList[params.dataIndex]
			                            },
			                        }
			    	            },
			    	            
				   	            label: {
				   	                normal: {
				   	                    show: true,
				   	                    position: 'right',
				   	                    formatter: '{c}'+yuanOrMillionYuan+''
				   	                },
				   	                
				   	            }
				   	        }
				   	    ]
				   	};
					myChart.clear();
					myChart.setOption(option);
		 		    $(window).resize(myChart.resize);
		}});
} 
function queryMdMaterielTop() {
	$.ajax({
		url: "${nvix}/nvixnt/purchase/vixntPurchaseDetailedListAction!queryMaterielOrderMoneyTop.action", 
	 	type: "POST",
	 	data: {queryTime:$('#queryTime').val(),topNum:'10',supplierID:$('#querySupplier').val()},
	    dataType: "json",
		success:function(json){
			var yuanOrMillionYuan = "万元";
			var myChart = echarts.init(document.getElementById("dataViewC"));
			var	option = {
			   	    title: {
			   	    	left: 'left',
			   	        text: '',
			   	        subtext: ''
			   	    },
			   		 tooltip : {
		    	        trigger: 'axis',
		    	        formatter: '{b}<br/> <span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:#3EA0D8"></span>{a0} : {c0}'+yuanOrMillionYuan+'<br/>'
		    	        		/* +'<span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:#FF9900"></span>{a1} : {c1}%<br/>'
		    	        		+'<span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:#297029"></span>{a2} : {c2}%<br/>' */
		    	        ,  
		    	        axisPointer: {
		    	        	 type: 'shadow',
		    	            crossStyle: {
		    	                color: '#FFFF33'
		    	            }
		    	        }
		    	    },
			   	    legend: {
			   	        data: ['物料采购金额']
		    	  		,left: 'center'
			   	    },
			   	    grid: {
			   	        left: '3%',
			   	        right: '4%',
			   	        bottom: '3%',
			   	        containLabel: true
			   	    },
			   	    xAxis: [{
			   	        type: 'value',
			   	        boundaryGap: [0, 0.01]
			   	    }],
			   	    yAxis: [{
			   	        type: 'category',    
			   	        data:json.nameArr
			   	    }],
			   	    series: [
			   	        {
			   	            name: '物料采购金额',
			   	            type: 'bar',
			   	            data: myNumArrToMillionYuan(json.valueArr),
			   	            itemStyle: {
		                        normal: {
		                            color: function(params) {
		                                var colorList = [
		                                  '#AAD047' 
		                                ];
		                                return colorList[params.dataIndex]
		                            },
		                        }
		    	            },
		    	            
			   	            label: {
			   	                normal: {
			   	                    show: true,
			   	                    position: 'right',
			   	                    formatter: '{c}'+yuanOrMillionYuan+''
			   	                },
			   	                
			   	            }
			   	        }
			   	    ]
			   	};
				myChart.clear();
				myChart.setOption(option);
	 		    $(window).resize(myChart.resize);
	}});
}		
</script>		
<script type="text/javascript">
function choice() {//页面选择条件 
	$('#startdate').datepicker({
		dateFormat : 'yy-mm-dd',
		prevText : '<i class="fa fa-chevron-left"></i>',
		nextText : '<i class="fa fa-chevron-right"></i>',
		onSelect : function(selectedDate) {
			$('#finishdate').datepicker('option', 'minDate', selectedDate);
		}
	});
	$(".choosegroup .list-group-item").click(function(){
		var html=$(this).html();
		$(".chooseSort").append("<li class='list-group-item'><span class='btn bg-color-red txt-color-white badge pull-right'><i class='fa fa-times'></i></span>"+html+"</li>")
	});
	$(".chooseMultiple .list-multiple-item").click(function(){
		var html=$(this).html();
		if($('#alreadySltSupplier').length>0){
			$('#alreadySltSupplier').text(html);
		}else{
			$(".chooseSort").append("<li class='list-group-item'><span class='btn bg-color-red txt-color-white badge pull-right'><i class='fa fa-times'></i></span><span id='alreadySltSupplier'>"+html+"</span></li>")
		}
	});
	$(".list-unstyled li span").click(function(){
		var html=$(this).html();
		$(".chooseSort").append("<li class='list-group-item'><span class='btn bg-color-red txt-color-white badge pull-right'><i class='fa fa-times'></i></span>"+html+"</li>")
	});
	$(".chooseSort").on("click","li span",function(){
		$(this).parents("li").remove();
	})
}
		$(document).ready(function() {
			pageSetUp();//初始化小图
			choice();
			slowLoadSupplierMoneyMomAndAn();
			queryMdSupplierTop();
			queryMdMaterielTop();
		});
</script>

<script type="text/javascript">
pageSetUp();
var supplierCostLstColumns = [ {
"title" : "编号",
"width" : "25%",
"defaultContent" : ''
}, {
"title" : "供应商",
"width" : "25%",
"data" : function(data) {
	return (data.sqlname != null ? data.sqlname : "" );
}
}, {
"title" : "采购金额",
"width" : "25%",
"data" : function(data) {
	return (data.sqlnum != null ? Number(data.sqlnum).toFixed(2) : "" );  
}
}, {
"title" : "占比",
"width" : "25%",
"data" : function(data) {
	return (data.doubleTemp != null ? Number(data.doubleTemp).toFixed(2)+"%" : "" );  
}
}];
	var supplierCostLstTable = initDataTable("supplierCostLstTableId", "${nvix}/nvixnt/purchase/vixntPurchaseDetailedListAction!searchSupplierCostTable.action", supplierCostLstColumns, function(page, pageSize, orderField, orderBy) {
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
pageSetUp();
var goodsColumns = [ {
"title" : "编号",
"width" : "8%",
"defaultContent" : ''
}, {
"title" : "物料编号",
"width" : "20%",
"data" : function(data) {
	return (data.itemcode != null ? data.itemcode : "" );
}
}, {
"title" : "物料名称",
"width" : "20%",
"data" : function(data) {
	return (data.itemname != null ? data.itemname : "" );
}
}, {
"title" : "数量",
"width" : "9%",
"data" : function(data) {
	return (data.amount != null ? data.amount : "" );
}
}, {
"title" : "单位",
"width" : "9%",
"data" : function(data) {
	return (data.unit != null ? data.unit : "" );
}
}, {
"title" : "单价",
"width" : "9%",
"data" : function(data) {
	return (data.price != null ? data.price : "" );
}
}, {
"title" : "采购金额",
"width" : "14%",
"data" : function(data) {
	return (data.sqlnum != null ? Number(data.sqlnum).toFixed(2) : "" );  
}
}, {
"title" : "占比",
"data" : function(data) {
	return (data.doubleTemp != null ? Number(data.doubleTemp).toFixed(2)+"%" : "" );  
}
}];
	var goodsTable = initDataTable("goodsTableId", "${nvix}/nvixnt/purchase/vixntPurchaseDetailedListAction!searchMaterielCostTable.action", goodsColumns, function(page, pageSize, orderField, orderBy) {
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
	       	     var timeString = startstr+" - "+endstr;
		       	 if($('#alreadySltTime').length>0){
		     		$('#alreadySltTime').text(timeString);
		     	 }else{
		     		$(".chooseSort").append("<li class='list-group-item'><span class='btn bg-color-red txt-color-white badge pull-right'><i class='fa fa-times'></i></span><span id='alreadySltTime'>"+timeString+"</span></li>");
		     	 }
       		});
        });
</script>

