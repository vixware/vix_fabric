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
<script src="${nvix}/vixntcommon/base/js/plugin/echarts/echarts.min.js"></script>
<div id="content">
	<div class="row">
		<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
			<h1 class="page-title txt-color-blueDark">
				<i class="fa fa-list-alt fa-bar-chart-o"></i> 智能分析 <span>> 会员画像分析 </span>
			</h1>
		</div>
		<div class="col-xs-12 col-sm-5 col-md-5 col-lg-8"></div>
	</div>

	<div class="row">
				   <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<div class="col-xs-4 col-sm-4 text-center" style="width:20%">
								<div class="well padding-10 " style="height:110px;background-color:#EF9178;">
									<div class="text-center">
										    <h5>  <!-- drawMember 会员画像分析页面  drawMberPageSoy -->
<a class="myHoverLine" href="javascript:;" onClick="controlSQLWdMethod('suiaAMACp{MemberAll}','drawMberPageSoy');"><span class="font-lg txt-color-red padding-5" id="AllCustomerSpan">0人</span></a>
												<div class="padding-5"></div>
												<strong class="txt-color-greenLight">全部客户</strong>
											</h5>
											<div class="padding-5"></div>
									</div>
								</div>
							</div>
							
							<div class="col-xs-2 col-sm-2 text-center" style="width:20%">
								<div class="well padding-10 " style="height:110px;background-color:#4AB1E3;">
									<div class="text-center">
										<h5>
<a class="myHoverLine" href="javascript:;" onClick="controlSQLTwo('LatelyMonths{1}','LatelyMonths{1}','3','JaeSgtdg{MainCustomer}','drawMberPageSoy');"><span class="font-lg txt-color-red padding-5" id="MainCustomerSpan">0人</span></a>
											<div class="padding-5"></div>
											<strong class="txt-color-greenLight">主力客户</strong>
										</h5>
										<div class="padding-5"></div>
									</div>
								</div>
							</div>
							
							<div class="col-xs-2 col-sm-2 text-center" style="width:20%">
								<div class="well padding-10 " style="height:110px;background-color:#38B7AF;">
									<div class="text-center">
										<h5>
<a class="myHoverLine" href="javascript:;" onClick="controlSQLTwo('LatelyMonths{1}','LatelyMonths{2}','0','JaeSgtdg{WillLossCustomer}','drawMberPageSoy');"><span class="font-lg txt-color-red padding-5" id="WillLossCustomerSpan">0人</span></a>
											<div class="padding-5"></div>
											<strong class="txt-color-greenLight">将流失客户</strong>
										</h5>
										<div class="padding-5"></div>
									</div>
								</div>
							</div>
							
							<div class="col-xs-2 col-sm-2 text-center" style="width:20%">
								<div class="well padding-10 " style="height:110px;background-color:#CA95FF;">
									<div class="text-center">
										<h5>
<a class="myHoverLine" href="javascript:;" onClick="controlSQLTwo('LatelyMonths{3}','LatelyMonths{3}','0','JaeSgtdg{AlreadyLostCustomer}','drawMberPageSoy');"><span class="font-lg txt-color-red padding-5" id="AlreadyLostCustomerSpan">0人</span></a>
											<div class="padding-5"></div>
											<strong class="txt-color-greenLight">已流失客户</strong>
										</h5>
										<div class="padding-5"></div>
									</div>
								</div>
							</div>
							
							<div class="col-xs-2 col-sm-2 text-center" style="width:20%">
								<div class="well padding-10 " style="height:110px;background-color:#DFBD50;">
									<div class="text-center">
										<h5>
