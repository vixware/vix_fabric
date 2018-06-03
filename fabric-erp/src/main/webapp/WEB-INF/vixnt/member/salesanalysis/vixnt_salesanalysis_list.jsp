<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
<script src="${nvix}/vixntcommon/base/js/plugin/echarts/echarts.min.js"></script>
<style>
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
				<i class="fa fa-list-alt fa-bar-chart-o"></i> 智能分析 <span>> 会员消费分析 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-5 col-md-5 col-lg-8"></div>
	</div>
	 
	 <div class="row">
				   <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<div class="col-xs-4 col-sm-4 text-center" style="width:20%">
								<div class="well padding-10 " style="height:110px;background-color:#EF9178;">
									<div class="text-center">  <!-- （Cash 现金  ，WeChat 微信 ，Alipay 支付宝 ，BankCard银行卡 ,MemberCard 会员卡也就是余额 ，All 全部） -->
										    <h5> <!-- suidaConsumptionMode 消费方式  suidaConMode   --> <!--  consumptionAnalysis消费分析页面 取  conAnaPageSagj -->
<a class="myHoverLine" href="javascript:;" onClick="controlSQLWdMethod('suidaConMode{All{Today}}','conAnaPageSagj');"><span class="font-lg txt-color-red padding-5" id="d01num">¥0.00</span></a>
												<div class="padding-5"></div>
												<strong class="txt-color-greenLight" id="d01numStr">今日消费总金额</strong>
											</h5>
											<div class="padding-5"></div>
									</div>
								</div>
							</div>
							
							<div class="col-xs-2 col-sm-2 text-center" style="width:20%">
								<div class="well padding-10 " style="height:110px;background-color:#4AB1E3;">
									<div class="text-center">
										<h5>
<a class="myHoverLine" href="javascript:;" onClick="controlSQLWdMethod('suidaConMode{Cash{Today}}','conAnaPageSagj');"><span class="font-lg txt-color-red padding-5" id="d02num">¥0.00</span></a>
											<div class="padding-5"></div>
											<strong class="txt-color-greenLight" id="d02numStr">今日现金消费</strong>
										</h5>
										<div class="padding-5"></div>
									</div>
								</div>
							</div>
							
							<div class="col-xs-2 col-sm-2 text-center" style="width:20%">
								<div class="well padding-10 " style="height:110px;background-color:#38B7AF;">
									<div class="text-center">
										<h5>
<a class="myHoverLine" href="javascript:;" onClick="controlSQLWdMethod('suidaConMode{WeChat{Today}}','conAnaPageSagj');"><span class="font-lg txt-color-red padding-5" id="d03num">¥0.00</span></a>
											<div class="padding-5"></div>
											<strong class="txt-color-greenLight" id="d03numStr">今日微信消费</strong>
										</h5>
										<div class="padding-5"></div>
									</div>
								</div>
							</div>
							
							<div class="col-xs-2 col-sm-2 text-center" style="width:20%">
								<div class="well padding-10 " style="height:110px;background-color:#CA95FF;">
									<div class="text-center">
										<h5>
<a class="myHoverLine" href="javascript:;" onClick="controlSQLWdMethod('suidaConMode{Alipay{Today}}','conAnaPageSagj');"><span class="font-lg txt-color-red padding-5"  id="d04num">¥0.00</span></a>
											<div class="padding-5"></div>
											<strong class="txt-color-greenLight" id="d04numStr">今日支付宝消费</strong>
										</h5>
										<div class="padding-5"></div>
									</div>
								</div>
							</div>
							
							<div class="col-xs-2 col-sm-2 text-center" style="width:20%">
								<div class="well padding-10 " style="height:110px;background-color:#DFBD50;">
									<div class="text-center">
										<h5>
<a class="myHoverLine" href="javascript:;" onClick="controlSQLWdMethod('suidaConMode{Other{Today}}','conAnaPageSagj');"><span class="font-lg txt-color-red padding-5"  id="d05num">¥0.00</span></a>
											<div class="padding-5"></div>
											<strong class="txt-color-greenLight"  id="d05numStr">今日其他消费</strong>
										</h5>
										<div class="padding-5"></div>
									</div>
								</div>
							</div>
							
						</div>
				   </div>
	
	
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i></span>
			<h2>最近30日会员消费方式分析</h2>
		</header>
		<div class="row">
			<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div>
					<div class="widget-body no-padding">
						<div id="dataViewA" style="height: 400px"></div>
					</div>
				</div>
			</article>
		</div>	
	</div>
	
