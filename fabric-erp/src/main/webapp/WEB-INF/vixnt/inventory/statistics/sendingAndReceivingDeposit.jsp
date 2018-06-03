<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<script src="${nvix}/vixntcommon/base/js/plugin/echarts/echarts.min.js"></script>
<style type="text/css">
	.tmyColorA{color:#05CD15} /* 粉红色下箭头 用于环比符号显示 */
	.tmyColorB{color:#D0000D} /* 蓝色上箭头 用于环比符号显示 */
	.myHoverLine:hover{  /* 指定a标签鼠标移动上去加下划线 */
    border-bottom: 1px solid #000000;
    	color: #fff;
    text-decoration: none;
	}
	.myHoverLine {/* 指定a标签鼠标移出时，字体颜色为白色 */
	    color: #fff;
	}
</style>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-bar-chart-o"></i> 库存统计 <span>&gt; 收发存汇总表</span>
			</h1>
		</div>
	</div>
	
			<div class="row">
					<div class="col-sm-12 col-md-12">
						<div class="well">
							<div class="row" style="padding-top:20px;">
								<div class="col-md-2">
									<div class="well well-lg" style="background-color:#EF9178;">
										<div class="row">
											<div class="col-md-12 text-center txt-color-white">
												<strong class="">现存商品SKU数</strong><br>    
<a class="myHoverLine" href="javascript:;" ><strong class="font-lg">2</strong></a>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-2">
									<div class="well well-lg" style="background-color:#4AB1E3;">
										<div class="row">
											<div class="col-md-12 text-center txt-color-white">
												<strong class="">本月过期商品量<!-- (领导说:这里计算数量不要SKU数) --></strong><br>      
<a class="myHoverLine" href="javascript:;" ><strong class="font-lg">7</strong></a>  
											</div>
										</div>   
									</div>
								</div>
								<div class="col-md-2">
									<div class="well well-lg" style="background-color:#38B7AF;">
										<div class="row"> 
											<div class="col-md-12 text-center txt-color-white">
												<strong class="">库存不足商品SKU数</strong><br>    
<a class="myHoverLine" href="javascript:;" ><strong class="font-lg">3</strong></a>    
											</div>
										</div>   
									</div>
								</div>
								<div class="col-md-2">
									<div class="well well-lg" style="background-color:#7074B9;">
										<div class="row">  
											<div class="col-md-12 text-center txt-color-white">
												<strong class="">库存积压商品SKU数</strong><br>    
<a class="myHoverLine" href="javascript:;" ><strong class="font-lg">1</strong></a>    
											</div>
										</div>   
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<%-- <div class="row">
					<div class="col-sm-12 col-md-12">
						<div class="well">
							<div class="row">
								<div>
									<form role="search" class="navbar-form navbar-left">
										<div class="form-group">
											<label class="control-label" >
											<strong>选择日期： </strong>
											</label>
										</div>    
										<div class="input-group">
											<input value="${queryTime }" placeholder="时间" style="width: 130px;" id="startTimeA" name="startCreateTime"  data-prompt-position="topLeft" class="form-control " onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" /> <span class="input-group-addon"
												onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'startCreateTime'});"><i class="fa fa-calendar"></i></span>
										</div>
										<button type="button" class="btn btn-info" >
											<i class="glyphicon glyphicon-search"></i> 检索
										</button>
										<button type="button" class="btn btn-default" >
											<i class="glyphicon glyphicon-repeat"></i> 清空
										</button>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div> --%>
				
				<div class="row">
					<div class="col-sm-12 col-md-12">
						<div class="well">
							<div class="row">
								<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<hr class="simple" style="border-color:#c0c0c0;">
									<div class="row">
										<div class="col-xs-3 col-sm-3 text-center">
											<h5>
												<span class="font-lg txt-color-red padding-5">30319</span> 
												<div class="padding-5"></div>
												<strong class="txt-color-greenLight">今日入库商品总数</strong>
											</h5>
											<div class="padding-5"></div>
											<p>
											昨日入库<strong class="txt-color-pink"> 0</strong> 环比<strong class="txt-color-pink"> <i class="fa fa-arrow-up tmyColorB }"></i>100%</strong>
											</p>
										</div>
										<div class="col-xs-3 col-sm-3 text-center" style="border-left: 1px dashed #c0c0c0;padding: 0 10px;">
											<h5>
 												<span class="font-lg txt-color-red padding-5">30319</span> 
												<div class="padding-5"></div>
												<strong class="txt-color-greenLight">今日出库商品总数</strong>
											</h5>
											<div class="padding-5"></div>
											<p>
											昨日出库<strong class="txt-color-pink"> 0</strong> 环比<strong class="txt-color-pink"> <i class="fa fa-arrow-up tmyColorB }"></i>100%</strong>
											</p>
										</div>
										<div class="col-xs-3 col-sm-3 text-center" style="border-left: 1px dashed #c0c0c0;padding: 0 10px;">
											<h5>
 												<span class="font-lg txt-color-red padding-5">30319</span> 
												<div class="padding-5"></div>
												<strong class="txt-color-greenLight">今日领料商品总数</strong>
											</h5>
											<div class="padding-5"></div>
											<p>
											昨日领料<strong class="txt-color-pink"> 0</strong> 环比<strong class="txt-color-pink"> <i class="fa fa-arrow-up tmyColorB }"></i>100%</strong>
											</p>
										</div>
										<div class="col-xs-3 col-sm-3 text-center" style="border-left: 1px dashed #c0c0c0;padding: 0 10px;">
											<h5>
 												<span class="font-lg txt-color-red padding-5">3位</span> 
												<div class="padding-5"></div>
												<strong class="txt-color-greenLight">今日领料人</strong>
											</h5>
											<div class="padding-5"></div>
											<p>
											昨日领料<strong class="txt-color-pink"> 0位</strong> 环比<strong class="txt-color-pink"> <i class="fa fa-arrow-up tmyColorB }"></i>100%</strong>
											</p>
										</div>
									</div>
									<hr class="simple" style="border-color:#c0c0c0;">
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
								<h2>最近30日商品入库趋势图</h2>
							</header>
							<div>
								<div class="widget-body no-padding">
									<div id="dataViewC" style="height: 300px"></div>
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
								<h2>最近30日商品出库趋势图</h2>
							</header>
							<div>
								<div class="widget-body no-padding">
									<div id="dataViewD" style="height: 300px"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
		</div>
	</div>		
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>最近30日入库单据</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div  id="brow">
					<form role="search" class="navbar-form navbar-left">
						<div class="form-group">
							创建人: <input type="text" value="" class="form-control" id="mytitle" style="width: 130px;" placeholder="">
						</div>
						<button  type="button" class="btn btn-info" id="searchButtonB">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button type="button" class="btn btn-default">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</form>
				</div>
				<table id="listGoodsInSqoqmTableId" class="table table-striped table-bordered table-hover" width="100%">
				</table>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>最近30日出库单据</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div  id="brow">
					<form role="search" class="navbar-form navbar-left">
						<div class="form-group">
							创建人: <input type="text" value="" class="form-control" id="mytitle" style="width: 130px;" placeholder="">
						</div>
						<button  type="button" class="btn btn-info" id="searchButtonB">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button type="button" class="btn btn-default">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</form>
				</div>
				<table id="listGoodsOutTableId" class="table table-striped table-bordered table-hover" width="100%">
				</table>
			</div>
		</div>
	</div>
	
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>本月过期商品列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div  id="brow">
					<form role="search" class="navbar-form navbar-left">
						<div class="form-group">
							商品: <input type="text" value="" class="form-control" id="mytitle" style="width: 130px;" placeholder="">
						</div>
						<button  type="button" class="btn btn-info" id="searchButtonB">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button type="button" class="btn btn-default">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</form>
				</div>
				<table id="listGoodsOverdueTableId" class="table table-striped table-bordered table-hover" width="100%">
				</table>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>库存不足商品列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div  id="brow">
					<form role="search" class="navbar-form navbar-left">
						<div class="form-group">
							商品: <input type="text" value="" class="form-control" id="mytitle" style="width: 130px;" placeholder="">
						</div>
						<button  type="button" class="btn btn-info" id="searchButtonB">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button type="button" class="btn btn-default">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</form>
				</div>
				<table id="goodsLessTableId" class="table table-striped table-bordered table-hover" width="100%">
				</table>
			</div>
		</div>
	</div>
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>库存积压商品列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div  id="brow">
					<form role="search" class="navbar-form navbar-left">
						<div class="form-group">
							商品: <input type="text" value="" class="form-control" id="mytitle" style="width: 130px;" placeholder="">
						</div>
						<button  type="button" class="btn btn-info" id="searchButtonB">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button type="button" class="btn btn-default">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</form>
				</div>
				<table id="goodsMoreTableId" class="table table-striped table-bordered table-hover" width="100%">
				</table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
