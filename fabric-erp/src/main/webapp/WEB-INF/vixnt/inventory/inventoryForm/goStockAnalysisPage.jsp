<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<script src="${nvix}/vixntcommon/base/js/plugin/echarts/echarts.min.js"></script>
<style>
	.tmyColorA{color:#05CD15} /* 粉红色下箭头 用于环比符号显示 */
	.tmyColorB{color:#D0000D} /* 蓝色上箭头 用于环比符号显示 */
	.mydivBorderUpperLeftRight{/* border 上Upper  下Lower  左left 右Right */  
		border-top:1px solid #E2E2E2;/* 下边框*/
		border-left:1px solid #E2E2E2;
		border-right:1px solid #E2E2E2;
	} 
	.mydivBorderLeftRight{ 
		border-left:1px solid #E2E2E2;
		border-right:1px solid #E2E2E2;
	}
	.mydivBorderLowerLeftRight{   
	    border-bottom:1px solid #E2E2E2;
		border-left:1px solid #E2E2E2;
		border-right:1px solid #E2E2E2;
	}
</style>	
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-bar-chart-o"></i> 库存管理 <span>> 库存报表 </span><span>> 库存分析 </span>
			</h1>
		</div>
	</div>
	
<div class="row">
	<div class="col-sm-12 col-md-12">
		<div class="well">
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" >
				
	<div class="row" style="border-bottom:1px solid #7AB1E6;margin-top:-13px;background-color:#EDF1F5;">
		 <div class="col-xs-4 col-sm-4 text-left" style="font-weight:500;height:30px;line-height: 25px;margin-top:6px;"> 
			<span class="font-md padding-5" style="color:#4594E0;">SKU库存查询</span>
		</div>
		<div class="pull-right padding-5" style="margin-right:9px;">
             <form class="form-inline" >
                 <div class="input-group">
                   <label class="control-label">时间:</label>
                      <div class="input-group">
					<input placeholder="时间" value="2018-08" style="width: 150px;" id="startTimeC" name="startCreateTime"  data-prompt-position="topLeft" class="form-control " onClick="WdatePicker({dateFmt:'yyyy-MM'})"  type="text" /> <span class="input-group-addon"
						onclick="WdatePicker({dateFmt:'yyyy-MM',el:'startCreateTime'});"><i class="fa fa-calendar"></i></span>
				      </div>
                 </div>
             </form>
        </div>
		<div class="pull-right padding-5" >
             <form class="form-inline" >
                 <div class="input-group">
                   <label class="control-label">仓库:</label>
                      <div class="input-group">
								<input type="text" class="form-control" id="sltSupplier" style="width: 150px;">
                                <div class="input-group-btn">
                                    <button type="button" class="btn btn-white dropdown-toggle" data-toggle="dropdown">
                                        <span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu dropdown-menu-right" role="menu">
                                    </ul>
                                </div>
				      </div>
                 </div>
             </form>
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
					    		{"id" : "all","word" : "所有仓库"},
					    		{"id" : "alla","word" : "仓库1"},
								{"id" : "out","word" : "仓库2"},
								{"id" : "in","word" : "仓库3"}
					   		]
					     } 
					 });
					 /**选择供应商时**/
					 	testdataBsSuggest.attr("data-id", "all").val("所有仓库");
					 </script>
	</div>			
	
				</div>
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
	<div class="row">
		<div class="jarviswidget">
			<header>
				<span class="widget-icon"> <i class="fa fa-table"></i></span>
				<h2>SKU库存查询列表</h2>
			</header>
			<div>
				<div class="widget-body no-padding">
								<div id="">
									<form role="search" class="navbar-form navbar-left">
										<div class="form-group">
											商品代码: <input type="text" value="" class="form-control" id="selectItemname">
											商品名称: <input type="text" value="" class="form-control" id="selectItemcode">
											
											销量: <input type="text" value="" class="form-control" id="" style="width:60px;">
											至 <input type="text" value="" class="form-control" id=""  style="width:60px;">
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
				</div>
			</div>
		</div>
	</div>
</div>
	
	
	