<div class="row">
		<div class="col-sm-12 col-md-12">
			
				<div class="row">
					<div class="col-sm-6 col-md-6">
						<div id="wid-id-4" class="jarviswidget jarviswidget-sortable" data-widget-editbutton="false" role="widget">
							<header>
								<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i></span>
								<h2>最近30日会员消费排行Top10</h2>
							</header>
							<div>
								<div class="widget-body no-padding">
									<div id="dataViewB" style="height: 400px"></div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-sm-6 col-md-6">
						<div id="wid-id-4" class="jarviswidget jarviswidget-sortable" data-widget-editbutton="false" role="widget">
							<header>
								<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i></span>
								<h2>最近30日商品销量排行Top10</h2>
							</header>
							<div>
								<div class="widget-body no-padding">
									<div id="dataViewC" style="height: 400px"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			
		</div>
	</div>
	
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i></span>
			<h2>最近30日订单数</h2>
		</header>
		<div class="row">
			<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div>
					<div class="widget-body no-padding">
						<div id="dataViewF" style="height: 400px"></div>
					</div>
				</div>
			</article>
		</div>
	</div>
	
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i></span>
			<h2>最近30日客单价视图</h2>
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
	
	
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-table"></i></span>
			<h2>客户消费列表</h2>
		</header>
		<div>
			<div class="widget-body no-padding">
				<div  id="brow">
					<form role="search" class="navbar-form navbar-left">
						<div class="form-group">
							姓名: <input type="text" value="" class="form-control" id="mytitle" style="width: 130px;" placeholder="请输入姓名">
						</div>
						<div class="form-group">
							手机号: <input type="text" value="" class="form-control" id="mymobilePhone" style="width: 130px;" placeholder="请输入手机号">
						</div>
						<div class="input-group">
							<input placeholder="开始时间" style="width: 130px;" id="startTimeB" name="startCreateTime"  data-prompt-position="topLeft" class="form-control " onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> <span class="input-group-addon"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'startCreateTime'});"><i class="fa fa-calendar"></i></span>
						</div>
						<div class="input-group">
							<input placeholder="结束时间" style="width: 130px;" id="endTimeB" name="endCreateTime"  data-prompt-position="topLeft" class="form-control " onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text" /> <span class="input-group-addon"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',el:'endCreateTime'});"><i class="fa fa-calendar"></i></span>
						</div>
						
					
						<button onclick="" type="button" class="btn btn-info" id="searchButtonB">
							<i class="glyphicon glyphicon-search"></i> 检索
						</button>
						<button onclick="$('#mytitle').val('');$('#mymobilePhone').val('');timeClear('brow');stockRecordsTable.ajax.reload();" type="button" class="btn btn-default">
							<i class="glyphicon glyphicon-repeat"></i> 清空
						</button>
					</form>
				</div>
				<table id="stockRecordsTableId" class="table table-striped table-bordered table-hover" width="100%">
				</table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