<a class="myHoverLine" href="javascript:;" onClick="controlSQLTwo('no','no','1','JaeSgtdg{NewCustomer}','drawMberPageSoy');"><span class="font-lg txt-color-red padding-5" id="NewCustomerSpan">0人</span></a>
											<div class="padding-5"></div>
											<strong class="txt-color-greenLight">新客户</strong>
										</h5>
										<div class="padding-5"></div>
									</div>
								</div>
							</div>
							
						</div>
				   </div>

	<div class="row">
		<div class="col-sm-12 col-md-12">
			<div class="well">
				<div class="row">
					<div class="col-sm-6 col-md-6">
						<div id="wid-id-4" class="jarviswidget jarviswidget-sortable" data-widget-editbutton="false" role="widget">
							<header>
								<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i></span>
								<h2>客户流失风险</h2>
							</header>
							<div>
								<div class="widget-body no-padding">
									<div id="dataViewC" style="height: 400px"></div>
								</div>
								
								<div class="form-group has-feedback" style="height: 100px">
									<p><strong>新客户：</strong>只消费1次的客户</p>   
									<p><strong>主力客户：</strong>最近1个月消费3次及以上的客户</p>
									<p><strong>将流失客户：</strong>最近1个月未到店消费，而之前的最近上个月来过店消费的客户</p>
									<p><strong>已流失的客户：</strong>最近3个月未到店消费的客户并且客户的注册时间在3个月之前</p>
								</div>
							</div>
						</div>
					</div>
					<div class="col-sm-6 col-md-6">
						<div id="wid-id-4" class="jarviswidget jarviswidget-sortable" data-widget-editbutton="false" role="widget">
							<header>
								<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i></span>
								<h2>高价值客户分析</h2>
							</header>
							<div>
								<div class="widget-body no-padding">
									<div id="dataViewD" style="height: 400px"></div>
									
									<div class="form-group has-feedback" style="height: 100px">
										<p><strong>&nbsp;&nbsp;&nbsp;忠诚客户：</strong>最近1个月消费3次及其以上并且最近上个月也消费3次及其以上的客户</p>   
										<p><strong>&nbsp;&nbsp;&nbsp;瞌睡客户：</strong>最近上个月消费3次及其以上，但最近1个月消费1-3次的客户</p>
										<p><strong>&nbsp;&nbsp;&nbsp;半睡客户：</strong>最近1个月消费总次数大于等于1次，小于等于3次的客户</p>
									</div>
								</div>
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
			<h2>客户消费次数分析</h2> 
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
	
	<div class="jarviswidget">
		<header>
			<span class="widget-icon"> <i class="fa fa-bar-chart-o"></i></span>
			<h2>会员消费次数排行</h2>
		</header>
		<div class="row">
			<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div>
					<div class="widget-body no-padding">
						<div id="dataViewB" style="height: 400px"></div>
					</div>
				</div>
			</article>
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
function controlSQLTwo(startTime,endTime,frequency,changeSQL,fromPage){//不同客户的查询  (changeSQL:JaeSgtdg{NewCustomer}新客户)
	$.ajax({  
		url : '${nvix}/nvixnt/vixntMemberManageDataAction!startsWithForListPage.action',
		data: {startTime:startTime,endTime:endTime,frequency:frequency,changeSQL:changeSQL,fromPage:fromPage},  
		cache : false,
		success : function(html) {
			$("#mainContent").html(html); 
		}
	});
}
function padleft0(obj) {//补齐两位数的函数 
    return obj.toString().replace(/^[0-9]{1}$/, "0" + obj);  
}
function timeClear(divrow){ //清空时间
		 $("#"+divrow+" input[name='startCreateTime']").each(function(){
			    $(this).val("开始时间");
		});
		 $("#"+divrow+" input[name='endCreateTime']").each(function(){
			    $(this).val("结束时间");
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
		

function queryMethod_B(startTime,endTime) {
	$.ajax({    
		url: "${nvix}/nvixnt/vixntCategoryAnalysisAction!categoryAnalysisViewA.action",  //客户消费次数分析
     	type: "POST",
     	data: {startTime:startTime,endTime:endTime},
        dataType: "json",
 		success:function(json){
 			var myChart = echarts.init(document.getElementById('dataViewA'));
 			var myColors = ['#66CC66', '#434348', '#90ed7d', '#f7a35c', '#8085e9','#FF3366', '#e4d354', '#8085e8', '#8d4653', '#91e8e1','#66FF00'];
					var option = {
		 		    	    title : {
		 		    	        text: '最近30日客户消费次数分析',
		 		    	    },
		 		    	    tooltip : {
		 		    	        trigger: 'axis',
formatter: '{b}<br/> <span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'+myColors[0]+'"></span>{a0}: {c0}人<br/>'+
	/* '<span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'+myColors[1]+'"></span>{a1}: {c1}元<br/>'+ 
	 '<span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'+myColors[2]+'"></span>{a2}: {c2}元<br/>'+ 
	'<span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'+myColors[3]+'"></span>{a3}: {c3}元<br/>'+ 
	'<span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'+myColors[4]+'"></span>{a4}: {c4}元<br/>'+ 
	'<span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'+myColors[5]+'"></span>{a5}: {c5}元<br/>'+ */
		'',
		 		    	   		axisPointer: {
		 		       	        	 type: 'shadow',
		 		     	            crossStyle: {
		 		     	                color: '#FFFF33'
		 		     	            }
		 		     	        }
		 		    	    },
		 		    	    legend: {
		 		    	        data:['相应人数']
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
		 		    	            data : json.nameArr//["001", "001", "001", "001", "001", "001", "001", "001", "001", "001", "001", "001"]
		 		    	        }
		 		    	    ],
		 		    	    yAxis : [
		 		    	        {
		 		    	            type : 'value',name: '人数/人'
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
		 		    	            name:'相应人数',
		 		    	            type:'bar',
		 		    	            data: json.valueArr,   //[211, 344, 466, 555, 666, 799, 921, 1043, 1143, 1133, 1233, 1383],
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
function queryMethod_C(startTime,endTime) {
	$.ajax({    
		url: "${nvix}/nvixnt/vixntCategoryAnalysisAction!categoryAnalysisViewB.action", 
     	type: "POST",
     	data: {startTime:startTime,endTime:endTime,topNum:'10'},
        dataType: "json",
 		success:function(json){
     		var myChart = echarts.init(document.getElementById('dataViewB'));
			var	option = {
			   	    title: {
			   	    	left: 'left',
			   	        text: '最近30日客户消费次数Top10',
			   	        subtext: ''
			   	    },
			   		 tooltip : {
		    	        trigger: 'axis',
		    	        formatter: '{b}<br/> <span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:#3EA0D8"></span>{a0} : {c0}次<br/>'
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
			   	        data: ['客户消费次数']
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
			   	            name: '客户消费次数',
			   	            type: 'bar',
			   	         	data: json.valueArr, //参考  data: [30,78,111,178],
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
			   	                    formatter: '{c}次'
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
function customerLossRisk_A(startTime,endTime,frequency) {//customerLossRisk 客户流失风险 | A 主力客户 | frequency 频率，次数
	var MainCustomer = 0;
	$.ajax({    
		url: "${nvix}/nvixnt/vixntCategoryAnalysisAction!customerLossRiskA.action", 
     	type: "POST",
     	async: false,
     	data: {startTime:startTime,endTime:endTime,frequency:frequency},
        dataType: "json",
 		success:function(json){
     	  MainCustomer = json.jsonOnlyOne; 
	}});
	return  MainCustomer;// 返回‘ 主力客户 ’ 的人数
}
function customerLossRisk_B(startTime,endTime,frequency) {//customerLossRisk 客户流失风险 | B 新客户 | frequency 频率，次数
	var NewCustomer = 0;
	$.ajax({    
		url: "${nvix}/nvixnt/vixntCategoryAnalysisAction!customerLossRiskB.action", 
     	type: "POST",
     	async: false,
     	data: {startTime:startTime,endTime:endTime,frequency:frequency},
        dataType: "json",
 		success:function(json){
 		 NewCustomer = json.jsonOnlyOne; 
	}});
	return  NewCustomer;// 返回‘ 新客户’ 的人数
}
function customerLossRisk_C(startTime,endTime,frequency) {//customerLossRisk 客户流失风险 | C 已经流失客户 | frequency 频率，次数 AlreadyLost
	var AlreadyLostCustomer = 0;
	$.ajax({    
		url: "${nvix}/nvixnt/vixntCategoryAnalysisAction!customerLossRiskC.action", 
     	type: "POST",
     	async: false,
     	data: {startTime:startTime,endTime:endTime,frequency:frequency},
        dataType: "json",
 		success:function(json){
 			AlreadyLostCustomer = json.jsonOnlyOne; 
	}});
	return  AlreadyLostCustomer;// 返回‘ 新客户’ 的人数
}
function customerLossRisk_D(startTime,endTime,frequency) {//customerLossRisk 客户流失风险 | D 将要流失客户 | frequency 频率，次数WillLoss 将要流失
	var WillLossCustomer = 0;
	$.ajax({    
		url: "${nvix}/nvixnt/vixntCategoryAnalysisAction!customerLossRiskD.action", 
     	type: "POST",
     	async: false,
     	data: {startTime:startTime,endTime:endTime,frequency:frequency},
        dataType: "json",
 		success:function(json){
 			WillLossCustomer = json.jsonOnlyOne; 
	}});
	return  WillLossCustomer;// 返回‘ 将要流失客户 ’ 的人数
}
function customerLossRisk_E(startTime,endTime) {//customerLossRisk 客户流失风险 | E 全部客户 
	var AllCustomer = 0;
	$.ajax({    
		url: "${nvix}/nvixnt/vixntCategoryAnalysisAction!customerLossRiskE.action", 
     	type: "POST",
     	async: false,
     	data: {startTime:startTime,endTime:endTime},
        dataType: "json",
 		success:function(json){
 			AllCustomer = json.jsonOnlyOne; 
	}});
	return  AllCustomer;// 返回‘ 将要流失客户 ’ 的人数
}
function customerLossRisk_Total() {
	var MainCustomer = customerLossRisk_A('LatelyMonths{1}','LatelyMonths{1}','3');// 最近 1个月  | 3代表3次或3次以上 | 主力客户人数
	var NewCustomer = customerLossRisk_B('no','no','1');// 最近 1个月  | 只消费1次 | 新客户人数
	var AlreadyLostCustomer =customerLossRisk_C('LatelyMonths{3}','LatelyMonths{3}','0');// 最近 3个月  | 没有消费 |已经流失客户
	var WillLossCustomer =customerLossRisk_D('LatelyMonths{1}','LatelyMonths{2}','0');//LatelyMonths{1}代表最近1个月没有消费过|LatelyMonths{2}代表最近2个月-最近1个月|返回‘ 将要流失客户 ’ 的人数
	var AllCustomer = customerLossRisk_E('no','no');// 全部客户 
	$("#MainCustomerSpan").text( MainCustomer+'人'); 
	$("#NewCustomerSpan").text( NewCustomer+'人');
	$("#AlreadyLostCustomerSpan").text( AlreadyLostCustomer+'人');
	$("#WillLossCustomerSpan").text( WillLossCustomer+'人');
	$("#AllCustomerSpan").text( AllCustomer+'人');
		
		var myChart2 = echarts.init(document.getElementById('dataViewC'));
		var myColours = ['#CC3399','#FF3399','#7900FF','#BD514B'];
	var	option2 = {
		title : {
			        text: '',
			        x:'center',
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
	        data:['新客户','主力客户','将流失客户','已经流失客户'] //arrayLegend//参考['储值卡','计次卡','储值计次卡']
	    },
	    series : [
	        {
	            name: '客户流失风险',
	            type: 'pie',
	            radius : '55%',
	            center: ['50%', '60%'],
	            data:  [
		                {value:NewCustomer, name:'新客户'},
		                {value:MainCustomer, name:'主力客户'},
		                {value:WillLossCustomer, name:'将流失客户'},
		                {value:AlreadyLostCustomer, name:'已经流失客户'}
		            ],   //arraySeries,  
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
}
function highValueMember_A(startTime,endTime,frequency) {//highValueMember 高价值客户分析 |A 忠诚客户   |Loyal 忠诚
	var LoyalCustomer = 0;
	$.ajax({    
		url: "${nvix}/nvixnt/vixntCategoryAnalysisAction!highValueMemberA.action", 
     	type: "POST",
     	async: false,
     	data: {startTime:startTime,endTime:endTime,frequency:frequency},
        dataType: "json",
 		success:function(json){
 			LoyalCustomer = json.jsonOnlyOne; 
	}});
	return  LoyalCustomer;// 返回‘ 忠诚客户 ’ 的人数
}
function highValueMember_B(startTime,endTime,frequency) {//highValueMember 高价值客户分析 |B 瞌睡客户   |Doze 瞌睡
	var DozeCustomer = 0;
	$.ajax({    
		url: "${nvix}/nvixnt/vixntCategoryAnalysisAction!highValueMemberB.action", 
     	type: "POST",
     	async: false,
     	data: {startTime:startTime,endTime:endTime,frequency:frequency},
        dataType: "json",
 		success:function(json){
 			DozeCustomer = json.jsonOnlyOne; 
	}});
	return  DozeCustomer;// 返回‘ 瞌睡客户 ’ 的人数
}
function highValueMember_C(startTime,endTime,frequency) {//highValueMember 高价值客户分析 |C 半睡客户   |HalfAsleep半睡
	var HalfAsleepCustomer = 0;
	$.ajax({    
		url: "${nvix}/nvixnt/vixntCategoryAnalysisAction!highValueMemberC.action", 
     	type: "POST",
     	async: false,
     	data: {startTime:startTime,endTime:endTime,frequency:frequency},
        dataType: "json",
 		success:function(json){
 			HalfAsleepCustomer = json.jsonOnlyOne; 
	}});
	return  HalfAsleepCustomer;// 返回‘ 半睡客户 ’ 的人数
}
function highValueMember_Total() {
	var LoyalCustomer =highValueMember_A('LatelyMonths{1}','LatelyMonths{2}','3');//忠诚客户
	var DozeCustomer =highValueMember_B('LatelyMonths{1}','LatelyMonths{2}','3');//瞌睡客户
	var HalfAsleepCustomer =highValueMember_C('LatelyMonths{1}','NO','3');//半睡客户
	 
	var myChart2 = echarts.init(document.getElementById('dataViewD'));
	var myColours = ['#99FF00','#4C974A','#F894D4','#7D2D12','#91D5E4','#C0C0C0','#EFCDB2','#CC00FF','#303030','#DAD94B','#3060A2'];// 高价值客户分析 专用颜色数组
var	option2 = {
	title : {
		        text: '',
		        x:'center',
        subtextStyle:{  //副标题样式
            fontSize: 16,
        	color: '#FF0033'
        } 
		},
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c}人 ({d}%)"
    },
    color:myColours,
    legend: {
        orient: 'horizontal',
        top: 'top',
        data:['忠诚客户','瞌睡客户','半睡客户'] //arrayLegend//参考['储值卡','计次卡','储值计次卡']
    },
    series : [
        {
            name: '高价值客户分析',
            type: 'pie',
            radius : '55%',
            center: ['50%', '60%'],
            data:  [
	                {value:LoyalCustomer, name:'忠诚客户'},
	                {value:DozeCustomer, name:'瞌睡客户'},
	                {value:HalfAsleepCustomer, name:'半睡客户'}
	            ],   //arraySeries,  
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
	
}
	$(document).ready(function() {
		pageSetUp();
		// PAGE RELATED SCRIPTS
		queryMethod_B('Day30','Day30');
		queryMethod_C('Day30','Day30');
		customerLossRisk_Total();
		highValueMember_Total();
	})
</script>