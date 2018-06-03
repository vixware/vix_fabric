<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<script src="${nvix}/vixntcommon/base/js/plugin/echarts/echarts.min.js"></script>
<div id="dataViewDPurchase" style="height: 300px"></div>
<script type="text/javascript">
			var yuanOrMillionYuan = "万元";
			var myChart = echarts.init(document.getElementById('dataViewDPurchase'));
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
 		    	        data:['当日已完成采购订单总金额']
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
 		    	            data : ${timeStr}//json.timeStr //["001", "001", "001", "001", "001", "001", "001", "001", "001", "001", "001", "001"]
 		    	        }
 		    	    ],
 		    	    yAxis : [
 		    	        {
 		    	            type : 'value',name: '单位/'+yuanOrMillionYuan+''
 		    	        }
 		    	    ],
 		    	    series : [
 		    	        {
 		    	            name:'当日已完成采购订单总金额',
 		    	            type:'bar',
 		    	            data: myNumArrToMillionYuan(${valueArr}),//[211, 344, 466, 555, 666, 799, 921, 1043, 1143, 1133, 1233, 1383],
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
</script>