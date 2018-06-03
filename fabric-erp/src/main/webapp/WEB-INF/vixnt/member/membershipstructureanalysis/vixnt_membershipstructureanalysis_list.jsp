<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/vixntcommon/page/taglibs.jsp"%>
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
				<i class="fa fa-list-alt fa-bar-chart-o"></i> 智能分析 <span>> 客户分析 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-5 col-md-5 col-lg-8"></div>
	</div>

	<div class="row">
							<div class="col-sm-3">
								<div class="well padding-10 " style="height:110px;background-color:#EF9178;">
									<div class="text-center">
										 <h5>     <!-- CustomerAnalysisPage客户分析  用‘cusAnaPage’代替         -->
<a class="myHoverLine" href="javascript:;" onClick="controlSQLWdMethod('suiaAMACp{MemberAll}','cusAnaPage');"><span class="font-lg txt-color-red padding-5" id="d01num">0人</span></a>
											<div class="padding-5"></div>
											<strong class="txt-color-greenLight">会员总数</strong>
										 </h5>
										 <div class="padding-5"></div>
									</div>
								</div>
							</div>
							
							<div class="col-sm-3">
								<div class="well padding-10 " style="height:110px;background-color:#4AB1E3;">
									<div class="text-center">
										 <h5>  <!-- suiaAMACp 是  AllMemberAllClip 的变化  所有的会员，所有的会员卡   客户分析页面中查询‘所有会员人数超链接’ 和‘所有会员卡超链接’ 其中suia代表随机数 -->
<a class="myHoverLine" href="javascript:;" onClick="controlSQLWdMethod('suiaAMACp{ClipAll}','cusAnaPage');"><span class="font-lg txt-color-red padding-5" id="d02num">0张</span></a>
											<div class="padding-5"></div>
											<strong class="txt-color-greenLight">会员卡总数</strong>
										</h5>
										<div class="padding-5"></div>
									</div>
								</div>
							</div>
							
							<div class="col-sm-3">
								<div class="well padding-10 " style="height:110px;background-color:#38B7AF;">
									<div class="text-center">
										 <h5>    <!-- CustomerConsumption 客户消费  CusConSuib -->
<a class="myHoverLine" href="javascript:;" onClick="controlSQLWdMethod('suibCusConSuib{CusConSuib}','cusAnaPage');"><span class="font-lg txt-color-red padding-5" id="d03num">0.00元</span></a>
											<div class="padding-5"></div>
											<strong class="txt-color-greenLight">会员消费总金额</strong>
										</h5>
										<div class="padding-5"></div>
									</div>
								</div>
							</div>
							
							<div class="col-sm-3">
								<div class="well padding-10 " style="height:110px;background-color:#CA95FF;"> 
									<div class="text-center">
										 <h5>  <!--  suicRemainderMoney  会员卡总余额 -->
<a class="myHoverLine" href="javascript:;" onClick="controlSQLWdMethod('suicRemainderMoney{suic}','cusAnaPage');"><span class="font-lg txt-color-red padding-5" id="d04num">0.00元</span></a>
											<div class="padding-5"></div>
											<strong class="txt-color-greenLight">会员卡总余额</strong>
										</h5>
										<div class="padding-5"></div>
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
								<h2>会员等级分布</h2>
							</header>
							<div>
								<div class="widget-body no-padding">
									<div id="dataViewA" style="height: 400px"></div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-sm-6 col-md-6">
						<div id="wid-id-4" class="jarviswidget jarviswidget-sortable" data-widget-editbutton="false" role="widget">
							<header>
								<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i></span>
								<h2>会员卡类型分布</h2>
							</header>
							<div>
								<div class="widget-body no-padding">
									<div id="dataViewB" style="height: 400px"></div>
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
			<h2>最近30日客流量视图</h2>
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
</div>
<script type="text/javascript">
// DO NOT REMOVE : GLOBAL FUNCTIONS!
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
function myNumToFixed2(objArr) {//我的把数组保留2位小数返回新数组
			var newArr =[];
			if(Array.isArray(objArr)){
				for(var x=0;x<objArr.length;x++){
					newArr[x]= parseFloat(objArr[x].toFixed(2)); 
				}
			}
		    return newArr;  
		}
