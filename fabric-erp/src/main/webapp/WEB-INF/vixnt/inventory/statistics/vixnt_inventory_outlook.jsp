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
												<strong class="">本月销售订单数</strong><br>    
<a class="myHoverLine" href="javascript:;" ><strong class="font-lg">16单</strong></a>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-2">
									<div class="well well-lg" style="background-color:#4AB1E3;">
										<div class="row">
											<div class="col-md-12 text-center txt-color-white">
												<strong class="">本月销售金额</strong><br>      
<a class="myHoverLine" href="javascript:;" ><strong class="font-lg">5999.00元</strong></a>  
											</div>
										</div>   
									</div>
								</div>
								<div class="col-md-2">
									<div class="well well-lg" style="background-color:#38B7AF;">
										<div class="row"> 
											<div class="col-md-12 text-center txt-color-white">
												<strong class="">本月新客户</strong><br>    
<a class="myHoverLine" href="javascript:;" ><strong class="font-lg">3位</strong></a>    
											</div>
										</div>   
									</div>
								</div>
								<div class="col-md-2">
									<div class="well well-lg" style="background-color:#7074B9;">
										<div class="row">  
											<div class="col-md-12 text-center txt-color-white">
												<strong class="">退货订单数</strong><br>    
<a class="myHoverLine" href="javascript:;" ><strong class="font-lg">2单</strong></a>    
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
												<span class="font-lg txt-color-red padding-5">2单</span>
												<div class="padding-5"></div>
												<strong class="txt-color-greenLight">今日销售订单数</strong>
											</h5>
											<div class="padding-5"></div>
											<p>
											昨日<strong class="txt-color-pink"> 2单</strong> 环比<strong class="txt-color-pink"> <i class="fa fa-arrow-down tmyColorA"></i>10%</strong>
											</p>
										</div>
										<div class="col-xs-3 col-sm-3 text-center" style="border-left: 1px dashed #c0c0c0;padding: 0 10px;">
											<h5>
 												<span class="font-lg txt-color-red padding-5">566.68元</span> 
												<div class="padding-5"></div>
												<strong class="txt-color-greenLight">今日销售金额</strong>
											</h5>
											<div class="padding-5"></div>
											<p>
											昨日<strong class="txt-color-pink"> 33.55元</strong> 环比<strong class="txt-color-pink"> <i class="fa fa-arrow-up tmyColorB }"></i>20%</strong>
											</p>
										</div>
										<div class="col-xs-3 col-sm-3 text-center" style="border-left: 1px dashed #c0c0c0;padding: 0 10px;">
											<h5>
 												<span class="font-lg txt-color-red padding-5">1位</span> 
												<div class="padding-5"></div>
												<strong class="txt-color-greenLight">今日客户数</strong>
											</h5>
											<div class="padding-5"></div>
											<p>
											昨日<strong class="txt-color-pink"> 0位</strong> 环比<strong class="txt-color-pink"> <i class="fa fa-arrow-up tmyColorB }"></i>20%</strong>
											</p>
										</div>
										<div class="col-xs-3 col-sm-3 text-center" style="border-left: 1px dashed #c0c0c0;padding: 0 10px;">
											<h5>
 												<span class="font-lg txt-color-red padding-5">10种</span> 
												<div class="padding-5"></div>
												<strong class="txt-color-greenLight">今日销售产品种类</strong>
											</h5>
											<div class="padding-5"></div>
											<p>
											昨日<strong class="txt-color-pink"> 6种</strong> 环比<strong class="txt-color-pink"> <i class="fa fa-arrow-up tmyColorB }"></i>20%</strong>
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
								<h2>最近30日销售订单趋势</h2>
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
								<h2>最近30日销售金额趋势图</h2>
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
	
	<div class="row">
		<div class="col-sm-12 col-md-12">
			
				<div class="row">
					<div class="col-sm-6 col-md-6">
						<div id="wid-id-4" class="jarviswidget jarviswidget-sortable" data-widget-editbutton="false" role="widget">
							<header>
								<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i></span>
								<h2>产品销售数量TOP10</h2>
							</header>
							<div>
								<div class="widget-body no-padding">
									<div id="dataViewA" style="height: 300px"></div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-sm-6 col-md-6">
						<div id="wid-id-4" class="jarviswidget jarviswidget-sortable" data-widget-editbutton="false" role="widget">
							<header>
								<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i></span>
								<h2>产品销售金额TOP10</h2>
							</header>
							<div>
								<div class="widget-body no-padding">
									<div id="dataViewB" style="height: 300px"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-sm-6 col-md-6">
						<div id="wid-id-4" class="jarviswidget jarviswidget-sortable" data-widget-editbutton="false" role="widget">
							<header>
								<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i></span>
								<h2>客户购买金额TOP10</h2>
							</header>
							<div>
								<div class="widget-body no-padding">
									<div id="dataViewE" style="height: 300px"></div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-sm-6 col-md-6">
						<div id="wid-id-4" class="jarviswidget jarviswidget-sortable" data-widget-editbutton="false" role="widget">
							<header>
								<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i></span>
								<h2>销售人员业绩TOP10</h2>
							</header>
							<div>
								<div class="widget-body no-padding">
									<div id="dataViewF" style="height: 300px"></div>
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
			<h2>销售订单分析列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div  id="brow">
					<form role="search" class="navbar-form navbar-left">
						<div class="form-group">
							客户: <input type="text" value="" class="form-control" id="mytitle" style="width: 130px;" placeholder="">
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
</div>
<script type="text/javascript">
function controlSQLTwo(changeSQL,fromPage){
	$.ajax({  
		url : '${nvix}/nvixnt/vixntMemberManageDataAction!startsWithForListPage.action',
		data: {changeSQL:changeSQL,fromPage:fromPage},  
		cache : false,//<a class="myHoverLine" href="javascript:;" onClick="controlSQLTwo('JaeSqKg{ExistGoodS}','storeStockPageSvy');">
		success : function(html) {
			$("#mainContent").html(html); 
		}
	});
}
function judgeTimeLargeNow(timeStr) {//获取的时间thetime 格式应为  2016-5-28
	var timeStrDate = new Date(Date.parse(timeStr.replace(/-/g,"/")));
	var curDate = new Date();
	if(timeStrDate > curDate){
		return 1;/*return 1 时  alert("请重新选择开始时间，开始时间不能超过今天"); */
	}else{
		return 6;
	}
}
function padleft0(obj) {//补齐两位数的函数 
    return obj.toString().replace(/^[0-9]{1}$/, "0" + obj);  
}
function controlJump() {
	var startTime = $("#startTimeA").val();
	var state = 0;
	if(startTime == null || startTime.length < 3 ){
		layer.alert("请选择时间!");
		state++;
	}
	if(judgeTimeLargeNow(startTime) == 1){
		state++;
		layer.alert("请重新选择时间,时间不能超过今天!");
	}
	if(state == 0){
		var queryTime = startTime;
		var act = "?queryTime="+queryTime;
		loadContent('drp_inventorystatistics','${nvix}/nvixnt/vixntStockViewDataAction!goStockView.action'+act);
	}
}
function controlJumpForclear() {//清空时
	loadContent('drp_inventorystatistics','${nvix}/nvixnt/vixntStockViewDataAction!goStockView.action');
}

