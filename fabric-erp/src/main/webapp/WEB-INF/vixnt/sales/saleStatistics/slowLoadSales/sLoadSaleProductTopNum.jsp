<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<script src="${nvix}/vixntcommon/base/js/plugin/echarts/echarts.min.js"></script>
<div id="dataViewA" style="height: 300px"></div>   <!-- 产品销售数量TOP10 -->
<script type="text/javascript">
function sloadSellProductsNumTop() {  
	$.ajax({    
		url: '${nvix}/nvixnt/nvixntSalesStatisticsAction!slowQuerySalesTopView.action', 
		type: "POST",
		data: {queryTime:'-Lately-Day{30}',controlSQL:'qvSellProductsNumTop',topNum:'10'},
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
			   	    	data:json.nameArr  //参考  data:['','','','','','','','','大众1号车轮','x反光镜']
			   	    }],
			   	    series: [
			   	        {
			   	            name: '产品数量',
			   	            type: 'bar',
			   	         	data: json.valueArr,  //参考  	data: [0,0,0,0,0,0,0,0,111,178],
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
    $(document).ready(function() {
    	sloadSellProductsNumTop();
    });
</script>
