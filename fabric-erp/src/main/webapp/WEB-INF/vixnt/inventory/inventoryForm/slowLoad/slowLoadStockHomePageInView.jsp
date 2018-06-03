<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<script src="${nvix}/vixntcommon/base/js/plugin/echarts/echarts.min.js"></script>
<div id="slowLoadResultViewIn" style="height: 300px"></div>
<script type="text/javascript">
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
function slowLoadResultViewIn(DivTestId,chancevar) {
	$.ajax({    
		url: "${nvix}/nvixnt/vixntStockQueryHomePageAction!queryDataViewStockInOut.action",
	 	type: "POST",
	 	data: {queryTime:'-Lately-Day{30}',inOrOutStock:'in'},
	    dataType: "json",
		success:function(json){
		  var valueArrNumberVar = json.valueArrNumber;//数量数组
		  var valueArrMoneyVar = myNumArrToMillionYuan(json.valueArrMoney);//金额数组  
		  var timeArrVar = json.timeStr;//时间数组
		  var myColors = ['#549FF0', '#A9CF44', '#90ed7d'];//颜色数组1
		  if(chancevar=='2'){
			  myColors = ['#FC9B43', '#44C2BE', '#44C2BE'];//颜色数组2
		  }
		  var myChart = echarts.init(document.getElementById(DivTestId));
		  var option = {
		    	    title : {
		    	    },
		    	    tooltip : {
 		    	        trigger: 'axis',
formatter: '{b}<br/> <span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'+myColors[1]+'"></span>{a0}: {c0}<br/>'+
'<span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'+myColors[0]+'"></span>{a1}: {c1}万元<br/>'+ 
'',
 		    	   		axisPointer: {
 		       	        	 type: 'shadow',
 		     	            crossStyle: {
 		     	                color: '#FFFF33'
 		     	            }
 		     	        }
 		    	    },
		    	    legend: {
		    	        data:['数量','金额']
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
		    	            data : timeArrVar //["001", "001"]
		    	        }
		    	    ],
		    	    yAxis : [
		    	        {
		    	            type : 'value',name: '数量'
		    	        }
		    	        ,{type : 'value',name: '金额/万元'}
		    	    ],
		    	    series : [
		    	        {
		    	            name:'数量',
		    	            type:'bar',
		    	            data: valueArrNumberVar,   //[211, 344],
		    	          	yAxisIndex: 0,
		    	            itemStyle :{
		    	            	normal:{
		    	            		color:''+myColors[1] 
		    	            	}
		    	            } 
		    	        },
		    	       {
		    	            name:'金额',
		    	            type:'bar',
		    	            data: valueArrMoneyVar,   //[211, 344],
		    	            yAxisIndex: 1,
		    	            itemStyle :{
		    	            	normal:{
		    	            		color:''+myColors[0] 
		    	            	}
		    	            } 
		    	        }
		    	    ]
		    	};
		  	/** 下面是用js对数据块中今日,昨日,环比...等进行显示html操作     开始 **/
		    var ytdayInNum = Number( valueArrNumberVar[(valueArrNumberVar.length-2)]  ).toFixed(2);
		    $("#ytdayInNum").html(ytdayInNum);//昨日入库-物料总数  
		    var todayInNum = Number( valueArrNumberVar[(valueArrNumberVar.length-1)]  ).toFixed(2);
		    $("#todayInNum").html(todayInNum);//今日入库-物料总数
		    var perNum = getMomStr(todayInNum,ytdayInNum);//环比百分数
			   if(perNum<0.0){
				   $("#iclasspertodayInNum").html("<i class='fa fa-arrow-down tmyColorA '></i>"+Number( (-(perNum))  ).toFixed(2)+"%");  
			   }else{
				   $("#iclasspertodayInNum").html("<i class='fa fa-arrow-up tmyColorB '></i>"+Number( perNum  ).toFixed(2)+"%");  
			   }
			/** 下面是用js对数据块中今日,昨日,环比...等进行显示html操作     /结束 **/
		    myChart.clear();
			myChart.setOption(option);
		    $(window).resize(myChart.resize);
	}});
}
function getMomStr(thisPeriodData,lastPeriodData) {
	var momStr = 0.00;
	if(lastPeriodData==0.0 && thisPeriodData>0.0){
		return 100.00;
	}else if(lastPeriodData==0.0 && thisPeriodData<0.0){
		return -100.00;
	}else if(lastPeriodData==0.0 && thisPeriodData==0.0){
		return 0.00;
	}else{
		var num= ((thisPeriodData-lastPeriodData)/lastPeriodData)*100;
		momStr =   Number( num  ).toFixed(2);//保留两位小数
	}
	return momStr; 
}
$(document).ready(function() {
	slowLoadResultViewIn('slowLoadResultViewIn','1');
})
</script>
