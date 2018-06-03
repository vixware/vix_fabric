<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<script src="${nvix}/vixntcommon/base/js/plugin/echarts/echarts.min.js"></script>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa-fw fa fa-home"></i> 门店管理 <span>> 门店数据统计 </span> <span>> 门店销售统计</span>
			</h1>
		</div>
	</div>
	
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i></span>
			<h2>最近30日商品销售数量Top10</h2>
		</header>
		<div class="row">
			<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div>
					<div class="widget-body no-padding">
						<div id="dataViewC" style="height: 400px"></div>
					</div>
				</div>
			</article>
		</div>	
	</div>
	
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i></span>
			<h2>最近30日商品销售金额Top10</h2>
		</header>
		<div class="row">
			<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div>
					<div class="widget-body no-padding">
						<div id="dataViewD" style="height: 400px"></div>
					</div>
				</div>
			</article>
		</div>	
	</div>
	
</div>
<script type="text/javascript">
function myNumToFixed2(objArr) {//我的把数组保留2位小数返回新数组
	var newArr =[];
	if(Array.isArray(objArr)){
		for(var x=0;x<objArr.length;x++){
			newArr[x]= parseFloat(objArr[x].toFixed(2)); 
		}
	}
    return newArr;  
}
function queryMethod_C(startTime,endTime) {
	$.ajax({    
		url: "${nvix}/nvixnt/vixntStockViewDataAction!saleAnalysisViewC.action", 
     	type: "POST",
     	data: {startTime:startTime,endTime:endTime,topNum:'10'},
        dataType: "json",
 		success:function(json){
     		var myChart = echarts.init(document.getElementById('dataViewC'));
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
			   	        data: ['商品销售数量']
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
			   	        data:json.nameArr       //参考  data:['联想1号笔记本','雀巢咖啡','OPPOX手机','汉兰眼镜']
			   	    }],
			   	    series: [
			   	        {
			   	            name: '商品销售数量',
			   	            type: 'bar',
			   	         	data: myNumToFixed2(json.valueArr), //参考  data: [30,78,111,178],
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
function queryMethod_D(startTime,endTime) {
	$.ajax({    
		url: "${nvix}/nvixnt/vixntStockViewDataAction!saleAnalysisViewD.action", 
     	type: "POST",
     	data: {startTime:startTime,endTime:endTime,topNum:'10'},
        dataType: "json",
 		success:function(json){
     		var myChart = echarts.init(document.getElementById('dataViewD'));
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
			   	        data: ['商品销售金额']
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
			   	        data:json.nameArr       //参考  data:['联想1号笔记本','雀巢咖啡','OPPOX手机','汉兰眼镜']
			   	    }],
			   	    series: [
			   	        {
			   	            name: '商品销售金额',
			   	            type: 'bar',
			   	         	data: myNumToFixed2(json.valueArr), //参考  data: [30,78,111,178],
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

$(document).ready(function() {
	pageSetUp();
	queryMethod_C('-Lately-Day{30}','-Lately-Day{30}');
	queryMethod_D('-Lately-Day{30}','-Lately-Day{30}');
})
</script>