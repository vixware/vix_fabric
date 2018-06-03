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
	.mytxt-color-wathet { /* 目标按钮浅蓝色 */
	background-color:#428BCA !important;
	}
</style>	
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-lg fa-fw fa-bar-chart-o"></i> 库存管理 <span>> 库存报表 </span><span>> 进销存概览</span>
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
			<div style="float:left;margin-right: 0px;"><span class="font-md padding-5" style="color:#4594E0;">进销存概览</span></div>
			<div class="" style="float:left;margin-left:15px;">
			<a style="border-radius: 35px;" href="javascript:void(0);" class="btn bg-color-blueLight mytxt-color-wathet  txt-color-white btn-sm" id="SituationIn"  onclick="clickSituation('SituationIn');" >收货情况</a>
			<a style="border-radius: 35px;margin-left:8px;" href="javascript:void(0);" class="btn bg-color-blueLight  txt-color-white btn-sm" id="SituationOnt"  onclick="clickSituation('SituationOnt');"  >发货情况</a>
			<a style="border-radius: 35px;margin-left:8px;" href="javascript:void(0);" class="btn bg-color-blueLight  txt-color-white btn-sm"  id="SituationStore"  onclick="clickSituation('SituationStore');"  >库存情况</a>
			</div>
		</div>
		<div class="pull-right padding-5" style="margin-right:9px;">
             <form class="form-inline" >
                 <div class="input-group">
                   <label class="control-label">时间:</label>
                      <div class="input-group" >
					<input placeholder="时间" value="${todayStr }" style="width: 150px;" id="startTimeC" name="startCreateTime"  data-prompt-position="topLeft" class="form-control " onClick="WdatePicker({dateFmt:'yyyy-MM-dd',onpicked: transmitCondition});"  type="text" /> 
						<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
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
					     /* data:{
					    	 "value" : [
					    		{"id" : "all","word" : "所有仓库"},
					    		{"id" : "alla","word" : "仓库1"},
								{"id" : "out","word" : "仓库2"},
								{"id" : "in","word" : "仓库3"}
					   		]
					     } */ 
					     data:${jsonBsSuggestJava}
					 });
					 /**选择供应商时**/
					 	$("input#sltSupplierB").on("onSetSelectValue", function (event, result) {
						    var sID = result.id;
						    var fdStart = sID.indexOf("all!");
						    if(sID=='all!'){
						    	sID="";
						    	qCondition.channelDistributorID=sID;
						    	qCondition.warehouseID= "";
						    }else if(fdStart == 0){
						    	var arr = sID.split("!");
						    	sID = arr[1];
						    	qCondition.channelDistributorID=sID;
						    	qCondition.warehouseID= "";
						    }else{
						    	qCondition.warehouseID=sID;
						    	qCondition.channelDistributorID= "";
						    }
							transmitCondition();
						});
					 	testdataBsSuggest.attr("data-id", "all!"+${qChannelDistributorID} ).val("所有仓库");
					 </script>
	</div>			
				
	<div class="row">
		<div class="col-xs-4 col-sm-4 text-left" style="font-weight:700;height:50px;line-height: 45px;">
			<span class="font-sm txt-color-blueDark padding-5">进销存情况</span><i class="fa-fw fa fa-question-circle"></i>
		</div>
	</div>
				</div>
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
	<div class="row">
		<div class="col-xs-3 col-sm-3 text-center" >
			<div style="border-bottom:1px solid #E2E2E2;height:50px;line-height: 50px;font-weight:500;" class="mydivBorderUpperLeftRight">
			<span class="font-md txt-color-blueDark padding-5">收货商品数量</span>
			</div>
			<div style="height:70px;line-height: 100px;" class="mydivBorderLeftRight">
			 <span class="font-xl padding-5" style="color:#009BFF;" id="blockDataA">0.00</span>
			</div>
			 <div style="height:35px;line-height: 30px;" class="mydivBorderLowerLeftRight">
				<!-- <span class="font-sm padding-5" style="color:#009BFF;">较上一周期 !18.18%</span> -->
			 </div>
		</div>
		<div class="col-xs-3 col-sm-3 text-center" style="border-left: 1px dashed #FBFBFB;padding: 0 10px;">
			<div style="border-bottom:1px solid #E2E2E2;height:50px;line-height: 50px;font-weight:500;" class="mydivBorderUpperLeftRight">
			<span class="font-md txt-color-blueDark padding-5">收货SKU数</span>
			</div>
			<div style="height:70px;line-height: 100px;" class="mydivBorderLeftRight">
			 <span class="font-xl padding-5" style="color:#009BFF;" id="blockDataB">0个</span>
			</div>
			 <div style="height:35px;line-height: 30px;" class="mydivBorderLowerLeftRight">
				<!-- <span class="font-sm padding-5" style="color:#009BFF;">较上一周期 !48.18%</span> -->
			 </div>
		</div>
		<div class="col-xs-3 col-sm-3 text-center" style="border-left: 1px dashed #FBFBFB;padding: 0 10px;">
			<div style="border-bottom:1px solid #E2E2E2;height:50px;line-height: 50px;font-weight:500;" class="mydivBorderUpperLeftRight">
			<span class="font-md txt-color-blueDark padding-5">收货订单数</span>
			</div>
			<div style="height:70px;line-height: 100px;" class="mydivBorderLeftRight">
			 <span class="font-xl padding-5" style="color:#009BFF;" id="blockDataC">0个</span>
			</div>
			 <div style="height:35px;line-height: 30px;" class="mydivBorderLowerLeftRight">
				<!-- <span class="font-sm padding-5" style="color:#009BFF;">较上一周期 !48.18%</span> -->
			 </div>
		</div>
		<div class="col-xs-3 col-sm-3 text-center" style="border-left: 1px dashed #FBFBFB;padding: 0 10px;">
			<div style="border-bottom:1px solid #E2E2E2;height:50px;line-height: 50px;font-weight:500;" class="mydivBorderUpperLeftRight">
			<span class="font-md txt-color-blueDark padding-5">收货平均时长</span>
			</div>
			<div style="height:70px;line-height: 100px;" class="mydivBorderLeftRight">
			 <span class="font-xl padding-5" style="color:#009BFF;" id="blockDataD">0小时0分钟</span>
			</div>
			 <div style="height:35px;line-height: 30px;" class="mydivBorderLowerLeftRight">
				<!-- <span class="font-sm padding-5" style="color:#009BFF;">较上一周期 !38.18%</span> -->
			 </div>
		</div>
	</div>
	
	<div class="row">
		<div class="col-xs-6 col-sm-6" >
		<br>
			<div id="dataViewA" style="height: 300px"></div>
		</div>
		<div class="col-xs-6 col-sm-6" >
		<br>
			<div id="dataViewB" style="height: 300px"></div>
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
		 <input type="hidden" value="${qbChannelDistributorID}" id="querychannelDistributorID">
		 <input type="hidden" value="" id="querywarehouseID">  