function queryMethodTest(DivTestId) {
	var testArrBarA = [1,3,4,5,2,3];
	var testArrBarB = [8,9,9,6,5,3];
	var testArrBarC = [1,1,2,3,5,6];
	var testArrTime = ['08-01','08-02','08-03','08-04','08-05','08-06'];
	var myChart = echarts.init(document.getElementById(DivTestId));
		var myColors = ['#7cb5ec', '#434348', '#90ed7d', '#f7a35c', '#8085e9','#FF3366', '#e4d354', '#8085e8', '#8d4653', '#91e8e1','#66FF00'];
			var option = {
 		    	    title : {
 		    	       // text: '最近30日会员消费方式分析',
 		    	    },
 		    	    tooltip : {
 		    	        trigger: 'axis',
 		    	   		axisPointer: {
 		       	        	 type: 'shadow',
 		     	            crossStyle: {
 		     	                color: '#FFFF33'
 		     	            }
 		     	        }
 		    	    },
 		    	    legend: {
 		    	        data:['销售订单','采购订单']
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
 		    	            data : testArrTime //["001", "001", "001", "001", "001", "001", "001", "001", "001", "001", "001", "001"]
 		    	        }
 		    	    ],
 		    	    yAxis : [
 		    	        {
 		    	            type : 'value',name: '单位/单'
 		    	        }
 		    	    ],
 		    	    series : [
 		    	        {
 		    	            name:'销售订单',
 		    	            type:'line',
 		    	            data: testArrBarA,   //[211, 344, 466, 555, 666, 799, 921, 1043, 1143, 1133, 1233, 1383],
 		    	            itemStyle :{
 		    	            	normal:{
 		    	            		color:myColors[4] 
 		    	            	}
 		    	            } 
 		    	        },{
 		    	            name:'采购订单',
 		    	            type:'line',
 		    	            data: testArrBarB,   //[211, 344, 466, 555, 666, 799, 921, 1043, 1143, 1133, 1233, 1383],
 		    	            itemStyle :{
 		    	            	normal:{
 		    	            		color:myColors[8] 
 		    	            	}
 		    	            } 
 		    	        }
 		    	    ]
 		    	};
				myChart.clear();
				myChart.setOption(option);
	 		    $(window).resize(myChart.resize);
}