<div class="row">
	<div class="col-sm-12 col-md-12">
		<div class="well">
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" >
				
	<div class="row" style="border-bottom:1px solid #7AB1E6;margin-top:-13px;background-color:#EDF1F5;">
		 <div class="col-xs-4 col-sm-4 text-left" style="font-weight:500;height:30px;line-height: 25px;margin-top:6px;"> 
			<span class="font-md padding-5" style="color:#4594E0;">库存分析</span>
		</div>
		<div class="pull-right padding-5" style="margin-right:9px;">
             <form class="form-inline" >
                 <div class="input-group">
                   <label class="control-label">时间:</label>
                      <div class="input-group">
					<input placeholder="时间" value="2018-08" style="width: 150px;" id="startTimeC" name="startCreateTime"  data-prompt-position="topLeft" class="form-control " onClick="WdatePicker({dateFmt:'yyyy-MM'})"  type="text" /> <span class="input-group-addon"
						onclick="WdatePicker({dateFmt:'yyyy-MM',el:'startCreateTime'});"><i class="fa fa-calendar"></i></span>
				      </div>
                 </div>
             </form>
        </div>
		<div class="pull-right padding-5" >
             <form class="form-inline" >
                 <div class="input-group">
                   <label class="control-label">仓库:</label>
                      <div class="input-group">
								<input type="text" class="form-control" id="sltSupplierB" style="width: 150px;">
                                <div class="input-group-btn">
                                    <button type="button" class="btn btn-white dropdown-toggle" data-toggle="dropdown">
                                        <span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu dropdown-menu-right" role="menu">
                                    </ul>
                                </div>
				      </div>
                 </div>
             </form>
        </div>
        <script type="text/javascript">
					 var testdataBsSuggest = $("#sltSupplierB").bsSuggest({
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
					    		{"id" : "all","word" : "所有仓库"},
					    		{"id" : "alla","word" : "仓库1"},
								{"id" : "out","word" : "仓库2"},
								{"id" : "in","word" : "仓库3"}
					   		]
					     } 
					 });
					 /**选择供应商时**/
					 	testdataBsSuggest.attr("data-id", "all").val("所有仓库");
					 </script>
	</div>			
				
	<div class="row">
		<div class="col-xs-4 col-sm-4 text-left" style="font-weight:700;height:50px;line-height: 45px;">
			<span class="font-sm txt-color-blueDark padding-5">库存指标概况</span><i class="fa-fw fa fa-question-circle"></i>
		</div>
	</div>
				</div>
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
	<div class="row">
		<div class="col-xs-4 col-sm-4 text-center" >
			<div style="border-bottom:1px solid #E2E2E2;height:50px;line-height: 50px;font-weight:500;" class="mydivBorderUpperLeftRight">
			<span class="font-md txt-color-blueDark padding-5">周转天数</span>
			</div>
			<div style="height:70px;line-height: 100px;" class="mydivBorderLeftRight">
			 <span class="font-xl padding-5" style="color:#009BFF;">52天</span>
			</div>
			 <div style="height:35px;line-height: 30px;" class="mydivBorderLowerLeftRight">  
				<span class="font-sm padding-5" style="color:#8D8785;">较上一周期 18.18%<i class="fa fa-arrow-up tmyColorB }"></i></span>
			 </div>
		</div>
		<div class="col-xs-4 col-sm-4 text-center" style="border-left: 1px dashed #FBFBFB;padding: 0 10px;">
			<div style="border-bottom:1px solid #E2E2E2;height:50px;line-height: 50px;font-weight:500;" class="mydivBorderUpperLeftRight">
			<span class="font-md txt-color-blueDark padding-5">呆滞率</span>
			</div>
			<div style="height:70px;line-height: 100px;" class="mydivBorderLeftRight">
			 <span class="font-xl padding-5" style="color:#009BFF;">53.11%</span>
			</div>
			 <div style="height:35px;line-height: 30px;" class="mydivBorderLowerLeftRight">
				<span class="font-sm padding-5" style="color:#8D8785;">较上一周期 48.18%<i class="fa fa-arrow-up tmyColorB }"></i></span>
			 </div>
		</div>
		<div class="col-xs-4 col-sm-4 text-center" style="border-left: 1px dashed #FBFBFB;padding: 0 10px;">
			<div style="border-bottom:1px solid #E2E2E2;height:50px;line-height: 50px;font-weight:500;" class="mydivBorderUpperLeftRight">
			<span class="font-md txt-color-blueDark padding-5">残次品率</span>
			</div>
			<div style="height:70px;line-height: 100px;" class="mydivBorderLeftRight">
			 <span class="font-xl padding-5" style="color:#009BFF;">13.32%</span>
			</div>
			 <div style="height:35px;line-height: 30px;" class="mydivBorderLowerLeftRight">
				<span class="font-sm padding-5" style="color:#8D8785;">较上一周期 38.18%<i class="fa fa-arrow-up tmyColorB }"></i></span>
			 </div>
		</div>
	</div>
				</div>
			</div>
		</div>
	</div>