</div>
<script type="text/javascript">
/** 把页面查询条件封装成js对象 **/
function queryCondition(time,channelDistributorID,warehouseID){
    this.time = time;
    this.channelDistributorID = channelDistributorID;
    this.warehouseID = warehouseID;
}

var qCondition = new queryCondition( $('#startTimeC').val() , $('#querychannelDistributorID').val(),"");//不能删除，后面用
/** 传递最终查询参数 **/
function transmitCondition(){
	$('#querychannelDistributorID').val(qCondition.channelDistributorID);
	$('#querywarehouseID').val(qCondition.warehouseID);
	qCondition.time = $('#startTimeC').val();
	if(qCondition.time.length<=3){
		alert('请选择时间!');
		return false;
	}
	queryBlockAbc();
	queryDataView();
	queryTopDataViewC();
}
/** 点击按钮 **/
function clickSituation(id){
	 $('#SituationIn').removeClass('mytxt-color-wathet');
	 $('#SituationOnt').removeClass('mytxt-color-wathet');
	 $('#SituationStore').removeClass('mytxt-color-wathet');
     $('#'+id).addClass('mytxt-color-wathet');
}
/** 查询:收货商品数量,收货SKU数,收货订单数,收货平均时长 **/
function queryBlockAbc() {
	$.ajax({
		url: "${nvix}/nvixnt/vixntStockSerachDataAction!queryBlockAbc.action",
		type : "POST",
		data : {queryTime:qCondition.time,channelDistributorID:qCondition.channelDistributorID,warehouseID:qCondition.warehouseID},
		dataType : "json",
		success : function(json) {
			$("#blockDataA").text((json.inStoreQuantity).toFixed(2));
			$("#blockDataB").text((json.inSKUquantity).toFixed(0)+"个" );
			$("#blockDataC").text((json.orderQuantity).toFixed(0)+"个" );
			$("#blockDataD").text((json.inTimeLengthHour).toFixed(0)+"小时"+(json.inTimeLengthMinute).toFixed(0)+"分钟" );
		}
	});
}
/**查询'进销存概览'的收货商品总数折线图和收货SKU数折线图 **/
function queryDataView() {
	$.ajax({
		url: "${nvix}/nvixnt/vixntStockSerachDataAction!queryViewBrokenLineData.action",
		type : "POST",
		data : {queryTime:qCondition.time,channelDistributorID:qCondition.channelDistributorID,warehouseID:qCondition.warehouseID},
		dataType : "json",
		success : function(json) {
			var arrDataA = json.inStoreQuantityValueArr;//[1,3,4,5,2,3,3];
			var arrTime = json.timeArr;//['08-01','08-02','08-03','08-04','08-05','08-06','08-07'];
			var myChart = echarts.init(document.getElementById('dataViewA'));
		    var myColors = ['#7cb5ec', '#A9CF44'];
			var option = {
 		    	    title : {
 		    	       // text: '最近30日会员消费方式分析',
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
 		    	        data:['收货商品总数']
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
 		    	            data : arrTime 
 		    	        }
 		    	    ],
 		    	    yAxis : [
 		    	        {
 		    	            type : 'value',name: ''
 		    	        }
 		    	    ],
 		    	    series : [
 		    	        {
 		    	            name:'收货商品总数',
 		    	            type:'line',
							smooth:true,
 		    	            data: arrDataA,  
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
	 		    ///////
	 		    var arrDataB = json.inSKUquantityValueArr;
	 		   var myChartB = echarts.init(document.getElementById('dataViewB'));
	 		  var optionB = {
	 		    	    title : {
	 		    	       // text: '最近30日会员消费方式分析',
	 		    	    },
	 		    	    tooltip : {
	 		    	        trigger: 'axis',
	formatter: '{b}<br/> <span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'+myColors[1]+'"></span>{a0}: {c0}个<br/>'+
	'',
	 		    	   		axisPointer: {
	 		       	        	 type: 'shadow',
	 		     	            crossStyle: {
	 		     	                color: '#FFFF33'
	 		     	            }
	 		     	        }
	 		    	    },
	 		    	    legend: {
	 		    	        data:['收货SKU']
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
	 		    	            data : arrTime //["001", "2018-01-01"]
	 		    	        }
	 		    	    ],
	 		    	    yAxis : [
	 		    	        {
	 		    	            type : 'value',name: ''
	 		    	        }
	 		    	    ],
	 		    	    series : [
	 		    	        {
	 		    	            name:'收货SKU',
	 		    	            type:'line',
								smooth:true,
	 		    	            data: arrDataB,
	 		    	            itemStyle :{
	 		    	            	normal:{
	 		    	            		color:''+myColors[1] 
	 		    	            	}
	 		    	            } 
	 		    	        }
	 		    	    ]
	 		    	};
	 			 myChartB.clear();
				 myChartB.setOption(optionB);
	 		     $(window).resize(myChartB.resize);
		}
	});
}
/** 查询金额比例top柱图和top饼图 **/
function queryTopDataViewC() {
	$.ajax({
		url: "${nvix}/nvixnt/vixntStockSerachDataAction!queryViewMoneyTopData.action",
		type : "POST",
		data : {queryTime:qCondition.time,channelDistributorID:qCondition.channelDistributorID,warehouseID:qCondition.warehouseID},
		dataType : "json",
		success : function(json) {
/////////////////////////////////////视图分界线//////////////////////////////////////////////////////////
			var testNameArr = json.nameArrStr;//['','','','','','','','七匹狼服饰','一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三','balabala童鞋'];
			var testMoneyArr = json.valueArr;//['','','','','','','','1802.21','3334.13','12000.11'];
			var myChart = echarts.init(document.getElementById('dataViewC'));
			var	option = {
				   	 title: {
				   		 left: 'left',
				         text: '收货SKU金额Top10',
				         subtext: '时间:'+qCondition.time,
				         textStyle: {      
			                   fontSize: 12
			             },
			             subtextStyle: {      
			                   fontSize: 12
			             },
				     },
			   		 tooltip : {
		    	        trigger: 'axis',
		    	        formatter: function (params) { 
		    	        	var res='<div><p>收货Top10  金额占比</p><p>SKU：'+params[0].name+'</p></div>' 
		    	        	for(var i=0;i<params.length;i++){
		    	        		res+='<p>占比:&nbsp;'+params[i].data+'%</p>'+'<p>金额:&nbsp;'+testMoneyArr[(params[i].dataIndex)]+'元</p>'+'<p>名称: '+testNameArr[(params[i].dataIndex)]+'</p>'
		    	        	}
		    	        	return res;
			    	    },
		    	        axisPointer: {
		    	        	 type: 'shadow',
		    	            crossStyle: {
		    	                color: '#FFFF33'
		    	            }
		    	        }
		    	    },
			   	    legend: {
			   	        data: ['收货TOP10 SKU']
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
			   	        data: json.nameArr//['','','','','','','','1893246','3828206','2828001']
			   	    }],
			   	    series: [
			   	        {
			   	            name: '收货TOP10 SKU',
			   	            type: 'bar',
			   	            data: json.valueArrProportion,//[0,0,0,0,0,0,0,5,15,18],
			   	         itemStyle: {
		                        normal: {
		                            color: function(params) {
		                                var colorList = [
		                                  '#0D62A3' 
		                                ];
		                                return colorList[params.dataIndex]
		                            },
		                        }
		    	            },
			   	            label: {
			   	                normal: {
			   	                    show: true,
			   	                    position: 'right',
			   	                    formatter: '{c}%'
			   	                },
			   	                
			   	            }
			   	        }
			   	    ]
			   	};
				myChart.clear();
				myChart.setOption(option);
				$(window).resize(myChart.resize);
////////////////////////////////视图分界线////////////////////////////////////////////////////////////////////		
				 var testMoneyArrB = [json.doubleA,json.doubleB];//['0.00','0.00'];
			     var numberArrB  = [json.pieProportionTopTen,json.pieProportionOther];//[0.00,0.00];
				 var stringArrB  = ['收货TOP10SKU','其他SKU'];
				 var arraySeriesB = [];  
				 var customPieColoursMy = ['#0D62A3', '#67A5D8'];
				 var mapB ={}; 
					for(var i=0 ;i<numberArrB.length;i++){
						mapB.value = numberArrB[i];
					    mapB.name = stringArrB[i]; 
						arraySeriesB[i]=mapB;
					    mapB ={};
					 }
				var arrayLegendB = []; 
					 for(var i=0 ;i<stringArrB.length;i++){
						arrayLegendB[i]=stringArrB[i]; 
					 }
				var noDataB ='';
					 if(numberArrB.length == 0){
						noDataB = '暂无数据';
					 }
				var myChartB = echarts.init(document.getElementById('dataViewD'));
				var	optionB = {
				color:customPieColoursMy,
			    tooltip : {
			        trigger: 'item',
			        formatter: function (params) {    
			        	var res='<p>金额占比</p><p>名称:'+params.data.name+'</p>'+'<p>占比:&nbsp;'+params.data.value+'%</p>'+'<p>金额:&nbsp;'+Number(testMoneyArrB[(params.dataIndex)]).toFixed(2)+'元</p>';  
			        	return res;
		    	    }
			    },
			    series: [
			        {
			            name:'收货 SKU 金额占比',
			            type:'pie',
			            radius: ['40%', '55%'],
			            label: {
			                normal: {
			                    formatter: '{b}:  {c}% ',
			                }
			            },
			            data:arraySeriesB
			        }
			    ]
			};
				    myChartB.clear();
				    myChartB.setOption(optionB);
				    $(window).resize(myChartB.resize);
///////////////////////视图分界线//////////////////////////////////////////////////////////////////////////
		}
	});
}
$(document).ready(function() {
	transmitCondition();
});
</script>