function myNumToFixed2(objArr) {//我的把数组保留2位小数返回新数组
	var newArr =[];
	if(Array.isArray(objArr)){
		for(var x=0;x<objArr.length;x++){
			newArr[x]= parseFloat(objArr[x].toFixed(2)); 
		}
	}
    return newArr;  
}
function queryMethod_A(startTime,endTime) {
	$.ajax({    
		url: "${nvix}/nvixnt/vixntStockViewDataAction!stockAnalysisViewA.action", //原型借用url，其他地方有真用这个url
     	type: "POST",
     	data: {startTime:startTime,endTime:endTime,topNum:'10'},
        dataType: "json",
 		success:function(json){
     		var myChart = echarts.init(document.getElementById('dataViewA'));
			var	option = {
			   	    title: {
			   	    	left: 'left',
			   	        text: '',
			   	        subtext: ''
			   	    },
			   		 tooltip : {
		    	        trigger: 'axis',
		    	        formatter: '{b}<br/> <span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:#3EA0D8"></span>{a0} : {c0}<br/>'
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
			   	        data: ['产品数量']
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
			   	    	data:['','','','','','','','','大众1号车轮','x反光镜']
			   	        /* data:json.nameArr */       //参考  data:['','','','','','','','','大众1号车轮','x反光镜']
			   	    }],
			   	    series: [
			   	        {
			   	            name: '产品数量',
			   	            type: 'bar',
			   	         	data: [0,0,0,0,0,0,0,0,111,178],
			   	         	/* data: myNumToFixed2(json.valueArr), */ //参考  	data: [0,0,0,0,0,0,0,0,111,178],
			   	         itemStyle: {
		                        normal: {
		                            color: function(params) {
		                                var colorList = [
		                                  '#B6A2DE' 
		                                ];
		                                return colorList[params.dataIndex]
		                            },
		                        }
		    	            },
		    	            
			   	            label: {
			   	                normal: {
			   	                    show: true,
			   	                    position: 'right',
			   	                    formatter: '{c}'
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
function queryMethod_B(startTime,endTime) {
	$.ajax({    
		url: "${nvix}/nvixnt/vixntStockViewDataAction!stockAnalysisViewB.action", 
     	type: "POST",
     	data: {startTime:startTime,endTime:endTime,topNum:'10'},
        dataType: "json",
 		success:function(json){
     		var myChart = echarts.init(document.getElementById('dataViewB'));
			var	option = {
			   	    title: {
			   	    	left: 'left',
			   	        text: '',
			   	        subtext: ''
			   	    },
			   		 tooltip : {
		    	        trigger: 'axis',
		    	        formatter: '{b}<br/> <span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:#3EA0D8"></span>{a0} : {c0}元<br/>'
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
			   	        data: ['产品金额']
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
			   	     data:['','','','','','','','','大众1号车轮','x反光镜']
			   	        /* data:json.nameArr  */      //参考  data:['联想1号笔记本','雀巢咖啡','OPPOX手机','汉兰眼镜']
			   	    }],
			   	    series: [
			   	        {
			   	            name: '产品金额',
			   	            type: 'bar',
			   	         data: [0,0,0,0,0,0,0,0,311,1278],
			   	         	/* data: myNumToFixed2(json.valueArr), */ //参考  data: [30,78,111,178],
			   	         itemStyle: {
		                        normal: {
		                            color: function(params) {
		                                var colorList = [
		                                  '#4ECFD1' 
		                                ];
		                                return colorList[params.dataIndex]
		                            },
		                        }
		    	            },
		    	            
			   	            label: {
			   	                normal: {
			   	                    show: true,
			   	                    position: 'right',
			   	                    formatter: '{c}元'
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
function queryMethod_E(divId) {
     		var myChart = echarts.init(document.getElementById(divId));
			var	option = {
			   	    title: {
			   	    	left: 'left',
			   	        text: '',
			   	        subtext: ''
			   	    },
			   		 tooltip : {
		    	        trigger: 'axis',
		    	        formatter: '{b}<br/> <span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:#3EA0D8"></span>{a0} : {c0}<br/>'
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
			   	        data: ['客户购买总金额']
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
			   	     data:['','','','','','','','王小四','王小三','王小二']
			   	        /* data:json.nameArr */       //参考  data:['联想1号笔记本','雀巢咖啡','OPPOX手机','汉兰眼镜']
			   	    }],
			   	    series: [
			   	        {
			   	            name: '客户购买总金额',
			   	            type: 'bar',
			   	         data: [0,0,0,0,0,0,0,12,311,1278],
			   	         	/* data: myNumToFixed2(json.valueArr),  *///参考  data: [30,78,111,178],
			   	         itemStyle: {
		                        normal: {
		                            color: function(params) {
		                                var colorList = [
		                                  '#003399' 
		                                ];
		                                return colorList[params.dataIndex]
		                            },
		                        }
		    	            },
		    	            
			   	            label: {
			   	                normal: {
			   	                    show: true,
			   	                    position: 'right',
			   	                    formatter: '{c}元'
			   	                },
			   	                
			   	            }
			   	        }
			   	    ]
			   	};
				myChart.clear();
				myChart.setOption(option);
	 		    $(window).resize(myChart.resize);
}
function queryMethod_F(startTime,endTime) {
	$.ajax({    
		url: "${nvix}/nvixnt/vixntStockViewDataAction!stockAnalysisViewF.action", 
     	type: "POST",
     	data: {startTime:startTime,endTime:endTime,topNum:'10'},
        dataType: "json",
 		success:function(json){
     		var myChart = echarts.init(document.getElementById('dataViewF'));
			var	option = {
			   	    title: {
			   	    	left: 'left',
			   	        text: '',
			   	        subtext: ''
			   	    },
			   		 tooltip : {
		    	        trigger: 'axis',
		    	        formatter: '{b}<br/> <span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:#3EA0D8"></span>{a0} : {c0}元<br/>'
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
			   	        data: ['销售业绩']
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
			   	     data:['','','','','','','','','王小三','王小二']
			   	       /*  data:json.nameArr */       //参考  data:['联想1号笔记本','雀巢咖啡','OPPOX手机','汉兰眼镜']
			   	    }],
			   	    series: [
			   	        {
			   	            name: '销售业绩',
			   	            type: 'bar',
			   	         data: [0,0,0,0,0,0,0,0,3,12],
			   	         	/* data: myNumToFixed2(json.valueArr), */ //参考  data: [30,78,111,178],
			   	         itemStyle: {
		                        normal: {
		                            color: function(params) {
		                                var colorList = [
		                                  '#6BBE30' 
		                                ];
		                                return colorList[params.dataIndex]
		                            },
		                        }
		    	            },
		    	            
			   	            label: {
			   	                normal: {
			   	                    show: true,
			   	                    position: 'right',
			   	                    formatter: '{c}元'
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

function timeRetrieval(startTimeID,endTimeID) {//   timeRetrieval时间检索的方法（开始时间的id,结束时间的id）如果合格就返回  ‘1’
	 var state = 1;
	 var searchStartTime =  $('#'+startTimeID).val();  
	 var searchEndTime   =  $('#'+endTimeID).val();
	 var remind = "请同时选择开始时间和结束时间";
	 	if(searchStartTime.length<=3 && searchEndTime.length>3){
			alert(remind);
			state++;
		}
		if(searchStartTime.length>3 && searchEndTime.length<=3){
			alert(remind);
			state++;
		}
		if(judgeTimeLargeNow(searchStartTime) == 1){
			state++;
			alert("请重新选择开始时间,开始时间不能超过今天!");
		}
		if(judgeTimeLargeNow(searchEndTime) == 1){
			state++;
			alert("请重新选择结束时间,结束时间不能超过今天!");
		}
		var d_1 = searchStartTime;
		var d_2 = searchEndTime;
		var nowtime = new Date();  
        var year = nowtime.getFullYear();  
        var month = padleft0(nowtime.getMonth() + 1);  
        var day = padleft0(nowtime.getDate()); 
		var d_now = year + "-" + month + "-" + day ;
		var e_now = new Date(d_now).getTime();
		var e_1 = new Date(d_1).getTime();
		var e_2 = new Date(d_2).getTime();
		if(e_1>e_2){
			alert('对不起，请重新选择时间段，开始时间要在结束时间之前');
			state++;
		}
		return state;	  
}

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
formatter: '{b}<br/> <span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'+myColors[0]+'"></span>{a0}: {c0}单<br/>'+
'',
 		    	   		axisPointer: {
 		       	        	 type: 'shadow',
 		     	            crossStyle: {
 		     	                color: '#FFFF33'
 		     	            }
 		     	        }
 		    	    },
 		    	    legend: {
 		    	        data:['销售订单']
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
 		    	            smooth:true,
 		    	            data: testArrBarA,   //[211, 344, 466, 555, 666, 799, 921, 1043, 1143, 1133, 1233, 1383],
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

function queryMethodTestBar(DivTestId) {
	var testArrBarA = [1300.01,388.03,0.0,0.0,589.12,203.00];
	var testArrTime = ['08-01','08-02','08-03','08-04','08-05','08-06'];
	var myChart = echarts.init(document.getElementById(DivTestId));
		var myColors = ['#91e8e1', '#434348', '#90ed7d', '#f7a35c', '#8085e9','#FF3366', '#e4d354', '#8085e8', '#8d4653', '#91e8e1','#66FF00'];
			var option = {
 		    	    title : {
 		    	    },
 		    	    tooltip : {
 		    	        trigger: 'axis',
formatter: '{b}<br/> <span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'+myColors[0]+'"></span>{a0}: {c0}元<br/>'+
'',
 		    	   		axisPointer: {
 		       	        	 type: 'shadow',
 		     	            crossStyle: {
 		     	                color: '#FFFF33'
 		     	            }
 		     	        }
 		    	    },
 		    	    legend: {
 		    	        data:['销售金额']
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
 		    	            type : 'value',name: '单位/元'
 		    	        }
 		    	    ],
 		    	    series : [
 		    	        {
 		    	            name:'销售金额',
 		    	            type:'bar',
 		    	            data: testArrBarA,   //[211, 344, 466, 555, 666, 799, 921, 1043, 1143, 1133, 1233, 1383],
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
	queryMethod_A('-Lately-Day{30}','-Lately-Day{30}');
	queryMethod_B('-Lately-Day{30}','-Lately-Day{30}');
	queryMethod_E('dataViewE');
	queryMethod_F('-Lately-Day{30}','-Lately-Day{30}');
	queryMethodTest('dataViewC');
	queryMethodTestBar('dataViewD');
	$('#searchButtonB').click(function(){ //产品入库列表检索
		if(timeRetrieval('startTimeB','endTimeB')==1){
			listGoodsInSqoqmTable.ajax.reload();
		}
	});
	$('#searchButtonC').click(function(){ //产品出库列表检索
		if(timeRetrieval('startTimeC','endTimeC')==1){
			listGoodsOutSaocmTable.ajax.reload();
		}
	});
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
	"title" : "客户",
	"data" : function(data) {
		return "";
	}
	},{
	"title" : "购买次数",   
	"data" : function(data) {
		return ""; 
	}
	},{
	"title" : "购买总金额",
	"data" : function(data) {
		return "";
	}
	},{
	"title" : "最近购买时间",
	"data" : function(data) {
		return "";
	}
	} ]; 																										//下面是原型借用而已
	var listGoodsInSqoqmTable = initDataTable("listGoodsInSqoqmTableId", "${nvix}/nvixnt/vixntStockViewDataAction!goGoodsQueryList.action", listGoodsInSqoqmColumns, function(page, pageSize, orderField, orderBy) {
		var myflag = "In";//标记查入库，还是‘出库’
		myflag = Url.encode(myflag);
		var mytitle = "测试";
		mytitle = Url.encode(mytitle);
		var mynum =  "123456789123";
		var startTime = "2999-01-01";
		var endTime = "2999-01-01";
		startTime = Url.encode(startTime);
		endTime = Url.encode(endTime);
		mynum = Url.encode(mynum);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"mytitle" : mytitle,
		"startTime" : startTime,
		"endTime" : endTime,
		"myflag" : myflag,
		"mynum" : mynum
		};
	});
</script>
