<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<script src="${nvix}/vixntcommon/base/js/plugin/echarts/echarts.min.js"></script>
<style>
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
				<i class="fa fa-lg fa-fw fa-bar-chart-o"></i> 库存管理 <span>> 库存报表 </span><span>> 促销推荐</span>
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
			<div style="float:left;margin-right: 0px;"><span class="font-md padding-5" style="color:#4594E0;">促销推荐</span></div>
			<div class="" style="float:left;margin-left:15px;">
			<span class="font-xs padding-5" style="color:#8A8B8C;">数据跟新时间 : 2018-04-10</span>
			</div>
		</div>
	</div>			
				
	<div class="row">
		<div class="col-xs-4 col-sm-4 text-left" style="font-weight:700;height:50px;line-height: 45px;">
			<span class="font-sm txt-color-blueDark padding-5">推荐概览</span>
		</div>
	</div>
				</div>
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
	<div class="row">
		<div class="col-xs-2 col-sm-2 text-center" >
			<div style="border-bottom:1px solid #E2E2E2;height:50px;line-height: 50px;font-weight:500;" class="mydivBorderUpperLeftRight">
			<span class="font-md txt-color-blueDark padding-5">促销SKU数量</span>
			</div>
			<div style="height:70px;line-height: 100px;" class="mydivBorderLeftRight">
			 <span class="font-xl padding-5" style="color:#009BFF;">50个</span>
			</div>
			 <div style="height:35px;line-height: 30px;" class="mydivBorderLowerLeftRight">
				<!-- <span class="font-sm padding-5" style="color:#009BFF;">较上一周期 !18.18%</span> -->
			 </div>
		</div>
		<div class="col-xs-2 col-sm-2 text-center" style="border-left: 1px dashed #FBFBFB;padding: 0 10px;">
			<div style="border-bottom:1px solid #E2E2E2;height:50px;line-height: 50px;font-weight:500;" class="mydivBorderUpperLeftRight">
			<span class="font-md txt-color-blueDark padding-5">促销商品数量</span>
			</div>
			<div style="height:70px;line-height: 100px;" class="mydivBorderLeftRight">
			 <span class="font-xl padding-5" style="color:#009BFF;">560</span>
			</div>
			 <div style="height:35px;line-height: 30px;" class="mydivBorderLowerLeftRight">
				<!-- <span class="font-sm padding-5" style="color:#009BFF;">较上一周期 !48.18%</span> -->
			 </div>
		</div>
		<div class="col-xs-2 col-sm-2 text-center" style="border-left: 1px dashed #FBFBFB;padding: 0 10px;">
			<div style="border-bottom:1px solid #E2E2E2;height:50px;line-height: 50px;font-weight:500;" class="mydivBorderUpperLeftRight">
			<span class="font-md txt-color-blueDark padding-5">占全部临期商品比例</span>
			</div>
			<div style="height:70px;line-height: 100px;" class="mydivBorderLeftRight">
			 <span class="font-xl padding-5" style="color:#009BFF;">56%</span>
			</div>
			 <div style="height:35px;line-height: 30px;" class="mydivBorderLowerLeftRight">
				<!-- <span class="font-sm padding-5" style="color:#009BFF;">较上一周期 !48.18%</span> -->
			 </div>
		</div>
		<div class="col-xs-2 col-sm-2 text-center" style="border-left: 1px dashed #FBFBFB;padding: 0 10px;">
			<div style="border-bottom:1px solid #E2E2E2;height:50px;line-height: 50px;font-weight:500;" class="mydivBorderUpperLeftRight">
			<span class="font-md txt-color-blueDark padding-5">剩余保质期平均天数</span>
			</div>
			<div style="height:70px;line-height: 100px;" class="mydivBorderLeftRight">
			 <span class="font-xl padding-5" style="color:#009BFF;">154天</span>
			</div>
			 <div style="height:35px;line-height: 30px;" class="mydivBorderLowerLeftRight">
				<!-- <span class="font-sm padding-5" style="color:#009BFF;">较上一周期 !38.18%</span> -->
			 </div>
		</div>
		<div class="col-xs-2 col-sm-2 text-center" style="border-left: 1px dashed #FBFBFB;padding: 0 10px;">
			<div style="border-bottom:1px solid #E2E2E2;height:50px;line-height: 50px;font-weight:500;" class="mydivBorderUpperLeftRight">
			<span class="font-md txt-color-blueDark padding-5">库存可售卖平均天数</span>
			</div>
			<div style="height:70px;line-height: 100px;" class="mydivBorderLeftRight">
			 <span class="font-xl padding-5" style="color:#009BFF;">788天</span>
			</div>
			 <div style="height:35px;line-height: 30px;" class="mydivBorderLowerLeftRight">
				<!-- <span class="font-sm padding-5" style="color:#009BFF;">较上一周期 !38.18%</span> -->
			 </div>
		</div>
		<div class="col-xs-2 col-sm-2 text-center" style="border-left: 1px dashed #FBFBFB;padding: 0 10px;">
			<div style="border-bottom:1px solid #E2E2E2;height:50px;line-height: 50px;font-weight:500;" class="mydivBorderUpperLeftRight">
			<span class="font-md txt-color-blueDark padding-5">呆滞报废可能比例</span>
			</div>
			<div style="height:70px;line-height: 100px;" class="mydivBorderLeftRight">
			 <span class="font-xl padding-5" style="color:#009BFF;">78%</span>
			</div>
			 <div style="height:35px;line-height: 30px;" class="mydivBorderLowerLeftRight">
				<!-- <span class="font-sm padding-5" style="color:#009BFF;">较上一周期 !38.18%</span> -->
			 </div>
		</div>
	</div>
	
	<div class="row">
		<div class="col-xs-6 col-sm-6" >
		<br>
			<div id="dataViewC" style="height: 300px"></div>
		</div>
		<div class="col-xs-6 col-sm-6" >
		<br>
			<div id="dataViewD" style="height: 300px"></div>
		</div>
	</div>
	
				</div>
			</div>
		</div>
	</div>