</div>

	<div class="row">
		<div class="col-sm-12 col-md-12">
				<div class="row">
					<div class="col-sm-12 col-md-12">
						<div id="wid-id-4" class="jarviswidget jarviswidget-sortable" data-widget-editbutton="false" role="widget">
							<header>
								<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i></span>
								<span class="font-md txt-color-blueDark padding-5" style="margin-left:5px;margin-top:-13px;">库龄结构</span>
								<i class="fa-fw fa fa-question-circle"></i>
								<a class="myHoverLine" href="javascript:;" ><span class="font-sm padding-5" style="color:#009BFF;">下载库龄相关报表</span></a>
							</header>
							<div>
								<div class="widget-body no-padding" >
									<div id="dataViewD" style="height: 300px"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
		</div>
	</div>
					
				</div>
	
	
</div>

<script type="text/javascript">
function myQueryDataView() {
	$.ajax({     
		url: '${nvix}/nvixnt/vixntStockQueryHomePageAction!calculationStockAge.action', 
		type: "POST",  
	    dataType: "json",
		success:function(json){
			var myChart = echarts.init(document.getElementById('dataViewD'));
			var myColorsYuanLai = ['#91E8E1', '#434348', '#90ed7d', '#f7a35c', '#8085e9','#FF3366', '#e4d354', '#8085e8', '#8d4653', '#91e8e1','#66FF00'];
			var myColors = ['#8BBB05'];
			var option = {
 		    	    title : {
 		    	    },
 		    	    tooltip : {
 		    	        trigger: 'axis',
formatter: '{b}<br/> <span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'+myColors[0]+'"></span>{a0}: {c0}<br/>'+
'',
 		    	   		axisPointer: {
 		       	        	 type: 'shadow',
 		     	            crossStyle: {
 		     	                color: '#FFFF33'
 		     	            }
 		     	        }
 		    	    },
 		    	    legend: {
 		    	        data:['库存数量']
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
 		    	            data : json.nameArr 
 		    	        }
 		    	    ],
 		    	    yAxis : [
 		    	        {
 		    	            type : 'value',name: ''
 		    	        }
 		    	    ],
 		    	    series : [
 		    	        {
 		    	            name:'库存数量',
 		    	            type:'bar',
 		    	            data: json.valueArr, 
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
$(document).ready(function() {
	myQueryDataView();
});
</script>

<script type="text/javascript">
pageSetUp();
var arrivalListColumns = [ { 
"title" : "编号",
"width" : "5%",
"defaultContent" : ''
}, {
"title" : "仓库",
"data" : function(data) {
	return "";  
}
}, {
"title" : "商品代码",
"data" : function(data) {
	return ""; 
}
}, {
"title" : "商品名称",
"data" : function(data) {
	return ""; 
}
}, {
"title" : "昨日总库存",
"data" : function(data) {
	return ""; 
}
}, {
"title" : "昨日可用库存",
"data" : function(data) {
	return ""; 
}
},{
"title" : "周期销量",
"data" : function(data) {       
	return ""; 
}
}, {
"title" : "平均库龄(天)",
"data" : function(data) {  
	return ""; 
}
}, {
"title" : "库龄结构",
"data" : function(data) {
	return "";  
}
}, {
"title" : "库龄明细",
"data" : function(data) {
	return ""; 
}
}, {
"title" : "库存/销量趋势",
"data" : function(data) {
	return ""; 
}
}];                                
	var arrivalListTable = initDataTable("arrivalListTableId", "${nvix}/nvixnt/vixntStockQueryStatisticsAction!emptyList.action", arrivalListColumns, function(page, pageSize, orderField, orderBy) {
		return {
		"page" : page,
		"pageSize" : pageSize
		};
	}, 10, '0');
</script>