function queryMethod_A() {
	$.ajax({    
		url: "${nvix}/nvixnt/vixntMembershipStructureAnalysisAction!memberAnalysisJsonB.action",  
		data: {controlSQL:'PieViewCardType'},//查询： 客户分析>会员卡类型分布  | PieView饼图视图|CardType会员卡类型
     	type: "POST",
        dataType: "json",
 		success:function(json){
 			var numberArr  =  json.numberResult;//获得 数字
 			var stringArr  =  json.stringResult;//获得 名称
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
     		var myChart2 = echarts.init(document.getElementById('dataViewB'));
     		var myColours = ['#EFCDB2','#4C974A','#DAD94B','#3060A2','#F894D4','#7D2D12','#91D5E4','#99FF00','#C0C0C0','#CC00FF','#303030'];// 会员卡类型分布 专用颜色数组
			var	option2 = {
				title : {
	   			        text: '',
	   			        x:'center',
 		   			  	subtext: noData,
			        subtextStyle:{  //副标题样式
			            fontSize: 16,
			        	color: '#FF0033'
			        } 
	   			},
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c}张 ({d}%)"
			    },
			    color:myColours,
			    legend: {
			        orient: 'horizontal',
			        top: 'top',
			        data: arrayLegend//参考['储值卡','计次卡','储值计次卡']
			    },
			    series : [
			        {
			            name: '会员卡类型',
			            type: 'pie',
			            radius : '55%',
			            center: ['50%', '60%'],
			            data:arraySeries,  //参考 [{value:987, name:'储值卡'},{value:310, name:'计次卡'},{value:234, name:'储值计次卡'}],
			            itemStyle: {
			                emphasis: {
			                    shadowBlur: 10,
			                    shadowOffsetX: 0,
			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
			                }
			            }
			        }
			    ]
			};
				myChart2.clear();
				myChart2.setOption(option2);
	 		    $(window).resize(myChart2.resize);
	}});
}
function queryMethod_B() {
	$.ajax({    
		url: "${nvix}/nvixnt/vixntMembershipStructureAnalysisAction!memberAnalysisJsonB.action",  
		data: {controlSQL:'PieViewMemberLevel'},//查询： 客户分析>会员等级分布 | PieView饼图视图| |PieViewMemberLevel
     	type: "POST",
        dataType: "json",
 		success:function(json){
 			var numberArr  =  json.numberResult;//获得 数字
 			var stringArr  =  json.stringResult;//获得 名称
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
     		var myChart2 = echarts.init(document.getElementById('dataViewA'));
     		var myColours = ['#CC3399','#FF3399','#7900FF','#BD514B'];
			var	option2 = {
				title : {
	   			        text: '',
	   			        x:'center',
 		   			  	subtext: noData,
			        subtextStyle:{  //副标题样式
			            fontSize: 16,
			        	color: '#FF0033'
			        } 
	   			},
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c}人 ({d}%)"
			    },
			    legend: {
			        orient: 'horizontal',
			        top: 'top',
			        data: arrayLegend//参考['储值卡','计次卡','储值计次卡']
			    },
			    series : [
			        {
			            name: '会员等级',
			            type: 'pie',
			            radius : '55%',
			            center: ['50%', '60%'],
			            data:arraySeries,  //参考 [{value:987, name:'储值卡'},{value:310, name:'计次卡'},{value:234, name:'储值计次卡'}],
			            itemStyle: {
			                emphasis: {
			                    shadowBlur: 10,
			                    shadowOffsetX: 0,
			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
			                }
			            }
			        }
			    ]
			};
				myChart2.clear();
				myChart2.setOption(option2);
	 		    $(window).resize(myChart2.resize);
	}});
}
function queryMethod_C(startTime,endTime) {
	$.ajax({     
		url: "${nvix}/nvixnt/vixntMembershipStructureAnalysisAction!memberAnalysisJsonC.action",  
     	type: "POST",
     	data: {startTime:startTime,endTime:endTime},
        dataType: "json",
 		success:function(json){
 			var myChart = echarts.init(document.getElementById('dataViewC'));
 			var myColors = ['#193A77', '#434348', '#90ed7d', '#f7a35c', '#8085e9','#FF3366', '#e4d354', '#8085e8', '#8d4653', '#91e8e1','#66FF00'];
					var option = {
		 		    	    title : {
		 		    	       // text: '最近30日会员消费方式分析',
		 		    	    },
		 		    	    tooltip : {
		 		    	        trigger: 'axis',
formatter: '{b}<br/> <span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'+myColors[0]+'"></span>{a0}: {c0}次<br/>'+
		'',
		 		    	   		axisPointer: {
		 		       	        	 type: 'shadow',
		 		     	            crossStyle: {
		 		     	                color: '#FFFF33'
		 		     	            }
		 		     	        }
		 		    	    },
		 		    	    legend: {
		 		    	        data:['客户消费次数']
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
		 		    	            type : 'value',name: '消费次数/次'
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
		 		    	            name:'客户消费次数',
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
		$.ajax({     
			url: "${nvix}/nvixnt/vixntMembershipStructureAnalysisAction!memberAnalysisJsonA.action",  
	     	type: "POST",//会员总数+会员卡总数+会员卡总积分+会员卡平均积分
	     	data: {controlSQL:''},
	        dataType: "json",
	 		success:function(json){
	 			$("#d01num").text(json.MemberTotal+'人'); 
	 			$("#d02num").text(json.MemberCardTotal+'张');
	 			$("#d03num").text((json.MemberTotalMoneySab).toFixed(2)+'元'); //原先是‘会员卡总积分’现在改为‘会员消费总金额’   
	 			$("#d04num").text((json.MemberStillMoney).toFixed(2)+'元');//改为‘会员卡总余额’  
		}}); 
		queryMethod_A();
		queryMethod_B();
		queryMethod_C('Day30','Day30');
	})
</script>
<script type="text/javascript">
	var stockRecordsColumns = [ {
	"width" : "7%",
	"orderable" : false,
	"data" : function(data) {
		return "&nbsp;";
	}
	}, {
	"width" : "15%",
	"name" : "code",
	"data" : function(data) {
		return data.code;
	}
	}, {
	"width" : "26%",
	"name" : "name",
	"data" : function(data) {
		return data.name;
	}
	}, {
	"width" : "26%",
	"name" : "price",
	"data" : function(data) {
		return data.price;
	}
	}, {
	"width" : "7%",
	"orderable" : false,
	"data" : function(data) {
		var update = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"goSaveOrUpdate('" + data.id + "');\" title='修改'><span class='txt-color-blue pull-right'><i class='fa fa-pencil'></i></span></a>";
		var del = "<a href='javascript:void(0);' class='btn btn-xs btn-default' onclick=\"deleteById('" + data.id + "');\" title='删除'><span class='txt-color-red pull-right'><i class='fa fa-times'></i></span></a>";
		return update + "&nbsp;&nbsp;" + del;

	}
	} ];
	var stockRecordsTable = initDataTable("stockRecordsTableId", "${nvix}/nvixnt/vixntProductDisassemblyAction!goSingleList.action", stockRecordsColumns, function(page, pageSize, orderField, orderBy) {
		var title = $("#mytitle").val();
		title = Url.encode(title);
		return {
		"page" : page,
		"pageSize" : pageSize,
		"title" : title
		};
	});
	function goList(dateType) {
		loadContent('', '${nvix}/nvixnt/vixntStoreSalesStatisticsAction!goList.action?dateType=' + dateType);
	};
</script>