</div>
					
</div>
<script type="text/javascript">
function myQueryDataViewC(DivTestId) {
	 var numberArr  = [10,30,60];
		var stringArr  = ['广州优选3号配送衷心','厦门惠美冷库','南京雨花食品仓库'];
		var arraySeries = [];  
		var map ={}; 
			for(var i=0 ;i<numberArr.length;i++){
				map.value = numberArr[i];
			    map.name = stringArr[i]; 
				arraySeries[i]=map;
			    map ={};
			 }
		var arrayLegend = []; 
			 for(var i=0 ;i<stringArr.length;i++){
				arrayLegend[i]=stringArr[i]; 
			 }
		var noData ='';
			 if(numberArr.length == 0){
				noData = '暂无数据';
			 }
		var myChart2 = echarts.init(document.getElementById(''+DivTestId));
		var myColours = ['#CC3399','#FF3399','#7900FF','#BD514B'];
		var	option2 = {
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c}% "
	    },
	    series: [
	        {
	            name:'仓库分布比例',
	            type:'pie',
	            radius: ['40%', '55%'],
	            label: {
	                normal: {
	                    formatter: '{b}:  {c}% ',
	                }
	            },
	            data:arraySeries
	        }
	    ]
	};
		    myChart2.clear();
		    myChart2.setOption(option2);
		    $(window).resize(myChart2.resize);
}
function myQueryDataViewD(DivTestId) {
	 var numberArr  = [10,20,30,40];
		var stringArr  = ['母婴','家具食品','休闲食品','其它'];
		var arraySeries = [];  
		var map ={}; 
			for(var i=0 ;i<numberArr.length;i++){
				map.value = numberArr[i];
			    map.name = stringArr[i]; 
				arraySeries[i]=map;
			    map ={};
			 }
		var arrayLegend = []; 
			 for(var i=0 ;i<stringArr.length;i++){
				arrayLegend[i]=stringArr[i]; 
			 }
		var noData ='';
			 if(numberArr.length == 0){
				noData = '暂无数据';
			 }
		var myChart2 = echarts.init(document.getElementById(''+DivTestId));
		var myColours = ['#CC3399','#FF3399','#7900FF','#BD514B'];
		var	option2 = {
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c}% "
	    },
	    series: [
	        {
	            name:'品类分布比例',
	            type:'pie',
	            radius: ['40%', '55%'],
	            label: {
	                normal: {
	                    formatter: '{b}:  {c}% ',
	                }
	            },
	            data:arraySeries
	        }
	    ]
	};
		    myChart2.clear();
		    myChart2.setOption(option2);
		    $(window).resize(myChart2.resize);
}
$(document).ready(function() {
	myQueryDataViewC('dataViewC');
	myQueryDataViewD('dataViewD');
});
</script>
