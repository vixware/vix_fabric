<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<script src="${nvix}/vixntcommon/base/js/plugin/echarts/echarts.min.js"></script>
<div id="dataViewCPurchase" style="height: 300px"></div>
<script type="text/javascript">
		var myChart = echarts.init(document.getElementById('dataViewCPurchase'));
		var myColors = ['#7cb5ec', '#434348', '#90ed7d', '#f7a35c', '#8085e9','#FF3366', '#e4d354', '#8085e8', '#8d4653', '#91e8e1','#66FF00'];
			var option = {
 		    	    title : {
 		    	       // text: '最近30日会员消费方式分析',
 		    	    },
 		    	    tooltip : {
 		    	        trigger: 'axis',
formatter: '{b}<br/> <span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'+myColors[0]+'"></span>{a0}: {c0}单<br/>'+
'<span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'+myColors[1]+'"></span>{a1}: {c1}单<br/>'+ 
'<span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'+myColors[2]+'"></span>{a2}: {c2}单<br/>'+ 
'',
 		    	   		axisPointer: {
 		       	        	 type: 'shadow',
 		     	            crossStyle: {
 		     	                color: '#FFFF33'
 		     	            }
 		     	        }
 		    	    },
 		    	    legend: {
 		    	        data:['采购计划','在途采购订单','已完成采购订单']
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
 		    	            type : 'value',name: '单位/单'
 		    	        }
 		    	    ],
 		    	    series : [
 		    	        {
 		    	            name:'采购计划',
 		    	            type:'line',
 		    	            smooth:true,
 		    	            data: ${valueArr},//json.valueArr,   //[211, 344, 466, 555, 666, 799, 921, 1043, 1143, 1133, 1233, 1383],
 		    	            itemStyle :{
 		    	            	normal:{
 		    	            		color:''+myColors[0] 
 		    	            	}
 		    	            } 
 		    	        },
 		    	        {
 		    	            name:'在途采购订单',
 		    	            type:'line',
 		    	            smooth:true,
 		    	            data: ${valueArrOnPassage},//json.valueArrOnPassage,   //[211, 344, 466, 555, 666, 799, 921, 1043, 1143, 1133, 1233, 1383],
 		    	            itemStyle :{
 		    	            	normal:{
 		    	            		color:''+myColors[1] 
 		    	            	}
 		    	            } 
 		    	        },
 		    	        {
 		    	            name:'已完成采购订单',
 		    	            type:'line',
 		    	            smooth:true,
 		    	            data: ${valueArrCompleted},//json.valueArrCompleted,    //[211, 344, 466, 555, 666, 799, 921, 1043, 1143, 1133, 1233, 1383],
 		    	            itemStyle :{
 		    	            	normal:{
 		    	            		color:''+myColors[2]
 		    	            	}
 		    	            } 
 		    	        }
 		    	    ]
 		    	};
				myChart.clear();
				myChart.setOption(option);
	 		    $(window).resize(myChart.resize);
</script>
