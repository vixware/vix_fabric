<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<script src="${nvix}/vixntcommon/base/js/plugin/echarts/echarts.min.js"></script>
<div id="dataViewBProducts" style="height: 300px"></div>  <!--  产品销售金额TOP10 -->
<script type="text/javascript">
function sloadSellProductsMoneyTop() {  
	$.ajax({    
		url: '${nvix}/nvixnt/nvixntSalesStatisticsAction!slowQuerySalesTopView.action', 
		type: "POST",
		data: {queryTime:'-Lately-Day{30}',controlSQL:'qvSellProductsMoneyTop',topNum:'10'},
	    dataType: "json",
		success:function(json){
     		var myChart = echarts.init(document.getElementById('dataViewBProducts'));
			var	option = {
			   	    title: {
			   	    	left: 'left',
			   	        text: '',
			   	        subtext: ''
			   	    },
			   		 tooltip : {
		    	        trigger: 'axis',
		    	        formatter: '{b}<br/> <span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:#3EA0D8"></span>{a0} : {c0}万元<br/>'
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
			   	     	data:json.nameArr  //参考  data:['','','','','','','','','大众1号车轮','x反光镜']
			   	    }],
			   	    series: [
			   	        {
			   	            name: '产品金额',
			   	            type: 'bar',
				   	        data: myNumArrToMillionYuan(json.valueArr),//参考  data: [30,78,111,178],  
				   	        itemStyle: {
		                        normal: {
		                            color: function(params) {
		                                var colorList = [
		                                  '#AAD144' 
		                                ];
		                                return colorList[params.dataIndex]
		                            },
		                        }
		    	            },
		    	            
			   	            label: {
			   	                normal: {
			   	                    show: true,
			   	                    position: 'right',
			   	                    formatter: '{c}万元'
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
    	sloadSellProductsMoneyTop();
    });
</script>