function queryMethodTestBar(DivTestId,chancevar) {
	var testArrBarA = [181,239,204,0,0,195,0,102,0,0,105,178,0,290,102,0,0,247,0,188,0,0,112,99,0,218,130,0,0,294];
	 var testArrBarB = [27846,12771,6336,0,0,16660,0,33401,0,0,24930,5304,0,5246,7168,0,0,26332,0,7812,0,0,7379,9472,0,3456,31464,0,0,14976];
	 var myColors = ['#549FF0', '#A9CF44', '#90ed7d'];
	 if(chancevar=='2'){
		 testArrBarA =[45,73,58,0,0,74,0,72,0,0,47,30,0,78,52,0,0,58,0,54,0,0,30,62,0,76,77,0,0,72];
		 testArrBarB = [1190,8970,3990,0,0,4345,0,4560,0,0,3844,3600,0,1700,8064,0,0,990,0,2597,0,0,7500,2600,0,4514,3744,0,0,1782];
		 myColors = ['#FC9B43', '#44C2BE', '#44C2BE'];
	 }
	 
	 
	var testArrTime = ['08.01','08.02','08.03','08.04','08.05','08.06','08.07','08.08','08.09','08.10','08.11','08.12','08.13','08.14','08.15','08.16','08.17','08.18','08.19','08.20','08.21','08.22','08.23','08.24','08.25','08.26','08.27','08.28','08.29','08.30']
	var myChart = echarts.init(document.getElementById(DivTestId));
			var option = {
 		    	    title : {
 		    	    },
 		    	    tooltip : {
 		    	        trigger: 'axis',
 		    	   		axisPointer: {
 		       	        	 type: 'shadow',
 		     	            crossStyle: {
 		     	                color: '#FFFF33'
 		     	            }
 		     	        }
 		    	    },
 		    	    legend: {
 		    	        data:['商品数量','商品金额']
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
 		    	            data : testArrTime //["001", "001", "001", "001", "001", "001", "001", "001", "001", "001", "001", "001"]
 		    	        }
 		    	    ],
 		    	    yAxis : [
 		    	        {
 		    	            type : 'value',name: '数量'
 		    	        }
 		    	        ,{type : 'value',name: '金额/元'}
 		    	    ],
 		    	    series : [
 		    	        {
 		    	            name:'商品数量',
 		    	            type:'bar',
 		    	            data: testArrBarA,   //[211, 344, 466, 555, 666, 799, 921, 1043, 1143, 1133, 1233, 1383],
 		    	          	yAxisIndex: 0,
 		    	            itemStyle :{
 		    	            	normal:{
 		    	            		color:''+myColors[1] 
 		    	            	}
 		    	            } 
 		    	        },
 		    	       {
 		    	            name:'商品金额',
 		    	            type:'bar',
 		    	            data: testArrBarB,   //[211, 344, 466, 555, 666, 799, 921, 1043, 1143, 1133, 1233, 1383],
 		    	            yAxisIndex: 1,
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
}

$(document).ready(function() {
	pageSetUp();
	queryMethodTestBar('dataViewD','2');
	queryMethodTestBar('dataViewC','1');
})
</script>

<script type="text/javascript">
	pageSetUp();
	var listGoodsInSqoqmColumns = [ {
	"title" : "编号",
	"width" : "8%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "单据编码",
	"data" : function(data) {
		return "";
	}
	},{
	"title" : "主题",   
	"data" : function(data) {
		return ""; 
	}
	},{
	"title" : "仓库名称",
	"data" : function(data) {
		return "";
	}
	},{
	"title" : "入库时间",
	"data" : function(data) {
		return "";
	}
	},{
	"title" : "创建人",
	"data" : function(data) {
		return "";
	}
	} ]; 																										
	var listGoodsInSqoqmTable = initDataTable("listGoodsInSqoqmTableId", "${nvix}/nvixnt/purchase/vixntPurchaseStatisticsAction!goTable.action", listGoodsInSqoqmColumns, function(page, pageSize, orderField, orderBy) {
		return {
		"page" : page,
		"pageSize" : pageSize
		};
	});
</script>
<script type="text/javascript">
	pageSetUp();
	var listGoodsOutColumns = [ {
	"title" : "编号",
	"width" : "8%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "单据编码",
	"data" : function(data) {
		return "";
	}
	},{
	"title" : "主题",   
	"data" : function(data) {
		return ""; 
	}
	},{
	"title" : "仓库名称",
	"data" : function(data) {
		return "";
	}
	},{
	"title" : "出库时间",
	"data" : function(data) {
		return "";
	}
	},{
	"title" : "创建人",
	"data" : function(data) {
		return "";
	}
	} ]; 																										
	var listGoodsOutTable = initDataTable("listGoodsOutTableId", "${nvix}/nvixnt/purchase/vixntPurchaseStatisticsAction!goTable.action", listGoodsOutColumns, function(page, pageSize, orderField, orderBy) {
		return {
		"page" : page,
		"pageSize" : pageSize
		};
	});
</script>

<script type="text/javascript">
	pageSetUp();
	var listGoodsOverdueColumns = [ {
	"title" : "编号",
	"width" : "8%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "商品编码",
	"data" : function(data) {
		return "";
	}
	},{
	"title" : "SKU编码",   
	"data" : function(data) {
		return ""; 
	}
	},{
	"title" : "商品名称",
	"data" : function(data) {
		return "";
	}
	},{
	"title" : "数量",
	"data" : function(data) {
		return "";
	}
	},{
	"title" : "单价",
	"data" : function(data) {
		return "";
	}
	},{
	"title" : "仓库",
	"data" : function(data) {
		return "";
	}
	},{
	"title" : "货位",
	"data" : function(data) {
		return "";
	}
	},{
	"title" : "批次号",
	"data" : function(data) {
		return "";
	}
	},{
	"title" : "到期日",
	"data" : function(data) {
		return "";
	}
	} ]; 																										
	var listGoodsOverdueTable = initDataTable("listGoodsOverdueTableId", "${nvix}/nvixnt/purchase/vixntPurchaseStatisticsAction!goTable.action", listGoodsOverdueColumns, function(page, pageSize, orderField, orderBy) {
		return {
		"page" : page,
		"pageSize" : pageSize
		};
	});
</script>

<script type="text/javascript">
	pageSetUp();
	var goodsLessColumns = [ {
	"title" : "编号",
	"width" : "8%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "商品编码",
	"data" : function(data) {
		return "";
	}
	},{
	"title" : "SKU编码",   
	"data" : function(data) {
		return ""; 
	}
	},{
	"title" : "商品名称",
	"data" : function(data) {
		return "";
	}
	},{
	"title" : "数量",
	"data" : function(data) {
		return "";
	}
	},{
	"title" : "单价",
	"data" : function(data) {
		return "";
	}
	},{
	"title" : "预警数量",
	"data" : function(data) {
		return "";
	}
	} ]; 																										
	var goodsLessTable = initDataTable("goodsLessTableId", "${nvix}/nvixnt/purchase/vixntPurchaseStatisticsAction!goTable.action", goodsLessColumns, function(page, pageSize, orderField, orderBy) {
		return {
		"page" : page,
		"pageSize" : pageSize
		};
	});
</script>

<script type="text/javascript">
	pageSetUp();
	var goodsMoreColumns = [ {
	"title" : "编号",
	"width" : "8%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "商品编码",
	"data" : function(data) {
		return "";
	}
	},{
	"title" : "SKU编码",   
	"data" : function(data) {
		return ""; 
	}
	},{
	"title" : "商品名称",
	"data" : function(data) {
		return "";
	}
	},{
	"title" : "数量",
	"data" : function(data) {
		return "";
	}
	},{
	"title" : "单价",
	"data" : function(data) {
		return "";
	}
	},{
	"title" : "预警数量",
	"data" : function(data) {
		return "";
	}
	} ]; 																										
	var goodsMoreTable = initDataTable("goodsMoreTableId", "${nvix}/nvixnt/purchase/vixntPurchaseStatisticsAction!goTable.action", goodsMoreColumns, function(page, pageSize, orderField, orderBy) {
		return {
		"page" : page,
		"pageSize" : pageSize
		};
	});
</script>