function controlSQLWdMethod(condition,returnPage) {//传递查询条件给列表
	newHtml(condition,returnPage);
}
function newHtml(controlSQL,returnPage) {//超链接页面;  
	$.ajax({
		url : '${nvix}/nvixnt/vixntMemberManageDataAction!goMemberListNewHtml.action',
		data: {controlSQL:controlSQL,returnPage:returnPage},  
		cache : false,
		success : function(html) {
			$("#mainContent").html(html); 
		}
	});
};
function padleft0(obj) {//补齐两位数的函数 
    return obj.toString().replace(/^[0-9]{1}$/, "0" + obj);  
}
function timeClear(divrow){ //清空时间
		 $("#"+divrow+" input[name='startCreateTime']").each(function(){
			    $(this).val('');
		});
		 $("#"+divrow+" input[name='endCreateTime']").each(function(){
			    $(this).val('');
		});
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
		url: "${nvix}/nvixnt/vixntSalesAnalysisAction!consumptionAnalysis.action",  
     	type: "POST",
     	data: {startTime:startTime,endTime:endTime},
        dataType: "json",
 		success:function(json){
 			$("#d01num").text("¥"+(json.Total).toFixed(2));   
 			$("#d01numStr").text(json.todayStr+'消费总金额');
 			$("#d02num").text("¥"+(json.Cash).toFixed(2));    
 			$("#d02numStr").text(json.todayStr+'现金消费');
 			$("#d03num").text("¥"+(json.WeChat).toFixed(2));  
 			$("#d03numStr").text(json.todayStr+'微信消费');
 			$("#d04num").text("¥"+(json.Alipay).toFixed(2));  
 			$("#d04numStr").text(json.todayStr+'支付宝消费');
 			$("#d05num").text("¥"+(json.Other).toFixed(2));   
 			$("#d05numStr").text(json.todayStr+'其他消费');
	}});
}
function queryMethod_B(startTime,endTime) {
	$.ajax({    
		url: "${nvix}/nvixnt/vixntSalesAnalysisAction!consumptionAnalysisViewA.action",  
     	type: "POST",
     	data: {startTime:startTime,endTime:endTime},
        dataType: "json",
 		success:function(json){
 			var myChart = echarts.init(document.getElementById('dataViewA'));
 			var myColors = ['#7cb5ec', '#434348', '#90ed7d', '#f7a35c', '#8085e9','#FF3366', '#e4d354', '#8085e8', '#8d4653', '#91e8e1','#66FF00'];
					var option = {
		 		    	    title : {
		 		    	       // text: '最近30日会员消费方式分析',
		 		    	    },
		 		    	    tooltip : {
		 		    	        trigger: 'axis',
formatter: '{b}<br/> <span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'+myColors[0]+'"></span>{a0}: {c0}元<br/>'+
	'<span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'+myColors[1]+'"></span>{a1}: {c1}元<br/>'+ 
	 '<span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'+myColors[2]+'"></span>{a2}: {c2}元<br/>'+ 
	'<span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'+myColors[3]+'"></span>{a3}: {c3}元<br/>'+ 
	'<span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'+myColors[4]+'"></span>{a4}: {c4}元<br/>'+ 
	'<span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'+myColors[5]+'"></span>{a5}: {c5}元<br/>'+
		'',
		 		    	   		axisPointer: {
		 		       	        	 type: 'shadow',
		 		     	            crossStyle: {
		 		     	                color: '#FFFF33'
		 		     	            }
		 		     	        }
		 		    	    },
		 		    	    legend: {
		 		    	        data:['现金消费','微信消费','支付宝消费','银行卡消费','其他消费']
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
		 		    	            data : json.timeStr//["001", "001", "001", "001", "001", "001", "001", "001", "001", "001", "001", "001"]
		 		    	        }
		 		    	    ],
		 		    	    yAxis : [
		 		    	        {
		 		    	            type : 'value',name: '消费/元'
		 		    	        }
		 		    	    ],
		 		    	    series : [
		 		    	        {
		 		    	            name:'现金消费',
		 		    	            type:'bar',
		 		    	            stack: '消费总金额',
		 		    	            data: myNumToFixed2(json.Cash),   //[211, 344, 466, 555, 666, 799, 921, 1043, 1143, 1133, 1233, 1383],
		 		    	            itemStyle :{
		 		    	            	normal:{
		 		    	            		color:''+myColors[0] 
		 		    	            	}
		 		    	            } 
		 		    	        },
		 		    	        {
		 		    	            name:'微信消费',
		 		    	            type:'bar',
		 		    	            stack: '消费总金额',
		 		    	            data: myNumToFixed2(json.WeChat),   //[211, 344, 466, 555, 666, 799, 921, 1043, 1143, 1133, 1233, 1383],
		 		    	            itemStyle :{
		 		    	            	normal:{
		 		    	            		color:''+myColors[1] 
		 		    	            	}
		 		    	            } 
		 		    	        },
		 		    	        {
		 		    	            name:'支付宝消费',
		 		    	            type:'bar',
		 		    	            stack: '消费总金额',
		 		    	            data: myNumToFixed2(json.Alipay),    //[211, 344, 466, 555, 666, 799, 921, 1043, 1143, 1133, 1233, 1383],
		 		    	            itemStyle :{
		 		    	            	normal:{
		 		    	            		color:''+myColors[2]
		 		    	            	}
		 		    	            } 
		 		    	        },
		 		    	       {
		 		    	            name:'银行卡消费',
		 		    	            type:'bar',
		 		    	            stack: '消费总金额',
		 		    	            data: myNumToFixed2(json.BankCard),    //[211, 344, 1466, 555, 666, 799, 921, 1043, 1999, 1133, 1233, 1383],
		 		    	            itemStyle :{
		 		    	            	normal:{
		 		    	            		color:''+myColors[3]
		 		    	            	}
		 		    	            } 
		 		    	        },
		 		    	        {
		 		    	            name:'其他消费',
		 		    	            type:'bar',
		 		    	            stack: '消费总金额',
		 		    	            data: myNumToFixed2(json.Other),    //  [211, 344, 466, 555, 666, 799, 921, 1043, 1143, 1133, 1233, 1383],
		 		    	            itemStyle :{
		 		    	            	normal:{
		 		    	            		color:''+myColors[4] 
		 		    	            	}
		 		    	            } 
		 		    	        },
		 			 				    { 
		 			    	        	name: '消费总金额', 
		 			    	        	type: 'bar', 
		 			    	        	stack: '消费总金额', 
		 			    	        	label: { 
		 				    	        	normal: { 
		 					    	        	show: true, 
		 					    	        	position: 'insideBottom', 
		 					    	        	formatter:'{c}', 
		 					    	        	textStyle:{ color:''+myColors[5] } 
		 				    	        	}
		 			    	        	}, 
		 				    	        	itemStyle:{ 
		 				    	        	normal:{ 
		 				    	        	color:'rgba(128, 128, 128, 0)' 
		 				    	        	} 
		 				    	        	}, 
		 				    	        	data: myNumToFixed2(json.Total)    // [211, 344, 466, 555, 666, 799, 921, 1043, 1143, 1133, 1233, 1383]
		 			    	        	},
		 		    	    ]
		 		    	};
						myChart.clear();
						myChart.setOption(option);
			 		    $(window).resize(myChart.resize);
	}});
}		
function queryMethod_C(startTime,endTime) {
	$.ajax({    
		url: "${nvix}/nvixnt/vixntSalesAnalysisAction!consumptionAnalysisViewB.action",  
     	type: "POST",
     	data: {startTime:startTime,endTime:endTime,topNum:'10'},
        dataType: "json",
 		success:function(json){
     		var myChart2 = echarts.init(document.getElementById('dataViewB'));
     		var myColours = ['#CC3399','#FF3399','#7900FF','#BD514B'];
			var	option2 = {
			   	    title: {
			   	    	left: 'left',
			   	        //text: '最近30日会员消费排行Top10',
			   	        subtext: ''
			   	    },
			   		 tooltip : {
		    	        trigger: 'axis',
		    	        formatter: '{b}<br/> <span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'+myColours[0]+'"></span>{a0} : {c0}元<br/>'
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
			   	        data: ['会员消费金额']
		    	  		,left: 'center'
			   	    },
			   	    grid: {
			   	        left: '3%',
			   	        right: '6%',
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
			   	            name: '会员消费金额',
			   	            type: 'bar',
			   	         	data: myNumToFixed2(json.valueArr), //参考  data: [30,78,111,178],
			   	         itemStyle :{
			 	            	normal:{
			 	            		color:''+myColours[0] 
			 	            	}
			 	            } ,
		    	            
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
				myChart2.clear();
				myChart2.setOption(option2);
	 		    $(window).resize(myChart2.resize);
	}});
}		
function queryMethod_D(startTime,endTime) {
	$.ajax({    
		url: "${nvix}/nvixnt/vixntSalesAnalysisAction!consumptionAnalysisViewC.action",  
     	type: "POST",
     	data: {startTime:startTime,endTime:endTime,topNum:'10'},
        dataType: "json",
 		success:function(json){
     		var myChart = echarts.init(document.getElementById('dataViewC'));
     		var myColours = ['#629EA9','#FF3399','#7900FF','#BD514B'];
			var	option = {
			   	    title: {
			   	    	left: 'left',
			   	        //text: '最近30日会员消费排行Top10',
			   	        subtext: ''
			   	    },
			   		 tooltip : {
		    	        trigger: 'axis',
		    	        formatter: '{b}<br/> <span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'+myColours[0]+'"></span>{a0} : {c0}件<br/>'
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
			   	        right: '6%',
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
			   	         itemStyle :{
			 	            	normal:{
			 	            		color:''+myColours[0] 
			 	            	}
			 	            } ,
		    	            
			   	            label: {
			   	                normal: {
			   	                    show: true,
			   	                    position: 'right',
			   	                    formatter: '{c}件'
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
function queryMethod_E(startTime,endTime) {
	$.ajax({     
		url: "${nvix}/nvixnt/vixntSalesAnalysisAction!consumptionAnalysisViewD.action", 
     	type: "POST",
     	data: {startTime:startTime,endTime:endTime},
        dataType: "json",
 		success:function(json){
 			var myChart = echarts.init(document.getElementById('dataViewD'));
 			var myColors = [ '#f7a35c', '#8085e9','#f15c80', '#e4d354', '#8085e8', '#8d4653', '#91e8e1'];
					var option = {
		 		    	    title : {
		 		    	       // text: '最近30日会员消费方式分析',
		 		    	    },
		 		    	    tooltip : {
		 		    	        trigger: 'axis',
formatter: '{b}<br/> <span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'+myColors[0]+'"></span>{a0}: {c0}元<br/>'+
 '<span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'+myColors[1]+'"></span>{a1}: {c1}人 <br/>'+ 
 '<span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'+myColors[2]+'"></span>{a2}: {c2}元 <br/>'+ 
		'',
		 		    	   		axisPointer: {
		 		       	        	 type: 'shadow',
		 		     	            crossStyle: {
		 		     	                color: '#FFFF33'
		 		     	            }
		 		     	        }
		 		    	    },
		 		    	    legend: {
		 		    	        data:['消费金额','会员','客单价']
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
		 		    	            data : json.timeStr //["001", "001", "001", "001", "001", "001", "001", "001", "001", "001", "001", "001"]
		 		    	        }
		 		    	    ],
		 		    	    yAxis : [
										{
										    type : 'value',name:'金额/元'
										},
										{
											type:'value',name: '会员/人'
												 ,axisLabel: { //前面是y轴屏蔽小数  ,axisLabel                 
										             formatter: function (value, index) {
										                	var str = value.toString()
										                	  if( str.indexOf('.') >= 0 ){
										                		return null;
										                	}else{
										                		return value;
										                	} 
										             }                
										        }
										}
		 		    	    ],
		 		    	    series : [
		 		    	        {
		 		    	            name:'消费金额',
		 		    	            type:'bar',
		 		    	            data: myNumToFixed2(json.memberOrderTotalMoneyArr),   //[211, 344, 466, 555, 666, 799, 921, 1043, 1143, 1133, 1233, 1383],
		 		    	            itemStyle :{
		 		    	            	normal:{
		 		    	            		color:''+myColors[0] 
		 		    	            	}
		 		    	            } 
		 		    	        },
		 		    	       {
		 		    	            name:'会员',
		 		    	            type:'line',   
		 		    	            smooth:true, 
		 		    	            yAxisIndex: 1,
		 		    	            data:json.orderMemberIdDistinctArr,   //[211, 344, 466, 555, 666, 799, 921, 1043, 1143, 1133, 1233, 1383],
		 		    	            itemStyle :{
		 		    	            	normal:{
		 		    	            		color:''+myColors[1] 
		 		    	            	}
		 		    	            } 
		 		    	        },
		 		    	       {
		 		    	            name:'客单价',
		 		    	            type:'line',  
		 		    	            smooth:true, 
		 		    	            data:json.perTicketSalesArr,   //[211, 344, 466, 555, 666, 799, 921, 1043, 1143, 1133, 1233, 1383],
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
	}});
}
function queryMethod_F(startTime,endTime) {
	$.ajax({
		url: "${nvix}/nvixnt/vixntSalesAnalysisAction!consumptionAnalysisViewF.action", 
     	type: "POST",
     	data: {startTime:startTime,endTime:endTime},
        dataType: "json",
 		success:function(json){
 			var myChart = echarts.init(document.getElementById('dataViewF'));
 			var myColors = ['#193A77', '#434348', '#90ed7d', '#f7a35c', '#8085e9','#FF3366', '#e4d354', '#8085e8', '#8d4653', '#91e8e1','#66FF00'];
					var option = {
		 		    	    title : {
		 		    	       // text: '最近30日会员消费方式分析',
		 		    	    },
		 		    	    tooltip : {
		 		    	        trigger: 'axis',
formatter: '{b}<br/> <span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'+myColors[0]+'"></span>{a0}: {c0}笔<br/>'+
		'',
		 		    	   		axisPointer: {
		 		       	        	 type: 'shadow',
		 		     	            crossStyle: {
		 		     	                color: '#FFFF33'
		 		     	            }
		 		     	        }
		 		    	    },
		 		    	    legend: {
		 		    	        data:['订单数']
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
		 		    	            data : json.timeStr //["001", "001", "001", "001", "001", "001", "001", "001", "001", "001", "001", "001"]
		 		    	        }
		 		    	    ],
		 		    	    yAxis : [
		 		    	        {
		 		    	            type : 'value',name: '订单数/笔'
		 		    	            	,axisLabel: { //前面是y轴屏蔽小数  ,axisLabel                 
		    	            	             formatter: function (value, index) {
		    	            	                	var str = value.toString()
		    	            	                	  if( str.indexOf('.') >= 0 ){
		    	            	                		return null;
		    	            	                	}else{
		    	            	                		return value;
		    	            	                	} 
			            		             }                
			            		        }
		 		    	        }
		 		    	    ],
		 		    	    series : [
		 		    	        {
		 		    	            name:'订单数',
		 		    	            type:'line',
		 		    	            smooth:true, 
		 		    	            data:json.memberOrderPassengersArr,   //[211, 344, 466, 555, 666, 799, 921, 1043, 1143, 1133, 1233, 1383],
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
	}});
}
	$(document).ready(function() {
		pageSetUp();
		// PAGE RELATED SCRIPTS
		queryMethod_A('Today','Today');
		queryMethod_B('Day30','Day30');
		queryMethod_C('Day30','Day30');
		queryMethod_D('Day30','Day30');
		queryMethod_E('Day30','Day30');
		queryMethod_F('Day30','Day30');
		 $('#searchButtonA').click(function(){ //数据块检索
			 var state = 1;
			 var searchStartTime =  $('#startTimeA').val();  
			 var searchEndTime   =  $('#endTimeA').val();
			 	if(searchStartTime.length<=3 || searchStartTime=='开始时间'){
					alert('请选择开始时间');
					state++;
				}
				if(searchEndTime.length<=3 || searchEndTime=='结束时间'){
					alert('请选择结束时间');
					state++;
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
				if(state == 1){
					queryMethod_A(searchStartTime,searchEndTime);
				}	  
		});
		 $('#searchButtonB').click(function(){ //客户消费明细检索
			 var state = 1;
			 var searchStartTime =  $('#startTimeB').val();  
			 var searchEndTime   =  $('#endTimeB').val();
			 	if(searchStartTime.length<=3 || searchStartTime=='开始时间'){
					alert('请选择开始时间');
					state++;
				}
				if(searchEndTime.length<=3 || searchEndTime=='结束时间'){
					alert('请选择结束时间');
					state++;
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
				if(state == 1){
					stockRecordsTable.ajax.reload();
				}	  
		});
		
	})

</script>
<script type="text/javascript">
	pageSetUp();
	var stockRecordsColumns = [ {
	"title" : "编号",
	"width" : "8%",
	"data" : function(data) {
		return "";
	}
	}, {
	"title" : "姓名",
	"data" : function(data) {
		return data.name;
	}
	},{
	"title" : "手机号",   
	"data" : function(data) {
		return data.mobilePhone;   
	}
	},{
	"title" : "消费总金额(元)",
	"data" : function(data) {
		return data.selfExtendStringField1;
	}
	},{
	"title" : "消费次数",
	"data" : function(data) {
		return data.selfExtendStringField2;
	}
	},{
	"title" : "消费均价(元)",
	"data" : function(data) {
		return data.selfExtendStringField3;
	}
	},{
	"title" : "最近消费金额",
	"data" : function(data) {
		return data.selfExtendStringField5;
	}
	},{
	"title" : "最近消费时间",    
	"data" : function(data) {
		return data.selfExtendStringField4;
	}
	} ]; 
	var stockRecordsTable = initDataTable("stockRecordsTableId", "${nvix}/nvixnt/vixntSalesAnalysisAction!goSingleList.action", stockRecordsColumns, function(page, pageSize, orderField, orderBy) {
		var mytitle = $("#mytitle").val();
		mytitle = Url.encode(mytitle);
		var mymobilePhone = $("#mymobilePhone").val();
		var startTime = $("#startTimeB").val();
		var endTime = $("#endTimeB").val();
		startTime = Url.encode(startTime);
		endTime = Url.encode(endTime);
		mymobilePhone = Url.encode(mymobilePhone);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"mytitle" : mytitle,
		"startTime" : startTime,
		"endTime" : endTime,
		"mymobilePhone" : mymobilePhone
		};
	});
</script>


